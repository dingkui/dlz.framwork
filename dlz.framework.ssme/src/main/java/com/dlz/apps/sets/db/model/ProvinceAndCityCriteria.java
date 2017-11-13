package com.dlz.apps.sets.db.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.sets.db.model.ProvinceAndCityCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class ProvinceAndCityCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public ProvinceAndCityCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andPIdIsNull() {
            addCriterion("P_ID is null");return this;
        }

        public GeneratedCriteria andPIdIsNotNull() {
            addCriterion("P_ID is not null");return this;
        }

        public GeneratedCriteria andPIdEqualTo(Long value) {
            addCriterion("P_ID =", value, "pId");return this;
        }

        public GeneratedCriteria andPIdNotEqualTo(Long value) {
            addCriterion("P_ID <>", value, "pId");return this;
        }

        public GeneratedCriteria andPIdGreaterThan(Long value) {
            addCriterion("P_ID >", value, "pId");return this;
        }

        public GeneratedCriteria andPIdGreaterThanOrEqualTo(Long value) {
            addCriterion("P_ID >=", value, "pId");return this;
        }

        public GeneratedCriteria andPIdLessThan(Long value) {
            addCriterion("P_ID <", value, "pId");return this;
        }

        public GeneratedCriteria andPIdLessThanOrEqualTo(Long value) {
            addCriterion("P_ID <=", value, "pId");return this;
        }

        public GeneratedCriteria andPIdIn(List<Long> values) {
            addCriterion("P_ID in", values, "pId");return this;
        }

        public GeneratedCriteria andPIdNotIn(List<Long> values) {
            addCriterion("P_ID not in", values, "pId");return this;
        }

        public GeneratedCriteria andPIdBetween(Long value1, Long value2) {
            addCriterion("P_ID between", value1, value2, "pId");return this;
        }

        public GeneratedCriteria andPIdNotBetween(Long value1, Long value2) {
            addCriterion("P_ID not between", value1, value2, "pId");return this;
        }

        public GeneratedCriteria andPNameIsNull() {
            addCriterion("P_NAME is null");return this;
        }

        public GeneratedCriteria andPNameIsNotNull() {
            addCriterion("P_NAME is not null");return this;
        }

        public GeneratedCriteria andPNameEqualTo(String value) {
            addCriterion("P_NAME =", value, "pName");return this;
        }

        public GeneratedCriteria andPNameNotEqualTo(String value) {
            addCriterion("P_NAME <>", value, "pName");return this;
        }

        public GeneratedCriteria andPNameGreaterThan(String value) {
            addCriterion("P_NAME >", value, "pName");return this;
        }

        public GeneratedCriteria andPNameGreaterThanOrEqualTo(String value) {
            addCriterion("P_NAME >=", value, "pName");return this;
        }

        public GeneratedCriteria andPNameLessThan(String value) {
            addCriterion("P_NAME <", value, "pName");return this;
        }

        public GeneratedCriteria andPNameLessThanOrEqualTo(String value) {
            addCriterion("P_NAME <=", value, "pName");return this;
        }

        public GeneratedCriteria andPNameLike(String value) {
            addCriterion("P_NAME like", value, "pName");return this;
        }

        public GeneratedCriteria andPNameNotLike(String value) {
            addCriterion("P_NAME not like", value, "pName");return this;
        }

        public GeneratedCriteria andPNameIn(List<String> values) {
            addCriterion("P_NAME in", values, "pName");return this;
        }

        public GeneratedCriteria andPNameNotIn(List<String> values) {
            addCriterion("P_NAME not in", values, "pName");return this;
        }

        public GeneratedCriteria andPNameBetween(String value1, String value2) {
            addCriterion("P_NAME between", value1, value2, "pName");return this;
        }

        public GeneratedCriteria andPNameNotBetween(String value1, String value2) {
            addCriterion("P_NAME not between", value1, value2, "pName");return this;
        }

        public GeneratedCriteria andPEnnameIsNull() {
            addCriterion("P_ENNAME is null");return this;
        }

        public GeneratedCriteria andPEnnameIsNotNull() {
            addCriterion("P_ENNAME is not null");return this;
        }

        public GeneratedCriteria andPEnnameEqualTo(String value) {
            addCriterion("P_ENNAME =", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameNotEqualTo(String value) {
            addCriterion("P_ENNAME <>", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameGreaterThan(String value) {
            addCriterion("P_ENNAME >", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameGreaterThanOrEqualTo(String value) {
            addCriterion("P_ENNAME >=", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameLessThan(String value) {
            addCriterion("P_ENNAME <", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameLessThanOrEqualTo(String value) {
            addCriterion("P_ENNAME <=", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameLike(String value) {
            addCriterion("P_ENNAME like", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameNotLike(String value) {
            addCriterion("P_ENNAME not like", value, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameIn(List<String> values) {
            addCriterion("P_ENNAME in", values, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameNotIn(List<String> values) {
            addCriterion("P_ENNAME not in", values, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameBetween(String value1, String value2) {
            addCriterion("P_ENNAME between", value1, value2, "pEnname");return this;
        }

        public GeneratedCriteria andPEnnameNotBetween(String value1, String value2) {
            addCriterion("P_ENNAME not between", value1, value2, "pEnname");return this;
        }

        public GeneratedCriteria andPFidIsNull() {
            addCriterion("P_FID is null");return this;
        }

        public GeneratedCriteria andPFidIsNotNull() {
            addCriterion("P_FID is not null");return this;
        }

        public GeneratedCriteria andPFidEqualTo(Long value) {
            addCriterion("P_FID =", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidNotEqualTo(Long value) {
            addCriterion("P_FID <>", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidGreaterThan(Long value) {
            addCriterion("P_FID >", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidGreaterThanOrEqualTo(Long value) {
            addCriterion("P_FID >=", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidLessThan(Long value) {
            addCriterion("P_FID <", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidLessThanOrEqualTo(Long value) {
            addCriterion("P_FID <=", value, "pFid");return this;
        }

        public GeneratedCriteria andPFidIn(List<Long> values) {
            addCriterion("P_FID in", values, "pFid");return this;
        }

        public GeneratedCriteria andPFidNotIn(List<Long> values) {
            addCriterion("P_FID not in", values, "pFid");return this;
        }

        public GeneratedCriteria andPFidBetween(Long value1, Long value2) {
            addCriterion("P_FID between", value1, value2, "pFid");return this;
        }

        public GeneratedCriteria andPFidNotBetween(Long value1, Long value2) {
            addCriterion("P_FID not between", value1, value2, "pFid");return this;
        }

        public GeneratedCriteria andPOrderIsNull() {
            addCriterion("P_ORDER is null");return this;
        }

        public GeneratedCriteria andPOrderIsNotNull() {
            addCriterion("P_ORDER is not null");return this;
        }

        public GeneratedCriteria andPOrderEqualTo(Long value) {
            addCriterion("P_ORDER =", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderNotEqualTo(Long value) {
            addCriterion("P_ORDER <>", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderGreaterThan(Long value) {
            addCriterion("P_ORDER >", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderGreaterThanOrEqualTo(Long value) {
            addCriterion("P_ORDER >=", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderLessThan(Long value) {
            addCriterion("P_ORDER <", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderLessThanOrEqualTo(Long value) {
            addCriterion("P_ORDER <=", value, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderIn(List<Long> values) {
            addCriterion("P_ORDER in", values, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderNotIn(List<Long> values) {
            addCriterion("P_ORDER not in", values, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderBetween(Long value1, Long value2) {
            addCriterion("P_ORDER between", value1, value2, "pOrder");return this;
        }

        public GeneratedCriteria andPOrderNotBetween(Long value1, Long value2) {
            addCriterion("P_ORDER not between", value1, value2, "pOrder");return this;
        }

        public GeneratedCriteria andPCodeIsNull() {
            addCriterion("P_CODE is null");return this;
        }

        public GeneratedCriteria andPCodeIsNotNull() {
            addCriterion("P_CODE is not null");return this;
        }

        public GeneratedCriteria andPCodeEqualTo(String value) {
            addCriterion("P_CODE =", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeNotEqualTo(String value) {
            addCriterion("P_CODE <>", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeGreaterThan(String value) {
            addCriterion("P_CODE >", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeGreaterThanOrEqualTo(String value) {
            addCriterion("P_CODE >=", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeLessThan(String value) {
            addCriterion("P_CODE <", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeLessThanOrEqualTo(String value) {
            addCriterion("P_CODE <=", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeLike(String value) {
            addCriterion("P_CODE like", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeNotLike(String value) {
            addCriterion("P_CODE not like", value, "pCode");return this;
        }

        public GeneratedCriteria andPCodeIn(List<String> values) {
            addCriterion("P_CODE in", values, "pCode");return this;
        }

        public GeneratedCriteria andPCodeNotIn(List<String> values) {
            addCriterion("P_CODE not in", values, "pCode");return this;
        }

        public GeneratedCriteria andPCodeBetween(String value1, String value2) {
            addCriterion("P_CODE between", value1, value2, "pCode");return this;
        }

        public GeneratedCriteria andPCodeNotBetween(String value1, String value2) {
            addCriterion("P_CODE not between", value1, value2, "pCode");return this;
        }

        public GeneratedCriteria andPFcodeIsNull() {
            addCriterion("P_FCODE is null");return this;
        }

        public GeneratedCriteria andPFcodeIsNotNull() {
            addCriterion("P_FCODE is not null");return this;
        }

        public GeneratedCriteria andPFcodeEqualTo(String value) {
            addCriterion("P_FCODE =", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeNotEqualTo(String value) {
            addCriterion("P_FCODE <>", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeGreaterThan(String value) {
            addCriterion("P_FCODE >", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeGreaterThanOrEqualTo(String value) {
            addCriterion("P_FCODE >=", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeLessThan(String value) {
            addCriterion("P_FCODE <", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeLessThanOrEqualTo(String value) {
            addCriterion("P_FCODE <=", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeLike(String value) {
            addCriterion("P_FCODE like", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeNotLike(String value) {
            addCriterion("P_FCODE not like", value, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeIn(List<String> values) {
            addCriterion("P_FCODE in", values, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeNotIn(List<String> values) {
            addCriterion("P_FCODE not in", values, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeBetween(String value1, String value2) {
            addCriterion("P_FCODE between", value1, value2, "pFcode");return this;
        }

        public GeneratedCriteria andPFcodeNotBetween(String value1, String value2) {
            addCriterion("P_FCODE not between", value1, value2, "pFcode");return this;
        }

        public GeneratedCriteria andPFlagIsNull() {
            addCriterion("P_FLAG is null");return this;
        }

        public GeneratedCriteria andPFlagIsNotNull() {
            addCriterion("P_FLAG is not null");return this;
        }

        public GeneratedCriteria andPFlagEqualTo(Long value) {
            addCriterion("P_FLAG =", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagNotEqualTo(Long value) {
            addCriterion("P_FLAG <>", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagGreaterThan(Long value) {
            addCriterion("P_FLAG >", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("P_FLAG >=", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagLessThan(Long value) {
            addCriterion("P_FLAG <", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagLessThanOrEqualTo(Long value) {
            addCriterion("P_FLAG <=", value, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagIn(List<Long> values) {
            addCriterion("P_FLAG in", values, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagNotIn(List<Long> values) {
            addCriterion("P_FLAG not in", values, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagBetween(Long value1, Long value2) {
            addCriterion("P_FLAG between", value1, value2, "pFlag");return this;
        }

        public GeneratedCriteria andPFlagNotBetween(Long value1, Long value2) {
            addCriterion("P_FLAG not between", value1, value2, "pFlag");return this;
        }
    }
}