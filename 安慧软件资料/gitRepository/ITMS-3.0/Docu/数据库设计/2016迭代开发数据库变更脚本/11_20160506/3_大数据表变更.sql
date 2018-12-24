------------------------------------------
-- Export file for user ITMS3           --
-- Created by STJ on 2016/5/6, 14:43:45 --
------------------------------------------

set define off
spool 11_3_大数据表变更.log


DROP TABLE T_TRUK_ACCOMPANY;
DROP TABLE T_TRUK_VIOLATION;


DROP TABLE T_VEHTRACK_CLONE_VEHICLE;
prompt
prompt Creating table T_VEHTRACK_CLONE_VEHICLE
prompt =======================================
prompt
create table T_VEHTRACK_CLONE_VEHICLE
(
  clone_vehicle_id       VARCHAR2(32) not null,
  plate_nbr              VARCHAR2(16) not null,
  plate_color            VARCHAR2(2) not null,
  vehicle_brand          VARCHAR2(32),
  vehicle_color          VARCHAR2(5),
  register_vehicle_brand VARCHAR2(64),
  register_vehicle_color VARCHAR2(32),
  device_nbr1            VARCHAR2(18) not null,
  snap_nbr1              VARCHAR2(32) not null,
  plate_color1           VARCHAR2(2),
  site_code1             VARCHAR2(16) not null,
  pass_time1             DATE not null,
  device_nbr2            VARCHAR2(18) not null,
  snap_nbr2              VARCHAR2(32) not null,
  plate_color2           VARCHAR2(2),
  pass_time2             DATE not null,
  site_code2             VARCHAR2(16) not null,
  between_distance       FLOAT,
  count_form             CHAR(1) not null,
  confirm_reason         VARCHAR2(256),
  clone_flag             CHAR(1),
  confirm_desc           VARCHAR2(256),
  confirmor              VARCHAR2(32),
  confirm_time           DATE,
  create_time            DATE default SYSDATE,
  vehicle_type           VARCHAR2(3),
  register_vehicle_type  VARCHAR2(64),
  register_plate_color   VARCHAR2(32),
  vehicle_shape          VARCHAR2(2),
  image_url1             VARCHAR2(128),
  image_url2             VARCHAR2(128),
  plate_type             VARCHAR2(2),
  org_authority_code     VARCHAR2(32)
)
;
comment on table T_VEHTRACK_CLONE_VEHICLE
  is '套牌车分析记录表';
comment on column T_VEHTRACK_CLONE_VEHICLE.clone_vehicle_id
  is '套牌车分析ID';
comment on column T_VEHTRACK_CLONE_VEHICLE.plate_nbr
  is '号牌号码';
comment on column T_VEHTRACK_CLONE_VEHICLE.plate_color
  is '号牌颜色(003)：由前端设备识别';
comment on column T_VEHTRACK_CLONE_VEHICLE.vehicle_brand
  is '车辆品牌：由前端设备识别';
comment on column T_VEHTRACK_CLONE_VEHICLE.vehicle_color
  is '车身颜色(006)：由前端设备识别';
comment on column T_VEHTRACK_CLONE_VEHICLE.register_vehicle_brand
  is '车辆登记品牌';
comment on column T_VEHTRACK_CLONE_VEHICLE.register_vehicle_color
  is '车辆登记车身颜色';
comment on column T_VEHTRACK_CLONE_VEHICLE.device_nbr1
  is '设备编号1';
comment on column T_VEHTRACK_CLONE_VEHICLE.snap_nbr1
  is '抓拍编号1';
comment on column T_VEHTRACK_CLONE_VEHICLE.plate_color1
  is '号牌颜色1';
comment on column T_VEHTRACK_CLONE_VEHICLE.site_code1
  is '过车点位1';
comment on column T_VEHTRACK_CLONE_VEHICLE.pass_time1
  is '过车时间1';
comment on column T_VEHTRACK_CLONE_VEHICLE.device_nbr2
  is '设备编号2';
comment on column T_VEHTRACK_CLONE_VEHICLE.snap_nbr2
  is '抓拍编号2';
comment on column T_VEHTRACK_CLONE_VEHICLE.plate_color2
  is '号牌颜色2';
comment on column T_VEHTRACK_CLONE_VEHICLE.pass_time2
  is '过车时间2';
comment on column T_VEHTRACK_CLONE_VEHICLE.site_code2
  is '过车点位2';
comment on column T_VEHTRACK_CLONE_VEHICLE.between_distance
  is '两点距离';
