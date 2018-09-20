package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.LoginDeviceCriteria.GeneratedCriteria;
public class LoginDeviceCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public LoginDeviceCriteria() {
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

        public GeneratedCriteria andUserIdIsNull() {
            addCriterion("USER_ID is null");return this;
        }

        public GeneratedCriteria andUserIdIsNotNull() {
            addCriterion("USER_ID is not null");return this;
        }

        public GeneratedCriteria andUserIdEqualTo(Long value) {
            addCriterion("USER_ID =", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotEqualTo(Long value) {
            addCriterion("USER_ID <>", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdGreaterThan(Long value) {
            addCriterion("USER_ID >", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdGreaterThanOrEqualTo(Long value) {
            addCriterion("USER_ID >=", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdLessThan(Long value) {
            addCriterion("USER_ID <", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdLessThanOrEqualTo(Long value) {
            addCriterion("USER_ID <=", value, "userId");return this;
        }

        public GeneratedCriteria andUserIdIn(List<Long> values) {
            addCriterion("USER_ID in", values, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotIn(List<Long> values) {
            addCriterion("USER_ID not in", values, "userId");return this;
        }

        public GeneratedCriteria andUserIdBetween(Long value1, Long value2) {
            addCriterion("USER_ID between", value1, value2, "userId");return this;
        }

        public GeneratedCriteria andUserIdNotBetween(Long value1, Long value2) {
            addCriterion("USER_ID not between", value1, value2, "userId");return this;
        }

        public GeneratedCriteria andPidIsNull() {
            addCriterion("PID is null");return this;
        }

        public GeneratedCriteria andPidIsNotNull() {
            addCriterion("PID is not null");return this;
        }

        public GeneratedCriteria andPidEqualTo(String value) {
            addCriterion("PID =", value, "pid");return this;
        }

        public GeneratedCriteria andPidNotEqualTo(String value) {
            addCriterion("PID <>", value, "pid");return this;
        }

        public GeneratedCriteria andPidGreaterThan(String value) {
            addCriterion("PID >", value, "pid");return this;
        }

        public GeneratedCriteria andPidGreaterThanOrEqualTo(String value) {
            addCriterion("PID >=", value, "pid");return this;
        }

        public GeneratedCriteria andPidLessThan(String value) {
            addCriterion("PID <", value, "pid");return this;
        }

        public GeneratedCriteria andPidLessThanOrEqualTo(String value) {
            addCriterion("PID <=", value, "pid");return this;
        }

        public GeneratedCriteria andPidLike(String value) {
            addCriterion("PID like", value, "pid");return this;
        }

        public GeneratedCriteria andPidNotLike(String value) {
            addCriterion("PID not like", value, "pid");return this;
        }

        public GeneratedCriteria andPidIn(List<String> values) {
            addCriterion("PID in", values, "pid");return this;
        }

        public GeneratedCriteria andPidNotIn(List<String> values) {
            addCriterion("PID not in", values, "pid");return this;
        }

        public GeneratedCriteria andPidBetween(String value1, String value2) {
            addCriterion("PID between", value1, value2, "pid");return this;
        }

        public GeneratedCriteria andPidNotBetween(String value1, String value2) {
            addCriterion("PID not between", value1, value2, "pid");return this;
        }

        public GeneratedCriteria andRegTimeIsNull() {
            addCriterion("REG_TIME is null");return this;
        }

        public GeneratedCriteria andRegTimeIsNotNull() {
            addCriterion("REG_TIME is not null");return this;
        }

        public GeneratedCriteria andRegTimeEqualTo(Date value) {
            addCriterion("REG_TIME =", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeNotEqualTo(Date value) {
            addCriterion("REG_TIME <>", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeGreaterThan(Date value) {
            addCriterion("REG_TIME >", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("REG_TIME >=", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeLessThan(Date value) {
            addCriterion("REG_TIME <", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeLessThanOrEqualTo(Date value) {
            addCriterion("REG_TIME <=", value, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeIn(List<Date> values) {
            addCriterion("REG_TIME in", values, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeNotIn(List<Date> values) {
            addCriterion("REG_TIME not in", values, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeBetween(Date value1, Date value2) {
            addCriterion("REG_TIME between", value1, value2, "regTime");return this;
        }

        public GeneratedCriteria andRegTimeNotBetween(Date value1, Date value2) {
            addCriterion("REG_TIME not between", value1, value2, "regTime");return this;
        }

        public GeneratedCriteria andOrdernoIsNull() {
            addCriterion("ORDERNO is null");return this;
        }

        public GeneratedCriteria andOrdernoIsNotNull() {
            addCriterion("ORDERNO is not null");return this;
        }

        public GeneratedCriteria andOrdernoEqualTo(Long value) {
            addCriterion("ORDERNO =", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoNotEqualTo(Long value) {
            addCriterion("ORDERNO <>", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoGreaterThan(Long value) {
            addCriterion("ORDERNO >", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoGreaterThanOrEqualTo(Long value) {
            addCriterion("ORDERNO >=", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoLessThan(Long value) {
            addCriterion("ORDERNO <", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoLessThanOrEqualTo(Long value) {
            addCriterion("ORDERNO <=", value, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoIn(List<Long> values) {
            addCriterion("ORDERNO in", values, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoNotIn(List<Long> values) {
            addCriterion("ORDERNO not in", values, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoBetween(Long value1, Long value2) {
            addCriterion("ORDERNO between", value1, value2, "orderno");return this;
        }

        public GeneratedCriteria andOrdernoNotBetween(Long value1, Long value2) {
            addCriterion("ORDERNO not between", value1, value2, "orderno");return this;
        }
    }
}