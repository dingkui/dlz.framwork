package com.dlz.framework.ssme.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;
import com.dlz.framework.ssme.db.model.RoleCriteria.GeneratedCriteria;
public class RoleCriteria extends BaseCriteria<GeneratedCriteria> {

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public RoleCriteria() {
        super();
        oredCriteria = new ArrayList<GeneratedCriteria>();
    }

    public class GeneratedCriteria extends BaseGeneratedCriteria {

        protected GeneratedCriteria() {
            super();
        }

        public GeneratedCriteria andRoleIdIsNull() {
            addCriterion("ROLE_ID is null");return this;
        }

        public GeneratedCriteria andRoleIdIsNotNull() {
            addCriterion("ROLE_ID is not null");return this;
        }

        public GeneratedCriteria andRoleIdEqualTo(Long value) {
            addCriterion("ROLE_ID =", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdNotEqualTo(Long value) {
            addCriterion("ROLE_ID <>", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdGreaterThan(Long value) {
            addCriterion("ROLE_ID >", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdGreaterThanOrEqualTo(Long value) {
            addCriterion("ROLE_ID >=", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdLessThan(Long value) {
            addCriterion("ROLE_ID <", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdLessThanOrEqualTo(Long value) {
            addCriterion("ROLE_ID <=", value, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdIn(List<Long> values) {
            addCriterion("ROLE_ID in", values, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdNotIn(List<Long> values) {
            addCriterion("ROLE_ID not in", values, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdBetween(Long value1, Long value2) {
            addCriterion("ROLE_ID between", value1, value2, "roleId");return this;
        }

        public GeneratedCriteria andRoleIdNotBetween(Long value1, Long value2) {
            addCriterion("ROLE_ID not between", value1, value2, "roleId");return this;
        }

        public GeneratedCriteria andRoleNmIsNull() {
            addCriterion("ROLE_NM is null");return this;
        }

        public GeneratedCriteria andRoleNmIsNotNull() {
            addCriterion("ROLE_NM is not null");return this;
        }

        public GeneratedCriteria andRoleNmEqualTo(String value) {
            addCriterion("ROLE_NM =", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmNotEqualTo(String value) {
            addCriterion("ROLE_NM <>", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmGreaterThan(String value) {
            addCriterion("ROLE_NM >", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_NM >=", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmLessThan(String value) {
            addCriterion("ROLE_NM <", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmLessThanOrEqualTo(String value) {
            addCriterion("ROLE_NM <=", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmLike(String value) {
            addCriterion("ROLE_NM like", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmNotLike(String value) {
            addCriterion("ROLE_NM not like", value, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmIn(List<String> values) {
            addCriterion("ROLE_NM in", values, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmNotIn(List<String> values) {
            addCriterion("ROLE_NM not in", values, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmBetween(String value1, String value2) {
            addCriterion("ROLE_NM between", value1, value2, "roleNm");return this;
        }

        public GeneratedCriteria andRoleNmNotBetween(String value1, String value2) {
            addCriterion("ROLE_NM not between", value1, value2, "roleNm");return this;
        }

        public GeneratedCriteria andRoleDescIsNull() {
            addCriterion("ROLE_DESC is null");return this;
        }

        public GeneratedCriteria andRoleDescIsNotNull() {
            addCriterion("ROLE_DESC is not null");return this;
        }

        public GeneratedCriteria andRoleDescEqualTo(String value) {
            addCriterion("ROLE_DESC =", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescNotEqualTo(String value) {
            addCriterion("ROLE_DESC <>", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescGreaterThan(String value) {
            addCriterion("ROLE_DESC >", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_DESC >=", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescLessThan(String value) {
            addCriterion("ROLE_DESC <", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescLessThanOrEqualTo(String value) {
            addCriterion("ROLE_DESC <=", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescLike(String value) {
            addCriterion("ROLE_DESC like", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescNotLike(String value) {
            addCriterion("ROLE_DESC not like", value, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescIn(List<String> values) {
            addCriterion("ROLE_DESC in", values, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescNotIn(List<String> values) {
            addCriterion("ROLE_DESC not in", values, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescBetween(String value1, String value2) {
            addCriterion("ROLE_DESC between", value1, value2, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleDescNotBetween(String value1, String value2) {
            addCriterion("ROLE_DESC not between", value1, value2, "roleDesc");return this;
        }

        public GeneratedCriteria andRoleTypeIsNull() {
            addCriterion("ROLE_TYPE is null");return this;
        }

        public GeneratedCriteria andRoleTypeIsNotNull() {
            addCriterion("ROLE_TYPE is not null");return this;
        }

        public GeneratedCriteria andRoleTypeEqualTo(String value) {
            addCriterion("ROLE_TYPE =", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeNotEqualTo(String value) {
            addCriterion("ROLE_TYPE <>", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeGreaterThan(String value) {
            addCriterion("ROLE_TYPE >", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeGreaterThanOrEqualTo(String value) {
            addCriterion("ROLE_TYPE >=", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeLessThan(String value) {
            addCriterion("ROLE_TYPE <", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeLessThanOrEqualTo(String value) {
            addCriterion("ROLE_TYPE <=", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeLike(String value) {
            addCriterion("ROLE_TYPE like", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeNotLike(String value) {
            addCriterion("ROLE_TYPE not like", value, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeIn(List<String> values) {
            addCriterion("ROLE_TYPE in", values, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeNotIn(List<String> values) {
            addCriterion("ROLE_TYPE not in", values, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeBetween(String value1, String value2) {
            addCriterion("ROLE_TYPE between", value1, value2, "roleType");return this;
        }

        public GeneratedCriteria andRoleTypeNotBetween(String value1, String value2) {
            addCriterion("ROLE_TYPE not between", value1, value2, "roleType");return this;
        }
    }
}