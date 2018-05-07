package com.dlz.common.service;


import java.util.List;

import com.dlz.framework.db.modal.ResultMap;

public interface IConfigService {
	/**
	 * 用户ID登录
	 * @param uid
	 */
	public List<ResultMap> getConfigs();
}

