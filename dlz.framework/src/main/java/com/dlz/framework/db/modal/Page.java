package com.dlz.framework.db.modal;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.framework.db.SqlUtil;
import com.dlz.framework.util.StringUtils;

public class Page<T>  implements Serializable{
	private static final long serialVersionUID = 2651739814148073895L;

	public static final int DEFAULT_PAGE_SIZE = 20;

	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private Integer pageNow=1;//从1开始
	
	private Integer begin=0;
	private Integer end=0;
	
	private Integer pages=0;//页数
	private Integer count=0;//总条数
	
	private Map<String,Object> atrrs=new HashMap<String,Object>();
	
	private String sortField;
	private String sortOrder;
	private String orderBy;
	protected List<T> data;

	public Page(int pageIndex,int pageSize,String sortField,String sortOrder){
		if(pageSize<=0){
			pageSize=DEFAULT_PAGE_SIZE;
		}
		this.setPageSize(pageSize);
		this.setPageIndex(pageIndex);
		this.sortField=sortField;
		this.sortOrder=sortOrder;
	}
	public Page(int pageIndex,int pageSize,String orderBy){
		this(pageIndex, pageSize, null, null);
		this.setOrderBy(orderBy);
	}
	public Page(){
		this(0, DEFAULT_PAGE_SIZE, null, null);
	}
	public Page(int count,List<T> data){
		setCount(count);
		setData(data);
	}
	public String getOrderBy() {
		return orderBy==null?(StringUtils.isEmpty(sortField)?null:(SqlUtil.converStr2ClumnStr(sortField)+" "+(sortOrder==null?"":sortOrder))):orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = SqlUtil.converStr2ClumnStr(orderBy);
	}
	public Map<String, Object> getAtrrs() {
		return atrrs;
	}
	public void setAtrrs(Map<String, Object> atrrs) {
		this.atrrs = atrrs;
	}
	public int getPageSize() {
		return pageSize;
	}
	public int getBegin() {
		return begin;
	}
	
	public int getEnd() {
		if(this.end==0){
			this.end=this.pageSize;
		}
		return end;
	}

	public Page setPageSize(int pageSize) {
		if(pageSize>0){
			this.pageSize = pageSize;
			setCNT();
		}
		return this;
	}

	public void setPageIndex(int pageIndex) {
		setPageNow(pageIndex+1);
	}

	public int getPages() {
		return pages;
	}

	public void setPages(int pages) {
		this.pages = pages;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count=count;
		setCNT();
	}

	public String getSortField() {
		return sortField;
	}

	public void setSortField(String sortField) {
		this.sortField = sortField;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**  
	 * 获取pageNow
	 * @return pageNow pageNow  
	 */
	public int getPageNow() {
		return pageNow;
	}
	/** 
	 * 设置pageNow 
	 * @param pageNow pageNow 
	 */
	public void setPageNow(int pageNow) {
		if(pageNow<=1){
			pageNow=1;
		}
		this.pageNow = pageNow;
		setCNT();
	}
	private void setCNT(){
		pageSize = pageSize<0?1:pageSize;
		
		pages=count%pageSize==0?count/pageSize:count/pageSize+1;
		if(pages>0&&getPageNow()>pages){
			setPageNow(pages);
		}
		begin=(pageNow-1)*pageSize;
		end=begin+pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
}
