package com.dlz.app.sys.bean;

import java.util.HashSet;
import java.util.Set;

/**
 * 登录用户信息
 * @author dk 2017-06-15
 *
 */
public class AuthUser implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	private Integer id;	//ID
	private String name;//会员名
	private String l_id;//用户名
	private String pwd;	//密码
	private String mobile;//会员手机号
	private Set<Integer> roles=new HashSet<Integer>();//会员角色
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getL_id() {
		return l_id;
	}
	public void setL_id(String l_id) {
		this.l_id = l_id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public Set<Integer> getRoles() {
		return this.roles;
	}
	/**
	 * 是否有某角色
	 * @param role
	 * @return
	 */
	public boolean hasRole(Integer role) {
		return roles.contains(role);
	}
	
	/**
	 * 是否有某角色中的一种
	 * @param role
	 * @return
	 */
	public boolean hasRole(String roles) {
		if(roles==null){
			return false;
		}
		String[] rs=roles.split(",");
		boolean hasRole=false;
		for(String r:rs){
			try{
				if(hasRole(Integer.parseInt(r))){
					hasRole=true;
					break;
				}
			}catch(Exception e){
			}
		}
		return hasRole;
	}
}