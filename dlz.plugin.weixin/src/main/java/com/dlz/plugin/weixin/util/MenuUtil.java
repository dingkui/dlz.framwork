package com.dlz.plugin.weixin.util;

import com.dlz.framework.bean.JSONMap;
import com.dlz.framework.logger.MyLogger;
import com.dlz.plugin.weixin.menu.Menu;

import net.sf.json.JSONObject;

/**
 * 自定义菜单工具类
 * 
 * @author 陈孙亮（微信）
 *
 */
public class MenuUtil {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private final static MyLogger log = MyLogger.getLogger(MenuUtil.class);
	
	//菜单创建(POST)
	public final static String menu_create_url = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	
	//菜单查询(GET)
	public final static String menu_get_url = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	
	//菜单删除(GET)
	public final static String menu_delete_url = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	
	
	/**
	 * 创建菜单
	 * 
	 * @param menu 菜单实例
	 * @param accessToken 凭证
	 * @return true 成功 false 失败
	 */
	public static boolean createMenu(Menu menu,String accessToken){
		boolean result = false;
		String url = menu_create_url.replace("ACCESS_TOKEN", accessToken);
		//将菜单对象转换为JSON字符串
		String jsonMenu = JSONObject.fromObject(menu).toString();
		//发起POST 请求创建菜单
		JSONMap jsonObject = CommonUtil.httpsRequest(url, "POST", jsonMenu);
		if(null != jsonObject){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getStr("errmsg");
			if(0 == errorCode){
				result = true;
			}else{
				result = false;
				log.error("创建菜单那失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
		
		return result;
	}
	
	/**
	 * 查询菜单
	 * 
	 * @param accessToken 凭证
	 * @return 
	 */
	public static String getMenu(String accessToken){
		String result = null;
		String requestUrl = menu_get_url.replace("ACCESS_TOKEN", accessToken);
		//发起GET请求查询菜单
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		if(jsonObject != null){
			result = jsonObject.toString();
		}
		return result;
	}

	/**
	 * 删除菜单
	 * 
	 * @param accessToken 凭证
	 * @return true 成功 false 失败
	 */
	public static boolean deleteMenu(String accessToken){
		boolean result = false;
		String requestUrl = menu_delete_url.replace("ACCESS_TOKEN", accessToken);
		//发起GET请求删除菜单
		JSONMap jsonObject = CommonUtil.httpsRequest(requestUrl, "GET", null);
		
		if(jsonObject != null){
			int errorCode = jsonObject.getInt("errcode");
			String errorMsg = jsonObject.getStr("errmsg");
			if( 0 == errorCode){
				result = true;
			}else{
				result = false;
				log.error("删除菜单失败 errcode:{} errmsg:{}",errorCode,errorMsg);
			}
		}
		
		return result;
	}
}
