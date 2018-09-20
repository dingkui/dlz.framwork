package com.dlz.apps.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.sys.service.DeptServiceExt;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.model.Dept;

@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceExtImpl implements DeptServiceExt {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	@Autowired
	protected ICommService commService;
	@Override
	public List<Dept> getDepts(Long userid){
		String sql="select * from t_p_dept pd where exists(select 1 from t_p_dept_user pdu where pd.d_id = pdu.du_d_id and pdu.du_u_id = #{id})";
		ParaMap pm = new ParaMap(sql);
		pm.addPara("id", userid);
		return commService.getBeanList(pm, Dept.class);
	}
}