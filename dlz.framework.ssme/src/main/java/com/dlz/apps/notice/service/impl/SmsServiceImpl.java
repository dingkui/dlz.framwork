package com.dlz.apps.notice.service.impl;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aliyun.mns.client.CloudAccount;
import com.aliyun.mns.client.CloudTopic;
import com.aliyun.mns.client.MNSClient;
import com.aliyun.mns.common.ServiceException;
import com.aliyun.mns.model.BatchSmsAttributes;
import com.aliyun.mns.model.MessageAttributes;
import com.aliyun.mns.model.RawTopicMessage;
import com.aliyun.mns.model.TopicMessage;
import com.dlz.apps.notice.dao.SmsMapper;
import com.dlz.apps.notice.model.Sms;
import com.dlz.apps.notice.service.SmsService;
import com.dlz.framework.ssme.base.service.impl.BaseServiceImpl;

@Service
@Transactional(rollbackFor=Exception.class)
public class SmsServiceImpl extends BaseServiceImpl<Sms, Long> implements SmsService {

    @Autowired
    public void setMapper(SmsMapper mapper) {
        this.mapper=mapper;
    }
    

	/**
	 * Step 1. 获取主题引用
	 */
	private static CloudAccount account = new CloudAccount("LTAI66tVGfsIZSzg", "gtnRiacO2Jmnc0I4z5I9g6Ij5mkCUB", "http://1018586260602151.mns.cn-hangzhou.aliyuncs.com/");
	private static MNSClient client = account.getMNSClient();
	private static CloudTopic topic = client.getTopicRef("sms.topic-cn-hangzhou");

	@Override
	public Boolean sendMsg(String templateCode, String phone, Map<String, String> params) {
		/**
		 * Step 2. 设置SMS消息体（必须）
		 *
		 * 注：目前暂时不支持消息内容为空，需要指定消息内容，不为空即可。
		 */
		RawTopicMessage msg = new RawTopicMessage();
		msg.setMessageBody("sms-message");
		/**
		 * Step 3. 生成SMS消息属性
		 */
		MessageAttributes messageAttributes = new MessageAttributes();
		BatchSmsAttributes batchSmsAttributes = new BatchSmsAttributes();
		// 3.1 设置发送短信的签名（SMSSignName）
		batchSmsAttributes.setFreeSignName("藻钙云");
		// 3.2 设置发送短信使用的模板（SMSTempateCode）
		batchSmsAttributes.setTemplateCode(templateCode);
		// 3.3 设置发送短信所使用的模板中参数对应的值（在短信模板中定义的，没有可以不用设置）
		BatchSmsAttributes.SmsReceiverParams smsReceiverParams = new BatchSmsAttributes.SmsReceiverParams();
		for(String key : params.keySet()){
			smsReceiverParams.setParam(key, params.get(key));
		}
		// 3.4 增加接收短信的号码
		batchSmsAttributes.addSmsReceiver(phone, smsReceiverParams);
		messageAttributes.setBatchSmsAttributes(batchSmsAttributes);
		try {
			/**
			 * Step 4. 发布SMS消息
			 */
			TopicMessage ret = topic.publishMessage(msg, messageAttributes);
			return StringUtils.isNotBlank(ret.getMessageId()) ? true : false;
		} catch (ServiceException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		client.close();
		return false;
	}
}