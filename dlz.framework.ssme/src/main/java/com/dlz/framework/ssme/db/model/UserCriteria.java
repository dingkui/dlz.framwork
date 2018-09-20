package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.UserCriteria.GeneratedCriteria;
public class UserCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public UserCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
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

        public GeneratedCriteria andLoginIdIsNull() {
            addCriterion("LOGIN_ID is null");return this;
        }

        public GeneratedCriteria andLoginIdIsNotNull() {
            addCriterion("LOGIN_ID is not null");return this;
        }

        public GeneratedCriteria andLoginIdEqualTo(String value) {
            addCriterion("LOGIN_ID =", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdNotEqualTo(String value) {
            addCriterion("LOGIN_ID <>", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdGreaterThan(String value) {
            addCriterion("LOGIN_ID >", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_ID >=", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdLessThan(String value) {
            addCriterion("LOGIN_ID <", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_ID <=", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdLike(String value) {
            addCriterion("LOGIN_ID like", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdNotLike(String value) {
            addCriterion("LOGIN_ID not like", value, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdIn(List<String> values) {
            addCriterion("LOGIN_ID in", values, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdNotIn(List<String> values) {
            addCriterion("LOGIN_ID not in", values, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdBetween(String value1, String value2) {
            addCriterion("LOGIN_ID between", value1, value2, "loginId");return this;
        }

        public GeneratedCriteria andLoginIdNotBetween(String value1, String value2) {
            addCriterion("LOGIN_ID not between", value1, value2, "loginId");return this;
        }

        public GeneratedCriteria andPwdIsNull() {
            addCriterion("PWD is null");return this;
        }

        public GeneratedCriteria andPwdIsNotNull() {
            addCriterion("PWD is not null");return this;
        }

        public GeneratedCriteria andPwdEqualTo(String value) {
            addCriterion("PWD =", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdNotEqualTo(String value) {
            addCriterion("PWD <>", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdGreaterThan(String value) {
            addCriterion("PWD >", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdGreaterThanOrEqualTo(String value) {
            addCriterion("PWD >=", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdLessThan(String value) {
            addCriterion("PWD <", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdLessThanOrEqualTo(String value) {
            addCriterion("PWD <=", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdLike(String value) {
            addCriterion("PWD like", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdNotLike(String value) {
            addCriterion("PWD not like", value, "pwd");return this;
        }

        public GeneratedCriteria andPwdIn(List<String> values) {
            addCriterion("PWD in", values, "pwd");return this;
        }

        public GeneratedCriteria andPwdNotIn(List<String> values) {
            addCriterion("PWD not in", values, "pwd");return this;
        }

        public GeneratedCriteria andPwdBetween(String value1, String value2) {
            addCriterion("PWD between", value1, value2, "pwd");return this;
        }

        public GeneratedCriteria andPwdNotBetween(String value1, String value2) {
            addCriterion("PWD not between", value1, value2, "pwd");return this;
        }

        public GeneratedCriteria andSaltIsNull() {
            addCriterion("SALT is null");return this;
        }

        public GeneratedCriteria andSaltIsNotNull() {
            addCriterion("SALT is not null");return this;
        }

        public GeneratedCriteria andSaltEqualTo(String value) {
            addCriterion("SALT =", value, "salt");return this;
        }

        public GeneratedCriteria andSaltNotEqualTo(String value) {
            addCriterion("SALT <>", value, "salt");return this;
        }

        public GeneratedCriteria andSaltGreaterThan(String value) {
            addCriterion("SALT >", value, "salt");return this;
        }

        public GeneratedCriteria andSaltGreaterThanOrEqualTo(String value) {
            addCriterion("SALT >=", value, "salt");return this;
        }

        public GeneratedCriteria andSaltLessThan(String value) {
            addCriterion("SALT <", value, "salt");return this;
        }

        public GeneratedCriteria andSaltLessThanOrEqualTo(String value) {
            addCriterion("SALT <=", value, "salt");return this;
        }

        public GeneratedCriteria andSaltLike(String value) {
            addCriterion("SALT like", value, "salt");return this;
        }

        public GeneratedCriteria andSaltNotLike(String value) {
            addCriterion("SALT not like", value, "salt");return this;
        }

        public GeneratedCriteria andSaltIn(List<String> values) {
            addCriterion("SALT in", values, "salt");return this;
        }

        public GeneratedCriteria andSaltNotIn(List<String> values) {
            addCriterion("SALT not in", values, "salt");return this;
        }

        public GeneratedCriteria andSaltBetween(String value1, String value2) {
            addCriterion("SALT between", value1, value2, "salt");return this;
        }

        public GeneratedCriteria andSaltNotBetween(String value1, String value2) {
            addCriterion("SALT not between", value1, value2, "salt");return this;
        }

        public GeneratedCriteria andUserNameIsNull() {
            addCriterion("USER_NAME is null");return this;
        }

        public GeneratedCriteria andUserNameIsNotNull() {
            addCriterion("USER_NAME is not null");return this;
        }

        public GeneratedCriteria andUserNameEqualTo(String value) {
            addCriterion("USER_NAME =", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameNotEqualTo(String value) {
            addCriterion("USER_NAME <>", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameGreaterThan(String value) {
            addCriterion("USER_NAME >", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameGreaterThanOrEqualTo(String value) {
            addCriterion("USER_NAME >=", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameLessThan(String value) {
            addCriterion("USER_NAME <", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameLessThanOrEqualTo(String value) {
            addCriterion("USER_NAME <=", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameLike(String value) {
            addCriterion("USER_NAME like", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameNotLike(String value) {
            addCriterion("USER_NAME not like", value, "userName");return this;
        }

        public GeneratedCriteria andUserNameIn(List<String> values) {
            addCriterion("USER_NAME in", values, "userName");return this;
        }

        public GeneratedCriteria andUserNameNotIn(List<String> values) {
            addCriterion("USER_NAME not in", values, "userName");return this;
        }

        public GeneratedCriteria andUserNameBetween(String value1, String value2) {
            addCriterion("USER_NAME between", value1, value2, "userName");return this;
        }

        public GeneratedCriteria andUserNameNotBetween(String value1, String value2) {
            addCriterion("USER_NAME not between", value1, value2, "userName");return this;
        }

        public GeneratedCriteria andUserStatusIsNull() {
            addCriterion("USER_STATUS is null");return this;
        }

        public GeneratedCriteria andUserStatusIsNotNull() {
            addCriterion("USER_STATUS is not null");return this;
        }

        public GeneratedCriteria andUserStatusEqualTo(String value) {
            addCriterion("USER_STATUS =", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusNotEqualTo(String value) {
            addCriterion("USER_STATUS <>", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusGreaterThan(String value) {
            addCriterion("USER_STATUS >", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusGreaterThanOrEqualTo(String value) {
            addCriterion("USER_STATUS >=", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusLessThan(String value) {
            addCriterion("USER_STATUS <", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusLessThanOrEqualTo(String value) {
            addCriterion("USER_STATUS <=", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusLike(String value) {
            addCriterion("USER_STATUS like", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusNotLike(String value) {
            addCriterion("USER_STATUS not like", value, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusIn(List<String> values) {
            addCriterion("USER_STATUS in", values, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusNotIn(List<String> values) {
            addCriterion("USER_STATUS not in", values, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusBetween(String value1, String value2) {
            addCriterion("USER_STATUS between", value1, value2, "userStatus");return this;
        }

        public GeneratedCriteria andUserStatusNotBetween(String value1, String value2) {
            addCriterion("USER_STATUS not between", value1, value2, "userStatus");return this;
        }
    }
}