package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.SysGroupDto;
import ah.its.wrokflow.repository.dao.SysGroup;

public interface SysGroupActionI {
	public Map queryGroups(SysGroupDto groupDto);
}
