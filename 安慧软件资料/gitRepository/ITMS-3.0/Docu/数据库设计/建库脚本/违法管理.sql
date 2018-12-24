/*==============================================================*/
/* DBMS name:      ORACLE Version 11g                           */
/* Created on:     2015/6/15 15:46:24                           */
/*==============================================================*/


alter table T_VIO_LOCAL_PUNISH
   drop constraint FK_T_VIO_LO_RELATIONS_T_VIO_VI;

drop table T_VIO_ASSO_ACTION cascade constraints;

drop table T_VIO_EVIDENCE cascade constraints;

drop table T_VIO_FAKE_CLONE cascade constraints;

drop index "Relationship_2_FK";

drop table T_VIO_LOCAL_PUNISH cascade constraints;

drop index "Relationship_17_FK";

drop table T_VIO_OPERATION_LOG cascade constraints;

drop table T_VIO_UPLOAD_LOG cascade constraints;

drop table T_VIO_VIOLATION cascade constraints;

/*==============================================================*/
/* Table: T_VIO_ASSO_ACTION                                     */
/*==============================================================*/
create table T_VIO_ASSO_ACTION 
(
   VIO_ACTION_MATCH_ID  VARCHAR2(32)         not null,
   ROAD_TYPE            VARCHAR2(2)          default '0',
   VIOLATION_TYPE       CHAR(1),
   VIOLATION_DESC       VARCHAR2(128),
   VIOLATION_CODE       VARCHAR2(8),
   LIMIT_SPEED          numb(5),
   MAX_RATIO            number(5),
   MIN_RATIO            number(5),
   constraint PK_T_VIO_ASSO_ACTION primary key (VIO_ACTION_MATCH_ID)
);

comment on table T_VIO_ASSO_ACTION is
'违法代码匹配关系表';

comment on column T_VIO_ASSO_ACTION.VIO_ACTION_MATCH_ID is
'主键Id';

comment on column T_VIO_ASSO_ACTION.VIOLATION_TYPE is
'违法类型(030)：
0：大车占道
1：超高速
2：超低速
3：逆行
4：闯红灯
5：压黄线
6：违章停车
7：区间超速
8：禁行
9：其他
a：违法占道
b：遮挡号牌
c：不按导向行驶
d：压线
e：未系安全带
';

comment on column T_VIO_ASSO_ACTION.VIOLATION_DESC is
'违法描述';

comment on column T_VIO_ASSO_ACTION.VIOLATION_CODE is
'违法代码';

comment on column T_VIO_ASSO_ACTION.LIMIT_SPEED is
'限速值';

comment on column T_VIO_ASSO_ACTION.MAX_RATIO is
'最大超速比';

comment on column T_VIO_ASSO_ACTION.MIN_RATIO is
'最小超速比';

