package com.dlz.framework.ssme.db.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.DictsCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class DictsCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public DictsCriteria() {
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

        public GeneratedCriteria andNameIsNull() {
            addCriterion("NAME is null");return this;
        }

        public GeneratedCriteria andNameIsNotNull() {
            addCriterion("NAME is not null");return this;
        }

        public GeneratedCriteria andNameEqualTo(String value) {
            addCriterion("NAME =", value, "name");return this;
        }

        public GeneratedCriteria andNameNotEqualTo(String value) {
            addCriterion("NAME <>", value, "name");return this;
        }

        public GeneratedCriteria andNameGreaterThan(String value) {
            addCriterion("NAME >", value, "name");return this;
        }

        public GeneratedCriteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("NAME >=", value, "name");return this;
        }

        public GeneratedCriteria andNameLessThan(String value) {
            addCriterion("NAME <", value, "name");return this;
        }

        public GeneratedCriteria andNameLessThanOrEqualTo(String value) {
            addCriterion("NAME <=", value, "name");return this;
        }

        public GeneratedCriteria andNameLike(String value) {
            addCriterion("NAME like", value, "name");return this;
        }

        public GeneratedCriteria andNameNotLike(String value) {
            addCriterion("NAME not like", value, "name");return this;
        }

        public GeneratedCriteria andNameIn(List<String> values) {
            addCriterion("NAME in", values, "name");return this;
        }

        public GeneratedCriteria andNameNotIn(List<String> values) {
            addCriterion("NAME not in", values, "name");return this;
        }

        public GeneratedCriteria andNameBetween(String value1, String value2) {
            addCriterion("NAME between", value1, value2, "name");return this;
        }

        public GeneratedCriteria andNameNotBetween(String value1, String value2) {
            addCriterion("NAME not between", value1, value2, "name");return this;
        }

        public GeneratedCriteria andBzIsNull() {
            addCriterion("BZ is null");return this;
        }

        public GeneratedCriteria andBzIsNotNull() {
            addCriterion("BZ is not null");return this;
        }

        public GeneratedCriteria andBzEqualTo(String value) {
            addCriterion("BZ =", value, "bz");return this;
        }

        public GeneratedCriteria andBzNotEqualTo(String value) {
            addCriterion("BZ <>", value, "bz");return this;
        }

        public GeneratedCriteria andBzGreaterThan(String value) {
            addCriterion("BZ >", value, "bz");return this;
        }

        public GeneratedCriteria andBzGreaterThanOrEqualTo(String value) {
            addCriterion("BZ >=", value, "bz");return this;
        }

        public GeneratedCriteria andBzLessThan(String value) {
            addCriterion("BZ <", value, "bz");return this;
        }

        public GeneratedCriteria andBzLessThanOrEqualTo(String value) {
            addCriterion("BZ <=", value, "bz");return this;
        }

        public GeneratedCriteria andBzLike(String value) {
            addCriterion("BZ like", value, "bz");return this;
        }

        public GeneratedCriteria andBzNotLike(String value) {
            addCriterion("BZ not like", value, "bz");return this;
        }

        public GeneratedCriteria andBzIn(List<String> values) {
            addCriterion("BZ in", values, "bz");return this;
        }

        public GeneratedCriteria andBzNotIn(List<String> values) {
            addCriterion("BZ not in", values, "bz");return this;
        }

        public GeneratedCriteria andBzBetween(String value1, String value2) {
            addCriterion("BZ between", value1, value2, "bz");return this;
        }

        public GeneratedCriteria andBzNotBetween(String value1, String value2) {
            addCriterion("BZ not between", value1, value2, "bz");return this;
        }

        public GeneratedCriteria andPidIsNull() {
            addCriterion("PID is null");return this;
        }

        public GeneratedCriteria andPidIsNotNull() {
            addCriterion("PID is not null");return this;
        }

        public GeneratedCriteria andPidEqualTo(Long value) {
            addCriterion("PID =", value, "pid");return this;
        }

        public GeneratedCriteria andPidNotEqualTo(Long value) {
            addCriterion("PID <>", value, "pid");return this;
        }

        public GeneratedCriteria andPidGreaterThan(Long value) {
            addCriterion("PID >", value, "pid");return this;
        }

        public GeneratedCriteria andPidGreaterThanOrEqualTo(Long value) {
            addCriterion("PID >=", value, "pid");return this;
        }

        public GeneratedCriteria andPidLessThan(Long value) {
            addCriterion("PID <", value, "pid");return this;
        }

        public GeneratedCriteria andPidLessThanOrEqualTo(Long value) {
            addCriterion("PID <=", value, "pid");return this;
        }

        public GeneratedCriteria andPidIn(List<Long> values) {
            addCriterion("PID in", values, "pid");return this;
        }

        public GeneratedCriteria andPidNotIn(List<Long> values) {
            addCriterion("PID not in", values, "pid");return this;
        }

        public GeneratedCriteria andPidBetween(Long value1, Long value2) {
            addCriterion("PID between", value1, value2, "pid");return this;
        }

        public GeneratedCriteria andPidNotBetween(Long value1, Long value2) {
            addCriterion("PID not between", value1, value2, "pid");return this;
        }

        public GeneratedCriteria andOrdIsNull() {
            addCriterion("ORD is null");return this;
        }

        public GeneratedCriteria andOrdIsNotNull() {
            addCriterion("ORD is not null");return this;
        }

        public GeneratedCriteria andOrdEqualTo(Long value) {
            addCriterion("ORD =", value, "ord");return this;
        }

        public GeneratedCriteria andOrdNotEqualTo(Long value) {
            addCriterion("ORD <>", value, "ord");return this;
        }

        public GeneratedCriteria andOrdGreaterThan(Long value) {
            addCriterion("ORD >", value, "ord");return this;
        }

        public GeneratedCriteria andOrdGreaterThanOrEqualTo(Long value) {
            addCriterion("ORD >=", value, "ord");return this;
        }

        public GeneratedCriteria andOrdLessThan(Long value) {
            addCriterion("ORD <", value, "ord");return this;
        }

        public GeneratedCriteria andOrdLessThanOrEqualTo(Long value) {
            addCriterion("ORD <=", value, "ord");return this;
        }

        public GeneratedCriteria andOrdIn(List<Long> values) {
            addCriterion("ORD in", values, "ord");return this;
        }

        public GeneratedCriteria andOrdNotIn(List<Long> values) {
            addCriterion("ORD not in", values, "ord");return this;
        }

        public GeneratedCriteria andOrdBetween(Long value1, Long value2) {
            addCriterion("ORD between", value1, value2, "ord");return this;
        }

        public GeneratedCriteria andOrdNotBetween(Long value1, Long value2) {
            addCriterion("ORD not between", value1, value2, "ord");return this;
        }

        public GeneratedCriteria andIsLeafIsNull() {
            addCriterion("IS_LEAF is null");return this;
        }

        public GeneratedCriteria andIsLeafIsNotNull() {
            addCriterion("IS_LEAF is not null");return this;
        }

        public GeneratedCriteria andIsLeafEqualTo(Long value) {
            addCriterion("IS_LEAF =", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafNotEqualTo(Long value) {
            addCriterion("IS_LEAF <>", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafGreaterThan(Long value) {
            addCriterion("IS_LEAF >", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafGreaterThanOrEqualTo(Long value) {
            addCriterion("IS_LEAF >=", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafLessThan(Long value) {
            addCriterion("IS_LEAF <", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafLessThanOrEqualTo(Long value) {
            addCriterion("IS_LEAF <=", value, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafIn(List<Long> values) {
            addCriterion("IS_LEAF in", values, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafNotIn(List<Long> values) {
            addCriterion("IS_LEAF not in", values, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafBetween(Long value1, Long value2) {
            addCriterion("IS_LEAF between", value1, value2, "isLeaf");return this;
        }

        public GeneratedCriteria andIsLeafNotBetween(Long value1, Long value2) {
            addCriterion("IS_LEAF not between", value1, value2, "isLeaf");return this;
        }

        public GeneratedCriteria andValIsNull() {
            addCriterion("VAL is null");return this;
        }

        public GeneratedCriteria andValIsNotNull() {
            addCriterion("VAL is not null");return this;
        }

        public GeneratedCriteria andValEqualTo(String value) {
            addCriterion("VAL =", value, "val");return this;
        }

        public GeneratedCriteria andValNotEqualTo(String value) {
            addCriterion("VAL <>", value, "val");return this;
        }

        public GeneratedCriteria andValGreaterThan(String value) {
            addCriterion("VAL >", value, "val");return this;
        }

        public GeneratedCriteria andValGreaterThanOrEqualTo(String value) {
            addCriterion("VAL >=", value, "val");return this;
        }

        public GeneratedCriteria andValLessThan(String value) {
            addCriterion("VAL <", value, "val");return this;
        }

        public GeneratedCriteria andValLessThanOrEqualTo(String value) {
            addCriterion("VAL <=", value, "val");return this;
        }

        public GeneratedCriteria andValLike(String value) {
            addCriterion("VAL like", value, "val");return this;
        }

        public GeneratedCriteria andValNotLike(String value) {
            addCriterion("VAL not like", value, "val");return this;
        }

        public GeneratedCriteria andValIn(List<String> values) {
            addCriterion("VAL in", values, "val");return this;
        }

        public GeneratedCriteria andValNotIn(List<String> values) {
            addCriterion("VAL not in", values, "val");return this;
        }

        public GeneratedCriteria andValBetween(String value1, String value2) {
            addCriterion("VAL between", value1, value2, "val");return this;
        }

        public GeneratedCriteria andValNotBetween(String value1, String value2) {
            addCriterion("VAL not between", value1, value2, "val");return this;
        }

        public GeneratedCriteria andNameEnIsNull() {
            addCriterion("NAME_EN is null");return this;
        }

        public GeneratedCriteria andNameEnIsNotNull() {
            addCriterion("NAME_EN is not null");return this;
        }

        public GeneratedCriteria andNameEnEqualTo(String value) {
            addCriterion("NAME_EN =", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnNotEqualTo(String value) {
            addCriterion("NAME_EN <>", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnGreaterThan(String value) {
            addCriterion("NAME_EN >", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("NAME_EN >=", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnLessThan(String value) {
            addCriterion("NAME_EN <", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnLessThanOrEqualTo(String value) {
            addCriterion("NAME_EN <=", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnLike(String value) {
            addCriterion("NAME_EN like", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnNotLike(String value) {
            addCriterion("NAME_EN not like", value, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnIn(List<String> values) {
            addCriterion("NAME_EN in", values, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnNotIn(List<String> values) {
            addCriterion("NAME_EN not in", values, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnBetween(String value1, String value2) {
            addCriterion("NAME_EN between", value1, value2, "nameEn");return this;
        }

        public GeneratedCriteria andNameEnNotBetween(String value1, String value2) {
            addCriterion("NAME_EN not between", value1, value2, "nameEn");return this;
        }

        public GeneratedCriteria andCodeIsNull() {
            addCriterion("CODE is null");return this;
        }

        public GeneratedCriteria andCodeIsNotNull() {
            addCriterion("CODE is not null");return this;
        }

        public GeneratedCriteria andCodeEqualTo(String value) {
            addCriterion("CODE =", value, "code");return this;
        }

        public GeneratedCriteria andCodeNotEqualTo(String value) {
            addCriterion("CODE <>", value, "code");return this;
        }

        public GeneratedCriteria andCodeGreaterThan(String value) {
            addCriterion("CODE >", value, "code");return this;
        }

        public GeneratedCriteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("CODE >=", value, "code");return this;
        }

        public GeneratedCriteria andCodeLessThan(String value) {
            addCriterion("CODE <", value, "code");return this;
        }

        public GeneratedCriteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("CODE <=", value, "code");return this;
        }

        public GeneratedCriteria andCodeLike(String value) {
            addCriterion("CODE like", value, "code");return this;
        }

        public GeneratedCriteria andCodeNotLike(String value) {
            addCriterion("CODE not like", value, "code");return this;
        }

        public GeneratedCriteria andCodeIn(List<String> values) {
            addCriterion("CODE in", values, "code");return this;
        }

        public GeneratedCriteria andCodeNotIn(List<String> values) {
            addCriterion("CODE not in", values, "code");return this;
        }

        public GeneratedCriteria andCodeBetween(String value1, String value2) {
            addCriterion("CODE between", value1, value2, "code");return this;
        }

        public GeneratedCriteria andCodeNotBetween(String value1, String value2) {
            addCriterion("CODE not between", value1, value2, "code");return this;
        }
    }
}