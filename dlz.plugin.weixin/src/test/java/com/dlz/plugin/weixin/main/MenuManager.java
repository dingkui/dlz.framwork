package com.dlz.plugin.weixin.main;

import org.slf4j.Logger;
import com.dlz.plugin.weixin.menu.Button;
import com.dlz.plugin.weixin.menu.ClickButton;
import com.dlz.plugin.weixin.menu.ComplexButton;
import com.dlz.plugin.weixin.menu.Menu;
import com.dlz.plugin.weixin.menu.ViewButton;
import com.dlz.plugin.weixin.util.MenuUtil;
import com.dlz.web.util.WxUtil.AccessToken;

/**
 * 菜单管理器类
 * 
 * @author 陈孙亮（微信）
 *
 */
public class MenuManager {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	private static Logger log = org.slf4j.LoggerFactory.getLogger(MenuManager.class);
	
	/**
	 * 定义菜单结构
	 * @return
	 */
	private static Menu getMenu(){
		
		/*ClickButton btnl_1 = new ClickButton();
		btnl_1.setName("我的快件");
		btnl_1.setType("click");
		btnl_1.setKey("wdkj");
		
		ClickButton btnl_2 = new ClickButton();
		btnl_2.setName("手机绑定");
		btnl_2.setType("click");
		btnl_2.setKey("sjbd");
		
		
		ClickButton btnl_3 = new ClickButton();
		btnl_3.setName("查询绑定");
		btnl_3.setType("click");
		btnl_3.setKey("cxbd");
		
		ClickButton btnl_4 = new ClickButton();
		btnl_4.setName("解除绑定");
		btnl_4.setType("click");
		btnl_4.setKey("jcbd");
		
		ClickButton btnl_5 = new ClickButton();
		btnl_5.setName("中驿站点");
		btnl_5.setType("click");
		btnl_5.setKey("zywd");
		*/

		ViewButton btnl_2_1 = new ViewButton();
		btnl_2_1.setName("免登陆功能");
		btnl_2_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2815aca1da578be7&redirect_uri=https://www.yoyojr.com/weixin/enterExemptLoginPage&response_type=code&scope=snsapi_base&state=exemptLogin#wechat_redirect");
		btnl_2_1.setType("view");
		
		
		
		ViewButton btnl_2_2 = new ViewButton();
		btnl_2_2.setName("注册/登录");
		btnl_2_2.setUrl("https://www.yoyojr.com/weixin/enterImportPhone?operation=login");
		btnl_2_2.setType("view");
		
		
		ClickButton btnl_3_1 = new ClickButton();
		btnl_3_1.setName("我的推广");
		btnl_3_1.setType("click");
		btnl_3_1.setKey("wdtg");
		
		ClickButton btnl_3_2 = new ClickButton();
		btnl_3_2.setName("客服电话");
		btnl_3_2.setType("click");
		btnl_3_2.setKey("kfdh");
		
		ViewButton btnl_3_3 = new ViewButton();
		btnl_3_3.setName("新手指引");
		btnl_3_3.setUrl("https://www.yoyojr.com/index/userguide");
		btnl_3_3.setType("view");
		
//		ViewButton btnl_3_4 = new ViewButton();
//		btnl_3_4.setName("致理财人");
//		btnl_3_4.setUrl("https://www.yoyojr.com/activity/letter");
//		btnl_3_4.setType("view");
		
		ViewButton btnl_3_5 = new ViewButton();
		btnl_3_5.setName("最新活动");
		btnl_3_5.setUrl("https://www.yoyojr.com/activity/award");
		btnl_3_5.setType("view");
		
		/*ComplexButton mainBthn_1 = new ComplexButton();
		mainBthn_1.setName("进入有友");
		mainBthn_1.setSub_button(new Button[] {btnl_1,btnl_2,btnl_3,btnl_4,btnl_5});*/
		ViewButton mainBthn_1 = new ViewButton();
		mainBthn_1.setName("进入有友");
		//mainBthn_1.setUrl("https://www.yoyojr.com/weixin/enterHome");
		mainBthn_1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx2815aca1da578be7&redirect_uri=https://www.yoyojr.com/weixin/enterHome&response_type=code&scope=snsapi_base&state=fromWeixin#wechat_redirect");
		mainBthn_1.setType("view");
		
		ComplexButton mainBthn_2 = new ComplexButton();
		mainBthn_2.setName("我的有友");
		mainBthn_2.setSub_button(new Button[] {btnl_2_1,btnl_2_2});
		
		
		ComplexButton mainBthn_3 = new ComplexButton();
		mainBthn_3.setName("更多");
		mainBthn_3.setSub_button(new Button[] {btnl_3_1,btnl_3_2,btnl_3_3,btnl_3_5});
		
		
		Menu menu = new Menu();
		menu.setButton(new Button[] {mainBthn_1,mainBthn_2,mainBthn_3});
		
		return menu;
	}
	
	
	public static void main(String[] args){
		//第三方用户唯一凭证
//		String appId = "wxb025f381e07118f1";//jincube
//		String appId = "wx2815aca1da578be7";//yoyojr
		String appId = "wxc885116a3a30e82d";//my test
		
		//第三方用户唯一凭证密钥
//		String appSecret = "a414e861300beef0364d84e88bb4c515";//jincube
//		String appSecret = "3896b6255e0e1fce2ea0407dd3ea6c09";//yoyojr
		String appSecret = "d4624c36b6795d1d99dcf0547af5443d";//my test
		 
		//调用接口获取凭证
		String token = AccessToken.getAccessToken(appId, appSecret);
		
		if(token != null){
			//创建菜单
			boolean result = MenuUtil.createMenu(getMenu(), token);
//			boolean result = MenuUtil.deleteMenu(token.getAccessToken());
			
			//判断菜单创建结果
			if(result ){
				log.info("菜单创建成功!");
			}else{
				log.info("菜单创建失败!");
			}
		}
	}
}
