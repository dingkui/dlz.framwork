package com.dlz.framework.db.modal;

import java.util.Map;

import com.dlz.framework.db.SqlUtil;

/**
 * 构造单表的添加操作sql
 * @author dingkui
 *
 */
public class InsertParaMap extends CreateSqlParaMap{
	private static final long serialVersionUID = 8374167270612933157L;
	private static final String SQL="key.comm.insertTable";
	private static final String STR_COLUMS="colums";
	private static final String STR_VALUES="values";
	public InsertParaMap(String tableName){
		super(SQL,tableName,null);
	}
	public void addValue(String str,Object value){
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
		sbColums.append(SqlUtil.converStr2ClumnStr(str));
		if(value instanceof String){
			String v = ((String) value);
			if(v.startsWith("sql:")){
				sbValues.append(SqlUtil.converStr2ClumnStr(v.substring(4)));
			}else{
				sbValues.append("#{").append(str).append("}");
				addPara(str, value);
			}
		}else{
			sbValues.append("#{").append(str).append("}");
			if(value==null)
				value="";
			addPara(str, value);
		}
	}
	
	public void addValues(Map<String,Object> values){
		for(String str:values.keySet()){
			addValue(str, values.get(str));
		}
	}
	
}