/*==============================================================*/
/* Table: T_VIO_EVIDENCE                                        */
/*==============================================================*/
create table T_VIO_EVIDENCE 
(
   VIO_EVIDENCE_ID      varchar2(32)         not null,
   VIO_DEVICE_NBR       VARCHAR2(32),
   SNAP_NBR             VARCHAR2(32),
   VIO_ORG_CODE         VARCHAR2(32)         not null,
   COLLECTION_TYPE      CHAR(1)              not null,
   COLLECTION_POLICE    VARCHAR2(32),
   DISTRICT_CODE        VARCHAR2(6),
   VIO_SITE_CODE        VARCHAR2(16),
   VIOLATION_ADDRESS_DESC VARCHAR2(128),
   DIRECTION_TYPE       VARCHAR2(2),
   DIRECTION_NAME       VARCHAR2(32),
   LANE_NBR             VARCHAR2(2),
   PLATE_NBR            VARCHAR2(16),
   PLATE_TYPE           VARCHAR2(2),
   PLATE_COLOR          VARCHAR2(2),
   VIOLATION_TIME       DATE                 not null,
   VIOLATION_TYPE       CHAR(1)              not null,
   VIOLATION_CODE       VARCHAR2(8)          not null,
   VIOLATION_DESC       VARCHAR2(128),
   SPEED                NUMBER(5),
   LIMIT_LARGE          number(10),
   LIMIT_SMALL          number(10),
   LIMIT_LOWER          NUMBER(5),
   OVER_SPEED_PERCENT   NUMBER(5),
   REGIONAL_CODE        VARCHAR2(18),
   ENTRY_SITE_CODE      VARCHAR2(12),
   REGION_ENTRY_TIME    DATE,
   REGION_DISTANCE      NUMBER(10),
   RED_LIGHT_BEGIN_TIME DATE,
   RED_LIGHT_END_TIME   DATE,
   VERIFICATION         VARCHAR2(32),
   SORT_FLAG            CHAR(1)              not null,
   SORTING_BY           VARCHAR2(32),
   SORTING_TIME         CHAR(10),
   DISCARDED_REASON     CHAR(2),
   REVIEW_AUDIT_FLAG    CHAR(1)              not null,
   LOCK_FLAG            CHAR(1)              default '0' not null,
   LOCK_USER            VARCHAR2(32),
   LOCK_TIME            DATE,
   EXPORT_FLAG          CHAR(1)              default '0' not null,
   EXPORT_TIME          DATE,
   IMAGE                VARCHAR2(1024),
   VIDEO                VARCHAR2(1024),
   CREATE_TIME          DATE                 default SYSDATE not null,
   UPDATE_TIME          DATE,
   REMARK               VARCHAR2(256),
   constraint PK_T_VIO_EVIDENCE primary key (VIO_EVIDENCE_ID)
);

comment on table T_VIO_EVIDENCE is
'存放抓拍的违法毛数据。';

comment on column T_VIO_EVIDENCE.VIO_EVIDENCE_ID is
'违法ID';

comment on column T_VIO_EVIDENCE.VIO_DEVICE_NBR is
'设备、区间';

comment on column T_VIO_EVIDENCE.SNAP_NBR is
'抓拍编号';

comment on column T_VIO_EVIDENCE.COLLECTION_TYPE is
'1：闯红灯设备
2：公路卡口设备
3：测速设备
4：闭路电视
5：移动摄像
6：警务通
7：区间测速
8：卫星定位装置
9：其它电子设备';

comment on column T_VIO_EVIDENCE.VIOLATION_ADDRESS_DESC is
'违法地点';

comment on column T_VIO_EVIDENCE.DIRECTION_TYPE is
'1：上行；2：下行。';

comment on column T_VIO_EVIDENCE.PLATE_NBR is
'号牌号码';

comment on column T_VIO_EVIDENCE.PLATE_TYPE is
'号牌类型(002)';

comment on column T_VIO_EVIDENCE.PLATE_COLOR is
'号牌颜色(003)';

comment on column T_VIO_EVIDENCE.VIOLATION_TIME is
'违法时间';

comment on column T_VIO_EVIDENCE.VIOLATION_TYPE is
'违法类型(030)：
0：大车占道
1：超高速
2：超低速
3：逆行
4：闯红灯
5：压黄线
6：违章停车
7：区间超速
8：禁行
9：其他
a：违法占道
b：遮挡号牌
c：不按导向行驶
d：压线
e：未系安全带
';

comment on column T_VIO_EVIDENCE.VIOLATION_CODE is
'违法代码';

comment on column T_VIO_EVIDENCE.VIOLATION_DESC is
'违法描述';

comment on column T_VIO_EVIDENCE.SPEED is
'车速';

comment on column T_VIO_EVIDENCE.OVER_SPEED_PERCENT is
'超速比';

comment on column T_VIO_EVIDENCE.REGIONAL_CODE is
'区间编号';

comment on column T_VIO_EVIDENCE.ENTRY_SITE_CODE is
'入口点位代码';

comment on column T_VIO_EVIDENCE.REGION_ENTRY_TIME is
'区间入口时间';

comment on column T_VIO_EVIDENCE.REGION_DISTANCE is
'区间距离';

comment on column T_VIO_EVIDENCE.RED_LIGHT_BEGIN_TIME is
'红灯开始时间';

comment on column T_VIO_EVIDENCE.RED_LIGHT_END_TIME is
'红灯结束时间';

comment on column T_VIO_EVIDENCE.VERIFICATION is
'验证码';

