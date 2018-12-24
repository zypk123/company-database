------------------------------------------------------
-- Export file for user ITMS3                       --
-- Created by Administrator on 2015-12-16, 17:14:37 --
------------------------------------------------------

set define off
spool 函数与视图.log

prompt
prompt Creating view T_SYS_DEVICE_VIEW
prompt ===============================
prompt
create or replace force view itms3.t_sys_device_view as
select t.device_id,
       t.device_sys_model_id,
       r.function_code_lists,
       t.section_id_list,
       t.contract_id,
       v.contractor_id,
       v.contractor_name,
       t.device_sys_nbr,
       t.device_name,
       t.device_type,
       t.use_status_flag,
       t.site_id,
       t.org_id,
       t.collection_type,
       t.create_time
  from T_DEVICE_SYS t,
       (select t.contract_id,
               t.contract_nbr,
               t.contract_name,
               v.contractor_id,
               v.contractor_name
          from t_device_contract t, t_device_contractor v
         where t.contractor_id = v.contractor_id(+)) v,
       (select t.device_sys_model_id,
               wmsys.wm_concat(function_code) as function_code_lists
          from T_DEVICE_MODEL_FUNCTION t, t_device_sys_function v
         where t.device_sys_function_id = v.device_sys_function_id
         group by t.device_sys_model_id) r
 where t.contract_id = v.contract_id(+)
   and t.device_sys_model_id = r.device_sys_model_id(+);

prompt
prompt Creating view T_SYS_ORG_VIEW
prompt ============================
prompt
create or replace force view itms3.t_sys_org_view as
select a.org_id,
       a.org_code,
       a.org_name,
       a.level_hierarchy,
       a.collapse_hierarchy,
       case
         when a.level_hierarchy >= 3 then
          substr(a.collapse_hierarchy,
                 instr(a.collapse_hierarchy, '->', 1, 2) + 2,
                 12)
         else
          a.org_code
       end display_org_code
  from (select a.org_id,
               a.org_code,
               a.org_name,
               level level_hierarchy,
               sys_connect_by_path(org_code, '->') collapse_hierarchy
          from t_sys_org a
         start with a.parent_org_id is null
        connect by prior a.org_id = a.parent_org_id) a;

prompt
prompt Creating view T_SYS_SECTION_VIEW
prompt ================================
prompt
create or replace force view itms3.t_sys_section_view as
select t.section_id,
       v.site_code,
       v.site_name,
       t.direction_type,
       r.code_name as direction_type_name,
       t.direction_name,
       t.enter_or_exit_city,
       v.district_code,
       v.road_id,
       v.road_section_id,
       v.cross_id
  from t_sys_section t,
       t_sys_site v,
       (select t.code_no, t.code_name
          from t_sys_code t
         where t.code_type = '070') r
 where t.site_id = v.site_id(+)
   and t.direction_type = r.code_no(+);

prompt
prompt Creating view T_SYS_SITE_VIEW
prompt =============================
prompt
create or replace force view itms3.t_sys_site_view as
select t.site_id,
       t.site_code,
       t.site_name,
       t.site_address,
       v.org_code,
       v.org_privilege_code,
       t.district_code,
       r.road_code,
       t.device_num
  from (select t.org_id,
               v.district_code,
               v.road_id,
               t.site_id,
               v.site_code,
               v.site_name,
               v.site_address,
               count(*) as device_num
          from T_DEVICE_SYS t, t_sys_site v
         where t.collection_type is not null
           and t.site_id = v.site_id
         group by t.org_id,
                  v.district_code,
                  v.road_id,
                  t.site_id,
                  v.site_code,
                  v.site_name,v.site_address) t,
       t_sys_org v,
       t_sys_road r
 where t.org_id = v.org_id
   and t.road_id = r.road_id(+);

prompt
prompt Creating function FUNC_DISTRICT_FILTER
prompt ======================================
prompt
CREATE OR REPLACE FUNCTION ITMS3.FUNC_DISTRICT_FILTER(in_field IN VARCHAR2,
                                                in_param IN VARCHAR2)
/******************************************************************************
  * AUTHOR         -  lijie
  * CREATION DATE  -  2015-12-16
  * SERVICE NAME   -  LanDun
  *
  * PROCEDURE NAME :FUNC_DISTRICT_FILTER
  *
  * DESCRIPTION    :判断某区划的级别，返回前N个字符串
  *
  * Parameters :
  *  district_code        IN  参数
  ******************************************************************************
  * POSSIBLE ERROR CONDITIONS :
  ******************************************************************************
  * CHANGE LOG
  ******************************************************************************
  * CHANGE NUMBER:
  * DATE:
  * DEVELOPER:
  * DESCRIPTION:
  *****************************************************************************/
 RETURN VARCHAR2 AS
  v_length      integer;
  v_result      VARCHAR2(5);
  v_prm_code    NUMBER;
  v_prm_errtext VARCHAR2(200);
