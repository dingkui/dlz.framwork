package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.BaseSetCriteria.GeneratedCriteria;

public class BaseSetCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public BaseSetCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andBaseCodeIsNull() {
            addCriterion("BASE_CODE is null");return this;
        }

        public GeneratedCriteria andBaseCodeIsNotNull() {
            addCriterion("BASE_CODE is not null");return this;
        }

        public GeneratedCriteria andBaseCodeEqualTo(String value) {
            addCriterion("BASE_CODE =", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeNotEqualTo(String value) {
            addCriterion("BASE_CODE <>", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeGreaterThan(String value) {
            addCriterion("BASE_CODE >", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_CODE >=", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeLessThan(String value) {
            addCriterion("BASE_CODE <", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeLessThanOrEqualTo(String value) {
            addCriterion("BASE_CODE <=", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeLike(String value) {
            addCriterion("BASE_CODE like", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeNotLike(String value) {
            addCriterion("BASE_CODE not like", value, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeIn(List<String> values) {
            addCriterion("BASE_CODE in", values, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeNotIn(List<String> values) {
            addCriterion("BASE_CODE not in", values, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeBetween(String value1, String value2) {
            addCriterion("BASE_CODE between", value1, value2, "baseCode");return this;
        }

        public GeneratedCriteria andBaseCodeNotBetween(String value1, String value2) {
            addCriterion("BASE_CODE not between", value1, value2, "baseCode");return this;
        }

        public GeneratedCriteria andBaseValueIsNull() {
            addCriterion("BASE_VALUE is null");return this;
        }

        public GeneratedCriteria andBaseValueIsNotNull() {
            addCriterion("BASE_VALUE is not null");return this;
        }

        public GeneratedCriteria andBaseValueEqualTo(String value) {
            addCriterion("BASE_VALUE =", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueNotEqualTo(String value) {
            addCriterion("BASE_VALUE <>", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueGreaterThan(String value) {
            addCriterion("BASE_VALUE >", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueGreaterThanOrEqualTo(String value) {
            addCriterion("BASE_VALUE >=", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueLessThan(String value) {
            addCriterion("BASE_VALUE <", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueLessThanOrEqualTo(String value) {
            addCriterion("BASE_VALUE <=", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueLike(String value) {
            addCriterion("BASE_VALUE like", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueNotLike(String value) {
            addCriterion("BASE_VALUE not like", value, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueIn(List<String> values) {
            addCriterion("BASE_VALUE in", values, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueNotIn(List<String> values) {
            addCriterion("BASE_VALUE not in", values, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueBetween(String value1, String value2) {
            addCriterion("BASE_VALUE between", value1, value2, "baseValue");return this;
        }

        public GeneratedCriteria andBaseValueNotBetween(String value1, String value2) {
            addCriterion("BASE_VALUE not between", value1, value2, "baseValue");return this;
        }

        public GeneratedCriteria andStatusIsNull() {
            addCriterion("STATUS is null");return this;
        }

        public GeneratedCriteria andStatusIsNotNull() {
            addCriterion("STATUS is not null");return this;
        }

        public GeneratedCriteria andStatusEqualTo(String value) {
            addCriterion("STATUS =", value, "status");return this;
        }

        public GeneratedCriteria andStatusNotEqualTo(String value) {
            addCriterion("STATUS <>", value, "status");return this;
        }

        public GeneratedCriteria andStatusGreaterThan(String value) {
            addCriterion("STATUS >", value, "status");return this;
        }

        public GeneratedCriteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("STATUS >=", value, "status");return this;
        }

        public GeneratedCriteria andStatusLessThan(String value) {
            addCriterion("STATUS <", value, "status");return this;
        }

        public GeneratedCriteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("STATUS <=", value, "status");return this;
        }

        public GeneratedCriteria andStatusLike(String value) {
            addCriterion("STATUS like", value, "status");return this;
        }

        public GeneratedCriteria andStatusNotLike(String value) {
            addCriterion("STATUS not like", value, "status");return this;
        }

        public GeneratedCriteria andStatusIn(List<String> values) {
            addCriterion("STATUS in", values, "status");return this;
        }

        public GeneratedCriteria andStatusNotIn(List<String> values) {
            addCriterion("STATUS not in", values, "status");return this;
        }

        public GeneratedCriteria andStatusBetween(String value1, String value2) {
            addCriterion("STATUS between", value1, value2, "status");return this;
        }

        public GeneratedCriteria andStatusNotBetween(String value1, String value2) {
            addCriterion("STATUS not between", value1, value2, "status");return this;
        }

        public GeneratedCriteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");return this;
        }

        public GeneratedCriteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");return this;
        }

        public GeneratedCriteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");return this;
        }

        public GeneratedCriteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");return this;
        }

        public GeneratedCriteria andCreateIdIsNull() {
            addCriterion("CREATE_ID is null");return this;
        }

        public GeneratedCriteria andCreateIdIsNotNull() {
            addCriterion("CREATE_ID is not null");return this;
        }

        public GeneratedCriteria andCreateIdEqualTo(Long value) {
            addCriterion("CREATE_ID =", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdNotEqualTo(Long value) {
            addCriterion("CREATE_ID <>", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdGreaterThan(Long value) {
            addCriterion("CREATE_ID >", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("CREATE_ID >=", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdLessThan(Long value) {
            addCriterion("CREATE_ID <", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdLessThanOrEqualTo(Long value) {
            addCriterion("CREATE_ID <=", value, "createId");return this;
        }

        public GeneratedCriteria andCreateIdIn(List<Long> values) {
            addCriterion("CREATE_ID in", values, "createId");return this;
        }

        public GeneratedCriteria andCreateIdNotIn(List<Long> values) {
            addCriterion("CREATE_ID not in", values, "createId");return this;
        }

        public GeneratedCriteria andCreateIdBetween(Long value1, Long value2) {
            addCriterion("CREATE_ID between", value1, value2, "createId");return this;
        }

        public GeneratedCriteria andCreateIdNotBetween(Long value1, Long value2) {
            addCriterion("CREATE_ID not between", value1, value2, "createId");return this;
        }

        public GeneratedCriteria andCreateNameIsNull() {
            addCriterion("CREATE_NAME is null");return this;
        }

        public GeneratedCriteria andCreateNameIsNotNull() {
            addCriterion("CREATE_NAME is not null");return this;
        }

        public GeneratedCriteria andCreateNameEqualTo(String value) {
            addCriterion("CREATE_NAME =", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameNotEqualTo(String value) {
            addCriterion("CREATE_NAME <>", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameGreaterThan(String value) {
            addCriterion("CREATE_NAME >", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME >=", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameLessThan(String value) {
            addCriterion("CREATE_NAME <", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameLessThanOrEqualTo(String value) {
            addCriterion("CREATE_NAME <=", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameLike(String value) {
            addCriterion("CREATE_NAME like", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameNotLike(String value) {
            addCriterion("CREATE_NAME not like", value, "createName");return this;
        }

        public GeneratedCriteria andCreateNameIn(List<String> values) {
            addCriterion("CREATE_NAME in", values, "createName");return this;
        }

        public GeneratedCriteria andCreateNameNotIn(List<String> values) {
            addCriterion("CREATE_NAME not in", values, "createName");return this;
        }

        public GeneratedCriteria andCreateNameBetween(String value1, String value2) {
            addCriterion("CREATE_NAME between", value1, value2, "createName");return this;
        }

        public GeneratedCriteria andCreateNameNotBetween(String value1, String value2) {
            addCriterion("CREATE_NAME not between", value1, value2, "createName");return this;
        }

        public GeneratedCriteria andRemarksIsNull() {
            addCriterion("REMARKS is null");return this;
        }

        public GeneratedCriteria andRemarksIsNotNull() {
            addCriterion("REMARKS is not null");return this;
        }

        public GeneratedCriteria andRemarksEqualTo(String value) {
            addCriterion("REMARKS =", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksNotEqualTo(String value) {
            addCriterion("REMARKS <>", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksGreaterThan(String value) {
            addCriterion("REMARKS >", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("REMARKS >=", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksLessThan(String value) {
            addCriterion("REMARKS <", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksLessThanOrEqualTo(String value) {
            addCriterion("REMARKS <=", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksLike(String value) {
            addCriterion("REMARKS like", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksNotLike(String value) {
            addCriterion("REMARKS not like", value, "remarks");return this;
        }

        public GeneratedCriteria andRemarksIn(List<String> values) {
            addCriterion("REMARKS in", values, "remarks");return this;
        }

        public GeneratedCriteria andRemarksNotIn(List<String> values) {
            addCriterion("REMARKS not in", values, "remarks");return this;
        }

        public GeneratedCriteria andRemarksBetween(String value1, String value2) {
            addCriterion("REMARKS between", value1, value2, "remarks");return this;
        }

        public GeneratedCriteria andRemarksNotBetween(String value1, String value2) {
            addCriterion("REMARKS not between", value1, value2, "remarks");return this;
        }

        public GeneratedCriteria andUpdateTimeIsNull() {
            addCriterion("UPDATE_TIME is null");return this;
        }

        public GeneratedCriteria andUpdateTimeIsNotNull() {
            addCriterion("UPDATE_TIME is not null");return this;
        }

        public GeneratedCriteria andUpdateTimeEqualTo(Date value) {
            addCriterion("UPDATE_TIME =", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("UPDATE_TIME <>", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("UPDATE_TIME >", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME >=", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeLessThan(Date value) {
            addCriterion("UPDATE_TIME <", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("UPDATE_TIME <=", value, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeIn(List<Date> values) {
            addCriterion("UPDATE_TIME in", values, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("UPDATE_TIME not in", values, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME between", value1, value2, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("UPDATE_TIME not between", value1, value2, "updateTime");return this;
        }

        public GeneratedCriteria andUpdateIdIsNull() {
            addCriterion("UPDATE_ID is null");return this;
        }

        public GeneratedCriteria andUpdateIdIsNotNull() {
            addCriterion("UPDATE_ID is not null");return this;
        }

        public GeneratedCriteria andUpdateIdEqualTo(Long value) {
            addCriterion("UPDATE_ID =", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdNotEqualTo(Long value) {
            addCriterion("UPDATE_ID <>", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdGreaterThan(Long value) {
            addCriterion("UPDATE_ID >", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdGreaterThanOrEqualTo(Long value) {
            addCriterion("UPDATE_ID >=", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdLessThan(Long value) {
            addCriterion("UPDATE_ID <", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdLessThanOrEqualTo(Long value) {
            addCriterion("UPDATE_ID <=", value, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdIn(List<Long> values) {
            addCriterion("UPDATE_ID in", values, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdNotIn(List<Long> values) {
            addCriterion("UPDATE_ID not in", values, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdBetween(Long value1, Long value2) {
            addCriterion("UPDATE_ID between", value1, value2, "updateId");return this;
        }

        public GeneratedCriteria andUpdateIdNotBetween(Long value1, Long value2) {
            addCriterion("UPDATE_ID not between", value1, value2, "updateId");return this;
        }

        public GeneratedCriteria andUpdateNameIsNull() {
            addCriterion("UPDATE_NAME is null");return this;
        }

        public GeneratedCriteria andUpdateNameIsNotNull() {
            addCriterion("UPDATE_NAME is not null");return this;
        }

        public GeneratedCriteria andUpdateNameEqualTo(String value) {
            addCriterion("UPDATE_NAME =", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameNotEqualTo(String value) {
            addCriterion("UPDATE_NAME <>", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameGreaterThan(String value) {
            addCriterion("UPDATE_NAME >", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameGreaterThanOrEqualTo(String value) {
            addCriterion("UPDATE_NAME >=", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameLessThan(String value) {
            addCriterion("UPDATE_NAME <", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameLessThanOrEqualTo(String value) {
            addCriterion("UPDATE_NAME <=", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameLike(String value) {
            addCriterion("UPDATE_NAME like", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameNotLike(String value) {
            addCriterion("UPDATE_NAME not like", value, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameIn(List<String> values) {
            addCriterion("UPDATE_NAME in", values, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameNotIn(List<String> values) {
            addCriterion("UPDATE_NAME not in", values, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameBetween(String value1, String value2) {
            addCriterion("UPDATE_NAME between", value1, value2, "updateName");return this;
        }

        public GeneratedCriteria andUpdateNameNotBetween(String value1, String value2) {
            addCriterion("UPDATE_NAME not between", value1, value2, "updateName");return this;
        }
    }
}