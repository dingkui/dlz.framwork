package com.dlz.framework.ssme.db.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.ssme.db.dao.MenuDataOptMapper;
import com.dlz.framework.ssme.db.model.MenuDataOpt;
import com.dlz.framework.ssme.db.service.MenuDataOptService;

@Service
@Transactional(rollbackFor=Exception.class)
public class MenuDataOptServiceImpl  extends BaseServiceImpl<MenuDataOpt, Long> implements MenuDataOptService  {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	public void setMapper(MenuDataOptMapper mapper) {
		super.mapper = mapper;
	}
}
