package com.dlz.commbiz.sys.rbac.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.commbiz.sys.rbac.dao.RbacMapper;
import com.dlz.commbiz.sys.rbac.model.User;
import com.dlz.commbiz.sys.rbac.service.RbacService;
import com.dlz.framework.db.modal.Page;
import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.service.ICommService;

@Service
public class RbacServiceImpl implements RbacService {

	@Autowired
	private RbacMapper rbacMapper;
	@Autowired
	private ICommService commService;
	
	/*
	 * 查看用户-根据用户组编号查询所有用户登录名称
	 */
	public List<User> getLoginIdByGrpId(String grpId) {
		return rbacMapper.getLoginIdByGrpId(grpId);
	}

	public String getCode(String pCode,String codeName,String tableName) throws Exception{
		SearchParaMap spm = new SearchParaMap(tableName,"max("+codeName+") as code");
		spm.addCondition(codeName, "like", new String[]{"",pCode,"__"});
		spm.setPage(new Page());
		spm.getPage().setOrderBy("code desc");
		Object o = commService.getColum(spm);
		if(o==null || "".equals(o)){
			return pCode+"01";
		}else{
			String maxSubCode=String.valueOf(o);
			int maxSub=Integer.parseInt(maxSubCode.substring(pCode.length()),16)+1;
			String value=Integer.toHexString(maxSub);
			if(value.length()==1){
				return pCode+"0"+value.toUpperCase();
			}else{
				return pCode+value.toUpperCase();
			}
		}
	}
}