comment on column T_VIO_EVIDENCE.SORT_FLAG is
'0：未分拣；1：有效记录；2：废弃记录;3：套牌、假牌';

comment on column T_VIO_EVIDENCE.DISCARDED_REASON is
'01：车速不足:
02：无号牌
03：套牌车:
04：查无车辆信息
05：军警车:
06：图片模糊
07：多车图片:
08：一车不二罚
09：摭挡号牌:
10：号牌不完整
11：其它

';

comment on column T_VIO_EVIDENCE.REVIEW_AUDIT_FLAG is
'0：未复核；1：复核通过；2：复核不通过';

comment on column T_VIO_EVIDENCE.LOCK_FLAG is
'锁定标识
0：未锁定；1：已锁定。';

comment on column T_VIO_EVIDENCE.LOCK_USER is
'锁定人';

comment on column T_VIO_EVIDENCE.LOCK_TIME is
'锁定时间';

comment on column T_VIO_EVIDENCE.EXPORT_FLAG is
'导出标识(040)
0：未导出；1：已导出。';

comment on column T_VIO_EVIDENCE.EXPORT_TIME is
'导出时间';

comment on column T_VIO_EVIDENCE.IMAGE is
'图片存储路径';

comment on column T_VIO_EVIDENCE.VIDEO is
'视频存储路径';

/*==============================================================*/
/* Table: T_VIO_FAKE_CLONE                                      */
/*==============================================================*/
create table T_VIO_FAKE_CLONE 
(
   FAKE_CLONE_VIO_ID    VARCHAR2(32)         not null,
   VIO_EVIDENCE_ID      varchar2(32)         not null,
   VIO_DEVICE_NBR       VARCHAR2(32)         not null,
   SNAP_NBR             VARCHAR2(32),
   VIO_ORG_CODE         VARCHAR2(32)         not null,
   COLLECTION_TYPE      CHAR(1)              not null,
   COLLECTION_POLICE    VARCHAR2(32),
   DISTRICT_CODE        VARCHAR2(6)          not null,
   VIO_SITE_CODE        VARCHAR2(16)         not null,
   VIOLATION_ADDRESS_DESC VARCHAR2(128)        not null,
   DIRECTION_TYPE       VARCHAR2(2),
   DIRECTION_NAME       VARCHAR2(32),
   LANE_NBR             VARCHAR2(2),
   FAKE_OR_CLONE        CHAR(1)              not null,
   PLATE_NBR            VARCHAR2(16)         not null,
   PLATE_TYPE           VARCHAR2(2)          not null,
   PLATE_COLOR          VARCHAR2(2)          not null,
   VEHICLE_TYPE         VARCHAR2(3),
   VEH_CHARCTER         VARCHAR2(2),
   VEHICLE_COLOR        VARCHAR2(5),
   VEHICLE_BRAND        VARCHAR2(32),
   VIOLATION_TIME       DATE                 not null,
   VIOLATION_TYPE       CHAR(1)              not null,
   VIOLATION_CODE       VARCHAR2(8),
   VIOLATION_DESC       VARCHAR2(128)        not null,
   SPEED                NUMBER(5),
   LIMIT_LARGE          number(10),
   LIMIT_SMALL          number(10),
   LIMIT_LOWER          NUMBER(5),
   OVER_SPEED_PERCENT   NUMBER(5),
   REGIONAL_CODE        VARCHAR2(18),
   ENTRY_SITE_CODE      VARCHAR2(12),
   REGION_ENTRY_TIME    DATE,
   REGION_DISTANCE      NUMBER(10),
   RED_LIGHT_BEGIN_TIME DATE,
   RED_LIGHT_END_TIME   DATE,
   VERIFICATION         VARCHAR2(32),
   EXPORT_FLAG          CHAR(1)              default '0' not null,
   AUDIT_FLAG           CHAR(1)              not null,
   EXPORT_TIME          DATE,
   ENTRY_BY             varchar2(16),
   ENTRY_TIME           DATE,
   AUDIT_BY             varchar2(16),
   AUDIT_TIME           DATE,
   CREATE_TIME          DATE                 default SYSDATE,
   UPDATE_TIME          DATE,
   IMAGE                VARCHAR2(1024),
   VIDEO                VARCHAR2(1024),
   REMARK               VARCHAR2(256),
   constraint PK_T_VIO_FAKE_CLONE primary key (FAKE_CLONE_VIO_ID)
);

