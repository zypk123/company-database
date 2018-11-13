package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TacConditionRelatedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TacConditionRelatedExample() {
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

        public Criteria andCndrIdIsNull() {
            addCriterion("cndr_id is null");
            return (Criteria) this;
        }

        public Criteria andCndrIdIsNotNull() {
            addCriterion("cndr_id is not null");
            return (Criteria) this;
        }

        public Criteria andCndrIdEqualTo(String value) {
            addCriterion("cndr_id =", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdNotEqualTo(String value) {
            addCriterion("cndr_id <>", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdGreaterThan(String value) {
            addCriterion("cndr_id >", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_id >=", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdLessThan(String value) {
            addCriterion("cndr_id <", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdLessThanOrEqualTo(String value) {
            addCriterion("cndr_id <=", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdLike(String value) {
            addCriterion("cndr_id like", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdNotLike(String value) {
            addCriterion("cndr_id not like", value, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdIn(List<String> values) {
            addCriterion("cndr_id in", values, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdNotIn(List<String> values) {
            addCriterion("cndr_id not in", values, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdBetween(String value1, String value2) {
            addCriterion("cndr_id between", value1, value2, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrIdNotBetween(String value1, String value2) {
            addCriterion("cndr_id not between", value1, value2, "cndrId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdIsNull() {
            addCriterion("cndr_prop_related_id is null");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdIsNotNull() {
            addCriterion("cndr_prop_related_id is not null");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdEqualTo(String value) {
            addCriterion("cndr_prop_related_id =", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdNotEqualTo(String value) {
            addCriterion("cndr_prop_related_id <>", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdGreaterThan(String value) {
            addCriterion("cndr_prop_related_id >", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop_related_id >=", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdLessThan(String value) {
            addCriterion("cndr_prop_related_id <", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop_related_id <=", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdLike(String value) {
            addCriterion("cndr_prop_related_id like", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdNotLike(String value) {
            addCriterion("cndr_prop_related_id not like", value, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdIn(List<String> values) {
            addCriterion("cndr_prop_related_id in", values, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdNotIn(List<String> values) {
            addCriterion("cndr_prop_related_id not in", values, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdBetween(String value1, String value2) {
            addCriterion("cndr_prop_related_id between", value1, value2, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIdNotBetween(String value1, String value2) {
            addCriterion("cndr_prop_related_id not between", value1, value2, "cndrPropRelatedId");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIsNull() {
            addCriterion("cndr_prop_related is null");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIsNotNull() {
            addCriterion("cndr_prop_related is not null");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedEqualTo(String value) {
            addCriterion("cndr_prop_related =", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedNotEqualTo(String value) {
            addCriterion("cndr_prop_related <>", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedGreaterThan(String value) {
            addCriterion("cndr_prop_related >", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop_related >=", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedLessThan(String value) {
            addCriterion("cndr_prop_related <", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop_related <=", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedLike(String value) {
            addCriterion("cndr_prop_related like", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedNotLike(String value) {
            addCriterion("cndr_prop_related not like", value, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedIn(List<String> values) {
            addCriterion("cndr_prop_related in", values, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedNotIn(List<String> values) {
            addCriterion("cndr_prop_related not in", values, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedBetween(String value1, String value2) {
            addCriterion("cndr_prop_related between", value1, value2, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrPropRelatedNotBetween(String value1, String value2) {
            addCriterion("cndr_prop_related not between", value1, value2, "cndrPropRelated");
            return (Criteria) this;
        }

        public Criteria andCndrSeqIsNull() {
            addCriterion("cndr_seq is null");
            return (Criteria) this;
        }

        public Criteria andCndrSeqIsNotNull() {
            addCriterion("cndr_seq is not null");
            return (Criteria) this;
        }

        public Criteria andCndrSeqEqualTo(Integer value) {
            addCriterion("cndr_seq =", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqNotEqualTo(Integer value) {
            addCriterion("cndr_seq <>", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqGreaterThan(Integer value) {
            addCriterion("cndr_seq >", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("cndr_seq >=", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqLessThan(Integer value) {
            addCriterion("cndr_seq <", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqLessThanOrEqualTo(Integer value) {
            addCriterion("cndr_seq <=", value, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqIn(List<Integer> values) {
            addCriterion("cndr_seq in", values, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqNotIn(List<Integer> values) {
            addCriterion("cndr_seq not in", values, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqBetween(Integer value1, Integer value2) {
            addCriterion("cndr_seq between", value1, value2, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("cndr_seq not between", value1, value2, "cndrSeq");
            return (Criteria) this;
        }

        public Criteria andCndrPropIsNull() {
            addCriterion("cndr_prop is null");
            return (Criteria) this;
        }

        public Criteria andCndrPropIsNotNull() {
            addCriterion("cndr_prop is not null");
            return (Criteria) this;
        }

        public Criteria andCndrPropEqualTo(String value) {
            addCriterion("cndr_prop =", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropNotEqualTo(String value) {
            addCriterion("cndr_prop <>", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropGreaterThan(String value) {
            addCriterion("cndr_prop >", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop >=", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropLessThan(String value) {
            addCriterion("cndr_prop <", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop <=", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropLike(String value) {
            addCriterion("cndr_prop like", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropNotLike(String value) {
            addCriterion("cndr_prop not like", value, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropIn(List<String> values) {
            addCriterion("cndr_prop in", values, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropNotIn(List<String> values) {
            addCriterion("cndr_prop not in", values, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropBetween(String value1, String value2) {
            addCriterion("cndr_prop between", value1, value2, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrPropNotBetween(String value1, String value2) {
            addCriterion("cndr_prop not between", value1, value2, "cndrProp");
            return (Criteria) this;
        }

        public Criteria andCndrProp2IsNull() {
            addCriterion("cndr_prop2 is null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2IsNotNull() {
            addCriterion("cndr_prop2 is not null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2EqualTo(String value) {
            addCriterion("cndr_prop2 =", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2NotEqualTo(String value) {
            addCriterion("cndr_prop2 <>", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2GreaterThan(String value) {
            addCriterion("cndr_prop2 >", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2GreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop2 >=", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2LessThan(String value) {
            addCriterion("cndr_prop2 <", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2LessThanOrEqualTo(String value) {
            addCriterion("cndr_prop2 <=", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2Like(String value) {
            addCriterion("cndr_prop2 like", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2NotLike(String value) {
            addCriterion("cndr_prop2 not like", value, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2In(List<String> values) {
            addCriterion("cndr_prop2 in", values, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2NotIn(List<String> values) {
            addCriterion("cndr_prop2 not in", values, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2Between(String value1, String value2) {
            addCriterion("cndr_prop2 between", value1, value2, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrProp2NotBetween(String value1, String value2) {
            addCriterion("cndr_prop2 not between", value1, value2, "cndrProp2");
            return (Criteria) this;
        }

        public Criteria andCndrOperateIsNull() {
            addCriterion("cndr_operate is null");
            return (Criteria) this;
        }

        public Criteria andCndrOperateIsNotNull() {
            addCriterion("cndr_operate is not null");
            return (Criteria) this;
        }

        public Criteria andCndrOperateEqualTo(String value) {
            addCriterion("cndr_operate =", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateNotEqualTo(String value) {
            addCriterion("cndr_operate <>", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateGreaterThan(String value) {
            addCriterion("cndr_operate >", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_operate >=", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateLessThan(String value) {
            addCriterion("cndr_operate <", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateLessThanOrEqualTo(String value) {
            addCriterion("cndr_operate <=", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateLike(String value) {
            addCriterion("cndr_operate like", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateNotLike(String value) {
            addCriterion("cndr_operate not like", value, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateIn(List<String> values) {
            addCriterion("cndr_operate in", values, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateNotIn(List<String> values) {
            addCriterion("cndr_operate not in", values, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateBetween(String value1, String value2) {
            addCriterion("cndr_operate between", value1, value2, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrOperateNotBetween(String value1, String value2) {
            addCriterion("cndr_operate not between", value1, value2, "cndrOperate");
            return (Criteria) this;
        }

        public Criteria andCndrValueIsNull() {
            addCriterion("cndr_value is null");
            return (Criteria) this;
        }

        public Criteria andCndrValueIsNotNull() {
            addCriterion("cndr_value is not null");
            return (Criteria) this;
        }

        public Criteria andCndrValueEqualTo(String value) {
            addCriterion("cndr_value =", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueNotEqualTo(String value) {
            addCriterion("cndr_value <>", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueGreaterThan(String value) {
            addCriterion("cndr_value >", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_value >=", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueLessThan(String value) {
            addCriterion("cndr_value <", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueLessThanOrEqualTo(String value) {
            addCriterion("cndr_value <=", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueLike(String value) {
            addCriterion("cndr_value like", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueNotLike(String value) {
            addCriterion("cndr_value not like", value, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueIn(List<String> values) {
            addCriterion("cndr_value in", values, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueNotIn(List<String> values) {
            addCriterion("cndr_value not in", values, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueBetween(String value1, String value2) {
            addCriterion("cndr_value between", value1, value2, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueNotBetween(String value1, String value2) {
            addCriterion("cndr_value not between", value1, value2, "cndrValue");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeIsNull() {
            addCriterion("cndr_value_type is null");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeIsNotNull() {
            addCriterion("cndr_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeEqualTo(String value) {
            addCriterion("cndr_value_type =", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeNotEqualTo(String value) {
            addCriterion("cndr_value_type <>", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeGreaterThan(String value) {
            addCriterion("cndr_value_type >", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_value_type >=", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeLessThan(String value) {
            addCriterion("cndr_value_type <", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeLessThanOrEqualTo(String value) {
            addCriterion("cndr_value_type <=", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeLike(String value) {
            addCriterion("cndr_value_type like", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeNotLike(String value) {
            addCriterion("cndr_value_type not like", value, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeIn(List<String> values) {
            addCriterion("cndr_value_type in", values, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeNotIn(List<String> values) {
            addCriterion("cndr_value_type not in", values, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeBetween(String value1, String value2) {
            addCriterion("cndr_value_type between", value1, value2, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andCndrValueTypeNotBetween(String value1, String value2) {
            addCriterion("cndr_value_type not between", value1, value2, "cndrValueType");
            return (Criteria) this;
        }

        public Criteria andIsenableIsNull() {
            addCriterion("isenable is null");
            return (Criteria) this;
        }

        public Criteria andIsenableIsNotNull() {
            addCriterion("isenable is not null");
            return (Criteria) this;
        }

        public Criteria andIsenableEqualTo(Byte value) {
            addCriterion("isenable =", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableNotEqualTo(Byte value) {
            addCriterion("isenable <>", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableGreaterThan(Byte value) {
            addCriterion("isenable >", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableGreaterThanOrEqualTo(Byte value) {
            addCriterion("isenable >=", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableLessThan(Byte value) {
            addCriterion("isenable <", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableLessThanOrEqualTo(Byte value) {
            addCriterion("isenable <=", value, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableIn(List<Byte> values) {
            addCriterion("isenable in", values, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableNotIn(List<Byte> values) {
            addCriterion("isenable not in", values, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableBetween(Byte value1, Byte value2) {
            addCriterion("isenable between", value1, value2, "isenable");
            return (Criteria) this;
        }

        public Criteria andIsenableNotBetween(Byte value1, Byte value2) {
            addCriterion("isenable not between", value1, value2, "isenable");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNull() {
            addCriterion("addtime is null");
            return (Criteria) this;
        }

        public Criteria andAddtimeIsNotNull() {
            addCriterion("addtime is not null");
            return (Criteria) this;
        }

        public Criteria andAddtimeEqualTo(Date value) {
            addCriterion("addtime =", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotEqualTo(Date value) {
            addCriterion("addtime <>", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThan(Date value) {
            addCriterion("addtime >", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("addtime >=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThan(Date value) {
            addCriterion("addtime <", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeLessThanOrEqualTo(Date value) {
            addCriterion("addtime <=", value, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeIn(List<Date> values) {
            addCriterion("addtime in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotIn(List<Date> values) {
            addCriterion("addtime not in", values, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeBetween(Date value1, Date value2) {
            addCriterion("addtime between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAddtimeNotBetween(Date value1, Date value2) {
            addCriterion("addtime not between", value1, value2, "addtime");
            return (Criteria) this;
        }

        public Criteria andAdduserIsNull() {
            addCriterion("adduser is null");
            return (Criteria) this;
        }

        public Criteria andAdduserIsNotNull() {
            addCriterion("adduser is not null");
            return (Criteria) this;
        }

        public Criteria andAdduserEqualTo(String value) {
            addCriterion("adduser =", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotEqualTo(String value) {
            addCriterion("adduser <>", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserGreaterThan(String value) {
            addCriterion("adduser >", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserGreaterThanOrEqualTo(String value) {
            addCriterion("adduser >=", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLessThan(String value) {
            addCriterion("adduser <", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLessThanOrEqualTo(String value) {
            addCriterion("adduser <=", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserLike(String value) {
            addCriterion("adduser like", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotLike(String value) {
            addCriterion("adduser not like", value, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserIn(List<String> values) {
            addCriterion("adduser in", values, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotIn(List<String> values) {
            addCriterion("adduser not in", values, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserBetween(String value1, String value2) {
            addCriterion("adduser between", value1, value2, "adduser");
            return (Criteria) this;
        }

        public Criteria andAdduserNotBetween(String value1, String value2) {
            addCriterion("adduser not between", value1, value2, "adduser");
            return (Criteria) this;
        }

        public Criteria andModtimeIsNull() {
            addCriterion("modtime is null");
            return (Criteria) this;
        }

        public Criteria andModtimeIsNotNull() {
            addCriterion("modtime is not null");
            return (Criteria) this;
        }

        public Criteria andModtimeEqualTo(Date value) {
            addCriterion("modtime =", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeNotEqualTo(Date value) {
            addCriterion("modtime <>", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeGreaterThan(Date value) {
            addCriterion("modtime >", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeGreaterThanOrEqualTo(Date value) {
            addCriterion("modtime >=", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeLessThan(Date value) {
            addCriterion("modtime <", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeLessThanOrEqualTo(Date value) {
            addCriterion("modtime <=", value, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeIn(List<Date> values) {
            addCriterion("modtime in", values, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeNotIn(List<Date> values) {
            addCriterion("modtime not in", values, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeBetween(Date value1, Date value2) {
            addCriterion("modtime between", value1, value2, "modtime");
            return (Criteria) this;
        }

        public Criteria andModtimeNotBetween(Date value1, Date value2) {
            addCriterion("modtime not between", value1, value2, "modtime");
            return (Criteria) this;
        }

        public Criteria andModuserIsNull() {
            addCriterion("moduser is null");
            return (Criteria) this;
        }

        public Criteria andModuserIsNotNull() {
            addCriterion("moduser is not null");
            return (Criteria) this;
        }

        public Criteria andModuserEqualTo(String value) {
            addCriterion("moduser =", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserNotEqualTo(String value) {
            addCriterion("moduser <>", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserGreaterThan(String value) {
            addCriterion("moduser >", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserGreaterThanOrEqualTo(String value) {
            addCriterion("moduser >=", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserLessThan(String value) {
            addCriterion("moduser <", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserLessThanOrEqualTo(String value) {
            addCriterion("moduser <=", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserLike(String value) {
            addCriterion("moduser like", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserNotLike(String value) {
            addCriterion("moduser not like", value, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserIn(List<String> values) {
            addCriterion("moduser in", values, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserNotIn(List<String> values) {
            addCriterion("moduser not in", values, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserBetween(String value1, String value2) {
            addCriterion("moduser between", value1, value2, "moduser");
            return (Criteria) this;
        }

        public Criteria andModuserNotBetween(String value1, String value2) {
            addCriterion("moduser not between", value1, value2, "moduser");
            return (Criteria) this;
        }

        public Criteria andAcctidIsNull() {
            addCriterion("acctid is null");
            return (Criteria) this;
        }

        public Criteria andAcctidIsNotNull() {
            addCriterion("acctid is not null");
            return (Criteria) this;
        }

        public Criteria andAcctidEqualTo(Integer value) {
            addCriterion("acctid =", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotEqualTo(Integer value) {
            addCriterion("acctid <>", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidGreaterThan(Integer value) {
            addCriterion("acctid >", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidGreaterThanOrEqualTo(Integer value) {
            addCriterion("acctid >=", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidLessThan(Integer value) {
            addCriterion("acctid <", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidLessThanOrEqualTo(Integer value) {
            addCriterion("acctid <=", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidIn(List<Integer> values) {
            addCriterion("acctid in", values, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotIn(List<Integer> values) {
            addCriterion("acctid not in", values, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidBetween(Integer value1, Integer value2) {
            addCriterion("acctid between", value1, value2, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotBetween(Integer value1, Integer value2) {
            addCriterion("acctid not between", value1, value2, "acctid");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeIsNull() {
            addCriterion("cndr_prop_origin_code is null");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeIsNotNull() {
            addCriterion("cndr_prop_origin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeEqualTo(String value) {
            addCriterion("cndr_prop_origin_code =", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeNotEqualTo(String value) {
            addCriterion("cndr_prop_origin_code <>", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeGreaterThan(String value) {
            addCriterion("cndr_prop_origin_code >", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop_origin_code >=", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeLessThan(String value) {
            addCriterion("cndr_prop_origin_code <", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop_origin_code <=", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeLike(String value) {
            addCriterion("cndr_prop_origin_code like", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeNotLike(String value) {
            addCriterion("cndr_prop_origin_code not like", value, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeIn(List<String> values) {
            addCriterion("cndr_prop_origin_code in", values, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeNotIn(List<String> values) {
            addCriterion("cndr_prop_origin_code not in", values, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeBetween(String value1, String value2) {
            addCriterion("cndr_prop_origin_code between", value1, value2, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrPropOriginCodeNotBetween(String value1, String value2) {
            addCriterion("cndr_prop_origin_code not between", value1, value2, "cndrPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeIsNull() {
            addCriterion("cndr_prop2_origin_code is null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeIsNotNull() {
            addCriterion("cndr_prop2_origin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeEqualTo(String value) {
            addCriterion("cndr_prop2_origin_code =", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeNotEqualTo(String value) {
            addCriterion("cndr_prop2_origin_code <>", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeGreaterThan(String value) {
            addCriterion("cndr_prop2_origin_code >", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop2_origin_code >=", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeLessThan(String value) {
            addCriterion("cndr_prop2_origin_code <", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop2_origin_code <=", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeLike(String value) {
            addCriterion("cndr_prop2_origin_code like", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeNotLike(String value) {
            addCriterion("cndr_prop2_origin_code not like", value, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeIn(List<String> values) {
            addCriterion("cndr_prop2_origin_code in", values, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeNotIn(List<String> values) {
            addCriterion("cndr_prop2_origin_code not in", values, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeBetween(String value1, String value2) {
            addCriterion("cndr_prop2_origin_code between", value1, value2, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrProp2OriginCodeNotBetween(String value1, String value2) {
            addCriterion("cndr_prop2_origin_code not between", value1, value2, "cndrProp2OriginCode");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnIsNull() {
            addCriterion("cndr_class1_name_en is null");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnIsNotNull() {
            addCriterion("cndr_class1_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnEqualTo(String value) {
            addCriterion("cndr_class1_name_en =", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnNotEqualTo(String value) {
            addCriterion("cndr_class1_name_en <>", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnGreaterThan(String value) {
            addCriterion("cndr_class1_name_en >", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_class1_name_en >=", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnLessThan(String value) {
            addCriterion("cndr_class1_name_en <", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnLessThanOrEqualTo(String value) {
            addCriterion("cndr_class1_name_en <=", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnLike(String value) {
            addCriterion("cndr_class1_name_en like", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnNotLike(String value) {
            addCriterion("cndr_class1_name_en not like", value, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnIn(List<String> values) {
            addCriterion("cndr_class1_name_en in", values, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnNotIn(List<String> values) {
            addCriterion("cndr_class1_name_en not in", values, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnBetween(String value1, String value2) {
            addCriterion("cndr_class1_name_en between", value1, value2, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass1NameEnNotBetween(String value1, String value2) {
            addCriterion("cndr_class1_name_en not between", value1, value2, "cndrClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeIsNull() {
            addCriterion("cndr_prop1_code is null");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeIsNotNull() {
            addCriterion("cndr_prop1_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeEqualTo(String value) {
            addCriterion("cndr_prop1_code =", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeNotEqualTo(String value) {
            addCriterion("cndr_prop1_code <>", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeGreaterThan(String value) {
            addCriterion("cndr_prop1_code >", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop1_code >=", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeLessThan(String value) {
            addCriterion("cndr_prop1_code <", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop1_code <=", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeLike(String value) {
            addCriterion("cndr_prop1_code like", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeNotLike(String value) {
            addCriterion("cndr_prop1_code not like", value, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeIn(List<String> values) {
            addCriterion("cndr_prop1_code in", values, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeNotIn(List<String> values) {
            addCriterion("cndr_prop1_code not in", values, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeBetween(String value1, String value2) {
            addCriterion("cndr_prop1_code between", value1, value2, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp1CodeNotBetween(String value1, String value2) {
            addCriterion("cndr_prop1_code not between", value1, value2, "cndrProp1Code");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnIsNull() {
            addCriterion("cndr_class2_name_en is null");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnIsNotNull() {
            addCriterion("cndr_class2_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnEqualTo(String value) {
            addCriterion("cndr_class2_name_en =", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnNotEqualTo(String value) {
            addCriterion("cndr_class2_name_en <>", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnGreaterThan(String value) {
            addCriterion("cndr_class2_name_en >", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_class2_name_en >=", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnLessThan(String value) {
            addCriterion("cndr_class2_name_en <", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnLessThanOrEqualTo(String value) {
            addCriterion("cndr_class2_name_en <=", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnLike(String value) {
            addCriterion("cndr_class2_name_en like", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnNotLike(String value) {
            addCriterion("cndr_class2_name_en not like", value, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnIn(List<String> values) {
            addCriterion("cndr_class2_name_en in", values, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnNotIn(List<String> values) {
            addCriterion("cndr_class2_name_en not in", values, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnBetween(String value1, String value2) {
            addCriterion("cndr_class2_name_en between", value1, value2, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrClass2NameEnNotBetween(String value1, String value2) {
            addCriterion("cndr_class2_name_en not between", value1, value2, "cndrClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeIsNull() {
            addCriterion("cndr_prop2_code is null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeIsNotNull() {
            addCriterion("cndr_prop2_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeEqualTo(String value) {
            addCriterion("cndr_prop2_code =", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeNotEqualTo(String value) {
            addCriterion("cndr_prop2_code <>", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeGreaterThan(String value) {
            addCriterion("cndr_prop2_code >", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndr_prop2_code >=", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeLessThan(String value) {
            addCriterion("cndr_prop2_code <", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeLessThanOrEqualTo(String value) {
            addCriterion("cndr_prop2_code <=", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeLike(String value) {
            addCriterion("cndr_prop2_code like", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeNotLike(String value) {
            addCriterion("cndr_prop2_code not like", value, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeIn(List<String> values) {
            addCriterion("cndr_prop2_code in", values, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeNotIn(List<String> values) {
            addCriterion("cndr_prop2_code not in", values, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeBetween(String value1, String value2) {
            addCriterion("cndr_prop2_code between", value1, value2, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrProp2CodeNotBetween(String value1, String value2) {
            addCriterion("cndr_prop2_code not between", value1, value2, "cndrProp2Code");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagIsNull() {
            addCriterion("cndr_type_flag is null");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagIsNotNull() {
            addCriterion("cndr_type_flag is not null");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagEqualTo(Integer value) {
            addCriterion("cndr_type_flag =", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagNotEqualTo(Integer value) {
            addCriterion("cndr_type_flag <>", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagGreaterThan(Integer value) {
            addCriterion("cndr_type_flag >", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("cndr_type_flag >=", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagLessThan(Integer value) {
            addCriterion("cndr_type_flag <", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagLessThanOrEqualTo(Integer value) {
            addCriterion("cndr_type_flag <=", value, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagIn(List<Integer> values) {
            addCriterion("cndr_type_flag in", values, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagNotIn(List<Integer> values) {
            addCriterion("cndr_type_flag not in", values, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagBetween(Integer value1, Integer value2) {
            addCriterion("cndr_type_flag between", value1, value2, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andCndrTypeFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("cndr_type_flag not between", value1, value2, "cndrTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagIsNull() {
            addCriterion("class_type_flag is null");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagIsNotNull() {
            addCriterion("class_type_flag is not null");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagEqualTo(Integer value) {
            addCriterion("class_type_flag =", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagNotEqualTo(Integer value) {
            addCriterion("class_type_flag <>", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagGreaterThan(Integer value) {
            addCriterion("class_type_flag >", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagGreaterThanOrEqualTo(Integer value) {
            addCriterion("class_type_flag >=", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagLessThan(Integer value) {
            addCriterion("class_type_flag <", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagLessThanOrEqualTo(Integer value) {
            addCriterion("class_type_flag <=", value, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagIn(List<Integer> values) {
            addCriterion("class_type_flag in", values, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagNotIn(List<Integer> values) {
            addCriterion("class_type_flag not in", values, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagBetween(Integer value1, Integer value2) {
            addCriterion("class_type_flag between", value1, value2, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andClassTypeFlagNotBetween(Integer value1, Integer value2) {
            addCriterion("class_type_flag not between", value1, value2, "classTypeFlag");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdIsNull() {
            addCriterion("link_class_or_class_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdIsNotNull() {
            addCriterion("link_class_or_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdEqualTo(String value) {
            addCriterion("link_class_or_class_id =", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdNotEqualTo(String value) {
            addCriterion("link_class_or_class_id <>", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdGreaterThan(String value) {
            addCriterion("link_class_or_class_id >", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("link_class_or_class_id >=", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdLessThan(String value) {
            addCriterion("link_class_or_class_id <", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdLessThanOrEqualTo(String value) {
            addCriterion("link_class_or_class_id <=", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdLike(String value) {
            addCriterion("link_class_or_class_id like", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdNotLike(String value) {
            addCriterion("link_class_or_class_id not like", value, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdIn(List<String> values) {
            addCriterion("link_class_or_class_id in", values, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdNotIn(List<String> values) {
            addCriterion("link_class_or_class_id not in", values, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdBetween(String value1, String value2) {
            addCriterion("link_class_or_class_id between", value1, value2, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClassOrClassIdNotBetween(String value1, String value2) {
            addCriterion("link_class_or_class_id not between", value1, value2, "linkClassOrClassId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdIsNull() {
            addCriterion("link_clss_id is null");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdIsNotNull() {
            addCriterion("link_clss_id is not null");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdEqualTo(String value) {
            addCriterion("link_clss_id =", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdNotEqualTo(String value) {
            addCriterion("link_clss_id <>", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdGreaterThan(String value) {
            addCriterion("link_clss_id >", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdGreaterThanOrEqualTo(String value) {
            addCriterion("link_clss_id >=", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdLessThan(String value) {
            addCriterion("link_clss_id <", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdLessThanOrEqualTo(String value) {
            addCriterion("link_clss_id <=", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdLike(String value) {
            addCriterion("link_clss_id like", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdNotLike(String value) {
            addCriterion("link_clss_id not like", value, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdIn(List<String> values) {
            addCriterion("link_clss_id in", values, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdNotIn(List<String> values) {
            addCriterion("link_clss_id not in", values, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdBetween(String value1, String value2) {
            addCriterion("link_clss_id between", value1, value2, "linkClssId");
            return (Criteria) this;
        }

        public Criteria andLinkClssIdNotBetween(String value1, String value2) {
            addCriterion("link_clss_id not between", value1, value2, "linkClssId");
            return (Criteria) this;
        }
        public Criteria andConstTypeIsNull() {
            addCriterion("const_type is null");
            return (Criteria) this;
        }

        public Criteria andConstTypeIsNotNull() {
            addCriterion("const_type is not null");
            return (Criteria) this;
        }

        public Criteria andConstTypeEqualTo(Integer value) {
            addCriterion("const_type =", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotEqualTo(Integer value) {
            addCriterion("const_type <>", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeGreaterThan(Integer value) {
            addCriterion("const_type >", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("const_type >=", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeLessThan(Integer value) {
            addCriterion("const_type <", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeLessThanOrEqualTo(Integer value) {
            addCriterion("const_type <=", value, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeIn(List<Integer> values) {
            addCriterion("const_type in", values, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotIn(List<Integer> values) {
            addCriterion("const_type not in", values, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeBetween(Integer value1, Integer value2) {
            addCriterion("const_type between", value1, value2, "ConstType");
            return (Criteria) this;
        }

        public Criteria andConstTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("const_type not between", value1, value2, "ConstType");
            return (Criteria) this;
        }
        public Criteria andCndtObjectNumberIsNull() {
            addCriterion("cndt_object_number is null");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberIsNotNull() {
            addCriterion("cndt_object_number is not null");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberEqualTo(String value) {
            addCriterion("cndt_object_number =", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberNotEqualTo(String value) {
            addCriterion("cndt_object_number <>", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberGreaterThan(String value) {
            addCriterion("cndt_object_number >", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_object_number >=", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberLessThan(String value) {
            addCriterion("cndt_object_number <", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberLessThanOrEqualTo(String value) {
            addCriterion("cndt_object_number <=", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberLike(String value) {
            addCriterion("cndt_object_number like", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberNotLike(String value) {
            addCriterion("cndt_object_number not like", value, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberIn(List<String> values) {
            addCriterion("cndt_object_number in", values, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberNotIn(List<String> values) {
            addCriterion("cndt_object_number not in", values, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberBetween(String value1, String value2) {
            addCriterion("cndt_object_number between", value1, value2, "CndtObjectNumber");
            return (Criteria) this;
        }

        public Criteria andCndtObjectNumberNotBetween(String value1, String value2) {
            addCriterion("cndt_object_number not between", value1, value2, "CndtObjectNumber");
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