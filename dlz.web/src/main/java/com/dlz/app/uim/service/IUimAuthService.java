package com.dlz.app.uim.service;

import com.dlz.app.uim.bean.AuthUser;
import com.dlz.framework.bean.JSONMap;

/**
 * 用户认证相关接口
 * @author dingkui
 *
 */
public interface IUimAuthService {
	/**
	 * 注册用户 1.检查用户是否存在 2.注册
	 * 
	 * @param info
	 * @return
	 */
	public Long regist(AuthUser info);

	/**
	 * 用户Cookie登录
	 * 
	 * @param uid
	 */
	public int loginByCookie();

	public int loginById(Integer parseInt);

	/**
	 * 会员登录
	 * 
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return 1:成功, 0：失败
	 */
	public int login(String username, String password);

	/**
	 * 会员第三方登录
	 * 
	 * @param third
	 *            登录方式
	 * @param id
	 *            登录id
	 * @return 1:成功, 0：失败
	 */
	public int loginByThird(String third, String id);

	/**
	 * 会员第三方登录信息添加
	 * 
	 * @param third
	 *            登录方式
	 * @param id
	 *            登录id
	 * @param userid
	 *            用户id
	 * @return 1:成功, 0：失败
	 */
	public int addThirdInfo(String third, String id, Integer userid, JSONMap thidInfo);

	/**
	 * 获取会员第三方登录ID
	 * 
	 * @param third
	 *            登录方式
	 * @param userid
	 *            用户id
	 * @return 1:成功, 0：失败
	 */
	public String getThirdId(String third, Integer userid);

	/**
	 * 管理员以会员身份登录
	 * 
	 * @param uid
	 *            要登录的会员ID
	 * @return 0登录失败，无此会员
	 * @throws RuntimeException
	 *             无权操作
	 */
	public int loginBySys(int uid);

	/**
	 * 会员注销
	 */
	public void logout();
}