comment on table T_VIO_FAKE_CLONE is
'记录假牌套牌车违法事实。';

comment on column T_VIO_FAKE_CLONE.VIO_EVIDENCE_ID is
'违法ID';

comment on column T_VIO_FAKE_CLONE.VIO_DEVICE_NBR is
'设备、区间';

comment on column T_VIO_FAKE_CLONE.SNAP_NBR is
'抓拍编号';

comment on column T_VIO_FAKE_CLONE.COLLECTION_TYPE is
'1：闯红灯设备
2：公路卡口设备
3：测速设备
4：闭路电视
5：移动摄像
6：警务通
7：区间测速
8：卫星定位装置
9：其它电子设备';

comment on column T_VIO_FAKE_CLONE.VIOLATION_ADDRESS_DESC is
'违法地点';

comment on column T_VIO_FAKE_CLONE.DIRECTION_TYPE is
'1：上行；2：下行。';

comment on column T_VIO_FAKE_CLONE.FAKE_OR_CLONE is
'1：假牌；2：套牌。';

comment on column T_VIO_FAKE_CLONE.PLATE_NBR is
'号牌号码';

comment on column T_VIO_FAKE_CLONE.PLATE_TYPE is
'号牌类型(002)';

comment on column T_VIO_FAKE_CLONE.PLATE_COLOR is
'号牌颜色(003)';

comment on column T_VIO_FAKE_CLONE.VEHICLE_TYPE is
'参考国家机动车类型代码表：GA802、GA24.4。只能识别出机动车大类的记1位大类代码（H、K、M等），只能识别出大类和规格的记2位；全识别的记3位；只能识别出机动车规格的记2位数字，第一位用0补齐（1：大车；2：中型车；3：小型车；4：微型车）；摩托车为M1或M2';

comment on column T_VIO_FAKE_CLONE.VEH_CHARCTER is
'GA802-2008。参照稽查布控系统编码：
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

comment on column T_VIO_FAKE_CLONE.VEHICLE_COLOR is
'车身颜色(006)';

comment on column T_VIO_FAKE_CLONE.VEHICLE_BRAND is
'车辆品牌';

comment on column T_VIO_FAKE_CLONE.VIOLATION_TIME is
'违法时间';

comment on column T_VIO_FAKE_CLONE.VIOLATION_TYPE is
'违法类型(030)：
0：大车占道
1：超高速
2：超低速
3：逆行
4：闯红灯
5：压黄线
6：违章停车
7：区间超速
8：禁行
9：其他
a：违法占道
b：遮挡号牌
c：不按导向行驶
d：压线
e：未系安全带
';

comment on column T_VIO_FAKE_CLONE.VIOLATION_CODE is
'违法代码';

comment on column T_VIO_FAKE_CLONE.VIOLATION_DESC is
'违法描述';

comment on column T_VIO_FAKE_CLONE.SPEED is
'车速';

comment on column T_VIO_FAKE_CLONE.OVER_SPEED_PERCENT is
'超速比';

comment on column T_VIO_FAKE_CLONE.REGIONAL_CODE is
'区间编号';

comment on column T_VIO_FAKE_CLONE.ENTRY_SITE_CODE is
'入口点位代码';

comment on column T_VIO_FAKE_CLONE.REGION_ENTRY_TIME is
'区间入口时间';

comment on column T_VIO_FAKE_CLONE.REGION_DISTANCE is
'区间距离';

comment on column T_VIO_FAKE_CLONE.RED_LIGHT_BEGIN_TIME is
'红灯开始时间';

comment on column T_VIO_FAKE_CLONE.RED_LIGHT_END_TIME is
'红灯结束时间';

comment on column T_VIO_FAKE_CLONE.VERIFICATION is
'验证码';

comment on column T_VIO_FAKE_CLONE.EXPORT_FLAG is
'导出标识(040)
0：未导出；1：已导出。';

