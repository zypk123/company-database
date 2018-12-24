package cy.its.service.device.faultMaintain;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.dataAccess.DiscardedDbError;
import cy.its.service.common.dataModel.DeviceFault;
import cy.its.service.common.log.LogManager;

public class FaultWriter {

	/**
	 * 故障未处理
	 */
	final static String UN_PROCESS = "0";

	/**
	 * 处理故障
	 * 
	 * @param fault
	 * @throws Exception
	 */
	public static boolean write(DeviceFault fault) {
		try {
			DbExecuter.executeNonQuery(SQL, UN_PROCESS, fault.getFaultEventTime(), fault.getDeviceId(),
					fault.getSysComponentId(), fault.getFaultAlarm(), fault.getFaultType(), fault.getFaultSubType(),
					fault.getCollectMethod(), fault.getRemark(), fault.getOrgPrivilegeCode());
			
			return ConstValue.BOOL_TRUE;
		} catch (Throwable e) {
			if (DiscardedDbError.isDiscardedError(e)) {
//				LogManager.error("故障处理失败,丢弃故障, 失败原因: " + StringUtil.getErrorDetail(e));
				LogManager.error("故障处理失败,原因: " + StringUtil.getErrorDetail(e));
				return ConstValue.BOOL_TRUE;
			}
			
//			LogManager.error("故障处理失败,保留故障等待下次处理, 失败原因: " + StringUtil.getErrorDetail(e));
			LogManager.error("故障处理失败,原因: " + StringUtil.getErrorDetail(e));
			return ConstValue.BOOL_FALSE;
		}
	}

	private static String SQL;

