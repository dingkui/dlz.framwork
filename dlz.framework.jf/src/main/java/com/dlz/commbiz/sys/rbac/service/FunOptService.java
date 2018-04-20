package com.dlz.commbiz.sys.rbac.service;

import java.util.List;
import java.util.Map;

import com.dlz.common.base.service.BaseService;
import com.dlz.commbiz.sys.rbac.model.FunOpt;

public interface FunOptService extends BaseService<FunOpt, Long> {
	public abstract List<Map<String, Object>> getOptsByRoles(Map para);

	public abstract List<String> getFunOptUrlByUserId(Long userId);
}