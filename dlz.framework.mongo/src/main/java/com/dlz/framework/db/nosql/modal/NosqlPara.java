//package com.dlz.framework.db.nosql.modal;
//
//import java.io.Serializable;
//import java.util.Map;
//
//import com.dlz.framework.bean.JSONList;
//import com.dlz.framework.bean.JSONMap;
//import com.dlz.framework.db.SqlUtil;
//import com.dlz.framework.db.enums.ParaTypeEnum;
//import com.dlz.framework.db.modal.Page;
//import com.fasterxml.jackson.annotation.JsonIgnore;
//
//@SuppressWarnings("rawtypes")
//public class NosqlPara implements Serializable{
//	private static final long serialVersionUID = 8374167270612933157L;
//	@JsonIgnore
//	private String key;
//	private String name;
//	private String opt;
//	private String filterBson;
//	private String dataBson;
//	private JSONList datas=new JSONList();
//	private Page page;
//	private JSONMap para = new JSONMap();
//	
//	public String getOpt() {
//		return opt;
//	}
//	public void setOpt(String opt) {
//		this.opt = opt;
//	}
//	public JSONList getDatas() {
//		return datas;
//	}
//	public void addData(Object data) {
//		datas.add(data);
//	}
//	public String getName() {
//		return name;
//	}
//	public void setName(String name) {
//		this.name = name;
//	}
//	public String getFilterBson() {
//		return filterBson;
//	}
//	public void setFilterBson(String filterBson) {
//		this.filterBson = filterBson;
//	}
//	public String getDataBson() {
//		return dataBson;
//	}
//	public void createDataBson() {
//		this.dataBson = datas.toString();
//	}
//	public NosqlPara(String key,Page page){
//		this.key=key;
//		this.setPage(page);
//	}
//	public NosqlPara(String key){
//		this.key=key;
//	}
//	
//	
//	/**  
//	 * 创建Page
//	 * @return page page  
//	 */
//	public Page createPage() {
//		if(page==null){
//			page=new Page();
//		}
//		return page;
//	}
//	/**  
//	 * 获取page
//	 * @return page page  
//	 */
//	public Page getPage() {
//		return page;
//	}
//	/** 
//	 * 设置page 
//	 * @param page page 
//	 */
//	public void setPage(Page page) {
//		this.page = page;
//	}
//	/**  
//	 * 获取para
//	 * @return para para  
//	 */
//	public JSONMap getPara() {
//		return para;
//	}
//	public String getKey() {
//		return key;
//	}
//	public void setKey(String key) {
//		this.key = key;
//	}
//}
