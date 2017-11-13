package com.dlz.framework.ssme.db.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.DeptCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class DeptCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public DeptCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andDIdIsNull() {
            addCriterion("D_ID is null");return this;
        }

        public GeneratedCriteria andDIdIsNotNull() {
            addCriterion("D_ID is not null");return this;
        }

        public GeneratedCriteria andDIdEqualTo(Long value) {
            addCriterion("D_ID =", value, "dId");return this;
        }

        public GeneratedCriteria andDIdNotEqualTo(Long value) {
            addCriterion("D_ID <>", value, "dId");return this;
        }

        public GeneratedCriteria andDIdGreaterThan(Long value) {
            addCriterion("D_ID >", value, "dId");return this;
        }

        public GeneratedCriteria andDIdGreaterThanOrEqualTo(Long value) {
            addCriterion("D_ID >=", value, "dId");return this;
        }

        public GeneratedCriteria andDIdLessThan(Long value) {
            addCriterion("D_ID <", value, "dId");return this;
        }

        public GeneratedCriteria andDIdLessThanOrEqualTo(Long value) {
            addCriterion("D_ID <=", value, "dId");return this;
        }

        public GeneratedCriteria andDIdIn(List<Long> values) {
            addCriterion("D_ID in", values, "dId");return this;
        }

        public GeneratedCriteria andDIdNotIn(List<Long> values) {
            addCriterion("D_ID not in", values, "dId");return this;
        }

        public GeneratedCriteria andDIdBetween(Long value1, Long value2) {
            addCriterion("D_ID between", value1, value2, "dId");return this;
        }

        public GeneratedCriteria andDIdNotBetween(Long value1, Long value2) {
            addCriterion("D_ID not between", value1, value2, "dId");return this;
        }

        public GeneratedCriteria andDCodeIsNull() {
            addCriterion("D_CODE is null");return this;
        }

        public GeneratedCriteria andDCodeIsNotNull() {
            addCriterion("D_CODE is not null");return this;
        }

        public GeneratedCriteria andDCodeEqualTo(String value) {
            addCriterion("D_CODE =", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeNotEqualTo(String value) {
            addCriterion("D_CODE <>", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeGreaterThan(String value) {
            addCriterion("D_CODE >", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeGreaterThanOrEqualTo(String value) {
            addCriterion("D_CODE >=", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeLessThan(String value) {
            addCriterion("D_CODE <", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeLessThanOrEqualTo(String value) {
            addCriterion("D_CODE <=", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeLike(String value) {
            addCriterion("D_CODE like", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeNotLike(String value) {
            addCriterion("D_CODE not like", value, "dCode");return this;
        }

        public GeneratedCriteria andDCodeIn(List<String> values) {
            addCriterion("D_CODE in", values, "dCode");return this;
        }

        public GeneratedCriteria andDCodeNotIn(List<String> values) {
            addCriterion("D_CODE not in", values, "dCode");return this;
        }

        public GeneratedCriteria andDCodeBetween(String value1, String value2) {
            addCriterion("D_CODE between", value1, value2, "dCode");return this;
        }

        public GeneratedCriteria andDCodeNotBetween(String value1, String value2) {
            addCriterion("D_CODE not between", value1, value2, "dCode");return this;
        }

        public GeneratedCriteria andDNameIsNull() {
            addCriterion("D_NAME is null");return this;
        }

        public GeneratedCriteria andDNameIsNotNull() {
            addCriterion("D_NAME is not null");return this;
        }

        public GeneratedCriteria andDNameEqualTo(String value) {
            addCriterion("D_NAME =", value, "dName");return this;
        }

        public GeneratedCriteria andDNameNotEqualTo(String value) {
            addCriterion("D_NAME <>", value, "dName");return this;
        }

        public GeneratedCriteria andDNameGreaterThan(String value) {
            addCriterion("D_NAME >", value, "dName");return this;
        }

        public GeneratedCriteria andDNameGreaterThanOrEqualTo(String value) {
            addCriterion("D_NAME >=", value, "dName");return this;
        }

        public GeneratedCriteria andDNameLessThan(String value) {
            addCriterion("D_NAME <", value, "dName");return this;
        }

        public GeneratedCriteria andDNameLessThanOrEqualTo(String value) {
            addCriterion("D_NAME <=", value, "dName");return this;
        }

        public GeneratedCriteria andDNameLike(String value) {
            addCriterion("D_NAME like", value, "dName");return this;
        }

        public GeneratedCriteria andDNameNotLike(String value) {
            addCriterion("D_NAME not like", value, "dName");return this;
        }

        public GeneratedCriteria andDNameIn(List<String> values) {
            addCriterion("D_NAME in", values, "dName");return this;
        }

        public GeneratedCriteria andDNameNotIn(List<String> values) {
            addCriterion("D_NAME not in", values, "dName");return this;
        }

        public GeneratedCriteria andDNameBetween(String value1, String value2) {
            addCriterion("D_NAME between", value1, value2, "dName");return this;
        }

        public GeneratedCriteria andDNameNotBetween(String value1, String value2) {
            addCriterion("D_NAME not between", value1, value2, "dName");return this;
        }

        public GeneratedCriteria andDFidIsNull() {
            addCriterion("D_FID is null");return this;
        }

        public GeneratedCriteria andDFidIsNotNull() {
            addCriterion("D_FID is not null");return this;
        }

        public GeneratedCriteria andDFidEqualTo(Long value) {
            addCriterion("D_FID =", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidNotEqualTo(Long value) {
            addCriterion("D_FID <>", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidGreaterThan(Long value) {
            addCriterion("D_FID >", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidGreaterThanOrEqualTo(Long value) {
            addCriterion("D_FID >=", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidLessThan(Long value) {
            addCriterion("D_FID <", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidLessThanOrEqualTo(Long value) {
            addCriterion("D_FID <=", value, "dFid");return this;
        }

        public GeneratedCriteria andDFidIn(List<Long> values) {
            addCriterion("D_FID in", values, "dFid");return this;
        }

        public GeneratedCriteria andDFidNotIn(List<Long> values) {
            addCriterion("D_FID not in", values, "dFid");return this;
        }

        public GeneratedCriteria andDFidBetween(Long value1, Long value2) {
            addCriterion("D_FID between", value1, value2, "dFid");return this;
        }

        public GeneratedCriteria andDFidNotBetween(Long value1, Long value2) {
            addCriterion("D_FID not between", value1, value2, "dFid");return this;
        }

        public GeneratedCriteria andDScFlgIsNull() {
            addCriterion("D_SC_FLG is null");return this;
        }

        public GeneratedCriteria andDScFlgIsNotNull() {
            addCriterion("D_SC_FLG is not null");return this;
        }

        public GeneratedCriteria andDScFlgEqualTo(String value) {
            addCriterion("D_SC_FLG =", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgNotEqualTo(String value) {
            addCriterion("D_SC_FLG <>", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgGreaterThan(String value) {
            addCriterion("D_SC_FLG >", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgGreaterThanOrEqualTo(String value) {
            addCriterion("D_SC_FLG >=", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgLessThan(String value) {
            addCriterion("D_SC_FLG <", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgLessThanOrEqualTo(String value) {
            addCriterion("D_SC_FLG <=", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgLike(String value) {
            addCriterion("D_SC_FLG like", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgNotLike(String value) {
            addCriterion("D_SC_FLG not like", value, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgIn(List<String> values) {
            addCriterion("D_SC_FLG in", values, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgNotIn(List<String> values) {
            addCriterion("D_SC_FLG not in", values, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgBetween(String value1, String value2) {
            addCriterion("D_SC_FLG between", value1, value2, "dScFlg");return this;
        }

        public GeneratedCriteria andDScFlgNotBetween(String value1, String value2) {
            addCriterion("D_SC_FLG not between", value1, value2, "dScFlg");return this;
        }

        public GeneratedCriteria andDManagerIdIsNull() {
            addCriterion("D_MANAGER_ID is null");return this;
        }

        public GeneratedCriteria andDManagerIdIsNotNull() {
            addCriterion("D_MANAGER_ID is not null");return this;
        }

        public GeneratedCriteria andDManagerIdEqualTo(Long value) {
            addCriterion("D_MANAGER_ID =", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdNotEqualTo(Long value) {
            addCriterion("D_MANAGER_ID <>", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdGreaterThan(Long value) {
            addCriterion("D_MANAGER_ID >", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdGreaterThanOrEqualTo(Long value) {
            addCriterion("D_MANAGER_ID >=", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdLessThan(Long value) {
            addCriterion("D_MANAGER_ID <", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdLessThanOrEqualTo(Long value) {
            addCriterion("D_MANAGER_ID <=", value, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdIn(List<Long> values) {
            addCriterion("D_MANAGER_ID in", values, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdNotIn(List<Long> values) {
            addCriterion("D_MANAGER_ID not in", values, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdBetween(Long value1, Long value2) {
            addCriterion("D_MANAGER_ID between", value1, value2, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerIdNotBetween(Long value1, Long value2) {
            addCriterion("D_MANAGER_ID not between", value1, value2, "dManagerId");return this;
        }

        public GeneratedCriteria andDManagerNameIsNull() {
            addCriterion("D_MANAGER_NAME is null");return this;
        }

        public GeneratedCriteria andDManagerNameIsNotNull() {
            addCriterion("D_MANAGER_NAME is not null");return this;
        }

        public GeneratedCriteria andDManagerNameEqualTo(String value) {
            addCriterion("D_MANAGER_NAME =", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameNotEqualTo(String value) {
            addCriterion("D_MANAGER_NAME <>", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameGreaterThan(String value) {
            addCriterion("D_MANAGER_NAME >", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameGreaterThanOrEqualTo(String value) {
            addCriterion("D_MANAGER_NAME >=", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameLessThan(String value) {
            addCriterion("D_MANAGER_NAME <", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameLessThanOrEqualTo(String value) {
            addCriterion("D_MANAGER_NAME <=", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameLike(String value) {
            addCriterion("D_MANAGER_NAME like", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameNotLike(String value) {
            addCriterion("D_MANAGER_NAME not like", value, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameIn(List<String> values) {
            addCriterion("D_MANAGER_NAME in", values, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameNotIn(List<String> values) {
            addCriterion("D_MANAGER_NAME not in", values, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameBetween(String value1, String value2) {
            addCriterion("D_MANAGER_NAME between", value1, value2, "dManagerName");return this;
        }

        public GeneratedCriteria andDManagerNameNotBetween(String value1, String value2) {
            addCriterion("D_MANAGER_NAME not between", value1, value2, "dManagerName");return this;
        }

        public GeneratedCriteria andDTypeIsNull() {
            addCriterion("D_TYPE is null");return this;
        }

        public GeneratedCriteria andDTypeIsNotNull() {
            addCriterion("D_TYPE is not null");return this;
        }

        public GeneratedCriteria andDTypeEqualTo(String value) {
            addCriterion("D_TYPE =", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotEqualTo(String value) {
            addCriterion("D_TYPE <>", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeGreaterThan(String value) {
            addCriterion("D_TYPE >", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeGreaterThanOrEqualTo(String value) {
            addCriterion("D_TYPE >=", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLessThan(String value) {
            addCriterion("D_TYPE <", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLessThanOrEqualTo(String value) {
            addCriterion("D_TYPE <=", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLike(String value) {
            addCriterion("D_TYPE like", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotLike(String value) {
            addCriterion("D_TYPE not like", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeIn(List<String> values) {
            addCriterion("D_TYPE in", values, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotIn(List<String> values) {
            addCriterion("D_TYPE not in", values, "dType");return this;
        }

        public GeneratedCriteria andDTypeBetween(String value1, String value2) {
            addCriterion("D_TYPE between", value1, value2, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotBetween(String value1, String value2) {
            addCriterion("D_TYPE not between", value1, value2, "dType");return this;
        }
    }
}