BEGIN
  IF in_param IS NOT NULL AND LENGTH(LTRIM(RTRIM(in_param))) > 0 AND
     in_param <> 'null' AND in_param <> 'NULL' THEN
    v_length := length(RTRIM(in_param, '0'));
    IF (MOD(v_result, 2) != 0) THEN
      v_length := v_length + 1;
    END IF;
    if (substr(in_field, 0, v_length) = substr(in_param, 0, v_length)) then
      v_result := 'true';
    else
      v_result := 'false';
    end if;
  ELSE
    v_result := 'false';
  END IF;
  RETURN v_result;
EXCEPTION
  WHEN OTHERS THEN
    v_prm_code    := SQLCODE;
    v_prm_errtext := SQLERRM;
    DBMS_OUTPUT.put_line('this error message is from function FUNC_VARCHAR_FILTER.');
    DBMS_OUTPUT.put_line('SQLCODE: ' || v_prm_code);
    DBMS_OUTPUT.put_line('SQLERRM: ' || v_prm_errtext);
    RETURN null;
END;
/

prompt
prompt Creating function FUNC_FUNTION_FILTER
prompt =====================================
prompt
CREATE OR REPLACE FUNCTION ITMS3.FUNC_FUNTION_FILTER(in_code_lists IN VARCHAR2,
                                               in_code  IN VARCHAR2)
/******************************************************************************
  * AUTHOR         -  lijie
  * CREATION DATE  -  2015-11-25
  * SERVICE NAME   -  LanDun
  *
  * PROCEDURE NAME :FUNC_FUNTION_FILTER
  *
  * DESCRIPTION    :判断某型号设备是否包含某功能，并返回结果
  *
  * Parameters :
  *  in_funtion_lists        IN  字段
  *  in_function        IN  参数
  ******************************************************************************
  * POSSIBLE ERROR CONDITIONS :
  ******************************************************************************
  * CHANGE LOG
  ******************************************************************************
  * CHANGE NUMBER:
  * DATE:
  * DEVELOPER:
  * DESCRIPTION:
  *****************************************************************************/
 RETURN VARCHAR2 AS
  v_value       varchar2(1);
  v_result      VARCHAR2(5);
  v_prm_code    NUMBER;
  v_prm_errtext VARCHAR2(200);
BEGIN
  IF in_code IS NOT NULL AND
     LENGTH(LTRIM(RTRIM(in_code))) > 0 AND
     in_code <> 'null' AND in_code <> 'NULL' THEN
    IF in_code = in_code_lists THEN
      v_result := 'true';
    ELSE
      SELECT COUNT(*)
        INTO v_value
        FROM dual
       WHERE in_code IN
             (SELECT regexp_substr(function_code, '[^,]+', 1, rownum) AS function_code
                FROM (SELECT in_code_lists function_code FROM dual)
              CONNECT BY rownum <=
                         length(function_code) - length(REPLACE(function_code, ',')) + 1);
      IF (v_value > 0) THEN
        v_result := 'true';
      ELSE
        v_result := 'false';
      END IF;
    END IF;
  ELSE
    v_result := 'true';
  END IF;

  RETURN v_result;
EXCEPTION
  WHEN OTHERS THEN
    v_prm_code    := SQLCODE;
    v_prm_errtext := SQLERRM;
    DBMS_OUTPUT.put_line('this error message is from function FUNC_VARCHAR_FILTER.');
    DBMS_OUTPUT.put_line('SQLCODE: ' || v_prm_code);
    DBMS_OUTPUT.put_line('SQLERRM: ' || v_prm_errtext);
    RETURN null;
END;
/

prompt
prompt Creating function FUNC_GET_DISTRICT_CODE
prompt ========================================
prompt
CREATE OR REPLACE FUNCTION ITMS3.FUNC_GET_DISTRICT_CODE(p_list in varchar2,
                                                   p_del  in varchar2)
  RETURN VARCHAR2 AS
  v_idx    pls_integer;
  v_str    VARCHAR2(400);
  v_return VARCHAR2(2000);
  v_list   varchar2(512);
  v_code   varchar2(2000);

  /* add by lijie 字符串处理 把字符串转换成符合In条件*/
