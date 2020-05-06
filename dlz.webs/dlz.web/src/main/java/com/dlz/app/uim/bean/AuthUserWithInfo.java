package com.dlz.app.uim.bean;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;

import com.dlz.app.uim.service.IUimInfoService;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.holder.SpringHolder;
import com.dlz.comm.util.JacksonUtil;

/**
 * 登录用户信息
 * @author dk 2017-06-15
 *
 */
public class AuthUserWithInfo extends AuthUser {
	private static Logger logger=org.slf4j.LoggerFactory.getLogger(AuthUserWithInfo.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 5070462418563344534L;
	
	private Map<String,Object> infos=new HashMap<>();//会员角色信息或扩展信息
	private Set<String> infosKey=new HashSet<>();//会员角色信息或扩展信息是否被取得过
	
	/**
	 * 删除取得某角色对应的用户信息
	 * @param role
	 * @return
	 */
	public void delRoleInfo(Long roleId) {
		String key="R_"+roleId;
		if(infosKey.remove(key)){
			infos.remove(key);
		};
	}
	/**
	 * 删除取得某角色对应的用户信息
	 * @param role
	 * @return
	 */
	public void delExtInfo(String extId) {
		String key="E_"+extId;
		if(infosKey.remove(key)){
			infos.remove(key);
		};
	}
	/**
	 * 取得某角色对应的用户信息
	 * @param role
	 * @return
	 */
	public <T> T getRoleInfo(Long roleId,Class<T> clazz) {
		String key="R_"+roleId;
		Object obj =infos.get(key);
		if(obj!=null){
			return JacksonUtil.coverObj(obj,clazz);
		}
		if(!infosKey.contains(key)){
			infosKey.add(key);
			IUimInfoService uimInfoService=SpringHolder.getBean(IUimInfoService.class);
			T info=uimInfoService.getRoleInfo(getId(), roleId).as(clazz);
			if(info!=null){
				infos.put(key, info);
				return info;
			}else{
				logger.warn("用户【"+getId()+"】无角色信息【"+roleId+"】");
				return null;
			}
		}
		return null;
	}
	/**
	 * 取得用户扩展信息
	 * @param role
	 * @return
	 */
	public <T> T getExtInfo(String extId,Class<T> clazz) {
		String key="E_"+extId;
		Object obj =infos.get(key);
		if(obj!=null){
			return JacksonUtil.coverObj(obj,clazz);
		}
		if(!infosKey.contains(key)){
			infosKey.add(key);
			IUimInfoService uimInfoService=SpringHolder.getBean(IUimInfoService.class);
			T info=uimInfoService.getExtInfo(getId(), extId).as(clazz);
			if(info!=null){
				infos.put(key, info);
				return info;
			}else{
				logger.warn("用户【"+getId()+"】无扩展信息【"+extId+"】");
				return null;
			}
		}
		return null;
	}
	
	/**
	 * 取得用户扩展信息
	 * @param role
	 * @return
	 */
	public JSONMap getExtInfo(String extId) {
		return getExtInfo(extId, JSONMap.class);
	}
	/**
	 * 取得用户角色信息
	 * @param role
	 * @return
	 */
	public JSONMap getRoleInfo(Long roleId) {
		return getRoleInfo(roleId, JSONMap.class);
	}
}