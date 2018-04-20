package com.dlz.commbiz.dict.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class DictDetail extends DictDetailKey {
    private static final long serialVersionUID = 1L;

    @JsonIgnore
    public String tableName = "T_B_DICT_DETAIL";

    @JsonIgnore
    public String tableColums = "DICT_ID,DICT_PARAM_VALUE,DICT_PARAM_NAME,DICT_PARAM_STATUS,DICT_PARAM_NAME_EN,DICT_ORDER";

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_NAME
     * 字典参数名称
     */
    private String dictParamName;

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_STATUS
     * 字典参数状态
     */
    private String dictParamStatus;

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_NAME_EN
     * 字典参数名称英文
     */
    private String dictParamNameEn;

    /**
     * T_B_DICT_DETAIL.DICT_ORDER
     * 字典排序
     */
    private Long dictOrder;

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_NAME
     * 字典参数名称
     */
    public String getDictParamName() {
        return dictParamName;
    }

    /**
     * @param dictParamName the value for T_B_DICT_DETAIL.DICT_PARAM_NAME
     * 字典参数名称
     */
    public void setDictParamName(String dictParamName) {
        this.dictParamName = dictParamName == null ? null : dictParamName.trim();
    }

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_STATUS
     * 字典参数状态
     */
    public String getDictParamStatus() {
        return dictParamStatus;
    }

    /**
     * @param dictParamStatus the value for T_B_DICT_DETAIL.DICT_PARAM_STATUS
     * 字典参数状态
     */
    public void setDictParamStatus(String dictParamStatus) {
        this.dictParamStatus = dictParamStatus == null ? null : dictParamStatus.trim();
    }

    /**
     * T_B_DICT_DETAIL.DICT_PARAM_NAME_EN
     * 字典参数名称英文
     */
    public String getDictParamNameEn() {
        return dictParamNameEn;
    }

    /**
     * @param dictParamNameEn the value for T_B_DICT_DETAIL.DICT_PARAM_NAME_EN
     * 字典参数名称英文
     */
    public void setDictParamNameEn(String dictParamNameEn) {
        this.dictParamNameEn = dictParamNameEn == null ? null : dictParamNameEn.trim();
    }

    /**
     * T_B_DICT_DETAIL.DICT_ORDER
     * 字典排序
     */
    public Long getDictOrder() {
        return dictOrder;
    }

    /**
     * @param dictOrder the value for T_B_DICT_DETAIL.DICT_ORDER
     * 字典排序
     */
    public void setDictOrder(Long dictOrder) {
        this.dictOrder = dictOrder;
    }
}