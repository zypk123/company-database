package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TplPropertyLimitExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TplPropertyLimitExample() {
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

        public Criteria andPrprIdIsNull() {
            addCriterion("prpr_id is null");
            return (Criteria) this;
        }

        public Criteria andPrprIdIsNotNull() {
            addCriterion("prpr_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrprIdEqualTo(String value) {
            addCriterion("prpr_id =", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdNotEqualTo(String value) {
            addCriterion("prpr_id <>", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdGreaterThan(String value) {
            addCriterion("prpr_id >", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_id >=", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdLessThan(String value) {
            addCriterion("prpr_id <", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdLessThanOrEqualTo(String value) {
            addCriterion("prpr_id <=", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdLike(String value) {
            addCriterion("prpr_id like", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdNotLike(String value) {
            addCriterion("prpr_id not like", value, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdIn(List<String> values) {
            addCriterion("prpr_id in", values, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdNotIn(List<String> values) {
            addCriterion("prpr_id not in", values, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdBetween(String value1, String value2) {
            addCriterion("prpr_id between", value1, value2, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprIdNotBetween(String value1, String value2) {
            addCriterion("prpr_id not between", value1, value2, "prprId");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataIsNull() {
            addCriterion("prpr_prop_mata is null");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataIsNotNull() {
            addCriterion("prpr_prop_mata is not null");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataEqualTo(String value) {
            addCriterion("prpr_prop_mata =", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataNotEqualTo(String value) {
            addCriterion("prpr_prop_mata <>", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataGreaterThan(String value) {
            addCriterion("prpr_prop_mata >", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_prop_mata >=", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataLessThan(String value) {
            addCriterion("prpr_prop_mata <", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataLessThanOrEqualTo(String value) {
            addCriterion("prpr_prop_mata <=", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataLike(String value) {
            addCriterion("prpr_prop_mata like", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataNotLike(String value) {
            addCriterion("prpr_prop_mata not like", value, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataIn(List<String> values) {
            addCriterion("prpr_prop_mata in", values, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataNotIn(List<String> values) {
            addCriterion("prpr_prop_mata not in", values, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataBetween(String value1, String value2) {
            addCriterion("prpr_prop_mata between", value1, value2, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropMataNotBetween(String value1, String value2) {
            addCriterion("prpr_prop_mata not between", value1, value2, "prprPropMata");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayIsNull() {
            addCriterion("prpr_prop_display is null");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayIsNotNull() {
            addCriterion("prpr_prop_display is not null");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayEqualTo(String value) {
            addCriterion("prpr_prop_display =", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayNotEqualTo(String value) {
            addCriterion("prpr_prop_display <>", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayGreaterThan(String value) {
            addCriterion("prpr_prop_display >", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_prop_display >=", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayLessThan(String value) {
            addCriterion("prpr_prop_display <", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayLessThanOrEqualTo(String value) {
            addCriterion("prpr_prop_display <=", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayLike(String value) {
            addCriterion("prpr_prop_display like", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayNotLike(String value) {
            addCriterion("prpr_prop_display not like", value, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayIn(List<String> values) {
            addCriterion("prpr_prop_display in", values, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayNotIn(List<String> values) {
            addCriterion("prpr_prop_display not in", values, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayBetween(String value1, String value2) {
            addCriterion("prpr_prop_display between", value1, value2, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprPropDisplayNotBetween(String value1, String value2) {
            addCriterion("prpr_prop_display not between", value1, value2, "prprPropDisplay");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaIsNull() {
            addCriterion("prpr_condition_formula is null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaIsNotNull() {
            addCriterion("prpr_condition_formula is not null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaEqualTo(String value) {
            addCriterion("prpr_condition_formula =", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaNotEqualTo(String value) {
            addCriterion("prpr_condition_formula <>", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaGreaterThan(String value) {
            addCriterion("prpr_condition_formula >", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_condition_formula >=", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaLessThan(String value) {
            addCriterion("prpr_condition_formula <", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaLessThanOrEqualTo(String value) {
            addCriterion("prpr_condition_formula <=", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaLike(String value) {
            addCriterion("prpr_condition_formula like", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaNotLike(String value) {
            addCriterion("prpr_condition_formula not like", value, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaIn(List<String> values) {
            addCriterion("prpr_condition_formula in", values, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaNotIn(List<String> values) {
            addCriterion("prpr_condition_formula not in", values, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaBetween(String value1, String value2) {
            addCriterion("prpr_condition_formula between", value1, value2, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionFormulaNotBetween(String value1, String value2) {
            addCriterion("prpr_condition_formula not between", value1, value2, "prprConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameIsNull() {
            addCriterion("prpr_condition_name is null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameIsNotNull() {
            addCriterion("prpr_condition_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameEqualTo(String value) {
            addCriterion("prpr_condition_name =", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameNotEqualTo(String value) {
            addCriterion("prpr_condition_name <>", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameGreaterThan(String value) {
            addCriterion("prpr_condition_name >", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_condition_name >=", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameLessThan(String value) {
            addCriterion("prpr_condition_name <", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameLessThanOrEqualTo(String value) {
            addCriterion("prpr_condition_name <=", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameLike(String value) {
            addCriterion("prpr_condition_name like", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameNotLike(String value) {
            addCriterion("prpr_condition_name not like", value, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameIn(List<String> values) {
            addCriterion("prpr_condition_name in", values, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameNotIn(List<String> values) {
            addCriterion("prpr_condition_name not in", values, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameBetween(String value1, String value2) {
            addCriterion("prpr_condition_name between", value1, value2, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionNameNotBetween(String value1, String value2) {
            addCriterion("prpr_condition_name not between", value1, value2, "prprConditionName");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescIsNull() {
            addCriterion("prpr_condition_desc is null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescIsNotNull() {
            addCriterion("prpr_condition_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescEqualTo(String value) {
            addCriterion("prpr_condition_desc =", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescNotEqualTo(String value) {
            addCriterion("prpr_condition_desc <>", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescGreaterThan(String value) {
            addCriterion("prpr_condition_desc >", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescGreaterThanOrEqualTo(String value) {
            addCriterion("prpr_condition_desc >=", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescLessThan(String value) {
            addCriterion("prpr_condition_desc <", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescLessThanOrEqualTo(String value) {
            addCriterion("prpr_condition_desc <=", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescLike(String value) {
            addCriterion("prpr_condition_desc like", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescNotLike(String value) {
            addCriterion("prpr_condition_desc not like", value, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescIn(List<String> values) {
            addCriterion("prpr_condition_desc in", values, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescNotIn(List<String> values) {
            addCriterion("prpr_condition_desc not in", values, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescBetween(String value1, String value2) {
            addCriterion("prpr_condition_desc between", value1, value2, "prprConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrprConditionDescNotBetween(String value1, String value2) {
            addCriterion("prpr_condition_desc not between", value1, value2, "prprConditionDesc");
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