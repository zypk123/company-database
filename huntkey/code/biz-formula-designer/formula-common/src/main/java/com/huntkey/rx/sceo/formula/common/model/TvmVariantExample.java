package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TvmVariantExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TvmVariantExample() {
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

        public Criteria andVrntIdIsNull() {
            addCriterion("vrnt_id is null");
            return (Criteria) this;
        }

        public Criteria andVrntIdIsNotNull() {
            addCriterion("vrnt_id is not null");
            return (Criteria) this;
        }

        public Criteria andVrntIdEqualTo(String value) {
            addCriterion("vrnt_id =", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdNotEqualTo(String value) {
            addCriterion("vrnt_id <>", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdGreaterThan(String value) {
            addCriterion("vrnt_id >", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_id >=", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdLessThan(String value) {
            addCriterion("vrnt_id <", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdLessThanOrEqualTo(String value) {
            addCriterion("vrnt_id <=", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdLike(String value) {
            addCriterion("vrnt_id like", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdNotLike(String value) {
            addCriterion("vrnt_id not like", value, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdIn(List<String> values) {
            addCriterion("vrnt_id in", values, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdNotIn(List<String> values) {
            addCriterion("vrnt_id not in", values, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdBetween(String value1, String value2) {
            addCriterion("vrnt_id between", value1, value2, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntIdNotBetween(String value1, String value2) {
            addCriterion("vrnt_id not between", value1, value2, "vrntId");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameIsNull() {
            addCriterion("vrnt_var_name is null");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameIsNotNull() {
            addCriterion("vrnt_var_name is not null");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameEqualTo(String value) {
            addCriterion("vrnt_var_name =", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameNotEqualTo(String value) {
            addCriterion("vrnt_var_name <>", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameGreaterThan(String value) {
            addCriterion("vrnt_var_name >", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_var_name >=", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameLessThan(String value) {
            addCriterion("vrnt_var_name <", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameLessThanOrEqualTo(String value) {
            addCriterion("vrnt_var_name <=", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameLike(String value) {
            addCriterion("vrnt_var_name like", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameNotLike(String value) {
            addCriterion("vrnt_var_name not like", value, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameIn(List<String> values) {
            addCriterion("vrnt_var_name in", values, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameNotIn(List<String> values) {
            addCriterion("vrnt_var_name not in", values, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameBetween(String value1, String value2) {
            addCriterion("vrnt_var_name between", value1, value2, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarNameNotBetween(String value1, String value2) {
            addCriterion("vrnt_var_name not between", value1, value2, "vrntVarName");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescIsNull() {
            addCriterion("vrnt_var_desc is null");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescIsNotNull() {
            addCriterion("vrnt_var_desc is not null");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescEqualTo(String value) {
            addCriterion("vrnt_var_desc =", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescNotEqualTo(String value) {
            addCriterion("vrnt_var_desc <>", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescGreaterThan(String value) {
            addCriterion("vrnt_var_desc >", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_var_desc >=", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescLessThan(String value) {
            addCriterion("vrnt_var_desc <", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescLessThanOrEqualTo(String value) {
            addCriterion("vrnt_var_desc <=", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescLike(String value) {
            addCriterion("vrnt_var_desc like", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescNotLike(String value) {
            addCriterion("vrnt_var_desc not like", value, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescIn(List<String> values) {
            addCriterion("vrnt_var_desc in", values, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescNotIn(List<String> values) {
            addCriterion("vrnt_var_desc not in", values, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescBetween(String value1, String value2) {
            addCriterion("vrnt_var_desc between", value1, value2, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andVrntVarDescNotBetween(String value1, String value2) {
            addCriterion("vrnt_var_desc not between", value1, value2, "vrntVarDesc");
            return (Criteria) this;
        }

        public Criteria andFormulaIdIsNull() {
            addCriterion("formula_id is null");
            return (Criteria) this;
        }

        public Criteria andFormulaIdIsNotNull() {
            addCriterion("formula_id is not null");
            return (Criteria) this;
        }

        public Criteria andFormulaIdEqualTo(String value) {
            addCriterion("formula_id =", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdNotEqualTo(String value) {
            addCriterion("formula_id <>", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdGreaterThan(String value) {
            addCriterion("formula_id >", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdGreaterThanOrEqualTo(String value) {
            addCriterion("formula_id >=", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdLessThan(String value) {
            addCriterion("formula_id <", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdLessThanOrEqualTo(String value) {
            addCriterion("formula_id <=", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdLike(String value) {
            addCriterion("formula_id like", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdNotLike(String value) {
            addCriterion("formula_id not like", value, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdIn(List<String> values) {
            addCriterion("formula_id in", values, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdNotIn(List<String> values) {
            addCriterion("formula_id not in", values, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdBetween(String value1, String value2) {
            addCriterion("formula_id between", value1, value2, "formulaId");
            return (Criteria) this;
        }

        public Criteria andFormulaIdNotBetween(String value1, String value2) {
            addCriterion("formula_id not between", value1, value2, "formulaId");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeIsNull() {
            addCriterion("vrnt_var_type is null");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeIsNotNull() {
            addCriterion("vrnt_var_type is not null");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeEqualTo(String value) {
            addCriterion("vrnt_var_type =", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeNotEqualTo(String value) {
            addCriterion("vrnt_var_type <>", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeGreaterThan(String value) {
            addCriterion("vrnt_var_type >", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_var_type >=", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeLessThan(String value) {
            addCriterion("vrnt_var_type <", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeLessThanOrEqualTo(String value) {
            addCriterion("vrnt_var_type <=", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeLike(String value) {
            addCriterion("vrnt_var_type like", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeNotLike(String value) {
            addCriterion("vrnt_var_type not like", value, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeIn(List<String> values) {
            addCriterion("vrnt_var_type in", values, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeNotIn(List<String> values) {
            addCriterion("vrnt_var_type not in", values, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeBetween(String value1, String value2) {
            addCriterion("vrnt_var_type between", value1, value2, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarTypeNotBetween(String value1, String value2) {
            addCriterion("vrnt_var_type not between", value1, value2, "vrntVarType");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeIsNull() {
            addCriterion("vrnt_var_scope is null");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeIsNotNull() {
            addCriterion("vrnt_var_scope is not null");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeEqualTo(String value) {
            addCriterion("vrnt_var_scope =", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeNotEqualTo(String value) {
            addCriterion("vrnt_var_scope <>", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeGreaterThan(String value) {
            addCriterion("vrnt_var_scope >", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_var_scope >=", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeLessThan(String value) {
            addCriterion("vrnt_var_scope <", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeLessThanOrEqualTo(String value) {
            addCriterion("vrnt_var_scope <=", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeLike(String value) {
            addCriterion("vrnt_var_scope like", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeNotLike(String value) {
            addCriterion("vrnt_var_scope not like", value, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeIn(List<String> values) {
            addCriterion("vrnt_var_scope in", values, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeNotIn(List<String> values) {
            addCriterion("vrnt_var_scope not in", values, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeBetween(String value1, String value2) {
            addCriterion("vrnt_var_scope between", value1, value2, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntVarScopeNotBetween(String value1, String value2) {
            addCriterion("vrnt_var_scope not between", value1, value2, "vrntVarScope");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdIsNull() {
            addCriterion("vrnt_formula_id is null");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdIsNotNull() {
            addCriterion("vrnt_formula_id is not null");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdEqualTo(String value) {
            addCriterion("vrnt_formula_id =", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdNotEqualTo(String value) {
            addCriterion("vrnt_formula_id <>", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdGreaterThan(String value) {
            addCriterion("vrnt_formula_id >", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_formula_id >=", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdLessThan(String value) {
            addCriterion("vrnt_formula_id <", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdLessThanOrEqualTo(String value) {
            addCriterion("vrnt_formula_id <=", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdLike(String value) {
            addCriterion("vrnt_formula_id like", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdNotLike(String value) {
            addCriterion("vrnt_formula_id not like", value, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdIn(List<String> values) {
            addCriterion("vrnt_formula_id in", values, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdNotIn(List<String> values) {
            addCriterion("vrnt_formula_id not in", values, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdBetween(String value1, String value2) {
            addCriterion("vrnt_formula_id between", value1, value2, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntFormulaIdNotBetween(String value1, String value2) {
            addCriterion("vrnt_formula_id not between", value1, value2, "vrntFormulaId");
            return (Criteria) this;
        }

        public Criteria andVrntStateIsNull() {
            addCriterion("vrnt_state is null");
            return (Criteria) this;
        }

        public Criteria andVrntStateIsNotNull() {
            addCriterion("vrnt_state is not null");
            return (Criteria) this;
        }

        public Criteria andVrntStateEqualTo(String value) {
            addCriterion("vrnt_state =", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateNotEqualTo(String value) {
            addCriterion("vrnt_state <>", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateGreaterThan(String value) {
            addCriterion("vrnt_state >", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_state >=", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateLessThan(String value) {
            addCriterion("vrnt_state <", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateLessThanOrEqualTo(String value) {
            addCriterion("vrnt_state <=", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateLike(String value) {
            addCriterion("vrnt_state like", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateNotLike(String value) {
            addCriterion("vrnt_state not like", value, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateIn(List<String> values) {
            addCriterion("vrnt_state in", values, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateNotIn(List<String> values) {
            addCriterion("vrnt_state not in", values, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateBetween(String value1, String value2) {
            addCriterion("vrnt_state between", value1, value2, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntStateNotBetween(String value1, String value2) {
            addCriterion("vrnt_state not between", value1, value2, "vrntState");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksIsNull() {
            addCriterion("vrnt_modify_remarks is null");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksIsNotNull() {
            addCriterion("vrnt_modify_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksEqualTo(String value) {
            addCriterion("vrnt_modify_remarks =", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksNotEqualTo(String value) {
            addCriterion("vrnt_modify_remarks <>", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksGreaterThan(String value) {
            addCriterion("vrnt_modify_remarks >", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("vrnt_modify_remarks >=", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksLessThan(String value) {
            addCriterion("vrnt_modify_remarks <", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksLessThanOrEqualTo(String value) {
            addCriterion("vrnt_modify_remarks <=", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksLike(String value) {
            addCriterion("vrnt_modify_remarks like", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksNotLike(String value) {
            addCriterion("vrnt_modify_remarks not like", value, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksIn(List<String> values) {
            addCriterion("vrnt_modify_remarks in", values, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksNotIn(List<String> values) {
            addCriterion("vrnt_modify_remarks not in", values, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksBetween(String value1, String value2) {
            addCriterion("vrnt_modify_remarks between", value1, value2, "vrntModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andVrntModifyRemarksNotBetween(String value1, String value2) {
            addCriterion("vrnt_modify_remarks not between", value1, value2, "vrntModifyRemarks");
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