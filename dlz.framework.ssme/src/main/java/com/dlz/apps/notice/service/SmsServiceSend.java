package com.dlz.apps.notice.service;

import java.util.Map;


public interface SmsServiceSend {

//    /**
//     * 个人会员注册是否成功通知
//     */
//    public Map<String, Object> smsPersonReg(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember);
//
//    /**
//     * 企业会员注册是否成功通知
//     */
//    public Map<String, Object> smsCompanyReg(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember);

    /**
     * 先不做
     * 院校会员注册通知
     */
    public boolean smsSchoolReg();

    /**
     * 先不做
     * 培训会员注册通知
     */
    public boolean smsTrainReg();

//    /**
//     * 找回密码短信
//     */
//    public Map<String, Object> smsGetPwd(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmemberr);
//
//    /**
//     * 手机验证码通知短信
//     */
//    public Map<String, Object> smsGetMobileKey(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember )  throws Exception ;
//
//    /**
//     * 面试邀请通知短信
//     */
//    public Map<String, Object> smsInSend(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember,String place)throws Exception;
//
//    /**
//     * 申请职位通知短信
//     */
//    public Map<String, Object> smsResumeSend(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember,String  place);
//
//    /**
//     * 生日祝福短信
//     */
//    public Map<String, Object> smsBirthday(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember);
//
//    /**
//     * 到期前3天短信通知
//     */
//    public Map<String, Object> smsMemberEnd3(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember);
//
//    /**
//     * 到期当天短信通知
//     */
//    public Map<String, Object> smsMemberEnd(String mobile,String code,Long actton,Long type,
//  			Member pmember,Member cmember);
    /**
     * 手机验证登录
     * @author  wangsl
     * @date 创建时间：2015-8-11 下午3:53:32 
     * @param mobile
     * @param code
     */
    public Map<String, Object> smsMemberLogin(String mobile,String code);
    
    /**
     * 手机注册
     * @author  wangsl
     * @date 创建时间：2015-8-11 下午3:53:32 
     * @param mobile
     * @param code
     */
    public Map<String, Object> smsMemberReg(String mobile,String code);
}
