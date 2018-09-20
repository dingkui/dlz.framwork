package com.dlz.apps.notice.db.model;

import java.util.Date;

import com.dlz.framework.db.modal.BaseModel;

public class Sms extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    /**
     * JOB_SMS.S_ID
     * null
     */
    private Long sId;

    /**
     * JOB_SMS.S_MEMBERLOGIN
     * 当前账号
     */
    private String sMemberlogin;

    /**
     * JOB_SMS.S_TOMOBILE
     * 到手机
     */
    private String sTomobile;

    /**
     * JOB_SMS.S_ISSUCCESS
     * 是否成功 0 ,否 1 是
     */
    private Long sIssuccess;

    /**
     * JOB_SMS.S_SENDTIME
     * 发送时间
     */
    private Date sSendtime;

    /**
     * JOB_SMS.S_CONTENT
     * 内容
     */
    private String sContent;

    /**
     * JOB_SMS.S_TOMEMBERLOGIN
     * 接收的账户
     */
    private String sTomemberlogin;

    /**
     * JOB_SMS.S_MEMBER_NAME
     * 发送者名字
     */
    private String sMemberName;

    /**
     * JOB_SMS.S_TOMEMBER_NAME
     * 接受者名字
     */
    private String sTomemberName;

    /**
     * JOB_SMS.S_ID
     * null
     */
    public Long getsId() {
        return sId;
    }

    /**
     * @param null sId:JOB_SMS.S_ID
     */
    public void setsId(Long sId) {
        this.sId = sId;
    }

    /**
     * JOB_SMS.S_MEMBERLOGIN
     * 当前账号
     */
    public String getsMemberlogin() {
        return sMemberlogin;
    }

    /**
     * @param 当前账号 sMemberlogin:JOB_SMS.S_MEMBERLOGIN
     */
    public void setsMemberlogin(String sMemberlogin) {
        this.sMemberlogin = sMemberlogin == null ? null : sMemberlogin.trim();
    }

    /**
     * JOB_SMS.S_TOMOBILE
     * 到手机
     */
    public String getsTomobile() {
        return sTomobile;
    }

    /**
     * @param 到手机 sTomobile:JOB_SMS.S_TOMOBILE
     */
    public void setsTomobile(String sTomobile) {
        this.sTomobile = sTomobile == null ? null : sTomobile.trim();
    }

    /**
     * JOB_SMS.S_ISSUCCESS
     * 是否成功 0 ,否 1 是
     */
    public Long getsIssuccess() {
        return sIssuccess;
    }

    /**
     * @param 是否成功 0 ,否 1 是 sIssuccess:JOB_SMS.S_ISSUCCESS
     */
    public void setsIssuccess(Long sIssuccess) {
        this.sIssuccess = sIssuccess;
    }

    /**
     * JOB_SMS.S_SENDTIME
     * 发送时间
     */
    public Date getsSendtime() {
        return sSendtime;
    }

    /**
     * @param 发送时间 sSendtime:JOB_SMS.S_SENDTIME
     */
    public void setsSendtime(Date sSendtime) {
        this.sSendtime = sSendtime;
    }

    /**
     * JOB_SMS.S_CONTENT
     * 内容
     */
    public String getsContent() {
        return sContent;
    }

    /**
     * @param 内容 sContent:JOB_SMS.S_CONTENT
     */
    public void setsContent(String sContent) {
        this.sContent = sContent == null ? null : sContent.trim();
    }

    /**
     * JOB_SMS.S_TOMEMBERLOGIN
     * 接收的账户
     */
    public String getsTomemberlogin() {
        return sTomemberlogin;
    }

    /**
     * @param 接收的账户 sTomemberlogin:JOB_SMS.S_TOMEMBERLOGIN
     */
    public void setsTomemberlogin(String sTomemberlogin) {
        this.sTomemberlogin = sTomemberlogin == null ? null : sTomemberlogin.trim();
    }

    /**
     * JOB_SMS.S_MEMBER_NAME
     * 发送者名字
     */
    public String getsMemberName() {
        return sMemberName;
    }

    /**
     * @param 发送者名字 sMemberName:JOB_SMS.S_MEMBER_NAME
     */
    public void setsMemberName(String sMemberName) {
        this.sMemberName = sMemberName == null ? null : sMemberName.trim();
    }

    /**
     * JOB_SMS.S_TOMEMBER_NAME
     * 接受者名字
     */
    public String getsTomemberName() {
        return sTomemberName;
    }

    /**
     * @param 接受者名字 sTomemberName:JOB_SMS.S_TOMEMBER_NAME
     */
    public void setsTomemberName(String sTomemberName) {
        this.sTomemberName = sTomemberName == null ? null : sTomemberName.trim();
    }
}