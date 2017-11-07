package com.dlz.commbiz.sys.meb.model;

import com.dlz.framework.db.modal.BaseModel;
import java.util.Date;

public class MebAccDetail extends BaseModel {
    private static final long serialVersionUID = 1L;

    public String tableName = "S_MEB_ACC_DETAIL";

    public String tableColums = "A_ID,A_OID,A_OPDATE,A_MID_FROM,A_MID_TO,A_TYPE,A_AMOUNT,A_CARDID,A_OTHER_OID,NOTE,A_OFFER_ID,A_FLAG";

    /**
     * S_MEB_ACC_DETAIL.A_ID
     * 主键
     */
    private Long aId;

    /**
     * S_MEB_ACC_DETAIL.A_OID
     * 操作流水号
     */
    private String aOid;

    /**
     * S_MEB_ACC_DETAIL.A_OPDATE
     * 操作时间
     */
    private Date aOpdate;

    /**
     * S_MEB_ACC_DETAIL.A_MID_FROM
     * 支付账户  充值时：设置为0
     */
    private Long aMidFrom;

    /**
     * S_MEB_ACC_DETAIL.A_MID_TO
     * 收款账户  提现时：设置为0
     */
    private Long aMidTo;

    /**
     * S_MEB_ACC_DETAIL.A_TYPE
     * 操作类型  1:充值 2:工资预付 31:工资支付（预付款支付） 32:工资支付（余额支付）  4:预付款退回 5:工资垫付 6:提现 
     */
    private String aType;

    /**
     * S_MEB_ACC_DETAIL.A_AMOUNT
     * 金额
     */
    private Double aAmount;

    /**
     * S_MEB_ACC_DETAIL.A_CARDID
     * 银行卡号
     */
    private String aCardid;

    /**
     * S_MEB_ACC_DETAIL.A_OTHER_OID
     * 第三方平台流水号
     */
    private String aOtherOid;

    /**
     * S_MEB_ACC_DETAIL.NOTE
     * 备注
     */
    private String note;

    /**
     * S_MEB_ACC_DETAIL.A_OFFER_ID
     * offer id
     */
    private Long aOfferId;

    /**
     * S_MEB_ACC_DETAIL.A_FLAG
     * (充值、提现)状态：0,初始;1,成功;2,失败;3,拒绝
     */
    private Long aFlag;

    /**
     * S_MEB_ACC_DETAIL.A_ID
     * 主键
     */
    public Long getaId() {
        return aId;
    }

    /**
     * @param aId the value for S_MEB_ACC_DETAIL.A_ID
     * 主键
     */
    public void setaId(Long aId) {
        this.aId = aId;
    }

    /**
     * S_MEB_ACC_DETAIL.A_OID
     * 操作流水号
     */
    public String getaOid() {
        return aOid;
    }

    /**
     * @param aOid the value for S_MEB_ACC_DETAIL.A_OID
     * 操作流水号
     */
    public void setaOid(String aOid) {
        this.aOid = aOid == null ? null : aOid.trim();
    }

    /**
     * S_MEB_ACC_DETAIL.A_OPDATE
     * 操作时间
     */
    public Date getaOpdate() {
        return aOpdate;
    }

    /**
     * @param aOpdate the value for S_MEB_ACC_DETAIL.A_OPDATE
     * 操作时间
     */
    public void setaOpdate(Date aOpdate) {
        this.aOpdate = aOpdate;
    }

    /**
     * S_MEB_ACC_DETAIL.A_MID_FROM
     * 支付账户  充值时：设置为0
     */
    public Long getaMidFrom() {
        return aMidFrom;
    }

    /**
     * @param aMidFrom the value for S_MEB_ACC_DETAIL.A_MID_FROM
     * 支付账户  充值时：设置为0
     */
    public void setaMidFrom(Long aMidFrom) {
        this.aMidFrom = aMidFrom;
    }

    /**
     * S_MEB_ACC_DETAIL.A_MID_TO
     * 收款账户  提现时：设置为0
     */
    public Long getaMidTo() {
        return aMidTo;
    }

    /**
     * @param aMidTo the value for S_MEB_ACC_DETAIL.A_MID_TO
     * 收款账户  提现时：设置为0
     */
    public void setaMidTo(Long aMidTo) {
        this.aMidTo = aMidTo;
    }

    /**
     * S_MEB_ACC_DETAIL.A_TYPE
     * 操作类型  1:充值 2:工资预付 31:工资支付（预付款支付） 32:工资支付（余额支付）  4:预付款退回 5:工资垫付 6:提现 
     */
    public String getaType() {
        return aType;
    }

    /**
     * @param aType the value for S_MEB_ACC_DETAIL.A_TYPE
     * 操作类型  1:充值 2:工资预付 31:工资支付（预付款支付） 32:工资支付（余额支付）  4:预付款退回 5:工资垫付 6:提现 
     */
    public void setaType(String aType) {
        this.aType = aType == null ? null : aType.trim();
    }

    /**
     * S_MEB_ACC_DETAIL.A_AMOUNT
     * 金额
     */
    public Double getaAmount() {
        return aAmount;
    }

    /**
     * @param aAmount the value for S_MEB_ACC_DETAIL.A_AMOUNT
     * 金额
     */
    public void setaAmount(Double aAmount) {
        this.aAmount = aAmount;
    }

    /**
     * S_MEB_ACC_DETAIL.A_CARDID
     * 银行卡号
     */
    public String getaCardid() {
        return aCardid;
    }

    /**
     * @param aCardid the value for S_MEB_ACC_DETAIL.A_CARDID
     * 银行卡号
     */
    public void setaCardid(String aCardid) {
        this.aCardid = aCardid == null ? null : aCardid.trim();
    }

    /**
     * S_MEB_ACC_DETAIL.A_OTHER_OID
     * 第三方平台流水号
     */
    public String getaOtherOid() {
        return aOtherOid;
    }

    /**
     * @param aOtherOid the value for S_MEB_ACC_DETAIL.A_OTHER_OID
     * 第三方平台流水号
     */
    public void setaOtherOid(String aOtherOid) {
        this.aOtherOid = aOtherOid == null ? null : aOtherOid.trim();
    }

    /**
     * S_MEB_ACC_DETAIL.NOTE
     * 备注
     */
    public String getNote() {
        return note;
    }

    /**
     * @param note the value for S_MEB_ACC_DETAIL.NOTE
     * 备注
     */
    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    /**
     * S_MEB_ACC_DETAIL.A_OFFER_ID
     * offer id
     */
    public Long getaOfferId() {
        return aOfferId;
    }

    /**
     * @param aOfferId the value for S_MEB_ACC_DETAIL.A_OFFER_ID
     * offer id
     */
    public void setaOfferId(Long aOfferId) {
        this.aOfferId = aOfferId;
    }

    /**
     * S_MEB_ACC_DETAIL.A_FLAG
     * (充值、提现)状态：0,初始;1,成功;2,失败;3,拒绝
     */
    public Long getaFlag() {
        return aFlag;
    }

    /**
     * @param aFlag the value for S_MEB_ACC_DETAIL.A_FLAG
     * (充值、提现)状态：0,初始;1,成功;2,失败;3,拒绝
     */
    public void setaFlag(Long aFlag) {
        this.aFlag = aFlag;
    }
}