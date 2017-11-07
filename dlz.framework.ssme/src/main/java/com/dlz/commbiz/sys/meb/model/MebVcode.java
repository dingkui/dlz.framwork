package com.dlz.commbiz.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;
import java.util.Date;

public class MebVcode extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEB_VCODE";

    public String tableColums = "V_ID,V_M_ID,V_CODE,V_VALID_DATE,V_SEND_DATE,V_SEND_STR,V_TYPE,V_ACTION";

    /**
     * S_MEB_VCODE.V_ID
     * 认证ID
     */
    private Long vId;

    /**
     * S_MEB_VCODE.V_M_ID
     * 用户ID
     */
    private Long vMId;

    /**
     * S_MEB_VCODE.V_CODE
     * 发送编码
     */
    private String vCode;

    /**
     * S_MEB_VCODE.V_VALID_DATE
     * 有效期
     */
    private Date vValidDate;

    /**
     * S_MEB_VCODE.V_SEND_DATE
     * 发送时间
     */
    private Date vSendDate;

    /**
     * S_MEB_VCODE.V_SEND_STR
     * 发送对象 邮箱或者手机号
     */
    private String vSendStr;

    /**
     * S_MEB_VCODE.V_TYPE
     * 发送对象区分  1:手机 2:邮箱
     */
    private Long vType;

    /**
     * S_MEB_VCODE.V_ACTION
     * 功能区分 1：验证  2：修改邮箱 3：忘记密码 4 个人手机注册成功   5 企业手机注册成功 6发送邮件面试邀请 7.申请职位成功向企业方发送
8 生日短信 9 到期前3天短信 10 到期当天短信

     */
    private Long vAction;

    /**
     * S_MEB_VCODE.V_ID
     * 认证ID
     */
    public Long getvId() {
        return vId;
    }

    /**
     * @param vId the value for S_MEB_VCODE.V_ID
     * 认证ID
     */
    public void setvId(Long vId) {
        this.vId = vId;
    }

    /**
     * S_MEB_VCODE.V_M_ID
     * 用户ID
     */
    public Long getvMId() {
        return vMId;
    }

    /**
     * @param vMId the value for S_MEB_VCODE.V_M_ID
     * 用户ID
     */
    public void setvMId(Long vMId) {
        this.vMId = vMId;
    }

    /**
     * S_MEB_VCODE.V_CODE
     * 发送编码
     */
    public String getvCode() {
        return vCode;
    }

    /**
     * @param vCode the value for S_MEB_VCODE.V_CODE
     * 发送编码
     */
    public void setvCode(String vCode) {
        this.vCode = vCode == null ? null : vCode.trim();
    }

    /**
     * S_MEB_VCODE.V_VALID_DATE
     * 有效期
     */
    public Date getvValidDate() {
        return vValidDate;
    }

    /**
     * @param vValidDate the value for S_MEB_VCODE.V_VALID_DATE
     * 有效期
     */
    public void setvValidDate(Date vValidDate) {
        this.vValidDate = vValidDate;
    }

    /**
     * S_MEB_VCODE.V_SEND_DATE
     * 发送时间
     */
    public Date getvSendDate() {
        return vSendDate;
    }

    /**
     * @param vSendDate the value for S_MEB_VCODE.V_SEND_DATE
     * 发送时间
     */
    public void setvSendDate(Date vSendDate) {
        this.vSendDate = vSendDate;
    }

    /**
     * S_MEB_VCODE.V_SEND_STR
     * 发送对象 邮箱或者手机号
     */
    public String getvSendStr() {
        return vSendStr;
    }

    /**
     * @param vSendStr the value for S_MEB_VCODE.V_SEND_STR
     * 发送对象 邮箱或者手机号
     */
    public void setvSendStr(String vSendStr) {
        this.vSendStr = vSendStr == null ? null : vSendStr.trim();
    }

    /**
     * S_MEB_VCODE.V_TYPE
     * 发送对象区分  1:手机 2:邮箱
     */
    public Long getvType() {
        return vType;
    }

    /**
     * @param vType the value for S_MEB_VCODE.V_TYPE
     * 发送对象区分  1:手机 2:邮箱
     */
    public void setvType(Long vType) {
        this.vType = vType;
    }

    /**
     * S_MEB_VCODE.V_ACTION
     * 功能区分 1：验证  2：修改邮箱 3：忘记密码 4 个人手机注册成功   5 企业手机注册成功 6发送邮件面试邀请 7.申请职位成功向企业方发送
8 生日短信 9 到期前3天短信 10 到期当天短信

     */
    public Long getvAction() {
        return vAction;
    }

    /**
     * @param vAction the value for S_MEB_VCODE.V_ACTION
     * 功能区分 1：验证  2：修改邮箱 3：忘记密码 4 个人手机注册成功   5 企业手机注册成功 6发送邮件面试邀请 7.申请职位成功向企业方发送
8 生日短信 9 到期前3天短信 10 到期当天短信

     */
    public void setvAction(Long vAction) {
        this.vAction = vAction;
    }
}