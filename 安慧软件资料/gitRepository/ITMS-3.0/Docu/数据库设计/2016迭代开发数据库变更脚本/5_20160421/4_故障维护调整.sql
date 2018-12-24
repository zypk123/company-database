set define off
spool 5_4_故障维护调整.log

alter table T_DEVICE_FAULT_LOG modify fault_sub_type VARCHAR2(5);
-- Add comments to the columns 
comment on column T_DEVICE_FAULT_LOG.fault_type
  is '故障/报警类型。11:相机子故障;12:雷达子故障;13:电源子故障;14:线圈子故障;15:网络子故障;16:GPS故障子故障;17:能见度仪子故障;18:LED子故障;99:其他故障;';

-- Add/modify columns 
alter table T_DEVICE_FAULT modify fault_sub_type VARCHAR2(5);
-- Add comments to the columns 
comment on column T_DEVICE_FAULT.fault_type
  is '故障/报警类型。11:相机子故障;12:雷达子故障;13:电源子故障;14:线圈子故障;15:网络子故障;16:GPS故障子故障;17:能见度仪子故障;18:LED子故障;99:其他故障;';
  
  
spool off

exit;
