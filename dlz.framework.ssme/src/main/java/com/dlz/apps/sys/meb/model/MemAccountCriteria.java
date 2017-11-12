package com.dlz.apps.sys.meb.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.sys.meb.model.MemAccountCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.List;

public class MemAccountCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MemAccountCriteria() {
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

        public GeneratedCriteria andAMidIsNull() {
            addCriterion("A_MID is null");return this;
        }

        public GeneratedCriteria andAMidIsNotNull() {
            addCriterion("A_MID is not null");return this;
        }

        public GeneratedCriteria andAMidEqualTo(Long value) {
            addCriterion("A_MID =", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidNotEqualTo(Long value) {
            addCriterion("A_MID <>", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidGreaterThan(Long value) {
            addCriterion("A_MID >", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidGreaterThanOrEqualTo(Long value) {
            addCriterion("A_MID >=", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidLessThan(Long value) {
            addCriterion("A_MID <", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidLessThanOrEqualTo(Long value) {
            addCriterion("A_MID <=", value, "aMid");return this;
        }

        public GeneratedCriteria andAMidIn(List<Long> values) {
            addCriterion("A_MID in", values, "aMid");return this;
        }

        public GeneratedCriteria andAMidNotIn(List<Long> values) {
            addCriterion("A_MID not in", values, "aMid");return this;
        }

        public GeneratedCriteria andAMidBetween(Long value1, Long value2) {
            addCriterion("A_MID between", value1, value2, "aMid");return this;
        }

        public GeneratedCriteria andAMidNotBetween(Long value1, Long value2) {
            addCriterion("A_MID not between", value1, value2, "aMid");return this;
        }

        public GeneratedCriteria andATypeIsNull() {
            addCriterion("A_TYPE is null");return this;
        }

        public GeneratedCriteria andATypeIsNotNull() {
            addCriterion("A_TYPE is not null");return this;
        }

        public GeneratedCriteria andATypeEqualTo(Long value) {
            addCriterion("A_TYPE =", value, "aType");return this;
        }

        public GeneratedCriteria andATypeNotEqualTo(Long value) {
            addCriterion("A_TYPE <>", value, "aType");return this;
        }

        public GeneratedCriteria andATypeGreaterThan(Long value) {
            addCriterion("A_TYPE >", value, "aType");return this;
        }

        public GeneratedCriteria andATypeGreaterThanOrEqualTo(Long value) {
            addCriterion("A_TYPE >=", value, "aType");return this;
        }

        public GeneratedCriteria andATypeLessThan(Long value) {
            addCriterion("A_TYPE <", value, "aType");return this;
        }

        public GeneratedCriteria andATypeLessThanOrEqualTo(Long value) {
            addCriterion("A_TYPE <=", value, "aType");return this;
        }

        public GeneratedCriteria andATypeIn(List<Long> values) {
            addCriterion("A_TYPE in", values, "aType");return this;
        }

        public GeneratedCriteria andATypeNotIn(List<Long> values) {
            addCriterion("A_TYPE not in", values, "aType");return this;
        }

        public GeneratedCriteria andATypeBetween(Long value1, Long value2) {
            addCriterion("A_TYPE between", value1, value2, "aType");return this;
        }

        public GeneratedCriteria andATypeNotBetween(Long value1, Long value2) {
            addCriterion("A_TYPE not between", value1, value2, "aType");return this;
        }

        public GeneratedCriteria andANameIsNull() {
            addCriterion("A_NAME is null");return this;
        }

        public GeneratedCriteria andANameIsNotNull() {
            addCriterion("A_NAME is not null");return this;
        }

        public GeneratedCriteria andANameEqualTo(String value) {
            addCriterion("A_NAME =", value, "aName");return this;
        }

        public GeneratedCriteria andANameNotEqualTo(String value) {
            addCriterion("A_NAME <>", value, "aName");return this;
        }

        public GeneratedCriteria andANameGreaterThan(String value) {
            addCriterion("A_NAME >", value, "aName");return this;
        }

        public GeneratedCriteria andANameGreaterThanOrEqualTo(String value) {
            addCriterion("A_NAME >=", value, "aName");return this;
        }

        public GeneratedCriteria andANameLessThan(String value) {
            addCriterion("A_NAME <", value, "aName");return this;
        }

        public GeneratedCriteria andANameLessThanOrEqualTo(String value) {
            addCriterion("A_NAME <=", value, "aName");return this;
        }

        public GeneratedCriteria andANameLike(String value) {
            addCriterion("A_NAME like", value, "aName");return this;
        }

        public GeneratedCriteria andANameNotLike(String value) {
            addCriterion("A_NAME not like", value, "aName");return this;
        }

        public GeneratedCriteria andANameIn(List<String> values) {
            addCriterion("A_NAME in", values, "aName");return this;
        }

        public GeneratedCriteria andANameNotIn(List<String> values) {
            addCriterion("A_NAME not in", values, "aName");return this;
        }

        public GeneratedCriteria andANameBetween(String value1, String value2) {
            addCriterion("A_NAME between", value1, value2, "aName");return this;
        }

        public GeneratedCriteria andANameNotBetween(String value1, String value2) {
            addCriterion("A_NAME not between", value1, value2, "aName");return this;
        }

        public GeneratedCriteria andABalanceIsNull() {
            addCriterion("A_BALANCE is null");return this;
        }

        public GeneratedCriteria andABalanceIsNotNull() {
            addCriterion("A_BALANCE is not null");return this;
        }

        public GeneratedCriteria andABalanceEqualTo(Long value) {
            addCriterion("A_BALANCE =", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceNotEqualTo(Long value) {
            addCriterion("A_BALANCE <>", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceGreaterThan(Long value) {
            addCriterion("A_BALANCE >", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceGreaterThanOrEqualTo(Long value) {
            addCriterion("A_BALANCE >=", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceLessThan(Long value) {
            addCriterion("A_BALANCE <", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceLessThanOrEqualTo(Long value) {
            addCriterion("A_BALANCE <=", value, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceIn(List<Long> values) {
            addCriterion("A_BALANCE in", values, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceNotIn(List<Long> values) {
            addCriterion("A_BALANCE not in", values, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceBetween(Long value1, Long value2) {
            addCriterion("A_BALANCE between", value1, value2, "aBalance");return this;
        }

        public GeneratedCriteria andABalanceNotBetween(Long value1, Long value2) {
            addCriterion("A_BALANCE not between", value1, value2, "aBalance");return this;
        }

        public GeneratedCriteria andAFreezAmountIsNull() {
            addCriterion("A_FREEZ_AMOUNT is null");return this;
        }

        public GeneratedCriteria andAFreezAmountIsNotNull() {
            addCriterion("A_FREEZ_AMOUNT is not null");return this;
        }

        public GeneratedCriteria andAFreezAmountEqualTo(Long value) {
            addCriterion("A_FREEZ_AMOUNT =", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountNotEqualTo(Long value) {
            addCriterion("A_FREEZ_AMOUNT <>", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountGreaterThan(Long value) {
            addCriterion("A_FREEZ_AMOUNT >", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountGreaterThanOrEqualTo(Long value) {
            addCriterion("A_FREEZ_AMOUNT >=", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountLessThan(Long value) {
            addCriterion("A_FREEZ_AMOUNT <", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountLessThanOrEqualTo(Long value) {
            addCriterion("A_FREEZ_AMOUNT <=", value, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountIn(List<Long> values) {
            addCriterion("A_FREEZ_AMOUNT in", values, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountNotIn(List<Long> values) {
            addCriterion("A_FREEZ_AMOUNT not in", values, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountBetween(Long value1, Long value2) {
            addCriterion("A_FREEZ_AMOUNT between", value1, value2, "aFreezAmount");return this;
        }

        public GeneratedCriteria andAFreezAmountNotBetween(Long value1, Long value2) {
            addCriterion("A_FREEZ_AMOUNT not between", value1, value2, "aFreezAmount");return this;
        }
    }
}