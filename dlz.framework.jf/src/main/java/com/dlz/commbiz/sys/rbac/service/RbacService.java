package com.dlz.commbiz.sys.rbac.service;

import java.util.List;

import com.dlz.commbiz.sys.rbac.model.User;

public interface RbacService {

	public abstract List<User> getLoginIdByGrpId(String grpId);
	
	public String getCode(String pCode,String codeName,String tableName) throws Exception;
	
}
