package com.dlz.commbiz.sys.meb.model;

import com.dlz.common.base.criteria.BaseCriteria;
import com.dlz.common.base.criteria.BaseGeneratedCriteria;
import com.dlz.commbiz.sys.meb.model.MebAccDetailCriteria.GeneratedCriteria;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MebAccDetailCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MebAccDetailCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andAIdIsNull() {
            addCriterion("A_ID is null");return this;
        }

        public GeneratedCriteria andAIdIsNotNull() {
            addCriterion("A_ID is not null");return this;
        }

        public GeneratedCriteria andAIdEqualTo(Long value) {
            addCriterion("A_ID =", value, "aId");return this;
        }

        public GeneratedCriteria andAIdNotEqualTo(Long value) {
            addCriterion("A_ID <>", value, "aId");return this;
        }

        public GeneratedCriteria andAIdGreaterThan(Long value) {
            addCriterion("A_ID >", value, "aId");return this;
        }

        public GeneratedCriteria andAIdGreaterThanOrEqualTo(Long value) {
            addCriterion("A_ID >=", value, "aId");return this;
        }

        public GeneratedCriteria andAIdLessThan(Long value) {
            addCriterion("A_ID <", value, "aId");return this;
        }

        public GeneratedCriteria andAIdLessThanOrEqualTo(Long value) {
            addCriterion("A_ID <=", value, "aId");return this;
        }

        public GeneratedCriteria andAIdIn(List<Long> values) {
            addCriterion("A_ID in", values, "aId");return this;
        }

        public GeneratedCriteria andAIdNotIn(List<Long> values) {
            addCriterion("A_ID not in", values, "aId");return this;
        }

        public GeneratedCriteria andAIdBetween(Long value1, Long value2) {
            addCriterion("A_ID between", value1, value2, "aId");return this;
        }

        public GeneratedCriteria andAIdNotBetween(Long value1, Long value2) {
            addCriterion("A_ID not between", value1, value2, "aId");return this;
        }

        public GeneratedCriteria andAOidIsNull() {
            addCriterion("A_OID is null");return this;
        }

        public GeneratedCriteria andAOidIsNotNull() {
            addCriterion("A_OID is not null");return this;
        }

        public GeneratedCriteria andAOidEqualTo(String value) {
            addCriterion("A_OID =", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidNotEqualTo(String value) {
            addCriterion("A_OID <>", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidGreaterThan(String value) {
            addCriterion("A_OID >", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidGreaterThanOrEqualTo(String value) {
            addCriterion("A_OID >=", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidLessThan(String value) {
            addCriterion("A_OID <", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidLessThanOrEqualTo(String value) {
            addCriterion("A_OID <=", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidLike(String value) {
            addCriterion("A_OID like", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidNotLike(String value) {
            addCriterion("A_OID not like", value, "aOid");return this;
        }

        public GeneratedCriteria andAOidIn(List<String> values) {
            addCriterion("A_OID in", values, "aOid");return this;
        }

        public GeneratedCriteria andAOidNotIn(List<String> values) {
            addCriterion("A_OID not in", values, "aOid");return this;
        }

        public GeneratedCriteria andAOidBetween(String value1, String value2) {
            addCriterion("A_OID between", value1, value2, "aOid");return this;
        }

        public GeneratedCriteria andAOidNotBetween(String value1, String value2) {
            addCriterion("A_OID not between", value1, value2, "aOid");return this;
        }

        public GeneratedCriteria andAOpdateIsNull() {
            addCriterion("A_OPDATE is null");return this;
        }

        public GeneratedCriteria andAOpdateIsNotNull() {
            addCriterion("A_OPDATE is not null");return this;
        }

        public GeneratedCriteria andAOpdateEqualTo(Date value) {
            addCriterion("A_OPDATE =", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateNotEqualTo(Date value) {
            addCriterion("A_OPDATE <>", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateGreaterThan(Date value) {
            addCriterion("A_OPDATE >", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateGreaterThanOrEqualTo(Date value) {
            addCriterion("A_OPDATE >=", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateLessThan(Date value) {
            addCriterion("A_OPDATE <", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateLessThanOrEqualTo(Date value) {
            addCriterion("A_OPDATE <=", value, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateIn(List<Date> values) {
            addCriterion("A_OPDATE in", values, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateNotIn(List<Date> values) {
            addCriterion("A_OPDATE not in", values, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateBetween(Date value1, Date value2) {
            addCriterion("A_OPDATE between", value1, value2, "aOpdate");return this;
        }

        public GeneratedCriteria andAOpdateNotBetween(Date value1, Date value2) {
            addCriterion("A_OPDATE not between", value1, value2, "aOpdate");return this;
        }

        public GeneratedCriteria andAMidFromIsNull() {
            addCriterion("A_MID_FROM is null");return this;
        }

        public GeneratedCriteria andAMidFromIsNotNull() {
            addCriterion("A_MID_FROM is not null");return this;
        }

        public GeneratedCriteria andAMidFromEqualTo(Long value) {
            addCriterion("A_MID_FROM =", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromNotEqualTo(Long value) {
            addCriterion("A_MID_FROM <>", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromGreaterThan(Long value) {
            addCriterion("A_MID_FROM >", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromGreaterThanOrEqualTo(Long value) {
            addCriterion("A_MID_FROM >=", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromLessThan(Long value) {
            addCriterion("A_MID_FROM <", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromLessThanOrEqualTo(Long value) {
            addCriterion("A_MID_FROM <=", value, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromIn(List<Long> values) {
            addCriterion("A_MID_FROM in", values, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromNotIn(List<Long> values) {
            addCriterion("A_MID_FROM not in", values, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromBetween(Long value1, Long value2) {
            addCriterion("A_MID_FROM between", value1, value2, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidFromNotBetween(Long value1, Long value2) {
            addCriterion("A_MID_FROM not between", value1, value2, "aMidFrom");return this;
        }

        public GeneratedCriteria andAMidToIsNull() {
            addCriterion("A_MID_TO is null");return this;
        }

        public GeneratedCriteria andAMidToIsNotNull() {
            addCriterion("A_MID_TO is not null");return this;
        }

        public GeneratedCriteria andAMidToEqualTo(Long value) {
            addCriterion("A_MID_TO =", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToNotEqualTo(Long value) {
            addCriterion("A_MID_TO <>", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToGreaterThan(Long value) {
            addCriterion("A_MID_TO >", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToGreaterThanOrEqualTo(Long value) {
            addCriterion("A_MID_TO >=", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToLessThan(Long value) {
            addCriterion("A_MID_TO <", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToLessThanOrEqualTo(Long value) {
            addCriterion("A_MID_TO <=", value, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToIn(List<Long> values) {
            addCriterion("A_MID_TO in", values, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToNotIn(List<Long> values) {
            addCriterion("A_MID_TO not in", values, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToBetween(Long value1, Long value2) {
            addCriterion("A_MID_TO between", value1, value2, "aMidTo");return this;
        }

        public GeneratedCriteria andAMidToNotBetween(Long value1, Long value2) {
            addCriterion("A_MID_TO not between", value1, value2, "aMidTo");return this;
        }

        public GeneratedCriteria andATypeIsNull() {
            addCriterion("A_TYPE is null");return this;
        }

        public GeneratedCriteria andATypeIsNotNull() {
            addCriterion("A_TYPE is not null");return this;
        }

        public GeneratedCriteria andATypeEqualTo(String value) {
            addCriterion("A_TYPE =", value, "aType");return this;
        }

        public GeneratedCriteria andATypeNotEqualTo(String value) {
            addCriterion("A_TYPE <>", value, "aType");return this;
        }

        public GeneratedCriteria andATypeGreaterThan(String value) {
            addCriterion("A_TYPE >", value, "aType");return this;
        }

        public GeneratedCriteria andATypeGreaterThanOrEqualTo(String value) {
            addCriterion("A_TYPE >=", value, "aType");return this;
        }

        public GeneratedCriteria andATypeLessThan(String value) {
            addCriterion("A_TYPE <", value, "aType");return this;
        }

        public GeneratedCriteria andATypeLessThanOrEqualTo(String value) {
            addCriterion("A_TYPE <=", value, "aType");return this;
        }

        public GeneratedCriteria andATypeLike(String value) {
            addCriterion("A_TYPE like", value, "aType");return this;
        }

        public GeneratedCriteria andATypeNotLike(String value) {
            addCriterion("A_TYPE not like", value, "aType");return this;
        }

        public GeneratedCriteria andATypeIn(List<String> values) {
            addCriterion("A_TYPE in", values, "aType");return this;
        }

        public GeneratedCriteria andATypeNotIn(List<String> values) {
            addCriterion("A_TYPE not in", values, "aType");return this;
        }

        public GeneratedCriteria andATypeBetween(String value1, String value2) {
            addCriterion("A_TYPE between", value1, value2, "aType");return this;
        }

        public GeneratedCriteria andATypeNotBetween(String value1, String value2) {
            addCriterion("A_TYPE not between", value1, value2, "aType");return this;
        }

        public GeneratedCriteria andAAmountIsNull() {
            addCriterion("A_AMOUNT is null");return this;
        }

        public GeneratedCriteria andAAmountIsNotNull() {
            addCriterion("A_AMOUNT is not null");return this;
        }

        public GeneratedCriteria andAAmountEqualTo(Double value) {
            addCriterion("A_AMOUNT =", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountNotEqualTo(Double value) {
            addCriterion("A_AMOUNT <>", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountGreaterThan(Double value) {
            addCriterion("A_AMOUNT >", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountGreaterThanOrEqualTo(Double value) {
            addCriterion("A_AMOUNT >=", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountLessThan(Double value) {
            addCriterion("A_AMOUNT <", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountLessThanOrEqualTo(Double value) {
            addCriterion("A_AMOUNT <=", value, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountIn(List<Double> values) {
            addCriterion("A_AMOUNT in", values, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountNotIn(List<Double> values) {
            addCriterion("A_AMOUNT not in", values, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountBetween(Double value1, Double value2) {
            addCriterion("A_AMOUNT between", value1, value2, "aAmount");return this;
        }

        public GeneratedCriteria andAAmountNotBetween(Double value1, Double value2) {
            addCriterion("A_AMOUNT not between", value1, value2, "aAmount");return this;
        }

        public GeneratedCriteria andACardidIsNull() {
            addCriterion("A_CARDID is null");return this;
        }

        public GeneratedCriteria andACardidIsNotNull() {
            addCriterion("A_CARDID is not null");return this;
        }

        public GeneratedCriteria andACardidEqualTo(String value) {
            addCriterion("A_CARDID =", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidNotEqualTo(String value) {
            addCriterion("A_CARDID <>", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidGreaterThan(String value) {
            addCriterion("A_CARDID >", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidGreaterThanOrEqualTo(String value) {
            addCriterion("A_CARDID >=", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidLessThan(String value) {
            addCriterion("A_CARDID <", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidLessThanOrEqualTo(String value) {
            addCriterion("A_CARDID <=", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidLike(String value) {
            addCriterion("A_CARDID like", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidNotLike(String value) {
            addCriterion("A_CARDID not like", value, "aCardid");return this;
        }

        public GeneratedCriteria andACardidIn(List<String> values) {
            addCriterion("A_CARDID in", values, "aCardid");return this;
        }

        public GeneratedCriteria andACardidNotIn(List<String> values) {
            addCriterion("A_CARDID not in", values, "aCardid");return this;
        }

        public GeneratedCriteria andACardidBetween(String value1, String value2) {
            addCriterion("A_CARDID between", value1, value2, "aCardid");return this;
        }

        public GeneratedCriteria andACardidNotBetween(String value1, String value2) {
            addCriterion("A_CARDID not between", value1, value2, "aCardid");return this;
        }

        public GeneratedCriteria andAOtherOidIsNull() {
            addCriterion("A_OTHER_OID is null");return this;
        }

        public GeneratedCriteria andAOtherOidIsNotNull() {
            addCriterion("A_OTHER_OID is not null");return this;
        }

        public GeneratedCriteria andAOtherOidEqualTo(String value) {
            addCriterion("A_OTHER_OID =", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidNotEqualTo(String value) {
            addCriterion("A_OTHER_OID <>", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidGreaterThan(String value) {
            addCriterion("A_OTHER_OID >", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidGreaterThanOrEqualTo(String value) {
            addCriterion("A_OTHER_OID >=", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidLessThan(String value) {
            addCriterion("A_OTHER_OID <", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidLessThanOrEqualTo(String value) {
            addCriterion("A_OTHER_OID <=", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidLike(String value) {
            addCriterion("A_OTHER_OID like", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidNotLike(String value) {
            addCriterion("A_OTHER_OID not like", value, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidIn(List<String> values) {
            addCriterion("A_OTHER_OID in", values, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidNotIn(List<String> values) {
            addCriterion("A_OTHER_OID not in", values, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidBetween(String value1, String value2) {
            addCriterion("A_OTHER_OID between", value1, value2, "aOtherOid");return this;
        }

        public GeneratedCriteria andAOtherOidNotBetween(String value1, String value2) {
            addCriterion("A_OTHER_OID not between", value1, value2, "aOtherOid");return this;
        }

        public GeneratedCriteria andNoteIsNull() {
            addCriterion("NOTE is null");return this;
        }

        public GeneratedCriteria andNoteIsNotNull() {
            addCriterion("NOTE is not null");return this;
        }

        public GeneratedCriteria andNoteEqualTo(String value) {
            addCriterion("NOTE =", value, "note");return this;
        }

        public GeneratedCriteria andNoteNotEqualTo(String value) {
            addCriterion("NOTE <>", value, "note");return this;
        }

        public GeneratedCriteria andNoteGreaterThan(String value) {
            addCriterion("NOTE >", value, "note");return this;
        }

        public GeneratedCriteria andNoteGreaterThanOrEqualTo(String value) {
            addCriterion("NOTE >=", value, "note");return this;
        }

        public GeneratedCriteria andNoteLessThan(String value) {
            addCriterion("NOTE <", value, "note");return this;
        }

        public GeneratedCriteria andNoteLessThanOrEqualTo(String value) {
            addCriterion("NOTE <=", value, "note");return this;
        }

        public GeneratedCriteria andNoteLike(String value) {
            addCriterion("NOTE like", value, "note");return this;
        }

        public GeneratedCriteria andNoteNotLike(String value) {
            addCriterion("NOTE not like", value, "note");return this;
        }

        public GeneratedCriteria andNoteIn(List<String> values) {
            addCriterion("NOTE in", values, "note");return this;
        }

        public GeneratedCriteria andNoteNotIn(List<String> values) {
            addCriterion("NOTE not in", values, "note");return this;
        }

        public GeneratedCriteria andNoteBetween(String value1, String value2) {
            addCriterion("NOTE between", value1, value2, "note");return this;
        }

        public GeneratedCriteria andNoteNotBetween(String value1, String value2) {
            addCriterion("NOTE not between", value1, value2, "note");return this;
        }

        public GeneratedCriteria andAOfferIdIsNull() {
            addCriterion("A_OFFER_ID is null");return this;
        }

        public GeneratedCriteria andAOfferIdIsNotNull() {
            addCriterion("A_OFFER_ID is not null");return this;
        }

        public GeneratedCriteria andAOfferIdEqualTo(Long value) {
            addCriterion("A_OFFER_ID =", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdNotEqualTo(Long value) {
            addCriterion("A_OFFER_ID <>", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdGreaterThan(Long value) {
            addCriterion("A_OFFER_ID >", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdGreaterThanOrEqualTo(Long value) {
            addCriterion("A_OFFER_ID >=", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdLessThan(Long value) {
            addCriterion("A_OFFER_ID <", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdLessThanOrEqualTo(Long value) {
            addCriterion("A_OFFER_ID <=", value, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdIn(List<Long> values) {
            addCriterion("A_OFFER_ID in", values, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdNotIn(List<Long> values) {
            addCriterion("A_OFFER_ID not in", values, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdBetween(Long value1, Long value2) {
            addCriterion("A_OFFER_ID between", value1, value2, "aOfferId");return this;
        }

        public GeneratedCriteria andAOfferIdNotBetween(Long value1, Long value2) {
            addCriterion("A_OFFER_ID not between", value1, value2, "aOfferId");return this;
        }

        public GeneratedCriteria andAFlagIsNull() {
            addCriterion("A_FLAG is null");return this;
        }

        public GeneratedCriteria andAFlagIsNotNull() {
            addCriterion("A_FLAG is not null");return this;
        }

        public GeneratedCriteria andAFlagEqualTo(Long value) {
            addCriterion("A_FLAG =", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagNotEqualTo(Long value) {
            addCriterion("A_FLAG <>", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagGreaterThan(Long value) {
            addCriterion("A_FLAG >", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagGreaterThanOrEqualTo(Long value) {
            addCriterion("A_FLAG >=", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagLessThan(Long value) {
            addCriterion("A_FLAG <", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagLessThanOrEqualTo(Long value) {
            addCriterion("A_FLAG <=", value, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagIn(List<Long> values) {
            addCriterion("A_FLAG in", values, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagNotIn(List<Long> values) {
            addCriterion("A_FLAG not in", values, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagBetween(Long value1, Long value2) {
            addCriterion("A_FLAG between", value1, value2, "aFlag");return this;
        }

        public GeneratedCriteria andAFlagNotBetween(Long value1, Long value2) {
            addCriterion("A_FLAG not between", value1, value2, "aFlag");return this;
        }
    }
}