package com.dlz.apps.notice.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.dlz.apps.notice.model.AnnounceCriteria.GeneratedCriteria;
import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;

public class AnnounceCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public AnnounceCriteria() {
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

        public GeneratedCriteria andATitleIsNull() {
            addCriterion("A_TITLE is null");return this;
        }

        public GeneratedCriteria andATitleIsNotNull() {
            addCriterion("A_TITLE is not null");return this;
        }

        public GeneratedCriteria andATitleEqualTo(String value) {
            addCriterion("A_TITLE =", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleNotEqualTo(String value) {
            addCriterion("A_TITLE <>", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleGreaterThan(String value) {
            addCriterion("A_TITLE >", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleGreaterThanOrEqualTo(String value) {
            addCriterion("A_TITLE >=", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleLessThan(String value) {
            addCriterion("A_TITLE <", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleLessThanOrEqualTo(String value) {
            addCriterion("A_TITLE <=", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleLike(String value) {
            addCriterion("A_TITLE like", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleNotLike(String value) {
            addCriterion("A_TITLE not like", value, "aTitle");return this;
        }

        public GeneratedCriteria andATitleIn(List<String> values) {
            addCriterion("A_TITLE in", values, "aTitle");return this;
        }

        public GeneratedCriteria andATitleNotIn(List<String> values) {
            addCriterion("A_TITLE not in", values, "aTitle");return this;
        }

        public GeneratedCriteria andATitleBetween(String value1, String value2) {
            addCriterion("A_TITLE between", value1, value2, "aTitle");return this;
        }

        public GeneratedCriteria andATitleNotBetween(String value1, String value2) {
            addCriterion("A_TITLE not between", value1, value2, "aTitle");return this;
        }

        public GeneratedCriteria andAAuthorIsNull() {
            addCriterion("A_AUTHOR is null");return this;
        }

        public GeneratedCriteria andAAuthorIsNotNull() {
            addCriterion("A_AUTHOR is not null");return this;
        }

        public GeneratedCriteria andAAuthorEqualTo(String value) {
            addCriterion("A_AUTHOR =", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorNotEqualTo(String value) {
            addCriterion("A_AUTHOR <>", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorGreaterThan(String value) {
            addCriterion("A_AUTHOR >", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorGreaterThanOrEqualTo(String value) {
            addCriterion("A_AUTHOR >=", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorLessThan(String value) {
            addCriterion("A_AUTHOR <", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorLessThanOrEqualTo(String value) {
            addCriterion("A_AUTHOR <=", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorLike(String value) {
            addCriterion("A_AUTHOR like", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorNotLike(String value) {
            addCriterion("A_AUTHOR not like", value, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorIn(List<String> values) {
            addCriterion("A_AUTHOR in", values, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorNotIn(List<String> values) {
            addCriterion("A_AUTHOR not in", values, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorBetween(String value1, String value2) {
            addCriterion("A_AUTHOR between", value1, value2, "aAuthor");return this;
        }

        public GeneratedCriteria andAAuthorNotBetween(String value1, String value2) {
            addCriterion("A_AUTHOR not between", value1, value2, "aAuthor");return this;
        }

        public GeneratedCriteria andADateandtimeIsNull() {
            addCriterion("A_DATEANDTIME is null");return this;
        }

        public GeneratedCriteria andADateandtimeIsNotNull() {
            addCriterion("A_DATEANDTIME is not null");return this;
        }

        public GeneratedCriteria andADateandtimeEqualTo(Date value) {
            addCriterion("A_DATEANDTIME =", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeNotEqualTo(Date value) {
            addCriterion("A_DATEANDTIME <>", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeGreaterThan(Date value) {
            addCriterion("A_DATEANDTIME >", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("A_DATEANDTIME >=", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeLessThan(Date value) {
            addCriterion("A_DATEANDTIME <", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeLessThanOrEqualTo(Date value) {
            addCriterion("A_DATEANDTIME <=", value, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeIn(List<Date> values) {
            addCriterion("A_DATEANDTIME in", values, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeNotIn(List<Date> values) {
            addCriterion("A_DATEANDTIME not in", values, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeBetween(Date value1, Date value2) {
            addCriterion("A_DATEANDTIME between", value1, value2, "aDateandtime");return this;
        }

        public GeneratedCriteria andADateandtimeNotBetween(Date value1, Date value2) {
            addCriterion("A_DATEANDTIME not between", value1, value2, "aDateandtime");return this;
        }

        public GeneratedCriteria andAIsnewIsNull() {
            addCriterion("A_ISNEW is null");return this;
        }

        public GeneratedCriteria andAIsnewIsNotNull() {
            addCriterion("A_ISNEW is not null");return this;
        }

        public GeneratedCriteria andAIsnewEqualTo(Long value) {
            addCriterion("A_ISNEW =", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewNotEqualTo(Long value) {
            addCriterion("A_ISNEW <>", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewGreaterThan(Long value) {
            addCriterion("A_ISNEW >", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewGreaterThanOrEqualTo(Long value) {
            addCriterion("A_ISNEW >=", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewLessThan(Long value) {
            addCriterion("A_ISNEW <", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewLessThanOrEqualTo(Long value) {
            addCriterion("A_ISNEW <=", value, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewIn(List<Long> values) {
            addCriterion("A_ISNEW in", values, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewNotIn(List<Long> values) {
            addCriterion("A_ISNEW not in", values, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewBetween(Long value1, Long value2) {
            addCriterion("A_ISNEW between", value1, value2, "aIsnew");return this;
        }

        public GeneratedCriteria andAIsnewNotBetween(Long value1, Long value2) {
            addCriterion("A_ISNEW not between", value1, value2, "aIsnew");return this;
        }

        public GeneratedCriteria andAChannelidIsNull() {
            addCriterion("A_CHANNELID is null");return this;
        }

        public GeneratedCriteria andAChannelidIsNotNull() {
            addCriterion("A_CHANNELID is not null");return this;
        }

        public GeneratedCriteria andAChannelidEqualTo(Long value) {
            addCriterion("A_CHANNELID =", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidNotEqualTo(Long value) {
            addCriterion("A_CHANNELID <>", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidGreaterThan(Long value) {
            addCriterion("A_CHANNELID >", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidGreaterThanOrEqualTo(Long value) {
            addCriterion("A_CHANNELID >=", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidLessThan(Long value) {
            addCriterion("A_CHANNELID <", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidLessThanOrEqualTo(Long value) {
            addCriterion("A_CHANNELID <=", value, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidIn(List<Long> values) {
            addCriterion("A_CHANNELID in", values, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidNotIn(List<Long> values) {
            addCriterion("A_CHANNELID not in", values, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidBetween(Long value1, Long value2) {
            addCriterion("A_CHANNELID between", value1, value2, "aChannelid");return this;
        }

        public GeneratedCriteria andAChannelidNotBetween(Long value1, Long value2) {
            addCriterion("A_CHANNELID not between", value1, value2, "aChannelid");return this;
        }

        public GeneratedCriteria andAShowtypeIsNull() {
            addCriterion("A_SHOWTYPE is null");return this;
        }

        public GeneratedCriteria andAShowtypeIsNotNull() {
            addCriterion("A_SHOWTYPE is not null");return this;
        }

        public GeneratedCriteria andAShowtypeEqualTo(Long value) {
            addCriterion("A_SHOWTYPE =", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeNotEqualTo(Long value) {
            addCriterion("A_SHOWTYPE <>", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeGreaterThan(Long value) {
            addCriterion("A_SHOWTYPE >", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeGreaterThanOrEqualTo(Long value) {
            addCriterion("A_SHOWTYPE >=", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeLessThan(Long value) {
            addCriterion("A_SHOWTYPE <", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeLessThanOrEqualTo(Long value) {
            addCriterion("A_SHOWTYPE <=", value, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeIn(List<Long> values) {
            addCriterion("A_SHOWTYPE in", values, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeNotIn(List<Long> values) {
            addCriterion("A_SHOWTYPE not in", values, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeBetween(Long value1, Long value2) {
            addCriterion("A_SHOWTYPE between", value1, value2, "aShowtype");return this;
        }

        public GeneratedCriteria andAShowtypeNotBetween(Long value1, Long value2) {
            addCriterion("A_SHOWTYPE not between", value1, value2, "aShowtype");return this;
        }

        public GeneratedCriteria andAOuttimeIsNull() {
            addCriterion("A_OUTTIME is null");return this;
        }

        public GeneratedCriteria andAOuttimeIsNotNull() {
            addCriterion("A_OUTTIME is not null");return this;
        }

        public GeneratedCriteria andAOuttimeEqualTo(Long value) {
            addCriterion("A_OUTTIME =", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeNotEqualTo(Long value) {
            addCriterion("A_OUTTIME <>", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeGreaterThan(Long value) {
            addCriterion("A_OUTTIME >", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeGreaterThanOrEqualTo(Long value) {
            addCriterion("A_OUTTIME >=", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeLessThan(Long value) {
            addCriterion("A_OUTTIME <", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeLessThanOrEqualTo(Long value) {
            addCriterion("A_OUTTIME <=", value, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeIn(List<Long> values) {
            addCriterion("A_OUTTIME in", values, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeNotIn(List<Long> values) {
            addCriterion("A_OUTTIME not in", values, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeBetween(Long value1, Long value2) {
            addCriterion("A_OUTTIME between", value1, value2, "aOuttime");return this;
        }

        public GeneratedCriteria andAOuttimeNotBetween(Long value1, Long value2) {
            addCriterion("A_OUTTIME not between", value1, value2, "aOuttime");return this;
        }

        public GeneratedCriteria andASiteIsNull() {
            addCriterion("A_SITE is null");return this;
        }

        public GeneratedCriteria andASiteIsNotNull() {
            addCriterion("A_SITE is not null");return this;
        }

        public GeneratedCriteria andASiteEqualTo(Long value) {
            addCriterion("A_SITE =", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteNotEqualTo(Long value) {
            addCriterion("A_SITE <>", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteGreaterThan(Long value) {
            addCriterion("A_SITE >", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteGreaterThanOrEqualTo(Long value) {
            addCriterion("A_SITE >=", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteLessThan(Long value) {
            addCriterion("A_SITE <", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteLessThanOrEqualTo(Long value) {
            addCriterion("A_SITE <=", value, "aSite");return this;
        }

        public GeneratedCriteria andASiteIn(List<Long> values) {
            addCriterion("A_SITE in", values, "aSite");return this;
        }

        public GeneratedCriteria andASiteNotIn(List<Long> values) {
            addCriterion("A_SITE not in", values, "aSite");return this;
        }

        public GeneratedCriteria andASiteBetween(Long value1, Long value2) {
            addCriterion("A_SITE between", value1, value2, "aSite");return this;
        }

        public GeneratedCriteria andASiteNotBetween(Long value1, Long value2) {
            addCriterion("A_SITE not between", value1, value2, "aSite");return this;
        }

        public GeneratedCriteria andAContentIsNull() {
            addCriterion("A_CONTENT is null");return this;
        }

        public GeneratedCriteria andAContentIsNotNull() {
            addCriterion("A_CONTENT is not null");return this;
        }

        public GeneratedCriteria andAContentEqualTo(String value) {
            addCriterion("A_CONTENT =", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentNotEqualTo(String value) {
            addCriterion("A_CONTENT <>", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentGreaterThan(String value) {
            addCriterion("A_CONTENT >", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentGreaterThanOrEqualTo(String value) {
            addCriterion("A_CONTENT >=", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentLessThan(String value) {
            addCriterion("A_CONTENT <", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentLessThanOrEqualTo(String value) {
            addCriterion("A_CONTENT <=", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentLike(String value) {
            addCriterion("A_CONTENT like", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentNotLike(String value) {
            addCriterion("A_CONTENT not like", value, "aContent");return this;
        }

        public GeneratedCriteria andAContentIn(List<String> values) {
            addCriterion("A_CONTENT in", values, "aContent");return this;
        }

        public GeneratedCriteria andAContentNotIn(List<String> values) {
            addCriterion("A_CONTENT not in", values, "aContent");return this;
        }

        public GeneratedCriteria andAContentBetween(String value1, String value2) {
            addCriterion("A_CONTENT between", value1, value2, "aContent");return this;
        }

        public GeneratedCriteria andAContentNotBetween(String value1, String value2) {
            addCriterion("A_CONTENT not between", value1, value2, "aContent");return this;
        }
    }
}