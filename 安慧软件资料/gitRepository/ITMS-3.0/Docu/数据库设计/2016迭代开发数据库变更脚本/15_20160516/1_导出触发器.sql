set define off
spool 15_1_µ¼³ö´¥·¢Æ÷.log

-- Create sequence 
create sequence SEQ_T_SYS_EXPORT
minvalue 1
maxvalue 999999
start with 1
increment by 1
cache 100;

create or replace trigger TRIG_T_SYS_EXPORT
  before insert on T_SYS_EXPORT
  for each row
begin
  if(:new.TASK_ID is null or :new.TASK_ID = '') then
  select SEQ_T_SYS_EXPORT.nextval into :new.TASK_ID from dual;
  end if;
end TRIG_T_SYS_EXPORT;
/

spool off

exit;