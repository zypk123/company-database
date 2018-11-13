package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmMethodArgExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmMethodArgExample() {
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

        public Criteria andEdmaEdmmIdIsNull() {
            addCriterion("edma_edmm_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdIsNotNull() {
            addCriterion("edma_edmm_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdEqualTo(String value) {
            addCriterion("edma_edmm_id =", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdNotEqualTo(String value) {
            addCriterion("edma_edmm_id <>", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdGreaterThan(String value) {
            addCriterion("edma_edmm_id >", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdGreaterThanOrEqualTo(String value) {
            addCriterion("edma_edmm_id >=", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdLessThan(String value) {
            addCriterion("edma_edmm_id <", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdLessThanOrEqualTo(String value) {
            addCriterion("edma_edmm_id <=", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdLike(String value) {
            addCriterion("edma_edmm_id like", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdNotLike(String value) {
            addCriterion("edma_edmm_id not like", value, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdIn(List<String> values) {
            addCriterion("edma_edmm_id in", values, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdNotIn(List<String> values) {
            addCriterion("edma_edmm_id not in", values, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdBetween(String value1, String value2) {
            addCriterion("edma_edmm_id between", value1, value2, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaEdmmIdNotBetween(String value1, String value2) {
            addCriterion("edma_edmm_id not between", value1, value2, "edmaEdmmId");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeIsNull() {
            addCriterion("edma_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeIsNotNull() {
            addCriterion("edma_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeEqualTo(Byte value) {
            addCriterion("edma_type =", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeNotEqualTo(Byte value) {
            addCriterion("edma_type <>", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeGreaterThan(Byte value) {
            addCriterion("edma_type >", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeGreaterThanOrEqualTo(Byte value) {
            addCriterion("edma_type >=", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeLessThan(Byte value) {
            addCriterion("edma_type <", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeLessThanOrEqualTo(Byte value) {
            addCriterion("edma_type <=", value, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeIn(List<Byte> values) {
            addCriterion("edma_type in", values, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeNotIn(List<Byte> values) {
            addCriterion("edma_type not in", values, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeBetween(Byte value1, Byte value2) {
            addCriterion("edma_type between", value1, value2, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaTypeNotBetween(Byte value1, Byte value2) {
            addCriterion("edma_type not between", value1, value2, "edmaType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeIsNull() {
            addCriterion("edma_data_type is null");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeIsNotNull() {
            addCriterion("edma_data_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeEqualTo(String value) {
            addCriterion("edma_data_type =", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeNotEqualTo(String value) {
            addCriterion("edma_data_type <>", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeGreaterThan(String value) {
            addCriterion("edma_data_type >", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edma_data_type >=", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeLessThan(String value) {
            addCriterion("edma_data_type <", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeLessThanOrEqualTo(String value) {
            addCriterion("edma_data_type <=", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeLike(String value) {
            addCriterion("edma_data_type like", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeNotLike(String value) {
            addCriterion("edma_data_type not like", value, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeIn(List<String> values) {
            addCriterion("edma_data_type in", values, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeNotIn(List<String> values) {
            addCriterion("edma_data_type not in", values, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeBetween(String value1, String value2) {
            addCriterion("edma_data_type between", value1, value2, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaDataTypeNotBetween(String value1, String value2) {
            addCriterion("edma_data_type not between", value1, value2, "edmaDataType");
            return (Criteria) this;
        }

        public Criteria andEdmaNameIsNull() {
            addCriterion("edma_name is null");
            return (Criteria) this;
        }

        public Criteria andEdmaNameIsNotNull() {
            addCriterion("edma_name is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaNameEqualTo(String value) {
            addCriterion("edma_name =", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameNotEqualTo(String value) {
            addCriterion("edma_name <>", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameGreaterThan(String value) {
            addCriterion("edma_name >", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameGreaterThanOrEqualTo(String value) {
            addCriterion("edma_name >=", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameLessThan(String value) {
            addCriterion("edma_name <", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameLessThanOrEqualTo(String value) {
            addCriterion("edma_name <=", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameLike(String value) {
            addCriterion("edma_name like", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameNotLike(String value) {
            addCriterion("edma_name not like", value, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameIn(List<String> values) {
            addCriterion("edma_name in", values, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameNotIn(List<String> values) {
            addCriterion("edma_name not in", values, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameBetween(String value1, String value2) {
            addCriterion("edma_name between", value1, value2, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaNameNotBetween(String value1, String value2) {
            addCriterion("edma_name not between", value1, value2, "edmaName");
            return (Criteria) this;
        }

        public Criteria andEdmaDescIsNull() {
            addCriterion("edma_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmaDescIsNotNull() {
            addCriterion("edma_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaDescEqualTo(String value) {
            addCriterion("edma_desc =", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescNotEqualTo(String value) {
            addCriterion("edma_desc <>", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescGreaterThan(String value) {
            addCriterion("edma_desc >", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescGreaterThanOrEqualTo(String value) {
            addCriterion("edma_desc >=", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescLessThan(String value) {
            addCriterion("edma_desc <", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescLessThanOrEqualTo(String value) {
            addCriterion("edma_desc <=", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescLike(String value) {
            addCriterion("edma_desc like", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescNotLike(String value) {
            addCriterion("edma_desc not like", value, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescIn(List<String> values) {
            addCriterion("edma_desc in", values, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescNotIn(List<String> values) {
            addCriterion("edma_desc not in", values, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescBetween(String value1, String value2) {
            addCriterion("edma_desc between", value1, value2, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaDescNotBetween(String value1, String value2) {
            addCriterion("edma_desc not between", value1, value2, "edmaDesc");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqIsNull() {
            addCriterion("edma_seq is null");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqIsNotNull() {
            addCriterion("edma_seq is not null");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqEqualTo(Integer value) {
            addCriterion("edma_seq =", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqNotEqualTo(Integer value) {
            addCriterion("edma_seq <>", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqGreaterThan(Integer value) {
            addCriterion("edma_seq >", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("edma_seq >=", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqLessThan(Integer value) {
            addCriterion("edma_seq <", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqLessThanOrEqualTo(Integer value) {
            addCriterion("edma_seq <=", value, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqIn(List<Integer> values) {
            addCriterion("edma_seq in", values, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqNotIn(List<Integer> values) {
            addCriterion("edma_seq not in", values, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqBetween(Integer value1, Integer value2) {
            addCriterion("edma_seq between", value1, value2, "edmaSeq");
            return (Criteria) this;
        }

        public Criteria andEdmaSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("edma_seq not between", value1, value2, "edmaSeq");
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

        @Override
        public String toString() {
            return "GeneratedCriteria{" +
                    "criteria=" + criteria.toString() +
                    '}';
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
    }

    @Override
    public String toString() {
        return "EdmMethodArgExample{" +
                "orderByClause='" + orderByClause + '\'' +
                ", distinct=" + distinct +
                ", oredCriteria=" + oredCriteria.toString() +
                '}';
    }
}