comment on column T_VIO_FAKE_CLONE.AUDIT_FLAG is
'0：未审核；1：已审核。';

comment on column T_VIO_FAKE_CLONE.EXPORT_TIME is
'导出时间';

comment on column T_VIO_FAKE_CLONE.ENTRY_BY is
'录入人';

comment on column T_VIO_FAKE_CLONE.ENTRY_TIME is
'录入时间';

comment on column T_VIO_FAKE_CLONE.AUDIT_BY is
'审核人';

comment on column T_VIO_FAKE_CLONE.IMAGE is
'图片存储路径';

comment on column T_VIO_FAKE_CLONE.VIDEO is
'视频存储路径';

/*==============================================================*/
/* Table: T_VIO_LOCAL_PUNISH                                    */
/*==============================================================*/
create table T_VIO_LOCAL_PUNISH 
(
   PUNISH_ID            VARCHAR2(32)         not null,
   VIO_VIOLATION_ID     VARCHAR2(32),
   DECISION_NBR         VARCHAR2(15)         not null,
   FINE_MONEY           NUMBER(5)            not null,
   FINE_SCORE           NUMBER(5)            not null,
   FINE_LATE_FEE        NUMBER(6,2)          not null,
   PAYMENT_TIME         DATE,
   AGENT_NAME           VARCHAR2(32),
   AGENT_IDENTIFICATION_TYPE CHAR(1),
   AGENT_IDENTIFICATION_NBR VARCHAR2(64),
   PROCESS_ORG          VARCHAR2(16)         not null,
   PROCESS_POLICE       VARCHAR2(32)         not null,
   PROCESS_TIME         DATE                 not null,
   REMARK               VARCHAR2(256),
   constraint PK_T_VIO_LOCAL_PUNISH primary key (PUNISH_ID)
);

comment on table T_VIO_LOCAL_PUNISH is
'本地处罚表';

comment on column T_VIO_LOCAL_PUNISH.PUNISH_ID is
'处罚ID';

comment on column T_VIO_LOCAL_PUNISH.VIO_VIOLATION_ID is
'违法证据ID';

comment on column T_VIO_LOCAL_PUNISH.DECISION_NBR is
'决定书编号';

comment on column T_VIO_LOCAL_PUNISH.FINE_MONEY is
'处罚金额';

comment on column T_VIO_LOCAL_PUNISH.FINE_SCORE is
'违法记分';

comment on column T_VIO_LOCAL_PUNISH.FINE_LATE_FEE is
'滞纳金';

comment on column T_VIO_LOCAL_PUNISH.PAYMENT_TIME is
'交款时间';

comment on column T_VIO_LOCAL_PUNISH.AGENT_NAME is
'代理人姓名';

comment on column T_VIO_LOCAL_PUNISH.AGENT_IDENTIFICATION_TYPE is
'代理人证件类型(009)';

comment on column T_VIO_LOCAL_PUNISH.AGENT_IDENTIFICATION_NBR is
'代理人证件号码';

comment on column T_VIO_LOCAL_PUNISH.PROCESS_ORG is
'处理机构';

comment on column T_VIO_LOCAL_PUNISH.PROCESS_POLICE is
'处理交警';

comment on column T_VIO_LOCAL_PUNISH.PROCESS_TIME is
'处理时间';

/*==============================================================*/
/* Index: "Relationship_2_FK"                                   */
/*==============================================================*/
create index "Relationship_2_FK" on T_VIO_LOCAL_PUNISH (
   VIO_VIOLATION_ID ASC
);

/*==============================================================*/
/* Table: T_VIO_OPERATION_LOG                                   */
/*==============================================================*/
create table T_VIO_OPERATION_LOG 
(
   OPE_LOG_ID           VARCHAR2(32)         not null,
   SYS_FUNCTION_CODE    VARCHAR2(32),
   OPE_USER_NAME        VARCHAR2(32)         not null,
   OPERATE_TIME         DATE                 not null,
   OPERATE_RECORD_COUNTS VARCHAR2(10)         not null,
   CONSUME_SECONDS      FLOAT                not null,
   ERROR_DESC           VARCHAR2(1024),
   COMPUTER_IP          VARCHAR2(16),
   OPERATION_RESULT     char(1),
   OPERATION_CONTENT    varchar(4096),
   VIOLATION_ID         varchar(32),
   constraint PK_T_VIO_OPERATION_LOG primary key (OPE_LOG_ID)
);

