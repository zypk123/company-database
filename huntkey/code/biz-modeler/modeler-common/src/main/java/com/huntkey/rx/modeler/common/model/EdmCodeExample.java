package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.List;

public class EdmCodeExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmCodeExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("id like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("id not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNull() {
            addCriterion("code_type is null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIsNotNull() {
            addCriterion("code_type is not null");
            return (Criteria) this;
        }

        public Criteria andCodeTypeEqualTo(String value) {
            addCriterion("code_type =", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotEqualTo(String value) {
            addCriterion("code_type <>", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThan(String value) {
            addCriterion("code_type >", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeGreaterThanOrEqualTo(String value) {
            addCriterion("code_type >=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThan(String value) {
            addCriterion("code_type <", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLessThanOrEqualTo(String value) {
            addCriterion("code_type <=", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeLike(String value) {
            addCriterion("code_type like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotLike(String value) {
            addCriterion("code_type not like", value, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeIn(List<String> values) {
            addCriterion("code_type in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotIn(List<String> values) {
            addCriterion("code_type not in", values, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeBetween(String value1, String value2) {
            addCriterion("code_type between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeTypeNotBetween(String value1, String value2) {
            addCriterion("code_type not between", value1, value2, "codeType");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNull() {
            addCriterion("code_value is null");
            return (Criteria) this;
        }

        public Criteria andCodeValueIsNotNull() {
            addCriterion("code_value is not null");
            return (Criteria) this;
        }

        public Criteria andCodeValueEqualTo(String value) {
            addCriterion("code_value =", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotEqualTo(String value) {
            addCriterion("code_value <>", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThan(String value) {
            addCriterion("code_value >", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueGreaterThanOrEqualTo(String value) {
            addCriterion("code_value >=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThan(String value) {
            addCriterion("code_value <", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLessThanOrEqualTo(String value) {
            addCriterion("code_value <=", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueLike(String value) {
            addCriterion("code_value like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotLike(String value) {
            addCriterion("code_value not like", value, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueIn(List<String> values) {
            addCriterion("code_value in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotIn(List<String> values) {
            addCriterion("code_value not in", values, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueBetween(String value1, String value2) {
            addCriterion("code_value between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeValueNotBetween(String value1, String value2) {
            addCriterion("code_value not between", value1, value2, "codeValue");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNull() {
            addCriterion("code_name is null");
            return (Criteria) this;
        }

        public Criteria andCodeNameIsNotNull() {
            addCriterion("code_name is not null");
            return (Criteria) this;
        }

        public Criteria andCodeNameEqualTo(String value) {
            addCriterion("code_name =", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotEqualTo(String value) {
            addCriterion("code_name <>", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThan(String value) {
            addCriterion("code_name >", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameGreaterThanOrEqualTo(String value) {
            addCriterion("code_name >=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThan(String value) {
            addCriterion("code_name <", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLessThanOrEqualTo(String value) {
            addCriterion("code_name <=", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameLike(String value) {
            addCriterion("code_name like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotLike(String value) {
            addCriterion("code_name not like", value, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameIn(List<String> values) {
            addCriterion("code_name in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotIn(List<String> values) {
            addCriterion("code_name not in", values, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameBetween(String value1, String value2) {
            addCriterion("code_name between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeNameNotBetween(String value1, String value2) {
            addCriterion("code_name not between", value1, value2, "codeName");
            return (Criteria) this;
        }

        public Criteria andCodeDescIsNull() {
            addCriterion("code_desc is null");
            return (Criteria) this;
        }

        public Criteria andCodeDescIsNotNull() {
            addCriterion("code_desc is not null");
            return (Criteria) this;
        }

        public Criteria andCodeDescEqualTo(String value) {
            addCriterion("code_desc =", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotEqualTo(String value) {
            addCriterion("code_desc <>", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescGreaterThan(String value) {
            addCriterion("code_desc >", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescGreaterThanOrEqualTo(String value) {
            addCriterion("code_desc >=", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLessThan(String value) {
            addCriterion("code_desc <", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLessThanOrEqualTo(String value) {
            addCriterion("code_desc <=", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescLike(String value) {
            addCriterion("code_desc like", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotLike(String value) {
            addCriterion("code_desc not like", value, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescIn(List<String> values) {
            addCriterion("code_desc in", values, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotIn(List<String> values) {
            addCriterion("code_desc not in", values, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescBetween(String value1, String value2) {
            addCriterion("code_desc between", value1, value2, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeDescNotBetween(String value1, String value2) {
            addCriterion("code_desc not between", value1, value2, "codeDesc");
            return (Criteria) this;
        }

        public Criteria andCodeSeqIsNull() {
            addCriterion("code_seq is null");
            return (Criteria) this;
        }

        public Criteria andCodeSeqIsNotNull() {
            addCriterion("code_seq is not null");
            return (Criteria) this;
        }

        public Criteria andCodeSeqEqualTo(Integer value) {
            addCriterion("code_seq =", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqNotEqualTo(Integer value) {
            addCriterion("code_seq <>", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqGreaterThan(Integer value) {
            addCriterion("code_seq >", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("code_seq >=", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqLessThan(Integer value) {
            addCriterion("code_seq <", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqLessThanOrEqualTo(Integer value) {
            addCriterion("code_seq <=", value, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqIn(List<Integer> values) {
            addCriterion("code_seq in", values, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqNotIn(List<Integer> values) {
            addCriterion("code_seq not in", values, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqBetween(Integer value1, Integer value2) {
            addCriterion("code_seq between", value1, value2, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("code_seq not between", value1, value2, "codeSeq");
            return (Criteria) this;
        }

        public Criteria andCodeStatusIsNull() {
            addCriterion("code_status is null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusIsNotNull() {
            addCriterion("code_status is not null");
            return (Criteria) this;
        }

        public Criteria andCodeStatusEqualTo(Byte value) {
            addCriterion("code_status =", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNotEqualTo(Byte value) {
            addCriterion("code_status <>", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusGreaterThan(Byte value) {
            addCriterion("code_status >", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("code_status >=", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusLessThan(Byte value) {
            addCriterion("code_status <", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusLessThanOrEqualTo(Byte value) {
            addCriterion("code_status <=", value, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusIn(List<Byte> values) {
            addCriterion("code_status in", values, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNotIn(List<Byte> values) {
            addCriterion("code_status not in", values, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusBetween(Byte value1, Byte value2) {
            addCriterion("code_status between", value1, value2, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("code_status not between", value1, value2, "codeStatus");
            return (Criteria) this;
        }

        public Criteria andCodeExt1IsNull() {
            addCriterion("code_ext1 is null");
            return (Criteria) this;
        }

        public Criteria andCodeExt1IsNotNull() {
            addCriterion("code_ext1 is not null");
            return (Criteria) this;
        }

        public Criteria andCodeExt1EqualTo(String value) {
            addCriterion("code_ext1 =", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1NotEqualTo(String value) {
            addCriterion("code_ext1 <>", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1GreaterThan(String value) {
            addCriterion("code_ext1 >", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1GreaterThanOrEqualTo(String value) {
            addCriterion("code_ext1 >=", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1LessThan(String value) {
            addCriterion("code_ext1 <", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1LessThanOrEqualTo(String value) {
            addCriterion("code_ext1 <=", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1Like(String value) {
            addCriterion("code_ext1 like", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1NotLike(String value) {
            addCriterion("code_ext1 not like", value, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1In(List<String> values) {
            addCriterion("code_ext1 in", values, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1NotIn(List<String> values) {
            addCriterion("code_ext1 not in", values, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1Between(String value1, String value2) {
            addCriterion("code_ext1 between", value1, value2, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt1NotBetween(String value1, String value2) {
            addCriterion("code_ext1 not between", value1, value2, "codeExt1");
            return (Criteria) this;
        }

        public Criteria andCodeExt2IsNull() {
            addCriterion("code_ext2 is null");
            return (Criteria) this;
        }

        public Criteria andCodeExt2IsNotNull() {
            addCriterion("code_ext2 is not null");
            return (Criteria) this;
        }

        public Criteria andCodeExt2EqualTo(String value) {
            addCriterion("code_ext2 =", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2NotEqualTo(String value) {
            addCriterion("code_ext2 <>", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2GreaterThan(String value) {
            addCriterion("code_ext2 >", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2GreaterThanOrEqualTo(String value) {
            addCriterion("code_ext2 >=", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2LessThan(String value) {
            addCriterion("code_ext2 <", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2LessThanOrEqualTo(String value) {
            addCriterion("code_ext2 <=", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2Like(String value) {
            addCriterion("code_ext2 like", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2NotLike(String value) {
            addCriterion("code_ext2 not like", value, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2In(List<String> values) {
            addCriterion("code_ext2 in", values, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2NotIn(List<String> values) {
            addCriterion("code_ext2 not in", values, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2Between(String value1, String value2) {
            addCriterion("code_ext2 between", value1, value2, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt2NotBetween(String value1, String value2) {
            addCriterion("code_ext2 not between", value1, value2, "codeExt2");
            return (Criteria) this;
        }

        public Criteria andCodeExt3IsNull() {
            addCriterion("code_ext3 is null");
            return (Criteria) this;
        }

        public Criteria andCodeExt3IsNotNull() {
            addCriterion("code_ext3 is not null");
            return (Criteria) this;
        }

        public Criteria andCodeExt3EqualTo(String value) {
            addCriterion("code_ext3 =", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3NotEqualTo(String value) {
            addCriterion("code_ext3 <>", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3GreaterThan(String value) {
            addCriterion("code_ext3 >", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3GreaterThanOrEqualTo(String value) {
            addCriterion("code_ext3 >=", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3LessThan(String value) {
            addCriterion("code_ext3 <", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3LessThanOrEqualTo(String value) {
            addCriterion("code_ext3 <=", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3Like(String value) {
            addCriterion("code_ext3 like", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3NotLike(String value) {
            addCriterion("code_ext3 not like", value, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3In(List<String> values) {
            addCriterion("code_ext3 in", values, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3NotIn(List<String> values) {
            addCriterion("code_ext3 not in", values, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3Between(String value1, String value2) {
            addCriterion("code_ext3 between", value1, value2, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt3NotBetween(String value1, String value2) {
            addCriterion("code_ext3 not between", value1, value2, "codeExt3");
            return (Criteria) this;
        }

        public Criteria andCodeExt4IsNull() {
            addCriterion("code_ext4 is null");
            return (Criteria) this;
        }

        public Criteria andCodeExt4IsNotNull() {
            addCriterion("code_ext4 is not null");
            return (Criteria) this;
        }

        public Criteria andCodeExt4EqualTo(String value) {
            addCriterion("code_ext4 =", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4NotEqualTo(String value) {
            addCriterion("code_ext4 <>", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4GreaterThan(String value) {
            addCriterion("code_ext4 >", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4GreaterThanOrEqualTo(String value) {
            addCriterion("code_ext4 >=", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4LessThan(String value) {
            addCriterion("code_ext4 <", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4LessThanOrEqualTo(String value) {
            addCriterion("code_ext4 <=", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4Like(String value) {
            addCriterion("code_ext4 like", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4NotLike(String value) {
            addCriterion("code_ext4 not like", value, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4In(List<String> values) {
            addCriterion("code_ext4 in", values, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4NotIn(List<String> values) {
            addCriterion("code_ext4 not in", values, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4Between(String value1, String value2) {
            addCriterion("code_ext4 between", value1, value2, "codeExt4");
            return (Criteria) this;
        }

        public Criteria andCodeExt4NotBetween(String value1, String value2) {
            addCriterion("code_ext4 not between", value1, value2, "codeExt4");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}