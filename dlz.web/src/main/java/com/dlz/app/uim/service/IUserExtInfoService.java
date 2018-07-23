package com.dlz.app.uim.service;

import java.util.List;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.db.modal.ResultMap;

public interface IUserExtInfoService {
	public boolean saveExtInfo(Long id,JSONMap info);
	public JSONMap getExtInfo(Long id);
	public List<ResultMap> getExtInfo(List<Long> ids);
}
