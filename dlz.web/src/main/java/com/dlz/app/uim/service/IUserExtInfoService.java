package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.ResultMap;

public interface IUserExtInfoService {
	public boolean saveExtInfo(Long id,String extId,JSONMap info);
	public JSONMap getExtInfo(Long id,String extId);
	public List<ResultMap> getExtInfo(List<Long> ids,String extId);
	
	public boolean saveRoleInfo(Long id,Long roleId,JSONMap info);
	public JSONMap getRoleInfo(Long id,Long roleId);
	public List<ResultMap> getRoleInfo(List<Long> ids,Long roleId);
}
