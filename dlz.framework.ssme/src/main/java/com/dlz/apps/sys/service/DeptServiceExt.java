package com.dlz.apps.sys.service;

import com.dlz.framework.ssme.db.model.Dept;

public interface DeptServiceExt {
	Long getUserDept(Long userid) throws Exception;
	
	Dept getDept(Long userid);
} 