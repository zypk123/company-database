package com.huntkey.rx.sceo.formula.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfmFunctionDefinitionExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TfmFunctionDefinitionExample() {
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

        public Criteria andFundIdIsNull() {
            addCriterion("fund_id is null");
            return (Criteria) this;
        }

        public Criteria andFundIdIsNotNull() {
            addCriterion("fund_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundIdEqualTo(String value) {
            addCriterion("fund_id =", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotEqualTo(String value) {
            addCriterion("fund_id <>", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdGreaterThan(String value) {
            addCriterion("fund_id >", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdGreaterThanOrEqualTo(String value) {
            addCriterion("fund_id >=", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLessThan(String value) {
            addCriterion("fund_id <", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLessThanOrEqualTo(String value) {
            addCriterion("fund_id <=", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdLike(String value) {
            addCriterion("fund_id like", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotLike(String value) {
            addCriterion("fund_id not like", value, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdIn(List<String> values) {
            addCriterion("fund_id in", values, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotIn(List<String> values) {
            addCriterion("fund_id not in", values, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdBetween(String value1, String value2) {
            addCriterion("fund_id between", value1, value2, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundIdNotBetween(String value1, String value2) {
            addCriterion("fund_id not between", value1, value2, "fundId");
            return (Criteria) this;
        }

        public Criteria andFundFunNameIsNull() {
            addCriterion("fund_fun_name is null");
            return (Criteria) this;
        }

        public Criteria andFundFunNameIsNotNull() {
            addCriterion("fund_fun_name is not null");
            return (Criteria) this;
        }

        public Criteria andFundFunNameEqualTo(String value) {
            addCriterion("fund_fun_name =", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameNotEqualTo(String value) {
            addCriterion("fund_fun_name <>", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameGreaterThan(String value) {
            addCriterion("fund_fun_name >", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameGreaterThanOrEqualTo(String value) {
            addCriterion("fund_fun_name >=", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameLessThan(String value) {
            addCriterion("fund_fun_name <", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameLessThanOrEqualTo(String value) {
            addCriterion("fund_fun_name <=", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameLike(String value) {
            addCriterion("fund_fun_name like", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameNotLike(String value) {
            addCriterion("fund_fun_name not like", value, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameIn(List<String> values) {
            addCriterion("fund_fun_name in", values, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameNotIn(List<String> values) {
            addCriterion("fund_fun_name not in", values, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameBetween(String value1, String value2) {
            addCriterion("fund_fun_name between", value1, value2, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunNameNotBetween(String value1, String value2) {
            addCriterion("fund_fun_name not between", value1, value2, "fundFunName");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdIsNull() {
            addCriterion("fund_fun_catagory_id is null");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdIsNotNull() {
            addCriterion("fund_fun_catagory_id is not null");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdEqualTo(String value) {
            addCriterion("fund_fun_catagory_id =", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdNotEqualTo(String value) {
            addCriterion("fund_fun_catagory_id <>", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdGreaterThan(String value) {
            addCriterion("fund_fun_catagory_id >", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdGreaterThanOrEqualTo(String value) {
            addCriterion("fund_fun_catagory_id >=", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdLessThan(String value) {
            addCriterion("fund_fun_catagory_id <", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdLessThanOrEqualTo(String value) {
            addCriterion("fund_fun_catagory_id <=", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdLike(String value) {
            addCriterion("fund_fun_catagory_id like", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdNotLike(String value) {
            addCriterion("fund_fun_catagory_id not like", value, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdIn(List<String> values) {
            addCriterion("fund_fun_catagory_id in", values, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdNotIn(List<String> values) {
            addCriterion("fund_fun_catagory_id not in", values, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdBetween(String value1, String value2) {
            addCriterion("fund_fun_catagory_id between", value1, value2, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundFunCatagoryIdNotBetween(String value1, String value2) {
            addCriterion("fund_fun_catagory_id not between", value1, value2, "fundFunCatagoryId");
            return (Criteria) this;
        }

        public Criteria andFundStateIsNull() {
            addCriterion("fund_state is null");
            return (Criteria) this;
        }

        public Criteria andFundStateIsNotNull() {
            addCriterion("fund_state is not null");
            return (Criteria) this;
        }

        public Criteria andFundStateEqualTo(String value) {
            addCriterion("fund_state =", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateNotEqualTo(String value) {
            addCriterion("fund_state <>", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateGreaterThan(String value) {
            addCriterion("fund_state >", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateGreaterThanOrEqualTo(String value) {
            addCriterion("fund_state >=", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateLessThan(String value) {
            addCriterion("fund_state <", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateLessThanOrEqualTo(String value) {
            addCriterion("fund_state <=", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateLike(String value) {
            addCriterion("fund_state like", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateNotLike(String value) {
            addCriterion("fund_state not like", value, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateIn(List<String> values) {
            addCriterion("fund_state in", values, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateNotIn(List<String> values) {
            addCriterion("fund_state not in", values, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateBetween(String value1, String value2) {
            addCriterion("fund_state between", value1, value2, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundStateNotBetween(String value1, String value2) {
            addCriterion("fund_state not between", value1, value2, "fundState");
            return (Criteria) this;
        }

        public Criteria andFundFunDescIsNull() {
            addCriterion("fund_fun_desc is null");
            return (Criteria) this;
        }

        public Criteria andFundFunDescIsNotNull() {
            addCriterion("fund_fun_desc is not null");
            return (Criteria) this;
        }

        public Criteria andFundFunDescEqualTo(String value) {
            addCriterion("fund_fun_desc =", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescNotEqualTo(String value) {
            addCriterion("fund_fun_desc <>", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescGreaterThan(String value) {
            addCriterion("fund_fun_desc >", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescGreaterThanOrEqualTo(String value) {
            addCriterion("fund_fun_desc >=", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescLessThan(String value) {
            addCriterion("fund_fun_desc <", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescLessThanOrEqualTo(String value) {
            addCriterion("fund_fun_desc <=", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescLike(String value) {
            addCriterion("fund_fun_desc like", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescNotLike(String value) {
            addCriterion("fund_fun_desc not like", value, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescIn(List<String> values) {
            addCriterion("fund_fun_desc in", values, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescNotIn(List<String> values) {
            addCriterion("fund_fun_desc not in", values, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescBetween(String value1, String value2) {
            addCriterion("fund_fun_desc between", value1, value2, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundFunDescNotBetween(String value1, String value2) {
            addCriterion("fund_fun_desc not between", value1, value2, "fundFunDesc");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksIsNull() {
            addCriterion("fund_modify_remarks is null");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksIsNotNull() {
            addCriterion("fund_modify_remarks is not null");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksEqualTo(String value) {
            addCriterion("fund_modify_remarks =", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksNotEqualTo(String value) {
            addCriterion("fund_modify_remarks <>", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksGreaterThan(String value) {
            addCriterion("fund_modify_remarks >", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksGreaterThanOrEqualTo(String value) {
            addCriterion("fund_modify_remarks >=", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksLessThan(String value) {
            addCriterion("fund_modify_remarks <", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksLessThanOrEqualTo(String value) {
            addCriterion("fund_modify_remarks <=", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksLike(String value) {
            addCriterion("fund_modify_remarks like", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksNotLike(String value) {
            addCriterion("fund_modify_remarks not like", value, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksIn(List<String> values) {
            addCriterion("fund_modify_remarks in", values, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksNotIn(List<String> values) {
            addCriterion("fund_modify_remarks not in", values, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksBetween(String value1, String value2) {
            addCriterion("fund_modify_remarks between", value1, value2, "fundModifyRemarks");
            return (Criteria) this;
        }

        public Criteria andFundModifyRemarksNotBetween(String value1, String value2) {
            addCriterion("fund_modify_remarks not between", value1, value2, "fundModifyRemarks");
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
            addCriterion("a.isenable =", value, "isenable");
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