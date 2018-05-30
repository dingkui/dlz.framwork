package com.dlz.app.uim.bean;

import java.util.HashMap;
import java.util.Map;

import com.dlz.app.uim.service.IUimInfoService;
import com.dlz.framework.exception.LogicException;
import com.dlz.framework.holder.SpringHolder;

/**
 * 登录用户信息
 * @author dk 2017-06-15
 *
 */
public class AuthUserWithInfo extends AuthUser {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	
	private Map<Integer,UserRoleInfoBase> rolesInfos=new HashMap<Integer,UserRoleInfoBase>();//会员角色对应的用户信息
	private Map<String,UserExtInfoBase> extInfos=new HashMap<String,UserExtInfoBase>();//会员扩展信息
	
	/**
	 * 取得某角色对应的用户信息
	 * @param role
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T extends UserRoleInfoBase> T getRoleInfo(Integer roleId,Class<T> clazz) {
		UserRoleInfoBase info =rolesInfos.get(roleId);
		if(info==null){
			IUimInfoService uimInfoService=SpringHolder.getBean(IUimInfoService.class);
			info=uimInfoService.getRoleInfo(getId(), roleId).as(clazz);
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
	public <T extends UserExtInfoBase> T getExtInfo(String extId,Class<T> clazz) {
		UserExtInfoBase info =extInfos.get(extId);
		if(info==null){
			IUimInfoService uimInfoService=SpringHolder.getBean(IUimInfoService.class);
			info=uimInfoService.getExtInfo(getId(), extId).as(clazz);
			if(info!=null){
				extInfos.put(extId, info);
			}else{
				throw new LogicException("扩展信息【"+extId+"】对应的信息，信息未保存");
			}
		}
		return (T)info;
	}
	
}