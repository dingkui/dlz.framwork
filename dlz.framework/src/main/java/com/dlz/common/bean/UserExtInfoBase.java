package com.dlz.common.bean;

/**
 * 用户扩展信息基础
 * @author dk 2017-06-15
 *
 */
public class UserExtInfoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private Integer id;	//ID
	private String extInfoName;	//ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getExtInfoName() {
		return extInfoName;
	}
	public void setExtInfoName(String extInfoName) {
		this.extInfoName = extInfoName;
	}
	
}