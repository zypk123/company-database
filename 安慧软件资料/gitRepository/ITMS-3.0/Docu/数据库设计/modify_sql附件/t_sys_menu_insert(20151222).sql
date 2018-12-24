prompt Importing table T_SYS_MENU...
set feedback off
set define off
insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)
values ('0602', '0602', '断面流量分析', null, '06', '2', '1', null, null);

insert into T_SYS_MENU (MENU_CODE, SYS_CONFIG_ID, MENU_NAME, TARGET_URL, PARENT_CODE, SORT_INDEX, MENU_ENABLE_FLAG, REMARK, MENU_IMAGE_URL)
values ('060201', '020601', '交通流量趋势分析', 'tpls/statisticsAnalysis/sectionflowAnalysis/site-flow-trend-analysis.html', '0602', '1', '1', null, null);

prompt Done.
