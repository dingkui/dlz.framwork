package com.dlz.framework.ssme.db.service;

import java.util.List;

import com.dlz.framework.ssme.db.model.User;

public interface RbacService {

	public abstract List<User> getLoginIdByGrpId(String grpId);
	
	public String getCode(String pCode,String codeName,String tableName) throws Exception;
	
}
