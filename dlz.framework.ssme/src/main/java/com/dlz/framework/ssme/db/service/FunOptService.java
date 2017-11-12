package com.dlz.framework.ssme.db.service;

import java.util.List;
import java.util.Map;

import com.dlz.framework.ssme.base.service.BaseService;
import com.dlz.framework.ssme.db.model.FunOpt;

public interface FunOptService extends BaseService<FunOpt, Long> {
	public abstract List<Map<String, Object>> getOptsByRoles(Map para);

	public abstract List<String> getFunOptUrlByUserId(Long userId);
}