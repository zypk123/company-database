package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmConnectExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmConnectExample() {
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

        public Criteria andEdcnEdmpIdIsNull() {
            addCriterion("edcn_edmp_id is null");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdIsNotNull() {
            addCriterion("edcn_edmp_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdEqualTo(String value) {
            addCriterion("edcn_edmp_id =", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdNotEqualTo(String value) {
            addCriterion("edcn_edmp_id <>", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdGreaterThan(String value) {
            addCriterion("edcn_edmp_id >", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdGreaterThanOrEqualTo(String value) {
            addCriterion("edcn_edmp_id >=", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdLessThan(String value) {
            addCriterion("edcn_edmp_id <", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdLessThanOrEqualTo(String value) {
            addCriterion("edcn_edmp_id <=", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdLike(String value) {
            addCriterion("edcn_edmp_id like", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdNotLike(String value) {
            addCriterion("edcn_edmp_id not like", value, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdIn(List<String> values) {
            addCriterion("edcn_edmp_id in", values, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdNotIn(List<String> values) {
            addCriterion("edcn_edmp_id not in", values, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdBetween(String value1, String value2) {
            addCriterion("edcn_edmp_id between", value1, value2, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnEdmpIdNotBetween(String value1, String value2) {
            addCriterion("edcn_edmp_id not between", value1, value2, "edcnEdmpId");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableIsNull() {
            addCriterion("edcn_link_preservable is null");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableIsNotNull() {
            addCriterion("edcn_link_preservable is not null");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableEqualTo(Byte value) {
            addCriterion("edcn_link_preservable =", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableNotEqualTo(Byte value) {
            addCriterion("edcn_link_preservable <>", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableGreaterThan(Byte value) {
            addCriterion("edcn_link_preservable >", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableGreaterThanOrEqualTo(Byte value) {
            addCriterion("edcn_link_preservable >=", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableLessThan(Byte value) {
            addCriterion("edcn_link_preservable <", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableLessThanOrEqualTo(Byte value) {
            addCriterion("edcn_link_preservable <=", value, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableIn(List<Byte> values) {
            addCriterion("edcn_link_preservable in", values, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableNotIn(List<Byte> values) {
            addCriterion("edcn_link_preservable not in", values, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableBetween(Byte value1, Byte value2) {
            addCriterion("edcn_link_preservable between", value1, value2, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnLinkPreservableNotBetween(Byte value1, Byte value2) {
            addCriterion("edcn_link_preservable not between", value1, value2, "edcnLinkPreservable");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeIsNull() {
            addCriterion("edcn_update_type is null");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeIsNotNull() {
            addCriterion("edcn_update_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeEqualTo(String value) {
            addCriterion("edcn_update_type =", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeNotEqualTo(String value) {
            addCriterion("edcn_update_type <>", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeGreaterThan(String value) {
            addCriterion("edcn_update_type >", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edcn_update_type >=", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeLessThan(String value) {
            addCriterion("edcn_update_type <", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeLessThanOrEqualTo(String value) {
            addCriterion("edcn_update_type <=", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeLike(String value) {
            addCriterion("edcn_update_type like", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeNotLike(String value) {
            addCriterion("edcn_update_type not like", value, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeIn(List<String> values) {
            addCriterion("edcn_update_type in", values, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeNotIn(List<String> values) {
            addCriterion("edcn_update_type not in", values, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeBetween(String value1, String value2) {
            addCriterion("edcn_update_type between", value1, value2, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTypeNotBetween(String value1, String value2) {
            addCriterion("edcn_update_type not between", value1, value2, "edcnUpdateType");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeIsNull() {
            addCriterion("edcn_update_time is null");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeIsNotNull() {
            addCriterion("edcn_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeEqualTo(String value) {
            addCriterion("edcn_update_time =", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeNotEqualTo(String value) {
            addCriterion("edcn_update_time <>", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeGreaterThan(String value) {
            addCriterion("edcn_update_time >", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeGreaterThanOrEqualTo(String value) {
            addCriterion("edcn_update_time >=", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeLessThan(String value) {
            addCriterion("edcn_update_time <", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeLessThanOrEqualTo(String value) {
            addCriterion("edcn_update_time <=", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeLike(String value) {
            addCriterion("edcn_update_time like", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeNotLike(String value) {
            addCriterion("edcn_update_time not like", value, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeIn(List<String> values) {
            addCriterion("edcn_update_time in", values, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeNotIn(List<String> values) {
            addCriterion("edcn_update_time not in", values, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeBetween(String value1, String value2) {
            addCriterion("edcn_update_time between", value1, value2, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnUpdateTimeNotBetween(String value1, String value2) {
            addCriterion("edcn_update_time not between", value1, value2, "edcnUpdateTime");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeIsNull() {
            addCriterion("edcn_type is null");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeIsNotNull() {
            addCriterion("edcn_type is not null");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeEqualTo(String value) {
            addCriterion("edcn_type =", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeNotEqualTo(String value) {
            addCriterion("edcn_type <>", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeGreaterThan(String value) {
            addCriterion("edcn_type >", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeGreaterThanOrEqualTo(String value) {
            addCriterion("edcn_type >=", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeLessThan(String value) {
            addCriterion("edcn_type <", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeLessThanOrEqualTo(String value) {
            addCriterion("edcn_type <=", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeLike(String value) {
            addCriterion("edcn_type like", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeNotLike(String value) {
            addCriterion("edcn_type not like", value, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeIn(List<String> values) {
            addCriterion("edcn_type in", values, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeNotIn(List<String> values) {
            addCriterion("edcn_type not in", values, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeBetween(String value1, String value2) {
            addCriterion("edcn_type between", value1, value2, "edcnType");
            return (Criteria) this;
        }

        public Criteria andEdcnTypeNotBetween(String value1, String value2) {
            addCriterion("edcn_type not between", value1, value2, "edcnType");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityIsNull() {
            addCriterion("async_type_priority is null");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityIsNotNull() {
            addCriterion("async_type_priority is not null");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityEqualTo(String value) {
            addCriterion("async_type_priority =", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityNotEqualTo(String value) {
            addCriterion("async_type_priority <>", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityGreaterThan(String value) {
            addCriterion("async_type_priority >", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityGreaterThanOrEqualTo(String value) {
            addCriterion("async_type_priority >=", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityLessThan(String value) {
            addCriterion("async_type_priority <", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityLessThanOrEqualTo(String value) {
            addCriterion("async_type_priority <=", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityLike(String value) {
            addCriterion("async_type_priority like", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityNotLike(String value) {
            addCriterion("async_type_priority not like", value, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityIn(List<String> values) {
            addCriterion("async_type_priority in", values, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityNotIn(List<String> values) {
            addCriterion("async_type_priority not in", values, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityBetween(String value1, String value2) {
            addCriterion("async_type_priority between", value1, value2, "asyncTypePriority");
            return (Criteria) this;
        }

        public Criteria andAsyncTypePriorityNotBetween(String value1, String value2) {
            addCriterion("async_type_priority not between", value1, value2, "asyncTypePriority");
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