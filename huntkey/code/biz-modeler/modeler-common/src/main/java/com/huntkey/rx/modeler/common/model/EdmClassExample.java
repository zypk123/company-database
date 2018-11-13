package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmClassExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    @Override
    public String toString() {
        return "EdmClassExample{" +
                "orderByClause='" + orderByClause + '\'' +
                ", distinct=" + distinct +
                ", oredCriteria=" + oredCriteria.toString() +
                '}';
    }

    public EdmClassExample() {
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
        @Override
        public String toString() {
            return "GeneratedCriteria{" +
                    "criteria=" + criteria.toString() +
                    '}';
        }

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

        public Criteria andEdmcEdmdIdIsNull() {
            addCriterion("edmc_edmd_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdIsNotNull() {
            addCriterion("edmc_edmd_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdEqualTo(String value) {
            addCriterion("edmc_edmd_id =", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdNotEqualTo(String value) {
            addCriterion("edmc_edmd_id <>", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdGreaterThan(String value) {
            addCriterion("edmc_edmd_id >", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_edmd_id >=", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdLessThan(String value) {
            addCriterion("edmc_edmd_id <", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdLessThanOrEqualTo(String value) {
            addCriterion("edmc_edmd_id <=", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdLike(String value) {
            addCriterion("edmc_edmd_id like", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdNotLike(String value) {
            addCriterion("edmc_edmd_id not like", value, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdIn(List<String> values) {
            addCriterion("edmc_edmd_id in", values, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdNotIn(List<String> values) {
            addCriterion("edmc_edmd_id not in", values, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdBetween(String value1, String value2) {
            addCriterion("edmc_edmd_id between", value1, value2, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcEdmdIdNotBetween(String value1, String value2) {
            addCriterion("edmc_edmd_id not between", value1, value2, "edmcEdmdId");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeIsNull() {
            addCriterion("edmc_code is null");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeIsNotNull() {
            addCriterion("edmc_code is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeEqualTo(String value) {
            addCriterion("edmc_code =", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeNotEqualTo(String value) {
            addCriterion("edmc_code <>", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeGreaterThan(String value) {
            addCriterion("edmc_code >", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_code >=", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeLessThan(String value) {
            addCriterion("edmc_code <", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeLessThanOrEqualTo(String value) {
            addCriterion("edmc_code <=", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeLike(String value) {
            addCriterion("edmc_code like", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeNotLike(String value) {
            addCriterion("edmc_code not like", value, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeIn(List<String> values) {
            addCriterion("edmc_code in", values, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeNotIn(List<String> values) {
            addCriterion("edmc_code not in", values, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeBetween(String value1, String value2) {
            addCriterion("edmc_code between", value1, value2, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcCodeNotBetween(String value1, String value2) {
            addCriterion("edmc_code not between", value1, value2, "edmcCode");
            return (Criteria) this;
        }

        public Criteria andEdmcNameIsNull() {
            addCriterion("edmc_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmcNameIsNotNull() {
            addCriterion("edmc_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEqualTo(String value) {
            addCriterion("edmc_name =", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameNotEqualTo(String value) {
            addCriterion("edmc_name <>", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameGreaterThan(String value) {
            addCriterion("edmc_name >", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_name >=", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameLessThan(String value) {
            addCriterion("edmc_name <", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameLessThanOrEqualTo(String value) {
            addCriterion("edmc_name <=", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameLike(String value) {
            addCriterion("edmc_name like", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameNotLike(String value) {
            addCriterion("edmc_name not like", value, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameIn(List<String> values) {
            addCriterion("edmc_name in", values, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameNotIn(List<String> values) {
            addCriterion("edmc_name not in", values, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameBetween(String value1, String value2) {
            addCriterion("edmc_name between", value1, value2, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameNotBetween(String value1, String value2) {
            addCriterion("edmc_name not between", value1, value2, "edmcName");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnIsNull() {
            addCriterion("edmc_name_en is null");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnIsNotNull() {
            addCriterion("edmc_name_en is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnEqualTo(String value) {
            addCriterion("edmc_name_en =", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnNotEqualTo(String value) {
            addCriterion("edmc_name_en <>", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnGreaterThan(String value) {
            addCriterion("edmc_name_en >", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_name_en >=", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnLessThan(String value) {
            addCriterion("edmc_name_en <", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnLessThanOrEqualTo(String value) {
            addCriterion("edmc_name_en <=", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnLike(String value) {
            addCriterion("edmc_name_en like", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnNotLike(String value) {
            addCriterion("edmc_name_en not like", value, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnIn(List<String> values) {
            addCriterion("edmc_name_en in", values, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnNotIn(List<String> values) {
            addCriterion("edmc_name_en not in", values, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnBetween(String value1, String value2) {
            addCriterion("edmc_name_en between", value1, value2, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcNameEnNotBetween(String value1, String value2) {
            addCriterion("edmc_name_en not between", value1, value2, "edmcNameEn");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameIsNull() {
            addCriterion("edmc_short_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameIsNotNull() {
            addCriterion("edmc_short_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameEqualTo(String value) {
            addCriterion("edmc_short_name =", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameNotEqualTo(String value) {
            addCriterion("edmc_short_name <>", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameGreaterThan(String value) {
            addCriterion("edmc_short_name >", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_short_name >=", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameLessThan(String value) {
            addCriterion("edmc_short_name <", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameLessThanOrEqualTo(String value) {
            addCriterion("edmc_short_name <=", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameLike(String value) {
            addCriterion("edmc_short_name like", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameNotLike(String value) {
            addCriterion("edmc_short_name not like", value, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameIn(List<String> values) {
            addCriterion("edmc_short_name in", values, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameNotIn(List<String> values) {
            addCriterion("edmc_short_name not in", values, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameBetween(String value1, String value2) {
            addCriterion("edmc_short_name between", value1, value2, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcShortNameNotBetween(String value1, String value2) {
            addCriterion("edmc_short_name not between", value1, value2, "edmcShortName");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdIsNull() {
            addCriterion("edmc_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdIsNotNull() {
            addCriterion("edmc_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdEqualTo(String value) {
            addCriterion("edmc_parent_id =", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdNotEqualTo(String value) {
            addCriterion("edmc_parent_id <>", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdGreaterThan(String value) {
            addCriterion("edmc_parent_id >", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_parent_id >=", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdLessThan(String value) {
            addCriterion("edmc_parent_id <", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdLessThanOrEqualTo(String value) {
            addCriterion("edmc_parent_id <=", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdLike(String value) {
            addCriterion("edmc_parent_id like", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdNotLike(String value) {
            addCriterion("edmc_parent_id not like", value, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdIn(List<String> values) {
            addCriterion("edmc_parent_id in", values, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdNotIn(List<String> values) {
            addCriterion("edmc_parent_id not in", values, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdBetween(String value1, String value2) {
            addCriterion("edmc_parent_id between", value1, value2, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcParentIdNotBetween(String value1, String value2) {
            addCriterion("edmc_parent_id not between", value1, value2, "edmcParentId");
            return (Criteria) this;
        }

        public Criteria andEdmcVerIsNull() {
            addCriterion("edmc_ver is null");
            return (Criteria) this;
        }

        public Criteria andEdmcVerIsNotNull() {
            addCriterion("edmc_ver is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcVerEqualTo(String value) {
            addCriterion("edmc_ver =", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerNotEqualTo(String value) {
            addCriterion("edmc_ver <>", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerGreaterThan(String value) {
            addCriterion("edmc_ver >", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_ver >=", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerLessThan(String value) {
            addCriterion("edmc_ver <", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerLessThanOrEqualTo(String value) {
            addCriterion("edmc_ver <=", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerLike(String value) {
            addCriterion("edmc_ver like", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerNotLike(String value) {
            addCriterion("edmc_ver not like", value, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerIn(List<String> values) {
            addCriterion("edmc_ver in", values, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerNotIn(List<String> values) {
            addCriterion("edmc_ver not in", values, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerBetween(String value1, String value2) {
            addCriterion("edmc_ver between", value1, value2, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcVerNotBetween(String value1, String value2) {
            addCriterion("edmc_ver not between", value1, value2, "edmcVer");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalIsNull() {
            addCriterion("edmc_principal is null");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalIsNotNull() {
            addCriterion("edmc_principal is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalEqualTo(String value) {
            addCriterion("edmc_principal =", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalNotEqualTo(String value) {
            addCriterion("edmc_principal <>", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalGreaterThan(String value) {
            addCriterion("edmc_principal >", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_principal >=", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalLessThan(String value) {
            addCriterion("edmc_principal <", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalLessThanOrEqualTo(String value) {
            addCriterion("edmc_principal <=", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalLike(String value) {
            addCriterion("edmc_principal like", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalNotLike(String value) {
            addCriterion("edmc_principal not like", value, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalIn(List<String> values) {
            addCriterion("edmc_principal in", values, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalNotIn(List<String> values) {
            addCriterion("edmc_principal not in", values, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalBetween(String value1, String value2) {
            addCriterion("edmc_principal between", value1, value2, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcPrincipalNotBetween(String value1, String value2) {
            addCriterion("edmc_principal not between", value1, value2, "edmcPrincipal");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescIsNull() {
            addCriterion("edmc_use_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescIsNotNull() {
            addCriterion("edmc_use_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescEqualTo(String value) {
            addCriterion("edmc_use_desc =", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescNotEqualTo(String value) {
            addCriterion("edmc_use_desc <>", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescGreaterThan(String value) {
            addCriterion("edmc_use_desc >", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_use_desc >=", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescLessThan(String value) {
            addCriterion("edmc_use_desc <", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescLessThanOrEqualTo(String value) {
            addCriterion("edmc_use_desc <=", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescLike(String value) {
            addCriterion("edmc_use_desc like", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescNotLike(String value) {
            addCriterion("edmc_use_desc not like", value, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescIn(List<String> values) {
            addCriterion("edmc_use_desc in", values, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescNotIn(List<String> values) {
            addCriterion("edmc_use_desc not in", values, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescBetween(String value1, String value2) {
            addCriterion("edmc_use_desc between", value1, value2, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcUseDescNotBetween(String value1, String value2) {
            addCriterion("edmc_use_desc not between", value1, value2, "edmcUseDesc");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelIsNull() {
            addCriterion("edmc_level is null");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelIsNotNull() {
            addCriterion("edmc_level is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelEqualTo(String value) {
            addCriterion("edmc_level =", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelNotEqualTo(String value) {
            addCriterion("edmc_level <>", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelGreaterThan(String value) {
            addCriterion("edmc_level >", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_level >=", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelLessThan(String value) {
            addCriterion("edmc_level <", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelLessThanOrEqualTo(String value) {
            addCriterion("edmc_level <=", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelLike(String value) {
            addCriterion("edmc_level like", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelNotLike(String value) {
            addCriterion("edmc_level not like", value, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelIn(List<String> values) {
            addCriterion("edmc_level in", values, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelNotIn(List<String> values) {
            addCriterion("edmc_level not in", values, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelBetween(String value1, String value2) {
            addCriterion("edmc_level between", value1, value2, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcLevelNotBetween(String value1, String value2) {
            addCriterion("edmc_level not between", value1, value2, "edmcLevel");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeIsNull() {
            addCriterion("edmc_industry_code is null");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeIsNotNull() {
            addCriterion("edmc_industry_code is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeEqualTo(String value) {
            addCriterion("edmc_industry_code =", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeNotEqualTo(String value) {
            addCriterion("edmc_industry_code <>", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeGreaterThan(String value) {
            addCriterion("edmc_industry_code >", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_industry_code >=", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeLessThan(String value) {
            addCriterion("edmc_industry_code <", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeLessThanOrEqualTo(String value) {
            addCriterion("edmc_industry_code <=", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeLike(String value) {
            addCriterion("edmc_industry_code like", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeNotLike(String value) {
            addCriterion("edmc_industry_code not like", value, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeIn(List<String> values) {
            addCriterion("edmc_industry_code in", values, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeNotIn(List<String> values) {
            addCriterion("edmc_industry_code not in", values, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeBetween(String value1, String value2) {
            addCriterion("edmc_industry_code between", value1, value2, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andEdmcIndustryCodeNotBetween(String value1, String value2) {
            addCriterion("edmc_industry_code not between", value1, value2, "edmcIndustryCode");
            return (Criteria) this;
        }

        public Criteria andNormalPresentIsNull() {
            addCriterion("normal_present is null");
            return (Criteria) this;
        }

        public Criteria andNormalPresentIsNotNull() {
            addCriterion("normal_present is not null");
            return (Criteria) this;
        }

        public Criteria andNormalPresentEqualTo(String value) {
            addCriterion("normal_present =", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentNotEqualTo(String value) {
            addCriterion("normal_present <>", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentGreaterThan(String value) {
            addCriterion("normal_present >", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentGreaterThanOrEqualTo(String value) {
            addCriterion("normal_present >=", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentLessThan(String value) {
            addCriterion("normal_present <", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentLessThanOrEqualTo(String value) {
            addCriterion("normal_present <=", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentLike(String value) {
            addCriterion("normal_present like", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentNotLike(String value) {
            addCriterion("normal_present not like", value, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentIn(List<String> values) {
            addCriterion("normal_present in", values, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentNotIn(List<String> values) {
            addCriterion("normal_present not in", values, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentBetween(String value1, String value2) {
            addCriterion("normal_present between", value1, value2, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andNormalPresentNotBetween(String value1, String value2) {
            addCriterion("normal_present not between", value1, value2, "normalPresent");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudIsNull() {
            addCriterion("object_on_cloud is null");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudIsNotNull() {
            addCriterion("object_on_cloud is not null");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudEqualTo(String value) {
            addCriterion("object_on_cloud =", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudNotEqualTo(String value) {
            addCriterion("object_on_cloud <>", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudGreaterThan(String value) {
            addCriterion("object_on_cloud >", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudGreaterThanOrEqualTo(String value) {
            addCriterion("object_on_cloud >=", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudLessThan(String value) {
            addCriterion("object_on_cloud <", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudLessThanOrEqualTo(String value) {
            addCriterion("object_on_cloud <=", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudLike(String value) {
            addCriterion("object_on_cloud like", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudNotLike(String value) {
            addCriterion("object_on_cloud not like", value, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudIn(List<String> values) {
            addCriterion("object_on_cloud in", values, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudNotIn(List<String> values) {
            addCriterion("object_on_cloud not in", values, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudBetween(String value1, String value2) {
            addCriterion("object_on_cloud between", value1, value2, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andObjectOnCloudNotBetween(String value1, String value2) {
            addCriterion("object_on_cloud not between", value1, value2, "objectOnCloud");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqIsNull() {
            addCriterion("edmc_seq is null");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqIsNotNull() {
            addCriterion("edmc_seq is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqEqualTo(Integer value) {
            addCriterion("edmc_seq =", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqNotEqualTo(Integer value) {
            addCriterion("edmc_seq <>", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqGreaterThan(Integer value) {
            addCriterion("edmc_seq >", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("edmc_seq >=", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqLessThan(Integer value) {
            addCriterion("edmc_seq <", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqLessThanOrEqualTo(Integer value) {
            addCriterion("edmc_seq <=", value, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqIn(List<Integer> values) {
            addCriterion("edmc_seq in", values, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqNotIn(List<Integer> values) {
            addCriterion("edmc_seq not in", values, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqBetween(Integer value1, Integer value2) {
            addCriterion("edmc_seq between", value1, value2, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andEdmcSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("edmc_seq not between", value1, value2, "edmcSeq");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeIsNull() {
            addCriterion("database_type is null");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeIsNotNull() {
            addCriterion("database_type is not null");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeEqualTo(String value) {
            addCriterion("database_type =", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeNotEqualTo(String value) {
            addCriterion("database_type <>", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeGreaterThan(String value) {
            addCriterion("database_type >", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeGreaterThanOrEqualTo(String value) {
            addCriterion("database_type >=", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeLessThan(String value) {
            addCriterion("database_type <", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeLessThanOrEqualTo(String value) {
            addCriterion("database_type <=", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeLike(String value) {
            addCriterion("database_type like", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeNotLike(String value) {
            addCriterion("database_type not like", value, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeIn(List<String> values) {
            addCriterion("database_type in", values, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeNotIn(List<String> values) {
            addCriterion("database_type not in", values, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeBetween(String value1, String value2) {
            addCriterion("database_type between", value1, value2, "databaseType");
            return (Criteria) this;
        }

        public Criteria andDatabaseTypeNotBetween(String value1, String value2) {
            addCriterion("database_type not between", value1, value2, "databaseType");
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

        public Criteria andIsEntityIsNull() {
            addCriterion("is_entity is null");
            return (Criteria) this;
        }

        public Criteria andIsEntityIsNotNull() {
            addCriterion("is_entity is not null");
            return (Criteria) this;
        }

        public Criteria andIsEntityEqualTo(Byte value) {
            addCriterion("is_entity =", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityNotEqualTo(Byte value) {
            addCriterion("is_entity <>", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityGreaterThan(Byte value) {
            addCriterion("is_entity >", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityGreaterThanOrEqualTo(Byte value) {
            addCriterion("is_entity >=", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityLessThan(Byte value) {
            addCriterion("is_entity <", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityLessThanOrEqualTo(Byte value) {
            addCriterion("is_entity <=", value, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityIn(List<Byte> values) {
            addCriterion("is_entity in", values, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityNotIn(List<Byte> values) {
            addCriterion("is_entity not in", values, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityBetween(Byte value1, Byte value2) {
            addCriterion("is_entity between", value1, value2, "isEntity");
            return (Criteria) this;
        }

        public Criteria andIsEntityNotBetween(Byte value1, Byte value2) {
            addCriterion("is_entity not between", value1, value2, "isEntity");
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
        @Override
        public String toString() {
            return "Criterion{" +
                    "condition='" + condition + '\'' +
                    ", value=" + value +
                    ", secondValue=" + secondValue +
                    ", noValue=" + noValue +
                    ", singleValue=" + singleValue +
                    ", betweenValue=" + betweenValue +
                    ", listValue=" + listValue +
                    ", typeHandler='" + typeHandler + '\'' +
                    '}';
        }

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