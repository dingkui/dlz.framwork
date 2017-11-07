package com.dlz.commbiz.sys.tool.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Counter extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "S_COUNTER";

    @JsonIgnore
    public String tableColums = "ID,STR,NUM";

    /**
     * S_COUNTER.ID
     * 
     */
    private Long id;

    /**
     * S_COUNTER.STR
     * 计数编码
     */
    private String str;

    /**
     * S_COUNTER.NUM
     * 计数值
     */
    private Long num;

    /**
     * S_COUNTER.ID
     * 
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the value for S_COUNTER.ID
     * 
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * S_COUNTER.STR
     * 计数编码
     */
    public String getStr() {
        return str;
    }

    /**
     * @param str the value for S_COUNTER.STR
     * 计数编码
     */
    public void setStr(String str) {
        this.str = str == null ? null : str.trim();
    }

    /**
     * S_COUNTER.NUM
     * 计数值
     */
    public Long getNum() {
        return num;
    }

    /**
     * @param num the value for S_COUNTER.NUM
     * 计数值
     */
    public void setNum(Long num) {
        this.num = num;
    }
}