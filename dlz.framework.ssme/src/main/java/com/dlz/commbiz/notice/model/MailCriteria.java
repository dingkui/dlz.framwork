package com.dlz.commbiz.notice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.common.base.criteria.BaseCriteria;
import com.dlz.common.base.criteria.BaseGeneratedCriteria;
import com.dlz.commbiz.notice.model.MailCriteria.GeneratedCriteria;

public class MailCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MailCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andMIdIsNull() {
            addCriterion("M_ID is null");return this;
        }

        public GeneratedCriteria andMIdIsNotNull() {
            addCriterion("M_ID is not null");return this;
        }

        public GeneratedCriteria andMIdEqualTo(Long value) {
            addCriterion("M_ID =", value, "mId");return this;
        }

        public GeneratedCriteria andMIdNotEqualTo(Long value) {
            addCriterion("M_ID <>", value, "mId");return this;
        }

        public GeneratedCriteria andMIdGreaterThan(Long value) {
            addCriterion("M_ID >", value, "mId");return this;
        }

        public GeneratedCriteria andMIdGreaterThanOrEqualTo(Long value) {
            addCriterion("M_ID >=", value, "mId");return this;
        }

        public GeneratedCriteria andMIdLessThan(Long value) {
            addCriterion("M_ID <", value, "mId");return this;
        }

        public GeneratedCriteria andMIdLessThanOrEqualTo(Long value) {
            addCriterion("M_ID <=", value, "mId");return this;
        }

        public GeneratedCriteria andMIdIn(List<Long> values) {
            addCriterion("M_ID in", values, "mId");return this;
        }

        public GeneratedCriteria andMIdNotIn(List<Long> values) {
            addCriterion("M_ID not in", values, "mId");return this;
        }

        public GeneratedCriteria andMIdBetween(Long value1, Long value2) {
            addCriterion("M_ID between", value1, value2, "mId");return this;
        }

        public GeneratedCriteria andMIdNotBetween(Long value1, Long value2) {
            addCriterion("M_ID not between", value1, value2, "mId");return this;
        }

        public GeneratedCriteria andMTitleIsNull() {
            addCriterion("M_TITLE is null");return this;
        }

        public GeneratedCriteria andMTitleIsNotNull() {
            addCriterion("M_TITLE is not null");return this;
        }

        public GeneratedCriteria andMTitleEqualTo(String value) {
            addCriterion("M_TITLE =", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleNotEqualTo(String value) {
            addCriterion("M_TITLE <>", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleGreaterThan(String value) {
            addCriterion("M_TITLE >", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleGreaterThanOrEqualTo(String value) {
            addCriterion("M_TITLE >=", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleLessThan(String value) {
            addCriterion("M_TITLE <", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleLessThanOrEqualTo(String value) {
            addCriterion("M_TITLE <=", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleLike(String value) {
            addCriterion("M_TITLE like", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleNotLike(String value) {
            addCriterion("M_TITLE not like", value, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleIn(List<String> values) {
            addCriterion("M_TITLE in", values, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleNotIn(List<String> values) {
            addCriterion("M_TITLE not in", values, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleBetween(String value1, String value2) {
            addCriterion("M_TITLE between", value1, value2, "mTitle");return this;
        }

        public GeneratedCriteria andMTitleNotBetween(String value1, String value2) {
            addCriterion("M_TITLE not between", value1, value2, "mTitle");return this;
        }

        public GeneratedCriteria andMCycleIsNull() {
            addCriterion("M_CYCLE is null");return this;
        }

        public GeneratedCriteria andMCycleIsNotNull() {
            addCriterion("M_CYCLE is not null");return this;
        }

        public GeneratedCriteria andMCycleEqualTo(Long value) {
            addCriterion("M_CYCLE =", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleNotEqualTo(Long value) {
            addCriterion("M_CYCLE <>", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleGreaterThan(Long value) {
            addCriterion("M_CYCLE >", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleGreaterThanOrEqualTo(Long value) {
            addCriterion("M_CYCLE >=", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleLessThan(Long value) {
            addCriterion("M_CYCLE <", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleLessThanOrEqualTo(Long value) {
            addCriterion("M_CYCLE <=", value, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleIn(List<Long> values) {
            addCriterion("M_CYCLE in", values, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleNotIn(List<Long> values) {
            addCriterion("M_CYCLE not in", values, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleBetween(Long value1, Long value2) {
            addCriterion("M_CYCLE between", value1, value2, "mCycle");return this;
        }

        public GeneratedCriteria andMCycleNotBetween(Long value1, Long value2) {
            addCriterion("M_CYCLE not between", value1, value2, "mCycle");return this;
        }

        public GeneratedCriteria andMMemberIsNull() {
            addCriterion("M_MEMBER is null");return this;
        }

        public GeneratedCriteria andMMemberIsNotNull() {
            addCriterion("M_MEMBER is not null");return this;
        }

        public GeneratedCriteria andMMemberEqualTo(String value) {
            addCriterion("M_MEMBER =", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberNotEqualTo(String value) {
            addCriterion("M_MEMBER <>", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberGreaterThan(String value) {
            addCriterion("M_MEMBER >", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberGreaterThanOrEqualTo(String value) {
            addCriterion("M_MEMBER >=", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberLessThan(String value) {
            addCriterion("M_MEMBER <", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberLessThanOrEqualTo(String value) {
            addCriterion("M_MEMBER <=", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberLike(String value) {
            addCriterion("M_MEMBER like", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberNotLike(String value) {
            addCriterion("M_MEMBER not like", value, "mMember");return this;
        }

        public GeneratedCriteria andMMemberIn(List<String> values) {
            addCriterion("M_MEMBER in", values, "mMember");return this;
        }

        public GeneratedCriteria andMMemberNotIn(List<String> values) {
            addCriterion("M_MEMBER not in", values, "mMember");return this;
        }

        public GeneratedCriteria andMMemberBetween(String value1, String value2) {
            addCriterion("M_MEMBER between", value1, value2, "mMember");return this;
        }

        public GeneratedCriteria andMMemberNotBetween(String value1, String value2) {
            addCriterion("M_MEMBER not between", value1, value2, "mMember");return this;
        }

        public GeneratedCriteria andMEmailIsNull() {
            addCriterion("M_EMAIL is null");return this;
        }

        public GeneratedCriteria andMEmailIsNotNull() {
            addCriterion("M_EMAIL is not null");return this;
        }

        public GeneratedCriteria andMEmailEqualTo(String value) {
            addCriterion("M_EMAIL =", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailNotEqualTo(String value) {
            addCriterion("M_EMAIL <>", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailGreaterThan(String value) {
            addCriterion("M_EMAIL >", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailGreaterThanOrEqualTo(String value) {
            addCriterion("M_EMAIL >=", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailLessThan(String value) {
            addCriterion("M_EMAIL <", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailLessThanOrEqualTo(String value) {
            addCriterion("M_EMAIL <=", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailLike(String value) {
            addCriterion("M_EMAIL like", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailNotLike(String value) {
            addCriterion("M_EMAIL not like", value, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailIn(List<String> values) {
            addCriterion("M_EMAIL in", values, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailNotIn(List<String> values) {
            addCriterion("M_EMAIL not in", values, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailBetween(String value1, String value2) {
            addCriterion("M_EMAIL between", value1, value2, "mEmail");return this;
        }

        public GeneratedCriteria andMEmailNotBetween(String value1, String value2) {
            addCriterion("M_EMAIL not between", value1, value2, "mEmail");return this;
        }

        public GeneratedCriteria andMNumberIsNull() {
            addCriterion("M_NUMBER is null");return this;
        }

        public GeneratedCriteria andMNumberIsNotNull() {
            addCriterion("M_NUMBER is not null");return this;
        }

        public GeneratedCriteria andMNumberEqualTo(Long value) {
            addCriterion("M_NUMBER =", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberNotEqualTo(Long value) {
            addCriterion("M_NUMBER <>", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberGreaterThan(Long value) {
            addCriterion("M_NUMBER >", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberGreaterThanOrEqualTo(Long value) {
            addCriterion("M_NUMBER >=", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberLessThan(Long value) {
            addCriterion("M_NUMBER <", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberLessThanOrEqualTo(Long value) {
            addCriterion("M_NUMBER <=", value, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberIn(List<Long> values) {
            addCriterion("M_NUMBER in", values, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberNotIn(List<Long> values) {
            addCriterion("M_NUMBER not in", values, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberBetween(Long value1, Long value2) {
            addCriterion("M_NUMBER between", value1, value2, "mNumber");return this;
        }

        public GeneratedCriteria andMNumberNotBetween(Long value1, Long value2) {
            addCriterion("M_NUMBER not between", value1, value2, "mNumber");return this;
        }

        public GeneratedCriteria andMAdddateIsNull() {
            addCriterion("M_ADDDATE is null");return this;
        }

        public GeneratedCriteria andMAdddateIsNotNull() {
            addCriterion("M_ADDDATE is not null");return this;
        }

        public GeneratedCriteria andMAdddateEqualTo(Date value) {
            addCriterion("M_ADDDATE =", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateNotEqualTo(Date value) {
            addCriterion("M_ADDDATE <>", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateGreaterThan(Date value) {
            addCriterion("M_ADDDATE >", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateGreaterThanOrEqualTo(Date value) {
            addCriterion("M_ADDDATE >=", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateLessThan(Date value) {
            addCriterion("M_ADDDATE <", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateLessThanOrEqualTo(Date value) {
            addCriterion("M_ADDDATE <=", value, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateIn(List<Date> values) {
            addCriterion("M_ADDDATE in", values, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateNotIn(List<Date> values) {
            addCriterion("M_ADDDATE not in", values, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateBetween(Date value1, Date value2) {
            addCriterion("M_ADDDATE between", value1, value2, "mAdddate");return this;
        }

        public GeneratedCriteria andMAdddateNotBetween(Date value1, Date value2) {
            addCriterion("M_ADDDATE not between", value1, value2, "mAdddate");return this;
        }

        public GeneratedCriteria andMUpdateIsNull() {
            addCriterion("M_UPDATE is null");return this;
        }

        public GeneratedCriteria andMUpdateIsNotNull() {
            addCriterion("M_UPDATE is not null");return this;
        }

        public GeneratedCriteria andMUpdateEqualTo(Date value) {
            addCriterion("M_UPDATE =", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateNotEqualTo(Date value) {
            addCriterion("M_UPDATE <>", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateGreaterThan(Date value) {
            addCriterion("M_UPDATE >", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("M_UPDATE >=", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateLessThan(Date value) {
            addCriterion("M_UPDATE <", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateLessThanOrEqualTo(Date value) {
            addCriterion("M_UPDATE <=", value, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateIn(List<Date> values) {
            addCriterion("M_UPDATE in", values, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateNotIn(List<Date> values) {
            addCriterion("M_UPDATE not in", values, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateBetween(Date value1, Date value2) {
            addCriterion("M_UPDATE between", value1, value2, "mUpdate");return this;
        }

        public GeneratedCriteria andMUpdateNotBetween(Date value1, Date value2) {
            addCriterion("M_UPDATE not between", value1, value2, "mUpdate");return this;
        }

        public GeneratedCriteria andMSenddateIsNull() {
            addCriterion("M_SENDDATE is null");return this;
        }

        public GeneratedCriteria andMSenddateIsNotNull() {
            addCriterion("M_SENDDATE is not null");return this;
        }

        public GeneratedCriteria andMSenddateEqualTo(Date value) {
            addCriterion("M_SENDDATE =", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateNotEqualTo(Date value) {
            addCriterion("M_SENDDATE <>", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateGreaterThan(Date value) {
            addCriterion("M_SENDDATE >", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateGreaterThanOrEqualTo(Date value) {
            addCriterion("M_SENDDATE >=", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateLessThan(Date value) {
            addCriterion("M_SENDDATE <", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateLessThanOrEqualTo(Date value) {
            addCriterion("M_SENDDATE <=", value, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateIn(List<Date> values) {
            addCriterion("M_SENDDATE in", values, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateNotIn(List<Date> values) {
            addCriterion("M_SENDDATE not in", values, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateBetween(Date value1, Date value2) {
            addCriterion("M_SENDDATE between", value1, value2, "mSenddate");return this;
        }

        public GeneratedCriteria andMSenddateNotBetween(Date value1, Date value2) {
            addCriterion("M_SENDDATE not between", value1, value2, "mSenddate");return this;
        }

        public GeneratedCriteria andMTypeIsNull() {
            addCriterion("M_TYPE is null");return this;
        }

        public GeneratedCriteria andMTypeIsNotNull() {
            addCriterion("M_TYPE is not null");return this;
        }

        public GeneratedCriteria andMTypeEqualTo(Long value) {
            addCriterion("M_TYPE =", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeNotEqualTo(Long value) {
            addCriterion("M_TYPE <>", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeGreaterThan(Long value) {
            addCriterion("M_TYPE >", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("M_TYPE >=", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeLessThan(Long value) {
            addCriterion("M_TYPE <", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeLessThanOrEqualTo(Long value) {
            addCriterion("M_TYPE <=", value, "mType");return this;
        }

        public GeneratedCriteria andMTypeIn(List<Long> values) {
            addCriterion("M_TYPE in", values, "mType");return this;
        }

        public GeneratedCriteria andMTypeNotIn(List<Long> values) {
            addCriterion("M_TYPE not in", values, "mType");return this;
        }

        public GeneratedCriteria andMTypeBetween(Long value1, Long value2) {
            addCriterion("M_TYPE between", value1, value2, "mType");return this;
        }

        public GeneratedCriteria andMTypeNotBetween(Long value1, Long value2) {
            addCriterion("M_TYPE not between", value1, value2, "mType");return this;
        }

        public GeneratedCriteria andMContentIsNull() {
            addCriterion("M_CONTENT is null");return this;
        }

        public GeneratedCriteria andMContentIsNotNull() {
            addCriterion("M_CONTENT is not null");return this;
        }

        public GeneratedCriteria andMContentEqualTo(String value) {
            addCriterion("M_CONTENT =", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentNotEqualTo(String value) {
            addCriterion("M_CONTENT <>", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentGreaterThan(String value) {
            addCriterion("M_CONTENT >", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentGreaterThanOrEqualTo(String value) {
            addCriterion("M_CONTENT >=", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentLessThan(String value) {
            addCriterion("M_CONTENT <", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentLessThanOrEqualTo(String value) {
            addCriterion("M_CONTENT <=", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentLike(String value) {
            addCriterion("M_CONTENT like", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentNotLike(String value) {
            addCriterion("M_CONTENT not like", value, "mContent");return this;
        }

        public GeneratedCriteria andMContentIn(List<String> values) {
            addCriterion("M_CONTENT in", values, "mContent");return this;
        }

        public GeneratedCriteria andMContentNotIn(List<String> values) {
            addCriterion("M_CONTENT not in", values, "mContent");return this;
        }

        public GeneratedCriteria andMContentBetween(String value1, String value2) {
            addCriterion("M_CONTENT between", value1, value2, "mContent");return this;
        }

        public GeneratedCriteria andMContentNotBetween(String value1, String value2) {
            addCriterion("M_CONTENT not between", value1, value2, "mContent");return this;
        }
    }
}