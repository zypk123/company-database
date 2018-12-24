set define off
spool 17_3_巡航日志表变更.log

-- 日志表增加网巡预案冗余字段
alter table t_traffic_plan_log add video_plan_name VARCHAR2(256);
comment on column t_traffic_plan_log.video_plan_name
  is '网巡预案名称';


spool off

exit;