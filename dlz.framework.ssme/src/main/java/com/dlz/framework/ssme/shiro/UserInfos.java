package com.dlz.framework.ssme.shiro;

import java.util.HashSet;
import java.util.Set;

import com.dlz.framework.ssme.db.model.Dept;

/**
 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
 */
public class UserInfos extends UserInfo {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static final long serialVersionUID = 1L;
  	private Set<String> roleName = new HashSet<String>();
	private Set<Dept> depts = new HashSet<Dept>();

	public Set<String> getRoleName() {
		return roleName;
	}

	public Set<Dept> getDepts() {
		return depts;
	}
	public Dept getDept() {
		if(depts!=null&&!depts.isEmpty()){
			return depts.iterator().next();
		}
		return null;
	}
	private Dept getDept(String dCode) {
		for(Dept dept:depts){
			if(dept.getdCode().startsWith(dCode)){
				return dept;
			}
		}
		Dept dept=new Dept();
		dept.setdId(-1l);
		dept.setdFid(-1l);
		dept.setdName("无部门");
		//TODO 管理员所属的部门类型
		dept.setdType("D1");
		return dept;
	}
	public Dept getSaleDept() {
		return getDept("03");
	}

}