comment on table T_VIO_OPERATION_LOG is
'操作日志表
记录操作日志的目的：
（1）系统管理员可以审计系统应用的安全性，通过IP来判定，通过某些应用的使用频次来分析等；
（2）重要、敏感的操作，可以记录操作的内容，用以误操作或非法操作下的还原；
（3）工程技术人员和软件开发人员，可以通过操作日志，来分析系统功能的可靠性及效率。
';

comment on column T_VIO_OPERATION_LOG.OPE_LOG_ID is
'日志id';

comment on column T_VIO_OPERATION_LOG.SYS_FUNCTION_CODE is
'系统功能编码';

comment on column T_VIO_OPERATION_LOG.OPE_USER_NAME is
'操作用户';

comment on column T_VIO_OPERATION_LOG.OPERATE_TIME is
'操作时间';

comment on column T_VIO_OPERATION_LOG.CONSUME_SECONDS is
'操作耗时';

comment on column T_VIO_OPERATION_LOG.ERROR_DESC is
'操作失败原因';

comment on column T_VIO_OPERATION_LOG.COMPUTER_IP is
'用户终端IP';

comment on column T_VIO_OPERATION_LOG.OPERATION_RESULT is
'操作结果:1-成功，0-失败';

comment on column T_VIO_OPERATION_LOG.OPERATION_CONTENT is
'修改信息';

comment on column T_VIO_OPERATION_LOG.VIOLATION_ID is
'违法证据id';

/*==============================================================*/
/* Index: "Relationship_17_FK"                                  */
/*==============================================================*/
create index "Relationship_17_FK" on T_VIO_OPERATION_LOG (
   SYS_FUNCTION_CODE ASC
);

/*==============================================================*/
/* Table: T_VIO_UPLOAD_LOG                                      */
/*==============================================================*/
create table T_VIO_UPLOAD_LOG 
(
   UPLOAD_LOG_ID        varchar2(32)         not null,
   VIO_VIOLATION_ID     VARCHAR2(32)         not null,
   UPLOAD_TIME          DATE                 not null,
   UPLOAD_RESULT        VARCHAR2(1024),
   UPLOAD_CONTENT       VARCHAR2(2048),
   UPLOAD_TYPE          CHAR(1),
   constraint PK_T_VIO_UPLOAD_LOG primary key (UPLOAD_LOG_ID)
);

comment on table T_VIO_UPLOAD_LOG is
'违法上传日志表';

comment on column T_VIO_UPLOAD_LOG.UPLOAD_LOG_ID is
'记录ID';

comment on column T_VIO_UPLOAD_LOG.VIO_VIOLATION_ID is
'违法证据ID';

comment on column T_VIO_UPLOAD_LOG.UPLOAD_TIME is
'上传时间';

comment on column T_VIO_UPLOAD_LOG.UPLOAD_RESULT is
'上传结果';

comment on column T_VIO_UPLOAD_LOG.UPLOAD_CONTENT is
'上传内容';

comment on column T_VIO_UPLOAD_LOG.UPLOAD_TYPE is
'上传类型(048)';

