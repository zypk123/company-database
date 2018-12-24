------------------------------------------------------
-- Export file for user ITMS3                       --
-- Created by Administrator on 2015-12-22, 16:49:15 --
------------------------------------------------------

set define off
spool ´¥·¢Æ÷.log

prompt
prompt Creating sequence SEQ_X_F_D_FLOW
prompt ================================
prompt
create sequence ITMS3.SEQ_X_F_D_FLOW
minvalue 1
maxvalue 99999999999999999999
start with 8101
increment by 1
cache 20;

prompt
prompt Creating sequence SEQ_X_F_H_FLOW
prompt ================================
prompt
create sequence ITMS3.SEQ_X_F_H_FLOW
minvalue 1
maxvalue 99999999999999999999
start with 86361
increment by 1
cache 20;

prompt
prompt Creating trigger TRIG_X_P_H_PASS
prompt ================================
prompt
create or replace trigger itms3.TRIG_X_P_H_PASS
  before insert on X_P_H_PASS
  for each row
begin
  if (:new.vehicle_seq_id is null or :new.vehicle_seq_id = '') then
    select seq_x_p_h_pass.nextval into :new.vehicle_seq_id from dual;
  end if;
end TRIG_X_P_H_PASS;
/

prompt
prompt Creating trigger TRIG_X_P_H_PASS_QTR
prompt ====================================
prompt
create or replace trigger itms3.TRIG_X_P_H_PASS_QTR
  before insert on X_P_H_PASS_QTR
  for each row
begin
  if (:new.vehicle_seq_id is null or :new.vehicle_seq_id = '') then
    select seq_x_p_h_pass_qtr.nextval into :new.vehicle_seq_id from dual;
  end if;
end TRIG_X_P_H_PASS_QTR;
/

prompt
prompt Creating trigger TRIG_X_P_S_D_PASS
prompt ==================================
prompt
create or replace trigger itms3.TRIG_X_P_S_D_PASS
  before insert on X_P_S_D_PASS
  for each row
begin
  if (:new.vehicle_seq_id is null or :new.vehicle_seq_id = '') then
    select seq_x_p_s_d_pass.nextval into :new.vehicle_seq_id from dual;
  end if;
end TRIG_X_P_S_D_PASS;
/


spool off
