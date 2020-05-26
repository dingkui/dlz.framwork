package com.dlz.framework.db.modal;

import com.dlz.framework.db.SqlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * 构造单表的添加操作sql
 * @author dingkui
 *
 */
public class InsertParaMap extends CreateSqlParaMap{

	private static Logger logger=LoggerFactory.getLogger(InsertParaMap.class);
	private static final long serialVersionUID = 8374167270612933157L;
	private static final String SQL="key.comm.insertTable";
	private static final String STR_COLUMS="colums";
	private static final String STR_VALUES="values";
	public InsertParaMap(String tableName){
		super(SQL,tableName,null);
	}
	public void addValue(String key,Object value){
		String paraName = SqlUtil.converStr2ClumnStr(key);
		String clumnName = paraName.replaceAll("`", "");
		boolean isClumnExists = isClumnExists(clumnName);
		if(!isClumnExists){
			logger.warn("clumn is not exists:"+paraName);
			return;
		}
		
		
		StringBuilder sbColums = (StringBuilder)this.getPara().get(STR_COLUMS);
		StringBuilder sbValues = (StringBuilder)this.getPara().get(STR_VALUES);
		if(sbColums==null){
			sbColums=new StringBuilder();
			sbValues=new StringBuilder();
			addPara(STR_COLUMS, sbColums);
			addPara(STR_VALUES, sbValues);
		}
		if(sbColums.length()>0){
			sbColums.append(',');
			sbValues.append(',');
		}
		sbColums.append(paraName);
		if(value instanceof String){
			String v = ((String) value);
			if(v.startsWith("sql:")){
				sbValues.append(SqlUtil.converStr2ClumnStr(v.substring(4)));
			}else{
				sbValues.append("#{").append(clumnName).append("}");
				addClunmnValue(clumnName, value);
			}
		}else{
			sbValues.append("#{").append(clumnName).append("}");
			if(value==null)
				value="";
			addClunmnValue(clumnName, value);
		}
	}
	
	public void addValues(Map<String,Object> values){
		for(String str:values.keySet()){
			addValue(str, values.get(str));
		}
	}
	
}
