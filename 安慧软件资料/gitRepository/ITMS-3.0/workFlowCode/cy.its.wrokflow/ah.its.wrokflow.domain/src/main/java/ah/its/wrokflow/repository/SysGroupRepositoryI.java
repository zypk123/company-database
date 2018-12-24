package ah.its.wrokflow.repository;

import java.util.List;

import ah.its.wrokflow.repository.dao.SysGroup;

public interface SysGroupRepositoryI {
	List<SysGroup> querygroups(SysGroup record);
}