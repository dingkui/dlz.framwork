package com.dlz.app.uim.cache;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import com.dlz.app.uim.bean.AuthUserWithInfo;
import com.dlz.app.uim.service.IUimDeptService;
import com.dlz.app.uim.service.IUimMemberService;
import com.dlz.app.uim.service.IUimRoleService;
import com.dlz.comm.json.JSONMap;
import com.dlz.framework.cache.AbstractCache;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.comm.exception.CodeException;

/**
 * 用户信息缓存
 * 
 * @author dk
 * @version 1.0
 */
@Component
@Lazy
public class AuthUserCache extends AbstractCache<Long, AuthUserWithInfo>{
	@Autowired
	public IUimMemberService memberService;
	@Autowired
	public IUimDeptService deptService;
	@Autowired
	public IUimRoleService roleService;
	public AuthUserCache() {
		super(AuthUserCache.class.getSimpleName());
		dbOperator=new DbOperator() {
			protected AuthUserWithInfo getFromDb(Long user_id) {
				final ResultMap mapByKey = memberService.getMapByKey(user_id);
				if(mapByKey==null){
					throw new CodeException("用户取得失败，id="+user_id);
				}
				final AuthUserWithInfo authUser = mapByKey.as(AuthUserWithInfo.class);
				authUser.setId(user_id);
				authUser.getRoles().addAll(roleService.getUserRoleIds(user_id));
				authUser.getDepts().addAll(deptService.getUserDeptIds(user_id));
				return authUser;
			}
			protected void saveToDb(Long member_id,AuthUserWithInfo user){
				memberService.addOrUpdate(new JSONMap(user));
			}
		};
	}
}
