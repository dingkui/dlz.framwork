package com.dlz.commbiz.sys.meb.model;

import com.dlz.common.base.criteria.BaseCriteria;
import com.dlz.common.base.criteria.BaseGeneratedCriteria;
import com.dlz.commbiz.sys.meb.model.MebVcodeCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MebVcodeCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MebVcodeCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andVIdIsNull() {
            addCriterion("V_ID is null");return this;
        }

        public GeneratedCriteria andVIdIsNotNull() {
            addCriterion("V_ID is not null");return this;
        }

        public GeneratedCriteria andVIdEqualTo(Long value) {
            addCriterion("V_ID =", value, "vId");return this;
        }

        public GeneratedCriteria andVIdNotEqualTo(Long value) {
            addCriterion("V_ID <>", value, "vId");return this;
        }

        public GeneratedCriteria andVIdGreaterThan(Long value) {
            addCriterion("V_ID >", value, "vId");return this;
        }

        public GeneratedCriteria andVIdGreaterThanOrEqualTo(Long value) {
            addCriterion("V_ID >=", value, "vId");return this;
        }

        public GeneratedCriteria andVIdLessThan(Long value) {
            addCriterion("V_ID <", value, "vId");return this;
        }

        public GeneratedCriteria andVIdLessThanOrEqualTo(Long value) {
            addCriterion("V_ID <=", value, "vId");return this;
        }

        public GeneratedCriteria andVIdIn(List<Long> values) {
            addCriterion("V_ID in", values, "vId");return this;
        }

        public GeneratedCriteria andVIdNotIn(List<Long> values) {
            addCriterion("V_ID not in", values, "vId");return this;
        }

        public GeneratedCriteria andVIdBetween(Long value1, Long value2) {
            addCriterion("V_ID between", value1, value2, "vId");return this;
        }

        public GeneratedCriteria andVIdNotBetween(Long value1, Long value2) {
            addCriterion("V_ID not between", value1, value2, "vId");return this;
        }

        public GeneratedCriteria andVMIdIsNull() {
            addCriterion("V_M_ID is null");return this;
        }

        public GeneratedCriteria andVMIdIsNotNull() {
            addCriterion("V_M_ID is not null");return this;
        }

        public GeneratedCriteria andVMIdEqualTo(Long value) {
            addCriterion("V_M_ID =", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdNotEqualTo(Long value) {
            addCriterion("V_M_ID <>", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdGreaterThan(Long value) {
            addCriterion("V_M_ID >", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdGreaterThanOrEqualTo(Long value) {
            addCriterion("V_M_ID >=", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdLessThan(Long value) {
            addCriterion("V_M_ID <", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdLessThanOrEqualTo(Long value) {
            addCriterion("V_M_ID <=", value, "vMId");return this;
        }

        public GeneratedCriteria andVMIdIn(List<Long> values) {
            addCriterion("V_M_ID in", values, "vMId");return this;
        }

        public GeneratedCriteria andVMIdNotIn(List<Long> values) {
            addCriterion("V_M_ID not in", values, "vMId");return this;
        }

        public GeneratedCriteria andVMIdBetween(Long value1, Long value2) {
            addCriterion("V_M_ID between", value1, value2, "vMId");return this;
        }

        public GeneratedCriteria andVMIdNotBetween(Long value1, Long value2) {
            addCriterion("V_M_ID not between", value1, value2, "vMId");return this;
        }

        public GeneratedCriteria andVCodeIsNull() {
            addCriterion("V_CODE is null");return this;
        }

        public GeneratedCriteria andVCodeIsNotNull() {
            addCriterion("V_CODE is not null");return this;
        }

        public GeneratedCriteria andVCodeEqualTo(String value) {
            addCriterion("V_CODE =", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeNotEqualTo(String value) {
            addCriterion("V_CODE <>", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeGreaterThan(String value) {
            addCriterion("V_CODE >", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeGreaterThanOrEqualTo(String value) {
            addCriterion("V_CODE >=", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeLessThan(String value) {
            addCriterion("V_CODE <", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeLessThanOrEqualTo(String value) {
            addCriterion("V_CODE <=", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeLike(String value) {
            addCriterion("V_CODE like", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeNotLike(String value) {
            addCriterion("V_CODE not like", value, "vCode");return this;
        }

        public GeneratedCriteria andVCodeIn(List<String> values) {
            addCriterion("V_CODE in", values, "vCode");return this;
        }

        public GeneratedCriteria andVCodeNotIn(List<String> values) {
            addCriterion("V_CODE not in", values, "vCode");return this;
        }

        public GeneratedCriteria andVCodeBetween(String value1, String value2) {
            addCriterion("V_CODE between", value1, value2, "vCode");return this;
        }

        public GeneratedCriteria andVCodeNotBetween(String value1, String value2) {
            addCriterion("V_CODE not between", value1, value2, "vCode");return this;
        }

        public GeneratedCriteria andVValidDateIsNull() {
            addCriterion("V_VALID_DATE is null");return this;
        }

        public GeneratedCriteria andVValidDateIsNotNull() {
            addCriterion("V_VALID_DATE is not null");return this;
        }

        public GeneratedCriteria andVValidDateEqualTo(Date value) {
            addCriterion("V_VALID_DATE =", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateNotEqualTo(Date value) {
            addCriterion("V_VALID_DATE <>", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateGreaterThan(Date value) {
            addCriterion("V_VALID_DATE >", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateGreaterThanOrEqualTo(Date value) {
            addCriterion("V_VALID_DATE >=", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateLessThan(Date value) {
            addCriterion("V_VALID_DATE <", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateLessThanOrEqualTo(Date value) {
            addCriterion("V_VALID_DATE <=", value, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateIn(List<Date> values) {
            addCriterion("V_VALID_DATE in", values, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateNotIn(List<Date> values) {
            addCriterion("V_VALID_DATE not in", values, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateBetween(Date value1, Date value2) {
            addCriterion("V_VALID_DATE between", value1, value2, "vValidDate");return this;
        }

        public GeneratedCriteria andVValidDateNotBetween(Date value1, Date value2) {
            addCriterion("V_VALID_DATE not between", value1, value2, "vValidDate");return this;
        }

        public GeneratedCriteria andVSendDateIsNull() {
            addCriterion("V_SEND_DATE is null");return this;
        }

        public GeneratedCriteria andVSendDateIsNotNull() {
            addCriterion("V_SEND_DATE is not null");return this;
        }

        public GeneratedCriteria andVSendDateEqualTo(Date value) {
            addCriterion("V_SEND_DATE =", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateNotEqualTo(Date value) {
            addCriterion("V_SEND_DATE <>", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateGreaterThan(Date value) {
            addCriterion("V_SEND_DATE >", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateGreaterThanOrEqualTo(Date value) {
            addCriterion("V_SEND_DATE >=", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateLessThan(Date value) {
            addCriterion("V_SEND_DATE <", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateLessThanOrEqualTo(Date value) {
            addCriterion("V_SEND_DATE <=", value, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateIn(List<Date> values) {
            addCriterion("V_SEND_DATE in", values, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateNotIn(List<Date> values) {
            addCriterion("V_SEND_DATE not in", values, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateBetween(Date value1, Date value2) {
            addCriterion("V_SEND_DATE between", value1, value2, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendDateNotBetween(Date value1, Date value2) {
            addCriterion("V_SEND_DATE not between", value1, value2, "vSendDate");return this;
        }

        public GeneratedCriteria andVSendStrIsNull() {
            addCriterion("V_SEND_STR is null");return this;
        }

        public GeneratedCriteria andVSendStrIsNotNull() {
            addCriterion("V_SEND_STR is not null");return this;
        }

        public GeneratedCriteria andVSendStrEqualTo(String value) {
            addCriterion("V_SEND_STR =", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrNotEqualTo(String value) {
            addCriterion("V_SEND_STR <>", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrGreaterThan(String value) {
            addCriterion("V_SEND_STR >", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrGreaterThanOrEqualTo(String value) {
            addCriterion("V_SEND_STR >=", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrLessThan(String value) {
            addCriterion("V_SEND_STR <", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrLessThanOrEqualTo(String value) {
            addCriterion("V_SEND_STR <=", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrLike(String value) {
            addCriterion("V_SEND_STR like", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrNotLike(String value) {
            addCriterion("V_SEND_STR not like", value, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrIn(List<String> values) {
            addCriterion("V_SEND_STR in", values, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrNotIn(List<String> values) {
            addCriterion("V_SEND_STR not in", values, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrBetween(String value1, String value2) {
            addCriterion("V_SEND_STR between", value1, value2, "vSendStr");return this;
        }

        public GeneratedCriteria andVSendStrNotBetween(String value1, String value2) {
            addCriterion("V_SEND_STR not between", value1, value2, "vSendStr");return this;
        }

        public GeneratedCriteria andVTypeIsNull() {
            addCriterion("V_TYPE is null");return this;
        }

        public GeneratedCriteria andVTypeIsNotNull() {
            addCriterion("V_TYPE is not null");return this;
        }

        public GeneratedCriteria andVTypeEqualTo(Long value) {
            addCriterion("V_TYPE =", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeNotEqualTo(Long value) {
            addCriterion("V_TYPE <>", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeGreaterThan(Long value) {
            addCriterion("V_TYPE >", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("V_TYPE >=", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeLessThan(Long value) {
            addCriterion("V_TYPE <", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeLessThanOrEqualTo(Long value) {
            addCriterion("V_TYPE <=", value, "vType");return this;
        }

        public GeneratedCriteria andVTypeIn(List<Long> values) {
            addCriterion("V_TYPE in", values, "vType");return this;
        }

        public GeneratedCriteria andVTypeNotIn(List<Long> values) {
            addCriterion("V_TYPE not in", values, "vType");return this;
        }

        public GeneratedCriteria andVTypeBetween(Long value1, Long value2) {
            addCriterion("V_TYPE between", value1, value2, "vType");return this;
        }

        public GeneratedCriteria andVTypeNotBetween(Long value1, Long value2) {
            addCriterion("V_TYPE not between", value1, value2, "vType");return this;
        }

        public GeneratedCriteria andVActionIsNull() {
            addCriterion("V_ACTION is null");return this;
        }

        public GeneratedCriteria andVActionIsNotNull() {
            addCriterion("V_ACTION is not null");return this;
        }

        public GeneratedCriteria andVActionEqualTo(Long value) {
            addCriterion("V_ACTION =", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionNotEqualTo(Long value) {
            addCriterion("V_ACTION <>", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionGreaterThan(Long value) {
            addCriterion("V_ACTION >", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionGreaterThanOrEqualTo(Long value) {
            addCriterion("V_ACTION >=", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionLessThan(Long value) {
            addCriterion("V_ACTION <", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionLessThanOrEqualTo(Long value) {
            addCriterion("V_ACTION <=", value, "vAction");return this;
        }

        public GeneratedCriteria andVActionIn(List<Long> values) {
            addCriterion("V_ACTION in", values, "vAction");return this;
        }

        public GeneratedCriteria andVActionNotIn(List<Long> values) {
            addCriterion("V_ACTION not in", values, "vAction");return this;
        }

        public GeneratedCriteria andVActionBetween(Long value1, Long value2) {
            addCriterion("V_ACTION between", value1, value2, "vAction");return this;
        }

        public GeneratedCriteria andVActionNotBetween(Long value1, Long value2) {
            addCriterion("V_ACTION not between", value1, value2, "vAction");return this;
        }
    }
}