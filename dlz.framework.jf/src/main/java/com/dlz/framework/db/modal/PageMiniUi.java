package com.dlz.framework.db.modal;

import java.util.List;

class PageMiniUi {
	public static final int DEFAULT_PAGE_SIZE = 30;
	protected int pageSize = DEFAULT_PAGE_SIZE;
	protected int pageIndex=0;//从0开始
	protected int total;
	protected List<?> data;
	
	/**  
	 * 获取pageSize
	 * @return pageSize pageSize  
	 */
	public int getPageSize() {
		return pageSize;
	}
	/** 
	 * 设置pageSize 
	 * @param pageSize pageSize 
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	/**  
	 * 获取pageIndex
	 * @return pageIndex pageIndex  
	 */
	public int getPageIndex() {
		return pageIndex;
	}
	/** 
	 * 设置pageIndex 
	 * @param pageIndex pageIndex 
	 */
	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}
	/**  
	 * 获取total
	 * @return total total  
	 */
	public int getTotal() {
		return total;
	}
	/** 
	 * 设置total 
	 * @param total total 
	 */
	public void setTotal(int total) {
		this.total = total;
	}
	/**  
	 * 获取data
	 * @return data data  
	 */
	public List<?> getData() {
		return data;
	}
	/** 
	 * 设置data 
	 * @param data data 
	 */
	public void setData(List<?> data) {
		this.data = data;
	}
	
}
