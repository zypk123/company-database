package ah.its.wrokflow.domain.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.SysGroupDomainI;
import ah.its.wrokflow.repository.SysGroupRepositoryI;
import ah.its.wrokflow.repository.dao.SysGroup;

@Service
public class SysGroupDomainImpl implements SysGroupDomainI {
	@Autowired
	private SysGroupRepositoryI sysGroupRepository;
	
	@Override
	public List querygroups(SysGroup record) {
		List<SysGroup> list=sysGroupRepository.querygroups(record);
		return list;
	}
}
