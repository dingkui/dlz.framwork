package com.dlz.apps.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.sys.service.DeptServiceExt;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.model.Dept;
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

	@Override
	public Dept getDept(Long userid){
		String sql="select * from t_p_dept pd where exists(select * from t_p_dept_user pdu where pd.d_id = pdu.du_d_id and pdu.du_u_id = #{id})";
		ParaMap pm = new ParaMap(sql);
		pm.addPara("id", userid);
		Dept dept= commService.getBean(pm, Dept.class);
		if(dept==null){
			dept=new Dept();
			dept.setdId(-1l);
			dept.setdFid(-1l);
			dept.setdName("无部门");
			//TODO 管理员所属的部门类型
			dept.setdType("D1");
		}
		return dept;
	}
}