set define off
spool 3_1_视频巡航日志表增加机构ID.log

alter table t_traffic_plan_log add org_id varchar2(32);
comment on column t_traffic_plan_log.org_id is '机构id';

update t_traffic_plan_log l
   set l.org_id = (select p.org_id
                     from t_traffic_plan p
                    where p.video_plan_id = l.video_plan_id);

commit;

spool off

exit;