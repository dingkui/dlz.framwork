package com.dlz.framework.db;

import com.dlz.comm.exception.DbException;
import com.dlz.comm.exception.SystemException;
import com.dlz.comm.json.JSONMap;
import com.dlz.comm.util.JacksonUtil;
import com.dlz.comm.util.StringUtils;
import com.dlz.comm.util.ValUtil;
import com.dlz.framework.db.convertor.ConvertUtil;
import com.dlz.framework.db.enums.ParaTypeEnum;
import com.dlz.framework.db.modal.BaseParaMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.items.SqlItem;
import org.slf4j.Logger;

import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * sql操作Util
 *
 * @author ding_kui 2010-12-14
 */
public class SqlUtil {

    private static Logger logger = org.slf4j.LoggerFactory.getLogger(SqlUtil.class);
    private final static String BLOB_CHARSETNAME = "dbset.blob_charsetname";
    public final static String SU_STR_TABLE_NM = "SU_STR_TABLE_NM";
    public final static String SU_STR_UPDATE_KEYS = "SU_STR_UPDATE_KEYS";

    /**
     * 参数匹配符：如  ?
     */
    private static Pattern PATTERN_PARA = Pattern.compile("\\?");
    /**
     * 替换内容匹配符：如  ${bb}
     */
    private static Pattern PATTERN_REPLACE = Pattern.compile("\\$\\{(\\w[\\.\\w]*)\\}");
    /**
     * 预处理匹配符：如   #{aa}
     */
    private static Pattern PATTERN_PREPARE = Pattern.compile("#\\{(\\w+[\\.\\w]*)\\}");
    /**
     * 条件判断符号（只用作条件判断，不做输出）：如   ^#{cc}
     */
    private static Pattern PATTERN_NONE = Pattern.compile("\\^#\\{(\\w[\\.\\w]*)\\}");
    /**
     * 条件语句匹配 ：如   [xxx #{aa} ${bb} ^#{cc}]
     */
    private static Pattern PATTERN_CONDITION = Pattern.compile("\\[([^\\^][^\\[\\]]*)\\]");
    /**
     * 是否数字
     */
    private static Pattern PATTERN_ISNUM = Pattern.compile("^[\\d\\.-]+$");


//    private static AColumnNameConvertor columnMapper = new ColumnNameCamel();
//
//    static void setMapper(AColumnNameConvertor mapper) {
//        columnMapper = mapper;
//    }

    public static String getBlobCharsetname() {
        return DbInfo.getDbset(BLOB_CHARSETNAME);
    }



    /**
     * 转换参数
     *
     * @param paraMap
     * @return
     * @throws Exception
     * @author dk 2015-04-09
     */
    public static BaseParaMap dealParmToJdbc(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        String sql = sqlItem.getSqlRun();
        List<Object> paraList = new ArrayList<Object>();
        if (sql != null) {
            StringBuffer sb = new StringBuffer();
            int beginIndex = 0;
            Matcher mat = PATTERN_PREPARE.matcher(sql);
            JSONMap para = new JSONMap(paraMap.getPara());
            para.putAll(sqlItem.getSystemPara());
            while (mat.find()) {
                sb.append(sql.substring(beginIndex, mat.start()));
                sb.append("?");
                String group = mat.group(1);
                paraList.add(JacksonUtil.at(para, group));
                beginIndex = mat.end();
            }
            sb.append(sql.substring(beginIndex));
            sqlItem.setSqlJdbc(sb.toString());
            sqlItem.setSqlJdbcPara(paraList.toArray());
        }
        return paraMap;
    }

    /**
     * 转换参数
     *
     * @param paraMap
     * @return
     * @throws Exception
     * @author dk 2015-04-09
     */
    public static SqlItem dealParm(BaseParaMap paraMap) {
        SqlItem sqlItem = paraMap.getSqlItem();
        String sql = sqlItem.getSqlDeal();
        String sqlInput = sqlItem.getSqlKey();
        if (sql == null && sqlInput != null) {
            sql = createSqlDeal(paraMap.getPara(), sqlInput);
//			sql=PATTERN_PREPARE.matcher(sql).replaceAll("#{para.$1}");
            sqlItem.setSqlRun(sql);

//			if(sql.matches("[\\s]*(?i)select.*") && !sql.matches("[\\s]*(?i)select count(\\*).*") ){
//				paraMap.creatPage();
//			}
        }
        return sqlItem;
    }

