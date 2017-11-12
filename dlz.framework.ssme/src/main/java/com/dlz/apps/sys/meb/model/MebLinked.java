package com.dlz.apps.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;
import java.util.Date;

public class MebLinked extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEB_LINKED";

    public String tableColums = "L_ID,L_MID,L_OPENID,L_TYPE,L_TIMESTAMP,L_FLAG,L_OPENNAME";

    /**
     * S_MEB_LINKED.L_ID
     * 
     */
    private Long lId;

    /**
     * S_MEB_LINKED.L_MID
     * 平台用户ID
     */
    private Long lMid;

    /**
     * S_MEB_LINKED.L_OPENID
     * 第三方授权ID
     */
    private String lOpenid;

    /**
     * S_MEB_LINKED.L_TYPE
     * 登录类型  1：qq  2：微信  3：支付宝 4: 融云 5:公众号
     */
    private Long lType;

    /**
     * S_MEB_LINKED.L_TIMESTAMP
     * 授权时间
     */
    private Date lTimestamp;

    /**
     * S_MEB_LINKED.L_FLAG
     * 是否有效 0：无效 1：有效
     */
    private Long lFlag;

    /**
     * S_MEB_LINKED.L_OPENNAME
     * 
     */
    private String lOpenname;

    /**
     * S_MEB_LINKED.L_ID
     * 
     */
    public Long getlId() {
        return lId;
    }

    /**
     * @param lId the value for S_MEB_LINKED.L_ID
     * 
     */
    public void setlId(Long lId) {
        this.lId = lId;
    }

    /**
     * S_MEB_LINKED.L_MID
     * 平台用户ID
     */
    public Long getlMid() {
        return lMid;
    }

    /**
     * @param lMid the value for S_MEB_LINKED.L_MID
     * 平台用户ID
     */
    public void setlMid(Long lMid) {
        this.lMid = lMid;
    }

    /**
     * S_MEB_LINKED.L_OPENID
     * 第三方授权ID
     */
    public String getlOpenid() {
        return lOpenid;
    }

    /**
     * @param lOpenid the value for S_MEB_LINKED.L_OPENID
     * 第三方授权ID
     */
    public void setlOpenid(String lOpenid) {
        this.lOpenid = lOpenid == null ? null : lOpenid.trim();
    }

    /**
     * S_MEB_LINKED.L_TYPE
     * 登录类型  1：qq  2：微信  3：支付宝 4: 融云 5:公众号
     */
    public Long getlType() {
        return lType;
    }

    /**
     * @param lType the value for S_MEB_LINKED.L_TYPE
     * 登录类型  1：qq  2：微信  3：支付宝 4: 融云 5:公众号
     */
    public void setlType(Long lType) {
        this.lType = lType;
    }

    /**
     * S_MEB_LINKED.L_TIMESTAMP
     * 授权时间
     */
    public Date getlTimestamp() {
        return lTimestamp;
    }

    /**
     * @param lTimestamp the value for S_MEB_LINKED.L_TIMESTAMP
     * 授权时间
     */
    public void setlTimestamp(Date lTimestamp) {
        this.lTimestamp = lTimestamp;
    }

    /**
     * S_MEB_LINKED.L_FLAG
     * 是否有效 0：无效 1：有效
     */
    public Long getlFlag() {
        return lFlag;
    }

    /**
     * @param lFlag the value for S_MEB_LINKED.L_FLAG
     * 是否有效 0：无效 1：有效
     */
    public void setlFlag(Long lFlag) {
        this.lFlag = lFlag;
    }

    /**
     * S_MEB_LINKED.L_OPENNAME
     * 
     */
    public String getlOpenname() {
        return lOpenname;
    }

    /**
     * @param lOpenname the value for S_MEB_LINKED.L_OPENNAME
     * 
     */
    public void setlOpenname(String lOpenname) {
        this.lOpenname = lOpenname == null ? null : lOpenname.trim();
    }
}