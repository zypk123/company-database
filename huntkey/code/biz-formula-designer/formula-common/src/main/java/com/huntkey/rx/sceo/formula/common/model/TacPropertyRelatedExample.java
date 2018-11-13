package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TacPropertyRelatedExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TacPropertyRelatedExample() {
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

        public Criteria andPrplIdIsNull() {
            addCriterion("prpl_id is null");
            return (Criteria) this;
        }

        public Criteria andPrplIdIsNotNull() {
            addCriterion("prpl_id is not null");
            return (Criteria) this;
        }

        public Criteria andPrplIdEqualTo(String value) {
            addCriterion("prpl_id =", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdNotEqualTo(String value) {
            addCriterion("prpl_id <>", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdGreaterThan(String value) {
            addCriterion("prpl_id >", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_id >=", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdLessThan(String value) {
            addCriterion("prpl_id <", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdLessThanOrEqualTo(String value) {
            addCriterion("prpl_id <=", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdLike(String value) {
            addCriterion("prpl_id like", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdNotLike(String value) {
            addCriterion("prpl_id not like", value, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdIn(List<String> values) {
            addCriterion("prpl_id in", values, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdNotIn(List<String> values) {
            addCriterion("prpl_id not in", values, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdBetween(String value1, String value2) {
            addCriterion("prpl_id between", value1, value2, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplIdNotBetween(String value1, String value2) {
            addCriterion("prpl_id not between", value1, value2, "prplId");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromIsNull() {
            addCriterion("prpl_class_related_from is null");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromIsNotNull() {
            addCriterion("prpl_class_related_from is not null");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromEqualTo(String value) {
            addCriterion("prpl_class_related_from =", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromNotEqualTo(String value) {
            addCriterion("prpl_class_related_from <>", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromGreaterThan(String value) {
            addCriterion("prpl_class_related_from >", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_class_related_from >=", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromLessThan(String value) {
            addCriterion("prpl_class_related_from <", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromLessThanOrEqualTo(String value) {
            addCriterion("prpl_class_related_from <=", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromLike(String value) {
            addCriterion("prpl_class_related_from like", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromNotLike(String value) {
            addCriterion("prpl_class_related_from not like", value, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromIn(List<String> values) {
            addCriterion("prpl_class_related_from in", values, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromNotIn(List<String> values) {
            addCriterion("prpl_class_related_from not in", values, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromBetween(String value1, String value2) {
            addCriterion("prpl_class_related_from between", value1, value2, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedFromNotBetween(String value1, String value2) {
            addCriterion("prpl_class_related_from not between", value1, value2, "prplClassRelatedFrom");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToIsNull() {
            addCriterion("prpl_class_related_to is null");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToIsNotNull() {
            addCriterion("prpl_class_related_to is not null");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToEqualTo(String value) {
            addCriterion("prpl_class_related_to =", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToNotEqualTo(String value) {
            addCriterion("prpl_class_related_to <>", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToGreaterThan(String value) {
            addCriterion("prpl_class_related_to >", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_class_related_to >=", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToLessThan(String value) {
            addCriterion("prpl_class_related_to <", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToLessThanOrEqualTo(String value) {
            addCriterion("prpl_class_related_to <=", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToLike(String value) {
            addCriterion("prpl_class_related_to like", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToNotLike(String value) {
            addCriterion("prpl_class_related_to not like", value, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToIn(List<String> values) {
            addCriterion("prpl_class_related_to in", values, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToNotIn(List<String> values) {
            addCriterion("prpl_class_related_to not in", values, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToBetween(String value1, String value2) {
            addCriterion("prpl_class_related_to between", value1, value2, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClassRelatedToNotBetween(String value1, String value2) {
            addCriterion("prpl_class_related_to not between", value1, value2, "prplClassRelatedTo");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnIsNull() {
            addCriterion("prpl_class1_name_en is null");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnIsNotNull() {
            addCriterion("prpl_class1_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnEqualTo(String value) {
            addCriterion("prpl_class1_name_en =", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnNotEqualTo(String value) {
            addCriterion("prpl_class1_name_en <>", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnGreaterThan(String value) {
            addCriterion("prpl_class1_name_en >", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_class1_name_en >=", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnLessThan(String value) {
            addCriterion("prpl_class1_name_en <", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnLessThanOrEqualTo(String value) {
            addCriterion("prpl_class1_name_en <=", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnLike(String value) {
            addCriterion("prpl_class1_name_en like", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnNotLike(String value) {
            addCriterion("prpl_class1_name_en not like", value, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnIn(List<String> values) {
            addCriterion("prpl_class1_name_en in", values, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnNotIn(List<String> values) {
            addCriterion("prpl_class1_name_en not in", values, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnBetween(String value1, String value2) {
            addCriterion("prpl_class1_name_en between", value1, value2, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass1NameEnNotBetween(String value1, String value2) {
            addCriterion("prpl_class1_name_en not between", value1, value2, "prplClass1NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeIsNull() {
            addCriterion("prpl_prop1_code is null");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeIsNotNull() {
            addCriterion("prpl_prop1_code is not null");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeEqualTo(String value) {
            addCriterion("prpl_prop1_code =", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeNotEqualTo(String value) {
            addCriterion("prpl_prop1_code <>", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeGreaterThan(String value) {
            addCriterion("prpl_prop1_code >", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_prop1_code >=", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeLessThan(String value) {
            addCriterion("prpl_prop1_code <", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeLessThanOrEqualTo(String value) {
            addCriterion("prpl_prop1_code <=", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeLike(String value) {
            addCriterion("prpl_prop1_code like", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeNotLike(String value) {
            addCriterion("prpl_prop1_code not like", value, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeIn(List<String> values) {
            addCriterion("prpl_prop1_code in", values, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeNotIn(List<String> values) {
            addCriterion("prpl_prop1_code not in", values, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeBetween(String value1, String value2) {
            addCriterion("prpl_prop1_code between", value1, value2, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp1CodeNotBetween(String value1, String value2) {
            addCriterion("prpl_prop1_code not between", value1, value2, "prplProp1Code");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnIsNull() {
            addCriterion("prpl_class2_name_en is null");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnIsNotNull() {
            addCriterion("prpl_class2_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnEqualTo(String value) {
            addCriterion("prpl_class2_name_en =", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnNotEqualTo(String value) {
            addCriterion("prpl_class2_name_en <>", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnGreaterThan(String value) {
            addCriterion("prpl_class2_name_en >", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_class2_name_en >=", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnLessThan(String value) {
            addCriterion("prpl_class2_name_en <", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnLessThanOrEqualTo(String value) {
            addCriterion("prpl_class2_name_en <=", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnLike(String value) {
            addCriterion("prpl_class2_name_en like", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnNotLike(String value) {
            addCriterion("prpl_class2_name_en not like", value, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnIn(List<String> values) {
            addCriterion("prpl_class2_name_en in", values, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnNotIn(List<String> values) {
            addCriterion("prpl_class2_name_en not in", values, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnBetween(String value1, String value2) {
            addCriterion("prpl_class2_name_en between", value1, value2, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplClass2NameEnNotBetween(String value1, String value2) {
            addCriterion("prpl_class2_name_en not between", value1, value2, "prplClass2NameEn");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeIsNull() {
            addCriterion("prpl_prop2_code is null");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeIsNotNull() {
            addCriterion("prpl_prop2_code is not null");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeEqualTo(String value) {
            addCriterion("prpl_prop2_code =", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeNotEqualTo(String value) {
            addCriterion("prpl_prop2_code <>", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeGreaterThan(String value) {
            addCriterion("prpl_prop2_code >", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_prop2_code >=", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeLessThan(String value) {
            addCriterion("prpl_prop2_code <", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeLessThanOrEqualTo(String value) {
            addCriterion("prpl_prop2_code <=", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeLike(String value) {
            addCriterion("prpl_prop2_code like", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeNotLike(String value) {
            addCriterion("prpl_prop2_code not like", value, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeIn(List<String> values) {
            addCriterion("prpl_prop2_code in", values, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeNotIn(List<String> values) {
            addCriterion("prpl_prop2_code not in", values, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeBetween(String value1, String value2) {
            addCriterion("prpl_prop2_code between", value1, value2, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplProp2CodeNotBetween(String value1, String value2) {
            addCriterion("prpl_prop2_code not between", value1, value2, "prplProp2Code");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameIsNull() {
            addCriterion("prpl_condition_name is null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameIsNotNull() {
            addCriterion("prpl_condition_name is not null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameEqualTo(String value) {
            addCriterion("prpl_condition_name =", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameNotEqualTo(String value) {
            addCriterion("prpl_condition_name <>", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameGreaterThan(String value) {
            addCriterion("prpl_condition_name >", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_condition_name >=", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameLessThan(String value) {
            addCriterion("prpl_condition_name <", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameLessThanOrEqualTo(String value) {
            addCriterion("prpl_condition_name <=", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameLike(String value) {
            addCriterion("prpl_condition_name like", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameNotLike(String value) {
            addCriterion("prpl_condition_name not like", value, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameIn(List<String> values) {
            addCriterion("prpl_condition_name in", values, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameNotIn(List<String> values) {
            addCriterion("prpl_condition_name not in", values, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameBetween(String value1, String value2) {
            addCriterion("prpl_condition_name between", value1, value2, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionNameNotBetween(String value1, String value2) {
            addCriterion("prpl_condition_name not between", value1, value2, "prplConditionName");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaIsNull() {
            addCriterion("prpl_condition_formula is null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaIsNotNull() {
            addCriterion("prpl_condition_formula is not null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaEqualTo(String value) {
            addCriterion("prpl_condition_formula =", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaNotEqualTo(String value) {
            addCriterion("prpl_condition_formula <>", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaGreaterThan(String value) {
            addCriterion("prpl_condition_formula >", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_condition_formula >=", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaLessThan(String value) {
            addCriterion("prpl_condition_formula <", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaLessThanOrEqualTo(String value) {
            addCriterion("prpl_condition_formula <=", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaLike(String value) {
            addCriterion("prpl_condition_formula like", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaNotLike(String value) {
            addCriterion("prpl_condition_formula not like", value, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaIn(List<String> values) {
            addCriterion("prpl_condition_formula in", values, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaNotIn(List<String> values) {
            addCriterion("prpl_condition_formula not in", values, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaBetween(String value1, String value2) {
            addCriterion("prpl_condition_formula between", value1, value2, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionFormulaNotBetween(String value1, String value2) {
            addCriterion("prpl_condition_formula not between", value1, value2, "prplConditionFormula");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescIsNull() {
            addCriterion("prpl_condition_desc is null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescIsNotNull() {
            addCriterion("prpl_condition_desc is not null");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescEqualTo(String value) {
            addCriterion("prpl_condition_desc =", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescNotEqualTo(String value) {
            addCriterion("prpl_condition_desc <>", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescGreaterThan(String value) {
            addCriterion("prpl_condition_desc >", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescGreaterThanOrEqualTo(String value) {
            addCriterion("prpl_condition_desc >=", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescLessThan(String value) {
            addCriterion("prpl_condition_desc <", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescLessThanOrEqualTo(String value) {
            addCriterion("prpl_condition_desc <=", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescLike(String value) {
            addCriterion("prpl_condition_desc like", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescNotLike(String value) {
            addCriterion("prpl_condition_desc not like", value, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescIn(List<String> values) {
            addCriterion("prpl_condition_desc in", values, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescNotIn(List<String> values) {
            addCriterion("prpl_condition_desc not in", values, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescBetween(String value1, String value2) {
            addCriterion("prpl_condition_desc between", value1, value2, "prplConditionDesc");
            return (Criteria) this;
        }

        public Criteria andPrplConditionDescNotBetween(String value1, String value2) {
            addCriterion("prpl_condition_desc not between", value1, value2, "prplConditionDesc");
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