    /**
     * 转换参数
     *
     * @param sql
     * @param para
     * @return
     * @throws Exception
     * @author dk 2015-04-09
     */
    public static BaseParaMap getParmMap(String sql, Object... para) {
//		if("1".equals(DbInfo.getDbset("run.jdbc"))){
        BaseParaMap paraMap = new BaseParaMap(null);
        SqlItem sqlItem = paraMap.getSqlItem();
        sqlItem.setSqlJdbc(sql);
        sqlItem.setSqlJdbcPara(para);
        return paraMap;
//		}
//		ParaMap paraMap = new ParaMap(sql);
//		if(para.length<1){
//			return paraMap;
//		}
//		Matcher mat = PATTERN_PARA.matcher(sql);
//		StringBuffer sb = new StringBuffer();
//		int i=1;
//		while(mat.find()){
//			if(para.length<i){
//				throw new SystemException("sql中参数数量与传入参数不符");
//			}
//			paraMap.addPara("p"+i, para[i-1]);
//			mat.appendReplacement(sb, "#\\{p"+i+"\\}");
//			i++;
//		}
//		paraMap.setSqlInput(sb.toString());
//		return paraMap;
    }

    /**
     * 创建执行sql(带替换符)
     *
     * @param para
     * @param sql
     * @return
     * @throws Exception
     * @author dk 2015-04-09
     */
    private static String createSqlDeal(Map<String, Object> para, String sql) {
        sql = DbInfo.getSql(sql);
        sql = getConditionStr(sql, para);
        sql = replaceSql(sql, para, 0);
        sql = sql.replaceAll("[\\s]+", " ");
        return sql;
    }

    /**
     * 转换成翻页sql
     *
     * @return
     * @throws Exception
     */
    public static String createPageSql(BaseParaMap paraMap) {
		SqlItem sqlItem = paraMap.getSqlItem();
		String sqlDeal = sqlItem.getSqlDeal();
		SystemException.notNull(sqlDeal, "sqlDeal不应该为空");
		Page page = paraMap.getPage();
		if (page == null) {
			sqlItem.setSqlRun(sqlDeal);
			return sqlDeal;
		}
		String sqlPage = sqlItem.getSqlPage();
		if (sqlPage == null) {
			String _orderBy = StringUtils.isEmpty(page.getSortField()) ? null : (ConvertUtil.str2Clumn(page.getSortField()) + " " + (page.getSortOrder() == null ? "" : page.getSortOrder()));
			Integer _begin = null;
			Integer _end = null;

			if (page.getPageIndex() == 0) {
				_begin = null;
				_end = page.getPageSize();
			} else {
				_begin = page.getPageIndex() * page.getPageSize();
				_end = _begin + page.getPageSize();
			}

			Map<String, Object> p = new HashMap<String, Object>();
			p.put("_sql", sqlDeal);
			p.put("_begin", _begin);
			p.put("_end", _end);
			p.put("_pageSize", page.getPageSize());
			p.put("_orderBy", _orderBy);
			sqlPage = createSqlDeal(p, "key.comm.pageSql");
            sqlItem.getSystemPara().putAll(p);
		}
		sqlItem.setSqlRun(sqlPage);
		return sqlPage;
    }

    /**
     * 转换成查询条数sql
     *
     * @return
     * @throws Exception
     */
    public static String createCntSql(SqlItem sqlItem) {
        String sqlCnt = sqlItem.getSqlCnt();
//		if(sql==null){
//			Map<String,Object> p=new HashMap<String,Object>();
//			p.put("_sql", sqlItem.getSqlRun());
//			sql=createSqlRun(p, "key.comm.cntSql");
//			sqlItem.setSql_cnt(sql);
//		}
        if (sqlCnt == null) {
			String sqlDeal = sqlItem.getSqlDeal();
            int from = sqlDeal.toLowerCase().indexOf(" from ");
            SystemException.isTrue(from == -1, "sql语句无from：" + sqlDeal);
			sqlCnt = "select count(1) from" + sqlDeal.substring(from + 5);
        }
		sqlItem.setSqlRun(sqlCnt);
        return sqlCnt;
    }


