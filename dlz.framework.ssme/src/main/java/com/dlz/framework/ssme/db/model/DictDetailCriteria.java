package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.DictDetailCriteria.GeneratedCriteria;

public class DictDetailCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public DictDetailCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andDictIdIsNull() {
            addCriterion("DICT_ID is null");return this;
        }

        public GeneratedCriteria andDictIdIsNotNull() {
            addCriterion("DICT_ID is not null");return this;
        }

        public GeneratedCriteria andDictIdEqualTo(String value) {
            addCriterion("DICT_ID =", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdNotEqualTo(String value) {
            addCriterion("DICT_ID <>", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdGreaterThan(String value) {
            addCriterion("DICT_ID >", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_ID >=", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdLessThan(String value) {
            addCriterion("DICT_ID <", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdLessThanOrEqualTo(String value) {
            addCriterion("DICT_ID <=", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdLike(String value) {
            addCriterion("DICT_ID like", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdNotLike(String value) {
            addCriterion("DICT_ID not like", value, "dictId");return this;
        }

        public GeneratedCriteria andDictIdIn(List<String> values) {
            addCriterion("DICT_ID in", values, "dictId");return this;
        }

        public GeneratedCriteria andDictIdNotIn(List<String> values) {
            addCriterion("DICT_ID not in", values, "dictId");return this;
        }

        public GeneratedCriteria andDictIdBetween(String value1, String value2) {
            addCriterion("DICT_ID between", value1, value2, "dictId");return this;
        }

        public GeneratedCriteria andDictIdNotBetween(String value1, String value2) {
            addCriterion("DICT_ID not between", value1, value2, "dictId");return this;
        }

        public GeneratedCriteria andDictParamValueIsNull() {
            addCriterion("DICT_PARAM_VALUE is null");return this;
        }

        public GeneratedCriteria andDictParamValueIsNotNull() {
            addCriterion("DICT_PARAM_VALUE is not null");return this;
        }

        public GeneratedCriteria andDictParamValueEqualTo(String value) {
            addCriterion("DICT_PARAM_VALUE =", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueNotEqualTo(String value) {
            addCriterion("DICT_PARAM_VALUE <>", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueGreaterThan(String value) {
            addCriterion("DICT_PARAM_VALUE >", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_VALUE >=", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueLessThan(String value) {
            addCriterion("DICT_PARAM_VALUE <", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueLessThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_VALUE <=", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueLike(String value) {
            addCriterion("DICT_PARAM_VALUE like", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueNotLike(String value) {
            addCriterion("DICT_PARAM_VALUE not like", value, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueIn(List<String> values) {
            addCriterion("DICT_PARAM_VALUE in", values, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueNotIn(List<String> values) {
            addCriterion("DICT_PARAM_VALUE not in", values, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_VALUE between", value1, value2, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamValueNotBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_VALUE not between", value1, value2, "dictParamValue");return this;
        }

        public GeneratedCriteria andDictParamNameIsNull() {
            addCriterion("DICT_PARAM_NAME is null");return this;
        }

        public GeneratedCriteria andDictParamNameIsNotNull() {
            addCriterion("DICT_PARAM_NAME is not null");return this;
        }

        public GeneratedCriteria andDictParamNameEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME =", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameNotEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME <>", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameGreaterThan(String value) {
            addCriterion("DICT_PARAM_NAME >", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME >=", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameLessThan(String value) {
            addCriterion("DICT_PARAM_NAME <", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameLessThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME <=", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameLike(String value) {
            addCriterion("DICT_PARAM_NAME like", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameNotLike(String value) {
            addCriterion("DICT_PARAM_NAME not like", value, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameIn(List<String> values) {
            addCriterion("DICT_PARAM_NAME in", values, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameNotIn(List<String> values) {
            addCriterion("DICT_PARAM_NAME not in", values, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_NAME between", value1, value2, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamNameNotBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_NAME not between", value1, value2, "dictParamName");return this;
        }

        public GeneratedCriteria andDictParamStatusIsNull() {
            addCriterion("DICT_PARAM_STATUS is null");return this;
        }

        public GeneratedCriteria andDictParamStatusIsNotNull() {
            addCriterion("DICT_PARAM_STATUS is not null");return this;
        }

        public GeneratedCriteria andDictParamStatusEqualTo(String value) {
            addCriterion("DICT_PARAM_STATUS =", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusNotEqualTo(String value) {
            addCriterion("DICT_PARAM_STATUS <>", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusGreaterThan(String value) {
            addCriterion("DICT_PARAM_STATUS >", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_STATUS >=", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusLessThan(String value) {
            addCriterion("DICT_PARAM_STATUS <", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusLessThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_STATUS <=", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusLike(String value) {
            addCriterion("DICT_PARAM_STATUS like", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusNotLike(String value) {
            addCriterion("DICT_PARAM_STATUS not like", value, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusIn(List<String> values) {
            addCriterion("DICT_PARAM_STATUS in", values, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusNotIn(List<String> values) {
            addCriterion("DICT_PARAM_STATUS not in", values, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_STATUS between", value1, value2, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamStatusNotBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_STATUS not between", value1, value2, "dictParamStatus");return this;
        }

        public GeneratedCriteria andDictParamNameEnIsNull() {
            addCriterion("DICT_PARAM_NAME_EN is null");return this;
        }

        public GeneratedCriteria andDictParamNameEnIsNotNull() {
            addCriterion("DICT_PARAM_NAME_EN is not null");return this;
        }

        public GeneratedCriteria andDictParamNameEnEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME_EN =", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnNotEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME_EN <>", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnGreaterThan(String value) {
            addCriterion("DICT_PARAM_NAME_EN >", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME_EN >=", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnLessThan(String value) {
            addCriterion("DICT_PARAM_NAME_EN <", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnLessThanOrEqualTo(String value) {
            addCriterion("DICT_PARAM_NAME_EN <=", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnLike(String value) {
            addCriterion("DICT_PARAM_NAME_EN like", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnNotLike(String value) {
            addCriterion("DICT_PARAM_NAME_EN not like", value, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnIn(List<String> values) {
            addCriterion("DICT_PARAM_NAME_EN in", values, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnNotIn(List<String> values) {
            addCriterion("DICT_PARAM_NAME_EN not in", values, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_NAME_EN between", value1, value2, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictParamNameEnNotBetween(String value1, String value2) {
            addCriterion("DICT_PARAM_NAME_EN not between", value1, value2, "dictParamNameEn");return this;
        }

        public GeneratedCriteria andDictOrderIsNull() {
            addCriterion("DICT_ORDER is null");return this;
        }

        public GeneratedCriteria andDictOrderIsNotNull() {
            addCriterion("DICT_ORDER is not null");return this;
        }

        public GeneratedCriteria andDictOrderEqualTo(Long value) {
            addCriterion("DICT_ORDER =", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderNotEqualTo(Long value) {
            addCriterion("DICT_ORDER <>", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderGreaterThan(Long value) {
            addCriterion("DICT_ORDER >", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("DICT_ORDER >=", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderLessThan(Long value) {
            addCriterion("DICT_ORDER <", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderLessThanOrEqualTo(Long value) {
            addCriterion("DICT_ORDER <=", value, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderIn(List<Long> values) {
            addCriterion("DICT_ORDER in", values, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderNotIn(List<Long> values) {
            addCriterion("DICT_ORDER not in", values, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderBetween(Long value1, Long value2) {
            addCriterion("DICT_ORDER between", value1, value2, "dictOrder");return this;
        }

        public GeneratedCriteria andDictOrderNotBetween(Long value1, Long value2) {
            addCriterion("DICT_ORDER not between", value1, value2, "dictOrder");return this;
        }
    }
}