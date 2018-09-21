package com.dlz.apps.sys.db.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dlz.apps.sys.db.dao.PushsMapper;
import com.dlz.apps.sys.db.model.Pushs;
import com.dlz.apps.sys.db.service.PushsService;
import com.dlz.framework.db.modal.ParaMap;
import com.dlz.framework.db.modal.ResultMap;
import com.dlz.framework.db.service.ICommService;
import org.slf4j.Logger;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.util.JacksonUtil;
import com.dlz.framework.util.StringUtils;
import com.google.gson.JsonObject;

@Service
@Transactional(rollbackFor = Exception.class)
public class PushsServiceImpl extends BaseServiceImpl<Pushs, Long> implements PushsService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
	private static Logger logger = org.slf4j.LoggerFactory.getLogger(PushsServiceImpl.class);

	@Autowired
	public void setMapper(PushsMapper mapper) {
		this.mapper = mapper;
	}
	
	@Autowired
	ICommService commService;

	/**
	 * 透传推送记录保存
	 * 
	 * @param userIds
	 *          接收消息的用户
	 * @param title
	 *          消息标题
	 * @param text
	 *          消息内容
	 * @param type
	 *          消息类型 appPg 打开页面 evalJs 制定页面执行脚本 runJs 首页执行脚本 update app更新
	 * @param para
	 *          type=appPg {u,...} u:打开页面的路径，...:页面参数 
	 *          type=evalJs {id,fn,...}  id:页面的id，fn:页面执行的方法，...:方法参数  
	 *          type=runJs {js}js:执行的js 
	 *          type=update {iosV,androidV,title,androidUrl}
	 *           iosV:ios的新版本号（应用商店）,
	 *           title:ios更新时的提示信息 （打开应用商店安装）
	 *           androidV：安卓新版本号(发布的新版本号),
	 *           androidUrl:安卓新版本的下载地址（自动下载安装）
	 * @throws Exception
	 */
	public void addPushs(List<Long> userIds, String title, String text, String type, Map para) throws Exception {
		if (userIds.size() == 0) {
			logger.warn("推送发送对象为空，返回。title:{},text:{},type:{},para:{}", title, text, type, para);
			return;
		}
		for (Long userId : userIds) {
			Pushs pushs = new Pushs();
			pushs.setId(commService.getSeq(Pushs.class));// 编号
			pushs.setUserId(userId);// 用户ID
			pushs.setTitle(title);// 标题
			pushs.setTxt(text);// 内容
			pushs.setTp(type);// 类型
			pushs.setPara(JacksonUtil.getJson(para));// 参数
			pushs.setSta("1");// 状态 1:未发送 2:已经发送 3:已经接收
			pushs.setCreateTime(new Date());// 创建时间
			mapper.insert(pushs);
			push(pushs);
		}
	}

	/**
	 * 推送执行
	 * 
	 * @param id
	 * @throws Exception
	 */
	public void push(Pushs pushs) throws Exception {
		if (!"1".equals(pushs.getSta())) {
			// 不是待发送的返回
			return;
		}
		List<String> allIdList = new ArrayList<String>();
		List<String> clientIdList = new ArrayList<String>();
		List<String> tokenList = new ArrayList<String>();
		if (StringUtils.isEmpty(pushs.getClientId())) {
			String sql = "select other_Id,other_Id2,other_Id3,TERMINAL from t_p_user_ids where user_id = #{userId} and tp=1";
			ParaMap pm = new ParaMap(sql);
			pm.addPara("userId", pushs.getUserId());
			List<ResultMap> list = commService.getMapList(pm);
			for (ResultMap r : list) {
				if ("iOS".equals(r.getStr("terminal"))) {
					tokenList.add(r.getStr("otherId2"));
					allIdList.add(r.getStr("otherId2"));
				} else {
					clientIdList.add(r.getStr("otherId"));
					allIdList.add(r.getStr("otherId"));
				}
			}
			if (allIdList.size() > 0) {
				pushs.setClientId(StringUtils.join(allIdList, ","));
				mapper.updateByPrimaryKey(pushs);
			}
		} else {
			String[] clientIds = pushs.getClientId().split(",");
			for (String clientId : clientIds) {
				if (clientId.length() == 64) {
					tokenList.add(clientId);
				} else {
					clientIdList.add(clientId);
				}
				allIdList.add(clientId);
			}
		}
		if (allIdList.size() == 0) {
			pushs.setSendResult("发送失败：无客户端ID");// 发送结果
			pushs.setLastSendTime(new Date());// 最后发送时间
			mapper.updateByPrimaryKey(pushs);
			return;
		}
		Map<String, Object> para = JacksonUtil.readValue(pushs.getPara(), Map.class);
		JsonObject jsonObject = new JsonObject();
		for (String s : para.keySet()) {
			if(para.get(s) instanceof String){
				jsonObject.addProperty(s, (String)para.get(s));
			}else{
				jsonObject.addProperty(s, JacksonUtil.getJson(para.get(s)));
			}
		}
		jsonObject.addProperty("tp", pushs.getTp());
		jsonObject.addProperty("pId", pushs.getId());
		pushs.setMsg("");
		if(clientIdList.size()>0){
			String pushResult = PushUtil.listNotificationMsg(clientIdList, pushs.getTitle(), pushs.getTxt(), jsonObject);
			pushs.setSta("2");// 状态 1:未发送 2:已经发送 3:已经接收
			pushs.setMsg(pushs.getMsg()+pushResult);// 发送返回信息
			pushs.setSendResult("发送成功");// 发送结果
			pushs.setLastSendTime(new Date());// 最后发送时间
		}
		if(tokenList.size()>0){
			for(String tk:tokenList){
				String pushResult = PushUtil.apnPush(tk, pushs.getTitle(), pushs.getTxt(), jsonObject);
				pushs.setMsg(pushs.getMsg()+pushResult);// 发送返回信息
			}
			pushs.setSta("2");// 状态 1:未发送 2:已经发送 3:已经接收
			pushs.setSendResult("发送成功");// 发送结果
			pushs.setLastSendTime(new Date());// 最后发送时间
		}
		mapper.updateByPrimaryKey(pushs);
	}

	public void recive(Long id) throws Exception {
		Pushs pushs = this.mapper.selectByPrimaryKey(id);
		if (pushs == null) {
			logger.warn("推送对象不存在：Id:{}", id);
			return;
		}
		pushs.setSta("3");// 状态 1:未发送 2:已经发送 3:已经接收
		pushs.setSendResult("接收成功");// 发送结果
		pushs.setReciveTime(new Date());// 接收时间
		mapper.updateByPrimaryKey(pushs);
	}
	
}