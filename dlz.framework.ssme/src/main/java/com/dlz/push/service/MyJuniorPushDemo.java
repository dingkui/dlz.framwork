package com.dlz.push.service;
import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.SingleMessage;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.TransmissionTemplate;
public class MyJuniorPushDemo {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
		static String appId = "OhHfwNWSzG9OhPV2Gua544";
    static String appKey = "yDdCqDarj76Th04VEEJbo8";
    static String masterSecret = "yg9KSNJUgZ9LRBJgB3coW2";
    static String devicetoken = "E4F6345021EA0929E3358B8C2ADD3F6E1C757F7D374E45FE54D6C550BF70FCC5";//iOS设备唯一标识，由苹果那边生成
    static String url = "http://sdk.open.api.igexin.com/apiex.htm";
       public static void apnpush() throws Exception {
              IGtPush push = new IGtPush(url, appKey, masterSecret);  
//              APNTemplate t = new APNTemplate();
//              APNPayload apnpayload = new APNPayload();
//              apnpayload.setSound("");
//              //apn高级推送
//              APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
//              ////通知文本消息标题
//              alertMsg.setTitle("aaaaaa");
//              //通知文本消息字符串
//              alertMsg.setBody("bbbb");
//              //对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
//              alertMsg.setTitleLocKey("ccccc");
//              //指定执行按钮所使用的Localizable.strings
//              alertMsg.setActionLocKey("ddddd");
//              apnpayload.setAlertMsg(alertMsg);
//
//              t.setAPNInfo(apnpayload);
              TransmissionTemplate tr=getTemplate();
              SingleMessage sm = new SingleMessage();
              sm.setData(tr);
              IPushResult ret0 = push.pushAPNMessageToSingle(appId, devicetoken, sm);
              System.out.println(ret0.getResponse());

       }
       
       public static TransmissionTemplate getTemplate() {
         TransmissionTemplate template = new TransmissionTemplate();
         template.setAppId(appId);
         template.setAppkey(appKey);
         template.setTransmissionContent("透传内容11111111");
         template.setTransmissionType(2);
         APNPayload payload = new APNPayload();
         payload.setBadge(31);
         payload.setContentAvailable(41);
         payload.setSound("default");
         payload.setCategory("1111");
         //简单模式APNPayload.SimpleMsg 
         payload.setAlertMsg(new APNPayload.SimpleAlertMsg("hello"));
         //字典模式使用下者
         //payload.setAlertMsg(getDictionaryAlertMsg());
         template.setAPNInfo(payload);
         return template;
     }
     private static APNPayload.DictionaryAlertMsg getDictionaryAlertMsg(){
         APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
         alertMsg.setBody("body");
         alertMsg.setActionLocKey("ActionLockey");
         alertMsg.setLocKey("LocKey");
         alertMsg.addLocArg("loc-args");
         alertMsg.setLaunchImage("launch-image");
         // IOS8.2以上版本支持
         alertMsg.setTitle("Title");
         alertMsg.setTitleLocKey("TitleLocKey");
         alertMsg.addTitleLocArg("TitleLocArg");
         return alertMsg;
     }

       public static void main(String[] args) throws Exception {
              apnpush();
       }
}
