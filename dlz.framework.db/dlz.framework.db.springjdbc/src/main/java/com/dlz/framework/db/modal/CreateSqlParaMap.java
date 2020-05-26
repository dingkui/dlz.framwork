package com.dlz.framework.db.modal;

import com.dlz.framework.db.DbCoverUtil;
import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.exception.DbException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 构造单表的增删改查操作sql
 * @author dingkui
 *
 */
public class CreateSqlParaMap extends BaseParaMap{

	private static Logger logger=LoggerFactory.getLogger(CreateSqlParaMap.class);
	private static final long serialVersionUID = 8374167270612933157L;
	protected static final String STR_TABLENAME="tableName";
	protected static final String STR_WHERE="where";
	protected static final String STR_COLUMS="colums";
	private String tableName;
	@SuppressWarnings("rawtypes")
	protected CreateSqlParaMap(String Sql,String tableName,Page page){
		super(Sql,page);
		this.tableName=tableName;
		addPara(STR_TABLENAME, tableName);
		addPara(STR_WHERE, new StringBuilder(" where 2=3"));
	}
	public void setWhere(String where){
		addPara(STR_WHERE, where);
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public CreateSqlParaMap addClunmnValue(String key,Object value){
		return addClunmnValue(key, key, value);
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public CreateSqlParaMap addClunmnValue(String key,String clumnName,Object value){
		addPara(key, DbCoverUtil.getVal4Db(tableName,clumnName, value));
		return this;
	}
	/**
	 * 添加参数
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean isClumnExists(String clumnName){
		return DbCoverUtil.isClumnExists(tableName, clumnName);
	}
	public void addCondition(String paraName,String option,Object value){
		if(value==null||"".equals(value)){
			throw new DbException("addCondition 参数不能为空:table="+getPara().get(STR_TABLENAME)+",paraName="+paraName+",option="+option,1002);
		}
		
		paraName = SqlUtil.converStr2ClumnStr(paraName);
		String clumnName = paraName.replaceAll("`", "");
		boolean isClumnExists = isClumnExists(clumnName);
		if(!isClumnExists){
			logger.warn("clumn is not exists:"+paraName);
			return;
		}
		StringBuilder sbWhere = (StringBuilder)this.getPara().get(STR_WHERE);
//		if(sbWhere==null){
//			sbWhere=new StringBuilder(" where 2=3");
//			addPara(STR_WHERE, sbWhere);
//		}
		if(sbWhere.toString().endsWith("2=3")){
			sbWhere.replace(7, 10, "1=1");
		}
		sbWhere.append(" and ");
		sbWhere.append(paraName);
		sbWhere.append(' ');
		sbWhere.append(option.equals("eq")?"=":option);
		sbWhere.append(' ');
		if(option.matches("(?i)like")){
			if(value instanceof Object[]){
				Object[] os=(Object[])value;
				sbWhere.append("'"+os[0]+"'||");
				sbWhere.append("#{").append(clumnName).append("}");
				sbWhere.append("||'"+os[2]+"'");
				addClunmnValue(clumnName, os[1]);
			}else{
				sbWhere.append("'%'||");
				sbWhere.append("#{").append(clumnName).append("}");
				sbWhere.append("||'%'");
				addClunmnValue(clumnName, value);
			}
		}else if(option.matches("(?i)between")){
			sbWhere.append("#{").append(clumnName).append("1} and ");
			sbWhere.append("#{").append(clumnName).append("2}");
			Object[] os=(Object[])value;
			addClunmnValue(clumnName+"1",clumnName, os[0]);
			addClunmnValue(clumnName+"2",clumnName, os[1]);
		}else if(option.matches("(?i)in")){
			sbWhere.append("(");
			sbWhere.append(SqlUtil.getSqlInStr(value));
			sbWhere.append(")");
		}else if(option.matches("(?i)eq")||option.matches("=")){
			sbWhere.append("#{").append(clumnName).append("}");
			addPara(clumnName, value);
		}else{
			sbWhere.append(value);
		}
	}
	
	/**
	 * 添加要更新的值和更新条件集合
	 * @param setValues
	 * @param whereValues
	 * @return
	 */
	public void addEqConditions(Map<String,Object> conditionValues){
		for(String str:conditionValues.keySet()){
			addCondition(str,"=", conditionValues.get(str));
		}
	}
	/**
	 * 添加要更新的值和更新条件集合
	 * @param setValues
	 * @param whereValues
	 * @return
	 */
	public CreateSqlParaMap addEqCondition(String paraName,Object value){
		addCondition(paraName, "=", value);
		return this;
	}
	
	public void addRequestPara(Map<String,?> requestParaMap){
		for(String key:requestParaMap.keySet()){
			if(key.startsWith("search_")){
				String[] keys=key.split("_");
				if(keys.length<3){
					continue;
				}
				String k=key.substring(keys[0].length()+keys[1].length()+2);
				if(k.length()==0){
					continue;
				}
				Object o=requestParaMap.get(key);
				if(o instanceof String[]){
					String[] paras=(String[])o;
					if(paras.length==1){
						addCondition(k,keys[1], paras[0]);
					}else{
						addCondition(k,keys[1], paras);
					}
				}else{
					addCondition(k,keys[1], o);
				}
			}
		}
	}
}
