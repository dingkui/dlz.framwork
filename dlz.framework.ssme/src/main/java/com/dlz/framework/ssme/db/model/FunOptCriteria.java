package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.FunOptCriteria.GeneratedCriteria;

public class FunOptCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public FunOptCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andFunOptIdIsNull() {
            addCriterion("FUN_OPT_ID is null");return this;
        }

        public GeneratedCriteria andFunOptIdIsNotNull() {
            addCriterion("FUN_OPT_ID is not null");return this;
        }

        public GeneratedCriteria andFunOptIdEqualTo(Long value) {
            addCriterion("FUN_OPT_ID =", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdNotEqualTo(Long value) {
            addCriterion("FUN_OPT_ID <>", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdGreaterThan(Long value) {
            addCriterion("FUN_OPT_ID >", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("FUN_OPT_ID >=", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdLessThan(Long value) {
            addCriterion("FUN_OPT_ID <", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdLessThanOrEqualTo(Long value) {
            addCriterion("FUN_OPT_ID <=", value, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdIn(List<Long> values) {
            addCriterion("FUN_OPT_ID in", values, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdNotIn(List<Long> values) {
            addCriterion("FUN_OPT_ID not in", values, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdBetween(Long value1, Long value2) {
            addCriterion("FUN_OPT_ID between", value1, value2, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptIdNotBetween(Long value1, Long value2) {
            addCriterion("FUN_OPT_ID not between", value1, value2, "funOptId");return this;
        }

        public GeneratedCriteria andFunOptNmIsNull() {
            addCriterion("FUN_OPT_NM is null");return this;
        }

        public GeneratedCriteria andFunOptNmIsNotNull() {
            addCriterion("FUN_OPT_NM is not null");return this;
        }

        public GeneratedCriteria andFunOptNmEqualTo(String value) {
            addCriterion("FUN_OPT_NM =", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmNotEqualTo(String value) {
            addCriterion("FUN_OPT_NM <>", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmGreaterThan(String value) {
            addCriterion("FUN_OPT_NM >", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmGreaterThanOrEqualTo(String value) {
            addCriterion("FUN_OPT_NM >=", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmLessThan(String value) {
            addCriterion("FUN_OPT_NM <", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmLessThanOrEqualTo(String value) {
            addCriterion("FUN_OPT_NM <=", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmLike(String value) {
            addCriterion("FUN_OPT_NM like", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmNotLike(String value) {
            addCriterion("FUN_OPT_NM not like", value, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmIn(List<String> values) {
            addCriterion("FUN_OPT_NM in", values, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmNotIn(List<String> values) {
            addCriterion("FUN_OPT_NM not in", values, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmBetween(String value1, String value2) {
            addCriterion("FUN_OPT_NM between", value1, value2, "funOptNm");return this;
        }

        public GeneratedCriteria andFunOptNmNotBetween(String value1, String value2) {
            addCriterion("FUN_OPT_NM not between", value1, value2, "funOptNm");return this;
        }

        public GeneratedCriteria andUrlIsNull() {
            addCriterion("URL is null");return this;
        }

        public GeneratedCriteria andUrlIsNotNull() {
            addCriterion("URL is not null");return this;
        }

        public GeneratedCriteria andUrlEqualTo(String value) {
            addCriterion("URL =", value, "url");return this;
        }

        public GeneratedCriteria andUrlNotEqualTo(String value) {
            addCriterion("URL <>", value, "url");return this;
        }

        public GeneratedCriteria andUrlGreaterThan(String value) {
            addCriterion("URL >", value, "url");return this;
        }

        public GeneratedCriteria andUrlGreaterThanOrEqualTo(String value) {
            addCriterion("URL >=", value, "url");return this;
        }

        public GeneratedCriteria andUrlLessThan(String value) {
            addCriterion("URL <", value, "url");return this;
        }

        public GeneratedCriteria andUrlLessThanOrEqualTo(String value) {
            addCriterion("URL <=", value, "url");return this;
        }

        public GeneratedCriteria andUrlLike(String value) {
            addCriterion("URL like", value, "url");return this;
        }

        public GeneratedCriteria andUrlNotLike(String value) {
            addCriterion("URL not like", value, "url");return this;
        }

        public GeneratedCriteria andUrlIn(List<String> values) {
            addCriterion("URL in", values, "url");return this;
        }

        public GeneratedCriteria andUrlNotIn(List<String> values) {
            addCriterion("URL not in", values, "url");return this;
        }

        public GeneratedCriteria andUrlBetween(String value1, String value2) {
            addCriterion("URL between", value1, value2, "url");return this;
        }

        public GeneratedCriteria andUrlNotBetween(String value1, String value2) {
            addCriterion("URL not between", value1, value2, "url");return this;
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

        public GeneratedCriteria andParentFunOptIdIsNull() {
            addCriterion("PARENT_FUN_OPT_ID is null");return this;
        }

        public GeneratedCriteria andParentFunOptIdIsNotNull() {
            addCriterion("PARENT_FUN_OPT_ID is not null");return this;
        }

        public GeneratedCriteria andParentFunOptIdEqualTo(Long value) {
            addCriterion("PARENT_FUN_OPT_ID =", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdNotEqualTo(Long value) {
            addCriterion("PARENT_FUN_OPT_ID <>", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdGreaterThan(Long value) {
            addCriterion("PARENT_FUN_OPT_ID >", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdGreaterThanOrEqualTo(Long value) {
            addCriterion("PARENT_FUN_OPT_ID >=", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdLessThan(Long value) {
            addCriterion("PARENT_FUN_OPT_ID <", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdLessThanOrEqualTo(Long value) {
            addCriterion("PARENT_FUN_OPT_ID <=", value, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdIn(List<Long> values) {
            addCriterion("PARENT_FUN_OPT_ID in", values, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdNotIn(List<Long> values) {
            addCriterion("PARENT_FUN_OPT_ID not in", values, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdBetween(Long value1, Long value2) {
            addCriterion("PARENT_FUN_OPT_ID between", value1, value2, "parentFunOptId");return this;
        }

        public GeneratedCriteria andParentFunOptIdNotBetween(Long value1, Long value2) {
            addCriterion("PARENT_FUN_OPT_ID not between", value1, value2, "parentFunOptId");return this;
        }

        public GeneratedCriteria andFStateIsNull() {
            addCriterion("F_STATE is null");return this;
        }

        public GeneratedCriteria andFStateIsNotNull() {
            addCriterion("F_STATE is not null");return this;
        }

        public GeneratedCriteria andFStateEqualTo(Long value) {
            addCriterion("F_STATE =", value, "fState");return this;
        }

        public GeneratedCriteria andFStateNotEqualTo(Long value) {
            addCriterion("F_STATE <>", value, "fState");return this;
        }

        public GeneratedCriteria andFStateGreaterThan(Long value) {
            addCriterion("F_STATE >", value, "fState");return this;
        }

        public GeneratedCriteria andFStateGreaterThanOrEqualTo(Long value) {
            addCriterion("F_STATE >=", value, "fState");return this;
        }

        public GeneratedCriteria andFStateLessThan(Long value) {
            addCriterion("F_STATE <", value, "fState");return this;
        }

        public GeneratedCriteria andFStateLessThanOrEqualTo(Long value) {
            addCriterion("F_STATE <=", value, "fState");return this;
        }

        public GeneratedCriteria andFStateIn(List<Long> values) {
            addCriterion("F_STATE in", values, "fState");return this;
        }

        public GeneratedCriteria andFStateNotIn(List<Long> values) {
            addCriterion("F_STATE not in", values, "fState");return this;
        }

        public GeneratedCriteria andFStateBetween(Long value1, Long value2) {
            addCriterion("F_STATE between", value1, value2, "fState");return this;
        }

        public GeneratedCriteria andFStateNotBetween(Long value1, Long value2) {
            addCriterion("F_STATE not between", value1, value2, "fState");return this;
        }

        public GeneratedCriteria andFOrderIsNull() {
            addCriterion("F_ORDER is null");return this;
        }

        public GeneratedCriteria andFOrderIsNotNull() {
            addCriterion("F_ORDER is not null");return this;
        }

        public GeneratedCriteria andFOrderEqualTo(Long value) {
            addCriterion("F_ORDER =", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderNotEqualTo(Long value) {
            addCriterion("F_ORDER <>", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderGreaterThan(Long value) {
            addCriterion("F_ORDER >", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("F_ORDER >=", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderLessThan(Long value) {
            addCriterion("F_ORDER <", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderLessThanOrEqualTo(Long value) {
            addCriterion("F_ORDER <=", value, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderIn(List<Long> values) {
            addCriterion("F_ORDER in", values, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderNotIn(List<Long> values) {
            addCriterion("F_ORDER not in", values, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderBetween(Long value1, Long value2) {
            addCriterion("F_ORDER between", value1, value2, "fOrder");return this;
        }

        public GeneratedCriteria andFOrderNotBetween(Long value1, Long value2) {
            addCriterion("F_ORDER not between", value1, value2, "fOrder");return this;
        }

        public GeneratedCriteria andFCodeIsNull() {
            addCriterion("F_CODE is null");return this;
        }

        public GeneratedCriteria andFCodeIsNotNull() {
            addCriterion("F_CODE is not null");return this;
        }

        public GeneratedCriteria andFCodeEqualTo(String value) {
            addCriterion("F_CODE =", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeNotEqualTo(String value) {
            addCriterion("F_CODE <>", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeGreaterThan(String value) {
            addCriterion("F_CODE >", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeGreaterThanOrEqualTo(String value) {
            addCriterion("F_CODE >=", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeLessThan(String value) {
            addCriterion("F_CODE <", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeLessThanOrEqualTo(String value) {
            addCriterion("F_CODE <=", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeLike(String value) {
            addCriterion("F_CODE like", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeNotLike(String value) {
            addCriterion("F_CODE not like", value, "fCode");return this;
        }

        public GeneratedCriteria andFCodeIn(List<String> values) {
            addCriterion("F_CODE in", values, "fCode");return this;
        }

        public GeneratedCriteria andFCodeNotIn(List<String> values) {
            addCriterion("F_CODE not in", values, "fCode");return this;
        }

        public GeneratedCriteria andFCodeBetween(String value1, String value2) {
            addCriterion("F_CODE between", value1, value2, "fCode");return this;
        }

        public GeneratedCriteria andFCodeNotBetween(String value1, String value2) {
            addCriterion("F_CODE not between", value1, value2, "fCode");return this;
        }

        public GeneratedCriteria andFFlowIsNull() {
            addCriterion("F_FLOW is null");return this;
        }

        public GeneratedCriteria andFFlowIsNotNull() {
            addCriterion("F_FLOW is not null");return this;
        }

        public GeneratedCriteria andFFlowEqualTo(String value) {
            addCriterion("F_FLOW =", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowNotEqualTo(String value) {
            addCriterion("F_FLOW <>", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowGreaterThan(String value) {
            addCriterion("F_FLOW >", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowGreaterThanOrEqualTo(String value) {
            addCriterion("F_FLOW >=", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowLessThan(String value) {
            addCriterion("F_FLOW <", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowLessThanOrEqualTo(String value) {
            addCriterion("F_FLOW <=", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowLike(String value) {
            addCriterion("F_FLOW like", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowNotLike(String value) {
            addCriterion("F_FLOW not like", value, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowIn(List<String> values) {
            addCriterion("F_FLOW in", values, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowNotIn(List<String> values) {
            addCriterion("F_FLOW not in", values, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowBetween(String value1, String value2) {
            addCriterion("F_FLOW between", value1, value2, "fFlow");return this;
        }

        public GeneratedCriteria andFFlowNotBetween(String value1, String value2) {
            addCriterion("F_FLOW not between", value1, value2, "fFlow");return this;
        }
    }
}