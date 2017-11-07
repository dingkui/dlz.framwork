package com.dlz.framework.db.modal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.dlz.common.util.string.StringUtils;

public class Page extends PageMiniUi {
	public static final int DEFAULT_PAGE_SIZE = 20;

	private int pageSize = DEFAULT_PAGE_SIZE;
	private int pageNow=1;//从1开始
	
	private int begin=0;
	private int end=0;
	
	private int pages;//页数
	private int count;//总条数
	
	private Map<String,Object> atrrs=new HashMap<String,Object>();
	
	private String sortField;
	private String sortOrder;
	private String orderBy;
	
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
	public Page(int count,List<?> data){
		setCount(count);
		setData(data);
	}
	
	public Page(int pageIndex,int pageSize){
		this(pageIndex, pageSize, null);
	}
	
	public String getOrderBy() {
		return orderBy==null?(StringUtils.isEmpty(sortField)?null:(StringUtils.converStr2ClumnStr(sortField)+" "+(sortOrder==null?"":sortOrder))):orderBy;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = StringUtils.converStr2ClumnStr(orderBy);
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

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setCNT();
	}

	public int getPageIndex() {
		return pageIndex;
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
		setTotal(count);
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
		this.pageIndex=pageNow-1;
		setCNT();
	}
	private void setCNT(){
		pageSize = pageSize<0?1:pageSize;
		pages=count%pageSize==0?count/pageSize:count/pageSize+1;
		if(pages>0&&getPageNow()>pages){
			setPageNow(pages);
		}
		begin=pageIndex*pageSize;
		end=begin+pageSize;
	}
}
