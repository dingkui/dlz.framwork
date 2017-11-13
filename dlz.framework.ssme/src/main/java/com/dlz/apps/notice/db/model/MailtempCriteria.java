package com.dlz.apps.notice.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.notice.db.model.MailtempCriteria.GeneratedCriteria;

public class MailtempCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MailtempCriteria() {
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

        public GeneratedCriteria andMTitIsNull() {
            addCriterion("M_TIT is null");return this;
        }

        public GeneratedCriteria andMTitIsNotNull() {
            addCriterion("M_TIT is not null");return this;
        }

        public GeneratedCriteria andMTitEqualTo(String value) {
            addCriterion("M_TIT =", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitNotEqualTo(String value) {
            addCriterion("M_TIT <>", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitGreaterThan(String value) {
            addCriterion("M_TIT >", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitGreaterThanOrEqualTo(String value) {
            addCriterion("M_TIT >=", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitLessThan(String value) {
            addCriterion("M_TIT <", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitLessThanOrEqualTo(String value) {
            addCriterion("M_TIT <=", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitLike(String value) {
            addCriterion("M_TIT like", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitNotLike(String value) {
            addCriterion("M_TIT not like", value, "mTit");return this;
        }

        public GeneratedCriteria andMTitIn(List<String> values) {
            addCriterion("M_TIT in", values, "mTit");return this;
        }

        public GeneratedCriteria andMTitNotIn(List<String> values) {
            addCriterion("M_TIT not in", values, "mTit");return this;
        }

        public GeneratedCriteria andMTitBetween(String value1, String value2) {
            addCriterion("M_TIT between", value1, value2, "mTit");return this;
        }

        public GeneratedCriteria andMTitNotBetween(String value1, String value2) {
            addCriterion("M_TIT not between", value1, value2, "mTit");return this;
        }

        public GeneratedCriteria andMInfoIsNull() {
            addCriterion("M_INFO is null");return this;
        }

        public GeneratedCriteria andMInfoIsNotNull() {
            addCriterion("M_INFO is not null");return this;
        }

        public GeneratedCriteria andMInfoEqualTo(String value) {
            addCriterion("M_INFO =", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoNotEqualTo(String value) {
            addCriterion("M_INFO <>", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoGreaterThan(String value) {
            addCriterion("M_INFO >", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoGreaterThanOrEqualTo(String value) {
            addCriterion("M_INFO >=", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoLessThan(String value) {
            addCriterion("M_INFO <", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoLessThanOrEqualTo(String value) {
            addCriterion("M_INFO <=", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoLike(String value) {
            addCriterion("M_INFO like", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoNotLike(String value) {
            addCriterion("M_INFO not like", value, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoIn(List<String> values) {
            addCriterion("M_INFO in", values, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoNotIn(List<String> values) {
            addCriterion("M_INFO not in", values, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoBetween(String value1, String value2) {
            addCriterion("M_INFO between", value1, value2, "mInfo");return this;
        }

        public GeneratedCriteria andMInfoNotBetween(String value1, String value2) {
            addCriterion("M_INFO not between", value1, value2, "mInfo");return this;
        }

        public GeneratedCriteria andMSignIsNull() {
            addCriterion("M_SIGN is null");return this;
        }

        public GeneratedCriteria andMSignIsNotNull() {
            addCriterion("M_SIGN is not null");return this;
        }

        public GeneratedCriteria andMSignEqualTo(String value) {
            addCriterion("M_SIGN =", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignNotEqualTo(String value) {
            addCriterion("M_SIGN <>", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignGreaterThan(String value) {
            addCriterion("M_SIGN >", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignGreaterThanOrEqualTo(String value) {
            addCriterion("M_SIGN >=", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignLessThan(String value) {
            addCriterion("M_SIGN <", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignLessThanOrEqualTo(String value) {
            addCriterion("M_SIGN <=", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignLike(String value) {
            addCriterion("M_SIGN like", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignNotLike(String value) {
            addCriterion("M_SIGN not like", value, "mSign");return this;
        }

        public GeneratedCriteria andMSignIn(List<String> values) {
            addCriterion("M_SIGN in", values, "mSign");return this;
        }

        public GeneratedCriteria andMSignNotIn(List<String> values) {
            addCriterion("M_SIGN not in", values, "mSign");return this;
        }

        public GeneratedCriteria andMSignBetween(String value1, String value2) {
            addCriterion("M_SIGN between", value1, value2, "mSign");return this;
        }

        public GeneratedCriteria andMSignNotBetween(String value1, String value2) {
            addCriterion("M_SIGN not between", value1, value2, "mSign");return this;
        }

        public GeneratedCriteria andMOrderIsNull() {
            addCriterion("M_ORDER is null");return this;
        }

        public GeneratedCriteria andMOrderIsNotNull() {
            addCriterion("M_ORDER is not null");return this;
        }

        public GeneratedCriteria andMOrderEqualTo(Long value) {
            addCriterion("M_ORDER =", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderNotEqualTo(Long value) {
            addCriterion("M_ORDER <>", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderGreaterThan(Long value) {
            addCriterion("M_ORDER >", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("M_ORDER >=", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderLessThan(Long value) {
            addCriterion("M_ORDER <", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderLessThanOrEqualTo(Long value) {
            addCriterion("M_ORDER <=", value, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderIn(List<Long> values) {
            addCriterion("M_ORDER in", values, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderNotIn(List<Long> values) {
            addCriterion("M_ORDER not in", values, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderBetween(Long value1, Long value2) {
            addCriterion("M_ORDER between", value1, value2, "mOrder");return this;
        }

        public GeneratedCriteria andMOrderNotBetween(Long value1, Long value2) {
            addCriterion("M_ORDER not between", value1, value2, "mOrder");return this;
        }

        public GeneratedCriteria andMConIsNull() {
            addCriterion("M_CON is null");return this;
        }

        public GeneratedCriteria andMConIsNotNull() {
            addCriterion("M_CON is not null");return this;
        }

        public GeneratedCriteria andMConEqualTo(String value) {
            addCriterion("M_CON =", value, "mCon");return this;
        }

        public GeneratedCriteria andMConNotEqualTo(String value) {
            addCriterion("M_CON <>", value, "mCon");return this;
        }

        public GeneratedCriteria andMConGreaterThan(String value) {
            addCriterion("M_CON >", value, "mCon");return this;
        }

        public GeneratedCriteria andMConGreaterThanOrEqualTo(String value) {
            addCriterion("M_CON >=", value, "mCon");return this;
        }

        public GeneratedCriteria andMConLessThan(String value) {
            addCriterion("M_CON <", value, "mCon");return this;
        }

        public GeneratedCriteria andMConLessThanOrEqualTo(String value) {
            addCriterion("M_CON <=", value, "mCon");return this;
        }

        public GeneratedCriteria andMConLike(String value) {
            addCriterion("M_CON like", value, "mCon");return this;
        }

        public GeneratedCriteria andMConNotLike(String value) {
            addCriterion("M_CON not like", value, "mCon");return this;
        }

        public GeneratedCriteria andMConIn(List<String> values) {
            addCriterion("M_CON in", values, "mCon");return this;
        }

        public GeneratedCriteria andMConNotIn(List<String> values) {
            addCriterion("M_CON not in", values, "mCon");return this;
        }

        public GeneratedCriteria andMConBetween(String value1, String value2) {
            addCriterion("M_CON between", value1, value2, "mCon");return this;
        }

        public GeneratedCriteria andMConNotBetween(String value1, String value2) {
            addCriterion("M_CON not between", value1, value2, "mCon");return this;
        }
    }
}