BEGIN
  IF p_list IS NULL OR LENGTH(LTRIM(RTRIM(p_list))) = 0 THEN
    RETURN NULL;
  END IF;

  v_list := p_list;
  loop
    v_idx := instr(v_list, p_del);
    if v_idx > 0 then
      --如果不止一个区划编码，依次循环每个区划编码
      v_str := substr(v_list, 1, v_idx - 1);
      if (substr(v_str, 5, 2) = '00') then--如果该区划有子区域，查询所有子区域返回字符串，如果没有，返回此区划字符串
        --如果该节点是市级节点（也有可能省级节点）
        SELECT WMSYS.WM_CONCAT(T.DISTRICT_CODE)
          into v_code
          FROM T_SYS_DISTRICT T
         WHERE substr(T.DISTRICT_CODE,
                      0,
                      length(rtrim(v_str, '0')) +
                      mod(length(rtrim(v_str, '0')), 2)) =
               substr(v_str,
                      0,
                      length(rtrim(v_str, '0')) +
                      mod(length(rtrim(v_str, '0')), 2));
      else
        v_code := v_str;
      end if;
      v_return := v_return ||  v_code || ',';
      v_list   := substr(v_list, v_idx + length(p_del));
    else
      v_str := v_list;
      if (substr(v_str, 5, 2) = '00') then
        --如果该节点是市级节点（也有可能省级节点）
        SELECT WMSYS.WM_CONCAT(T.DISTRICT_CODE)
          into v_code
          FROM T_SYS_DISTRICT T
         WHERE substr(T.DISTRICT_CODE,
                      0,
                      length(rtrim(v_str, '0')) +
                      mod(length(rtrim(v_str, '0')), 2)) =
               substr(v_str,
                      0,
                      length(rtrim(v_str, '0')) +
                      mod(length(rtrim(v_str, '0')), 2));
      else
        v_code := v_str;
      end if;
      v_return := v_return || v_code;
      exit;
    end if;
  end loop;
  RETURN v_return;--返回所有已查询到区划编码
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.put_line('SQLCODE: ' || SQLCODE);
    DBMS_OUTPUT.put_line('SQLERRM: ' || SQLERRM);
    RETURN NULL;
END FUNC_GET_DISTRICT_CODE;
/

prompt
prompt Creating function FUNC_GET_TCODE_NAME
prompt =====================================
prompt
CREATE OR REPLACE FUNCTION ITMS3.FUNC_GET_TCODE_NAME(IN_CODE_NO   IN VARCHAR2,
                                               IN_CODE_TYPE IN VARCHAR2)
  /******************************************************************************
  * AUTHOR         -  lijie
  * CREATION DATE  -  2015-12-15
  * SERVICE NAME   -  LanDun
  *
  * PROCEDURE NAME :FUNC_VARCHAR_FILTER
  *
  * DESCRIPTION    :根据代码类型，代码编号获取代码名称，并返回结果
  *
  * Parameters :
  *  in_field        IN  字段
  *  in_param        IN  参数
  ******************************************************************************
  * POSSIBLE ERROR CONDITIONS :
  ******************************************************************************
  * CHANGE LOG
  ******************************************************************************
  * CHANGE NUMBER:
  * DATE:
  * DEVELOPER:
  * DESCRIPTION:
  *****************************************************************************/
  RETURN VARCHAR2 AS
  V_NAME VARCHAR2(256);
BEGIN
  SELECT T.CODE_NAME
    INTO V_NAME
    FROM T_SYS_CODE T
   WHERE T.CODE_TYPE = IN_CODE_TYPE
     AND T.CODE_NO = IN_CODE_NO;
  RETURN V_NAME;
EXCEPTION
  WHEN OTHERS THEN
    RETURN NULL;
END;
/

prompt
prompt Creating function FUNC_VARCHAR_FILTER
prompt =====================================
prompt
CREATE OR REPLACE FUNCTION ITMS3.FUNC_VARCHAR_FILTER(in_field  IN VARCHAR2,
                                               in_param  IN VARCHAR2)
