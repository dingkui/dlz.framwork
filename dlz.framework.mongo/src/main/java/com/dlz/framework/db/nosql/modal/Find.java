package com.dlz.framework.db.nosql.modal;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.util.StringUtils;

@SuppressWarnings("rawtypes")
public class Find extends NosqlFilterPara{
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 8374167270612933157L;
	private Page page;
	private JSONMap clumns;
	public Find(String key, Page page) {
		super(key);
		this.page=page;
	}
	public Find(String key) {
		super(key);
	}
	public JSONMap getClumns() {
		return clumns;
	}
	public void setClumns(String clumns) {
		if(clumns==null){
			this.clumns=null;
			return;
		}
		String[] strs=clumns.split(",");
		this.clumns=new JSONMap();
		for(String str:strs){
			str=str.trim();
			if(!StringUtils.isEmpty(str)){
				if(str.startsWith("!")){
					this.clumns.put(str.substring(1), 0);
				}else{
					this.clumns.put(str, 1);
				}
			}
		}
	}
	/**  
	 * 创建Page
	 * @return page page  
	 */
	public Page createPage() {
		if(page==null){
			page=new Page();
		}
		return page;
	}
	/**  
	 * 获取page
	 * @return page page  
	 */
	public Page getPage() {
		return page;
	}
	/** 
	 * 设置page 
	 * @param page page 
	 */
	public void setPage(Page page) {
		this.page = page;
	}
}
