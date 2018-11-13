package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfdClassRelatedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TfdClassRelatedExample() {
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

        public Criteria andClssIdIsNull() {
            addCriterion("clss_id is null");
            return (Criteria) this;
        }

        public Criteria andClssIdIsNotNull() {
            addCriterion("clss_id is not null");
            return (Criteria) this;
        }

        public Criteria andClssIdEqualTo(String value) {
            addCriterion("clss_id =", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdNotEqualTo(String value) {
            addCriterion("clss_id <>", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdGreaterThan(String value) {
            addCriterion("clss_id >", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdGreaterThanOrEqualTo(String value) {
            addCriterion("clss_id >=", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdLessThan(String value) {
            addCriterion("clss_id <", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdLessThanOrEqualTo(String value) {
            addCriterion("clss_id <=", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdLike(String value) {
            addCriterion("clss_id like", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdNotLike(String value) {
            addCriterion("clss_id not like", value, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdIn(List<String> values) {
            addCriterion("clss_id in", values, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdNotIn(List<String> values) {
            addCriterion("clss_id not in", values, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdBetween(String value1, String value2) {
            addCriterion("clss_id between", value1, value2, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssIdNotBetween(String value1, String value2) {
            addCriterion("clss_id not between", value1, value2, "clssId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdIsNull() {
            addCriterion("clss_class_id is null");
            return (Criteria) this;
        }

        public Criteria andClssClassIdIsNotNull() {
            addCriterion("clss_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClssClassIdEqualTo(String value) {
            addCriterion("clss_class_id =", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdNotEqualTo(String value) {
            addCriterion("clss_class_id <>", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdGreaterThan(String value) {
            addCriterion("clss_class_id >", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("clss_class_id >=", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdLessThan(String value) {
            addCriterion("clss_class_id <", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdLessThanOrEqualTo(String value) {
            addCriterion("clss_class_id <=", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdLike(String value) {
            addCriterion("clss_class_id like", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdNotLike(String value) {
            addCriterion("clss_class_id not like", value, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdIn(List<String> values) {
            addCriterion("clss_class_id in", values, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdNotIn(List<String> values) {
            addCriterion("clss_class_id not in", values, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdBetween(String value1, String value2) {
            addCriterion("clss_class_id between", value1, value2, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssClassIdNotBetween(String value1, String value2) {
            addCriterion("clss_class_id not between", value1, value2, "clssClassId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdIsNull() {
            addCriterion("clss_formula_id is null");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdIsNotNull() {
            addCriterion("clss_formula_id is not null");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdEqualTo(String value) {
            addCriterion("clss_formula_id =", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdNotEqualTo(String value) {
            addCriterion("clss_formula_id <>", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdGreaterThan(String value) {
            addCriterion("clss_formula_id >", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdGreaterThanOrEqualTo(String value) {
            addCriterion("clss_formula_id >=", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdLessThan(String value) {
            addCriterion("clss_formula_id <", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdLessThanOrEqualTo(String value) {
            addCriterion("clss_formula_id <=", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdLike(String value) {
            addCriterion("clss_formula_id like", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdNotLike(String value) {
            addCriterion("clss_formula_id not like", value, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdIn(List<String> values) {
            addCriterion("clss_formula_id in", values, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdNotIn(List<String> values) {
            addCriterion("clss_formula_id not in", values, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdBetween(String value1, String value2) {
            addCriterion("clss_formula_id between", value1, value2, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssFormulaIdNotBetween(String value1, String value2) {
            addCriterion("clss_formula_id not between", value1, value2, "clssFormulaId");
            return (Criteria) this;
        }

        public Criteria andClssSeqIsNull() {
            addCriterion("clss_seq is null");
            return (Criteria) this;
        }

        public Criteria andClssSeqIsNotNull() {
            addCriterion("clss_seq is not null");
            return (Criteria) this;
        }

        public Criteria andClssSeqEqualTo(Integer value) {
            addCriterion("clss_seq =", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqNotEqualTo(Integer value) {
            addCriterion("clss_seq <>", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqGreaterThan(Integer value) {
            addCriterion("clss_seq >", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("clss_seq >=", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqLessThan(Integer value) {
            addCriterion("clss_seq <", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqLessThanOrEqualTo(Integer value) {
            addCriterion("clss_seq <=", value, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqIn(List<Integer> values) {
            addCriterion("clss_seq in", values, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqNotIn(List<Integer> values) {
            addCriterion("clss_seq not in", values, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqBetween(Integer value1, Integer value2) {
            addCriterion("clss_seq between", value1, value2, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("clss_seq not between", value1, value2, "clssSeq");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameIsNull() {
            addCriterion("clss_class_related_name is null");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameIsNotNull() {
            addCriterion("clss_class_related_name is not null");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameEqualTo(String value) {
            addCriterion("clss_class_related_name =", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameNotEqualTo(String value) {
            addCriterion("clss_class_related_name <>", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameGreaterThan(String value) {
            addCriterion("clss_class_related_name >", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameGreaterThanOrEqualTo(String value) {
            addCriterion("clss_class_related_name >=", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameLessThan(String value) {
            addCriterion("clss_class_related_name <", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameLessThanOrEqualTo(String value) {
            addCriterion("clss_class_related_name <=", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameLike(String value) {
            addCriterion("clss_class_related_name like", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameNotLike(String value) {
            addCriterion("clss_class_related_name not like", value, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameIn(List<String> values) {
            addCriterion("clss_class_related_name in", values, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameNotIn(List<String> values) {
            addCriterion("clss_class_related_name not in", values, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameBetween(String value1, String value2) {
            addCriterion("clss_class_related_name between", value1, value2, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssClassRelatedNameNotBetween(String value1, String value2) {
            addCriterion("clss_class_related_name not between", value1, value2, "clssClassRelatedName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameIsNull() {
            addCriterion("clss_alias_name is null");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameIsNotNull() {
            addCriterion("clss_alias_name is not null");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameEqualTo(String value) {
            addCriterion("clss_alias_name =", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameNotEqualTo(String value) {
            addCriterion("clss_alias_name <>", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameGreaterThan(String value) {
            addCriterion("clss_alias_name >", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameGreaterThanOrEqualTo(String value) {
            addCriterion("clss_alias_name >=", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameLessThan(String value) {
            addCriterion("clss_alias_name <", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameLessThanOrEqualTo(String value) {
            addCriterion("clss_alias_name <=", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameLike(String value) {
            addCriterion("clss_alias_name like", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameNotLike(String value) {
            addCriterion("clss_alias_name not like", value, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameIn(List<String> values) {
            addCriterion("clss_alias_name in", values, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameNotIn(List<String> values) {
            addCriterion("clss_alias_name not in", values, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameBetween(String value1, String value2) {
            addCriterion("clss_alias_name between", value1, value2, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssAliasNameNotBetween(String value1, String value2) {
            addCriterion("clss_alias_name not between", value1, value2, "clssAliasName");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnIsNull() {
            addCriterion("clss_edmc_name_en is null");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnIsNotNull() {
            addCriterion("clss_edmc_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnEqualTo(String value) {
            addCriterion("clss_edmc_name_en =", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnNotEqualTo(String value) {
            addCriterion("clss_edmc_name_en <>", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnGreaterThan(String value) {
            addCriterion("clss_edmc_name_en >", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("clss_edmc_name_en >=", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnLessThan(String value) {
            addCriterion("clss_edmc_name_en <", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnLessThanOrEqualTo(String value) {
            addCriterion("clss_edmc_name_en <=", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnLike(String value) {
            addCriterion("clss_edmc_name_en like", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnNotLike(String value) {
            addCriterion("clss_edmc_name_en not like", value, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnIn(List<String> values) {
            addCriterion("clss_edmc_name_en in", values, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnNotIn(List<String> values) {
            addCriterion("clss_edmc_name_en not in", values, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnBetween(String value1, String value2) {
            addCriterion("clss_edmc_name_en between", value1, value2, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssEdmcNameEnNotBetween(String value1, String value2) {
            addCriterion("clss_edmc_name_en not between", value1, value2, "clssEdmcNameEn");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameIsNull() {
            addCriterion("clss_condition_name is null");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameIsNotNull() {
            addCriterion("clss_condition_name is not null");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameEqualTo(String value) {
            addCriterion("clss_condition_name =", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameNotEqualTo(String value) {
            addCriterion("clss_condition_name <>", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameGreaterThan(String value) {
            addCriterion("clss_condition_name >", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameGreaterThanOrEqualTo(String value) {
            addCriterion("clss_condition_name >=", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameLessThan(String value) {
            addCriterion("clss_condition_name <", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameLessThanOrEqualTo(String value) {
            addCriterion("clss_condition_name <=", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameLike(String value) {
            addCriterion("clss_condition_name like", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameNotLike(String value) {
            addCriterion("clss_condition_name not like", value, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameIn(List<String> values) {
            addCriterion("clss_condition_name in", values, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameNotIn(List<String> values) {
            addCriterion("clss_condition_name not in", values, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameBetween(String value1, String value2) {
            addCriterion("clss_condition_name between", value1, value2, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionNameNotBetween(String value1, String value2) {
            addCriterion("clss_condition_name not between", value1, value2, "clssConditionName");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaIsNull() {
            addCriterion("clss_condition_formula is null");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaIsNotNull() {
            addCriterion("clss_condition_formula is not null");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaEqualTo(String value) {
            addCriterion("clss_condition_formula =", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaNotEqualTo(String value) {
            addCriterion("clss_condition_formula <>", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaGreaterThan(String value) {
            addCriterion("clss_condition_formula >", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("clss_condition_formula >=", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaLessThan(String value) {
            addCriterion("clss_condition_formula <", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaLessThanOrEqualTo(String value) {
            addCriterion("clss_condition_formula <=", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaLike(String value) {
            addCriterion("clss_condition_formula like", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaNotLike(String value) {
            addCriterion("clss_condition_formula not like", value, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaIn(List<String> values) {
            addCriterion("clss_condition_formula in", values, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaNotIn(List<String> values) {
            addCriterion("clss_condition_formula not in", values, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaBetween(String value1, String value2) {
            addCriterion("clss_condition_formula between", value1, value2, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionFormulaNotBetween(String value1, String value2) {
            addCriterion("clss_condition_formula not between", value1, value2, "clssConditionFormula");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescIsNull() {
            addCriterion("clss_condition_desc is null");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescIsNotNull() {
            addCriterion("clss_condition_desc is not null");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescEqualTo(String value) {
            addCriterion("clss_condition_desc =", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescNotEqualTo(String value) {
            addCriterion("clss_condition_desc <>", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescGreaterThan(String value) {
            addCriterion("clss_condition_desc >", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescGreaterThanOrEqualTo(String value) {
            addCriterion("clss_condition_desc >=", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescLessThan(String value) {
            addCriterion("clss_condition_desc <", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescLessThanOrEqualTo(String value) {
            addCriterion("clss_condition_desc <=", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescLike(String value) {
            addCriterion("clss_condition_desc like", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescNotLike(String value) {
            addCriterion("clss_condition_desc not like", value, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescIn(List<String> values) {
            addCriterion("clss_condition_desc in", values, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescNotIn(List<String> values) {
            addCriterion("clss_condition_desc not in", values, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescBetween(String value1, String value2) {
            addCriterion("clss_condition_desc between", value1, value2, "clssConditionDesc");
            return (Criteria) this;
        }

        public Criteria andClssConditionDescNotBetween(String value1, String value2) {
            addCriterion("clss_condition_desc not between", value1, value2, "clssConditionDesc");
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

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(Integer value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(Integer value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(Integer value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(Integer value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(Integer value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(Integer value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<Integer> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<Integer> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(Integer value1, Integer value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(Integer value1, Integer value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdIsNull() {
            addCriterion("clss_link_class_id is null");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdIsNotNull() {
            addCriterion("clss_link_class_id is not null");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdEqualTo(String value) {
            addCriterion("clss_link_class_id =", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdNotEqualTo(String value) {
            addCriterion("clss_link_class_id <>", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdGreaterThan(String value) {
            addCriterion("clss_link_class_id >", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdGreaterThanOrEqualTo(String value) {
            addCriterion("clss_link_class_id >=", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdLessThan(String value) {
            addCriterion("clss_link_class_id <", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdLessThanOrEqualTo(String value) {
            addCriterion("clss_link_class_id <=", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdLike(String value) {
            addCriterion("clss_link_class_id like", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdNotLike(String value) {
            addCriterion("clss_link_class_id not like", value, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdIn(List<String> values) {
            addCriterion("clss_link_class_id in", values, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdNotIn(List<String> values) {
            addCriterion("clss_link_class_id not in", values, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdBetween(String value1, String value2) {
            addCriterion("clss_link_class_id between", value1, value2, "clssLinkClassId");
            return (Criteria) this;
        }

        public Criteria andClssLinkClassIdNotBetween(String value1, String value2) {
            addCriterion("clss_link_class_id not between", value1, value2, "clssLinkClassId");
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