/*==============================================================*/
/* Table: T_VIO_VIOLATION                                       */
/*==============================================================*/
create table T_VIO_VIOLATION 
(
   VIO_VIOLATION_ID     VARCHAR2(32)         not null,
   VIO_EVIDENCE_ID      varchar2(32)         not null,
   VIO_DEVICE_NBR       VARCHAR2(32)         not null,
   SNAP_NBR             VARCHAR2(32),
   VIO_ORG_CODE         VARCHAR2(32)         not null,
   COLLECTION_TYPE      CHAR(1)              not null,
   COLLECTION_POLICE    VARCHAR2(32),
   DISTRICT_CODE        VARCHAR2(6)          not null,
   VIO_SITE_CODE        VARCHAR2(16)         not null,
   VIOLATION_ADDRESS_DESC VARCHAR2(128)        not null,
   DIRECTION_TYPE       VARCHAR2(2),
   DIRECTION_NAME       VARCHAR2(32),
   LANE_NBR             VARCHAR2(2),
   PLATE_NBR            VARCHAR2(16)         not null,
   PLATE_TYPE           VARCHAR2(2)          not null,
   PLATE_COLOR          VARCHAR2(2)          not null,
   VIOLATION_TIME       DATE                 not null,
   ENFORCEMENT_CATEGORY CHAR(1)              not null,
   VIOLATION_TYPE       CHAR(1)              not null,
   VIOLATION_CODE       VARCHAR2(8)          not null,
   VIOLATION_DESC       VARCHAR2(128)        not null,
   SPEED                NUMBER(5),
   LIMIT_LARGE          number(10),
   LIMIT_SMALL          number(10),
   LIMIT_LOWER          NUMBER(5),
   OVER_SPEED_PERCENT   NUMBER(5),
   REGIONAL_CODE        VARCHAR2(18),
   ENTRY_SITE_CODE      VARCHAR2(12),
   REGION_ENTRY_TIME    DATE,
   REGION_DISTANCE      NUMBER(10),
   RED_LIGHT_BEGIN_TIME DATE,
   RED_LIGHT_END_TIME   DATE,
   VERIFICATION         VARCHAR2(32),
   VEHICLE_TYPE         VARCHAR2(3),
   VEH_CHARCTER         VARCHAR2(2),
   VEHICLE_COLOR        VARCHAR2(5),
   VEHICLE_BRAND        VARCHAR2(32),
   VEHICLE_OWNER_NAME   VARCHAR2(128),
   VEHICLE_ISSUE_ORG    VARCHAR2(16),
   VEHICLE_EXPIRE_DATE  DATE,
   VEHICLE_OWNER_ADDRESS VARCHAR2(128),
   VEHICLE_OWNER_CONTACT varchar2(64),
   VIO_STATUS           CHAR(1)              not null,
   LOCAL_PUNISH_FLAG    CHAR(1)              not null,
   EXPORT_FLAG          CHAR(1)              default '0' not null,
   EXPORT_TIME          DATE,
   ENTRY_BY             varchar2(16),
   ENTRY_TIME           DATE,
   AUDIT_BY             varchar2(16),
   AUDIT_TIME           DATE,
   UPLOAD_BY            varchar2(16),
   UPLOAD_TIME          DATE,
   CREATE_TIME          DATE                 default SYSDATE,
   UPDATE_TIME          DATE,
   IMAGE                VARCHAR2(1024),
   VIDEO                VARCHAR2(1024),
   REMARK               VARCHAR2(256),
   constraint PK_T_VIO_VIOLATION primary key (VIO_VIOLATION_ID)
);

comment on table T_VIO_VIOLATION is
'记录违法整理后的违法数据。';

comment on column T_VIO_VIOLATION.VIO_VIOLATION_ID is
'违法证据ID';

comment on column T_VIO_VIOLATION.VIO_EVIDENCE_ID is
'违法ID';

comment on column T_VIO_VIOLATION.VIO_DEVICE_NBR is
'设备、区间';

comment on column T_VIO_VIOLATION.SNAP_NBR is
'抓拍编号';

comment on column T_VIO_VIOLATION.COLLECTION_TYPE is
'1：闯红灯设备
2：公路卡口设备
3：测速设备
4：闭路电视
5：移动摄像
6：警务通
7：区间测速
8：卫星定位装置
9：其它电子设备';

comment on column T_VIO_VIOLATION.VIOLATION_ADDRESS_DESC is
'违法地点';

comment on column T_VIO_VIOLATION.DIRECTION_TYPE is
'1：上行；2：下行。';

comment on column T_VIO_VIOLATION.PLATE_NBR is
'号牌号码';

comment on column T_VIO_VIOLATION.PLATE_TYPE is
'号牌类型(002)';

comment on column T_VIO_VIOLATION.PLATE_COLOR is
'号牌颜色(003)';

comment on column T_VIO_VIOLATION.VIOLATION_TIME is
'违法时间';

