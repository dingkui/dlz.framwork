package com.dlz.apps.notice.db.model;

import java.util.Date;

import com.dlz.framework.db.modal.BaseModel;

public class Mail extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    /**
     * JOB_MAIL.M_ID
     * 邮件ID
     */
    private Long mId;

    /**
     * JOB_MAIL.M_TITLE
     * 邮件标题
     */
    private String mTitle;

    /**
     * JOB_MAIL.M_CYCLE
     * 邮件周期
     */
    private Long mCycle;

    /**
     * JOB_MAIL.M_MEMBER
     * 会员邮件
     */
    private String mMember;

    /**
     * JOB_MAIL.M_EMAIL
     * 电子邮件
     */
    private String mEmail;

    /**
     * JOB_MAIL.M_NUMBER
     * 邮件数量
     */
    private Long mNumber;

    /**
     * JOB_MAIL.M_ADDDATE
     * 添加邮件时间
     */
    private Date mAdddate;

    /**
     * JOB_MAIL.M_UPDATE
     * 更新邮件时间
     */
    private Date mUpdate;

    /**
     * JOB_MAIL.M_SENDDATE
     * 发送邮件时间
     */
    private Date mSenddate;

    /**
     * JOB_MAIL.M_TYPE
     * 邮件类型
     */
    private Long mType;

    /**
     * JOB_MAIL.M_CONTENT
     * 邮件内容
     */
    private String mContent;

    /**
     * JOB_MAIL.M_ID
     * 邮件ID
     */
    public Long getmId() {
        return mId;
    }

    /**
     * @param 邮件ID mId:JOB_MAIL.M_ID
     */
    public void setmId(Long mId) {
        this.mId = mId;
    }

    /**
     * JOB_MAIL.M_TITLE
     * 邮件标题
     */
    public String getmTitle() {
        return mTitle;
    }

    /**
     * @param 邮件标题 mTitle:JOB_MAIL.M_TITLE
     */
    public void setmTitle(String mTitle) {
        this.mTitle = mTitle == null ? null : mTitle.trim();
    }

    /**
     * JOB_MAIL.M_CYCLE
     * 邮件周期
     */
    public Long getmCycle() {
        return mCycle;
    }

    /**
     * @param 邮件周期 mCycle:JOB_MAIL.M_CYCLE
     */
    public void setmCycle(Long mCycle) {
        this.mCycle = mCycle;
    }

    /**
     * JOB_MAIL.M_MEMBER
     * 会员邮件
     */
    public String getmMember() {
        return mMember;
    }

    /**
     * @param 会员邮件 mMember:JOB_MAIL.M_MEMBER
     */
    public void setmMember(String mMember) {
        this.mMember = mMember == null ? null : mMember.trim();
    }

    /**
     * JOB_MAIL.M_EMAIL
     * 电子邮件
     */
    public String getmEmail() {
        return mEmail;
    }

    /**
     * @param 电子邮件 mEmail:JOB_MAIL.M_EMAIL
     */
    public void setmEmail(String mEmail) {
        this.mEmail = mEmail == null ? null : mEmail.trim();
    }

    /**
     * JOB_MAIL.M_NUMBER
     * 邮件数量
     */
    public Long getmNumber() {
        return mNumber;
    }

    /**
     * @param 邮件数量 mNumber:JOB_MAIL.M_NUMBER
     */
    public void setmNumber(Long mNumber) {
        this.mNumber = mNumber;
    }

    /**
     * JOB_MAIL.M_ADDDATE
     * 添加邮件时间
     */
    public Date getmAdddate() {
        return mAdddate;
    }

    /**
     * @param 添加邮件时间 mAdddate:JOB_MAIL.M_ADDDATE
     */
    public void setmAdddate(Date mAdddate) {
        this.mAdddate = mAdddate;
    }

    /**
     * JOB_MAIL.M_UPDATE
     * 更新邮件时间
     */
    public Date getmUpdate() {
        return mUpdate;
    }

    /**
     * @param 更新邮件时间 mUpdate:JOB_MAIL.M_UPDATE
     */
    public void setmUpdate(Date mUpdate) {
        this.mUpdate = mUpdate;
    }

    /**
     * JOB_MAIL.M_SENDDATE
     * 发送邮件时间
     */
    public Date getmSenddate() {
        return mSenddate;
    }

    /**
     * @param 发送邮件时间 mSenddate:JOB_MAIL.M_SENDDATE
     */
    public void setmSenddate(Date mSenddate) {
        this.mSenddate = mSenddate;
    }

    /**
     * JOB_MAIL.M_TYPE
     * 邮件类型
     */
    public Long getmType() {
        return mType;
    }

    /**
     * @param 邮件类型 mType:JOB_MAIL.M_TYPE
     */
    public void setmType(Long mType) {
        this.mType = mType;
    }

    /**
     * JOB_MAIL.M_CONTENT
     * 邮件内容
     */
    public String getmContent() {
        return mContent;
    }

    /**
     * @param 邮件内容 mContent:JOB_MAIL.M_CONTENT
     */
    public void setmContent(String mContent) {
        this.mContent = mContent == null ? null : mContent.trim();
    }
}