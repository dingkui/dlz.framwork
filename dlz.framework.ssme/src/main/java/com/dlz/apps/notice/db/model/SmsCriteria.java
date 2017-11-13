package com.dlz.apps.notice.db.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.apps.notice.db.model.SmsCriteria.GeneratedCriteria;
import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;

public class SmsCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public SmsCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andSIdIsNull() {
            addCriterion("S_ID is null");return this;
        }

        public GeneratedCriteria andSIdIsNotNull() {
            addCriterion("S_ID is not null");return this;
        }

        public GeneratedCriteria andSIdEqualTo(Long value) {
            addCriterion("S_ID =", value, "sId");return this;
        }

        public GeneratedCriteria andSIdNotEqualTo(Long value) {
            addCriterion("S_ID <>", value, "sId");return this;
        }

        public GeneratedCriteria andSIdGreaterThan(Long value) {
            addCriterion("S_ID >", value, "sId");return this;
        }

        public GeneratedCriteria andSIdGreaterThanOrEqualTo(Long value) {
            addCriterion("S_ID >=", value, "sId");return this;
        }

        public GeneratedCriteria andSIdLessThan(Long value) {
            addCriterion("S_ID <", value, "sId");return this;
        }

        public GeneratedCriteria andSIdLessThanOrEqualTo(Long value) {
            addCriterion("S_ID <=", value, "sId");return this;
        }

        public GeneratedCriteria andSIdIn(List<Long> values) {
            addCriterion("S_ID in", values, "sId");return this;
        }

        public GeneratedCriteria andSIdNotIn(List<Long> values) {
            addCriterion("S_ID not in", values, "sId");return this;
        }

        public GeneratedCriteria andSIdBetween(Long value1, Long value2) {
            addCriterion("S_ID between", value1, value2, "sId");return this;
        }

        public GeneratedCriteria andSIdNotBetween(Long value1, Long value2) {
            addCriterion("S_ID not between", value1, value2, "sId");return this;
        }

        public GeneratedCriteria andSMemberloginIsNull() {
            addCriterion("S_MEMBERLOGIN is null");return this;
        }

        public GeneratedCriteria andSMemberloginIsNotNull() {
            addCriterion("S_MEMBERLOGIN is not null");return this;
        }

        public GeneratedCriteria andSMemberloginEqualTo(String value) {
            addCriterion("S_MEMBERLOGIN =", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginNotEqualTo(String value) {
            addCriterion("S_MEMBERLOGIN <>", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginGreaterThan(String value) {
            addCriterion("S_MEMBERLOGIN >", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginGreaterThanOrEqualTo(String value) {
            addCriterion("S_MEMBERLOGIN >=", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginLessThan(String value) {
            addCriterion("S_MEMBERLOGIN <", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginLessThanOrEqualTo(String value) {
            addCriterion("S_MEMBERLOGIN <=", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginLike(String value) {
            addCriterion("S_MEMBERLOGIN like", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginNotLike(String value) {
            addCriterion("S_MEMBERLOGIN not like", value, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginIn(List<String> values) {
            addCriterion("S_MEMBERLOGIN in", values, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginNotIn(List<String> values) {
            addCriterion("S_MEMBERLOGIN not in", values, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginBetween(String value1, String value2) {
            addCriterion("S_MEMBERLOGIN between", value1, value2, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSMemberloginNotBetween(String value1, String value2) {
            addCriterion("S_MEMBERLOGIN not between", value1, value2, "sMemberlogin");return this;
        }

        public GeneratedCriteria andSTomobileIsNull() {
            addCriterion("S_TOMOBILE is null");return this;
        }

        public GeneratedCriteria andSTomobileIsNotNull() {
            addCriterion("S_TOMOBILE is not null");return this;
        }

        public GeneratedCriteria andSTomobileEqualTo(String value) {
            addCriterion("S_TOMOBILE =", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileNotEqualTo(String value) {
            addCriterion("S_TOMOBILE <>", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileGreaterThan(String value) {
            addCriterion("S_TOMOBILE >", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileGreaterThanOrEqualTo(String value) {
            addCriterion("S_TOMOBILE >=", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileLessThan(String value) {
            addCriterion("S_TOMOBILE <", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileLessThanOrEqualTo(String value) {
            addCriterion("S_TOMOBILE <=", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileLike(String value) {
            addCriterion("S_TOMOBILE like", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileNotLike(String value) {
            addCriterion("S_TOMOBILE not like", value, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileIn(List<String> values) {
            addCriterion("S_TOMOBILE in", values, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileNotIn(List<String> values) {
            addCriterion("S_TOMOBILE not in", values, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileBetween(String value1, String value2) {
            addCriterion("S_TOMOBILE between", value1, value2, "sTomobile");return this;
        }

        public GeneratedCriteria andSTomobileNotBetween(String value1, String value2) {
            addCriterion("S_TOMOBILE not between", value1, value2, "sTomobile");return this;
        }

        public GeneratedCriteria andSIssuccessIsNull() {
            addCriterion("S_ISSUCCESS is null");return this;
        }

        public GeneratedCriteria andSIssuccessIsNotNull() {
            addCriterion("S_ISSUCCESS is not null");return this;
        }

        public GeneratedCriteria andSIssuccessEqualTo(Long value) {
            addCriterion("S_ISSUCCESS =", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessNotEqualTo(Long value) {
            addCriterion("S_ISSUCCESS <>", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessGreaterThan(Long value) {
            addCriterion("S_ISSUCCESS >", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessGreaterThanOrEqualTo(Long value) {
            addCriterion("S_ISSUCCESS >=", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessLessThan(Long value) {
            addCriterion("S_ISSUCCESS <", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessLessThanOrEqualTo(Long value) {
            addCriterion("S_ISSUCCESS <=", value, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessIn(List<Long> values) {
            addCriterion("S_ISSUCCESS in", values, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessNotIn(List<Long> values) {
            addCriterion("S_ISSUCCESS not in", values, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessBetween(Long value1, Long value2) {
            addCriterion("S_ISSUCCESS between", value1, value2, "sIssuccess");return this;
        }

        public GeneratedCriteria andSIssuccessNotBetween(Long value1, Long value2) {
            addCriterion("S_ISSUCCESS not between", value1, value2, "sIssuccess");return this;
        }

        public GeneratedCriteria andSSendtimeIsNull() {
            addCriterion("S_SENDTIME is null");return this;
        }

        public GeneratedCriteria andSSendtimeIsNotNull() {
            addCriterion("S_SENDTIME is not null");return this;
        }

        public GeneratedCriteria andSSendtimeEqualTo(Date value) {
            addCriterion("S_SENDTIME =", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeNotEqualTo(Date value) {
            addCriterion("S_SENDTIME <>", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeGreaterThan(Date value) {
            addCriterion("S_SENDTIME >", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("S_SENDTIME >=", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeLessThan(Date value) {
            addCriterion("S_SENDTIME <", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeLessThanOrEqualTo(Date value) {
            addCriterion("S_SENDTIME <=", value, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeIn(List<Date> values) {
            addCriterion("S_SENDTIME in", values, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeNotIn(List<Date> values) {
            addCriterion("S_SENDTIME not in", values, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeBetween(Date value1, Date value2) {
            addCriterion("S_SENDTIME between", value1, value2, "sSendtime");return this;
        }

        public GeneratedCriteria andSSendtimeNotBetween(Date value1, Date value2) {
            addCriterion("S_SENDTIME not between", value1, value2, "sSendtime");return this;
        }

        public GeneratedCriteria andSContentIsNull() {
            addCriterion("S_CONTENT is null");return this;
        }

        public GeneratedCriteria andSContentIsNotNull() {
            addCriterion("S_CONTENT is not null");return this;
        }

        public GeneratedCriteria andSContentEqualTo(String value) {
            addCriterion("S_CONTENT =", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentNotEqualTo(String value) {
            addCriterion("S_CONTENT <>", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentGreaterThan(String value) {
            addCriterion("S_CONTENT >", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentGreaterThanOrEqualTo(String value) {
            addCriterion("S_CONTENT >=", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentLessThan(String value) {
            addCriterion("S_CONTENT <", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentLessThanOrEqualTo(String value) {
            addCriterion("S_CONTENT <=", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentLike(String value) {
            addCriterion("S_CONTENT like", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentNotLike(String value) {
            addCriterion("S_CONTENT not like", value, "sContent");return this;
        }

        public GeneratedCriteria andSContentIn(List<String> values) {
            addCriterion("S_CONTENT in", values, "sContent");return this;
        }

        public GeneratedCriteria andSContentNotIn(List<String> values) {
            addCriterion("S_CONTENT not in", values, "sContent");return this;
        }

        public GeneratedCriteria andSContentBetween(String value1, String value2) {
            addCriterion("S_CONTENT between", value1, value2, "sContent");return this;
        }

        public GeneratedCriteria andSContentNotBetween(String value1, String value2) {
            addCriterion("S_CONTENT not between", value1, value2, "sContent");return this;
        }

        public GeneratedCriteria andSTomemberloginIsNull() {
            addCriterion("S_TOMEMBERLOGIN is null");return this;
        }

        public GeneratedCriteria andSTomemberloginIsNotNull() {
            addCriterion("S_TOMEMBERLOGIN is not null");return this;
        }

        public GeneratedCriteria andSTomemberloginEqualTo(String value) {
            addCriterion("S_TOMEMBERLOGIN =", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginNotEqualTo(String value) {
            addCriterion("S_TOMEMBERLOGIN <>", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginGreaterThan(String value) {
            addCriterion("S_TOMEMBERLOGIN >", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginGreaterThanOrEqualTo(String value) {
            addCriterion("S_TOMEMBERLOGIN >=", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginLessThan(String value) {
            addCriterion("S_TOMEMBERLOGIN <", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginLessThanOrEqualTo(String value) {
            addCriterion("S_TOMEMBERLOGIN <=", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginLike(String value) {
            addCriterion("S_TOMEMBERLOGIN like", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginNotLike(String value) {
            addCriterion("S_TOMEMBERLOGIN not like", value, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginIn(List<String> values) {
            addCriterion("S_TOMEMBERLOGIN in", values, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginNotIn(List<String> values) {
            addCriterion("S_TOMEMBERLOGIN not in", values, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginBetween(String value1, String value2) {
            addCriterion("S_TOMEMBERLOGIN between", value1, value2, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSTomemberloginNotBetween(String value1, String value2) {
            addCriterion("S_TOMEMBERLOGIN not between", value1, value2, "sTomemberlogin");return this;
        }

        public GeneratedCriteria andSMemberNameIsNull() {
            addCriterion("S_MEMBER_NAME is null");return this;
        }

        public GeneratedCriteria andSMemberNameIsNotNull() {
            addCriterion("S_MEMBER_NAME is not null");return this;
        }

        public GeneratedCriteria andSMemberNameEqualTo(String value) {
            addCriterion("S_MEMBER_NAME =", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameNotEqualTo(String value) {
            addCriterion("S_MEMBER_NAME <>", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameGreaterThan(String value) {
            addCriterion("S_MEMBER_NAME >", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_MEMBER_NAME >=", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameLessThan(String value) {
            addCriterion("S_MEMBER_NAME <", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameLessThanOrEqualTo(String value) {
            addCriterion("S_MEMBER_NAME <=", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameLike(String value) {
            addCriterion("S_MEMBER_NAME like", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameNotLike(String value) {
            addCriterion("S_MEMBER_NAME not like", value, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameIn(List<String> values) {
            addCriterion("S_MEMBER_NAME in", values, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameNotIn(List<String> values) {
            addCriterion("S_MEMBER_NAME not in", values, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameBetween(String value1, String value2) {
            addCriterion("S_MEMBER_NAME between", value1, value2, "sMemberName");return this;
        }

        public GeneratedCriteria andSMemberNameNotBetween(String value1, String value2) {
            addCriterion("S_MEMBER_NAME not between", value1, value2, "sMemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameIsNull() {
            addCriterion("S_TOMEMBER_NAME is null");return this;
        }

        public GeneratedCriteria andSTomemberNameIsNotNull() {
            addCriterion("S_TOMEMBER_NAME is not null");return this;
        }

        public GeneratedCriteria andSTomemberNameEqualTo(String value) {
            addCriterion("S_TOMEMBER_NAME =", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameNotEqualTo(String value) {
            addCriterion("S_TOMEMBER_NAME <>", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameGreaterThan(String value) {
            addCriterion("S_TOMEMBER_NAME >", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameGreaterThanOrEqualTo(String value) {
            addCriterion("S_TOMEMBER_NAME >=", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameLessThan(String value) {
            addCriterion("S_TOMEMBER_NAME <", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameLessThanOrEqualTo(String value) {
            addCriterion("S_TOMEMBER_NAME <=", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameLike(String value) {
            addCriterion("S_TOMEMBER_NAME like", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameNotLike(String value) {
            addCriterion("S_TOMEMBER_NAME not like", value, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameIn(List<String> values) {
            addCriterion("S_TOMEMBER_NAME in", values, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameNotIn(List<String> values) {
            addCriterion("S_TOMEMBER_NAME not in", values, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameBetween(String value1, String value2) {
            addCriterion("S_TOMEMBER_NAME between", value1, value2, "sTomemberName");return this;
        }

        public GeneratedCriteria andSTomemberNameNotBetween(String value1, String value2) {
            addCriterion("S_TOMEMBER_NAME not between", value1, value2, "sTomemberName");return this;
        }
    }
}