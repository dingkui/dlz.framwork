package com.dlz.commbiz.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;

public class MebAccount extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEB_ACCOUNT";

    public String tableColums = "ID,A_MID,A_TYPE,A_NAME,A_BALANCE,A_FREEZ_AMOUNT";

    /**
     * S_MEB_ACCOUNT.ID
     * ID
     */
    private Long id;

    /**
     * S_MEB_ACCOUNT.A_MID
     * 用户id
     */
    private Long aMid;

    /**
     * S_MEB_ACCOUNT.A_TYPE
     * 账户类型  1:网关账户  2:平台账户  3:个人账户
     */
    private Long aType;

    /**
     * S_MEB_ACCOUNT.A_NAME
     * 账户名称
     */
    private String aName;

    /**
     * S_MEB_ACCOUNT.A_BALANCE
     * 余额
     */
    private Double aBalance;

    /**
     * S_MEB_ACCOUNT.A_FREEZ_AMOUNT
     * 冻结金额
     */
    private Double aFreezAmount;

    /**
     * S_MEB_ACCOUNT.ID
     * ID
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for S_MEB_ACCOUNT.ID
     * ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * S_MEB_ACCOUNT.A_MID
     * 用户id
     */
    public Long getaMid() {
        return aMid;
    }

    /**
     * @param aMid the value for S_MEB_ACCOUNT.A_MID
     * 用户id
     */
    public void setaMid(Long aMid) {
        this.aMid = aMid;
    }

    /**
     * S_MEB_ACCOUNT.A_TYPE
     * 账户类型  1:网关账户  2:平台账户  3:个人账户
     */
    public Long getaType() {
        return aType;
    }

    /**
     * @param aType the value for S_MEB_ACCOUNT.A_TYPE
     * 账户类型  1:网关账户  2:平台账户  3:个人账户
     */
    public void setaType(Long aType) {
        this.aType = aType;
    }

    /**
     * S_MEB_ACCOUNT.A_NAME
     * 账户名称
     */
    public String getaName() {
        return aName;
    }

    /**
     * @param aName the value for S_MEB_ACCOUNT.A_NAME
     * 账户名称
     */
    public void setaName(String aName) {
        this.aName = aName == null ? null : aName.trim();
    }

    /**
     * S_MEB_ACCOUNT.A_BALANCE
     * 余额
     */
    public Double getaBalance() {
        return aBalance;
    }

    /**
     * @param aBalance the value for S_MEB_ACCOUNT.A_BALANCE
     * 余额
     */
    public void setaBalance(Double aBalance) {
        this.aBalance = aBalance;
    }

    /**
     * S_MEB_ACCOUNT.A_FREEZ_AMOUNT
     * 冻结金额
     */
    public Double getaFreezAmount() {
        return aFreezAmount;
    }

    /**
     * @param aFreezAmount the value for S_MEB_ACCOUNT.A_FREEZ_AMOUNT
     * 冻结金额
     */
    public void setaFreezAmount(Double aFreezAmount) {
        this.aFreezAmount = aFreezAmount;
    }
}