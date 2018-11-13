package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/8/12
 */
public class AuditRolesExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public AuditRolesExample() {
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

        public Criteria andAditIdIsNull() {
            addCriterion("adit_id is null");
            return (Criteria) this;
        }

        public Criteria andAditIdIsNotNull() {
            addCriterion("adit_id is not null");
            return (Criteria) this;
        }

        public Criteria andAditIdEqualTo(String value) {
            addCriterion("adit_id =", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdNotEqualTo(String value) {
            addCriterion("adit_id <>", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdGreaterThan(String value) {
            addCriterion("adit_id >", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdGreaterThanOrEqualTo(String value) {
            addCriterion("adit_id >=", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdLessThan(String value) {
            addCriterion("adit_id <", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdLessThanOrEqualTo(String value) {
            addCriterion("adit_id <=", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdLike(String value) {
            addCriterion("adit_id like", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdNotLike(String value) {
            addCriterion("adit_id not like", value, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdIn(List<String> values) {
            addCriterion("adit_id in", values, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdNotIn(List<String> values) {
            addCriterion("adit_id not in", values, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdBetween(String value1, String value2) {
            addCriterion("adit_id between", value1, value2, "aditId");
            return (Criteria) this;
        }

        public Criteria andAditIdNotBetween(String value1, String value2) {
            addCriterion("adit_id not between", value1, value2, "aditId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdIsNull() {
            addCriterion("process_docu_obj_id is null");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdIsNotNull() {
            addCriterion("process_docu_obj_id is not null");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdEqualTo(String value) {
            addCriterion("process_docu_obj_id =", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdNotEqualTo(String value) {
            addCriterion("process_docu_obj_id <>", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdGreaterThan(String value) {
            addCriterion("process_docu_obj_id >", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdGreaterThanOrEqualTo(String value) {
            addCriterion("process_docu_obj_id >=", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdLessThan(String value) {
            addCriterion("process_docu_obj_id <", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdLessThanOrEqualTo(String value) {
            addCriterion("process_docu_obj_id <=", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdLike(String value) {
            addCriterion("process_docu_obj_id like", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdNotLike(String value) {
            addCriterion("process_docu_obj_id not like", value, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdIn(List<String> values) {
            addCriterion("process_docu_obj_id in", values, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdNotIn(List<String> values) {
            addCriterion("process_docu_obj_id not in", values, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdBetween(String value1, String value2) {
            addCriterion("process_docu_obj_id between", value1, value2, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andProcessDocuObjIdNotBetween(String value1, String value2) {
            addCriterion("process_docu_obj_id not between", value1, value2, "processDocuObjId");
            return (Criteria) this;
        }

        public Criteria andAuditSeqIsNull() {
            addCriterion("audit_seq is null");
            return (Criteria) this;
        }

        public Criteria andAuditSeqIsNotNull() {
            addCriterion("audit_seq is not null");
            return (Criteria) this;
        }

        public Criteria andAuditSeqEqualTo(Integer value) {
            addCriterion("audit_seq =", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqNotEqualTo(Integer value) {
            addCriterion("audit_seq <>", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqGreaterThan(Integer value) {
            addCriterion("audit_seq >", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqGreaterThanOrEqualTo(Integer value) {
            addCriterion("audit_seq >=", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqLessThan(Integer value) {
            addCriterion("audit_seq <", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqLessThanOrEqualTo(Integer value) {
            addCriterion("audit_seq <=", value, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqIn(List<Integer> values) {
            addCriterion("audit_seq in", values, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqNotIn(List<Integer> values) {
            addCriterion("audit_seq not in", values, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqBetween(Integer value1, Integer value2) {
            addCriterion("audit_seq between", value1, value2, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditSeqNotBetween(Integer value1, Integer value2) {
            addCriterion("audit_seq not between", value1, value2, "auditSeq");
            return (Criteria) this;
        }

        public Criteria andAuditPatternIsNull() {
            addCriterion("audit_pattern is null");
            return (Criteria) this;
        }

        public Criteria andAuditPatternIsNotNull() {
            addCriterion("audit_pattern is not null");
            return (Criteria) this;
        }

        public Criteria andAuditPatternEqualTo(String value) {
            addCriterion("audit_pattern =", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternNotEqualTo(String value) {
            addCriterion("audit_pattern <>", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternGreaterThan(String value) {
            addCriterion("audit_pattern >", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternGreaterThanOrEqualTo(String value) {
            addCriterion("audit_pattern >=", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternLessThan(String value) {
            addCriterion("audit_pattern <", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternLessThanOrEqualTo(String value) {
            addCriterion("audit_pattern <=", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternLike(String value) {
            addCriterion("audit_pattern like", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternNotLike(String value) {
            addCriterion("audit_pattern not like", value, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternIn(List<String> values) {
            addCriterion("audit_pattern in", values, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternNotIn(List<String> values) {
            addCriterion("audit_pattern not in", values, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternBetween(String value1, String value2) {
            addCriterion("audit_pattern between", value1, value2, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditPatternNotBetween(String value1, String value2) {
            addCriterion("audit_pattern not between", value1, value2, "auditPattern");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffIsNull() {
            addCriterion("audit_roles_staff is null");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffIsNotNull() {
            addCriterion("audit_roles_staff is not null");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffEqualTo(String value) {
            addCriterion("audit_roles_staff =", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffNotEqualTo(String value) {
            addCriterion("audit_roles_staff <>", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffGreaterThan(String value) {
            addCriterion("audit_roles_staff >", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffGreaterThanOrEqualTo(String value) {
            addCriterion("audit_roles_staff >=", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffLessThan(String value) {
            addCriterion("audit_roles_staff <", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffLessThanOrEqualTo(String value) {
            addCriterion("audit_roles_staff <=", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffLike(String value) {
            addCriterion("audit_roles_staff like", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffNotLike(String value) {
            addCriterion("audit_roles_staff not like", value, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffIn(List<String> values) {
            addCriterion("audit_roles_staff in", values, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffNotIn(List<String> values) {
            addCriterion("audit_roles_staff not in", values, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffBetween(String value1, String value2) {
            addCriterion("audit_roles_staff between", value1, value2, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditRolesStaffNotBetween(String value1, String value2) {
            addCriterion("audit_roles_staff not between", value1, value2, "auditRolesStaff");
            return (Criteria) this;
        }

        public Criteria andAuditConditionIsNull() {
            addCriterion("audit_condition is null");
            return (Criteria) this;
        }

        public Criteria andAuditConditionIsNotNull() {
            addCriterion("audit_condition is not null");
            return (Criteria) this;
        }

        public Criteria andAuditConditionEqualTo(String value) {
            addCriterion("audit_condition =", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionNotEqualTo(String value) {
            addCriterion("audit_condition <>", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionGreaterThan(String value) {
            addCriterion("audit_condition >", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionGreaterThanOrEqualTo(String value) {
            addCriterion("audit_condition >=", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionLessThan(String value) {
            addCriterion("audit_condition <", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionLessThanOrEqualTo(String value) {
            addCriterion("audit_condition <=", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionLike(String value) {
            addCriterion("audit_condition like", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionNotLike(String value) {
            addCriterion("audit_condition not like", value, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionIn(List<String> values) {
            addCriterion("audit_condition in", values, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionNotIn(List<String> values) {
            addCriterion("audit_condition not in", values, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionBetween(String value1, String value2) {
            addCriterion("audit_condition between", value1, value2, "auditCondition");
            return (Criteria) this;
        }

        public Criteria andAuditConditionNotBetween(String value1, String value2) {
            addCriterion("audit_condition not between", value1, value2, "auditCondition");
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

        public Criteria andAlternateField1IsNull() {
            addCriterion("alternate_field1 is null");
            return (Criteria) this;
        }

        public Criteria andAlternateField1IsNotNull() {
            addCriterion("alternate_field1 is not null");
            return (Criteria) this;
        }

        public Criteria andAlternateField1EqualTo(String value) {
            addCriterion("alternate_field1 =", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1NotEqualTo(String value) {
            addCriterion("alternate_field1 <>", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1GreaterThan(String value) {
            addCriterion("alternate_field1 >", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1GreaterThanOrEqualTo(String value) {
            addCriterion("alternate_field1 >=", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1LessThan(String value) {
            addCriterion("alternate_field1 <", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1LessThanOrEqualTo(String value) {
            addCriterion("alternate_field1 <=", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1Like(String value) {
            addCriterion("alternate_field1 like", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1NotLike(String value) {
            addCriterion("alternate_field1 not like", value, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1In(List<String> values) {
            addCriterion("alternate_field1 in", values, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1NotIn(List<String> values) {
            addCriterion("alternate_field1 not in", values, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1Between(String value1, String value2) {
            addCriterion("alternate_field1 between", value1, value2, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField1NotBetween(String value1, String value2) {
            addCriterion("alternate_field1 not between", value1, value2, "alternateField1");
            return (Criteria) this;
        }

        public Criteria andAlternateField2IsNull() {
            addCriterion("alternate_field2 is null");
            return (Criteria) this;
        }

        public Criteria andAlternateField2IsNotNull() {
            addCriterion("alternate_field2 is not null");
            return (Criteria) this;
        }

        public Criteria andAlternateField2EqualTo(String value) {
            addCriterion("alternate_field2 =", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2NotEqualTo(String value) {
            addCriterion("alternate_field2 <>", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2GreaterThan(String value) {
            addCriterion("alternate_field2 >", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2GreaterThanOrEqualTo(String value) {
            addCriterion("alternate_field2 >=", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2LessThan(String value) {
            addCriterion("alternate_field2 <", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2LessThanOrEqualTo(String value) {
            addCriterion("alternate_field2 <=", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2Like(String value) {
            addCriterion("alternate_field2 like", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2NotLike(String value) {
            addCriterion("alternate_field2 not like", value, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2In(List<String> values) {
            addCriterion("alternate_field2 in", values, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2NotIn(List<String> values) {
            addCriterion("alternate_field2 not in", values, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2Between(String value1, String value2) {
            addCriterion("alternate_field2 between", value1, value2, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField2NotBetween(String value1, String value2) {
            addCriterion("alternate_field2 not between", value1, value2, "alternateField2");
            return (Criteria) this;
        }

        public Criteria andAlternateField3IsNull() {
            addCriterion("alternate_field3 is null");
            return (Criteria) this;
        }

        public Criteria andAlternateField3IsNotNull() {
            addCriterion("alternate_field3 is not null");
            return (Criteria) this;
        }

        public Criteria andAlternateField3EqualTo(String value) {
            addCriterion("alternate_field3 =", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3NotEqualTo(String value) {
            addCriterion("alternate_field3 <>", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3GreaterThan(String value) {
            addCriterion("alternate_field3 >", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3GreaterThanOrEqualTo(String value) {
            addCriterion("alternate_field3 >=", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3LessThan(String value) {
            addCriterion("alternate_field3 <", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3LessThanOrEqualTo(String value) {
            addCriterion("alternate_field3 <=", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3Like(String value) {
            addCriterion("alternate_field3 like", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3NotLike(String value) {
            addCriterion("alternate_field3 not like", value, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3In(List<String> values) {
            addCriterion("alternate_field3 in", values, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3NotIn(List<String> values) {
            addCriterion("alternate_field3 not in", values, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3Between(String value1, String value2) {
            addCriterion("alternate_field3 between", value1, value2, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField3NotBetween(String value1, String value2) {
            addCriterion("alternate_field3 not between", value1, value2, "alternateField3");
            return (Criteria) this;
        }

        public Criteria andAlternateField4IsNull() {
            addCriterion("alternate_field4 is null");
            return (Criteria) this;
        }

        public Criteria andAlternateField4IsNotNull() {
            addCriterion("alternate_field4 is not null");
            return (Criteria) this;
        }

        public Criteria andAlternateField4EqualTo(String value) {
            addCriterion("alternate_field4 =", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4NotEqualTo(String value) {
            addCriterion("alternate_field4 <>", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4GreaterThan(String value) {
            addCriterion("alternate_field4 >", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4GreaterThanOrEqualTo(String value) {
            addCriterion("alternate_field4 >=", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4LessThan(String value) {
            addCriterion("alternate_field4 <", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4LessThanOrEqualTo(String value) {
            addCriterion("alternate_field4 <=", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4Like(String value) {
            addCriterion("alternate_field4 like", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4NotLike(String value) {
            addCriterion("alternate_field4 not like", value, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4In(List<String> values) {
            addCriterion("alternate_field4 in", values, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4NotIn(List<String> values) {
            addCriterion("alternate_field4 not in", values, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4Between(String value1, String value2) {
            addCriterion("alternate_field4 between", value1, value2, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField4NotBetween(String value1, String value2) {
            addCriterion("alternate_field4 not between", value1, value2, "alternateField4");
            return (Criteria) this;
        }

        public Criteria andAlternateField5IsNull() {
            addCriterion("alternate_field5 is null");
            return (Criteria) this;
        }

        public Criteria andAlternateField5IsNotNull() {
            addCriterion("alternate_field5 is not null");
            return (Criteria) this;
        }

        public Criteria andAlternateField5EqualTo(String value) {
            addCriterion("alternate_field5 =", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5NotEqualTo(String value) {
            addCriterion("alternate_field5 <>", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5GreaterThan(String value) {
            addCriterion("alternate_field5 >", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5GreaterThanOrEqualTo(String value) {
            addCriterion("alternate_field5 >=", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5LessThan(String value) {
            addCriterion("alternate_field5 <", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5LessThanOrEqualTo(String value) {
            addCriterion("alternate_field5 <=", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5Like(String value) {
            addCriterion("alternate_field5 like", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5NotLike(String value) {
            addCriterion("alternate_field5 not like", value, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5In(List<String> values) {
            addCriterion("alternate_field5 in", values, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5NotIn(List<String> values) {
            addCriterion("alternate_field5 not in", values, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5Between(String value1, String value2) {
            addCriterion("alternate_field5 between", value1, value2, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andAlternateField5NotBetween(String value1, String value2) {
            addCriterion("alternate_field5 not between", value1, value2, "alternateField5");
            return (Criteria) this;
        }

        public Criteria andMarkIsNull() {
            addCriterion("mark is null");
            return (Criteria) this;
        }

        public Criteria andMarkIsNotNull() {
            addCriterion("mark is not null");
            return (Criteria) this;
        }

        public Criteria andMarkEqualTo(String value) {
            addCriterion("mark =", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotEqualTo(String value) {
            addCriterion("mark <>", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThan(String value) {
            addCriterion("mark >", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkGreaterThanOrEqualTo(String value) {
            addCriterion("mark >=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThan(String value) {
            addCriterion("mark <", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLessThanOrEqualTo(String value) {
            addCriterion("mark <=", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkLike(String value) {
            addCriterion("mark like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotLike(String value) {
            addCriterion("mark not like", value, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkIn(List<String> values) {
            addCriterion("mark in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotIn(List<String> values) {
            addCriterion("mark not in", values, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkBetween(String value1, String value2) {
            addCriterion("mark between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andMarkNotBetween(String value1, String value2) {
            addCriterion("mark not between", value1, value2, "mark");
            return (Criteria) this;
        }

        public Criteria andChooseCodeIsNull() {
            addCriterion("choose_code is null");
            return (Criteria) this;
        }

        public Criteria andChooseCodeIsNotNull() {
            addCriterion("choose_code is not null");
            return (Criteria) this;
        }

        public Criteria andChooseCodeEqualTo(String value) {
            addCriterion("choose_code =", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeNotEqualTo(String value) {
            addCriterion("choose_code <>", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeGreaterThan(String value) {
            addCriterion("choose_code >", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeGreaterThanOrEqualTo(String value) {
            addCriterion("choose_code >=", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeLessThan(String value) {
            addCriterion("choose_code <", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeLessThanOrEqualTo(String value) {
            addCriterion("choose_code <=", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeLike(String value) {
            addCriterion("choose_code like", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeNotLike(String value) {
            addCriterion("choose_code not like", value, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeIn(List<String> values) {
            addCriterion("choose_code in", values, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeNotIn(List<String> values) {
            addCriterion("choose_code not in", values, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeBetween(String value1, String value2) {
            addCriterion("choose_code between", value1, value2, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andChooseCodeNotBetween(String value1, String value2) {
            addCriterion("choose_code not between", value1, value2, "chooseCode");
            return (Criteria) this;
        }

        public Criteria andBeiyong1IsNull() {
            addCriterion("beiyong1 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong1IsNotNull() {
            addCriterion("beiyong1 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong1EqualTo(String value) {
            addCriterion("beiyong1 =", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1NotEqualTo(String value) {
            addCriterion("beiyong1 <>", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1GreaterThan(String value) {
            addCriterion("beiyong1 >", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong1 >=", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1LessThan(String value) {
            addCriterion("beiyong1 <", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1LessThanOrEqualTo(String value) {
            addCriterion("beiyong1 <=", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1Like(String value) {
            addCriterion("beiyong1 like", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1NotLike(String value) {
            addCriterion("beiyong1 not like", value, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1In(List<String> values) {
            addCriterion("beiyong1 in", values, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1NotIn(List<String> values) {
            addCriterion("beiyong1 not in", values, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1Between(String value1, String value2) {
            addCriterion("beiyong1 between", value1, value2, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong1NotBetween(String value1, String value2) {
            addCriterion("beiyong1 not between", value1, value2, "beiyong1");
            return (Criteria) this;
        }

        public Criteria andBeiyong2IsNull() {
            addCriterion("beiyong2 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong2IsNotNull() {
            addCriterion("beiyong2 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong2EqualTo(String value) {
            addCriterion("beiyong2 =", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2NotEqualTo(String value) {
            addCriterion("beiyong2 <>", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2GreaterThan(String value) {
            addCriterion("beiyong2 >", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong2 >=", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2LessThan(String value) {
            addCriterion("beiyong2 <", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2LessThanOrEqualTo(String value) {
            addCriterion("beiyong2 <=", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2Like(String value) {
            addCriterion("beiyong2 like", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2NotLike(String value) {
            addCriterion("beiyong2 not like", value, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2In(List<String> values) {
            addCriterion("beiyong2 in", values, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2NotIn(List<String> values) {
            addCriterion("beiyong2 not in", values, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2Between(String value1, String value2) {
            addCriterion("beiyong2 between", value1, value2, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong2NotBetween(String value1, String value2) {
            addCriterion("beiyong2 not between", value1, value2, "beiyong2");
            return (Criteria) this;
        }

        public Criteria andBeiyong3IsNull() {
            addCriterion("beiyong3 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong3IsNotNull() {
            addCriterion("beiyong3 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong3EqualTo(String value) {
            addCriterion("beiyong3 =", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3NotEqualTo(String value) {
            addCriterion("beiyong3 <>", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3GreaterThan(String value) {
            addCriterion("beiyong3 >", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong3 >=", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3LessThan(String value) {
            addCriterion("beiyong3 <", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3LessThanOrEqualTo(String value) {
            addCriterion("beiyong3 <=", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3Like(String value) {
            addCriterion("beiyong3 like", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3NotLike(String value) {
            addCriterion("beiyong3 not like", value, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3In(List<String> values) {
            addCriterion("beiyong3 in", values, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3NotIn(List<String> values) {
            addCriterion("beiyong3 not in", values, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3Between(String value1, String value2) {
            addCriterion("beiyong3 between", value1, value2, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andBeiyong3NotBetween(String value1, String value2) {
            addCriterion("beiyong3 not between", value1, value2, "beiyong3");
            return (Criteria) this;
        }

        public Criteria andPostOperateIsNull() {
            addCriterion("post_operate is null");
            return (Criteria) this;
        }

        public Criteria andPostOperateIsNotNull() {
            addCriterion("post_operate is not null");
            return (Criteria) this;
        }

        public Criteria andPostOperateEqualTo(String value) {
            addCriterion("post_operate =", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateNotEqualTo(String value) {
            addCriterion("post_operate <>", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateGreaterThan(String value) {
            addCriterion("post_operate >", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateGreaterThanOrEqualTo(String value) {
            addCriterion("post_operate >=", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateLessThan(String value) {
            addCriterion("post_operate <", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateLessThanOrEqualTo(String value) {
            addCriterion("post_operate <=", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateLike(String value) {
            addCriterion("post_operate like", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateNotLike(String value) {
            addCriterion("post_operate not like", value, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateIn(List<String> values) {
            addCriterion("post_operate in", values, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateNotIn(List<String> values) {
            addCriterion("post_operate not in", values, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateBetween(String value1, String value2) {
            addCriterion("post_operate between", value1, value2, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostOperateNotBetween(String value1, String value2) {
            addCriterion("post_operate not between", value1, value2, "postOperate");
            return (Criteria) this;
        }

        public Criteria andPostValueIsNull() {
            addCriterion("post_value is null");
            return (Criteria) this;
        }

        public Criteria andPostValueIsNotNull() {
            addCriterion("post_value is not null");
            return (Criteria) this;
        }

        public Criteria andPostValueEqualTo(String value) {
            addCriterion("post_value =", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueNotEqualTo(String value) {
            addCriterion("post_value <>", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueGreaterThan(String value) {
            addCriterion("post_value >", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueGreaterThanOrEqualTo(String value) {
            addCriterion("post_value >=", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueLessThan(String value) {
            addCriterion("post_value <", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueLessThanOrEqualTo(String value) {
            addCriterion("post_value <=", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueLike(String value) {
            addCriterion("post_value like", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueNotLike(String value) {
            addCriterion("post_value not like", value, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueIn(List<String> values) {
            addCriterion("post_value in", values, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueNotIn(List<String> values) {
            addCriterion("post_value not in", values, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueBetween(String value1, String value2) {
            addCriterion("post_value between", value1, value2, "postValue");
            return (Criteria) this;
        }

        public Criteria andPostValueNotBetween(String value1, String value2) {
            addCriterion("post_value not between", value1, value2, "postValue");
            return (Criteria) this;
        }

        public Criteria andDeptOperateIsNull() {
            addCriterion("dept_operate is null");
            return (Criteria) this;
        }

        public Criteria andDeptOperateIsNotNull() {
            addCriterion("dept_operate is not null");
            return (Criteria) this;
        }

        public Criteria andDeptOperateEqualTo(String value) {
            addCriterion("dept_operate =", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateNotEqualTo(String value) {
            addCriterion("dept_operate <>", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateGreaterThan(String value) {
            addCriterion("dept_operate >", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateGreaterThanOrEqualTo(String value) {
            addCriterion("dept_operate >=", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateLessThan(String value) {
            addCriterion("dept_operate <", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateLessThanOrEqualTo(String value) {
            addCriterion("dept_operate <=", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateLike(String value) {
            addCriterion("dept_operate like", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateNotLike(String value) {
            addCriterion("dept_operate not like", value, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateIn(List<String> values) {
            addCriterion("dept_operate in", values, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateNotIn(List<String> values) {
            addCriterion("dept_operate not in", values, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateBetween(String value1, String value2) {
            addCriterion("dept_operate between", value1, value2, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptOperateNotBetween(String value1, String value2) {
            addCriterion("dept_operate not between", value1, value2, "deptOperate");
            return (Criteria) this;
        }

        public Criteria andDeptValueIsNull() {
            addCriterion("dept_value is null");
            return (Criteria) this;
        }

        public Criteria andDeptValueIsNotNull() {
            addCriterion("dept_value is not null");
            return (Criteria) this;
        }

        public Criteria andDeptValueEqualTo(String value) {
            addCriterion("dept_value =", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueNotEqualTo(String value) {
            addCriterion("dept_value <>", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueGreaterThan(String value) {
            addCriterion("dept_value >", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueGreaterThanOrEqualTo(String value) {
            addCriterion("dept_value >=", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueLessThan(String value) {
            addCriterion("dept_value <", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueLessThanOrEqualTo(String value) {
            addCriterion("dept_value <=", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueLike(String value) {
            addCriterion("dept_value like", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueNotLike(String value) {
            addCriterion("dept_value not like", value, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueIn(List<String> values) {
            addCriterion("dept_value in", values, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueNotIn(List<String> values) {
            addCriterion("dept_value not in", values, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueBetween(String value1, String value2) {
            addCriterion("dept_value between", value1, value2, "deptValue");
            return (Criteria) this;
        }

        public Criteria andDeptValueNotBetween(String value1, String value2) {
            addCriterion("dept_value not between", value1, value2, "deptValue");
            return (Criteria) this;
        }

        public Criteria andBeiyong4IsNull() {
            addCriterion("beiyong4 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong4IsNotNull() {
            addCriterion("beiyong4 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong4EqualTo(String value) {
            addCriterion("beiyong4 =", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4NotEqualTo(String value) {
            addCriterion("beiyong4 <>", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4GreaterThan(String value) {
            addCriterion("beiyong4 >", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong4 >=", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4LessThan(String value) {
            addCriterion("beiyong4 <", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4LessThanOrEqualTo(String value) {
            addCriterion("beiyong4 <=", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4Like(String value) {
            addCriterion("beiyong4 like", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4NotLike(String value) {
            addCriterion("beiyong4 not like", value, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4In(List<String> values) {
            addCriterion("beiyong4 in", values, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4NotIn(List<String> values) {
            addCriterion("beiyong4 not in", values, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4Between(String value1, String value2) {
            addCriterion("beiyong4 between", value1, value2, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong4NotBetween(String value1, String value2) {
            addCriterion("beiyong4 not between", value1, value2, "beiyong4");
            return (Criteria) this;
        }

        public Criteria andBeiyong5IsNull() {
            addCriterion("beiyong5 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong5IsNotNull() {
            addCriterion("beiyong5 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong5EqualTo(String value) {
            addCriterion("beiyong5 =", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5NotEqualTo(String value) {
            addCriterion("beiyong5 <>", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5GreaterThan(String value) {
            addCriterion("beiyong5 >", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong5 >=", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5LessThan(String value) {
            addCriterion("beiyong5 <", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5LessThanOrEqualTo(String value) {
            addCriterion("beiyong5 <=", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5Like(String value) {
            addCriterion("beiyong5 like", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5NotLike(String value) {
            addCriterion("beiyong5 not like", value, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5In(List<String> values) {
            addCriterion("beiyong5 in", values, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5NotIn(List<String> values) {
            addCriterion("beiyong5 not in", values, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5Between(String value1, String value2) {
            addCriterion("beiyong5 between", value1, value2, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong5NotBetween(String value1, String value2) {
            addCriterion("beiyong5 not between", value1, value2, "beiyong5");
            return (Criteria) this;
        }

        public Criteria andBeiyong6IsNull() {
            addCriterion("beiyong6 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong6IsNotNull() {
            addCriterion("beiyong6 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong6EqualTo(String value) {
            addCriterion("beiyong6 =", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6NotEqualTo(String value) {
            addCriterion("beiyong6 <>", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6GreaterThan(String value) {
            addCriterion("beiyong6 >", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong6 >=", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6LessThan(String value) {
            addCriterion("beiyong6 <", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6LessThanOrEqualTo(String value) {
            addCriterion("beiyong6 <=", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6Like(String value) {
            addCriterion("beiyong6 like", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6NotLike(String value) {
            addCriterion("beiyong6 not like", value, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6In(List<String> values) {
            addCriterion("beiyong6 in", values, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6NotIn(List<String> values) {
            addCriterion("beiyong6 not in", values, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6Between(String value1, String value2) {
            addCriterion("beiyong6 between", value1, value2, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong6NotBetween(String value1, String value2) {
            addCriterion("beiyong6 not between", value1, value2, "beiyong6");
            return (Criteria) this;
        }

        public Criteria andBeiyong7IsNull() {
            addCriterion("beiyong7 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong7IsNotNull() {
            addCriterion("beiyong7 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong7EqualTo(String value) {
            addCriterion("beiyong7 =", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7NotEqualTo(String value) {
            addCriterion("beiyong7 <>", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7GreaterThan(String value) {
            addCriterion("beiyong7 >", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong7 >=", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7LessThan(String value) {
            addCriterion("beiyong7 <", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7LessThanOrEqualTo(String value) {
            addCriterion("beiyong7 <=", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7Like(String value) {
            addCriterion("beiyong7 like", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7NotLike(String value) {
            addCriterion("beiyong7 not like", value, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7In(List<String> values) {
            addCriterion("beiyong7 in", values, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7NotIn(List<String> values) {
            addCriterion("beiyong7 not in", values, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7Between(String value1, String value2) {
            addCriterion("beiyong7 between", value1, value2, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong7NotBetween(String value1, String value2) {
            addCriterion("beiyong7 not between", value1, value2, "beiyong7");
            return (Criteria) this;
        }

        public Criteria andBeiyong8IsNull() {
            addCriterion("beiyong8 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong8IsNotNull() {
            addCriterion("beiyong8 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong8EqualTo(String value) {
            addCriterion("beiyong8 =", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8NotEqualTo(String value) {
            addCriterion("beiyong8 <>", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8GreaterThan(String value) {
            addCriterion("beiyong8 >", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong8 >=", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8LessThan(String value) {
            addCriterion("beiyong8 <", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8LessThanOrEqualTo(String value) {
            addCriterion("beiyong8 <=", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8Like(String value) {
            addCriterion("beiyong8 like", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8NotLike(String value) {
            addCriterion("beiyong8 not like", value, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8In(List<String> values) {
            addCriterion("beiyong8 in", values, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8NotIn(List<String> values) {
            addCriterion("beiyong8 not in", values, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8Between(String value1, String value2) {
            addCriterion("beiyong8 between", value1, value2, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong8NotBetween(String value1, String value2) {
            addCriterion("beiyong8 not between", value1, value2, "beiyong8");
            return (Criteria) this;
        }

        public Criteria andBeiyong9IsNull() {
            addCriterion("beiyong9 is null");
            return (Criteria) this;
        }

        public Criteria andBeiyong9IsNotNull() {
            addCriterion("beiyong9 is not null");
            return (Criteria) this;
        }

        public Criteria andBeiyong9EqualTo(String value) {
            addCriterion("beiyong9 =", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9NotEqualTo(String value) {
            addCriterion("beiyong9 <>", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9GreaterThan(String value) {
            addCriterion("beiyong9 >", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9GreaterThanOrEqualTo(String value) {
            addCriterion("beiyong9 >=", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9LessThan(String value) {
            addCriterion("beiyong9 <", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9LessThanOrEqualTo(String value) {
            addCriterion("beiyong9 <=", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9Like(String value) {
            addCriterion("beiyong9 like", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9NotLike(String value) {
            addCriterion("beiyong9 not like", value, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9In(List<String> values) {
            addCriterion("beiyong9 in", values, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9NotIn(List<String> values) {
            addCriterion("beiyong9 not in", values, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9Between(String value1, String value2) {
            addCriterion("beiyong9 between", value1, value2, "beiyong9");
            return (Criteria) this;
        }

        public Criteria andBeiyong9NotBetween(String value1, String value2) {
            addCriterion("beiyong9 not between", value1, value2, "beiyong9");
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