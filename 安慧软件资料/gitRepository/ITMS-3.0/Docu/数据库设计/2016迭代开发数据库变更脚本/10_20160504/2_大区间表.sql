set define off
spool 10_2_大区间表.log

--创建大区间表
--2016-05-04
create table T_VIO_BIG_REGION 
(
   REGIONAL_ID          VARCHAR2(32)         not null,
   REGIONAL_CODE        VARCHAR2(32)         not null,
   REGIONAL_NAME        VARCHAR2(64)         not null,
   DISTRICT_CODE        VARCHAR2(256)        not null,
   ORG_ID               VARCHAR2(32),
   ORG_PRIVILEGE_CODE   VARCHAR2(8)          not null,
   ROAD_ID              VARCHAR2(6)          not null,
   ENTRY_SITE_ID        VARCHAR2(32)         not null,
   EXIT_SITE_ID         VARCHAR2(32)         not null,
   DISTANCE             NUMBER(10,2)         not null,
   DIRECTION_TYPE       CHAR(1)              not null,
   DIRECTION_NAME       VARCHAR2(32),
   LANE_NUM             VARCHAR2(2)          not null,
   REGIONAL_DESIGN_CAPACITY VARCHAR2(32),
   LIMIT_LARGE          NUMBER(3),
   LIMIT_SMALL          NUMBER(3),
   LIMIT_OTHERS         number(10),
   LIMIT_LOWER          NUMBER(3),
   LIMIT_LARGE_MARGIN   NUMBER(5),
   LIMIT_SMALL_MARGIN   NUMBER(5),
   RELATED_VARIABLE_SPEED VARCHAR2(256),
   RELATED_LED          VARCHAR2(256),
   ENABLE_FLAG          CHAR(1),
   HISTORY_MAX_VEHICLE_NUM VARCHAR2(32),
   HISTORY_MAX_FLOW_TIME DATE,
   CREATE_BY            VARCHAR2(32),
   CREATE_TIME          DATE                 default SYSDATE,
   UPDATE_BY            VARCHAR2(32),
   REMARK               VARCHAR2(256),
   constraint PK_T_VIO_BIG_REGION primary key (REGIONAL_ID)
);

comment on table T_VIO_BIG_REGION is
'大区间表。';

comment on column T_VIO_BIG_REGION.REGIONAL_ID is
'区间ID';

comment on column T_VIO_BIG_REGION.REGIONAL_CODE is
'区间编号';

comment on column T_VIO_BIG_REGION.REGIONAL_NAME is
'区间名称';

comment on column T_VIO_BIG_REGION.DISTRICT_CODE is
'行政区划列表(007)';

comment on column T_VIO_BIG_REGION.ORG_ID is
'机构ID。以终点所属机构为主，用户也可以自己选择所属机构。';

comment on column T_VIO_BIG_REGION.ORG_PRIVILEGE_CODE is
'机构权限过滤代码';

comment on column T_VIO_BIG_REGION.ROAD_ID is
'道路ID';

comment on column T_VIO_BIG_REGION.ENTRY_SITE_ID is
'起点ID';

comment on column T_VIO_BIG_REGION.EXIT_SITE_ID is
'终点ID';

comment on column T_VIO_BIG_REGION.DISTANCE is
'区间距离。单位：公里。';

comment on column T_VIO_BIG_REGION.DIRECTION_TYPE is
'方向类型（070）。1：上行；2：下行。';

comment on column T_VIO_BIG_REGION.DIRECTION_NAME is
'方向名称';

comment on column T_VIO_BIG_REGION.LANE_NUM is
'车道数';

comment on column T_VIO_BIG_REGION.REGIONAL_DESIGN_CAPACITY is
'区间设计容量。根据区间距离和车道数计算得出。';

comment on column T_VIO_BIG_REGION.LIMIT_LARGE is
'大车限高速';

comment on column T_VIO_BIG_REGION.LIMIT_SMALL is
'小车限高速';

comment on column T_VIO_BIG_REGION.LIMIT_OTHERS is
'其它车限高速';

comment on column T_VIO_BIG_REGION.LIMIT_LOWER is
'限低速';

comment on column T_VIO_BIG_REGION.LIMIT_LARGE_MARGIN is
'大车限速容许值';

comment on column T_VIO_BIG_REGION.LIMIT_SMALL_MARGIN is
'小车限速容许值';

comment on column T_VIO_BIG_REGION.RELATED_VARIABLE_SPEED is
'关联可变限速牌';

comment on column T_VIO_BIG_REGION.RELATED_LED is
'关联LED诱导屏';

comment on column T_VIO_BIG_REGION.ENABLE_FLAG is
'启用标识';

comment on column T_VIO_BIG_REGION.HISTORY_MAX_VEHICLE_NUM is
'区间历史高峰车辆数';

comment on column T_VIO_BIG_REGION.HISTORY_MAX_FLOW_TIME is
'高峰时间';

comment on column T_VIO_BIG_REGION.CREATE_BY is
'创建人';

comment on column T_VIO_BIG_REGION.CREATE_TIME is
'创建时间';

comment on column T_VIO_BIG_REGION.UPDATE_BY is
'更新人员';

comment on column T_VIO_BIG_REGION.REMARK is
'备注';

--修改区间流量表
alter table T_TRAFFIC_REGION_FLOW ADD REGION_SATURATION VARCHAR2(12) ;
comment on column T_TRAFFIC_REGION_FLOW.REGION_SATURATION is '区间饱和度';

alter table T_TRAFFIC_REGION_FLOW ADD REGION_DENSITY VARCHAR2(12) ;
comment on column T_TRAFFIC_REGION_FLOW.REGION_DENSITY is '区间车流密度。单位：辆车/公里';



spool off

exit;