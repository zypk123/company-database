package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class SourceSystemRecordMappingExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public SourceSystemRecordMappingExample() {
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

        public Criteria andRecdIdIsNull() {
            addCriterion("recd_id is null");
            return (Criteria) this;
        }

        public Criteria andRecdIdIsNotNull() {
            addCriterion("recd_id is not null");
            return (Criteria) this;
        }

        public Criteria andRecdIdEqualTo(String value) {
            addCriterion("recd_id =", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdNotEqualTo(String value) {
            addCriterion("recd_id <>", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdGreaterThan(String value) {
            addCriterion("recd_id >", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdGreaterThanOrEqualTo(String value) {
            addCriterion("recd_id >=", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdLessThan(String value) {
            addCriterion("recd_id <", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdLessThanOrEqualTo(String value) {
            addCriterion("recd_id <=", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdLike(String value) {
            addCriterion("recd_id like", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdNotLike(String value) {
            addCriterion("recd_id not like", value, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdIn(List<String> values) {
            addCriterion("recd_id in", values, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdNotIn(List<String> values) {
            addCriterion("recd_id not in", values, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdBetween(String value1, String value2) {
            addCriterion("recd_id between", value1, value2, "recdId");
            return (Criteria) this;
        }

        public Criteria andRecdIdNotBetween(String value1, String value2) {
            addCriterion("recd_id not between", value1, value2, "recdId");
            return (Criteria) this;
        }

        public Criteria andSourceNameIsNull() {
            addCriterion("source_name is null");
            return (Criteria) this;
        }

        public Criteria andSourceNameIsNotNull() {
            addCriterion("source_name is not null");
            return (Criteria) this;
        }

        public Criteria andSourceNameEqualTo(String value) {
            addCriterion("source_name =", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameNotEqualTo(String value) {
            addCriterion("source_name <>", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameGreaterThan(String value) {
            addCriterion("source_name >", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameGreaterThanOrEqualTo(String value) {
            addCriterion("source_name >=", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameLessThan(String value) {
            addCriterion("source_name <", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameLessThanOrEqualTo(String value) {
            addCriterion("source_name <=", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameLike(String value) {
            addCriterion("source_name like", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameNotLike(String value) {
            addCriterion("source_name not like", value, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameIn(List<String> values) {
            addCriterion("source_name in", values, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameNotIn(List<String> values) {
            addCriterion("source_name not in", values, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameBetween(String value1, String value2) {
            addCriterion("source_name between", value1, value2, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceNameNotBetween(String value1, String value2) {
            addCriterion("source_name not between", value1, value2, "sourceName");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdIsNull() {
            addCriterion("source_mapping_id is null");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdIsNotNull() {
            addCriterion("source_mapping_id is not null");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdEqualTo(String value) {
            addCriterion("source_mapping_id =", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdNotEqualTo(String value) {
            addCriterion("source_mapping_id <>", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdGreaterThan(String value) {
            addCriterion("source_mapping_id >", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdGreaterThanOrEqualTo(String value) {
            addCriterion("source_mapping_id >=", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdLessThan(String value) {
            addCriterion("source_mapping_id <", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdLessThanOrEqualTo(String value) {
            addCriterion("source_mapping_id <=", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdLike(String value) {
            addCriterion("source_mapping_id like", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdNotLike(String value) {
            addCriterion("source_mapping_id not like", value, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdIn(List<String> values) {
            addCriterion("source_mapping_id in", values, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdNotIn(List<String> values) {
            addCriterion("source_mapping_id not in", values, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdBetween(String value1, String value2) {
            addCriterion("source_mapping_id between", value1, value2, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingIdNotBetween(String value1, String value2) {
            addCriterion("source_mapping_id not between", value1, value2, "sourceMappingId");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeIsNull() {
            addCriterion("source_mapping_type is null");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeIsNotNull() {
            addCriterion("source_mapping_type is not null");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeEqualTo(String value) {
            addCriterion("source_mapping_type =", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeNotEqualTo(String value) {
            addCriterion("source_mapping_type <>", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeGreaterThan(String value) {
            addCriterion("source_mapping_type >", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeGreaterThanOrEqualTo(String value) {
            addCriterion("source_mapping_type >=", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeLessThan(String value) {
            addCriterion("source_mapping_type <", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeLessThanOrEqualTo(String value) {
            addCriterion("source_mapping_type <=", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeLike(String value) {
            addCriterion("source_mapping_type like", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeNotLike(String value) {
            addCriterion("source_mapping_type not like", value, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeIn(List<String> values) {
            addCriterion("source_mapping_type in", values, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeNotIn(List<String> values) {
            addCriterion("source_mapping_type not in", values, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeBetween(String value1, String value2) {
            addCriterion("source_mapping_type between", value1, value2, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andSourceMappingTypeNotBetween(String value1, String value2) {
            addCriterion("source_mapping_type not between", value1, value2, "sourceMappingType");
            return (Criteria) this;
        }

        public Criteria andInsideIdIsNull() {
            addCriterion("inside_id is null");
            return (Criteria) this;
        }

        public Criteria andInsideIdIsNotNull() {
            addCriterion("inside_id is not null");
            return (Criteria) this;
        }

        public Criteria andInsideIdEqualTo(String value) {
            addCriterion("inside_id =", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdNotEqualTo(String value) {
            addCriterion("inside_id <>", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdGreaterThan(String value) {
            addCriterion("inside_id >", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdGreaterThanOrEqualTo(String value) {
            addCriterion("inside_id >=", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdLessThan(String value) {
            addCriterion("inside_id <", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdLessThanOrEqualTo(String value) {
            addCriterion("inside_id <=", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdLike(String value) {
            addCriterion("inside_id like", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdNotLike(String value) {
            addCriterion("inside_id not like", value, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdIn(List<String> values) {
            addCriterion("inside_id in", values, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdNotIn(List<String> values) {
            addCriterion("inside_id not in", values, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdBetween(String value1, String value2) {
            addCriterion("inside_id between", value1, value2, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideIdNotBetween(String value1, String value2) {
            addCriterion("inside_id not between", value1, value2, "insideId");
            return (Criteria) this;
        }

        public Criteria andInsideTypeIsNull() {
            addCriterion("inside_type is null");
            return (Criteria) this;
        }

        public Criteria andInsideTypeIsNotNull() {
            addCriterion("inside_type is not null");
            return (Criteria) this;
        }

        public Criteria andInsideTypeEqualTo(String value) {
            addCriterion("inside_type =", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeNotEqualTo(String value) {
            addCriterion("inside_type <>", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeGreaterThan(String value) {
            addCriterion("inside_type >", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeGreaterThanOrEqualTo(String value) {
            addCriterion("inside_type >=", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeLessThan(String value) {
            addCriterion("inside_type <", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeLessThanOrEqualTo(String value) {
            addCriterion("inside_type <=", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeLike(String value) {
            addCriterion("inside_type like", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeNotLike(String value) {
            addCriterion("inside_type not like", value, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeIn(List<String> values) {
            addCriterion("inside_type in", values, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeNotIn(List<String> values) {
            addCriterion("inside_type not in", values, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeBetween(String value1, String value2) {
            addCriterion("inside_type between", value1, value2, "insideType");
            return (Criteria) this;
        }

        public Criteria andInsideTypeNotBetween(String value1, String value2) {
            addCriterion("inside_type not between", value1, value2, "insideType");
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