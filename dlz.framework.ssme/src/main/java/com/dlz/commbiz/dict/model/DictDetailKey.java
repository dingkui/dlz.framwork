package com.dlz.commbiz.dict.model;

import java.io.Serializable;

public class DictDetailKey implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * T_B_DICT_DETAIL.DICT_ID
     * 字典编号
     */
    private String dictId;

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_VALUE
     * 字典参数值
     */
    private String dictParamValue;

    /**
     * T_B_DICT_DETAIL.DICT_ID
     * 字典编号
     */
    public String getDictId() {
        return dictId;
    }

    /**
     * @param dictId the value for T_B_DICT_DETAIL.DICT_ID
     * 字典编号
     */
    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
    }

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_VALUE
     * 字典参数值
     */
    public String getDictParamValue() {
        return dictParamValue;
    }

    /**
     * @param dictParamValue the value for T_B_DICT_DETAIL.DICT_PARAM_VALUE
     * 字典参数值
     */
    public void setDictParamValue(String dictParamValue) {
        this.dictParamValue = dictParamValue == null ? null : dictParamValue.trim();
    }
}