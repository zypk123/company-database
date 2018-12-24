package cy.its.service.device.statusChecker.data;

import java.util.ArrayList;
import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.device.statusChecker.model.StatusLog;

public class StatusLogWriter extends Updater<StatusLog> {

	static StatusLogWriter inserter = new StatusLogWriter();

	StatusLogWriter(){
		super("状态日志入库更新处理", "StatusLog", StatusLog.class);
	}
	
	public static void insert(StatusLog statusLog) {
		inserter.write(statusLog);
	}
	
	public static void stop() {
		inserter.close();
	}
	

	@Override
	String getSql() {
		StringBuilder sb = new StringBuilder();
		String line = System.getProperty("line.separator", "\n");
		
		sb.append("DECLARE                                                        ").append(line);
		sb.append("  logCount        number;                                      ").append(line);
		sb.append("  v$device_id     t_device_status_log.device_id%type := ?;     ").append(line);
		sb.append("  v$status_type   t_device_status_log.status_type%type := ?;   ").append(line);
		sb.append("  v$start_time    t_device_status_log.start_time%type := ?;    ").append(line);
		sb.append("  v$end_time      t_device_status_log.end_time%type := ?;      ").append(line);
		sb.append("BEGIN                                                          ").append(line);
		sb.append("  SELECT count(*)                                              ").append(line);
		sb.append("    into logCount                                              ").append(line);
		sb.append("    FROM t_device_status_log                                   ").append(line);
		sb.append("   WHERE device_id = v$device_id                               ").append(line);
		sb.append("     AND start_time = v$start_time                             ").append(line);
		sb.append("     AND rownum = 1;                                           ").append(line);
		sb.append("                                                               ").append(line);
		sb.append("  If logCount = 0 then                                         ").append(line);
		sb.append("    insert into t_device_status_log                            ").append(line);
		sb.append("      (satus_log_id,                                           ").append(line);
		sb.append("       device_id,                                              ").append(line);
		sb.append("       status_type,                                            ").append(line);
		sb.append("       start_time,                                             ").append(line);
		sb.append("       end_time,                                               ").append(line);
		sb.append("       duration_secs)                                          ").append(line);
		sb.append("    values                                                     ").append(line);
		sb.append("      (SEQ_DEFAULT.NEXTVAL,                                    ").append(line);
		sb.append("       v$device_id,                                            ").append(line);
		sb.append("       v$status_type,                                          ").append(line);
		sb.append("       v$start_time,                                           ").append(line);
		sb.append("       v$end_time,                                             ").append(line);
		sb.append("       (v$end_time - v$start_time) * 86400);                   ").append(line);
		sb.append("  else                                                         ").append(line);
		sb.append("    update t_device_status_log                                 ").append(line);
		sb.append("       set status_type   = v$status_type,                      ").append(line);
		sb.append("           end_time      = v$end_time,                         ").append(line);
		sb.append("           duration_secs = (v$end_time - v$start_time) * 86400 ").append(line);
		sb.append("     where device_id = v$device_id                             ").append(line);
		sb.append("       and start_time = v$start_time;                          ").append(line);
		sb.append("                                                               ").append(line);
		sb.append("  end if;                                                      ").append(line);
		sb.append("                                                               ").append(line);
		sb.append("  commit;                                                      ").append(line);
		sb.append("END;                                                           ").append(line);
	
		return sb.toString();
	}

	@Override
	List<Object> getParamters(StatusLog t) {
		List<Object> lstObj = new ArrayList<Object>(ConstValue.INT_4);
		lstObj.add(t.deviceId);
		lstObj.add(t.statusType);
		lstObj.add(t.startTime);
		lstObj.add(t.endTime);

		return lstObj;
	}
	
	
	
	
	

//sb.append("declare                                                     ").append(line);
//sb.append("  a    date := sysdate;                                     ").append(line);
//sb.append("  b    date := sysdate + 10 / 24;                           ").append(line);
//sb.append("  tmp0 date;                                                ").append(line);
//sb.append("  tmp1 date;                                                ").append(line);
//sb.append("  v0   date;                                                ").append(line);
//sb.append("  v1   date;                                                ").append(line);
//sb.append("  tag  integer := 0;                                        ").append(line);
//sb.append("begin                                                       ").append(line);
//sb.append("  tmp0 := trunc(a, 'dd');                                   ").append(line);
//sb.append("  tmp1 := tmp0 + 86399 / 86400;                             ").append(line);
//sb.append("  loop                                                      ").append(line);
//sb.append("    if a >= tmp0 and b <= tmp1 then                         ").append(line);
//sb.append("      v0  := a;                                             ").append(line);
//sb.append("      v1  := b;                                             ").append(line);
//sb.append("      tag := 1;                                             ").append(line);
//sb.append("    elsif a >= tmp0 and a <= tmp1 then                      ").append(line);
//sb.append("      v0 := a;                                              ").append(line);
//sb.append("      v1 := tmp1;                                           ").append(line);
//sb.append("    elsif b >= tmp0 and b <= tmp1 then                      ").append(line);
//sb.append("      v0  := tmp0;                                          ").append(line);
//sb.append("      v1  := b;                                             ").append(line);
//sb.append("      tag := 1;                                             ").append(line);
//sb.append("    else                                                    ").append(line);
//sb.append("      v0 := tmp0;                                           ").append(line);
//sb.append("      v1 := tmp1;                                           ").append(line);
//sb.append("    end if;                                                 ").append(line);
//sb.append("                                                            ").append(line);
//sb.append("    update t_device_status_log                              ").append(line);
//sb.append("       set status_type = ?, end_time = ?, duration_secs = ? ").append(line);
//sb.append("     where device_id = ?                                    ").append(line);
//sb.append("       and start_time = ?;                                  ").append(line);
//sb.append("                                                            ").append(line);
//sb.append("    If sql%rowcount = 0 then                                ").append(line);
//sb.append("      insert into t_device_status_log                       ").append(line);
//sb.append("        (satus_log_id,                                      ").append(line);
//sb.append("         device_id,                                         ").append(line);
//sb.append("         status_type,                                       ").append(line);
//sb.append("         start_time,                                        ").append(line);
//sb.append("         end_time,                                          ").append(line);
//sb.append("         duration_secs)                                     ").append(line);
//sb.append("      values                                                ").append(line);
//sb.append("        (SEQ_DEFAULT.NEXTVAL, ?, ?, ?, ?, ?);               ").append(line);
//sb.append("    end if;                                                 ").append(line);
//sb.append("                                                            ").append(line);
//sb.append("    exit when tag = 1;                                      ").append(line);
//sb.append("    tmp0 := tmp0 + 1;                                       ").append(line);
//sb.append("    tmp1 := tmp1 + 1;                                       ").append(line);
//sb.append("  end loop;                                                 ").append(line);
//sb.append("  dbms_output.put_line('OOO');                              ").append(line);
//sb.append("end;                                                        ").append(line);
//sb.append("                                                            ").append(line);

}
