package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.UserGroupCriteria.GeneratedCriteria;
public class UserGroupCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public UserGroupCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andGrpIdIsNull() {
            addCriterion("GRP_ID is null");return this;
        }

        public GeneratedCriteria andGrpIdIsNotNull() {
            addCriterion("GRP_ID is not null");return this;
        }

        public GeneratedCriteria andGrpIdEqualTo(Long value) {
            addCriterion("GRP_ID =", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdNotEqualTo(Long value) {
            addCriterion("GRP_ID <>", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdGreaterThan(Long value) {
            addCriterion("GRP_ID >", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdGreaterThanOrEqualTo(Long value) {
            addCriterion("GRP_ID >=", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdLessThan(Long value) {
            addCriterion("GRP_ID <", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdLessThanOrEqualTo(Long value) {
            addCriterion("GRP_ID <=", value, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdIn(List<Long> values) {
            addCriterion("GRP_ID in", values, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdNotIn(List<Long> values) {
            addCriterion("GRP_ID not in", values, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdBetween(Long value1, Long value2) {
            addCriterion("GRP_ID between", value1, value2, "grpId");return this;
        }

        public GeneratedCriteria andGrpIdNotBetween(Long value1, Long value2) {
            addCriterion("GRP_ID not between", value1, value2, "grpId");return this;
        }

        public GeneratedCriteria andGrpNmIsNull() {
            addCriterion("GRP_NM is null");return this;
        }

        public GeneratedCriteria andGrpNmIsNotNull() {
            addCriterion("GRP_NM is not null");return this;
        }

        public GeneratedCriteria andGrpNmEqualTo(String value) {
            addCriterion("GRP_NM =", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmNotEqualTo(String value) {
            addCriterion("GRP_NM <>", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmGreaterThan(String value) {
            addCriterion("GRP_NM >", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmGreaterThanOrEqualTo(String value) {
            addCriterion("GRP_NM >=", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmLessThan(String value) {
            addCriterion("GRP_NM <", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmLessThanOrEqualTo(String value) {
            addCriterion("GRP_NM <=", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmLike(String value) {
            addCriterion("GRP_NM like", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmNotLike(String value) {
            addCriterion("GRP_NM not like", value, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmIn(List<String> values) {
            addCriterion("GRP_NM in", values, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmNotIn(List<String> values) {
            addCriterion("GRP_NM not in", values, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmBetween(String value1, String value2) {
            addCriterion("GRP_NM between", value1, value2, "grpNm");return this;
        }

        public GeneratedCriteria andGrpNmNotBetween(String value1, String value2) {
            addCriterion("GRP_NM not between", value1, value2, "grpNm");return this;
        }

        public GeneratedCriteria andGrpDescIsNull() {
            addCriterion("GRP_DESC is null");return this;
        }

        public GeneratedCriteria andGrpDescIsNotNull() {
            addCriterion("GRP_DESC is not null");return this;
        }

        public GeneratedCriteria andGrpDescEqualTo(String value) {
            addCriterion("GRP_DESC =", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescNotEqualTo(String value) {
            addCriterion("GRP_DESC <>", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescGreaterThan(String value) {
            addCriterion("GRP_DESC >", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescGreaterThanOrEqualTo(String value) {
            addCriterion("GRP_DESC >=", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescLessThan(String value) {
            addCriterion("GRP_DESC <", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescLessThanOrEqualTo(String value) {
            addCriterion("GRP_DESC <=", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescLike(String value) {
            addCriterion("GRP_DESC like", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescNotLike(String value) {
            addCriterion("GRP_DESC not like", value, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescIn(List<String> values) {
            addCriterion("GRP_DESC in", values, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescNotIn(List<String> values) {
            addCriterion("GRP_DESC not in", values, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescBetween(String value1, String value2) {
            addCriterion("GRP_DESC between", value1, value2, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpDescNotBetween(String value1, String value2) {
            addCriterion("GRP_DESC not between", value1, value2, "grpDesc");return this;
        }

        public GeneratedCriteria andGrpTypeIsNull() {
            addCriterion("GRP_TYPE is null");return this;
        }

        public GeneratedCriteria andGrpTypeIsNotNull() {
            addCriterion("GRP_TYPE is not null");return this;
        }

        public GeneratedCriteria andGrpTypeEqualTo(String value) {
            addCriterion("GRP_TYPE =", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeNotEqualTo(String value) {
            addCriterion("GRP_TYPE <>", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeGreaterThan(String value) {
            addCriterion("GRP_TYPE >", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeGreaterThanOrEqualTo(String value) {
            addCriterion("GRP_TYPE >=", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeLessThan(String value) {
            addCriterion("GRP_TYPE <", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeLessThanOrEqualTo(String value) {
            addCriterion("GRP_TYPE <=", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeLike(String value) {
            addCriterion("GRP_TYPE like", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeNotLike(String value) {
            addCriterion("GRP_TYPE not like", value, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeIn(List<String> values) {
            addCriterion("GRP_TYPE in", values, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeNotIn(List<String> values) {
            addCriterion("GRP_TYPE not in", values, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeBetween(String value1, String value2) {
            addCriterion("GRP_TYPE between", value1, value2, "grpType");return this;
        }

        public GeneratedCriteria andGrpTypeNotBetween(String value1, String value2) {
            addCriterion("GRP_TYPE not between", value1, value2, "grpType");return this;
        }
    }
}