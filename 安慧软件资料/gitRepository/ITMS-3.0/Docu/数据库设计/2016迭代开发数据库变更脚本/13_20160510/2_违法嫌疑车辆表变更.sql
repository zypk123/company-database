set define off
spool 13_2_违法嫌疑车辆表变更.log

--修改违法嫌疑车辆表，增加确认结果记录
alter table T_VEHTRACK_PASS_TO_VIO ADD CONFIRM_RESULT VARCHAR2(256) ;
comment on column T_VEHTRACK_PASS_TO_VIO.CONFIRM_RESULT is '确认结果记录';

--创建sequence
create sequence SEQ_T_VEHTRACK_PASS_TO_VIO
minvalue 1000000000000000000000000
maxvalue 9999999999999999999999999
start with 1000000000000000000000000
increment by 1
cache 20
order;

--创建违法嫌疑车辆记录ID的触发器
create or replace trigger Trigger_T_VEHTRACK_PASS_TO_VIO
  before insert on T_VEHTRACK_PASS_TO_VIO  
  for each row
declare
  -- local variables here
begin
  if(:new.VIO_SUSPECTED_ID is null or :new.VIO_SUSPECTED_ID = 0) then
  select SEQ_T_VEHTRACK_PASS_TO_VIO.nextval into :new.VIO_SUSPECTED_ID from dual;
  end if;
end Trigger_T_VEHTRACK_PASS_TO_VIO;

/

spool off

exit;