comment on column T_VIO_VIOLATION.ENFORCEMENT_CATEGORY is
'证据类型(032)：
1、有效违法
2、白名单车违法
3、军警车违法
4、农机车牌违法
';

comment on column T_VIO_VIOLATION.VIOLATION_TYPE is
'违法类型(030)：
0：大车占道
1：超高速
2：超低速
3：逆行
4：闯红灯
5：压黄线
6：违章停车
7：区间超速
8：禁行
9：其他
a：违法占道
b：遮挡号牌
c：不按导向行驶
d：压线
e：未系安全带
';

comment on column T_VIO_VIOLATION.VIOLATION_CODE is
'违法代码';

comment on column T_VIO_VIOLATION.VIOLATION_DESC is
'违法描述';

comment on column T_VIO_VIOLATION.SPEED is
'车速';

comment on column T_VIO_VIOLATION.OVER_SPEED_PERCENT is
'超速比';

comment on column T_VIO_VIOLATION.REGIONAL_CODE is
'区间编号';

comment on column T_VIO_VIOLATION.ENTRY_SITE_CODE is
'入口点位代码';

comment on column T_VIO_VIOLATION.REGION_ENTRY_TIME is
'区间入口时间';

comment on column T_VIO_VIOLATION.REGION_DISTANCE is
'区间距离';

comment on column T_VIO_VIOLATION.RED_LIGHT_BEGIN_TIME is
'红灯开始时间';

comment on column T_VIO_VIOLATION.RED_LIGHT_END_TIME is
'红灯结束时间';

comment on column T_VIO_VIOLATION.VERIFICATION is
'验证码';

comment on column T_VIO_VIOLATION.VEHICLE_TYPE is
'参考国家机动车类型代码表：GA802、GA24.4。只能识别出机动车大类的记1位大类代码（H、K、M等），只能识别出大类和规格的记2位；全识别的记3位；只能识别出机动车规格的记2位数字，第一位用0补齐（1：大车；2：中型车；3：小型车；4：微型车）；摩托车为M1或M2';

comment on column T_VIO_VIOLATION.VEH_CHARCTER is
'GA802-2008。参照稽查布控系统编码：
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

comment on column T_VIO_VIOLATION.VEHICLE_COLOR is
'车身颜色(006)';

comment on column T_VIO_VIOLATION.VEHICLE_BRAND is
'车辆品牌';

comment on column T_VIO_VIOLATION.VEHICLE_OWNER_NAME is
'机动车所有人';

comment on column T_VIO_VIOLATION.VEHICLE_ISSUE_ORG is
'发证机关';

comment on column T_VIO_VIOLATION.VEHICLE_EXPIRE_DATE is
'有效期止';

comment on column T_VIO_VIOLATION.VEHICLE_OWNER_ADDRESS is
'登记住所';

comment on column T_VIO_VIOLATION.VEHICLE_OWNER_CONTACT is
'联系方式';

comment on column T_VIO_VIOLATION.VIO_STATUS is
'1：已录入
2：已审核
3：已上传
4：已通知';

comment on column T_VIO_VIOLATION.LOCAL_PUNISH_FLAG is
'0：未处罚；1：已处罚。';

comment on column T_VIO_VIOLATION.EXPORT_FLAG is
'导出标识(040)
0：未导出；1：已导出。';

comment on column T_VIO_VIOLATION.EXPORT_TIME is
'导出时间';

comment on column T_VIO_VIOLATION.ENTRY_BY is
'录入人';

comment on column T_VIO_VIOLATION.ENTRY_TIME is
'录入时间';

comment on column T_VIO_VIOLATION.AUDIT_BY is
'审核人';

comment on column T_VIO_VIOLATION.UPLOAD_BY is
'上传人';

comment on column T_VIO_VIOLATION.UPLOAD_TIME is
'上传时间';

comment on column T_VIO_VIOLATION.IMAGE is
'图片存储路径';

comment on column T_VIO_VIOLATION.VIDEO is
'视频存储路径';

alter table T_VIO_LOCAL_PUNISH
   add constraint FK_T_VIO_LO_RELATIONS_T_VIO_VI foreign key (VIO_VIOLATION_ID)
      references T_VIO_VIOLATION (VIO_VIOLATION_ID);

