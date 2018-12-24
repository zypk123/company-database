------------------------------------------------------
-- Export file for user ITMS3                       --
-- Created by Administrator on 2016-04-21, 11:22:41 --
------------------------------------------------------

set define off
spool 5_2_增加检定率统计视图.log

prompt
prompt Creating view V_DEVICE_CERTIFICATION
prompt ====================================
prompt
create or replace force view itms3.v_device_certification as
select t.device_id,
       t.device_sys_nbr,
       t.device_name,
       '488' || '400' || t.device_type as DEVICE_TYPE,
       t.org_id,
       t.contract_id,
       t.contractor_id,
       t.enable_update_date
  from t_device_sys t
 where t.use_status_flag = '1'
   and t.verification_mark = '1'
union
select t.equipment_id as device_id,
       t.equipment_nbr as device_sys_nbr,
       t.equipment_name as device_name,
       '488' || '484' || t.equipment_type as DEVICE_TYPE,
       t.org_id,
       t.contract_id,
       t.contractor_id,
       t.enable_update_date
  from t_device_equipment t
 where t.use_status_flag = '1'
   and t.verification_mark = '1'
union
select t.regional_id as device_id,
       t.regional_code as device_sys_nbr,
       t.regional_name as device_name,
       '488' || '000' || '01' as DEVICE_TYPE,
       t.org_id,
       t.contract_id,
       t.contractor_id,
       t.create_time as enable_update_date
  from t_device_region t
 where t.enable_flag = '1'
   and t.verification_mark = '1';


spool off

exit;
