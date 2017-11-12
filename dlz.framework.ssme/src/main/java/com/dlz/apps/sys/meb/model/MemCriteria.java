package com.dlz.apps.sys.meb.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.sys.meb.model.MemCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MemCriteria() {
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

        public GeneratedCriteria andLoginIsNull() {
            addCriterion("LOGIN is null");return this;
        }

        public GeneratedCriteria andLoginIsNotNull() {
            addCriterion("LOGIN is not null");return this;
        }

        public GeneratedCriteria andLoginEqualTo(String value) {
            addCriterion("LOGIN =", value, "login");return this;
        }

        public GeneratedCriteria andLoginNotEqualTo(String value) {
            addCriterion("LOGIN <>", value, "login");return this;
        }

        public GeneratedCriteria andLoginGreaterThan(String value) {
            addCriterion("LOGIN >", value, "login");return this;
        }

        public GeneratedCriteria andLoginGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN >=", value, "login");return this;
        }

        public GeneratedCriteria andLoginLessThan(String value) {
            addCriterion("LOGIN <", value, "login");return this;
        }

        public GeneratedCriteria andLoginLessThanOrEqualTo(String value) {
            addCriterion("LOGIN <=", value, "login");return this;
        }

        public GeneratedCriteria andLoginLike(String value) {
            addCriterion("LOGIN like", value, "login");return this;
        }

        public GeneratedCriteria andLoginNotLike(String value) {
            addCriterion("LOGIN not like", value, "login");return this;
        }

        public GeneratedCriteria andLoginIn(List<String> values) {
            addCriterion("LOGIN in", values, "login");return this;
        }

        public GeneratedCriteria andLoginNotIn(List<String> values) {
            addCriterion("LOGIN not in", values, "login");return this;
        }

        public GeneratedCriteria andLoginBetween(String value1, String value2) {
            addCriterion("LOGIN between", value1, value2, "login");return this;
        }

        public GeneratedCriteria andLoginNotBetween(String value1, String value2) {
            addCriterion("LOGIN not between", value1, value2, "login");return this;
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

        public GeneratedCriteria andRegDateIsNull() {
            addCriterion("REG_DATE is null");return this;
        }

        public GeneratedCriteria andRegDateIsNotNull() {
            addCriterion("REG_DATE is not null");return this;
        }

        public GeneratedCriteria andRegDateEqualTo(Date value) {
            addCriterion("REG_DATE =", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateNotEqualTo(Date value) {
            addCriterion("REG_DATE <>", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateGreaterThan(Date value) {
            addCriterion("REG_DATE >", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateGreaterThanOrEqualTo(Date value) {
            addCriterion("REG_DATE >=", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateLessThan(Date value) {
            addCriterion("REG_DATE <", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateLessThanOrEqualTo(Date value) {
            addCriterion("REG_DATE <=", value, "regDate");return this;
        }

        public GeneratedCriteria andRegDateIn(List<Date> values) {
            addCriterion("REG_DATE in", values, "regDate");return this;
        }

        public GeneratedCriteria andRegDateNotIn(List<Date> values) {
            addCriterion("REG_DATE not in", values, "regDate");return this;
        }

        public GeneratedCriteria andRegDateBetween(Date value1, Date value2) {
            addCriterion("REG_DATE between", value1, value2, "regDate");return this;
        }

        public GeneratedCriteria andRegDateNotBetween(Date value1, Date value2) {
            addCriterion("REG_DATE not between", value1, value2, "regDate");return this;
        }

        public GeneratedCriteria andLastLoginIpIsNull() {
            addCriterion("lAST_LOGIN_IP is null");return this;
        }

        public GeneratedCriteria andLastLoginIpIsNotNull() {
            addCriterion("lAST_LOGIN_IP is not null");return this;
        }

        public GeneratedCriteria andLastLoginIpEqualTo(String value) {
            addCriterion("lAST_LOGIN_IP =", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpNotEqualTo(String value) {
            addCriterion("lAST_LOGIN_IP <>", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpGreaterThan(String value) {
            addCriterion("lAST_LOGIN_IP >", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpGreaterThanOrEqualTo(String value) {
            addCriterion("lAST_LOGIN_IP >=", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpLessThan(String value) {
            addCriterion("lAST_LOGIN_IP <", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpLessThanOrEqualTo(String value) {
            addCriterion("lAST_LOGIN_IP <=", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpLike(String value) {
            addCriterion("lAST_LOGIN_IP like", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpNotLike(String value) {
            addCriterion("lAST_LOGIN_IP not like", value, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpIn(List<String> values) {
            addCriterion("lAST_LOGIN_IP in", values, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpNotIn(List<String> values) {
            addCriterion("lAST_LOGIN_IP not in", values, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpBetween(String value1, String value2) {
            addCriterion("lAST_LOGIN_IP between", value1, value2, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginIpNotBetween(String value1, String value2) {
            addCriterion("lAST_LOGIN_IP not between", value1, value2, "lastLoginIp");return this;
        }

        public GeneratedCriteria andLastLoginDateIsNull() {
            addCriterion("LAST_LOGIN_DATE is null");return this;
        }

        public GeneratedCriteria andLastLoginDateIsNotNull() {
            addCriterion("LAST_LOGIN_DATE is not null");return this;
        }

        public GeneratedCriteria andLastLoginDateEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE =", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateNotEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE <>", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateGreaterThan(Date value) {
            addCriterion("LAST_LOGIN_DATE >", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateGreaterThanOrEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE >=", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateLessThan(Date value) {
            addCriterion("LAST_LOGIN_DATE <", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateLessThanOrEqualTo(Date value) {
            addCriterion("LAST_LOGIN_DATE <=", value, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateIn(List<Date> values) {
            addCriterion("LAST_LOGIN_DATE in", values, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateNotIn(List<Date> values) {
            addCriterion("LAST_LOGIN_DATE not in", values, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateBetween(Date value1, Date value2) {
            addCriterion("LAST_LOGIN_DATE between", value1, value2, "lastLoginDate");return this;
        }

        public GeneratedCriteria andLastLoginDateNotBetween(Date value1, Date value2) {
            addCriterion("LAST_LOGIN_DATE not between", value1, value2, "lastLoginDate");return this;
        }

        public GeneratedCriteria andMobileIsNull() {
            addCriterion("MOBILE is null");return this;
        }

        public GeneratedCriteria andMobileIsNotNull() {
            addCriterion("MOBILE is not null");return this;
        }

        public GeneratedCriteria andMobileEqualTo(String value) {
            addCriterion("MOBILE =", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileNotEqualTo(String value) {
            addCriterion("MOBILE <>", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileGreaterThan(String value) {
            addCriterion("MOBILE >", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileGreaterThanOrEqualTo(String value) {
            addCriterion("MOBILE >=", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileLessThan(String value) {
            addCriterion("MOBILE <", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileLessThanOrEqualTo(String value) {
            addCriterion("MOBILE <=", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileLike(String value) {
            addCriterion("MOBILE like", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileNotLike(String value) {
            addCriterion("MOBILE not like", value, "mobile");return this;
        }

        public GeneratedCriteria andMobileIn(List<String> values) {
            addCriterion("MOBILE in", values, "mobile");return this;
        }

        public GeneratedCriteria andMobileNotIn(List<String> values) {
            addCriterion("MOBILE not in", values, "mobile");return this;
        }

        public GeneratedCriteria andMobileBetween(String value1, String value2) {
            addCriterion("MOBILE between", value1, value2, "mobile");return this;
        }

        public GeneratedCriteria andMobileNotBetween(String value1, String value2) {
            addCriterion("MOBILE not between", value1, value2, "mobile");return this;
        }

        public GeneratedCriteria andEmailIsNull() {
            addCriterion("EMAIL is null");return this;
        }

        public GeneratedCriteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");return this;
        }

        public GeneratedCriteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");return this;
        }

        public GeneratedCriteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");return this;
        }

        public GeneratedCriteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");return this;
        }

        public GeneratedCriteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");return this;
        }

        public GeneratedCriteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");return this;
        }

        public GeneratedCriteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");return this;
        }

        public GeneratedCriteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");return this;
        }

        public GeneratedCriteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");return this;
        }

        public GeneratedCriteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");return this;
        }

        public GeneratedCriteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");return this;
        }

        public GeneratedCriteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");return this;
        }

        public GeneratedCriteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");return this;
        }

        public GeneratedCriteria andRefereeIsNull() {
            addCriterion("REFEREE is null");return this;
        }

        public GeneratedCriteria andRefereeIsNotNull() {
            addCriterion("REFEREE is not null");return this;
        }

        public GeneratedCriteria andRefereeEqualTo(String value) {
            addCriterion("REFEREE =", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeNotEqualTo(String value) {
            addCriterion("REFEREE <>", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeGreaterThan(String value) {
            addCriterion("REFEREE >", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeGreaterThanOrEqualTo(String value) {
            addCriterion("REFEREE >=", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeLessThan(String value) {
            addCriterion("REFEREE <", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeLessThanOrEqualTo(String value) {
            addCriterion("REFEREE <=", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeLike(String value) {
            addCriterion("REFEREE like", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeNotLike(String value) {
            addCriterion("REFEREE not like", value, "referee");return this;
        }

        public GeneratedCriteria andRefereeIn(List<String> values) {
            addCriterion("REFEREE in", values, "referee");return this;
        }

        public GeneratedCriteria andRefereeNotIn(List<String> values) {
            addCriterion("REFEREE not in", values, "referee");return this;
        }

        public GeneratedCriteria andRefereeBetween(String value1, String value2) {
            addCriterion("REFEREE between", value1, value2, "referee");return this;
        }

        public GeneratedCriteria andRefereeNotBetween(String value1, String value2) {
            addCriterion("REFEREE not between", value1, value2, "referee");return this;
        }
    }
}