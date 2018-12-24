set define off
spool 4_视频巡航变更.log

alter table t_traffic_plan add DEFAULT_CRUISE_TIMES number;
comment on column t_traffic_plan.DEFAULT_CRUISE_TIMES is '默认单屏巡航时间';
 
 
alter table t_traffic_plan add TOTAL_CRUISE_TIMES number;
comment on column t_traffic_plan.TOTAL_CRUISE_TIMES is '单屏巡航时间总长';
 
alter table t_traffic_plan add PLAN_EXECUTE_TIMES number;
comment on column t_traffic_plan.PLAN_EXECUTE_TIMES is '方案执行时间';

alter table t_traffic_plan_video modify preset_list null;

alter table t_traffic_plan_video add sys_component_id varchar2(32) null;
comment on column t_traffic_plan_video.sys_component_id is '关联的视频组件id';
 
update t_sys_menu set target_url='tpls/trafficMonitor/videoMonitor/cruise-plan-config.html' where menu_code='020401';
commit;


spool off

exit;