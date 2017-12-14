package com.dlz.apps.sys.service;

import java.util.List;

import com.dlz.framework.ssme.db.model.Dept;

public interface DeptServiceExt {
	public List<Dept> getDepts(Long userid);
} 