	static {
		StringBuilder sb = new StringBuilder();
		String line = System.getProperty("line.separator", "\n");
		sb.append("DECLARE                                                                      ").append(line);
		sb.append("  HAS_DATA           INTEGER := 1;                                           ").append(line);
		sb.append("  NOT_PROCESS        VARCHAR2(1) := ?;                                       ").append(line);
		sb.append("  V_FAULT_EVENT_TIME T_DEVICE_FAULT_LOG.FAULT_EVENT_TIME%TYPE := ?;          ").append(line);
		sb.append("  V_DEVICE_ID        T_DEVICE_FAULT.DEVICE_ID%TYPE := ?;                     ").append(line);
		sb.append("  V_SYS_COMPONENT_ID T_DEVICE_FAULT.SYS_COMPONENT_ID%TYPE := ?;              ").append(line);
		sb.append("  V_FAULT_ALARM      T_DEVICE_FAULT.FAULT_ALARM%TYPE := ?;                   ").append(line);
		sb.append("  V_FAULT_TYPE       T_DEVICE_FAULT.FAULT_TYPE%TYPE := ?;                    ").append(line);
		sb.append("  V_FAULT_SUB_TYPE   T_DEVICE_FAULT.FAULT_SUB_TYPE%TYPE := ?;                ").append(line);
		sb.append("  V_COLLECT_METHOD   T_DEVICE_FAULT.COLLECT_METHOD%TYPE := ?;                ").append(line);
		sb.append("  V_REMARK           T_DEVICE_FAULT.REMARK%TYPE := ?;                        ").append(line);
		sb.append("  V_ORG_PRIVILEGE_CODE T_DEVICE_FAULT.ORG_PRIVILEGE_CODE%TYPE := ?;          ").append(line);

		sb.append("                                                                             ").append(line);
		sb.append("  TEMP_START_TIME T_DEVICE_FAULT.START_TIME%TYPE;                            ").append(line);
		sb.append("  TEMP_END_TIME   T_DEVICE_FAULT.END_TIME%TYPE;                              ").append(line);
		sb.append("  TEMP_FAULT_ID   T_DEVICE_FAULT.FAULT_ID%TYPE;                              ").append(line);
		sb.append("BEGIN                                                                        ").append(line);
		sb.append("  BEGIN                                                                      ").append(line);
		sb.append("    IF V_SYS_COMPONENT_ID IS NULL THEN                                       ").append(line);
		// sb.append(" /*查询故障表*/ ").append(line);
		sb.append("      SELECT F.FAULT_ID, F.START_TIME, F.END_TIME                            ").append(line);
		sb.append("        INTO TEMP_FAULT_ID, TEMP_START_TIME, TEMP_END_TIME                   ").append(line);
		sb.append("        FROM T_DEVICE_FAULT F                                                ").append(line);
		sb.append("       WHERE F.DEVICE_ID = V_DEVICE_ID                                       ").append(line);
		sb.append("         AND F.FAULT_SUB_TYPE = V_FAULT_SUB_TYPE                             ").append(line);
		sb.append("         AND F.PROCESS_STATE = NOT_PROCESS                                   ").append(line);
		sb.append("         AND ROWNUM = 1;                                                     ").append(line);
		sb.append("    ELSE                                                                     ").append(line);
		sb.append("      SELECT F.FAULT_ID, F.START_TIME, F.END_TIME                            ").append(line);
		sb.append("        INTO TEMP_FAULT_ID, TEMP_START_TIME, TEMP_END_TIME                   ").append(line);
		sb.append("        FROM T_DEVICE_FAULT F                                                ").append(line);
		sb.append("       WHERE F.DEVICE_ID = V_DEVICE_ID                                       ").append(line);
		sb.append("         AND F.SYS_COMPONENT_ID = V_SYS_COMPONENT_ID                         ").append(line);
		sb.append("         AND F.FAULT_SUB_TYPE = V_FAULT_SUB_TYPE                             ").append(line);
		sb.append("         AND F.PROCESS_STATE = NOT_PROCESS                                   ").append(line);
		sb.append("         AND ROWNUM = 1;                                                     ").append(line);
		sb.append("    END IF;                                                                  ").append(line);
		sb.append("  EXCEPTION                                                                  ").append(line);
		sb.append("    WHEN NO_DATA_FOUND THEN                                                  ").append(line);
		sb.append("      HAS_DATA := 0;                                                         ").append(line);
		sb.append("                                                                             ").append(line);
		sb.append("  END;                                                                       ").append(line);
		sb.append("                                                                             ").append(line);
		sb.append("  IF HAS_DATA = 0 THEN                                                       ").append(line);
		// sb.append(" /*不存在故障*/ ").append(line);
		// sb.append(" /*生成故障主键*/ ").append(line);
		sb.append("    SELECT SEQ_DEFAULT.NEXTVAL INTO TEMP_FAULT_ID FROM DUAL;                 ").append(line);
		// sb.append(" /*创建故障*/ ").append(line);
		sb.append("    INSERT INTO T_DEVICE_FAULT                                               ").append(line);
		sb.append("      (FAULT_ID,                                                             ").append(line);
		sb.append("       DEVICE_ID,                                                            ").append(line);
		sb.append("       SYS_COMPONENT_ID,                                                     ").append(line);
		sb.append("       FAULT_ALARM,                                                          ").append(line);
		sb.append("       FAULT_TYPE,                                                           ").append(line);
		sb.append("       FAULT_SUB_TYPE,                                                       ").append(line);
		sb.append("       START_TIME,                                                           ").append(line);
		sb.append("       COLLECT_METHOD,                                                       ").append(line);
		sb.append("       REMARK,                                                               ").append(line);
		sb.append("       PROCESS_STATE,                                                        ").append(line);
		sb.append("       CREATE_TIME,                                                          ").append(line);
		sb.append("       ORG_PRIVILEGE_CODE,                                                   ").append(line);
		sb.append("       PROCESS_TIME)                                                         ").append(line);
		sb.append("    VALUES                                                                   ").append(line);
		sb.append("      (TEMP_FAULT_ID,                                                        ").append(line);
		sb.append("       V_DEVICE_ID,                                                          ").append(line);
		sb.append("       V_SYS_COMPONENT_ID,                                                   ").append(line);
		sb.append("       V_FAULT_ALARM,                                                        ").append(line);
		sb.append("       V_FAULT_TYPE,                                                         ").append(line);
		sb.append("       V_FAULT_SUB_TYPE,                                                     ").append(line);
		sb.append("       V_FAULT_EVENT_TIME,                                                   ").append(line);
		sb.append("       V_COLLECT_METHOD,                                                     ").append(line);
		sb.append("       V_REMARK,                                                             ").append(line);
		sb.append("       NOT_PROCESS,                                                          ").append(line);
		sb.append("       SYSDATE,                                                              ").append(line);
		sb.append("       V_ORG_PRIVILEGE_CODE,                                                 ").append(line);
		sb.append("       SYSDATE);                                                             ").append(line);
		sb.append("  ELSE                                                                       ").append(line);
		// sb.append(" /*存在故障*/ ").append(line);
		// sb.append(" /*更新故障开始时间和结束时间*/ ").append(line);
		sb.append("    IF TEMP_END_TIME IS NULL THEN                                            ").append(line);
		// sb.append(" /*结束时间为空*/ ").append(line);
		sb.append("      IF V_FAULT_EVENT_TIME < TEMP_START_TIME THEN                           ").append(line);
		// sb.append(" /*最新故障时间小于故障开始时间*/ ").append(line);
		sb.append("        UPDATE T_DEVICE_FAULT                                                ").append(line);
		sb.append("           SET START_TIME = V_FAULT_EVENT_TIME, END_TIME = TEMP_START_TIME   ").append(line);
		sb.append("         WHERE FAULT_ID = TEMP_FAULT_ID;                                     ").append(line);
		sb.append("      ELSE                                                                   ").append(line);
		// sb.append(" /*最新故障时间大于等于故障开始时间*/ ").append(line);
		sb.append("        UPDATE T_DEVICE_FAULT                                                ").append(line);
		sb.append("           SET END_TIME = V_FAULT_EVENT_TIME                                 ").append(line);
		sb.append("         WHERE FAULT_ID = TEMP_FAULT_ID;                                     ").append(line);
		sb.append("      END IF;                                                                ").append(line);
		sb.append("    ELSE                                                                     ").append(line);
		// sb.append(" /*结束时间不为空*/ ").append(line);
		sb.append("      IF V_FAULT_EVENT_TIME > TEMP_END_TIME THEN                             ").append(line);
		// sb.append(" /*最新故障时间大于故障结束时间*/ ").append(line);
		sb.append("        UPDATE T_DEVICE_FAULT                                                ").append(line);
		sb.append("           SET END_TIME = V_FAULT_EVENT_TIME                                 ").append(line);
		sb.append("         WHERE FAULT_ID = TEMP_FAULT_ID;                                     ").append(line);
		sb.append("      ELSIF V_FAULT_EVENT_TIME < TEMP_START_TIME THEN                        ").append(line);
		// sb.append(" /*最新故障时间小于故障结束时间*/ ").append(line);
		sb.append("        UPDATE T_DEVICE_FAULT                                                ").append(line);
		sb.append("           SET START_TIME = V_FAULT_EVENT_TIME                               ").append(line);
		sb.append("         WHERE FAULT_ID = TEMP_FAULT_ID;                                     ").append(line);
		sb.append("      END IF;                                                                ").append(line);
		sb.append("    END IF;                                                                  ").append(line);
		sb.append("  END IF;                                                                    ").append(line);
		// sb.append(" /*创建故障日志*/ ").append(line);
		sb.append("  INSERT INTO T_DEVICE_FAULT_LOG                                             ").append(line);
		sb.append("    (FAULT_EVENT_ID,                                                         ").append(line);
		sb.append("     FAULT_ID,                                                               ").append(line);
		sb.append("     DEVICE_ID,                                                              ").append(line);
		sb.append("     FAULT_EVENT_TIME,                                                       ").append(line);
		sb.append("     FAULT_ALARM,                                                            ").append(line);
		sb.append("     FAULT_TYPE,                                                             ").append(line);
		sb.append("     FAULT_SUB_TYPE,                                                         ").append(line);
		sb.append("     COLLECT_METHOD,                                                         ").append(line);
		sb.append("     REMARK,                                                                 ").append(line);
		sb.append("     ORG_PRIVILEGE_CODE,                                                     ").append(line);
		sb.append("     SYS_COMPONENT_ID)                                                       ").append(line);
		sb.append("  VALUES                                                                     ").append(line);
		sb.append("    (SEQ_DEFAULT.NEXTVAL,                                                    ").append(line);
		sb.append("     TEMP_FAULT_ID,                                                          ").append(line);
		sb.append("     V_DEVICE_ID,                                                            ").append(line);
		sb.append("     V_FAULT_EVENT_TIME,                                                     ").append(line);
		sb.append("     V_FAULT_ALARM,                                                          ").append(line);
		sb.append("     V_FAULT_TYPE,                                                           ").append(line);
		sb.append("     V_FAULT_SUB_TYPE,                                                       ").append(line);
		sb.append("     V_COLLECT_METHOD,                                                       ").append(line);
		sb.append("     V_REMARK,                                                               ").append(line);
		sb.append("     V_ORG_PRIVILEGE_CODE,                                                   ").append(line);
		sb.append("     V_SYS_COMPONENT_ID);                                                    ").append(line);
		sb.append("  COMMIT;                                                                    ").append(line);
		sb.append("END;                                                                         ").append(line);

		SQL = sb.toString();
	}

}
