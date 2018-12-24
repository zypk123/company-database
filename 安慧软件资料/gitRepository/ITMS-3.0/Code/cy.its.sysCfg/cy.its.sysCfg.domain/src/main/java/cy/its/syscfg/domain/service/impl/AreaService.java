package cy.its.syscfg.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.model.traffic.SysArea;
import cy.its.syscfg.domain.repository.traffic.IAreaRepository;
import cy.its.syscfg.domain.service.IAreaService;

@Service
public class AreaService implements IAreaService {
	@Autowired
	IAreaRepository repository;

	@Override
	public int deleteByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return this.repository.deleteByPrimaryKey(areaId);
	}

	@Override
	public int insert(SysArea record) {
		// TODO Auto-generated method stub
		return this.repository.insert(record);
	}

	@Override
	public int insertSelective(SysArea record) {
		// TODO Auto-generated method stub
		return this.repository.insertSelective(record);
	}

	@Override
	public SysArea selectByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return this.repository.selectByPrimaryKey(areaId);
	}

	@Override
	public int updateByPrimaryKeySelective(SysArea record) {
		// TODO Auto-generated method stub
		return this.repository.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysArea record) {
		// TODO Auto-generated method stub
		return this.repository.updateByPrimaryKey(record);
	}

	@Override
	public List<SysArea> selectAreaList(SysArea record) {
		// TODO Auto-generated method stub
		return this.repository.selectAreaList(record);
	}

}
