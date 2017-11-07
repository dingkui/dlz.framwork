package com.dlz.commbiz.notice.model;

import java.util.Date;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Announce extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "C_ANNOUNCE";

    @JsonIgnore
    public String tableColums = "ID,A_TITLE,A_AUTHOR,A_DATEANDTIME,A_ISNEW,A_CHANNELID,A_SHOWTYPE,A_OUTTIME,A_CONTENT";

    /**
     * C_ANNOUNCE.ID
     * 通告ID
     */
    private Long id;

    /**
     * C_ANNOUNCE.A_TITLE
     * 通告标题
     */
    private String aTitle;

    /**
     * C_ANNOUNCE.A_AUTHOR
     * 通告作者
     */
    private String aAuthor;

    /**
     * C_ANNOUNCE.A_DATEANDTIME
     * 通告日期和时间
     */
    private Date aDateandtime;

    /**
     * C_ANNOUNCE.A_ISNEW
     * 
     */
    private Long aIsnew;

    /**
     * C_ANNOUNCE.A_CHANNELID
     * 通告频道ID
     */
    private Long aChannelid;

    /**
     * C_ANNOUNCE.A_SHOWTYPE
     * 通告类型
     */
    private Long aShowtype;

    /**
     * C_ANNOUNCE.A_OUTTIME
     * 通告结束时间
     */
    private Date aOuttime;

    /**
     * C_ANNOUNCE.A_CONTENT
     * 通告内容
     */
    private String aContent;

    /**
     * C_ANNOUNCE.ID
     * 通告ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for C_ANNOUNCE.ID
     * 通告ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * C_ANNOUNCE.A_TITLE
     * 通告标题
     */
    public String getaTitle() {
        return aTitle;
    }

    /**
     * @param aTitle the value for C_ANNOUNCE.A_TITLE
     * 通告标题
     */
    public void setaTitle(String aTitle) {
        this.aTitle = aTitle == null ? null : aTitle.trim();
    }

    /**
     * C_ANNOUNCE.A_AUTHOR
     * 通告作者
     */
    public String getaAuthor() {
        return aAuthor;
    }

    /**
     * @param aAuthor the value for C_ANNOUNCE.A_AUTHOR
     * 通告作者
     */
    public void setaAuthor(String aAuthor) {
        this.aAuthor = aAuthor == null ? null : aAuthor.trim();
    }

    /**
     * C_ANNOUNCE.A_DATEANDTIME
     * 通告日期和时间
     */
    public Date getaDateandtime() {
        return aDateandtime;
    }

    /**
     * @param aDateandtime the value for C_ANNOUNCE.A_DATEANDTIME
     * 通告日期和时间
     */
    public void setaDateandtime(Date aDateandtime) {
        this.aDateandtime = aDateandtime;
    }

    /**
     * C_ANNOUNCE.A_ISNEW
     * 
     */
    public Long getaIsnew() {
        return aIsnew;
    }

    /**
     * @param aIsnew the value for C_ANNOUNCE.A_ISNEW
     * 
     */
    public void setaIsnew(Long aIsnew) {
        this.aIsnew = aIsnew;
    }

    /**
     * C_ANNOUNCE.A_CHANNELID
     * 通告频道ID
     */
    public Long getaChannelid() {
        return aChannelid;
    }

    /**
     * @param aChannelid the value for C_ANNOUNCE.A_CHANNELID
     * 通告频道ID
     */
    public void setaChannelid(Long aChannelid) {
        this.aChannelid = aChannelid;
    }

    /**
     * C_ANNOUNCE.A_SHOWTYPE
     * 通告类型
     */
    public Long getaShowtype() {
        return aShowtype;
    }

    /**
     * @param aShowtype the value for C_ANNOUNCE.A_SHOWTYPE
     * 通告类型
     */
    public void setaShowtype(Long aShowtype) {
        this.aShowtype = aShowtype;
    }

    /**
     * C_ANNOUNCE.A_OUTTIME
     * 通告结束时间
     */
    public Date getaOuttime() {
        return aOuttime;
    }

    /**
     * @param aOuttime the value for C_ANNOUNCE.A_OUTTIME
     * 通告结束时间
     */
    public void setaOuttime(Date aOuttime) {
        this.aOuttime = aOuttime;
    }

    /**
     * C_ANNOUNCE.A_CONTENT
     * 通告内容
     */
    public String getaContent() {
        return aContent;
    }

    /**
     * @param aContent the value for C_ANNOUNCE.A_CONTENT
     * 通告内容
     */
    public void setaContent(String aContent) {
        this.aContent = aContent == null ? null : aContent.trim();
    }
}