package com.dlz.commbiz.dict.model;

import com.dlz.common.base.criteria.BaseCriteria;
import com.dlz.common.base.criteria.BaseGeneratedCriteria;
import com.dlz.commbiz.dict.model.DictCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class DictCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public DictCriteria() {
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

        public GeneratedCriteria andDictNameIsNull() {
            addCriterion("DICT_NAME is null");return this;
        }

        public GeneratedCriteria andDictNameIsNotNull() {
            addCriterion("DICT_NAME is not null");return this;
        }

        public GeneratedCriteria andDictNameEqualTo(String value) {
            addCriterion("DICT_NAME =", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameNotEqualTo(String value) {
            addCriterion("DICT_NAME <>", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameGreaterThan(String value) {
            addCriterion("DICT_NAME >", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_NAME >=", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameLessThan(String value) {
            addCriterion("DICT_NAME <", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameLessThanOrEqualTo(String value) {
            addCriterion("DICT_NAME <=", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameLike(String value) {
            addCriterion("DICT_NAME like", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameNotLike(String value) {
            addCriterion("DICT_NAME not like", value, "dictName");return this;
        }

        public GeneratedCriteria andDictNameIn(List<String> values) {
            addCriterion("DICT_NAME in", values, "dictName");return this;
        }

        public GeneratedCriteria andDictNameNotIn(List<String> values) {
            addCriterion("DICT_NAME not in", values, "dictName");return this;
        }

        public GeneratedCriteria andDictNameBetween(String value1, String value2) {
            addCriterion("DICT_NAME between", value1, value2, "dictName");return this;
        }

        public GeneratedCriteria andDictNameNotBetween(String value1, String value2) {
            addCriterion("DICT_NAME not between", value1, value2, "dictName");return this;
        }

        public GeneratedCriteria andDictDescIsNull() {
            addCriterion("DICT_DESC is null");return this;
        }

        public GeneratedCriteria andDictDescIsNotNull() {
            addCriterion("DICT_DESC is not null");return this;
        }

        public GeneratedCriteria andDictDescEqualTo(String value) {
            addCriterion("DICT_DESC =", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescNotEqualTo(String value) {
            addCriterion("DICT_DESC <>", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescGreaterThan(String value) {
            addCriterion("DICT_DESC >", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_DESC >=", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescLessThan(String value) {
            addCriterion("DICT_DESC <", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescLessThanOrEqualTo(String value) {
            addCriterion("DICT_DESC <=", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescLike(String value) {
            addCriterion("DICT_DESC like", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescNotLike(String value) {
            addCriterion("DICT_DESC not like", value, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescIn(List<String> values) {
            addCriterion("DICT_DESC in", values, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescNotIn(List<String> values) {
            addCriterion("DICT_DESC not in", values, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescBetween(String value1, String value2) {
            addCriterion("DICT_DESC between", value1, value2, "dictDesc");return this;
        }

        public GeneratedCriteria andDictDescNotBetween(String value1, String value2) {
            addCriterion("DICT_DESC not between", value1, value2, "dictDesc");return this;
        }

        public GeneratedCriteria andDictTypeIsNull() {
            addCriterion("DICT_TYPE is null");return this;
        }

        public GeneratedCriteria andDictTypeIsNotNull() {
            addCriterion("DICT_TYPE is not null");return this;
        }

        public GeneratedCriteria andDictTypeEqualTo(String value) {
            addCriterion("DICT_TYPE =", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeNotEqualTo(String value) {
            addCriterion("DICT_TYPE <>", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeGreaterThan(String value) {
            addCriterion("DICT_TYPE >", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_TYPE >=", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeLessThan(String value) {
            addCriterion("DICT_TYPE <", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeLessThanOrEqualTo(String value) {
            addCriterion("DICT_TYPE <=", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeLike(String value) {
            addCriterion("DICT_TYPE like", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeNotLike(String value) {
            addCriterion("DICT_TYPE not like", value, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeIn(List<String> values) {
            addCriterion("DICT_TYPE in", values, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeNotIn(List<String> values) {
            addCriterion("DICT_TYPE not in", values, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeBetween(String value1, String value2) {
            addCriterion("DICT_TYPE between", value1, value2, "dictType");return this;
        }

        public GeneratedCriteria andDictTypeNotBetween(String value1, String value2) {
            addCriterion("DICT_TYPE not between", value1, value2, "dictType");return this;
        }

        public GeneratedCriteria andDictStatusIsNull() {
            addCriterion("DICT_STATUS is null");return this;
        }

        public GeneratedCriteria andDictStatusIsNotNull() {
            addCriterion("DICT_STATUS is not null");return this;
        }

        public GeneratedCriteria andDictStatusEqualTo(String value) {
            addCriterion("DICT_STATUS =", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusNotEqualTo(String value) {
            addCriterion("DICT_STATUS <>", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusGreaterThan(String value) {
            addCriterion("DICT_STATUS >", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_STATUS >=", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusLessThan(String value) {
            addCriterion("DICT_STATUS <", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusLessThanOrEqualTo(String value) {
            addCriterion("DICT_STATUS <=", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusLike(String value) {
            addCriterion("DICT_STATUS like", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusNotLike(String value) {
            addCriterion("DICT_STATUS not like", value, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusIn(List<String> values) {
            addCriterion("DICT_STATUS in", values, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusNotIn(List<String> values) {
            addCriterion("DICT_STATUS not in", values, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusBetween(String value1, String value2) {
            addCriterion("DICT_STATUS between", value1, value2, "dictStatus");return this;
        }

        public GeneratedCriteria andDictStatusNotBetween(String value1, String value2) {
            addCriterion("DICT_STATUS not between", value1, value2, "dictStatus");return this;
        }

        public GeneratedCriteria andDictSourceIsNull() {
            addCriterion("DICT_SOURCE is null");return this;
        }

        public GeneratedCriteria andDictSourceIsNotNull() {
            addCriterion("DICT_SOURCE is not null");return this;
        }

        public GeneratedCriteria andDictSourceEqualTo(String value) {
            addCriterion("DICT_SOURCE =", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceNotEqualTo(String value) {
            addCriterion("DICT_SOURCE <>", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceGreaterThan(String value) {
            addCriterion("DICT_SOURCE >", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceGreaterThanOrEqualTo(String value) {
            addCriterion("DICT_SOURCE >=", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceLessThan(String value) {
            addCriterion("DICT_SOURCE <", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceLessThanOrEqualTo(String value) {
            addCriterion("DICT_SOURCE <=", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceLike(String value) {
            addCriterion("DICT_SOURCE like", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceNotLike(String value) {
            addCriterion("DICT_SOURCE not like", value, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceIn(List<String> values) {
            addCriterion("DICT_SOURCE in", values, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceNotIn(List<String> values) {
            addCriterion("DICT_SOURCE not in", values, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceBetween(String value1, String value2) {
            addCriterion("DICT_SOURCE between", value1, value2, "dictSource");return this;
        }

        public GeneratedCriteria andDictSourceNotBetween(String value1, String value2) {
            addCriterion("DICT_SOURCE not between", value1, value2, "dictSource");return this;
        }
    }
}