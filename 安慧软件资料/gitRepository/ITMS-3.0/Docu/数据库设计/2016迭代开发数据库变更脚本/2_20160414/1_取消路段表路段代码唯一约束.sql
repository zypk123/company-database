set define off
spool 2_1_取消路段表路段代码唯一约束.log

alter table T_SYS_ROAD_SECTION
  drop constraint ROAD_SECTION_CODE_UNIQUE cascade;
  
spool off

exit;