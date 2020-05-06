package com.dlz.framework.db.modal;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dlz.framework.db.SqlUtil;


/**
 * 构造单表的更新操作sql
 * @author dingkui
 *
 */
public class UpdateParaMap extends CreateSqlParaMap{

	private static Logger logger=LoggerFactory.getLogger(UpdateParaMap.class);
	private static final long serialVersionUID = 8374167270612933157L;
	private static final String SQL="key.comm.updateTable";
	private static final String STR_SETS="sets";
	public UpdateParaMap(String tableName){
		super(SQL,tableName,null);
	}
	/**
	 * 添加要更新的值
	 * @param setValues
	 * @return
	 */
	public void addSetValue(String paraName,Object value){
		StringBuilder sbSets = (StringBuilder)this.getPara().get(STR_SETS);
		if(sbSets==null){
			sbSets=new StringBuilder();
			addPara(STR_SETS, sbSets);
		}
		
		paraName = SqlUtil.converStr2ClumnStr(paraName);
		String clumnName = paraName.replaceAll("`", "");
		boolean isClumnExists = isClumnExists(clumnName);
		if(!isClumnExists){
			logger.warn("clumn is not exists:"+paraName);
			return;
		}
		
		if(sbSets.length()>0){
			sbSets.append(",");
		}
		sbSets.append(paraName);
		sbSets.append('=');
		if(value instanceof String){
			String v = ((String) value);
			if(v.startsWith("sql:")){
				sbSets.append(SqlUtil.converStr2ClumnStr(v.substring(4)));
			}else{
				sbSets.append("#{").append(clumnName).append("}");
				addClunmnValue(clumnName, value);
			}
		}else{
			sbSets.append("#{").append(clumnName).append("}");
			addClunmnValue(clumnName, value);
		}
	}
	/**
	 * 添加要更新的值集合
	 * @param setValues
	 * @return
	 */
	public void addSetValues(Map<String,Object> setValues){
		for(String str:setValues.keySet()){
			addSetValue(str, setValues.get(str));
		}
	}
}
