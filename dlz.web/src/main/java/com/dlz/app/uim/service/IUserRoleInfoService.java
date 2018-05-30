package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;

public interface IUserRoleInfoService {
	public boolean saveRoleInfo(int id,JSONMap info);
	public JSONMap getRoleInfo(Integer id);
	public List<JSONMap> getRoleInfo(List<Integer> ids);
}
