package com.dlz.framework.db.modal;

import com.dlz.comm.util.StringUtils;
import com.dlz.framework.db.SqlUtil;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class Page<T>  implements Serializable{
	private static final long serialVersionUID = 1L;

	public static final int DEFAULT_PAGE_SIZE = 20;

	private Integer pageSize = DEFAULT_PAGE_SIZE;
	private Integer pageIndex=-1;//从0开始
	
	private Integer begin=null;
	private Integer end=null;
	
	private Integer pages=0;//页数
	private Integer count=0;//总条数
	
	private String sortField;
	private String sortOrder;
	private String orderBy;
	private boolean needFy=true;//是否需要分页
	private boolean needCount=true;//是否需要统计条数
	private  Map<String, Object> extData;//扩展数据
	
	public boolean isNeedFy() {
		return needFy;
	}
	public void setNeedFy(boolean needFy) {
		this.needFy = needFy;
	}
	
	public boolean isNeedCount() {
		return needCount;
	}
	public void setNeedCount(boolean needCount) {
		this.needCount = needCount;
	}

	protected List<T> data;

	public Page(int pageIndex,int pageSize,String sortField,String sortOrder){
		this.setPageSize(pageSize);
		this.setPageIndex(pageIndex);
		this.sortField=sortField;
		this.sortOrder=sortOrder;
	}
	public Page(int pageIndex,int pageSize){
		this(pageIndex, pageSize, null, null);
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
	public int getPageSize() {
		return pageSize;
	}
	
	public void setBegin(Integer begin) {
		this.begin = begin;
	}
	public Integer getBegin() {
		return begin;
	}
	
	public Integer getEnd() {
		return end;
	}

	public Page<T> setPageSize(int pageSize) {
		this.pageSize = pageSize;
		setCNT();
		return this;
	}
	public Page<T> setPageIndex(int pageIndex) {
		this.pageIndex=pageIndex;
		return setCNT();
		
	}
	public Page<T> setCount(int count) {
		this.count=count;
		return setCNT();
	}
	
	private Page<T> setCNT(){
		//表示查询所有，不翻页
		if(pageSize==0){
			begin=null;
			end=null;
			count=0;
			return this;
		}
		//表示查询头几条，不翻页
		if(!isNeedFy()){
			begin=null;
			end=pageSize;
			return this;
		}
		
		
		pageSize = pageSize<0?DEFAULT_PAGE_SIZE:pageSize;
		pageIndex = pageIndex<0?0:pageIndex;
		
		pages=count%pageSize==0?count/pageSize:count/pageSize+1;
		if(pages>0&&pageIndex>pages-1){
			setPageIndex(pages-1);
		}
//		if(pageIndex==0){
//			begin=null;
//			end=pageSize;
//		}else{
			begin=pageIndex*pageSize;
			end=begin+pageSize;
//		}
		return this;
	}

	public Integer getPages() {
		return pages;
	}

	public Integer getCount() {
		return count;
	}
	public Integer getTotal() {
		return count;
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
	public Integer getPageNow() {
		return pageIndex+1;
	}
	/**  
	 * 获取pageNow
	 * @return pageNow pageNow  
	 */
	public Integer getPageIndex() {
		return pageIndex;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public Map<String, Object> getExtData() {
		return extData;
	}
	
	public void setExtData(Map<String, Object> extData) {
		this.extData = extData;
	}
	
}
