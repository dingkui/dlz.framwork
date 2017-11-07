package com.dlz.commbiz.sys.tool.model;

import com.dlz.common.base.criteria.BaseCriteria;
import com.dlz.common.base.criteria.BaseGeneratedCriteria;
import com.dlz.commbiz.sys.tool.model.CounterCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class CounterCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public CounterCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andIdIsNull() {
            addCriterion("ID is null");return this;
        }

        public GeneratedCriteria andIdIsNotNull() {
            addCriterion("ID is not null");return this;
        }

        public GeneratedCriteria andIdEqualTo(Long value) {
            addCriterion("ID =", value, "id");return this;
        }

        public GeneratedCriteria andIdNotEqualTo(Long value) {
            addCriterion("ID <>", value, "id");return this;
        }

        public GeneratedCriteria andIdGreaterThan(Long value) {
            addCriterion("ID >", value, "id");return this;
        }

        public GeneratedCriteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ID >=", value, "id");return this;
        }

        public GeneratedCriteria andIdLessThan(Long value) {
            addCriterion("ID <", value, "id");return this;
        }

        public GeneratedCriteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("ID <=", value, "id");return this;
        }

        public GeneratedCriteria andIdIn(List<Long> values) {
            addCriterion("ID in", values, "id");return this;
        }

        public GeneratedCriteria andIdNotIn(List<Long> values) {
            addCriterion("ID not in", values, "id");return this;
        }

        public GeneratedCriteria andIdBetween(Long value1, Long value2) {
            addCriterion("ID between", value1, value2, "id");return this;
        }

        public GeneratedCriteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("ID not between", value1, value2, "id");return this;
        }

        public GeneratedCriteria andStrIsNull() {
            addCriterion("STR is null");return this;
        }

        public GeneratedCriteria andStrIsNotNull() {
            addCriterion("STR is not null");return this;
        }

        public GeneratedCriteria andStrEqualTo(String value) {
            addCriterion("STR =", value, "str");return this;
        }

        public GeneratedCriteria andStrNotEqualTo(String value) {
            addCriterion("STR <>", value, "str");return this;
        }

        public GeneratedCriteria andStrGreaterThan(String value) {
            addCriterion("STR >", value, "str");return this;
        }

        public GeneratedCriteria andStrGreaterThanOrEqualTo(String value) {
            addCriterion("STR >=", value, "str");return this;
        }

        public GeneratedCriteria andStrLessThan(String value) {
            addCriterion("STR <", value, "str");return this;
        }

        public GeneratedCriteria andStrLessThanOrEqualTo(String value) {
            addCriterion("STR <=", value, "str");return this;
        }

        public GeneratedCriteria andStrLike(String value) {
            addCriterion("STR like", value, "str");return this;
        }

        public GeneratedCriteria andStrNotLike(String value) {
            addCriterion("STR not like", value, "str");return this;
        }

        public GeneratedCriteria andStrIn(List<String> values) {
            addCriterion("STR in", values, "str");return this;
        }

        public GeneratedCriteria andStrNotIn(List<String> values) {
            addCriterion("STR not in", values, "str");return this;
        }

        public GeneratedCriteria andStrBetween(String value1, String value2) {
            addCriterion("STR between", value1, value2, "str");return this;
        }

        public GeneratedCriteria andStrNotBetween(String value1, String value2) {
            addCriterion("STR not between", value1, value2, "str");return this;
        }

        public GeneratedCriteria andNumIsNull() {
            addCriterion("NUM is null");return this;
        }

        public GeneratedCriteria andNumIsNotNull() {
            addCriterion("NUM is not null");return this;
        }

        public GeneratedCriteria andNumEqualTo(Long value) {
            addCriterion("NUM =", value, "num");return this;
        }

        public GeneratedCriteria andNumNotEqualTo(Long value) {
            addCriterion("NUM <>", value, "num");return this;
        }

        public GeneratedCriteria andNumGreaterThan(Long value) {
            addCriterion("NUM >", value, "num");return this;
        }

        public GeneratedCriteria andNumGreaterThanOrEqualTo(Long value) {
            addCriterion("NUM >=", value, "num");return this;
        }

        public GeneratedCriteria andNumLessThan(Long value) {
            addCriterion("NUM <", value, "num");return this;
        }

        public GeneratedCriteria andNumLessThanOrEqualTo(Long value) {
            addCriterion("NUM <=", value, "num");return this;
        }

        public GeneratedCriteria andNumIn(List<Long> values) {
            addCriterion("NUM in", values, "num");return this;
        }

        public GeneratedCriteria andNumNotIn(List<Long> values) {
            addCriterion("NUM not in", values, "num");return this;
        }

        public GeneratedCriteria andNumBetween(Long value1, Long value2) {
            addCriterion("NUM between", value1, value2, "num");return this;
        }

        public GeneratedCriteria andNumNotBetween(Long value1, Long value2) {
            addCriterion("NUM not between", value1, value2, "num");return this;
        }
    }
}