    /**
     * sql 语句中 ${aa} 的内容进行文本替换
     *
     * @param sql
     * @param m
     * @param replaceTimes
     * @return
     */
    private static String replaceSql(String sql, Map<String, Object> m, int replaceTimes) {
        int length = sql.length();
        if (length > 10000 || replaceTimes++ > 3000) {
            throw new DbException("sql过长或出现引用死循环！", 1002);
        }
        Matcher mat = PATTERN_REPLACE.matcher(sql);
        int start = 0;
        StringBuffer sb = new StringBuffer();
        while (mat.find()) {
            String key = mat.group(1);
            Object o = JacksonUtil.at(m, key);
            if (o == null && key.startsWith("key.")) {
                o = getConditionStr(DbInfo.getSql(key), m);
            }
            String matStr = "";
            if (o != null) {
                if (o instanceof Object[] || o instanceof Collection) {
                    matStr = getSqlInStr(o);
                } else {
                    matStr = String.valueOf(o == null ? "" : o);
                }
            }
            sb.append(sql, start, mat.start());
            sb.append(replaceSql(matStr, m, replaceTimes));
            start = mat.end();
        }
        if (start == 0) {
            return sql;
        }
        sb.append(sql, start, length);
        return replaceSql(sb.toString(), m, replaceTimes);
    }

    /**
     * sql 语句中 [] 提交内容进行处理
     *
     * @param sql
     * @param m
     * @return
     */
    private static String getConditionStr(String sql, Map<String, Object> m) {
        Matcher mat = PATTERN_CONDITION.matcher(sql);
        int start = 0;
        StringBuffer sb = new StringBuffer();
        while (mat.find()) {
            String conditionInfo = mat.group(1);
            boolean append = false;

            Matcher mat2 = PATTERN_PREPARE.matcher(conditionInfo);
            while (mat2.find()) {
                if (isNotEmpty(m, mat2.group(1))) {
                    append = true;
                    break;
                }
            }
            if (!append) {
                Matcher mat3 = PATTERN_REPLACE.matcher(conditionInfo);
                while (mat3.find()) {
                    if (isNotEmpty(m, mat3.group(1))) {
                        append = true;
                        break;
                    }
                }
            }

            sb.append(sql, start, mat.start());
            if (append) {
                sb.append(PATTERN_NONE.matcher(conditionInfo).replaceAll(""));
            }
            start = mat.end();
        }

        if (start == 0) {
            return sql;
        }
        sb.append(sql, start, sql.length());
        return getConditionStr(sb.toString(), m);
    }

    private static boolean isNotEmpty(Map<String, Object> m, String key) {
        Object o = JacksonUtil.at(m, key);
        return o != null && !"".equals(o);
    }

    /**
     * 将数据库查询对象转换成String
     * @param m
     * @author dk 2015-04-09
     * @return
     */
//	public static Object coverObject2String(Object o){
//		if(o==null){
//			return null;
//		}
////		if(o instanceof BLOB){
////			try {
////				BLOB blob = (BLOB)o;
////				return  new String(blob.getBytes((long)1, (int)blob.length()),getBlobCharsetname());
////			} catch (SQLException e) {
////				logger.error(e.getMessage(),e);
////				return null;
////			} catch (UnsupportedEncodingException e) {
////				logger.error(e.getMessage(),e);
////				return null;
////			}
////		} 
//		return o;
//	}

    /**
     * 将参数转换成对应的Object
     *
     * @param value
     * @param pte
     * @return
     * @author dk 2015-04-09
     */
    public static Object coverString2Object(String value, ParaTypeEnum pte) {
        try {
            switch (pte) {
                case Blob:
                    return value.getBytes(SqlUtil.getBlobCharsetname());
                case Date:
                    return ValUtil.getDate(value);
                default:
                    return value;
            }
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return value;
    }


    /**
     * 创建表插入语句
     *
     * @param p
     * @return
     * @throws SQLException
     */
    public static String createInsertSql(Map<String, Object> p) throws SQLException {
        String tableNm = (String) p.get(SU_STR_TABLE_NM);
        if (tableNm == null) {
            throw new SQLException("创建sql出错，参数中缺少表名：" + SU_STR_TABLE_NM);
        }
        p.remove(SU_STR_TABLE_NM);

        StringBuffer sb1 = new StringBuffer("insert into " + tableNm + "(");
        StringBuffer sb2 = new StringBuffer(" values (");
        Iterator<Entry<String, Object>> it = p.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String columeNm = entry.getKey();
            String valueStr = "#{" + columeNm + "}";
            sb1.append((String) entry.getKey() + ",");
            sb2.append(valueStr + ",");
        }
        return sb1.substring(0, sb1.length() - 1) + ")" + sb2.substring(0, sb2.length() - 1) + ")";
    }

