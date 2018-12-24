package cy.its.syscfg.repository.traffic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import cy.its.syscfg.domain.model.traffic.SysArea;
import cy.its.syscfg.domain.repository.traffic.IAreaRepository;
import cy.its.syscfg.mybatis.client.SysAreaMapper;

@Repository
public class AreaRepository implements IAreaRepository{
	@Autowired
	SysAreaMapper mapper;

	@Override
	public int deleteByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return this.mapper.deleteByPrimaryKey(areaId);
	}

	@Override
	public int insert(SysArea record) {
		// TODO Auto-generated method stub
		return mapper.insert(record);
	}

	@Override
	public int insertSelective(SysArea record) {
		// TODO Auto-generated method stub
		return this.mapper.insertSelective(record);
	}

	@Override
	public SysArea selectByPrimaryKey(String areaId) {
		// TODO Auto-generated method stub
		return this.mapper.selectByPrimaryKey(areaId);
	}

	@Override
	public int updateByPrimaryKeySelective(SysArea record) {
		// TODO Auto-generated method stub
		return this.mapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(SysArea record) {
		// TODO Auto-generated method stub
		return this.mapper.updateByPrimaryKey(record);
	}
	public List<SysArea> selectAreaList(SysArea record){
		return this.mapper.selectAreaList(record);
	}
	

}
