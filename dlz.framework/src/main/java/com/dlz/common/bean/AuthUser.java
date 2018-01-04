package com.dlz.common.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.dlz.common.service.IUserExtInfoService;
import com.dlz.common.service.IUserRoleInfoService;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;

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
	
	private Map<Integer,UserRoleInfoBase> rolesInfos=new HashMap<Integer,UserRoleInfoBase>();//会员角色对应的用户信息
	private Map<String,UserExtInfoBase> extInfos=new HashMap<String,UserExtInfoBase>();//会员扩展信息
	
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
	public void addExtInfo(String extId,UserExtInfoBase userInfo) {
		if(userInfo!=null){
			extInfos.put(extId, userInfo);
		}
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
	/**
	 * 取得某角色对应的用户信息
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends UserRoleInfoBase> T getRoleInfo(Integer roleId) {
		UserRoleInfoBase info =rolesInfos.get(roleId);
		if(info==null){
			IUserRoleInfoService roleService=SpringHolder.getBean("userRoleInfoService_"+roleId);
			info=roleService.getRoleInfo(id);
			if(info!=null){
				rolesInfos.put(roleId, info);
			}else{
				throw new LogicException("用户角色【"+roleId+"】对应的信息未找到，信息未保存");
			}
		}
		return (T)info;
	}
	/**
	 * 取得用户扩展信息
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends UserExtInfoBase> T getExtInfo(String extId) {
		UserExtInfoBase info =extInfos.get(extId);
		if(info==null){
			IUserExtInfoService extservice=SpringHolder.getBean("userExtInfoService_"+extId);
			info=extservice.getExtInfo(id);
			if(info!=null){
				extInfos.put(extId, info);
			}else{
				throw new LogicException("扩展信息【"+extId+"】对应的信息，信息未保存");
			}
		}
		return (T)info;
	}
	
}