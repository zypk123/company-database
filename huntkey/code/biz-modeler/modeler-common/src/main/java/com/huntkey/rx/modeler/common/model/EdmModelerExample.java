package com.huntkey.rx.modeler.common.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EdmModelerExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public EdmModelerExample() {
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

        public Criteria andEdmdParentIdIsNull() {
            addCriterion("edmd_parent_id is null");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdIsNotNull() {
            addCriterion("edmd_parent_id is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdEqualTo(String value) {
            addCriterion("edmd_parent_id =", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdNotEqualTo(String value) {
            addCriterion("edmd_parent_id <>", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdGreaterThan(String value) {
            addCriterion("edmd_parent_id >", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdGreaterThanOrEqualTo(String value) {
            addCriterion("edmd_parent_id >=", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdLessThan(String value) {
            addCriterion("edmd_parent_id <", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdLessThanOrEqualTo(String value) {
            addCriterion("edmd_parent_id <=", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdLike(String value) {
            addCriterion("edmd_parent_id like", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdNotLike(String value) {
            addCriterion("edmd_parent_id not like", value, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdIn(List<String> values) {
            addCriterion("edmd_parent_id in", values, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdNotIn(List<String> values) {
            addCriterion("edmd_parent_id not in", values, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdBetween(String value1, String value2) {
            addCriterion("edmd_parent_id between", value1, value2, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdParentIdNotBetween(String value1, String value2) {
            addCriterion("edmd_parent_id not between", value1, value2, "edmdParentId");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeIsNull() {
            addCriterion("edmd_code is null");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeIsNotNull() {
            addCriterion("edmd_code is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeEqualTo(String value) {
            addCriterion("edmd_code =", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeNotEqualTo(String value) {
            addCriterion("edmd_code <>", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeGreaterThan(String value) {
            addCriterion("edmd_code >", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeGreaterThanOrEqualTo(String value) {
            addCriterion("edmd_code >=", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeLessThan(String value) {
            addCriterion("edmd_code <", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeLessThanOrEqualTo(String value) {
            addCriterion("edmd_code <=", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeLike(String value) {
            addCriterion("edmd_code like", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeNotLike(String value) {
            addCriterion("edmd_code not like", value, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeIn(List<String> values) {
            addCriterion("edmd_code in", values, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeNotIn(List<String> values) {
            addCriterion("edmd_code not in", values, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeBetween(String value1, String value2) {
            addCriterion("edmd_code between", value1, value2, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdCodeNotBetween(String value1, String value2) {
            addCriterion("edmd_code not between", value1, value2, "edmdCode");
            return (Criteria) this;
        }

        public Criteria andEdmdVerIsNull() {
            addCriterion("edmd_ver is null");
            return (Criteria) this;
        }

        public Criteria andEdmdVerIsNotNull() {
            addCriterion("edmd_ver is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdVerEqualTo(String value) {
            addCriterion("edmd_ver =", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerNotEqualTo(String value) {
            addCriterion("edmd_ver <>", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerGreaterThan(String value) {
            addCriterion("edmd_ver >", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerGreaterThanOrEqualTo(String value) {
            addCriterion("edmd_ver >=", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerLessThan(String value) {
            addCriterion("edmd_ver <", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerLessThanOrEqualTo(String value) {
            addCriterion("edmd_ver <=", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerLike(String value) {
            addCriterion("edmd_ver like", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerNotLike(String value) {
            addCriterion("edmd_ver not like", value, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerIn(List<String> values) {
            addCriterion("edmd_ver in", values, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerNotIn(List<String> values) {
            addCriterion("edmd_ver not in", values, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerBetween(String value1, String value2) {
            addCriterion("edmd_ver between", value1, value2, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdVerNotBetween(String value1, String value2) {
            addCriterion("edmd_ver not between", value1, value2, "edmdVer");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescIsNull() {
            addCriterion("edmd_update_desc is null");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescIsNotNull() {
            addCriterion("edmd_update_desc is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescEqualTo(String value) {
            addCriterion("edmd_update_desc =", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescNotEqualTo(String value) {
            addCriterion("edmd_update_desc <>", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescGreaterThan(String value) {
            addCriterion("edmd_update_desc >", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescGreaterThanOrEqualTo(String value) {
            addCriterion("edmd_update_desc >=", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescLessThan(String value) {
            addCriterion("edmd_update_desc <", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescLessThanOrEqualTo(String value) {
            addCriterion("edmd_update_desc <=", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescLike(String value) {
            addCriterion("edmd_update_desc like", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescNotLike(String value) {
            addCriterion("edmd_update_desc not like", value, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescIn(List<String> values) {
            addCriterion("edmd_update_desc in", values, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescNotIn(List<String> values) {
            addCriterion("edmd_update_desc not in", values, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescBetween(String value1, String value2) {
            addCriterion("edmd_update_desc between", value1, value2, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdUpdateDescNotBetween(String value1, String value2) {
            addCriterion("edmd_update_desc not between", value1, value2, "edmdUpdateDesc");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusIsNull() {
            addCriterion("edmd_status is null");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusIsNotNull() {
            addCriterion("edmd_status is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusEqualTo(Byte value) {
            addCriterion("edmd_status =", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusNotEqualTo(Byte value) {
            addCriterion("edmd_status <>", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusGreaterThan(Byte value) {
            addCriterion("edmd_status >", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusGreaterThanOrEqualTo(Byte value) {
            addCriterion("edmd_status >=", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusLessThan(Byte value) {
            addCriterion("edmd_status <", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusLessThanOrEqualTo(Byte value) {
            addCriterion("edmd_status <=", value, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusIn(List<Byte> values) {
            addCriterion("edmd_status in", values, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusNotIn(List<Byte> values) {
            addCriterion("edmd_status not in", values, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusBetween(Byte value1, Byte value2) {
            addCriterion("edmd_status between", value1, value2, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdStatusNotBetween(Byte value1, Byte value2) {
            addCriterion("edmd_status not between", value1, value2, "edmdStatus");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeIsNull() {
            addCriterion("edmd_release_time is null");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeIsNotNull() {
            addCriterion("edmd_release_time is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeEqualTo(Date value) {
            addCriterion("edmd_release_time =", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeNotEqualTo(Date value) {
            addCriterion("edmd_release_time <>", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeGreaterThan(Date value) {
            addCriterion("edmd_release_time >", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("edmd_release_time >=", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeLessThan(Date value) {
            addCriterion("edmd_release_time <", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeLessThanOrEqualTo(Date value) {
            addCriterion("edmd_release_time <=", value, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeIn(List<Date> values) {
            addCriterion("edmd_release_time in", values, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeNotIn(List<Date> values) {
            addCriterion("edmd_release_time not in", values, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeBetween(Date value1, Date value2) {
            addCriterion("edmd_release_time between", value1, value2, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdReleaseTimeNotBetween(Date value1, Date value2) {
            addCriterion("edmd_release_time not between", value1, value2, "edmdReleaseTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeIsNull() {
            addCriterion("edmd_expire_time is null");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeIsNotNull() {
            addCriterion("edmd_expire_time is not null");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeEqualTo(Date value) {
            addCriterion("edmd_expire_time =", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeNotEqualTo(Date value) {
            addCriterion("edmd_expire_time <>", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeGreaterThan(Date value) {
            addCriterion("edmd_expire_time >", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("edmd_expire_time >=", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeLessThan(Date value) {
            addCriterion("edmd_expire_time <", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeLessThanOrEqualTo(Date value) {
            addCriterion("edmd_expire_time <=", value, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeIn(List<Date> values) {
            addCriterion("edmd_expire_time in", values, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeNotIn(List<Date> values) {
            addCriterion("edmd_expire_time not in", values, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeBetween(Date value1, Date value2) {
            addCriterion("edmd_expire_time between", value1, value2, "edmdExpireTime");
            return (Criteria) this;
        }

        public Criteria andEdmdExpireTimeNotBetween(Date value1, Date value2) {
            addCriterion("edmd_expire_time not between", value1, value2, "edmdExpireTime");
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