comment on column T_VEHTRACK_CLONE_VEHICLE.count_form
  is '套牌车认定方式。1-车辆信息与本系统车辆登记信息不符；2-车辆信息与车驾管信息不符；3-两过车记录中车辆信息不相同；4-直线距离；5-点位实际距离';
comment on column T_VEHTRACK_CLONE_VEHICLE.confirm_reason
  is '认定理由';
comment on column T_VEHTRACK_CLONE_VEHICLE.clone_flag
  is '确认标记。0-未确认；1-已确认；2-证据不足；3-识别错误';
comment on column T_VEHTRACK_CLONE_VEHICLE.confirm_desc
  is '确认描述';
comment on column T_VEHTRACK_CLONE_VEHICLE.confirmor
  is '确认人';
comment on column T_VEHTRACK_CLONE_VEHICLE.confirm_time
  is '确认时间';
comment on column T_VEHTRACK_CLONE_VEHICLE.create_time
  is '创建时间';
comment on column T_VEHTRACK_CLONE_VEHICLE.vehicle_type
  is '车辆类型';
comment on column T_VEHTRACK_CLONE_VEHICLE.register_vehicle_type
  is '车辆登记车辆类型';
comment on column T_VEHTRACK_CLONE_VEHICLE.register_plate_color
  is '车辆登记号牌颜色';
comment on column T_VEHTRACK_CLONE_VEHICLE.vehicle_shape
  is '车辆外形';
comment on column T_VEHTRACK_CLONE_VEHICLE.image_url1
  is '过车图片1';
comment on column T_VEHTRACK_CLONE_VEHICLE.image_url2
  is '过车图片2';
comment on column T_VEHTRACK_CLONE_VEHICLE.plate_type
  is '号牌类型';
comment on column T_VEHTRACK_CLONE_VEHICLE.org_authority_code
  is '管理机构权限代码';
alter table T_VEHTRACK_CLONE_VEHICLE
  add constraint PK_T_VEHTRACK_CLONE_VEHICLE primary key (CLONE_VEHICLE_ID);


DROP TABLE T_VEHTRACK_FAKE_CLONE;
prompt
prompt Creating table T_VEHTRACK_FAKE_CLONE
prompt ====================================
prompt
create table T_VEHTRACK_FAKE_CLONE
(
  fake_clone_vehicle_id VARCHAR2(32) not null,
  plate_nbr             VARCHAR2(16) not null,
  plate_color           VARCHAR2(2) not null,
  plate_type            VARCHAR2(2),
  vehicle_brand         VARCHAR2(32),
  vehicle_type          VARCHAR2(3),
  veh_charcter          VARCHAR2(2),
  vehicle_color         VARCHAR2(5),
  fake_or_clone         CHAR(1) not null,
  track_flag            CHAR(1),
  confirm_desc          VARCHAR2(256),
  confirmor             VARCHAR2(32),
  confirm_time          DATE,
  create_time           DATE default SYSDATE,
  is_cancelled          CHAR(1),
  vehicle_shape         VARCHAR2(2),
  org_authority_code    VARCHAR2(32)
)
;
comment on table T_VEHTRACK_FAKE_CLONE
  is '已经确认的假牌、套牌车辆表';
comment on column T_VEHTRACK_FAKE_CLONE.fake_clone_vehicle_id
  is '车辆信息ID';
comment on column T_VEHTRACK_FAKE_CLONE.plate_nbr
  is '号牌号码';
comment on column T_VEHTRACK_FAKE_CLONE.plate_color
  is '号牌颜色(003)';
comment on column T_VEHTRACK_FAKE_CLONE.plate_type
  is '号牌类型(002)';
comment on column T_VEHTRACK_FAKE_CLONE.vehicle_brand
  is '车辆品牌';
comment on column T_VEHTRACK_FAKE_CLONE.vehicle_type
  is '车辆类型。参考国家机动车类型代码表：GA802、GA24.4。只能识别出机动车大类的记1位大类代码（H、K、M等），只能识别出大类和规格的记2位；全识别的记3位；只能识别出机动车规格的记2位数字，第一位用0补齐（1：大车；2：中型车；3：小型车；4：微型车）；摩托车为M1或M2';
comment on column T_VEHTRACK_FAKE_CLONE.veh_charcter
  is '车辆使用性质。GA802-2008。参照稽查布控系统编码：
0：未分析（系统补充）
G	租赁
H	警用
I	消防
J	救护
K	工程救险
L	营转非
M	出租转非
N	教练
O	幼儿校车
P	小学生校车
Q	初中生校车
S	中小学生校车
R	危化品运输
A	非营运
B	公路客运
C	公交客运
D	出租客运
E	旅游客运
F	货运';
comment on column T_VEHTRACK_FAKE_CLONE.vehicle_color
  is '车身颜色(006)';
