set define off
spool 12_1_´´½¨ºÅÅÆÇ°×º±í.log

--2016/05/09
--´´½¨ºÅÅÆÇ°×º±í
create table T_SYS_PLATE_PREFIX 
(
   PLATE_PREFIX         VARCHAR2(32)         not null,
   ORG_CODE             VARCHAR2(32)         not null,
   constraint PK_T_SYS_PLATE_PREFIX primary key (PLATE_PREFIX)
);

comment on table T_SYS_PLATE_PREFIX is 'ºÅÅÆÇ°×º±í';

--²åÈëÔÆÄÏÊ¡ºÅÅÆÇ°×ºÊý¾Ý
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆA', '530100000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆB', '530100000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆC', '530600000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆD', '530300000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆE', '532300000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆF', '530400000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆG', '532500000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆH', '532600000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆJ', '530800000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆL', '532900000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆK', '532800000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆM', '530500000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆN', '533100000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆP', '530700000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆQ', '533300000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆR', '533400000000');
insert into T_SYS_PLATE_PREFIX (PLATE_PREFIX, ORG_CODE)
values ( 'ÔÆS', '530900000000');
commit;


spool off

exit;
