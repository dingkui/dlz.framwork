package com.dlz.framework.db.modal;

import com.dlz.framework.db.SqlUtil;


/**
 * 构造单表的查询操作sql
 * @author dingkui
 *
 */
public class SearchParaMap extends CreateSqlParaMap{
	private static final long serialVersionUID = 8374167270612933157L;
	private static final String SQL="key.comm.searchTable";
	public SearchParaMap(String tableName,Page page){
		this(tableName,"*",page);
	}
	public SearchParaMap(String tableName){
		this(tableName,"*");
	}
	public SearchParaMap(String tableName,String colums,Page page){
		super(SQL,tableName,page);
		addPara(STR_COLUMS, SqlUtil.converStr2ClumnStr(colums));
	}
	public SearchParaMap(String tableName,String colums){
		super(SQL,tableName,null);
		addPara(STR_COLUMS, SqlUtil.converStr2ClumnStr(colums));
	}
}