comment on column T_VEHTRACK_FAKE_CLONE.fake_or_clone
  is '假牌或套牌。1：假牌；2：套牌。';
comment on column T_VEHTRACK_FAKE_CLONE.track_flag
  is '布控标记';
comment on column T_VEHTRACK_FAKE_CLONE.confirm_desc
  is '确认描述';
comment on column T_VEHTRACK_FAKE_CLONE.confirmor
  is '确认人';
comment on column T_VEHTRACK_FAKE_CLONE.confirm_time
  is '确认时间';
comment on column T_VEHTRACK_FAKE_CLONE.create_time
  is '创建时间';
comment on column T_VEHTRACK_FAKE_CLONE.is_cancelled
  is '是否撤销。有些假牌套牌车被处理后，在车管所登记，成为正常的车，假牌套牌记录需要有标记。
0 不撤销
1 撤销';
comment on column T_VEHTRACK_FAKE_CLONE.vehicle_shape
  is '车辆外形';
comment on column T_VEHTRACK_FAKE_CLONE.org_authority_code
  is '管理机构权限代码';
alter table T_VEHTRACK_FAKE_CLONE
  add constraint PK_T_VEHTRACK_FAKE_CLONE primary key (FAKE_CLONE_VEHICLE_ID);


DROP TABLE T_VEHTRACK_FAKE_VEHICLE;
prompt
prompt Creating table T_VEHTRACK_FAKE_VEHICLE
prompt ======================================
prompt
create table T_VEHTRACK_FAKE_VEHICLE
(
  fake_vehicle_id    VARCHAR2(32) not null,
  plate_nbr          VARCHAR2(16) not null,
  plate_color        VARCHAR2(2) not null,
  vehicle_brand      VARCHAR2(32),
  vehicle_color      VARCHAR2(5),
  fake_flag          CHAR(1),
  confirm_reason     VARCHAR2(256),
  confirm_desc       VARCHAR2(256),
  confirmor          VARCHAR2(32),
  confirm_time       DATE,
  create_time        DATE default SYSDATE,
  device_nbr         VARCHAR2(18),
  snap_nbr           VARCHAR2(32),
  vehicle_type       VARCHAR2(16),
  vehicle_shape      VARCHAR2(2),
  plate_type         VARCHAR2(2),
  org_authority_code VARCHAR2(32)
)
;
comment on table T_VEHTRACK_FAKE_VEHICLE
  is '假牌车分析记录表。来源：1、违法整理录入时候人工判别的假牌车辆；2、警务站在线比对的假牌车。';
comment on column T_VEHTRACK_FAKE_VEHICLE.fake_vehicle_id
  is '假牌车分析ID';
comment on column T_VEHTRACK_FAKE_VEHICLE.plate_nbr
  is '号牌号码';
comment on column T_VEHTRACK_FAKE_VEHICLE.plate_color
  is '号牌颜色(003)';
comment on column T_VEHTRACK_FAKE_VEHICLE.vehicle_brand
  is '车辆品牌';
comment on column T_VEHTRACK_FAKE_VEHICLE.vehicle_color
  is '车身颜色(006)';
comment on column T_VEHTRACK_FAKE_VEHICLE.fake_flag
  is '假牌标识';
comment on column T_VEHTRACK_FAKE_VEHICLE.confirm_reason
  is '认定理由';
comment on column T_VEHTRACK_FAKE_VEHICLE.confirm_desc
  is '确认描述';
comment on column T_VEHTRACK_FAKE_VEHICLE.confirmor
  is '确认人';
comment on column T_VEHTRACK_FAKE_VEHICLE.confirm_time
  is '确认时间';
comment on column T_VEHTRACK_FAKE_VEHICLE.create_time
  is '创建时间';
comment on column T_VEHTRACK_FAKE_VEHICLE.device_nbr
  is '设备编号';
comment on column T_VEHTRACK_FAKE_VEHICLE.snap_nbr
  is '抓拍编号';
comment on column T_VEHTRACK_FAKE_VEHICLE.vehicle_type
  is '车辆类型';
comment on column T_VEHTRACK_FAKE_VEHICLE.vehicle_shape
  is '车辆外形';
comment on column T_VEHTRACK_FAKE_VEHICLE.plate_type
  is '号牌类型';
comment on column T_VEHTRACK_FAKE_VEHICLE.org_authority_code
  is '管理机构权限代码';
alter table T_VEHTRACK_FAKE_VEHICLE
  add constraint PK_T_VEHTRACK_FAKE_VEHICLE primary key (FAKE_VEHICLE_ID);


spool off

exit;
