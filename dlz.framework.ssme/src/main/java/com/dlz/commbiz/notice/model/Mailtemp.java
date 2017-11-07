package com.dlz.commbiz.notice.model;

import com.dlz.framework.db.modal.BaseModel;

public class Mailtemp extends BaseModel {
    private static final long serialVersionUID = 1L;

    /**
     * JOB_MAILTEMP.M_ID
     * 邮件ID
     */
    private Long mId;

    /**
     * JOB_MAILTEMP.M_TIT
     * 邮件标题
     */
    private String mTit;

    /**
     * JOB_MAILTEMP.M_INFO
     * 标题
     */
    private String mInfo;

    /**
     * JOB_MAILTEMP.M_SIGN
     * 标签
     */
    private String mSign;

    /**
     * JOB_MAILTEMP.M_ORDER
     * 排序
     */
    private Long mOrder;

    /**
     * JOB_MAILTEMP.M_CON
     * 内容
     */
    private String mCon;

    /**
     * JOB_MAILTEMP.M_ID
     * 邮件ID
     */
    public Long getmId() {
        return mId;
    }

    /**
     * @param 邮件ID mId:JOB_MAILTEMP.M_ID
     */
    public void setmId(Long mId) {
        this.mId = mId;
    }

    /**
     * JOB_MAILTEMP.M_TIT
     * 邮件标题
     */
    public String getmTit() {
        return mTit;
    }

    /**
     * @param 邮件标题 mTit:JOB_MAILTEMP.M_TIT
     */
    public void setmTit(String mTit) {
        this.mTit = mTit == null ? null : mTit.trim();
    }

    /**
     * JOB_MAILTEMP.M_INFO
     * 标题
     */
    public String getmInfo() {
        return mInfo;
    }

    /**
     * @param 标题 mInfo:JOB_MAILTEMP.M_INFO
     */
    public void setmInfo(String mInfo) {
        this.mInfo = mInfo == null ? null : mInfo.trim();
    }

    /**
     * JOB_MAILTEMP.M_SIGN
     * 标签
     */
    public String getmSign() {
        return mSign;
    }

    /**
     * @param 标签 mSign:JOB_MAILTEMP.M_SIGN
     */
    public void setmSign(String mSign) {
        this.mSign = mSign == null ? null : mSign.trim();
    }

    /**
     * JOB_MAILTEMP.M_ORDER
     * 排序
     */
    public Long getmOrder() {
        return mOrder;
    }

    /**
     * @param 排序 mOrder:JOB_MAILTEMP.M_ORDER
     */
    public void setmOrder(Long mOrder) {
        this.mOrder = mOrder;
    }

    /**
     * JOB_MAILTEMP.M_CON
     * 内容
     */
    public String getmCon() {
        return mCon;
    }

    /**
     * @param 内容 mCon:JOB_MAILTEMP.M_CON
     */
    public void setmCon(String mCon) {
        this.mCon = mCon == null ? null : mCon.trim();
    }
}