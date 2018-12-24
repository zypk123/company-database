set define off
spool 5_平台首页.log

create table T_SYS_NOTICE 
(
   NOTICE_ID            VARCHAR2(32)         not null,
   USER_ID              VARCHAR2(32)         not null,
   NOTICE_TITLE         VARCHAR2(256)        not null,
   NOTICE_CONTENT       VARCHAR2(512),
   CREATE_TIME          DATE                 not null,
   UPDATE_TIME          DATE,
   constraint PK_T_SYS_NOTICE primary key (NOTICE_ID)
);

COMMENT ON TABLE T_SYS_NOTICE IS '通知公告表';
comment on column T_SYS_NOTICE.NOTICE_ID is '公告ID';
comment on column T_SYS_NOTICE.USER_ID is '发布人';
comment on column T_SYS_NOTICE.NOTICE_TITLE is '公告标题';
comment on column T_SYS_NOTICE.NOTICE_CONTENT is '公告内容';
comment on column T_SYS_NOTICE.CREATE_TIME is '发布时间';
comment on column T_SYS_NOTICE.UPDATE_TIME is '更新时间';

alter table T_SYS_NOTICE add constraint FK_NOTICE_TO_USER foreign key (USER_ID)
      references T_SYS_USER (USER_ID) on delete cascade;


create table T_SYS_SITE_LINK 
(
   LINK_ID              VARCHAR2(32)         not null,
   LINK_NAME            VARCHAR2(256)        not null,
   LINK_ICON            VARCHAR2(128),
   LINK_URL             VARCHAR2(128)        not null,
   constraint PK_T_SYS_SITE_LINK primary key (LINK_ID)
);

COMMENT ON TABLE T_SYS_SITE_LINK IS '常用链接表';
comment on column T_SYS_SITE_LINK.LINK_ID is '链接ID';
comment on column T_SYS_SITE_LINK.LINK_NAME is '链接名称';
comment on column T_SYS_SITE_LINK.LINK_ICON is '标志图像';
comment on column T_SYS_SITE_LINK.LINK_URL is 'URL地址';

create table T_SYS_DOWNLOAD 
(
   DOWNLOAD_ID          VARCHAR2(32)         not null,
   DOWNLOAD_TITLE       VARCHAR2(256)        not null,
   DOWNLOAD_CONTENT     VARCHAR2(256),
   DOWNLOAD_URL         VARCHAR2(128)        not null,
   CREATE_TIME          DATE                 not null,
   UPDATE_TIME          DATE,
   constraint PK_T_SYS_DOWNLOAD primary key (DOWNLOAD_ID)
);


COMMENT ON TABLE T_SYS_DOWNLOAD IS '下载内容表';
comment on column T_SYS_DOWNLOAD.DOWNLOAD_ID IS '下载内容ID';
comment on column T_SYS_DOWNLOAD.DOWNLOAD_TITLE IS '标题';
comment on column T_SYS_DOWNLOAD.DOWNLOAD_CONTENT IS '内容';
comment on column T_SYS_DOWNLOAD.DOWNLOAD_URL IS '下载地址';
comment on column T_SYS_DOWNLOAD.CREATE_TIME IS '发布时间';
comment on column T_SYS_DOWNLOAD.UPDATE_TIME IS '更新时间';



spool off

exit;