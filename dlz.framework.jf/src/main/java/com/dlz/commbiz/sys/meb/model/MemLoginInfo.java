package com.dlz.commbiz.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;
import java.util.Date;

public class MemLoginInfo extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEM_LOGIN_INFO";

    public String tableColums = "ML_ID,ML_MID,ML_TIME,ML_IP,ML_POINT,ML_ARER,ML_TYPE,ML_CHANEL";

    /**
     * S_MEM_LOGIN_INFO.ML_ID
     * 登陆ID
     */
    private Long mlId;

    /**
     * S_MEM_LOGIN_INFO.ML_MID
     * 用户ID
     */
    private Long mlMid;

    /**
     * S_MEM_LOGIN_INFO.ML_TIME
     * 登录时间
     */
    private Date mlTime;

    /**
     * S_MEM_LOGIN_INFO.ML_IP
     * 登录IP
     */
    private String mlIp;

    /**
     * S_MEM_LOGIN_INFO.ML_POINT
     * 登录坐标地点
     */
    private String mlPoint;

    /**
     * S_MEM_LOGIN_INFO.ML_ARER
     * 地点
     */
    private String mlArer;

    /**
     * S_MEM_LOGIN_INFO.ML_TYPE
     * 登录方式 1:注册登录 2:用户名密码登录 3:QQ绑定登录 4:微信绑定登录 5:自动登录 6:短信登录 7:找回密码
     */
    private Long mlType;

    /**
     * S_MEM_LOGIN_INFO.ML_CHANEL
     * 渠道 1:PC 2:安卓 3:Iphone 4:IPAD 5:微信 6:手机站
     */
    private Long mlChanel;

    /**
     * S_MEM_LOGIN_INFO.ML_ID
     * 登陆ID
     */
    public Long getMlId() {
        return mlId;
    }

    /**
     * @param mlId the value for S_MEM_LOGIN_INFO.ML_ID
     * 登陆ID
     */
    public void setMlId(Long mlId) {
        this.mlId = mlId;
    }

    /**
     * S_MEM_LOGIN_INFO.ML_MID
     * 用户ID
     */
    public Long getMlMid() {
        return mlMid;
    }

    /**
     * @param mlMid the value for S_MEM_LOGIN_INFO.ML_MID
     * 用户ID
     */
    public void setMlMid(Long mlMid) {
        this.mlMid = mlMid;
    }

    /**
     * S_MEM_LOGIN_INFO.ML_TIME
     * 登录时间
     */
    public Date getMlTime() {
        return mlTime;
    }

    /**
     * @param mlTime the value for S_MEM_LOGIN_INFO.ML_TIME
     * 登录时间
     */
    public void setMlTime(Date mlTime) {
        this.mlTime = mlTime;
    }

    /**
     * S_MEM_LOGIN_INFO.ML_IP
     * 登录IP
     */
    public String getMlIp() {
        return mlIp;
    }

    /**
     * @param mlIp the value for S_MEM_LOGIN_INFO.ML_IP
     * 登录IP
     */
    public void setMlIp(String mlIp) {
        this.mlIp = mlIp == null ? null : mlIp.trim();
    }

    /**
     * S_MEM_LOGIN_INFO.ML_POINT
     * 登录坐标地点
     */
    public String getMlPoint() {
        return mlPoint;
    }

    /**
     * @param mlPoint the value for S_MEM_LOGIN_INFO.ML_POINT
     * 登录坐标地点
     */
    public void setMlPoint(String mlPoint) {
        this.mlPoint = mlPoint == null ? null : mlPoint.trim();
    }

    /**
     * S_MEM_LOGIN_INFO.ML_ARER
     * 地点
     */
    public String getMlArer() {
        return mlArer;
    }

    /**
     * @param mlArer the value for S_MEM_LOGIN_INFO.ML_ARER
     * 地点
     */
    public void setMlArer(String mlArer) {
        this.mlArer = mlArer == null ? null : mlArer.trim();
    }

    /**
     * S_MEM_LOGIN_INFO.ML_TYPE
     * 登录方式 1:注册登录 2:用户名密码登录 3:QQ绑定登录 4:微信绑定登录 5:自动登录 6:短信登录 7:找回密码
     */
    public Long getMlType() {
        return mlType;
    }

    /**
     * @param mlType the value for S_MEM_LOGIN_INFO.ML_TYPE
     * 登录方式 1:注册登录 2:用户名密码登录 3:QQ绑定登录 4:微信绑定登录 5:自动登录 6:短信登录 7:找回密码
     */
    public void setMlType(Long mlType) {
        this.mlType = mlType;
    }

    /**
     * S_MEM_LOGIN_INFO.ML_CHANEL
     * 渠道 1:PC 2:安卓 3:Iphone 4:IPAD 5:微信 6:手机站
     */
    public Long getMlChanel() {
        return mlChanel;
    }

    /**
     * @param mlChanel the value for S_MEM_LOGIN_INFO.ML_CHANEL
     * 渠道 1:PC 2:安卓 3:Iphone 4:IPAD 5:微信 6:手机站
     */
    public void setMlChanel(Long mlChanel) {
        this.mlChanel = mlChanel;
    }
}