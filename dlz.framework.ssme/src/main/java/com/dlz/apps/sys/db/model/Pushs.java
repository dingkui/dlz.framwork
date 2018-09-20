package com.dlz.apps.sys.db.model;

import java.util.Date;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Pushs extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "S_PUSHS";

    @JsonIgnore
    public String tableColums = "ID,USER_ID,CLIENT_ID,TITLE,TXT,TP,PARA,STA,MSG,SEND_RESULT,LAST_SEND_TIME,RECIVE_TIME,CREATE_TIME";

    /**
     * S_PUSHS.ID
     * 编号
     */
    private Long id;

    /**
     * S_PUSHS.USER_ID
     * 用户ID
     */
    private Long userId;

    /**
     * S_PUSHS.CLIENT_ID
     * 客户端ID
     */
    private String clientId;

    /**
     * S_PUSHS.TITLE
     * 标题
     */
    private String title;

    /**
     * S_PUSHS.TXT
     * 内容
     */
    private String txt;

    /**
     * S_PUSHS.TP
     * 类型
     */
    private String tp;

    /**
     * S_PUSHS.PARA
     * 参数
     */
    private String para;

    /**
     * S_PUSHS.STA
     * 状态 1:未发送 2:已经发送 3:已经接收 
     */
    private String sta;

    /**
     * S_PUSHS.MSG
     * 发送返回信息
     */
    private String msg;

    /**
     * S_PUSHS.SEND_RESULT
     * 发送结果
     */
    private String sendResult;

    /**
     * S_PUSHS.LAST_SEND_TIME
     * 最后发送时间
     */
    private Date lastSendTime;

    /**
     * S_PUSHS.RECIVE_TIME
     * 接收时间
     */
    private Date reciveTime;

    /**
     * S_PUSHS.CREATE_TIME
     * 创建时间
     */
    private Date createTime;

    /**
     * S_PUSHS.ID
     * 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for S_PUSHS.ID
     * 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * S_PUSHS.USER_ID
     * 用户ID
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId the value for S_PUSHS.USER_ID
     * 用户ID
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * S_PUSHS.CLIENT_ID
     * 客户端ID
     */
    public String getClientId() {
        return clientId;
    }

    /**
     * @param clientId the value for S_PUSHS.CLIENT_ID
     * 客户端ID
     */
    public void setClientId(String clientId) {
        this.clientId = clientId == null ? null : clientId.trim();
    }

    /**
     * S_PUSHS.TITLE
     * 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the value for S_PUSHS.TITLE
     * 标题
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * S_PUSHS.TXT
     * 内容
     */
    public String getTxt() {
        return txt;
    }

    /**
     * @param txt the value for S_PUSHS.TXT
     * 内容
     */
    public void setTxt(String txt) {
        this.txt = txt == null ? null : txt.trim();
    }

    /**
     * S_PUSHS.TP
     * 类型
     */
    public String getTp() {
        return tp;
    }

    /**
     * @param tp the value for S_PUSHS.TP
     * 类型
     */
    public void setTp(String tp) {
        this.tp = tp == null ? null : tp.trim();
    }

    /**
     * S_PUSHS.PARA
     * 参数
     */
    public String getPara() {
        return para;
    }

    /**
     * @param para the value for S_PUSHS.PARA
     * 参数
     */
    public void setPara(String para) {
        this.para = para == null ? null : para.trim();
    }

    /**
     * S_PUSHS.STA
     * 状态 1:未发送 2:已经发送 3:已经接收 
     */
    public String getSta() {
        return sta;
    }

    /**
     * @param sta the value for S_PUSHS.STA
     * 状态 1:未发送 2:已经发送 3:已经接收 
     */
    public void setSta(String sta) {
        this.sta = sta == null ? null : sta.trim();
    }

    /**
     * S_PUSHS.MSG
     * 发送返回信息
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the value for S_PUSHS.MSG
     * 发送返回信息
     */
    public void setMsg(String msg) {
        this.msg = msg == null ? null : msg.trim();
    }

    /**
     * S_PUSHS.SEND_RESULT
     * 发送结果
     */
    public String getSendResult() {
        return sendResult;
    }

    /**
     * @param sendResult the value for S_PUSHS.SEND_RESULT
     * 发送结果
     */
    public void setSendResult(String sendResult) {
        this.sendResult = sendResult == null ? null : sendResult.trim();
    }

    /**
     * S_PUSHS.LAST_SEND_TIME
     * 最后发送时间
     */
    public Date getLastSendTime() {
        return lastSendTime;
    }

    /**
     * @param lastSendTime the value for S_PUSHS.LAST_SEND_TIME
     * 最后发送时间
     */
    public void setLastSendTime(Date lastSendTime) {
        this.lastSendTime = lastSendTime;
    }

    /**
     * S_PUSHS.RECIVE_TIME
     * 接收时间
     */
    public Date getReciveTime() {
        return reciveTime;
    }

    /**
     * @param reciveTime the value for S_PUSHS.RECIVE_TIME
     * 接收时间
     */
    public void setReciveTime(Date reciveTime) {
        this.reciveTime = reciveTime;
    }

    /**
     * S_PUSHS.CREATE_TIME
     * 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime the value for S_PUSHS.CREATE_TIME
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}