    /**
     * 创建表更新语句
     *
     * @param p
     * @param endSql
     * @return
     * @throws SQLException
     */
    public static String createUpdateSql(Map<String, Object> p, String endSql) throws SQLException {
        String tableNm = (String) p.get(SU_STR_TABLE_NM);
        if (tableNm == null) {
            throw new SQLException("创建sql出错，参数中缺少表名：" + SU_STR_TABLE_NM);
        }
        p.remove(SU_STR_TABLE_NM);

        String keys = (String) p.get(SU_STR_UPDATE_KEYS);
        if (keys == null) {
            throw new SQLException("创建sql出错，参数中缺少检索条件：" + SU_STR_UPDATE_KEYS);
        }
        p.remove(SU_STR_UPDATE_KEYS);
        // where语句
        StringBuffer sb2 = new StringBuffer(" where 1=1");
        for (String key : keys.split(",")) {
            if (p.containsKey(key)) {
                String valueStr = "#{" + key + "}";
                sb2.append(" and " + key + "=" + valueStr);
                p.remove(key);
            } else {
                throw new SQLException("参数中键值未设定：" + key);
            }
        }
        if (endSql != null) {
            sb2.append(endSql);
        }
        if (sb2.toString().equals(" where 1=1 ")) {
            throw new SQLException("更新条件为空：" + p);
        }
        // update语句
        StringBuffer sb1 = new StringBuffer("update " + tableNm + " set ");
        Iterator<Entry<String, Object>> it = p.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String columeNm = String.valueOf(entry.getKey());
            String valueStr = "#{" + columeNm + "}";
            sb1.append((String) entry.getKey() + "=");
            sb1.append(valueStr + ",");
        }
        // sql语句拼接
        return sb1.substring(0, sb1.length() - 1) + sb2.toString();
    }


    /**
     * 创建单表查询语句
     *
     * @param p
     * @param endSql
     * @return list
     * @throws SQLException
     */
    public static String createSelectSql(Map<String, Object> p, String endSql) throws SQLException {
        String tableNm = (String) p.get(SU_STR_TABLE_NM);
        if (tableNm == null) {
            throw new SQLException("创建sql出错，参数中缺少表名：" + SU_STR_TABLE_NM);
        }
        tableNm = tableNm.toUpperCase();
        p.remove(SU_STR_TABLE_NM);

        StringBuilder sql = new StringBuilder("SELECT *");
        sql.append(",'" + tableNm + "' " + SU_STR_TABLE_NM);
        sql.append(" FROM " + tableNm);
        sql.append(" WHERE 1=1 ");

        Iterator<Entry<String, Object>> it = p.entrySet().iterator();
        while (it.hasNext()) {
            Entry<String, Object> entry = it.next();
            String columeNm = String.valueOf(entry.getKey());
            String value = String.valueOf(entry.getValue());
            String valueStr = "#{" + columeNm + "}";
            if (value == null) {
                sql.append(" and " + entry.getKey() + " is null");
            } else {
                sql.append(" and " + entry.getKey() + "=" + valueStr);
            }
        }
        if (endSql != null) {
            sql.append(endSql);
        }
        return sql.toString();
    }

    public static String getSqlInStr(Object o) {
        if (StringUtils.isEmpty(o)) {
            throw new SystemException("转换成in的参数不能为空！");
        }
        boolean isNum = true;
        if (o instanceof CharSequence) {
            String valueOf = String.valueOf(o);
            o = valueOf.replaceAll("\\s*,\\s*", ",").trim().split(",");
        } else if (o instanceof Collection) {
            o = StringUtils.listToArray((Collection<?>) o);
        } else if (!(o instanceof Object[])) {
            throw new SystemException("转换成in的参数只能是字符串或者列表，数组");
        }

        Object[] o2 = (Object[]) o;
        for (int i = 0; i < o2.length; i++) {
            if (o2[i] instanceof Number) {
                continue;
            }
            String valueOf = String.valueOf(o2[i]);
            if (!PATTERN_ISNUM.matcher(valueOf).find()) {
                isNum = false;
                if (valueOf.startsWith("'") && valueOf.endsWith("'")) {
                    valueOf = valueOf.substring(1, valueOf.length() - 1);
                }
                o2[i] = valueOf.replaceAll("'", "''");
            }
        }
        if (isNum) {
            return StringUtils.join(o2, ",");
        }
        return "'" + StringUtils.join(o2, "','") + "'";
    }
}
