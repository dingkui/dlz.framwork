package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.DeptUserCriteria.GeneratedCriteria;

public class DeptUserCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public DeptUserCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andDuIdIsNull() {
            addCriterion("DU_ID is null");return this;
        }

        public GeneratedCriteria andDuIdIsNotNull() {
            addCriterion("DU_ID is not null");return this;
        }

        public GeneratedCriteria andDuIdEqualTo(Long value) {
            addCriterion("DU_ID =", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdNotEqualTo(Long value) {
            addCriterion("DU_ID <>", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdGreaterThan(Long value) {
            addCriterion("DU_ID >", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DU_ID >=", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdLessThan(Long value) {
            addCriterion("DU_ID <", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdLessThanOrEqualTo(Long value) {
            addCriterion("DU_ID <=", value, "duId");return this;
        }

        public GeneratedCriteria andDuIdIn(List<Long> values) {
            addCriterion("DU_ID in", values, "duId");return this;
        }

        public GeneratedCriteria andDuIdNotIn(List<Long> values) {
            addCriterion("DU_ID not in", values, "duId");return this;
        }

        public GeneratedCriteria andDuIdBetween(Long value1, Long value2) {
            addCriterion("DU_ID between", value1, value2, "duId");return this;
        }

        public GeneratedCriteria andDuIdNotBetween(Long value1, Long value2) {
            addCriterion("DU_ID not between", value1, value2, "duId");return this;
        }

        public GeneratedCriteria andDuDIdIsNull() {
            addCriterion("DU_D_ID is null");return this;
        }

        public GeneratedCriteria andDuDIdIsNotNull() {
            addCriterion("DU_D_ID is not null");return this;
        }

        public GeneratedCriteria andDuDIdEqualTo(Long value) {
            addCriterion("DU_D_ID =", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdNotEqualTo(Long value) {
            addCriterion("DU_D_ID <>", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdGreaterThan(Long value) {
            addCriterion("DU_D_ID >", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DU_D_ID >=", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdLessThan(Long value) {
            addCriterion("DU_D_ID <", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdLessThanOrEqualTo(Long value) {
            addCriterion("DU_D_ID <=", value, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdIn(List<Long> values) {
            addCriterion("DU_D_ID in", values, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdNotIn(List<Long> values) {
            addCriterion("DU_D_ID not in", values, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdBetween(Long value1, Long value2) {
            addCriterion("DU_D_ID between", value1, value2, "duDId");return this;
        }

        public GeneratedCriteria andDuDIdNotBetween(Long value1, Long value2) {
            addCriterion("DU_D_ID not between", value1, value2, "duDId");return this;
        }

        public GeneratedCriteria andDuUIdIsNull() {
            addCriterion("DU_U_ID is null");return this;
        }

        public GeneratedCriteria andDuUIdIsNotNull() {
            addCriterion("DU_U_ID is not null");return this;
        }

        public GeneratedCriteria andDuUIdEqualTo(Long value) {
            addCriterion("DU_U_ID =", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdNotEqualTo(Long value) {
            addCriterion("DU_U_ID <>", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdGreaterThan(Long value) {
            addCriterion("DU_U_ID >", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DU_U_ID >=", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdLessThan(Long value) {
            addCriterion("DU_U_ID <", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdLessThanOrEqualTo(Long value) {
            addCriterion("DU_U_ID <=", value, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdIn(List<Long> values) {
            addCriterion("DU_U_ID in", values, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdNotIn(List<Long> values) {
            addCriterion("DU_U_ID not in", values, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdBetween(Long value1, Long value2) {
            addCriterion("DU_U_ID between", value1, value2, "duUId");return this;
        }

        public GeneratedCriteria andDuUIdNotBetween(Long value1, Long value2) {
            addCriterion("DU_U_ID not between", value1, value2, "duUId");return this;
        }

        public GeneratedCriteria andDuDutyDesIsNull() {
            addCriterion("DU_DUTY_DES is null");return this;
        }

        public GeneratedCriteria andDuDutyDesIsNotNull() {
            addCriterion("DU_DUTY_DES is not null");return this;
        }

        public GeneratedCriteria andDuDutyDesEqualTo(String value) {
            addCriterion("DU_DUTY_DES =", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesNotEqualTo(String value) {
            addCriterion("DU_DUTY_DES <>", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesGreaterThan(String value) {
            addCriterion("DU_DUTY_DES >", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesGreaterThanOrEqualTo(String value) {
            addCriterion("DU_DUTY_DES >=", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesLessThan(String value) {
            addCriterion("DU_DUTY_DES <", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesLessThanOrEqualTo(String value) {
            addCriterion("DU_DUTY_DES <=", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesLike(String value) {
            addCriterion("DU_DUTY_DES like", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesNotLike(String value) {
            addCriterion("DU_DUTY_DES not like", value, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesIn(List<String> values) {
            addCriterion("DU_DUTY_DES in", values, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesNotIn(List<String> values) {
            addCriterion("DU_DUTY_DES not in", values, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesBetween(String value1, String value2) {
            addCriterion("DU_DUTY_DES between", value1, value2, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuDutyDesNotBetween(String value1, String value2) {
            addCriterion("DU_DUTY_DES not between", value1, value2, "duDutyDes");return this;
        }

        public GeneratedCriteria andDuAddTimeIsNull() {
            addCriterion("DU_ADD_TIME is null");return this;
        }

        public GeneratedCriteria andDuAddTimeIsNotNull() {
            addCriterion("DU_ADD_TIME is not null");return this;
        }

        public GeneratedCriteria andDuAddTimeEqualTo(Date value) {
            addCriterion("DU_ADD_TIME =", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeNotEqualTo(Date value) {
            addCriterion("DU_ADD_TIME <>", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeGreaterThan(Date value) {
            addCriterion("DU_ADD_TIME >", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("DU_ADD_TIME >=", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeLessThan(Date value) {
            addCriterion("DU_ADD_TIME <", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeLessThanOrEqualTo(Date value) {
            addCriterion("DU_ADD_TIME <=", value, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeIn(List<Date> values) {
            addCriterion("DU_ADD_TIME in", values, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeNotIn(List<Date> values) {
            addCriterion("DU_ADD_TIME not in", values, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeBetween(Date value1, Date value2) {
            addCriterion("DU_ADD_TIME between", value1, value2, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddTimeNotBetween(Date value1, Date value2) {
            addCriterion("DU_ADD_TIME not between", value1, value2, "duAddTime");return this;
        }

        public GeneratedCriteria andDuAddUserIdIsNull() {
            addCriterion("DU_ADD_USER_ID is null");return this;
        }

        public GeneratedCriteria andDuAddUserIdIsNotNull() {
            addCriterion("DU_ADD_USER_ID is not null");return this;
        }

        public GeneratedCriteria andDuAddUserIdEqualTo(Long value) {
            addCriterion("DU_ADD_USER_ID =", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdNotEqualTo(Long value) {
            addCriterion("DU_ADD_USER_ID <>", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdGreaterThan(Long value) {
            addCriterion("DU_ADD_USER_ID >", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DU_ADD_USER_ID >=", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdLessThan(Long value) {
            addCriterion("DU_ADD_USER_ID <", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdLessThanOrEqualTo(Long value) {
            addCriterion("DU_ADD_USER_ID <=", value, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdIn(List<Long> values) {
            addCriterion("DU_ADD_USER_ID in", values, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdNotIn(List<Long> values) {
            addCriterion("DU_ADD_USER_ID not in", values, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdBetween(Long value1, Long value2) {
            addCriterion("DU_ADD_USER_ID between", value1, value2, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuAddUserIdNotBetween(Long value1, Long value2) {
            addCriterion("DU_ADD_USER_ID not between", value1, value2, "duAddUserId");return this;
        }

        public GeneratedCriteria andDuDutyIsNull() {
            addCriterion("DU_DUTY is null");return this;
        }

        public GeneratedCriteria andDuDutyIsNotNull() {
            addCriterion("DU_DUTY is not null");return this;
        }

        public GeneratedCriteria andDuDutyEqualTo(String value) {
            addCriterion("DU_DUTY =", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyNotEqualTo(String value) {
            addCriterion("DU_DUTY <>", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyGreaterThan(String value) {
            addCriterion("DU_DUTY >", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyGreaterThanOrEqualTo(String value) {
            addCriterion("DU_DUTY >=", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyLessThan(String value) {
            addCriterion("DU_DUTY <", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyLessThanOrEqualTo(String value) {
            addCriterion("DU_DUTY <=", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyLike(String value) {
            addCriterion("DU_DUTY like", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyNotLike(String value) {
            addCriterion("DU_DUTY not like", value, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyIn(List<String> values) {
            addCriterion("DU_DUTY in", values, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyNotIn(List<String> values) {
            addCriterion("DU_DUTY not in", values, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyBetween(String value1, String value2) {
            addCriterion("DU_DUTY between", value1, value2, "duDuty");return this;
        }

        public GeneratedCriteria andDuDutyNotBetween(String value1, String value2) {
            addCriterion("DU_DUTY not between", value1, value2, "duDuty");return this;
        }
    }
}