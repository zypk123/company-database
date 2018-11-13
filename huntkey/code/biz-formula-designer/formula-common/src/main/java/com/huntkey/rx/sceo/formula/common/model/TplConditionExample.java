package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TplConditionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TplConditionExample() {
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

        public Criteria andCndtIdIsNull() {
            addCriterion("cndt_id is null");
            return (Criteria) this;
        }

        public Criteria andCndtIdIsNotNull() {
            addCriterion("cndt_id is not null");
            return (Criteria) this;
        }

        public Criteria andCndtIdEqualTo(String value) {
            addCriterion("cndt_id =", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdNotEqualTo(String value) {
            addCriterion("cndt_id <>", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdGreaterThan(String value) {
            addCriterion("cndt_id >", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_id >=", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdLessThan(String value) {
            addCriterion("cndt_id <", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdLessThanOrEqualTo(String value) {
            addCriterion("cndt_id <=", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdLike(String value) {
            addCriterion("cndt_id like", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdNotLike(String value) {
            addCriterion("cndt_id not like", value, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdIn(List<String> values) {
            addCriterion("cndt_id in", values, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdNotIn(List<String> values) {
            addCriterion("cndt_id not in", values, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdBetween(String value1, String value2) {
            addCriterion("cndt_id between", value1, value2, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtIdNotBetween(String value1, String value2) {
            addCriterion("cndt_id not between", value1, value2, "cndtId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdIsNull() {
            addCriterion("cndt_prop_clss_id is null");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdIsNotNull() {
            addCriterion("cndt_prop_clss_id is not null");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdEqualTo(String value) {
            addCriterion("cndt_prop_clss_id =", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdNotEqualTo(String value) {
            addCriterion("cndt_prop_clss_id <>", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdGreaterThan(String value) {
            addCriterion("cndt_prop_clss_id >", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_prop_clss_id >=", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdLessThan(String value) {
            addCriterion("cndt_prop_clss_id <", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdLessThanOrEqualTo(String value) {
            addCriterion("cndt_prop_clss_id <=", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdLike(String value) {
            addCriterion("cndt_prop_clss_id like", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdNotLike(String value) {
            addCriterion("cndt_prop_clss_id not like", value, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdIn(List<String> values) {
            addCriterion("cndt_prop_clss_id in", values, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdNotIn(List<String> values) {
            addCriterion("cndt_prop_clss_id not in", values, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdBetween(String value1, String value2) {
            addCriterion("cndt_prop_clss_id between", value1, value2, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtPropClssIdNotBetween(String value1, String value2) {
            addCriterion("cndt_prop_clss_id not between", value1, value2, "cndtPropClssId");
            return (Criteria) this;
        }

        public Criteria andCndtSeqIsNull() {
            addCriterion("cndt_seq is null");
            return (Criteria) this;
        }

        public Criteria andCndtSeqIsNotNull() {
            addCriterion("cndt_seq is not null");
            return (Criteria) this;
        }

        public Criteria andCndtSeqEqualTo(Integer value) {
            addCriterion("cndt_seq =", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqNotEqualTo(Integer value) {
            addCriterion("cndt_seq <>", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqGreaterThan(Integer value) {
            addCriterion("cndt_seq >", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("cndt_seq >=", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqLessThan(Integer value) {
            addCriterion("cndt_seq <", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqLessThanOrEqualTo(Integer value) {
            addCriterion("cndt_seq <=", value, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqIn(List<Integer> values) {
            addCriterion("cndt_seq in", values, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqNotIn(List<Integer> values) {
            addCriterion("cndt_seq not in", values, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqBetween(Integer value1, Integer value2) {
            addCriterion("cndt_seq between", value1, value2, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("cndt_seq not between", value1, value2, "cndtSeq");
            return (Criteria) this;
        }

        public Criteria andCndtPropIsNull() {
            addCriterion("cndt_prop is null");
            return (Criteria) this;
        }

        public Criteria andCndtPropIsNotNull() {
            addCriterion("cndt_prop is not null");
            return (Criteria) this;
        }

        public Criteria andCndtPropEqualTo(String value) {
            addCriterion("cndt_prop =", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropNotEqualTo(String value) {
            addCriterion("cndt_prop <>", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropGreaterThan(String value) {
            addCriterion("cndt_prop >", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_prop >=", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropLessThan(String value) {
            addCriterion("cndt_prop <", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropLessThanOrEqualTo(String value) {
            addCriterion("cndt_prop <=", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropLike(String value) {
            addCriterion("cndt_prop like", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropNotLike(String value) {
            addCriterion("cndt_prop not like", value, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropIn(List<String> values) {
            addCriterion("cndt_prop in", values, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropNotIn(List<String> values) {
            addCriterion("cndt_prop not in", values, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropBetween(String value1, String value2) {
            addCriterion("cndt_prop between", value1, value2, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtPropNotBetween(String value1, String value2) {
            addCriterion("cndt_prop not between", value1, value2, "cndtProp");
            return (Criteria) this;
        }

        public Criteria andCndtOperateIsNull() {
            addCriterion("cndt_operate is null");
            return (Criteria) this;
        }

        public Criteria andCndtOperateIsNotNull() {
            addCriterion("cndt_operate is not null");
            return (Criteria) this;
        }

        public Criteria andCndtOperateEqualTo(String value) {
            addCriterion("cndt_operate =", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateNotEqualTo(String value) {
            addCriterion("cndt_operate <>", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateGreaterThan(String value) {
            addCriterion("cndt_operate >", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_operate >=", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateLessThan(String value) {
            addCriterion("cndt_operate <", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateLessThanOrEqualTo(String value) {
            addCriterion("cndt_operate <=", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateLike(String value) {
            addCriterion("cndt_operate like", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateNotLike(String value) {
            addCriterion("cndt_operate not like", value, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateIn(List<String> values) {
            addCriterion("cndt_operate in", values, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateNotIn(List<String> values) {
            addCriterion("cndt_operate not in", values, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateBetween(String value1, String value2) {
            addCriterion("cndt_operate between", value1, value2, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtOperateNotBetween(String value1, String value2) {
            addCriterion("cndt_operate not between", value1, value2, "cndtOperate");
            return (Criteria) this;
        }

        public Criteria andCndtValueIsNull() {
            addCriterion("cndt_value is null");
            return (Criteria) this;
        }

        public Criteria andCndtValueIsNotNull() {
            addCriterion("cndt_value is not null");
            return (Criteria) this;
        }

        public Criteria andCndtValueEqualTo(String value) {
            addCriterion("cndt_value =", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueNotEqualTo(String value) {
            addCriterion("cndt_value <>", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueGreaterThan(String value) {
            addCriterion("cndt_value >", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_value >=", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueLessThan(String value) {
            addCriterion("cndt_value <", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueLessThanOrEqualTo(String value) {
            addCriterion("cndt_value <=", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueLike(String value) {
            addCriterion("cndt_value like", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueNotLike(String value) {
            addCriterion("cndt_value not like", value, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueIn(List<String> values) {
            addCriterion("cndt_value in", values, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueNotIn(List<String> values) {
            addCriterion("cndt_value not in", values, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueBetween(String value1, String value2) {
            addCriterion("cndt_value between", value1, value2, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueNotBetween(String value1, String value2) {
            addCriterion("cndt_value not between", value1, value2, "cndtValue");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeIsNull() {
            addCriterion("cndt_value_type is null");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeIsNotNull() {
            addCriterion("cndt_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeEqualTo(String value) {
            addCriterion("cndt_value_type =", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeNotEqualTo(String value) {
            addCriterion("cndt_value_type <>", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeGreaterThan(String value) {
            addCriterion("cndt_value_type >", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_value_type >=", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeLessThan(String value) {
            addCriterion("cndt_value_type <", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeLessThanOrEqualTo(String value) {
            addCriterion("cndt_value_type <=", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeLike(String value) {
            addCriterion("cndt_value_type like", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeNotLike(String value) {
            addCriterion("cndt_value_type not like", value, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeIn(List<String> values) {
            addCriterion("cndt_value_type in", values, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeNotIn(List<String> values) {
            addCriterion("cndt_value_type not in", values, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeBetween(String value1, String value2) {
            addCriterion("cndt_value_type between", value1, value2, "cndtValueType");
            return (Criteria) this;
        }

        public Criteria andCndtValueTypeNotBetween(String value1, String value2) {
            addCriterion("cndt_value_type not between", value1, value2, "cndtValueType");
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

        public Criteria andCndtValueClassIdIsNull() {
            addCriterion("cndt_value_class_id is null");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdIsNotNull() {
            addCriterion("cndt_value_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdEqualTo(String value) {
            addCriterion("cndt_value_class_id =", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdNotEqualTo(String value) {
            addCriterion("cndt_value_class_id <>", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdGreaterThan(String value) {
            addCriterion("cndt_value_class_id >", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_value_class_id >=", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdLessThan(String value) {
            addCriterion("cndt_value_class_id <", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdLessThanOrEqualTo(String value) {
            addCriterion("cndt_value_class_id <=", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdLike(String value) {
            addCriterion("cndt_value_class_id like", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdNotLike(String value) {
            addCriterion("cndt_value_class_id not like", value, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdIn(List<String> values) {
            addCriterion("cndt_value_class_id in", values, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdNotIn(List<String> values) {
            addCriterion("cndt_value_class_id not in", values, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdBetween(String value1, String value2) {
            addCriterion("cndt_value_class_id between", value1, value2, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtValueClassIdNotBetween(String value1, String value2) {
            addCriterion("cndt_value_class_id not between", value1, value2, "cndtValueClassId");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeIsNull() {
            addCriterion("cndt_prop_origin_code is null");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeIsNotNull() {
            addCriterion("cndt_prop_origin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeEqualTo(String value) {
            addCriterion("cndt_prop_origin_code =", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeNotEqualTo(String value) {
            addCriterion("cndt_prop_origin_code <>", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeGreaterThan(String value) {
            addCriterion("cndt_prop_origin_code >", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_prop_origin_code >=", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeLessThan(String value) {
            addCriterion("cndt_prop_origin_code <", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeLessThanOrEqualTo(String value) {
            addCriterion("cndt_prop_origin_code <=", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeLike(String value) {
            addCriterion("cndt_prop_origin_code like", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeNotLike(String value) {
            addCriterion("cndt_prop_origin_code not like", value, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeIn(List<String> values) {
            addCriterion("cndt_prop_origin_code in", values, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeNotIn(List<String> values) {
            addCriterion("cndt_prop_origin_code not in", values, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeBetween(String value1, String value2) {
            addCriterion("cndt_prop_origin_code between", value1, value2, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtPropOriginCodeNotBetween(String value1, String value2) {
            addCriterion("cndt_prop_origin_code not between", value1, value2, "cndtPropOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeIsNull() {
            addCriterion("cndt_value_origin_code is null");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeIsNotNull() {
            addCriterion("cndt_value_origin_code is not null");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeEqualTo(String value) {
            addCriterion("cndt_value_origin_code =", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeNotEqualTo(String value) {
            addCriterion("cndt_value_origin_code <>", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeGreaterThan(String value) {
            addCriterion("cndt_value_origin_code >", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeGreaterThanOrEqualTo(String value) {
            addCriterion("cndt_value_origin_code >=", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeLessThan(String value) {
            addCriterion("cndt_value_origin_code <", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeLessThanOrEqualTo(String value) {
            addCriterion("cndt_value_origin_code <=", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeLike(String value) {
            addCriterion("cndt_value_origin_code like", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeNotLike(String value) {
            addCriterion("cndt_value_origin_code not like", value, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeIn(List<String> values) {
            addCriterion("cndt_value_origin_code in", values, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeNotIn(List<String> values) {
            addCriterion("cndt_value_origin_code not in", values, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeBetween(String value1, String value2) {
            addCriterion("cndt_value_origin_code between", value1, value2, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andCndtValueOriginCodeNotBetween(String value1, String value2) {
            addCriterion("cndt_value_origin_code not between", value1, value2, "cndtValueOriginCode");
            return (Criteria) this;
        }

        public Criteria andPreVarcharIsNull() {
            addCriterion("pre_varchar is null");
            return (Criteria) this;
        }

        public Criteria andPreVarcharIsNotNull() {
            addCriterion("pre_varchar is not null");
            return (Criteria) this;
        }

        public Criteria andPreVarcharEqualTo(String value) {
            addCriterion("pre_varchar =", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharNotEqualTo(String value) {
            addCriterion("pre_varchar <>", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharGreaterThan(String value) {
            addCriterion("pre_varchar >", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharGreaterThanOrEqualTo(String value) {
            addCriterion("pre_varchar >=", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharLessThan(String value) {
            addCriterion("pre_varchar <", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharLessThanOrEqualTo(String value) {
            addCriterion("pre_varchar <=", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharLike(String value) {
            addCriterion("pre_varchar like", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharNotLike(String value) {
            addCriterion("pre_varchar not like", value, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharIn(List<String> values) {
            addCriterion("pre_varchar in", values, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharNotIn(List<String> values) {
            addCriterion("pre_varchar not in", values, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharBetween(String value1, String value2) {
            addCriterion("pre_varchar between", value1, value2, "preVarchar");
            return (Criteria) this;
        }

        public Criteria andPreVarcharNotBetween(String value1, String value2) {
            addCriterion("pre_varchar not between", value1, value2, "preVarchar");
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