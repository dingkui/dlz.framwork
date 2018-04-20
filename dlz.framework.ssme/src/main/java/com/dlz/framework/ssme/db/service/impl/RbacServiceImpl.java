package com.dlz.framework.ssme.db.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dlz.framework.db.modal.SearchParaMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.ssme.db.dao.RbacMapper;
import com.dlz.framework.ssme.db.model.User;
import com.dlz.framework.ssme.db.service.RbacService;

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
		SearchParaMap spm = new SearchParaMap(tableName,"nvl(max("+codeName+"),'') as code");
		spm.addCondition(codeName, "like", new String[]{"",pCode,"__"});
//		spm.createPage().setOrderBy("code desc");
		String o = commService.getStr(spm);
		if(o==null || "".equals(o)){
			return pCode+"01";
		}else{
			int maxSub=Integer.parseInt(o.substring(pCode.length()),16)+1;
			String value=Integer.toHexString(maxSub);
			if(value.length()==1){
				return pCode+"0"+value.toUpperCase();
			}else{
				return pCode+value.toUpperCase();
			}
		}
	}
}
