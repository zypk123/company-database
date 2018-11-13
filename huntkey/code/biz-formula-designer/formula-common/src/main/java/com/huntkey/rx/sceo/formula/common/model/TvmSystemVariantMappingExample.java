package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TvmSystemVariantMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TvmSystemVariantMappingExample() {
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

        public Criteria andSystIdIsNull() {
            addCriterion("syst_id is null");
            return (Criteria) this;
        }

        public Criteria andSystIdIsNotNull() {
            addCriterion("syst_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystIdEqualTo(String value) {
            addCriterion("syst_id =", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdNotEqualTo(String value) {
            addCriterion("syst_id <>", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdGreaterThan(String value) {
            addCriterion("syst_id >", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdGreaterThanOrEqualTo(String value) {
            addCriterion("syst_id >=", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdLessThan(String value) {
            addCriterion("syst_id <", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdLessThanOrEqualTo(String value) {
            addCriterion("syst_id <=", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdLike(String value) {
            addCriterion("syst_id like", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdNotLike(String value) {
            addCriterion("syst_id not like", value, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdIn(List<String> values) {
            addCriterion("syst_id in", values, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdNotIn(List<String> values) {
            addCriterion("syst_id not in", values, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdBetween(String value1, String value2) {
            addCriterion("syst_id between", value1, value2, "systId");
            return (Criteria) this;
        }

        public Criteria andSystIdNotBetween(String value1, String value2) {
            addCriterion("syst_id not between", value1, value2, "systId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdIsNull() {
            addCriterion("syst_formula_id is null");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdIsNotNull() {
            addCriterion("syst_formula_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdEqualTo(String value) {
            addCriterion("syst_formula_id =", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdNotEqualTo(String value) {
            addCriterion("syst_formula_id <>", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdGreaterThan(String value) {
            addCriterion("syst_formula_id >", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdGreaterThanOrEqualTo(String value) {
            addCriterion("syst_formula_id >=", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdLessThan(String value) {
            addCriterion("syst_formula_id <", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdLessThanOrEqualTo(String value) {
            addCriterion("syst_formula_id <=", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdLike(String value) {
            addCriterion("syst_formula_id like", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdNotLike(String value) {
            addCriterion("syst_formula_id not like", value, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdIn(List<String> values) {
            addCriterion("syst_formula_id in", values, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdNotIn(List<String> values) {
            addCriterion("syst_formula_id not in", values, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdBetween(String value1, String value2) {
            addCriterion("syst_formula_id between", value1, value2, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystFormulaIdNotBetween(String value1, String value2) {
            addCriterion("syst_formula_id not between", value1, value2, "systFormulaId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdIsNull() {
            addCriterion("syst_var_id is null");
            return (Criteria) this;
        }

        public Criteria andSystVarIdIsNotNull() {
            addCriterion("syst_var_id is not null");
            return (Criteria) this;
        }

        public Criteria andSystVarIdEqualTo(String value) {
            addCriterion("syst_var_id =", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdNotEqualTo(String value) {
            addCriterion("syst_var_id <>", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdGreaterThan(String value) {
            addCriterion("syst_var_id >", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdGreaterThanOrEqualTo(String value) {
            addCriterion("syst_var_id >=", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdLessThan(String value) {
            addCriterion("syst_var_id <", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdLessThanOrEqualTo(String value) {
            addCriterion("syst_var_id <=", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdLike(String value) {
            addCriterion("syst_var_id like", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdNotLike(String value) {
            addCriterion("syst_var_id not like", value, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdIn(List<String> values) {
            addCriterion("syst_var_id in", values, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdNotIn(List<String> values) {
            addCriterion("syst_var_id not in", values, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdBetween(String value1, String value2) {
            addCriterion("syst_var_id between", value1, value2, "systVarId");
            return (Criteria) this;
        }

        public Criteria andSystVarIdNotBetween(String value1, String value2) {
            addCriterion("syst_var_id not between", value1, value2, "systVarId");
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