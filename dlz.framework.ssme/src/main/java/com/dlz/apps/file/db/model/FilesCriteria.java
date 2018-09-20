package com.dlz.apps.file.db.model;

import java.util.ArrayList;
import java.util.List;

import com.dlz.apps.file.db.model.FilesCriteria.GeneratedCriteria;
import com.dlz.framework.ssme.base.criteria.BaseCriteria;
import com.dlz.framework.ssme.base.criteria.BaseGeneratedCriteria;

public class FilesCriteria extends BaseCriteria<GeneratedCriteria> {
	void doNothing(){new java.util.ArrayList<>().forEach(a->{});}

    protected GeneratedCriteria createCriteriaInternal1() {
        return new GeneratedCriteria();
    }

    public FilesCriteria() {
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

        public GeneratedCriteria andDTypeIsNull() {
            addCriterion("D_TYPE is null");return this;
        }

        public GeneratedCriteria andDTypeIsNotNull() {
            addCriterion("D_TYPE is not null");return this;
        }

        public GeneratedCriteria andDTypeEqualTo(String value) {
            addCriterion("D_TYPE =", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotEqualTo(String value) {
            addCriterion("D_TYPE <>", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeGreaterThan(String value) {
            addCriterion("D_TYPE >", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeGreaterThanOrEqualTo(String value) {
            addCriterion("D_TYPE >=", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLessThan(String value) {
            addCriterion("D_TYPE <", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLessThanOrEqualTo(String value) {
            addCriterion("D_TYPE <=", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeLike(String value) {
            addCriterion("D_TYPE like", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotLike(String value) {
            addCriterion("D_TYPE not like", value, "dType");return this;
        }

        public GeneratedCriteria andDTypeIn(List<String> values) {
            addCriterion("D_TYPE in", values, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotIn(List<String> values) {
            addCriterion("D_TYPE not in", values, "dType");return this;
        }

        public GeneratedCriteria andDTypeBetween(String value1, String value2) {
            addCriterion("D_TYPE between", value1, value2, "dType");return this;
        }

        public GeneratedCriteria andDTypeNotBetween(String value1, String value2) {
            addCriterion("D_TYPE not between", value1, value2, "dType");return this;
        }

        public GeneratedCriteria andDataIdIsNull() {
            addCriterion("DATA_ID is null");return this;
        }

        public GeneratedCriteria andDataIdIsNotNull() {
            addCriterion("DATA_ID is not null");return this;
        }

        public GeneratedCriteria andDataIdEqualTo(Long value) {
            addCriterion("DATA_ID =", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdNotEqualTo(Long value) {
            addCriterion("DATA_ID <>", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdGreaterThan(Long value) {
            addCriterion("DATA_ID >", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdGreaterThanOrEqualTo(Long value) {
            addCriterion("DATA_ID >=", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdLessThan(Long value) {
            addCriterion("DATA_ID <", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdLessThanOrEqualTo(Long value) {
            addCriterion("DATA_ID <=", value, "dataId");return this;
        }

        public GeneratedCriteria andDataIdIn(List<Long> values) {
            addCriterion("DATA_ID in", values, "dataId");return this;
        }

        public GeneratedCriteria andDataIdNotIn(List<Long> values) {
            addCriterion("DATA_ID not in", values, "dataId");return this;
        }

        public GeneratedCriteria andDataIdBetween(Long value1, Long value2) {
            addCriterion("DATA_ID between", value1, value2, "dataId");return this;
        }

        public GeneratedCriteria andDataIdNotBetween(Long value1, Long value2) {
            addCriterion("DATA_ID not between", value1, value2, "dataId");return this;
        }

        public GeneratedCriteria andFNameIsNull() {
            addCriterion("F_NAME is null");return this;
        }

        public GeneratedCriteria andFNameIsNotNull() {
            addCriterion("F_NAME is not null");return this;
        }

        public GeneratedCriteria andFNameEqualTo(String value) {
            addCriterion("F_NAME =", value, "fName");return this;
        }

        public GeneratedCriteria andFNameNotEqualTo(String value) {
            addCriterion("F_NAME <>", value, "fName");return this;
        }

        public GeneratedCriteria andFNameGreaterThan(String value) {
            addCriterion("F_NAME >", value, "fName");return this;
        }

        public GeneratedCriteria andFNameGreaterThanOrEqualTo(String value) {
            addCriterion("F_NAME >=", value, "fName");return this;
        }

        public GeneratedCriteria andFNameLessThan(String value) {
            addCriterion("F_NAME <", value, "fName");return this;
        }

        public GeneratedCriteria andFNameLessThanOrEqualTo(String value) {
            addCriterion("F_NAME <=", value, "fName");return this;
        }

        public GeneratedCriteria andFNameLike(String value) {
            addCriterion("F_NAME like", value, "fName");return this;
        }

        public GeneratedCriteria andFNameNotLike(String value) {
            addCriterion("F_NAME not like", value, "fName");return this;
        }

        public GeneratedCriteria andFNameIn(List<String> values) {
            addCriterion("F_NAME in", values, "fName");return this;
        }

        public GeneratedCriteria andFNameNotIn(List<String> values) {
            addCriterion("F_NAME not in", values, "fName");return this;
        }

        public GeneratedCriteria andFNameBetween(String value1, String value2) {
            addCriterion("F_NAME between", value1, value2, "fName");return this;
        }

        public GeneratedCriteria andFNameNotBetween(String value1, String value2) {
            addCriterion("F_NAME not between", value1, value2, "fName");return this;
        }

        public GeneratedCriteria andFPathIsNull() {
            addCriterion("F_PATH is null");return this;
        }

        public GeneratedCriteria andFPathIsNotNull() {
            addCriterion("F_PATH is not null");return this;
        }

        public GeneratedCriteria andFPathEqualTo(String value) {
            addCriterion("F_PATH =", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathNotEqualTo(String value) {
            addCriterion("F_PATH <>", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathGreaterThan(String value) {
            addCriterion("F_PATH >", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathGreaterThanOrEqualTo(String value) {
            addCriterion("F_PATH >=", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathLessThan(String value) {
            addCriterion("F_PATH <", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathLessThanOrEqualTo(String value) {
            addCriterion("F_PATH <=", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathLike(String value) {
            addCriterion("F_PATH like", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathNotLike(String value) {
            addCriterion("F_PATH not like", value, "fPath");return this;
        }

        public GeneratedCriteria andFPathIn(List<String> values) {
            addCriterion("F_PATH in", values, "fPath");return this;
        }

        public GeneratedCriteria andFPathNotIn(List<String> values) {
            addCriterion("F_PATH not in", values, "fPath");return this;
        }

        public GeneratedCriteria andFPathBetween(String value1, String value2) {
            addCriterion("F_PATH between", value1, value2, "fPath");return this;
        }

        public GeneratedCriteria andFPathNotBetween(String value1, String value2) {
            addCriterion("F_PATH not between", value1, value2, "fPath");return this;
        }

        public GeneratedCriteria andFSurfixIsNull() {
            addCriterion("F_SURFIX is null");return this;
        }

        public GeneratedCriteria andFSurfixIsNotNull() {
            addCriterion("F_SURFIX is not null");return this;
        }

        public GeneratedCriteria andFSurfixEqualTo(String value) {
            addCriterion("F_SURFIX =", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixNotEqualTo(String value) {
            addCriterion("F_SURFIX <>", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixGreaterThan(String value) {
            addCriterion("F_SURFIX >", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixGreaterThanOrEqualTo(String value) {
            addCriterion("F_SURFIX >=", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixLessThan(String value) {
            addCriterion("F_SURFIX <", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixLessThanOrEqualTo(String value) {
            addCriterion("F_SURFIX <=", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixLike(String value) {
            addCriterion("F_SURFIX like", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixNotLike(String value) {
            addCriterion("F_SURFIX not like", value, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixIn(List<String> values) {
            addCriterion("F_SURFIX in", values, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixNotIn(List<String> values) {
            addCriterion("F_SURFIX not in", values, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixBetween(String value1, String value2) {
            addCriterion("F_SURFIX between", value1, value2, "fSurfix");return this;
        }

        public GeneratedCriteria andFSurfixNotBetween(String value1, String value2) {
            addCriterion("F_SURFIX not between", value1, value2, "fSurfix");return this;
        }

        public GeneratedCriteria andFOrdIsNull() {
            addCriterion("F_ORD is null");return this;
        }

        public GeneratedCriteria andFOrdIsNotNull() {
            addCriterion("F_ORD is not null");return this;
        }

        public GeneratedCriteria andFOrdEqualTo(Long value) {
            addCriterion("F_ORD =", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdNotEqualTo(Long value) {
            addCriterion("F_ORD <>", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdGreaterThan(Long value) {
            addCriterion("F_ORD >", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdGreaterThanOrEqualTo(Long value) {
            addCriterion("F_ORD >=", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdLessThan(Long value) {
            addCriterion("F_ORD <", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdLessThanOrEqualTo(Long value) {
            addCriterion("F_ORD <=", value, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdIn(List<Long> values) {
            addCriterion("F_ORD in", values, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdNotIn(List<Long> values) {
            addCriterion("F_ORD not in", values, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdBetween(Long value1, Long value2) {
            addCriterion("F_ORD between", value1, value2, "fOrd");return this;
        }

        public GeneratedCriteria andFOrdNotBetween(Long value1, Long value2) {
            addCriterion("F_ORD not between", value1, value2, "fOrd");return this;
        }

        public GeneratedCriteria andFDelIsNull() {
            addCriterion("F_DEL is null");return this;
        }

        public GeneratedCriteria andFDelIsNotNull() {
            addCriterion("F_DEL is not null");return this;
        }

        public GeneratedCriteria andFDelEqualTo(Long value) {
            addCriterion("F_DEL =", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelNotEqualTo(Long value) {
            addCriterion("F_DEL <>", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelGreaterThan(Long value) {
            addCriterion("F_DEL >", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelGreaterThanOrEqualTo(Long value) {
            addCriterion("F_DEL >=", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelLessThan(Long value) {
            addCriterion("F_DEL <", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelLessThanOrEqualTo(Long value) {
            addCriterion("F_DEL <=", value, "fDel");return this;
        }

        public GeneratedCriteria andFDelIn(List<Long> values) {
            addCriterion("F_DEL in", values, "fDel");return this;
        }

        public GeneratedCriteria andFDelNotIn(List<Long> values) {
            addCriterion("F_DEL not in", values, "fDel");return this;
        }

        public GeneratedCriteria andFDelBetween(Long value1, Long value2) {
            addCriterion("F_DEL between", value1, value2, "fDel");return this;
        }

        public GeneratedCriteria andFDelNotBetween(Long value1, Long value2) {
            addCriterion("F_DEL not between", value1, value2, "fDel");return this;
        }
    }
}