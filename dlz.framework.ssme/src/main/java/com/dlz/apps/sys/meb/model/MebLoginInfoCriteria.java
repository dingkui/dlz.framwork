package com.dlz.apps.sys.meb.model;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.apps.sys.meb.model.MebLoginInfoCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MebLoginInfoCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MebLoginInfoCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andMlIdIsNull() {
            addCriterion("ML_ID is null");return this;
        }

        public GeneratedCriteria andMlIdIsNotNull() {
            addCriterion("ML_ID is not null");return this;
        }

        public GeneratedCriteria andMlIdEqualTo(Long value) {
            addCriterion("ML_ID =", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdNotEqualTo(Long value) {
            addCriterion("ML_ID <>", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdGreaterThan(Long value) {
            addCriterion("ML_ID >", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ML_ID >=", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdLessThan(Long value) {
            addCriterion("ML_ID <", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdLessThanOrEqualTo(Long value) {
            addCriterion("ML_ID <=", value, "mlId");return this;
        }

        public GeneratedCriteria andMlIdIn(List<Long> values) {
            addCriterion("ML_ID in", values, "mlId");return this;
        }

        public GeneratedCriteria andMlIdNotIn(List<Long> values) {
            addCriterion("ML_ID not in", values, "mlId");return this;
        }

        public GeneratedCriteria andMlIdBetween(Long value1, Long value2) {
            addCriterion("ML_ID between", value1, value2, "mlId");return this;
        }

        public GeneratedCriteria andMlIdNotBetween(Long value1, Long value2) {
            addCriterion("ML_ID not between", value1, value2, "mlId");return this;
        }

        public GeneratedCriteria andMlMidIsNull() {
            addCriterion("ML_MID is null");return this;
        }

        public GeneratedCriteria andMlMidIsNotNull() {
            addCriterion("ML_MID is not null");return this;
        }

        public GeneratedCriteria andMlMidEqualTo(Long value) {
            addCriterion("ML_MID =", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidNotEqualTo(Long value) {
            addCriterion("ML_MID <>", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidGreaterThan(Long value) {
            addCriterion("ML_MID >", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidGreaterThanOrEqualTo(Long value) {
            addCriterion("ML_MID >=", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidLessThan(Long value) {
            addCriterion("ML_MID <", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidLessThanOrEqualTo(Long value) {
            addCriterion("ML_MID <=", value, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidIn(List<Long> values) {
            addCriterion("ML_MID in", values, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidNotIn(List<Long> values) {
            addCriterion("ML_MID not in", values, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidBetween(Long value1, Long value2) {
            addCriterion("ML_MID between", value1, value2, "mlMid");return this;
        }

        public GeneratedCriteria andMlMidNotBetween(Long value1, Long value2) {
            addCriterion("ML_MID not between", value1, value2, "mlMid");return this;
        }

        public GeneratedCriteria andMlTimeIsNull() {
            addCriterion("ML_TIME is null");return this;
        }

        public GeneratedCriteria andMlTimeIsNotNull() {
            addCriterion("ML_TIME is not null");return this;
        }

        public GeneratedCriteria andMlTimeEqualTo(Date value) {
            addCriterion("ML_TIME =", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeNotEqualTo(Date value) {
            addCriterion("ML_TIME <>", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeGreaterThan(Date value) {
            addCriterion("ML_TIME >", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ML_TIME >=", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeLessThan(Date value) {
            addCriterion("ML_TIME <", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeLessThanOrEqualTo(Date value) {
            addCriterion("ML_TIME <=", value, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeIn(List<Date> values) {
            addCriterion("ML_TIME in", values, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeNotIn(List<Date> values) {
            addCriterion("ML_TIME not in", values, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeBetween(Date value1, Date value2) {
            addCriterion("ML_TIME between", value1, value2, "mlTime");return this;
        }

        public GeneratedCriteria andMlTimeNotBetween(Date value1, Date value2) {
            addCriterion("ML_TIME not between", value1, value2, "mlTime");return this;
        }

        public GeneratedCriteria andMlIpIsNull() {
            addCriterion("ML_IP is null");return this;
        }

        public GeneratedCriteria andMlIpIsNotNull() {
            addCriterion("ML_IP is not null");return this;
        }

        public GeneratedCriteria andMlIpEqualTo(String value) {
            addCriterion("ML_IP =", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpNotEqualTo(String value) {
            addCriterion("ML_IP <>", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpGreaterThan(String value) {
            addCriterion("ML_IP >", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpGreaterThanOrEqualTo(String value) {
            addCriterion("ML_IP >=", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpLessThan(String value) {
            addCriterion("ML_IP <", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpLessThanOrEqualTo(String value) {
            addCriterion("ML_IP <=", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpLike(String value) {
            addCriterion("ML_IP like", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpNotLike(String value) {
            addCriterion("ML_IP not like", value, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpIn(List<String> values) {
            addCriterion("ML_IP in", values, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpNotIn(List<String> values) {
            addCriterion("ML_IP not in", values, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpBetween(String value1, String value2) {
            addCriterion("ML_IP between", value1, value2, "mlIp");return this;
        }

        public GeneratedCriteria andMlIpNotBetween(String value1, String value2) {
            addCriterion("ML_IP not between", value1, value2, "mlIp");return this;
        }

        public GeneratedCriteria andMlPointIsNull() {
            addCriterion("ML_POINT is null");return this;
        }

        public GeneratedCriteria andMlPointIsNotNull() {
            addCriterion("ML_POINT is not null");return this;
        }

        public GeneratedCriteria andMlPointEqualTo(String value) {
            addCriterion("ML_POINT =", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointNotEqualTo(String value) {
            addCriterion("ML_POINT <>", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointGreaterThan(String value) {
            addCriterion("ML_POINT >", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointGreaterThanOrEqualTo(String value) {
            addCriterion("ML_POINT >=", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointLessThan(String value) {
            addCriterion("ML_POINT <", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointLessThanOrEqualTo(String value) {
            addCriterion("ML_POINT <=", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointLike(String value) {
            addCriterion("ML_POINT like", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointNotLike(String value) {
            addCriterion("ML_POINT not like", value, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointIn(List<String> values) {
            addCriterion("ML_POINT in", values, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointNotIn(List<String> values) {
            addCriterion("ML_POINT not in", values, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointBetween(String value1, String value2) {
            addCriterion("ML_POINT between", value1, value2, "mlPoint");return this;
        }

        public GeneratedCriteria andMlPointNotBetween(String value1, String value2) {
            addCriterion("ML_POINT not between", value1, value2, "mlPoint");return this;
        }

        public GeneratedCriteria andMlArerIsNull() {
            addCriterion("ML_ARER is null");return this;
        }

        public GeneratedCriteria andMlArerIsNotNull() {
            addCriterion("ML_ARER is not null");return this;
        }

        public GeneratedCriteria andMlArerEqualTo(String value) {
            addCriterion("ML_ARER =", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerNotEqualTo(String value) {
            addCriterion("ML_ARER <>", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerGreaterThan(String value) {
            addCriterion("ML_ARER >", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerGreaterThanOrEqualTo(String value) {
            addCriterion("ML_ARER >=", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerLessThan(String value) {
            addCriterion("ML_ARER <", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerLessThanOrEqualTo(String value) {
            addCriterion("ML_ARER <=", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerLike(String value) {
            addCriterion("ML_ARER like", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerNotLike(String value) {
            addCriterion("ML_ARER not like", value, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerIn(List<String> values) {
            addCriterion("ML_ARER in", values, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerNotIn(List<String> values) {
            addCriterion("ML_ARER not in", values, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerBetween(String value1, String value2) {
            addCriterion("ML_ARER between", value1, value2, "mlArer");return this;
        }

        public GeneratedCriteria andMlArerNotBetween(String value1, String value2) {
            addCriterion("ML_ARER not between", value1, value2, "mlArer");return this;
        }

        public GeneratedCriteria andMlTypeIsNull() {
            addCriterion("ML_TYPE is null");return this;
        }

        public GeneratedCriteria andMlTypeIsNotNull() {
            addCriterion("ML_TYPE is not null");return this;
        }

        public GeneratedCriteria andMlTypeEqualTo(Long value) {
            addCriterion("ML_TYPE =", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeNotEqualTo(Long value) {
            addCriterion("ML_TYPE <>", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeGreaterThan(Long value) {
            addCriterion("ML_TYPE >", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeGreaterThanOrEqualTo(Long value) {
            addCriterion("ML_TYPE >=", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeLessThan(Long value) {
            addCriterion("ML_TYPE <", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeLessThanOrEqualTo(Long value) {
            addCriterion("ML_TYPE <=", value, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeIn(List<Long> values) {
            addCriterion("ML_TYPE in", values, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeNotIn(List<Long> values) {
            addCriterion("ML_TYPE not in", values, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeBetween(Long value1, Long value2) {
            addCriterion("ML_TYPE between", value1, value2, "mlType");return this;
        }

        public GeneratedCriteria andMlTypeNotBetween(Long value1, Long value2) {
            addCriterion("ML_TYPE not between", value1, value2, "mlType");return this;
        }

        public GeneratedCriteria andMlChanelIsNull() {
            addCriterion("ML_CHANEL is null");return this;
        }

        public GeneratedCriteria andMlChanelIsNotNull() {
            addCriterion("ML_CHANEL is not null");return this;
        }

        public GeneratedCriteria andMlChanelEqualTo(Long value) {
            addCriterion("ML_CHANEL =", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelNotEqualTo(Long value) {
            addCriterion("ML_CHANEL <>", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelGreaterThan(Long value) {
            addCriterion("ML_CHANEL >", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelGreaterThanOrEqualTo(Long value) {
            addCriterion("ML_CHANEL >=", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelLessThan(Long value) {
            addCriterion("ML_CHANEL <", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelLessThanOrEqualTo(Long value) {
            addCriterion("ML_CHANEL <=", value, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelIn(List<Long> values) {
            addCriterion("ML_CHANEL in", values, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelNotIn(List<Long> values) {
            addCriterion("ML_CHANEL not in", values, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelBetween(Long value1, Long value2) {
            addCriterion("ML_CHANEL between", value1, value2, "mlChanel");return this;
        }

        public GeneratedCriteria andMlChanelNotBetween(Long value1, Long value2) {
            addCriterion("ML_CHANEL not between", value1, value2, "mlChanel");return this;
        }
    }
}