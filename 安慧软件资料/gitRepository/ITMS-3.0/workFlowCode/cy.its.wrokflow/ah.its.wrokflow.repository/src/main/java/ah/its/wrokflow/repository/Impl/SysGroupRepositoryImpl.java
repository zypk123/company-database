package ah.its.wrokflow.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.SysGroupRepositoryI;
import ah.its.wrokflow.repository.dao.SysGroup;
import ah.its.wrokflow.repository.domain.SysGroupMapper;

@Service
public class SysGroupRepositoryImpl implements SysGroupRepositoryI {
	@Autowired
	private SysGroupMapper sysGroupMapper;
	
	@Override
	public List<SysGroup> querygroups(SysGroup record) {
		return sysGroupMapper.querygroups(record);
	}
}