package com.dlz.commbiz.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.commbiz.sys.service.DeptServiceExt;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.util.ValUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceExtImpl implements DeptServiceExt {
	@Autowired
	protected ICommService commService;
	
	@Override
	public Long getUserDept(Long userid) throws Exception {
		String sql="select du_d_id from T_P_DEPT_USER where du_u_id=#{id}";
		ParaMap pm=new ParaMap(sql);
		pm.addPara("id", userid);
		return ValUtil.getLong(commService.getColum(pm));
	}
}