package com.dlz.commbiz.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;
import java.util.Date;

public class Mem extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEM";

    public String tableColums = "ID,LOGIN,PWD,REG_DATE,lAST_LOGIN_IP,LAST_LOGIN_DATE,MOBILE,EMAIL,REFEREE";

    /**
     * S_MEM.ID
     * 用户ID
     */
    private Long id;

    /**
     * S_MEM.LOGIN
     * 用户名
     */
    private String login;

    /**
     * S_MEM.PWD
     * 密码
     */
    private String pwd;

    /**
     * S_MEM.REG_DATE
     * 注册时间
     */
    private Date regDate;

    /**
     * S_MEM.lAST_LOGIN_IP
     * 登录IP
     */
    private String lastLoginIp;

    /**
     * S_MEM.LAST_LOGIN_DATE
     * 最后登录时间
     */
    private Date lastLoginDate;

    /**
     * S_MEM.MOBILE
     * 手机号
     */
    private String mobile;

    /**
     * S_MEM.EMAIL
     * 邮箱
     */
    private String email;

    /**
     * S_MEM.REFEREE
     * 推荐人 MID、手机号、邮箱
     */
    private String referee;

    /**
     * S_MEM.ID
     * 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for S_MEM.ID
     * 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * S_MEM.LOGIN
     * 用户名
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the value for S_MEM.LOGIN
     * 用户名
     */
    public void setLogin(String login) {
        this.login = login == null ? null : login.trim();
    }

    /**
     * S_MEM.PWD
     * 密码
     */
    public String getPwd() {
        return pwd;
    }

    /**
     * @param pwd the value for S_MEM.PWD
     * 密码
     */
    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    /**
     * S_MEM.REG_DATE
     * 注册时间
     */
    public Date getRegDate() {
        return regDate;
    }

    /**
     * @param regDate the value for S_MEM.REG_DATE
     * 注册时间
     */
    public void setRegDate(Date regDate) {
        this.regDate = regDate;
    }

    /**
     * S_MEM.lAST_LOGIN_IP
     * 登录IP
     */
    public String getLastLoginIp() {
        return lastLoginIp;
    }

    /**
     * @param lastLoginIp the value for S_MEM.lAST_LOGIN_IP
     * 登录IP
     */
    public void setLastLoginIp(String lastLoginIp) {
        this.lastLoginIp = lastLoginIp == null ? null : lastLoginIp.trim();
    }

    /**
     * S_MEM.LAST_LOGIN_DATE
     * 最后登录时间
     */
    public Date getLastLoginDate() {
        return lastLoginDate;
    }

    /**
     * @param lastLoginDate the value for S_MEM.LAST_LOGIN_DATE
     * 最后登录时间
     */
    public void setLastLoginDate(Date lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    /**
     * S_MEM.MOBILE
     * 手机号
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * @param mobile the value for S_MEM.MOBILE
     * 手机号
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * S_MEM.EMAIL
     * 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the value for S_MEM.EMAIL
     * 邮箱
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * S_MEM.REFEREE
     * 推荐人 MID、手机号、邮箱
     */
    public String getReferee() {
        return referee;
    }

    /**
     * @param referee the value for S_MEM.REFEREE
     * 推荐人 MID、手机号、邮箱
     */
    public void setReferee(String referee) {
        this.referee = referee == null ? null : referee.trim();
    }
}