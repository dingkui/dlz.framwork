package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;

public interface IUserExtInfoService {
	public boolean saveExtInfo(int id,JSONMap info);
	public JSONMap getExtInfo(Integer id);
	public List<JSONMap> getExtInfo(List<Integer> ids);
}
