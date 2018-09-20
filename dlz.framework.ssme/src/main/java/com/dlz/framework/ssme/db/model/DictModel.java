package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;

public class DictModel extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 7034929917474269421L;
	private String id;
	private String text;
	private String enText;
	private String pId;
	private String status;
	private String order;
	private String hot;
	private String ids;//多级ID  1111*1111
	private String texts;//多级text  xxxx*xxxx
	/**  
	 * 获取id
	 * @return id id  
	 */
	public String getId() {
		return id;
	}
	/** 
	 * 设置id 
	 * @param id id 
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**  
	 * 获取text
	 * @return text text  
	 */
	public String getText() {
		return text;
	}
	/** 
	 * 设置text 
	 * @param text text 
	 */
	public void setText(String text) {
		this.text = text;
	}
	/**  
	 * 获取enText
	 * @return enText enText  
	 */
	public String getEnText() {
		return enText;
	}
	/** 
	 * 设置enText 
	 * @param enText enText 
	 */
	public void setEnText(String enText) {
		this.enText = enText;
	}
	/**  
	 * 获取pId
	 * @return pId pId  
	 */
	public String getpId() {
		return pId;
	}
	/** 
	 * 设置pId 
	 * @param pId pId 
	 */
	public void setpId(String pId) {
		this.pId = pId;
	}
	/**  
	 * 获取status
	 * @return status status  
	 */
	public String getStatus() {
		return status;
	}
	/** 
	 * 设置status 
	 * @param status status 
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**  
	 * 获取order
	 * @return order order  
	 */
	public String getOrder() {
		return order;
	}
	/** 
	 * 设置order 
	 * @param order order 
	 */
	public void setOrder(String order) {
		this.order = order;
	}
	/**  
	 * 获取hot
	 * @return hot hot  
	 */
	public String getHot() {
		return hot;
	}
	/** 
	 * 设置hot 
	 * @param hot hot 
	 */
	public void setHot(String hot) {
		this.hot = hot;
	}
	/**  
	 * 获取ids
	 * @return ids ids  
	 */
	public String getIds() {
		return ids;
	}
	/** 
	 * 设置ids 
	 * @param ids ids 
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
	/**  
	 * 获取texts
	 * @return texts texts  
	 */
	public String getTexts() {
		return texts;
	}
	/** 
	 * 设置texts 
	 * @param texts texts 
	 */
	public void setTexts(String texts) {
		this.texts = texts;
	}
	
	

}
