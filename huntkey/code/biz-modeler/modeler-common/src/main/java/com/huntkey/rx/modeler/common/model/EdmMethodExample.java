package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmMethodExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmMethodExample() {
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

        public Criteria andEdmmEdmcIdIsNull() {
            addCriterion("edmm_edmc_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdIsNotNull() {
            addCriterion("edmm_edmc_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdEqualTo(String value) {
            addCriterion("edmm_edmc_id =", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdNotEqualTo(String value) {
            addCriterion("edmm_edmc_id <>", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdGreaterThan(String value) {
            addCriterion("edmm_edmc_id >", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_edmc_id >=", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdLessThan(String value) {
            addCriterion("edmm_edmc_id <", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdLessThanOrEqualTo(String value) {
            addCriterion("edmm_edmc_id <=", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdLike(String value) {
            addCriterion("edmm_edmc_id like", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdNotLike(String value) {
            addCriterion("edmm_edmc_id not like", value, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdIn(List<String> values) {
            addCriterion("edmm_edmc_id in", values, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdNotIn(List<String> values) {
            addCriterion("edmm_edmc_id not in", values, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdBetween(String value1, String value2) {
            addCriterion("edmm_edmc_id between", value1, value2, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmEdmcIdNotBetween(String value1, String value2) {
            addCriterion("edmm_edmc_id not between", value1, value2, "edmmEdmcId");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeIsNull() {
            addCriterion("edmm_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeIsNotNull() {
            addCriterion("edmm_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeEqualTo(String value) {
            addCriterion("edmm_type =", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeNotEqualTo(String value) {
            addCriterion("edmm_type <>", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeGreaterThan(String value) {
            addCriterion("edmm_type >", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_type >=", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeLessThan(String value) {
            addCriterion("edmm_type <", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeLessThanOrEqualTo(String value) {
            addCriterion("edmm_type <=", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeLike(String value) {
            addCriterion("edmm_type like", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeNotLike(String value) {
            addCriterion("edmm_type not like", value, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeIn(List<String> values) {
            addCriterion("edmm_type in", values, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeNotIn(List<String> values) {
            addCriterion("edmm_type not in", values, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeBetween(String value1, String value2) {
            addCriterion("edmm_type between", value1, value2, "edmmType");
            return (Criteria) this;
        }

        public Criteria andEdmmTypeNotBetween(String value1, String value2) {
            addCriterion("edmm_type not between", value1, value2, "edmmType");
            return (Criteria) this;
        }

        public Criteria andIsCoverIsNull() {
            addCriterion("is_cover is null");
            return (Criteria) this;
        }

        public Criteria andIsCoverIsNotNull() {
            addCriterion("is_cover is not null");
            return (Criteria) this;
        }

        public Criteria andIsCoverEqualTo(Byte value) {
            addCriterion("is_cover =", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverNotEqualTo(Byte value) {
            addCriterion("is_cover <>", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverGreaterThan(Byte value) {
            addCriterion("is_cover >", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_cover >=", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverLessThan(Byte value) {
            addCriterion("is_cover <", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverLessThanOrEqualTo(Byte value) {
            addCriterion("is_cover <=", value, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverIn(List<Byte> values) {
            addCriterion("is_cover in", values, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverNotIn(List<Byte> values) {
            addCriterion("is_cover not in", values, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverBetween(Byte value1, Byte value2) {
            addCriterion("is_cover between", value1, value2, "isCover");
            return (Criteria) this;
        }

        public Criteria andIsCoverNotBetween(Byte value1, Byte value2) {
            addCriterion("is_cover not between", value1, value2, "isCover");
            return (Criteria) this;
        }

        public Criteria andEdmmNameIsNull() {
            addCriterion("edmm_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmmNameIsNotNull() {
            addCriterion("edmm_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmNameEqualTo(String value) {
            addCriterion("edmm_name =", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameNotEqualTo(String value) {
            addCriterion("edmm_name <>", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameGreaterThan(String value) {
            addCriterion("edmm_name >", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_name >=", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameLessThan(String value) {
            addCriterion("edmm_name <", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameLessThanOrEqualTo(String value) {
            addCriterion("edmm_name <=", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameLike(String value) {
            addCriterion("edmm_name like", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameNotLike(String value) {
            addCriterion("edmm_name not like", value, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameIn(List<String> values) {
            addCriterion("edmm_name in", values, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameNotIn(List<String> values) {
            addCriterion("edmm_name not in", values, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameBetween(String value1, String value2) {
            addCriterion("edmm_name between", value1, value2, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmNameNotBetween(String value1, String value2) {
            addCriterion("edmm_name not between", value1, value2, "edmmName");
            return (Criteria) this;
        }

        public Criteria andEdmmDescIsNull() {
            addCriterion("edmm_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmmDescIsNotNull() {
            addCriterion("edmm_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmDescEqualTo(String value) {
            addCriterion("edmm_desc =", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescNotEqualTo(String value) {
            addCriterion("edmm_desc <>", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescGreaterThan(String value) {
            addCriterion("edmm_desc >", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_desc >=", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescLessThan(String value) {
            addCriterion("edmm_desc <", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescLessThanOrEqualTo(String value) {
            addCriterion("edmm_desc <=", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescLike(String value) {
            addCriterion("edmm_desc like", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescNotLike(String value) {
            addCriterion("edmm_desc not like", value, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescIn(List<String> values) {
            addCriterion("edmm_desc in", values, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescNotIn(List<String> values) {
            addCriterion("edmm_desc not in", values, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescBetween(String value1, String value2) {
            addCriterion("edmm_desc between", value1, value2, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmDescNotBetween(String value1, String value2) {
            addCriterion("edmm_desc not between", value1, value2, "edmmDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeIsNull() {
            addCriterion("edmm_program_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeIsNotNull() {
            addCriterion("edmm_program_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeEqualTo(String value) {
            addCriterion("edmm_program_type =", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeNotEqualTo(String value) {
            addCriterion("edmm_program_type <>", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeGreaterThan(String value) {
            addCriterion("edmm_program_type >", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_program_type >=", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeLessThan(String value) {
            addCriterion("edmm_program_type <", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeLessThanOrEqualTo(String value) {
            addCriterion("edmm_program_type <=", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeLike(String value) {
            addCriterion("edmm_program_type like", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeNotLike(String value) {
            addCriterion("edmm_program_type not like", value, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeIn(List<String> values) {
            addCriterion("edmm_program_type in", values, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeNotIn(List<String> values) {
            addCriterion("edmm_program_type not in", values, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeBetween(String value1, String value2) {
            addCriterion("edmm_program_type between", value1, value2, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramTypeNotBetween(String value1, String value2) {
            addCriterion("edmm_program_type not between", value1, value2, "edmmProgramType");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageIsNull() {
            addCriterion("edmm_program_storage is null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageIsNotNull() {
            addCriterion("edmm_program_storage is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageEqualTo(String value) {
            addCriterion("edmm_program_storage =", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageNotEqualTo(String value) {
            addCriterion("edmm_program_storage <>", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageGreaterThan(String value) {
            addCriterion("edmm_program_storage >", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_program_storage >=", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageLessThan(String value) {
            addCriterion("edmm_program_storage <", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageLessThanOrEqualTo(String value) {
            addCriterion("edmm_program_storage <=", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageLike(String value) {
            addCriterion("edmm_program_storage like", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageNotLike(String value) {
            addCriterion("edmm_program_storage not like", value, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageIn(List<String> values) {
            addCriterion("edmm_program_storage in", values, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageNotIn(List<String> values) {
            addCriterion("edmm_program_storage not in", values, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageBetween(String value1, String value2) {
            addCriterion("edmm_program_storage between", value1, value2, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramStorageNotBetween(String value1, String value2) {
            addCriterion("edmm_program_storage not between", value1, value2, "edmmProgramStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameIsNull() {
            addCriterion("edmm_program_source_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameIsNotNull() {
            addCriterion("edmm_program_source_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameEqualTo(String value) {
            addCriterion("edmm_program_source_name =", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameNotEqualTo(String value) {
            addCriterion("edmm_program_source_name <>", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameGreaterThan(String value) {
            addCriterion("edmm_program_source_name >", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_program_source_name >=", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameLessThan(String value) {
            addCriterion("edmm_program_source_name <", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameLessThanOrEqualTo(String value) {
            addCriterion("edmm_program_source_name <=", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameLike(String value) {
            addCriterion("edmm_program_source_name like", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameNotLike(String value) {
            addCriterion("edmm_program_source_name not like", value, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameIn(List<String> values) {
            addCriterion("edmm_program_source_name in", values, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameNotIn(List<String> values) {
            addCriterion("edmm_program_source_name not in", values, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameBetween(String value1, String value2) {
            addCriterion("edmm_program_source_name between", value1, value2, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmProgramSourceNameNotBetween(String value1, String value2) {
            addCriterion("edmm_program_source_name not between", value1, value2, "edmmProgramSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondIsNull() {
            addCriterion("edmm_trigger_cond is null");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondIsNotNull() {
            addCriterion("edmm_trigger_cond is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondEqualTo(String value) {
            addCriterion("edmm_trigger_cond =", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondNotEqualTo(String value) {
            addCriterion("edmm_trigger_cond <>", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondGreaterThan(String value) {
            addCriterion("edmm_trigger_cond >", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_trigger_cond >=", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondLessThan(String value) {
            addCriterion("edmm_trigger_cond <", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondLessThanOrEqualTo(String value) {
            addCriterion("edmm_trigger_cond <=", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondLike(String value) {
            addCriterion("edmm_trigger_cond like", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondNotLike(String value) {
            addCriterion("edmm_trigger_cond not like", value, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondIn(List<String> values) {
            addCriterion("edmm_trigger_cond in", values, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondNotIn(List<String> values) {
            addCriterion("edmm_trigger_cond not in", values, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondBetween(String value1, String value2) {
            addCriterion("edmm_trigger_cond between", value1, value2, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmTriggerCondNotBetween(String value1, String value2) {
            addCriterion("edmm_trigger_cond not between", value1, value2, "edmmTriggerCond");
            return (Criteria) this;
        }

        public Criteria andEdmmVerIsNull() {
            addCriterion("edmm_ver is null");
            return (Criteria) this;
        }

        public Criteria andEdmmVerIsNotNull() {
            addCriterion("edmm_ver is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmVerEqualTo(String value) {
            addCriterion("edmm_ver =", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerNotEqualTo(String value) {
            addCriterion("edmm_ver <>", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerGreaterThan(String value) {
            addCriterion("edmm_ver >", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_ver >=", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerLessThan(String value) {
            addCriterion("edmm_ver <", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerLessThanOrEqualTo(String value) {
            addCriterion("edmm_ver <=", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerLike(String value) {
            addCriterion("edmm_ver like", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerNotLike(String value) {
            addCriterion("edmm_ver not like", value, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerIn(List<String> values) {
            addCriterion("edmm_ver in", values, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerNotIn(List<String> values) {
            addCriterion("edmm_ver not in", values, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerBetween(String value1, String value2) {
            addCriterion("edmm_ver between", value1, value2, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmVerNotBetween(String value1, String value2) {
            addCriterion("edmm_ver not between", value1, value2, "edmmVer");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptIsNull() {
            addCriterion("edmm_develop_dept is null");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptIsNotNull() {
            addCriterion("edmm_develop_dept is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptEqualTo(String value) {
            addCriterion("edmm_develop_dept =", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptNotEqualTo(String value) {
            addCriterion("edmm_develop_dept <>", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptGreaterThan(String value) {
            addCriterion("edmm_develop_dept >", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_develop_dept >=", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptLessThan(String value) {
            addCriterion("edmm_develop_dept <", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptLessThanOrEqualTo(String value) {
            addCriterion("edmm_develop_dept <=", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptLike(String value) {
            addCriterion("edmm_develop_dept like", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptNotLike(String value) {
            addCriterion("edmm_develop_dept not like", value, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptIn(List<String> values) {
            addCriterion("edmm_develop_dept in", values, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptNotIn(List<String> values) {
            addCriterion("edmm_develop_dept not in", values, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptBetween(String value1, String value2) {
            addCriterion("edmm_develop_dept between", value1, value2, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmDevelopDeptNotBetween(String value1, String value2) {
            addCriterion("edmm_develop_dept not between", value1, value2, "edmmDevelopDept");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerIsNull() {
            addCriterion("edmm_programmer is null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerIsNotNull() {
            addCriterion("edmm_programmer is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerEqualTo(String value) {
            addCriterion("edmm_programmer =", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerNotEqualTo(String value) {
            addCriterion("edmm_programmer <>", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerGreaterThan(String value) {
            addCriterion("edmm_programmer >", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_programmer >=", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerLessThan(String value) {
            addCriterion("edmm_programmer <", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerLessThanOrEqualTo(String value) {
            addCriterion("edmm_programmer <=", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerLike(String value) {
            addCriterion("edmm_programmer like", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerNotLike(String value) {
            addCriterion("edmm_programmer not like", value, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerIn(List<String> values) {
            addCriterion("edmm_programmer in", values, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerNotIn(List<String> values) {
            addCriterion("edmm_programmer not in", values, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerBetween(String value1, String value2) {
            addCriterion("edmm_programmer between", value1, value2, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmProgrammerNotBetween(String value1, String value2) {
            addCriterion("edmm_programmer not between", value1, value2, "edmmProgrammer");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqIsNull() {
            addCriterion("edmm_seq is null");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqIsNotNull() {
            addCriterion("edmm_seq is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqEqualTo(Integer value) {
            addCriterion("edmm_seq =", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqNotEqualTo(Integer value) {
            addCriterion("edmm_seq <>", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqGreaterThan(Integer value) {
            addCriterion("edmm_seq >", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("edmm_seq >=", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqLessThan(Integer value) {
            addCriterion("edmm_seq <", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqLessThanOrEqualTo(Integer value) {
            addCriterion("edmm_seq <=", value, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqIn(List<Integer> values) {
            addCriterion("edmm_seq in", values, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqNotIn(List<Integer> values) {
            addCriterion("edmm_seq not in", values, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqBetween(Integer value1, Integer value2) {
            addCriterion("edmm_seq between", value1, value2, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("edmm_seq not between", value1, value2, "edmmSeq");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusIsNull() {
            addCriterion("edmm_status is null");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusIsNotNull() {
            addCriterion("edmm_status is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusEqualTo(String value) {
            addCriterion("edmm_status =", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusNotEqualTo(String value) {
            addCriterion("edmm_status <>", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusGreaterThan(String value) {
            addCriterion("edmm_status >", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_status >=", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusLessThan(String value) {
            addCriterion("edmm_status <", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusLessThanOrEqualTo(String value) {
            addCriterion("edmm_status <=", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusLike(String value) {
            addCriterion("edmm_status like", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusNotLike(String value) {
            addCriterion("edmm_status not like", value, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusIn(List<String> values) {
            addCriterion("edmm_status in", values, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusNotIn(List<String> values) {
            addCriterion("edmm_status not in", values, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusBetween(String value1, String value2) {
            addCriterion("edmm_status between", value1, value2, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmStatusNotBetween(String value1, String value2) {
            addCriterion("edmm_status not between", value1, value2, "edmmStatus");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescIsNull() {
            addCriterion("edmm_update_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescIsNotNull() {
            addCriterion("edmm_update_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescEqualTo(String value) {
            addCriterion("edmm_update_desc =", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescNotEqualTo(String value) {
            addCriterion("edmm_update_desc <>", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescGreaterThan(String value) {
            addCriterion("edmm_update_desc >", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_update_desc >=", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescLessThan(String value) {
            addCriterion("edmm_update_desc <", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescLessThanOrEqualTo(String value) {
            addCriterion("edmm_update_desc <=", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescLike(String value) {
            addCriterion("edmm_update_desc like", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescNotLike(String value) {
            addCriterion("edmm_update_desc not like", value, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescIn(List<String> values) {
            addCriterion("edmm_update_desc in", values, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescNotIn(List<String> values) {
            addCriterion("edmm_update_desc not in", values, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescBetween(String value1, String value2) {
            addCriterion("edmm_update_desc between", value1, value2, "edmmUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmUpdateDescNotBetween(String value1, String value2) {
            addCriterion("edmm_update_desc not between", value1, value2, "edmmUpdateDesc");
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

        public Criteria andEdmmArithmeticDescIsNull() {
            addCriterion("edmm_arithmetic_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescIsNotNull() {
            addCriterion("edmm_arithmetic_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescEqualTo(String value) {
            addCriterion("edmm_arithmetic_desc =", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescNotEqualTo(String value) {
            addCriterion("edmm_arithmetic_desc <>", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescGreaterThan(String value) {
            addCriterion("edmm_arithmetic_desc >", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_desc >=", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescLessThan(String value) {
            addCriterion("edmm_arithmetic_desc <", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescLessThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_desc <=", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescLike(String value) {
            addCriterion("edmm_arithmetic_desc like", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescNotLike(String value) {
            addCriterion("edmm_arithmetic_desc not like", value, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescIn(List<String> values) {
            addCriterion("edmm_arithmetic_desc in", values, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescNotIn(List<String> values) {
            addCriterion("edmm_arithmetic_desc not in", values, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_desc between", value1, value2, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticDescNotBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_desc not between", value1, value2, "edmmArithmeticDesc");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageIsNull() {
            addCriterion("edmm_arithmetic_storage is null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageIsNotNull() {
            addCriterion("edmm_arithmetic_storage is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageEqualTo(String value) {
            addCriterion("edmm_arithmetic_storage =", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageNotEqualTo(String value) {
            addCriterion("edmm_arithmetic_storage <>", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageGreaterThan(String value) {
            addCriterion("edmm_arithmetic_storage >", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_storage >=", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageLessThan(String value) {
            addCriterion("edmm_arithmetic_storage <", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageLessThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_storage <=", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageLike(String value) {
            addCriterion("edmm_arithmetic_storage like", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageNotLike(String value) {
            addCriterion("edmm_arithmetic_storage not like", value, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageIn(List<String> values) {
            addCriterion("edmm_arithmetic_storage in", values, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageNotIn(List<String> values) {
            addCriterion("edmm_arithmetic_storage not in", values, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_storage between", value1, value2, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticStorageNotBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_storage not between", value1, value2, "edmmArithmeticStorage");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameIsNull() {
            addCriterion("edmm_arithmetic_source_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameIsNotNull() {
            addCriterion("edmm_arithmetic_source_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameEqualTo(String value) {
            addCriterion("edmm_arithmetic_source_name =", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameNotEqualTo(String value) {
            addCriterion("edmm_arithmetic_source_name <>", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameGreaterThan(String value) {
            addCriterion("edmm_arithmetic_source_name >", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_source_name >=", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameLessThan(String value) {
            addCriterion("edmm_arithmetic_source_name <", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameLessThanOrEqualTo(String value) {
            addCriterion("edmm_arithmetic_source_name <=", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameLike(String value) {
            addCriterion("edmm_arithmetic_source_name like", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameNotLike(String value) {
            addCriterion("edmm_arithmetic_source_name not like", value, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameIn(List<String> values) {
            addCriterion("edmm_arithmetic_source_name in", values, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameNotIn(List<String> values) {
            addCriterion("edmm_arithmetic_source_name not in", values, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_source_name between", value1, value2, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmArithmeticSourceNameNotBetween(String value1, String value2) {
            addCriterion("edmm_arithmetic_source_name not between", value1, value2, "edmmArithmeticSourceName");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeIsNull() {
            addCriterion("edmm_request_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeIsNotNull() {
            addCriterion("edmm_request_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeEqualTo(String value) {
            addCriterion("edmm_request_type =", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeNotEqualTo(String value) {
            addCriterion("edmm_request_type <>", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeGreaterThan(String value) {
            addCriterion("edmm_request_type >", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_request_type >=", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeLessThan(String value) {
            addCriterion("edmm_request_type <", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeLessThanOrEqualTo(String value) {
            addCriterion("edmm_request_type <=", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeLike(String value) {
            addCriterion("edmm_request_type like", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeNotLike(String value) {
            addCriterion("edmm_request_type not like", value, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeIn(List<String> values) {
            addCriterion("edmm_request_type in", values, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeNotIn(List<String> values) {
            addCriterion("edmm_request_type not in", values, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeBetween(String value1, String value2) {
            addCriterion("edmm_request_type between", value1, value2, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmRequestTypeNotBetween(String value1, String value2) {
            addCriterion("edmm_request_type not between", value1, value2, "edmmRequestType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeIsNull() {
            addCriterion("edmm_method_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeIsNotNull() {
            addCriterion("edmm_method_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeEqualTo(String value) {
            addCriterion("edmm_method_type =", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeNotEqualTo(String value) {
            addCriterion("edmm_method_type <>", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeGreaterThan(String value) {
            addCriterion("edmm_method_type >", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_method_type >=", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeLessThan(String value) {
            addCriterion("edmm_method_type <", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeLessThanOrEqualTo(String value) {
            addCriterion("edmm_method_type <=", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeLike(String value) {
            addCriterion("edmm_method_type like", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeNotLike(String value) {
            addCriterion("edmm_method_type not like", value, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeIn(List<String> values) {
            addCriterion("edmm_method_type in", values, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeNotIn(List<String> values) {
            addCriterion("edmm_method_type not in", values, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeBetween(String value1, String value2) {
            addCriterion("edmm_method_type between", value1, value2, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmMethodTypeNotBetween(String value1, String value2) {
            addCriterion("edmm_method_type not between", value1, value2, "edmmMethodType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateIsNull() {
            addCriterion("edmm_exec_rate is null");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateIsNotNull() {
            addCriterion("edmm_exec_rate is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateEqualTo(String value) {
            addCriterion("edmm_exec_rate =", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateNotEqualTo(String value) {
            addCriterion("edmm_exec_rate <>", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateGreaterThan(String value) {
            addCriterion("edmm_exec_rate >", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_exec_rate >=", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateLessThan(String value) {
            addCriterion("edmm_exec_rate <", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateLessThanOrEqualTo(String value) {
            addCriterion("edmm_exec_rate <=", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateLike(String value) {
            addCriterion("edmm_exec_rate like", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateNotLike(String value) {
            addCriterion("edmm_exec_rate not like", value, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateIn(List<String> values) {
            addCriterion("edmm_exec_rate in", values, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateNotIn(List<String> values) {
            addCriterion("edmm_exec_rate not in", values, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateBetween(String value1, String value2) {
            addCriterion("edmm_exec_rate between", value1, value2, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmExecRateNotBetween(String value1, String value2) {
            addCriterion("edmm_exec_rate not between", value1, value2, "edmmExecRate");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdIsNull() {
            addCriterion("edmm_plan_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdIsNotNull() {
            addCriterion("edmm_plan_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdEqualTo(String value) {
            addCriterion("edmm_plan_id =", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdNotEqualTo(String value) {
            addCriterion("edmm_plan_id <>", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdGreaterThan(String value) {
            addCriterion("edmm_plan_id >", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_plan_id >=", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdLessThan(String value) {
            addCriterion("edmm_plan_id <", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdLessThanOrEqualTo(String value) {
            addCriterion("edmm_plan_id <=", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdLike(String value) {
            addCriterion("edmm_plan_id like", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdNotLike(String value) {
            addCriterion("edmm_plan_id not like", value, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdIn(List<String> values) {
            addCriterion("edmm_plan_id in", values, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdNotIn(List<String> values) {
            addCriterion("edmm_plan_id not in", values, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdBetween(String value1, String value2) {
            addCriterion("edmm_plan_id between", value1, value2, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmPlanIdNotBetween(String value1, String value2) {
            addCriterion("edmm_plan_id not between", value1, value2, "edmmPlanId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdIsNull() {
            addCriterion("edmm_job_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdIsNotNull() {
            addCriterion("edmm_job_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdEqualTo(String value) {
            addCriterion("edmm_job_id =", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdNotEqualTo(String value) {
            addCriterion("edmm_job_id <>", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdGreaterThan(String value) {
            addCriterion("edmm_job_id >", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_job_id >=", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdLessThan(String value) {
            addCriterion("edmm_job_id <", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdLessThanOrEqualTo(String value) {
            addCriterion("edmm_job_id <=", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdLike(String value) {
            addCriterion("edmm_job_id like", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdNotLike(String value) {
            addCriterion("edmm_job_id not like", value, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdIn(List<String> values) {
            addCriterion("edmm_job_id in", values, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdNotIn(List<String> values) {
            addCriterion("edmm_job_id not in", values, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdBetween(String value1, String value2) {
            addCriterion("edmm_job_id between", value1, value2, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmJobIdNotBetween(String value1, String value2) {
            addCriterion("edmm_job_id not between", value1, value2, "edmmJobId");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeIsNull() {
            addCriterion("edmm_execute_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeIsNotNull() {
            addCriterion("edmm_execute_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeEqualTo(String value) {
            addCriterion("edmm_execute_type =", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeNotEqualTo(String value) {
            addCriterion("edmm_execute_type <>", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeGreaterThan(String value) {
            addCriterion("edmm_execute_type >", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edmm_execute_type >=", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeLessThan(String value) {
            addCriterion("edmm_execute_type <", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeLessThanOrEqualTo(String value) {
            addCriterion("edmm_execute_type <=", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeLike(String value) {
            addCriterion("edmm_execute_type like", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeNotLike(String value) {
            addCriterion("edmm_execute_type not like", value, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeIn(List<String> values) {
            addCriterion("edmm_execute_type in", values, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeNotIn(List<String> values) {
            addCriterion("edmm_execute_type not in", values, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeBetween(String value1, String value2) {
            addCriterion("edmm_execute_type between", value1, value2, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andEdmmExecuteTypeNotBetween(String value1, String value2) {
            addCriterion("edmm_execute_type not between", value1, value2, "edmmExecuteType");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNull() {
            addCriterion("timeout is null");
            return (Criteria) this;
        }

        public Criteria andTimeoutIsNotNull() {
            addCriterion("timeout is not null");
            return (Criteria) this;
        }

        public Criteria andTimeoutEqualTo(Integer value) {
            addCriterion("timeout =", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotEqualTo(Integer value) {
            addCriterion("timeout <>", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThan(Integer value) {
            addCriterion("timeout >", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutGreaterThanOrEqualTo(Integer value) {
            addCriterion("timeout >=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThan(Integer value) {
            addCriterion("timeout <", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutLessThanOrEqualTo(Integer value) {
            addCriterion("timeout <=", value, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutIn(List<Integer> values) {
            addCriterion("timeout in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotIn(List<Integer> values) {
            addCriterion("timeout not in", values, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutBetween(Integer value1, Integer value2) {
            addCriterion("timeout between", value1, value2, "timeout");
            return (Criteria) this;
        }

        public Criteria andTimeoutNotBetween(Integer value1, Integer value2) {
            addCriterion("timeout not between", value1, value2, "timeout");
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