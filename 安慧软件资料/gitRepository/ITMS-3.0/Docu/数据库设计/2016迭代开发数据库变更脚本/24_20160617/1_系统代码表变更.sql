set define off
spool 24_1_系统代码表变更.log

--修改code表，增加触发器
create or replace trigger Trigger_T_SYS_CODE
  before insert on t_sys_code
  for each row
declare
  -- local variables here
begin
  if(:new.code_id is null or :new.code_id = 0) then
  select seq_t_code.nextval into :new.code_id from dual;
  end if;
end Trigger_T_SYS_CODE;
/

--修改code表，code_no的长度
alter table T_SYS_CODE modify CODE_NO VARCHAR2(64);

spool off

exit;
