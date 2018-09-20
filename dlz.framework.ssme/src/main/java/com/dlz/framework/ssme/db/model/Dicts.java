package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dicts extends BaseModel {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_B_DICTS";

    @JsonIgnore
    public String tableColums = "ID,NAME,BZ,PID,ORD,IS_LEAF,VAL,NAME_EN,CODE";

    /**
     * T_B_DICTS.ID
     * 编号
     */
    private Long id;

    /**
     * T_B_DICTS.NAME
     * 名称
     */
    private String name;

    /**
     * T_B_DICTS.BZ
     * 描述
     */
    private String bz;

    /**
     * T_B_DICTS.PID
     * 父节点
     */
    private Long pid;

    /**
     * T_B_DICTS.ORD
     * 排序
     */
    private Long ord;

    /**
     * T_B_DICTS.IS_LEAF
     * 是否子节点
     */
    private Long isLeaf;

    /**
     * T_B_DICTS.VAL
     * 值
     */
    private String val;

    /**
     * T_B_DICTS.NAME_EN
     * 英文名称
     */
    private String nameEn;

    /**
     * T_B_DICTS.CODE
     * 层级码
     */
    private String code;

    /**
     * T_B_DICTS.ID
     * 编号
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for T_B_DICTS.ID
     * 编号
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * T_B_DICTS.NAME
     * 名称
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the value for T_B_DICTS.NAME
     * 名称
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * T_B_DICTS.BZ
     * 描述
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz the value for T_B_DICTS.BZ
     * 描述
     */
    public void setBz(String bz) {
        this.bz = bz == null ? null : bz.trim();
    }

    /**
     * T_B_DICTS.PID
     * 父节点
     */
    public Long getPid() {
        return pid;
    }

    /**
     * @param pid the value for T_B_DICTS.PID
     * 父节点
     */
    public void setPid(Long pid) {
        this.pid = pid;
    }

    /**
     * T_B_DICTS.ORD
     * 排序
     */
    public Long getOrd() {
        return ord;
    }

    /**
     * @param ord the value for T_B_DICTS.ORD
     * 排序
     */
    public void setOrd(Long ord) {
        this.ord = ord;
    }

    /**
     * T_B_DICTS.IS_LEAF
     * 是否子节点
     */
    public Long getIsLeaf() {
        return isLeaf;
    }

    /**
     * @param isLeaf the value for T_B_DICTS.IS_LEAF
     * 是否子节点
     */
    public void setIsLeaf(Long isLeaf) {
        this.isLeaf = isLeaf;
    }

    /**
     * T_B_DICTS.VAL
     * 值
     */
    public String getVal() {
        return val;
    }

    /**
     * @param val the value for T_B_DICTS.VAL
     * 值
     */
    public void setVal(String val) {
        this.val = val == null ? null : val.trim();
    }

    /**
     * T_B_DICTS.NAME_EN
     * 英文名称
     */
    public String getNameEn() {
        return nameEn;
    }

    /**
     * @param nameEn the value for T_B_DICTS.NAME_EN
     * 英文名称
     */
    public void setNameEn(String nameEn) {
        this.nameEn = nameEn == null ? null : nameEn.trim();
    }

    /**
     * T_B_DICTS.CODE
     * 层级码
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the value for T_B_DICTS.CODE
     * 层级码
     */
    public void setCode(String code) {
        this.code = code == null ? null : code.trim();
    }
}