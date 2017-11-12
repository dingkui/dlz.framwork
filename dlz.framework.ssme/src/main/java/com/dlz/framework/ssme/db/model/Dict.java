package com.dlz.framework.ssme.db.model;

import com.dlz.framework.db.modal.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class Dict extends BaseModel {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_B_DICT";

    @JsonIgnore
    public String tableColums = "DICT_ID,DICT_NAME,DICT_DESC,DICT_TYPE,DICT_STATUS,DICT_SOURCE";

    /**
     * T_B_DICT.DICT_ID
     * 字典编号
     */
    private String dictId;

    /**
     * T_B_DICT.DICT_NAME
     * 字典名称
     */
    private String dictName;

    /**
     * T_B_DICT.DICT_DESC
     * 字典描述
     */
    private String dictDesc;

    /**
     * T_B_DICT.DICT_TYPE
     * 字典类型
     */
    private String dictType;

    /**
     * T_B_DICT.DICT_STATUS
     * 字典状态
     */
    private String dictStatus;

    /**
     * T_B_DICT.DICT_SOURCE
     * SQL
     */
    private String dictSource;

    /**
     * T_B_DICT.DICT_ID
     * 字典编号
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * @param dictId the value for T_B_DICT.DICT_ID
     * 字典编号
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    /**
     * T_B_DICT.DICT_NAME
     * 字典名称
     */
    public String getDictName() {
        return dictName;
    }

    /**
     * @param dictName the value for T_B_DICT.DICT_NAME
     * 字典名称
     */
    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    /**
     * T_B_DICT.DICT_DESC
     * 字典描述
     */
    public String getDictDesc() {
        return dictDesc;
    }

    /**
     * @param dictDesc the value for T_B_DICT.DICT_DESC
     * 字典描述
     */
    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    /**
     * T_B_DICT.DICT_TYPE
     * 字典类型
     */
    public String getDictType() {
        return dictType;
    }

    /**
     * @param dictType the value for T_B_DICT.DICT_TYPE
     * 字典类型
     */
    public void setDictType(String dictType) {
        this.dictType = dictType == null ? null : dictType.trim();
    }

    /**
     * T_B_DICT.DICT_STATUS
     * 字典状态
     */
    public String getDictStatus() {
        return dictStatus;
    }

    /**
     * @param dictStatus the value for T_B_DICT.DICT_STATUS
     * 字典状态
     */
    public void setDictStatus(String dictStatus) {
        this.dictStatus = dictStatus == null ? null : dictStatus.trim();
    }

    /**
     * T_B_DICT.DICT_SOURCE
     * SQL
     */
    public String getDictSource() {
        return dictSource;
    }

    /**
     * @param dictSource the value for T_B_DICT.DICT_SOURCE
     * SQL
     */
    public void setDictSource(String dictSource) {
        this.dictSource = dictSource == null ? null : dictSource.trim();
    }
}