/******************************************************************************
  * AUTHOR         -  Xujin.Jiao
  * CREATION DATE  -  2011-07-22
  * SERVICE NAME   -  LanDun
  *
  * PROCEDURE NAME :FUNC_VARCHAR_FILTER
  *
  * DESCRIPTION    :比较字段的值与参数是否相等，并返回结果
  *
  * Parameters :
  *  in_field        IN  字段
  *  in_param        IN  参数
  ******************************************************************************
  * POSSIBLE ERROR CONDITIONS :
  ******************************************************************************
  * CHANGE LOG
  ******************************************************************************
  * CHANGE NUMBER:
  * DATE:
  * DEVELOPER:
  * DESCRIPTION:
  *****************************************************************************/
  RETURN VARCHAR2 AS
  v_result              VARCHAR2(5);
  v_prm_code            NUMBER;
  v_prm_errtext         VARCHAR2(200);
BEGIN

  IF in_param IS NOT NULL AND LENGTH(LTRIM(RTRIM(in_param))) > 0 AND in_param <> 'null' AND in_param <> 'NULL' THEN
    IF in_param = in_field THEN
      v_result := 'true';
    ELSE
      v_result := 'false';
    END IF;
  ELSE
    v_result := 'true';
  END IF;

  RETURN v_result;
EXCEPTION
  WHEN OTHERS THEN
     v_prm_code    := SQLCODE;
     v_prm_errtext := SQLERRM;
     DBMS_OUTPUT.put_line('this error message is from function FUNC_VARCHAR_FILTER.');
     DBMS_OUTPUT.put_line('SQLCODE: ' || v_prm_code);
     DBMS_OUTPUT.put_line('SQLERRM: ' || v_prm_errtext);
     RETURN null;
END;
/

prompt
prompt Creating procedure DEVICE_UNUSUAL_STATUS_TIME
prompt =============================================
prompt
CREATE OR REPLACE PROCEDURE ITMS3.DEVICE_UNUSUAL_STATUS_TIME(p_tjDay in date) IS
  v_fault_sub_time_list FAULT_SUB_TIME_TABLE := FAULT_SUB_TIME_TABLE();
  CURSOR C1 is--查询该日设备故障时间
  select t.device_id, t.start_time, t.end_time, t.duration_secs as DURATION
          from t_device_status_log t
         where t.start_time >= p_tjDay and t.start_time<p_tjDay+1
           and t.status_type in ('2', '3');
           
  CURSOR C2 IS--查询每日小时故障，故障时长
    SELECT device_id,trunc(begintime,'HH')begintime,trunc(begintime,'HH')+1/24 endtime,sum(duration) duration
      FROM TABLE(CAST(v_fault_sub_time_list AS FAULT_SUB_TIME_TABLE))
     group by device_id, trunc(BEGINTIME,'HH')
     order by device_id,BEGINTIME;
     
  CURSOR C3 IS--查询每日故障，故障时长
    SELECT device_id,trunc(begintime)begintime,trunc(begintime) endtime,sum(duration) duration
      FROM TABLE(CAST(v_fault_sub_time_list AS FAULT_SUB_TIME_TABLE))
     group by device_id, trunc(BEGINTIME)
     order by device_id,BEGINTIME; 
     
BEGIN
    for r in C1 loop--找出每个设备故障时段每小时的故障记录，把一条故障记录拆分成几条，然后放到表类型里面去
      for m in (select r.device_id,r.begintime,r.endtime,(r.endtime-r.begintime)*86400 as duration from(select r.device_id,
                       case when trunc(r.start_time,'HH') + (rownum - 1) / 24<r.start_time then r.start_time else trunc(r.start_time,'HH') + (rownum - 1) / 24  end as BEGINTIME,
                       case when r.end_time<trunc(r.start_time,'HH')+rownum/24 then r.end_time else trunc(r.start_time,'HH')+rownum/24  end as ENDTIME
                  from DUAL
                connect by rownum <= (trunc(r.end_time,'HH') - trunc(r.start_time,'HH')) * 24 + 1) r ) loop
        v_fault_sub_time_list.EXTEND;
        v_fault_sub_time_list(v_fault_sub_time_list.last) := fault_sub_time_obj(m.device_id,
                                                                                m.begintime,
                                                                                m.endtime,
                                                                                m.duration);
      end loop;
    end loop;
    for r in C2 loop
      insert into x_d_d_unusual_time
        (log_id, device_id,start_time, end_time, duration_secs)
      values
        (seq_default.nextval,
         r.device_id,
         r.BEGINTIME,
         r.ENDTIME,
         r.duration);
      commit;
      end loop;
       for r in C3 loop
      insert into x_d_m_unusual_time
        (log_id, device_id, start_time, end_time, duration_secs)
      values
        (seq_default.nextval,
         r.device_id,
         r.BEGINTIME,
         r.ENDTIME,
         r.duration);
      commit;
      end loop;
END;
/


spool off
