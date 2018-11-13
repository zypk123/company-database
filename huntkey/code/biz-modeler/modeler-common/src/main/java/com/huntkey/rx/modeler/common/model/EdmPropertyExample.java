package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmPropertyExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmPropertyExample() {
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

        public Criteria andEdmpEdmcIdIsNull() {
            addCriterion("edmp_edmc_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdIsNotNull() {
            addCriterion("edmp_edmc_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdEqualTo(String value) {
            addCriterion("edmp_edmc_id =", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdNotEqualTo(String value) {
            addCriterion("edmp_edmc_id <>", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdGreaterThan(String value) {
            addCriterion("edmp_edmc_id >", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_edmc_id >=", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdLessThan(String value) {
            addCriterion("edmp_edmc_id <", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdLessThanOrEqualTo(String value) {
            addCriterion("edmp_edmc_id <=", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdLike(String value) {
            addCriterion("edmp_edmc_id like", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdNotLike(String value) {
            addCriterion("edmp_edmc_id not like", value, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdIn(List<String> values) {
            addCriterion("edmp_edmc_id in", values, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdNotIn(List<String> values) {
            addCriterion("edmp_edmc_id not in", values, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdBetween(String value1, String value2) {
            addCriterion("edmp_edmc_id between", value1, value2, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmcIdNotBetween(String value1, String value2) {
            addCriterion("edmp_edmc_id not between", value1, value2, "edmpEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdIsNull() {
            addCriterion("edmp_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdIsNotNull() {
            addCriterion("edmp_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdEqualTo(String value) {
            addCriterion("edmp_parent_id =", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdNotEqualTo(String value) {
            addCriterion("edmp_parent_id <>", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdGreaterThan(String value) {
            addCriterion("edmp_parent_id >", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_parent_id >=", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdLessThan(String value) {
            addCriterion("edmp_parent_id <", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdLessThanOrEqualTo(String value) {
            addCriterion("edmp_parent_id <=", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdLike(String value) {
            addCriterion("edmp_parent_id like", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdNotLike(String value) {
            addCriterion("edmp_parent_id not like", value, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdIn(List<String> values) {
            addCriterion("edmp_parent_id in", values, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdNotIn(List<String> values) {
            addCriterion("edmp_parent_id not in", values, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdBetween(String value1, String value2) {
            addCriterion("edmp_parent_id between", value1, value2, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpParentIdNotBetween(String value1, String value2) {
            addCriterion("edmp_parent_id not between", value1, value2, "edmpParentId");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeIsNull() {
            addCriterion("edmp_code is null");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeIsNotNull() {
            addCriterion("edmp_code is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeEqualTo(String value) {
            addCriterion("edmp_code =", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeNotEqualTo(String value) {
            addCriterion("edmp_code <>", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeGreaterThan(String value) {
            addCriterion("edmp_code >", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_code >=", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeLessThan(String value) {
            addCriterion("edmp_code <", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeLessThanOrEqualTo(String value) {
            addCriterion("edmp_code <=", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeLike(String value) {
            addCriterion("edmp_code like", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeNotLike(String value) {
            addCriterion("edmp_code not like", value, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeIn(List<String> values) {
            addCriterion("edmp_code in", values, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeNotIn(List<String> values) {
            addCriterion("edmp_code not in", values, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeBetween(String value1, String value2) {
            addCriterion("edmp_code between", value1, value2, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpCodeNotBetween(String value1, String value2) {
            addCriterion("edmp_code not between", value1, value2, "edmpCode");
            return (Criteria) this;
        }

        public Criteria andEdmpNameIsNull() {
            addCriterion("edmp_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmpNameIsNotNull() {
            addCriterion("edmp_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpNameEqualTo(String value) {
            addCriterion("edmp_name =", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameNotEqualTo(String value) {
            addCriterion("edmp_name <>", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameGreaterThan(String value) {
            addCriterion("edmp_name >", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_name >=", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameLessThan(String value) {
            addCriterion("edmp_name <", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameLessThanOrEqualTo(String value) {
            addCriterion("edmp_name <=", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameLike(String value) {
            addCriterion("edmp_name like", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameNotLike(String value) {
            addCriterion("edmp_name not like", value, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameIn(List<String> values) {
            addCriterion("edmp_name in", values, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameNotIn(List<String> values) {
            addCriterion("edmp_name not in", values, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameBetween(String value1, String value2) {
            addCriterion("edmp_name between", value1, value2, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpNameNotBetween(String value1, String value2) {
            addCriterion("edmp_name not between", value1, value2, "edmpName");
            return (Criteria) this;
        }

        public Criteria andEdmpDescIsNull() {
            addCriterion("edmp_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmpDescIsNotNull() {
            addCriterion("edmp_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpDescEqualTo(String value) {
            addCriterion("edmp_desc =", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescNotEqualTo(String value) {
            addCriterion("edmp_desc <>", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescGreaterThan(String value) {
            addCriterion("edmp_desc >", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_desc >=", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescLessThan(String value) {
            addCriterion("edmp_desc <", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescLessThanOrEqualTo(String value) {
            addCriterion("edmp_desc <=", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescLike(String value) {
            addCriterion("edmp_desc like", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescNotLike(String value) {
            addCriterion("edmp_desc not like", value, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescIn(List<String> values) {
            addCriterion("edmp_desc in", values, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescNotIn(List<String> values) {
            addCriterion("edmp_desc not in", values, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescBetween(String value1, String value2) {
            addCriterion("edmp_desc between", value1, value2, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDescNotBetween(String value1, String value2) {
            addCriterion("edmp_desc not between", value1, value2, "edmpDesc");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeIsNull() {
            addCriterion("edmp_data_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeIsNotNull() {
            addCriterion("edmp_data_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeEqualTo(String value) {
            addCriterion("edmp_data_type =", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeNotEqualTo(String value) {
            addCriterion("edmp_data_type <>", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeGreaterThan(String value) {
            addCriterion("edmp_data_type >", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_data_type >=", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeLessThan(String value) {
            addCriterion("edmp_data_type <", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeLessThanOrEqualTo(String value) {
            addCriterion("edmp_data_type <=", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeLike(String value) {
            addCriterion("edmp_data_type like", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeNotLike(String value) {
            addCriterion("edmp_data_type not like", value, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeIn(List<String> values) {
            addCriterion("edmp_data_type in", values, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeNotIn(List<String> values) {
            addCriterion("edmp_data_type not in", values, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeBetween(String value1, String value2) {
            addCriterion("edmp_data_type between", value1, value2, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpDataTypeNotBetween(String value1, String value2) {
            addCriterion("edmp_data_type not between", value1, value2, "edmpDataType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeIsNull() {
            addCriterion("edmp_value_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeIsNotNull() {
            addCriterion("edmp_value_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeEqualTo(String value) {
            addCriterion("edmp_value_type =", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeNotEqualTo(String value) {
            addCriterion("edmp_value_type <>", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeGreaterThan(String value) {
            addCriterion("edmp_value_type >", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_value_type >=", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeLessThan(String value) {
            addCriterion("edmp_value_type <", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeLessThanOrEqualTo(String value) {
            addCriterion("edmp_value_type <=", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeLike(String value) {
            addCriterion("edmp_value_type like", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeNotLike(String value) {
            addCriterion("edmp_value_type not like", value, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeIn(List<String> values) {
            addCriterion("edmp_value_type in", values, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeNotIn(List<String> values) {
            addCriterion("edmp_value_type not in", values, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeBetween(String value1, String value2) {
            addCriterion("edmp_value_type between", value1, value2, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueTypeNotBetween(String value1, String value2) {
            addCriterion("edmp_value_type not between", value1, value2, "edmpValueType");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeIsNull() {
            addCriterion("edmp_value_size is null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeIsNotNull() {
            addCriterion("edmp_value_size is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeEqualTo(String value) {
            addCriterion("edmp_value_size =", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeNotEqualTo(String value) {
            addCriterion("edmp_value_size <>", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeGreaterThan(String value) {
            addCriterion("edmp_value_size >", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_value_size >=", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeLessThan(String value) {
            addCriterion("edmp_value_size <", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeLessThanOrEqualTo(String value) {
            addCriterion("edmp_value_size <=", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeLike(String value) {
            addCriterion("edmp_value_size like", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeNotLike(String value) {
            addCriterion("edmp_value_size not like", value, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeIn(List<String> values) {
            addCriterion("edmp_value_size in", values, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeNotIn(List<String> values) {
            addCriterion("edmp_value_size not in", values, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeBetween(String value1, String value2) {
            addCriterion("edmp_value_size between", value1, value2, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andEdmpValueSizeNotBetween(String value1, String value2) {
            addCriterion("edmp_value_size not between", value1, value2, "edmpValueSize");
            return (Criteria) this;
        }

        public Criteria andTriggerCondIsNull() {
            addCriterion("trigger_cond is null");
            return (Criteria) this;
        }

        public Criteria andTriggerCondIsNotNull() {
            addCriterion("trigger_cond is not null");
            return (Criteria) this;
        }

        public Criteria andTriggerCondEqualTo(String value) {
            addCriterion("trigger_cond =", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondNotEqualTo(String value) {
            addCriterion("trigger_cond <>", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondGreaterThan(String value) {
            addCriterion("trigger_cond >", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondGreaterThanOrEqualTo(String value) {
            addCriterion("trigger_cond >=", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondLessThan(String value) {
            addCriterion("trigger_cond <", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondLessThanOrEqualTo(String value) {
            addCriterion("trigger_cond <=", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondLike(String value) {
            addCriterion("trigger_cond like", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondNotLike(String value) {
            addCriterion("trigger_cond not like", value, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondIn(List<String> values) {
            addCriterion("trigger_cond in", values, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondNotIn(List<String> values) {
            addCriterion("trigger_cond not in", values, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondBetween(String value1, String value2) {
            addCriterion("trigger_cond between", value1, value2, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andTriggerCondNotBetween(String value1, String value2) {
            addCriterion("trigger_cond not between", value1, value2, "triggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitIsNull() {
            addCriterion("edmp_value_limit is null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitIsNotNull() {
            addCriterion("edmp_value_limit is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitEqualTo(String value) {
            addCriterion("edmp_value_limit =", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitNotEqualTo(String value) {
            addCriterion("edmp_value_limit <>", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitGreaterThan(String value) {
            addCriterion("edmp_value_limit >", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_value_limit >=", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitLessThan(String value) {
            addCriterion("edmp_value_limit <", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitLessThanOrEqualTo(String value) {
            addCriterion("edmp_value_limit <=", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitLike(String value) {
            addCriterion("edmp_value_limit like", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitNotLike(String value) {
            addCriterion("edmp_value_limit not like", value, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitIn(List<String> values) {
            addCriterion("edmp_value_limit in", values, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitNotIn(List<String> values) {
            addCriterion("edmp_value_limit not in", values, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitBetween(String value1, String value2) {
            addCriterion("edmp_value_limit between", value1, value2, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLimitNotBetween(String value1, String value2) {
            addCriterion("edmp_value_limit not between", value1, value2, "edmpValueLimit");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaIsNull() {
            addCriterion("edmp_formula is null");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaIsNotNull() {
            addCriterion("edmp_formula is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaEqualTo(String value) {
            addCriterion("edmp_formula =", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaNotEqualTo(String value) {
            addCriterion("edmp_formula <>", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaGreaterThan(String value) {
            addCriterion("edmp_formula >", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_formula >=", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaLessThan(String value) {
            addCriterion("edmp_formula <", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaLessThanOrEqualTo(String value) {
            addCriterion("edmp_formula <=", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaLike(String value) {
            addCriterion("edmp_formula like", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaNotLike(String value) {
            addCriterion("edmp_formula not like", value, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaIn(List<String> values) {
            addCriterion("edmp_formula in", values, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaNotIn(List<String> values) {
            addCriterion("edmp_formula not in", values, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaBetween(String value1, String value2) {
            addCriterion("edmp_formula between", value1, value2, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpFormulaNotBetween(String value1, String value2) {
            addCriterion("edmp_formula not between", value1, value2, "edmpFormula");
            return (Criteria) this;
        }

        public Criteria andEdmpValueIsNull() {
            addCriterion("edmp_value is null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueIsNotNull() {
            addCriterion("edmp_value is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpValueEqualTo(String value) {
            addCriterion("edmp_value =", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueNotEqualTo(String value) {
            addCriterion("edmp_value <>", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueGreaterThan(String value) {
            addCriterion("edmp_value >", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_value >=", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLessThan(String value) {
            addCriterion("edmp_value <", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLessThanOrEqualTo(String value) {
            addCriterion("edmp_value <=", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueLike(String value) {
            addCriterion("edmp_value like", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueNotLike(String value) {
            addCriterion("edmp_value not like", value, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueIn(List<String> values) {
            addCriterion("edmp_value in", values, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueNotIn(List<String> values) {
            addCriterion("edmp_value not in", values, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueBetween(String value1, String value2) {
            addCriterion("edmp_value between", value1, value2, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpValueNotBetween(String value1, String value2) {
            addCriterion("edmp_value not between", value1, value2, "edmpValue");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdIsNull() {
            addCriterion("edmp_edmm_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdIsNotNull() {
            addCriterion("edmp_edmm_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdEqualTo(String value) {
            addCriterion("edmp_edmm_id =", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdNotEqualTo(String value) {
            addCriterion("edmp_edmm_id <>", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdGreaterThan(String value) {
            addCriterion("edmp_edmm_id >", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_edmm_id >=", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdLessThan(String value) {
            addCriterion("edmp_edmm_id <", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdLessThanOrEqualTo(String value) {
            addCriterion("edmp_edmm_id <=", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdLike(String value) {
            addCriterion("edmp_edmm_id like", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdNotLike(String value) {
            addCriterion("edmp_edmm_id not like", value, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdIn(List<String> values) {
            addCriterion("edmp_edmm_id in", values, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdNotIn(List<String> values) {
            addCriterion("edmp_edmm_id not in", values, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdBetween(String value1, String value2) {
            addCriterion("edmp_edmm_id between", value1, value2, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpEdmmIdNotBetween(String value1, String value2) {
            addCriterion("edmp_edmm_id not between", value1, value2, "edmpEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdIsNull() {
            addCriterion("edmp_obj_edmc_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdIsNotNull() {
            addCriterion("edmp_obj_edmc_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdEqualTo(String value) {
            addCriterion("edmp_obj_edmc_id =", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdNotEqualTo(String value) {
            addCriterion("edmp_obj_edmc_id <>", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdGreaterThan(String value) {
            addCriterion("edmp_obj_edmc_id >", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_obj_edmc_id >=", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdLessThan(String value) {
            addCriterion("edmp_obj_edmc_id <", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdLessThanOrEqualTo(String value) {
            addCriterion("edmp_obj_edmc_id <=", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdLike(String value) {
            addCriterion("edmp_obj_edmc_id like", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdNotLike(String value) {
            addCriterion("edmp_obj_edmc_id not like", value, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdIn(List<String> values) {
            addCriterion("edmp_obj_edmc_id in", values, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdNotIn(List<String> values) {
            addCriterion("edmp_obj_edmc_id not in", values, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdBetween(String value1, String value2) {
            addCriterion("edmp_obj_edmc_id between", value1, value2, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpObjEdmcIdNotBetween(String value1, String value2) {
            addCriterion("edmp_obj_edmc_id not between", value1, value2, "edmpObjEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqIsNull() {
            addCriterion("edmp_seq is null");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqIsNotNull() {
            addCriterion("edmp_seq is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqEqualTo(Integer value) {
            addCriterion("edmp_seq =", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqNotEqualTo(Integer value) {
            addCriterion("edmp_seq <>", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqGreaterThan(Integer value) {
            addCriterion("edmp_seq >", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("edmp_seq >=", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqLessThan(Integer value) {
            addCriterion("edmp_seq <", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqLessThanOrEqualTo(Integer value) {
            addCriterion("edmp_seq <=", value, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqIn(List<Integer> values) {
            addCriterion("edmp_seq in", values, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqNotIn(List<Integer> values) {
            addCriterion("edmp_seq not in", values, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqBetween(Integer value1, Integer value2) {
            addCriterion("edmp_seq between", value1, value2, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andEdmpSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("edmp_seq not between", value1, value2, "edmpSeq");
            return (Criteria) this;
        }

        public Criteria andIsCharacterIsNull() {
            addCriterion("is_character is null");
            return (Criteria) this;
        }

        public Criteria andIsCharacterIsNotNull() {
            addCriterion("is_character is not null");
            return (Criteria) this;
        }

        public Criteria andIsCharacterEqualTo(Byte value) {
            addCriterion("is_character =", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterNotEqualTo(Byte value) {
            addCriterion("is_character <>", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterGreaterThan(Byte value) {
            addCriterion("is_character >", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_character >=", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterLessThan(Byte value) {
            addCriterion("is_character <", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterLessThanOrEqualTo(Byte value) {
            addCriterion("is_character <=", value, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterIn(List<Byte> values) {
            addCriterion("is_character in", values, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterNotIn(List<Byte> values) {
            addCriterion("is_character not in", values, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterBetween(Byte value1, Byte value2) {
            addCriterion("is_character between", value1, value2, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsCharacterNotBetween(Byte value1, Byte value2) {
            addCriterion("is_character not between", value1, value2, "isCharacter");
            return (Criteria) this;
        }

        public Criteria andIsDefinedIsNull() {
            addCriterion("is_defined is null");
            return (Criteria) this;
        }

        public Criteria andIsDefinedIsNotNull() {
            addCriterion("is_defined is not null");
            return (Criteria) this;
        }

        public Criteria andIsDefinedEqualTo(Byte value) {
            addCriterion("is_defined =", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedNotEqualTo(Byte value) {
            addCriterion("is_defined <>", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedGreaterThan(Byte value) {
            addCriterion("is_defined >", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_defined >=", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedLessThan(Byte value) {
            addCriterion("is_defined <", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedLessThanOrEqualTo(Byte value) {
            addCriterion("is_defined <=", value, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedIn(List<Byte> values) {
            addCriterion("is_defined in", values, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedNotIn(List<Byte> values) {
            addCriterion("is_defined not in", values, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedBetween(Byte value1, Byte value2) {
            addCriterion("is_defined between", value1, value2, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDefinedNotBetween(Byte value1, Byte value2) {
            addCriterion("is_defined not between", value1, value2, "isDefined");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNull() {
            addCriterion("is_del is null");
            return (Criteria) this;
        }

        public Criteria andIsDelIsNotNull() {
            addCriterion("is_del is not null");
            return (Criteria) this;
        }

        public Criteria andIsDelEqualTo(Byte value) {
            addCriterion("is_del =", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotEqualTo(Byte value) {
            addCriterion("is_del <>", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThan(Byte value) {
            addCriterion("is_del >", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_del >=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThan(Byte value) {
            addCriterion("is_del <", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelLessThanOrEqualTo(Byte value) {
            addCriterion("is_del <=", value, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelIn(List<Byte> values) {
            addCriterion("is_del in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotIn(List<Byte> values) {
            addCriterion("is_del not in", values, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelBetween(Byte value1, Byte value2) {
            addCriterion("is_del between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsDelNotBetween(Byte value1, Byte value2) {
            addCriterion("is_del not between", value1, value2, "isDel");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNull() {
            addCriterion("is_visible is null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIsNotNull() {
            addCriterion("is_visible is not null");
            return (Criteria) this;
        }

        public Criteria andIsVisibleEqualTo(Byte value) {
            addCriterion("is_visible =", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotEqualTo(Byte value) {
            addCriterion("is_visible <>", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThan(Byte value) {
            addCriterion("is_visible >", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_visible >=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThan(Byte value) {
            addCriterion("is_visible <", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleLessThanOrEqualTo(Byte value) {
            addCriterion("is_visible <=", value, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleIn(List<Byte> values) {
            addCriterion("is_visible in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotIn(List<Byte> values) {
            addCriterion("is_visible not in", values, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleBetween(Byte value1, Byte value2) {
            addCriterion("is_visible between", value1, value2, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsVisibleNotBetween(Byte value1, Byte value2) {
            addCriterion("is_visible not between", value1, value2, "isVisible");
            return (Criteria) this;
        }

        public Criteria andIsIndexIsNull() {
            addCriterion("is_index is null");
            return (Criteria) this;
        }

        public Criteria andIsIndexIsNotNull() {
            addCriterion("is_index is not null");
            return (Criteria) this;
        }

        public Criteria andIsIndexEqualTo(Byte value) {
            addCriterion("is_index =", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexNotEqualTo(Byte value) {
            addCriterion("is_index <>", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexGreaterThan(Byte value) {
            addCriterion("is_index >", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_index >=", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexLessThan(Byte value) {
            addCriterion("is_index <", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexLessThanOrEqualTo(Byte value) {
            addCriterion("is_index <=", value, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexIn(List<Byte> values) {
            addCriterion("is_index in", values, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexNotIn(List<Byte> values) {
            addCriterion("is_index not in", values, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexBetween(Byte value1, Byte value2) {
            addCriterion("is_index between", value1, value2, "isIndex");
            return (Criteria) this;
        }

        public Criteria andIsIndexNotBetween(Byte value1, Byte value2) {
            addCriterion("is_index not between", value1, value2, "isIndex");
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

        public Criteria andAcctidEqualTo(Byte value) {
            addCriterion("acctid =", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotEqualTo(Byte value) {
            addCriterion("acctid <>", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidGreaterThan(Byte value) {
            addCriterion("acctid >", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidGreaterThanOrEqualTo(Byte value) {
            addCriterion("acctid >=", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidLessThan(Byte value) {
            addCriterion("acctid <", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidLessThanOrEqualTo(Byte value) {
            addCriterion("acctid <=", value, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidIn(List<Byte> values) {
            addCriterion("acctid in", values, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotIn(List<Byte> values) {
            addCriterion("acctid not in", values, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidBetween(Byte value1, Byte value2) {
            addCriterion("acctid between", value1, value2, "acctid");
            return (Criteria) this;
        }

        public Criteria andAcctidNotBetween(Byte value1, Byte value2) {
            addCriterion("acctid not between", value1, value2, "acctid");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNull() {
            addCriterion("tablename is null");
            return (Criteria) this;
        }

        public Criteria andTablenameIsNotNull() {
            addCriterion("tablename is not null");
            return (Criteria) this;
        }

        public Criteria andTablenameEqualTo(String value) {
            addCriterion("tablename =", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotEqualTo(String value) {
            addCriterion("tablename <>", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThan(String value) {
            addCriterion("tablename >", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameGreaterThanOrEqualTo(String value) {
            addCriterion("tablename >=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThan(String value) {
            addCriterion("tablename <", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLessThanOrEqualTo(String value) {
            addCriterion("tablename <=", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameLike(String value) {
            addCriterion("tablename like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotLike(String value) {
            addCriterion("tablename not like", value, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameIn(List<String> values) {
            addCriterion("tablename in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotIn(List<String> values) {
            addCriterion("tablename not in", values, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameBetween(String value1, String value2) {
            addCriterion("tablename between", value1, value2, "tablename");
            return (Criteria) this;
        }

        public Criteria andTablenameNotBetween(String value1, String value2) {
            addCriterion("tablename not between", value1, value2, "tablename");
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