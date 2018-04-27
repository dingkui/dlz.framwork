package com.dlz.framework.db.modal;

import java.util.Map;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.db.exception.DbException;
import com.dlz.framework.logger.MyLogger;

/**
 * 构造单表的增删改查操作sql
 * @author dingkui
 *
 */
public class CreateSqlParaMap extends BaseParaMap{
	private static MyLogger logger = MyLogger.getLogger(CreateSqlParaMap.class);
	private static final long serialVersionUID = 8374167270612933157L;
	protected static final String STR_TABLENAME="tableName";
	protected static final String STR_WHERE="where";
	protected static final String STR_COLUMS="colums";
	@SuppressWarnings("rawtypes")
	protected CreateSqlParaMap(String Sql,String tableName,Page page){
		super(Sql,page);
		addPara(STR_TABLENAME, tableName);
	}
	public void setWhere(String where){
		addPara(STR_WHERE, where);
	}
	public void addCondition(String paraName,String option,Object value){
		if(value==null||"".equals(value)){
			logger.error("addCondition 参数不能为空:table="+getPara().get(STR_TABLENAME)+",paraName="+paraName+",option="+option);
			throw new DbException("addCondition 参数不能为空:table="+getPara().get(STR_TABLENAME)+",paraName="+paraName+",option="+option);
		}
		StringBuilder sbWhere = (StringBuilder)this.getPara().get(STR_WHERE);
		if(sbWhere==null){
			sbWhere=new StringBuilder(" where 1=1");
			addPara(STR_WHERE, sbWhere);
		}
		sbWhere.append(" and ");
		sbWhere.append(SqlUtil.converStr2ClumnStr(paraName));
		sbWhere.append(' ');
		sbWhere.append(option.equals("eq")?"=":option);
		sbWhere.append(' ');
		if(option.matches("(?i)like")){
			if(value instanceof Object[]){
				Object[] os=(Object[])value;
				sbWhere.append("'"+os[0]+"'||");
				sbWhere.append("#{").append(paraName).append("}");
				sbWhere.append("||'"+os[2]+"'");
				addPara(paraName, os[1]);
			}else{
				sbWhere.append("'%'||");
				sbWhere.append("#{").append(paraName).append("}");
				sbWhere.append("||'%'");
				addPara(paraName, value);
			}
		}else if(option.matches("(?i)between")){
			sbWhere.append("#{").append(paraName).append("1} and ");
			sbWhere.append("#{").append(paraName).append("2}");
			Object[] os=(Object[])value;
			addPara(paraName+"1", os[0]);
			addPara(paraName+"2", os[1]);
		}else if(option.matches("(?i)in")){
			sbWhere.append("(");
			sbWhere.append("\\${").append(paraName).append("s}");
			sbWhere.append(")");
			addPara(paraName+"s", value);
		}else if(option.matches("(?i)eq")||option.matches("=")){
			sbWhere.append("#{").append(paraName).append("}");
			addPara(paraName, value);
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
