package com.dlz.apps.sys.meb.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.sys.meb.model.MebLinkedCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MebLinkedCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MebLinkedCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andLIdIsNull() {
            addCriterion("L_ID is null");return this;
        }

        public GeneratedCriteria andLIdIsNotNull() {
            addCriterion("L_ID is not null");return this;
        }

        public GeneratedCriteria andLIdEqualTo(Long value) {
            addCriterion("L_ID =", value, "lId");return this;
        }

        public GeneratedCriteria andLIdNotEqualTo(Long value) {
            addCriterion("L_ID <>", value, "lId");return this;
        }

        public GeneratedCriteria andLIdGreaterThan(Long value) {
            addCriterion("L_ID >", value, "lId");return this;
        }

        public GeneratedCriteria andLIdGreaterThanOrEqualTo(Long value) {
            addCriterion("L_ID >=", value, "lId");return this;
        }

        public GeneratedCriteria andLIdLessThan(Long value) {
            addCriterion("L_ID <", value, "lId");return this;
        }

        public GeneratedCriteria andLIdLessThanOrEqualTo(Long value) {
            addCriterion("L_ID <=", value, "lId");return this;
        }

        public GeneratedCriteria andLIdIn(List<Long> values) {
            addCriterion("L_ID in", values, "lId");return this;
        }

        public GeneratedCriteria andLIdNotIn(List<Long> values) {
            addCriterion("L_ID not in", values, "lId");return this;
        }

        public GeneratedCriteria andLIdBetween(Long value1, Long value2) {
            addCriterion("L_ID between", value1, value2, "lId");return this;
        }

        public GeneratedCriteria andLIdNotBetween(Long value1, Long value2) {
            addCriterion("L_ID not between", value1, value2, "lId");return this;
        }

        public GeneratedCriteria andLMidIsNull() {
            addCriterion("L_MID is null");return this;
        }

        public GeneratedCriteria andLMidIsNotNull() {
            addCriterion("L_MID is not null");return this;
        }

        public GeneratedCriteria andLMidEqualTo(Long value) {
            addCriterion("L_MID =", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidNotEqualTo(Long value) {
            addCriterion("L_MID <>", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidGreaterThan(Long value) {
            addCriterion("L_MID >", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidGreaterThanOrEqualTo(Long value) {
            addCriterion("L_MID >=", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidLessThan(Long value) {
            addCriterion("L_MID <", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidLessThanOrEqualTo(Long value) {
            addCriterion("L_MID <=", value, "lMid");return this;
        }

        public GeneratedCriteria andLMidIn(List<Long> values) {
            addCriterion("L_MID in", values, "lMid");return this;
        }

        public GeneratedCriteria andLMidNotIn(List<Long> values) {
            addCriterion("L_MID not in", values, "lMid");return this;
        }

        public GeneratedCriteria andLMidBetween(Long value1, Long value2) {
            addCriterion("L_MID between", value1, value2, "lMid");return this;
        }

        public GeneratedCriteria andLMidNotBetween(Long value1, Long value2) {
            addCriterion("L_MID not between", value1, value2, "lMid");return this;
        }

        public GeneratedCriteria andLOpenidIsNull() {
            addCriterion("L_OPENID is null");return this;
        }

        public GeneratedCriteria andLOpenidIsNotNull() {
            addCriterion("L_OPENID is not null");return this;
        }

        public GeneratedCriteria andLOpenidEqualTo(String value) {
            addCriterion("L_OPENID =", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidNotEqualTo(String value) {
            addCriterion("L_OPENID <>", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidGreaterThan(String value) {
            addCriterion("L_OPENID >", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidGreaterThanOrEqualTo(String value) {
            addCriterion("L_OPENID >=", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidLessThan(String value) {
            addCriterion("L_OPENID <", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidLessThanOrEqualTo(String value) {
            addCriterion("L_OPENID <=", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidLike(String value) {
            addCriterion("L_OPENID like", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidNotLike(String value) {
            addCriterion("L_OPENID not like", value, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidIn(List<String> values) {
            addCriterion("L_OPENID in", values, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidNotIn(List<String> values) {
            addCriterion("L_OPENID not in", values, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidBetween(String value1, String value2) {
            addCriterion("L_OPENID between", value1, value2, "lOpenid");return this;
        }

        public GeneratedCriteria andLOpenidNotBetween(String value1, String value2) {
            addCriterion("L_OPENID not between", value1, value2, "lOpenid");return this;
        }

        public GeneratedCriteria andLTypeIsNull() {
            addCriterion("L_TYPE is null");return this;
        }

        public GeneratedCriteria andLTypeIsNotNull() {
            addCriterion("L_TYPE is not null");return this;
        }

        public GeneratedCriteria andLTypeEqualTo(Long value) {
            addCriterion("L_TYPE =", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeNotEqualTo(Long value) {
            addCriterion("L_TYPE <>", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeGreaterThan(Long value) {
            addCriterion("L_TYPE >", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("L_TYPE >=", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeLessThan(Long value) {
            addCriterion("L_TYPE <", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeLessThanOrEqualTo(Long value) {
            addCriterion("L_TYPE <=", value, "lType");return this;
        }

        public GeneratedCriteria andLTypeIn(List<Long> values) {
            addCriterion("L_TYPE in", values, "lType");return this;
        }

        public GeneratedCriteria andLTypeNotIn(List<Long> values) {
            addCriterion("L_TYPE not in", values, "lType");return this;
        }

        public GeneratedCriteria andLTypeBetween(Long value1, Long value2) {
            addCriterion("L_TYPE between", value1, value2, "lType");return this;
        }

        public GeneratedCriteria andLTypeNotBetween(Long value1, Long value2) {
            addCriterion("L_TYPE not between", value1, value2, "lType");return this;
        }

        public GeneratedCriteria andLTimestampIsNull() {
            addCriterion("L_TIMESTAMP is null");return this;
        }

        public GeneratedCriteria andLTimestampIsNotNull() {
            addCriterion("L_TIMESTAMP is not null");return this;
        }

        public GeneratedCriteria andLTimestampEqualTo(Date value) {
            addCriterion("L_TIMESTAMP =", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampNotEqualTo(Date value) {
            addCriterion("L_TIMESTAMP <>", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampGreaterThan(Date value) {
            addCriterion("L_TIMESTAMP >", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampGreaterThanOrEqualTo(Date value) {
            addCriterion("L_TIMESTAMP >=", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampLessThan(Date value) {
            addCriterion("L_TIMESTAMP <", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampLessThanOrEqualTo(Date value) {
            addCriterion("L_TIMESTAMP <=", value, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampIn(List<Date> values) {
            addCriterion("L_TIMESTAMP in", values, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampNotIn(List<Date> values) {
            addCriterion("L_TIMESTAMP not in", values, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampBetween(Date value1, Date value2) {
            addCriterion("L_TIMESTAMP between", value1, value2, "lTimestamp");return this;
        }

        public GeneratedCriteria andLTimestampNotBetween(Date value1, Date value2) {
            addCriterion("L_TIMESTAMP not between", value1, value2, "lTimestamp");return this;
        }

        public GeneratedCriteria andLFlagIsNull() {
            addCriterion("L_FLAG is null");return this;
        }

        public GeneratedCriteria andLFlagIsNotNull() {
            addCriterion("L_FLAG is not null");return this;
        }

        public GeneratedCriteria andLFlagEqualTo(Long value) {
            addCriterion("L_FLAG =", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagNotEqualTo(Long value) {
            addCriterion("L_FLAG <>", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagGreaterThan(Long value) {
            addCriterion("L_FLAG >", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("L_FLAG >=", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagLessThan(Long value) {
            addCriterion("L_FLAG <", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagLessThanOrEqualTo(Long value) {
            addCriterion("L_FLAG <=", value, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagIn(List<Long> values) {
            addCriterion("L_FLAG in", values, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagNotIn(List<Long> values) {
            addCriterion("L_FLAG not in", values, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagBetween(Long value1, Long value2) {
            addCriterion("L_FLAG between", value1, value2, "lFlag");return this;
        }

        public GeneratedCriteria andLFlagNotBetween(Long value1, Long value2) {
            addCriterion("L_FLAG not between", value1, value2, "lFlag");return this;
        }

        public GeneratedCriteria andLOpennameIsNull() {
            addCriterion("L_OPENNAME is null");return this;
        }

        public GeneratedCriteria andLOpennameIsNotNull() {
            addCriterion("L_OPENNAME is not null");return this;
        }

        public GeneratedCriteria andLOpennameEqualTo(String value) {
            addCriterion("L_OPENNAME =", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameNotEqualTo(String value) {
            addCriterion("L_OPENNAME <>", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameGreaterThan(String value) {
            addCriterion("L_OPENNAME >", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameGreaterThanOrEqualTo(String value) {
            addCriterion("L_OPENNAME >=", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameLessThan(String value) {
            addCriterion("L_OPENNAME <", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameLessThanOrEqualTo(String value) {
            addCriterion("L_OPENNAME <=", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameLike(String value) {
            addCriterion("L_OPENNAME like", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameNotLike(String value) {
            addCriterion("L_OPENNAME not like", value, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameIn(List<String> values) {
            addCriterion("L_OPENNAME in", values, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameNotIn(List<String> values) {
            addCriterion("L_OPENNAME not in", values, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameBetween(String value1, String value2) {
            addCriterion("L_OPENNAME between", value1, value2, "lOpenname");return this;
        }

        public GeneratedCriteria andLOpennameNotBetween(String value1, String value2) {
            addCriterion("L_OPENNAME not between", value1, value2, "lOpenname");return this;
        }
    }
}