set define off
spool 13_4_在线率统计存储过程.log


prompt
prompt Creating procedure STATICDEVICEONLINERATE
prompt =========================================
prompt
CREATE OR REPLACE PROCEDURE ITMS3.StaticDeviceOnlineRate(orgCode      varchar2,
                                                   deviceType   varchar2,
                                                   contractorId varchar2,
                                                   timeWay      varchar2,
                                                   beginTime    varchar2,
                                                   endTime      varchar2,
                                                   myCursor     out sys_refcursor) IS
  orgLevel         integer;
  orgPrivilegeCode varchar2(32);
BEGIN
  select t.org_level, t.org_privilege_code
    into orgLevel, orgPrivilegeCode
    from t_sys_org t
   where t.org_code = orgCode;
  if (orglevel < 3) then
    --如果是总队或支队，显示下属机构的在线率
    if (timeWay = 'W' or timeWay = 'M') then --如果是周或者月，按日显示在线率
      open mycursor for
        with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code),
        show_orgname as (select t.org_code, t.org_name from org_gradation t where t.level_hierarchy in ('1', '2'))
        select *
          from (select t.display_org_code as org_code,
                       t.org_name,
                       nvl(to_char(t.statistics_date, 'yyyy-mm-dd'), '平均') as time_stamp,
                       round(avg(primary_online), 4) primary_online,
                       round(avg(correct_online), 4) correct_online
                  from (select t.device_id,
                               statistics_date,
                               primary_online,
                               correct_online,
                               v.display_org_code,
                               r.org_name
                          from (select t.device_id,
                                       t.org_id,
                                       t.statistics_date,
                                       case when t.duration_secs is null then 0 when t.duration_secs >= 86400 then 1 else t.duration_secs / 86400 end as primary_online,
                                       case when t.correct_should_secs is null then 0 when (t.duration_secs-t.fault_secs) >= t.correct_should_secs then 1 else  (t.duration_secs-t.fault_secs) / t.correct_should_secs end as correct_online
                                  from x_d_d_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.statistics_date >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_date <=to_date(endTime, 'yyyy-mm-dd')) t,
                               org_gradation v,
                               show_orgname r
                         where t.org_id = v.org_id(+)
                           and v.display_org_code = r.org_code(+)) t
                 group by rollup(t.display_org_code,t.org_name,t.statistics_date)
                having grouping_id(t.display_org_code, t.org_name) <= 0) 
                unpivot(online_rate for data_type in(correct_online as '2', primary_online as '1'));
    else --其他 按月显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code),
       show_orgname as (select t.org_code, t.org_name from org_gradation t where t.level_hierarchy in ('1', '2'))
       select * from(select t.display_org_code as org_code, 
                         r.org_name, 
                         nvl(to_char(t.statistics_month,'yyyy-mm'),'平均') as time_stamp, 
                         round(avg(primary_online),4)primary_online, 
                         round(avg(correct_online),4) correct_online from( 
        select t.device_id,statistics_month, primary_online,correct_online,v.display_org_code
          from (select t.device_id, 
               t.org_id, 
               trunc(t.statistics_date,'MM')statistics_month,
               case when t.duration_secs is null then 0 when t.duration_secs >= 86400 then 1 else t.duration_secs / 86400 end as primary_online,
               case when t.correct_should_secs is null then 0 when (t.duration_secs-t.fault_secs) >= t.correct_should_secs then 1 else  (t.duration_secs-t.fault_secs) / t.correct_should_secs end as correct_online
          from x_d_d_status_log t  
           where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.statistics_date >= to_date(beginTime,'yyyy-mm-dd')  
                                   and t.statistics_date <= to_date(endTime,'yyyy-mm-dd')) t, 
       org_gradation v
 where t.org_id = v.org_id(+))t,
   show_orgname r
   where t.display_org_code = r.org_code(+) 
    group by rollup(t.display_org_code, r.org_name, statistics_month) 
     having grouping_id(t.display_org_code,r.org_name)<=0) unpivot(online_rate for data_type in(correct_online as  '2',primary_online as '1'));
     end if;
  else
    --如果是大队或更低级别机构，显示管辖的设备在线率
    if (timeWay = 'W' or timeWay='M') then--如果是周或者月，按日显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code)
        select * from(select t.org_code as org_code,
                       t.org_name,
                       nvl(to_char(t.time_stamp, 'yyyy-mm-dd'), '平均') as time_stamp,
                       round(avg(primary_online), 4) primary_online,
                       round(avg(correct_online), 4) correct_online
                  from(select t.device_id as org_code,v.device_name as org_name,t.statistics_date as time_stamp,t.primary_online,t.correct_online from(select t.device_id,
                                       t.org_id,
                                       t.statistics_date,
                                       case when t.duration_secs is null then 0 when t.duration_secs >= 86400 then 1 else t.duration_secs / 86400 end as primary_online,
                                       case when t.correct_should_secs is null then 0 when (t.duration_secs-t.fault_secs) >= t.correct_should_secs then 1 else  (t.duration_secs-t.fault_secs) / t.correct_should_secs end as correct_online
                                  from x_d_d_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.statistics_date >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_date <=to_date(endTime, 'yyyy-mm-dd'))t,t_device_sys v where t.device_id=v.device_id)t
                                   group by rollup(t.org_code, t.org_name,t.time_stamp)
                                   having grouping_id(t.org_code, t.org_name) <= 0) 
                                   unpivot(online_rate for data_type in(correct_online as '2',primary_online as '1'));
    else --其他 按月显示在线率
      open myCursor for
       with org_gradation as
         (select a.org_id,
                 a.org_code,
                 a.org_name,
                 a.level_hierarchy,
                 case when a.level_hierarchy >= 3 then substr(a.collapse_hierarchy, instr(a.collapse_hierarchy, '->', 1, 2) + 2,12) else a.org_code end display_org_code
            from (select a.org_id,
                         a.org_code,
                         a.org_name,
                         level level_hierarchy,
                         sys_connect_by_path(org_code, '->') collapse_hierarchy
                    from t_sys_org a
                   where substr(a.org_privilege_code, 0, length(orgPrivilegeCode)) = orgPrivilegeCode
                   start with a.org_code = orgCode
                  connect by prior a.org_id = a.parent_instruct_org_id) a
           order by a.org_code)
        select * from(select t.org_code, 
                         t.org_name, 
                         nvl(to_char(t.time_stamp,'yyyy-mm'),'平均') as time_stamp, 
                         round(avg(primary_online),4)primary_online, 
                         round(avg(correct_online),4) correct_online from(
                         select t.device_id as org_code,v.device_name as org_name,t.statistics_month as time_stamp,t.primary_online,t.correct_online from(select t.device_id,
                                       t.org_id,
                                       t.statistics_month,
                                       t.primary_online,
                                       t.correct_online
                                  from x_d_m_status_log t
                                 where t.org_id in (select a.org_id from org_gradation a)
                                   and func_varchar_filter(t.contractor_id, contractorId) = 'true'
                                   and decode(deviceType,NULL,'true',func_funtion_filter(deviceType, t.device_type)) = 'true'
                                   and t.statistics_month >= to_date(beginTime, 'yyyy-mm-dd')
                                   and t.statistics_month <=to_date(endTime, 'yyyy-mm-dd'))t,t_device_sys v where t.device_id=v.device_id)t
                                   group by rollup(t.org_code, t.org_name, t.time_stamp) 
                                   having grouping_id(t.org_code, t.org_name)<=0)
                                   unpivot(online_rate for data_type in(correct_online as '2',primary_online as '1'));
    END if;
  end if;
END;
/

spool off

exit;
