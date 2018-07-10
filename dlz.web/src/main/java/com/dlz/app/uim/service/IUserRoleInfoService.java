package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.ResultMap;

public interface IUserRoleInfoService {
	public boolean saveRoleInfo(int id,JSONMap info);
	public JSONMap getRoleInfo(Integer id);
	public List<ResultMap> getRoleInfo(List<Integer> ids);
}
