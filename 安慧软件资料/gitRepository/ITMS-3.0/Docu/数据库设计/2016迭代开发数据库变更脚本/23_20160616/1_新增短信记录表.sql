set define off
spool 23_1_新增短信记录表.log

prompt
prompt Creating table T_MSG_INFO
prompt =========================
prompt
create table T_MSG_INFO
(
  id          VARCHAR2(32) not null,
  sender      VARCHAR2(32),
  send_time   DATE not null,
  send_status CHAR(1),
  msg_type    VARCHAR2(2),
  msg_title   VARCHAR2(128),
  msg_content VARCHAR2(2048) not null,
  receiver    VARCHAR2(32) not null,
  remark      VARCHAR2(256)
)
;
comment on table T_MSG_INFO
  is '用户短信信息表';
comment on column T_MSG_INFO.id
  is '短信信息ID';
comment on column T_MSG_INFO.sender
  is '发送者';
comment on column T_MSG_INFO.send_time
  is '发送时间';
comment on column T_MSG_INFO.send_status
  is '发送状态';
comment on column T_MSG_INFO.msg_type
  is '内容类型';
comment on column T_MSG_INFO.msg_title
  is '内容标题';
comment on column T_MSG_INFO.msg_content
  is '短信内容';
comment on column T_MSG_INFO.receiver
  is '接收者';
comment on column T_MSG_INFO.remark
  is '备注';
alter table T_MSG_INFO
  add constraint PK_T_MSG_INFO_INDEX primary key (ID);


spool off

exit;
