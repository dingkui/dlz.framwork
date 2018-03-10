package com.dlz.common.bean;

/**
 * 用户扩展信息基础
 * @author dk 2017-06-15
 *
 */
public class UserRoleInfoBase implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private Integer id;	//ID
	String infoName;	//ID
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getInfoName() {
		return infoName;
	}
	public void setInfoName(String infoName) {
		this.infoName = infoName;
	}
	
	
}