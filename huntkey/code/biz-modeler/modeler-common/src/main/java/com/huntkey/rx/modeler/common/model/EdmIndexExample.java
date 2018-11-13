package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmIndexExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmIndexExample() {
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

        public Criteria andEdmcIdIsNull() {
            addCriterion("edmc_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmcIdIsNotNull() {
            addCriterion("edmc_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmcIdEqualTo(String value) {
            addCriterion("edmc_id =", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdNotEqualTo(String value) {
            addCriterion("edmc_id <>", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdGreaterThan(String value) {
            addCriterion("edmc_id >", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmc_id >=", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdLessThan(String value) {
            addCriterion("edmc_id <", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdLessThanOrEqualTo(String value) {
            addCriterion("edmc_id <=", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdLike(String value) {
            addCriterion("edmc_id like", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdNotLike(String value) {
            addCriterion("edmc_id not like", value, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdIn(List<String> values) {
            addCriterion("edmc_id in", values, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdNotIn(List<String> values) {
            addCriterion("edmc_id not in", values, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdBetween(String value1, String value2) {
            addCriterion("edmc_id between", value1, value2, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmcIdNotBetween(String value1, String value2) {
            addCriterion("edmc_id not between", value1, value2, "edmcId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdIsNull() {
            addCriterion("edmp_ass_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdIsNotNull() {
            addCriterion("edmp_ass_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdEqualTo(String value) {
            addCriterion("edmp_ass_id =", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdNotEqualTo(String value) {
            addCriterion("edmp_ass_id <>", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdGreaterThan(String value) {
            addCriterion("edmp_ass_id >", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmp_ass_id >=", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdLessThan(String value) {
            addCriterion("edmp_ass_id <", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdLessThanOrEqualTo(String value) {
            addCriterion("edmp_ass_id <=", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdLike(String value) {
            addCriterion("edmp_ass_id like", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdNotLike(String value) {
            addCriterion("edmp_ass_id not like", value, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdIn(List<String> values) {
            addCriterion("edmp_ass_id in", values, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdNotIn(List<String> values) {
            addCriterion("edmp_ass_id not in", values, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdBetween(String value1, String value2) {
            addCriterion("edmp_ass_id between", value1, value2, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andEdmpAssIdNotBetween(String value1, String value2) {
            addCriterion("edmp_ass_id not between", value1, value2, "edmpAssId");
            return (Criteria) this;
        }

        public Criteria andIndexNameIsNull() {
            addCriterion("index_name is null");
            return (Criteria) this;
        }

        public Criteria andIndexNameIsNotNull() {
            addCriterion("index_name is not null");
            return (Criteria) this;
        }

        public Criteria andIndexNameEqualTo(String value) {
            addCriterion("index_name =", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotEqualTo(String value) {
            addCriterion("index_name <>", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameGreaterThan(String value) {
            addCriterion("index_name >", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameGreaterThanOrEqualTo(String value) {
            addCriterion("index_name >=", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLessThan(String value) {
            addCriterion("index_name <", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLessThanOrEqualTo(String value) {
            addCriterion("index_name <=", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameLike(String value) {
            addCriterion("index_name like", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotLike(String value) {
            addCriterion("index_name not like", value, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameIn(List<String> values) {
            addCriterion("index_name in", values, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotIn(List<String> values) {
            addCriterion("index_name not in", values, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameBetween(String value1, String value2) {
            addCriterion("index_name between", value1, value2, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexNameNotBetween(String value1, String value2) {
            addCriterion("index_name not between", value1, value2, "indexName");
            return (Criteria) this;
        }

        public Criteria andIndexTypeIsNull() {
            addCriterion("index_type is null");
            return (Criteria) this;
        }

        public Criteria andIndexTypeIsNotNull() {
            addCriterion("index_type is not null");
            return (Criteria) this;
        }

        public Criteria andIndexTypeEqualTo(String value) {
            addCriterion("index_type =", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeNotEqualTo(String value) {
            addCriterion("index_type <>", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeGreaterThan(String value) {
            addCriterion("index_type >", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeGreaterThanOrEqualTo(String value) {
            addCriterion("index_type >=", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeLessThan(String value) {
            addCriterion("index_type <", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeLessThanOrEqualTo(String value) {
            addCriterion("index_type <=", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeLike(String value) {
            addCriterion("index_type like", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeNotLike(String value) {
            addCriterion("index_type not like", value, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeIn(List<String> values) {
            addCriterion("index_type in", values, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeNotIn(List<String> values) {
            addCriterion("index_type not in", values, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeBetween(String value1, String value2) {
            addCriterion("index_type between", value1, value2, "indexType");
            return (Criteria) this;
        }

        public Criteria andIndexTypeNotBetween(String value1, String value2) {
            addCriterion("index_type not between", value1, value2, "indexType");
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

        public Criteria andIndexVerIsNull() {
            addCriterion("index_ver is null");
            return (Criteria) this;
        }

        public Criteria andIndexVerIsNotNull() {
            addCriterion("index_ver is not null");
            return (Criteria) this;
        }

        public Criteria andIndexVerEqualTo(String value) {
            addCriterion("index_ver =", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerNotEqualTo(String value) {
            addCriterion("index_ver <>", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerGreaterThan(String value) {
            addCriterion("index_ver >", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerGreaterThanOrEqualTo(String value) {
            addCriterion("index_ver >=", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerLessThan(String value) {
            addCriterion("index_ver <", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerLessThanOrEqualTo(String value) {
            addCriterion("index_ver <=", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerLike(String value) {
            addCriterion("index_ver like", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerNotLike(String value) {
            addCriterion("index_ver not like", value, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerIn(List<String> values) {
            addCriterion("index_ver in", values, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerNotIn(List<String> values) {
            addCriterion("index_ver not in", values, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerBetween(String value1, String value2) {
            addCriterion("index_ver between", value1, value2, "indexVer");
            return (Criteria) this;
        }

        public Criteria andIndexVerNotBetween(String value1, String value2) {
            addCriterion("index_ver not between", value1, value2, "indexVer");
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