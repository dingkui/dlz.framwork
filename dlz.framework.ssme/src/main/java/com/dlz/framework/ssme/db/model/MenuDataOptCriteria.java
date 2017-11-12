package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.MenuDataOptCriteria.GeneratedCriteria;
public class MenuDataOptCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public MenuDataOptCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andMdoIdIsNull() {
            addCriterion("MDO_ID is null");return this;
        }

        public GeneratedCriteria andMdoIdIsNotNull() {
            addCriterion("MDO_ID is not null");return this;
        }

        public GeneratedCriteria andMdoIdEqualTo(Long value) {
            addCriterion("MDO_ID =", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdNotEqualTo(Long value) {
            addCriterion("MDO_ID <>", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdGreaterThan(Long value) {
            addCriterion("MDO_ID >", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MDO_ID >=", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdLessThan(Long value) {
            addCriterion("MDO_ID <", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdLessThanOrEqualTo(Long value) {
            addCriterion("MDO_ID <=", value, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdIn(List<Long> values) {
            addCriterion("MDO_ID in", values, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdNotIn(List<Long> values) {
            addCriterion("MDO_ID not in", values, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdBetween(Long value1, Long value2) {
            addCriterion("MDO_ID between", value1, value2, "mdoId");return this;
        }

        public GeneratedCriteria andMdoIdNotBetween(Long value1, Long value2) {
            addCriterion("MDO_ID not between", value1, value2, "mdoId");return this;
        }

        public GeneratedCriteria andMemuIdIsNull() {
            addCriterion("MEMU_ID is null");return this;
        }

        public GeneratedCriteria andMemuIdIsNotNull() {
            addCriterion("MEMU_ID is not null");return this;
        }

        public GeneratedCriteria andMemuIdEqualTo(Long value) {
            addCriterion("MEMU_ID =", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdNotEqualTo(Long value) {
            addCriterion("MEMU_ID <>", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdGreaterThan(Long value) {
            addCriterion("MEMU_ID >", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdGreaterThanOrEqualTo(Long value) {
            addCriterion("MEMU_ID >=", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdLessThan(Long value) {
            addCriterion("MEMU_ID <", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdLessThanOrEqualTo(Long value) {
            addCriterion("MEMU_ID <=", value, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdIn(List<Long> values) {
            addCriterion("MEMU_ID in", values, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdNotIn(List<Long> values) {
            addCriterion("MEMU_ID not in", values, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdBetween(Long value1, Long value2) {
            addCriterion("MEMU_ID between", value1, value2, "memuId");return this;
        }

        public GeneratedCriteria andMemuIdNotBetween(Long value1, Long value2) {
            addCriterion("MEMU_ID not between", value1, value2, "memuId");return this;
        }

        public GeneratedCriteria andRoleIdsIsNull() {
            addCriterion("ROLE_IDS is null");return this;
        }

        public GeneratedCriteria andRoleIdsIsNotNull() {
            addCriterion("ROLE_IDS is not null");return this;
        }

        public GeneratedCriteria andRoleIdsEqualTo(String value) {
            addCriterion("ROLE_IDS =", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsNotEqualTo(String value) {
            addCriterion("ROLE_IDS <>", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsGreaterThan(String value) {
            addCriterion("ROLE_IDS >", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_IDS >=", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsLessThan(String value) {
            addCriterion("ROLE_IDS <", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsLessThanOrEqualTo(String value) {
            addCriterion("ROLE_IDS <=", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsLike(String value) {
            addCriterion("ROLE_IDS like", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsNotLike(String value) {
            addCriterion("ROLE_IDS not like", value, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsIn(List<String> values) {
            addCriterion("ROLE_IDS in", values, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsNotIn(List<String> values) {
            addCriterion("ROLE_IDS not in", values, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsBetween(String value1, String value2) {
            addCriterion("ROLE_IDS between", value1, value2, "roleIds");return this;
        }

        public GeneratedCriteria andRoleIdsNotBetween(String value1, String value2) {
            addCriterion("ROLE_IDS not between", value1, value2, "roleIds");return this;
        }

        public GeneratedCriteria andPrevStatusIsNull() {
            addCriterion("PREV_STATUS is null");return this;
        }

        public GeneratedCriteria andPrevStatusIsNotNull() {
            addCriterion("PREV_STATUS is not null");return this;
        }

        public GeneratedCriteria andPrevStatusEqualTo(String value) {
            addCriterion("PREV_STATUS =", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusNotEqualTo(String value) {
            addCriterion("PREV_STATUS <>", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusGreaterThan(String value) {
            addCriterion("PREV_STATUS >", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusGreaterThanOrEqualTo(String value) {
            addCriterion("PREV_STATUS >=", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusLessThan(String value) {
            addCriterion("PREV_STATUS <", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusLessThanOrEqualTo(String value) {
            addCriterion("PREV_STATUS <=", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusLike(String value) {
            addCriterion("PREV_STATUS like", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusNotLike(String value) {
            addCriterion("PREV_STATUS not like", value, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusIn(List<String> values) {
            addCriterion("PREV_STATUS in", values, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusNotIn(List<String> values) {
            addCriterion("PREV_STATUS not in", values, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusBetween(String value1, String value2) {
            addCriterion("PREV_STATUS between", value1, value2, "prevStatus");return this;
        }

        public GeneratedCriteria andPrevStatusNotBetween(String value1, String value2) {
            addCriterion("PREV_STATUS not between", value1, value2, "prevStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusIsNull() {
            addCriterion("CURRENT_STATUS is null");return this;
        }

        public GeneratedCriteria andCurrentStatusIsNotNull() {
            addCriterion("CURRENT_STATUS is not null");return this;
        }

        public GeneratedCriteria andCurrentStatusEqualTo(String value) {
            addCriterion("CURRENT_STATUS =", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusNotEqualTo(String value) {
            addCriterion("CURRENT_STATUS <>", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusGreaterThan(String value) {
            addCriterion("CURRENT_STATUS >", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusGreaterThanOrEqualTo(String value) {
            addCriterion("CURRENT_STATUS >=", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusLessThan(String value) {
            addCriterion("CURRENT_STATUS <", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusLessThanOrEqualTo(String value) {
            addCriterion("CURRENT_STATUS <=", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusLike(String value) {
            addCriterion("CURRENT_STATUS like", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusNotLike(String value) {
            addCriterion("CURRENT_STATUS not like", value, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusIn(List<String> values) {
            addCriterion("CURRENT_STATUS in", values, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusNotIn(List<String> values) {
            addCriterion("CURRENT_STATUS not in", values, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusBetween(String value1, String value2) {
            addCriterion("CURRENT_STATUS between", value1, value2, "currentStatus");return this;
        }

        public GeneratedCriteria andCurrentStatusNotBetween(String value1, String value2) {
            addCriterion("CURRENT_STATUS not between", value1, value2, "currentStatus");return this;
        }

        public GeneratedCriteria andNextStatusIsNull() {
            addCriterion("NEXT_STATUS is null");return this;
        }

        public GeneratedCriteria andNextStatusIsNotNull() {
            addCriterion("NEXT_STATUS is not null");return this;
        }

        public GeneratedCriteria andNextStatusEqualTo(String value) {
            addCriterion("NEXT_STATUS =", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusNotEqualTo(String value) {
            addCriterion("NEXT_STATUS <>", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusGreaterThan(String value) {
            addCriterion("NEXT_STATUS >", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusGreaterThanOrEqualTo(String value) {
            addCriterion("NEXT_STATUS >=", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusLessThan(String value) {
            addCriterion("NEXT_STATUS <", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusLessThanOrEqualTo(String value) {
            addCriterion("NEXT_STATUS <=", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusLike(String value) {
            addCriterion("NEXT_STATUS like", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusNotLike(String value) {
            addCriterion("NEXT_STATUS not like", value, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusIn(List<String> values) {
            addCriterion("NEXT_STATUS in", values, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusNotIn(List<String> values) {
            addCriterion("NEXT_STATUS not in", values, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusBetween(String value1, String value2) {
            addCriterion("NEXT_STATUS between", value1, value2, "nextStatus");return this;
        }

        public GeneratedCriteria andNextStatusNotBetween(String value1, String value2) {
            addCriterion("NEXT_STATUS not between", value1, value2, "nextStatus");return this;
        }

        public GeneratedCriteria andExt1IsNull() {
            addCriterion("EXT1 is null");return this;
        }

        public GeneratedCriteria andExt1IsNotNull() {
            addCriterion("EXT1 is not null");return this;
        }

        public GeneratedCriteria andExt1EqualTo(String value) {
            addCriterion("EXT1 =", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1NotEqualTo(String value) {
            addCriterion("EXT1 <>", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1GreaterThan(String value) {
            addCriterion("EXT1 >", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1GreaterThanOrEqualTo(String value) {
            addCriterion("EXT1 >=", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1LessThan(String value) {
            addCriterion("EXT1 <", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1LessThanOrEqualTo(String value) {
            addCriterion("EXT1 <=", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1Like(String value) {
            addCriterion("EXT1 like", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1NotLike(String value) {
            addCriterion("EXT1 not like", value, "ext1");return this;
        }

        public GeneratedCriteria andExt1In(List<String> values) {
            addCriterion("EXT1 in", values, "ext1");return this;
        }

        public GeneratedCriteria andExt1NotIn(List<String> values) {
            addCriterion("EXT1 not in", values, "ext1");return this;
        }

        public GeneratedCriteria andExt1Between(String value1, String value2) {
            addCriterion("EXT1 between", value1, value2, "ext1");return this;
        }

        public GeneratedCriteria andExt1NotBetween(String value1, String value2) {
            addCriterion("EXT1 not between", value1, value2, "ext1");return this;
        }

        public GeneratedCriteria andExt2IsNull() {
            addCriterion("EXT2 is null");return this;
        }

        public GeneratedCriteria andExt2IsNotNull() {
            addCriterion("EXT2 is not null");return this;
        }

        public GeneratedCriteria andExt2EqualTo(String value) {
            addCriterion("EXT2 =", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2NotEqualTo(String value) {
            addCriterion("EXT2 <>", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2GreaterThan(String value) {
            addCriterion("EXT2 >", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2GreaterThanOrEqualTo(String value) {
            addCriterion("EXT2 >=", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2LessThan(String value) {
            addCriterion("EXT2 <", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2LessThanOrEqualTo(String value) {
            addCriterion("EXT2 <=", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2Like(String value) {
            addCriterion("EXT2 like", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2NotLike(String value) {
            addCriterion("EXT2 not like", value, "ext2");return this;
        }

        public GeneratedCriteria andExt2In(List<String> values) {
            addCriterion("EXT2 in", values, "ext2");return this;
        }

        public GeneratedCriteria andExt2NotIn(List<String> values) {
            addCriterion("EXT2 not in", values, "ext2");return this;
        }

        public GeneratedCriteria andExt2Between(String value1, String value2) {
            addCriterion("EXT2 between", value1, value2, "ext2");return this;
        }

        public GeneratedCriteria andExt2NotBetween(String value1, String value2) {
            addCriterion("EXT2 not between", value1, value2, "ext2");return this;
        }

        public GeneratedCriteria andExt3IsNull() {
            addCriterion("EXT3 is null");return this;
        }

        public GeneratedCriteria andExt3IsNotNull() {
            addCriterion("EXT3 is not null");return this;
        }

        public GeneratedCriteria andExt3EqualTo(String value) {
            addCriterion("EXT3 =", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3NotEqualTo(String value) {
            addCriterion("EXT3 <>", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3GreaterThan(String value) {
            addCriterion("EXT3 >", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3GreaterThanOrEqualTo(String value) {
            addCriterion("EXT3 >=", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3LessThan(String value) {
            addCriterion("EXT3 <", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3LessThanOrEqualTo(String value) {
            addCriterion("EXT3 <=", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3Like(String value) {
            addCriterion("EXT3 like", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3NotLike(String value) {
            addCriterion("EXT3 not like", value, "ext3");return this;
        }

        public GeneratedCriteria andExt3In(List<String> values) {
            addCriterion("EXT3 in", values, "ext3");return this;
        }

        public GeneratedCriteria andExt3NotIn(List<String> values) {
            addCriterion("EXT3 not in", values, "ext3");return this;
        }

        public GeneratedCriteria andExt3Between(String value1, String value2) {
            addCriterion("EXT3 between", value1, value2, "ext3");return this;
        }

        public GeneratedCriteria andExt3NotBetween(String value1, String value2) {
            addCriterion("EXT3 not between", value1, value2, "ext3");return this;
        }
    }
}