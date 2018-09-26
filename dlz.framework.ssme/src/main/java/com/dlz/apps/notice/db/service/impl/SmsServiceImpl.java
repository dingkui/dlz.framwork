package com.dlz.apps.notice.db.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.dlz.apps.notice.db.dao.SmsMapper;
import com.dlz.apps.notice.db.model.Sms;
import com.dlz.apps.notice.db.service.SmsService;
import org.slf4j.Logger;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;
import com.dlz.framework.util.JacksonUtil;

@Service
@Transactional(rollbackFor = Exception.class)
public class SmsServiceImpl extends BaseServiceImpl<Sms, Long> implements SmsService {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

	@Autowired
	public void setMapper(SmsMapper mapper) {
		this.mapper = mapper;
	}

	private static Logger logger = org.slf4j.LoggerFactory.getLogger(SmsServiceImpl.class);

	// 产品名称:云通信短信API产品,开发者无需替换
	private static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	private static final String domain = "dysmsapi.aliyuncs.com";

	// 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	private static final String accessKeyId = "LTAI66tVGfsIZSzg";
	private static final String accessKeySecret = "gtnRiacO2Jmnc0I4z5I9g6Ij5mkCUB";

	@Override
	public Boolean sendMsg(String templateCode, String phone, Map<String, String> params) {
		try {
			// 可自助调整超时时间
//			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//			System.setProperty("sun.net.client.defaultReadTimeout", "10000");
			logger.debug("==============阿里云短信发送开始==============");
			logger.debug("手机号:"+phone);
			logger.debug("请求参数："+JacksonUtil.writeValueAsString(params));
			logger.debug("============================================");
			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(phone);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("藻钙云");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(templateCode);
			
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam(JacksonUtil.writeValueAsString(params));
			// 选填-上行短信扩展码(无特殊需求用户请忽略此字段)
			// request.setSmsUpExtendCode("90997");
			
			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			// request.setOutId("yourOutId");
			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			logger.debug("返回结果:"+JacksonUtil.writeValueAsString(sendSmsResponse));
			logger.debug("==============阿里云短信发送开始==============");
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				return true;
			}
		} catch (Exception ex) {
			logger.error(ex.getMessage(), ex);
		}
		return false;
	}
}