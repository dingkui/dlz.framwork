package com.dlz.commbiz.sys.rbac.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.common.base.service.impl.BaseServiceImpl;
import com.dlz.commbiz.sys.rbac.dao.MenuDataOptMapper;
import com.dlz.commbiz.sys.rbac.model.MenuDataOpt;
import com.dlz.commbiz.sys.rbac.service.MenuDataOptService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MenuDataOptServiceImpl  extends BaseServiceImpl<MenuDataOpt, Long> implements MenuDataOptService  {
	@Autowired
	public void setMapper(MenuDataOptMapper mapper) {
		super.mapper = mapper;
	}
}
