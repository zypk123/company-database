package cy.its.road.repository.region;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.RegionCriteria;
import cy.its.road.domain.model.region.Region;
import cy.its.road.domain.repository.region.IRegionRepository;
import cy.its.road.mybatis.client.RegionMapper;

@Service
public class RegionRepository implements IRegionRepository {

	@Autowired
	RegionMapper regionMapper;

	public Region aggregateOfId(String id) throws Exception {
		return regionMapper.selectByPrimaryKey(id);
	}

	public String save(Region obj) {
		obj.setRegionalId(StringUtil.generateUUID());
		regionMapper.insertSelective(obj);
		return obj.getRegionalId();
	}

	public int delete(String id) {
		return regionMapper.deleteByPrimaryKey(id);
	}
	
	public int remove(Map<String,Object> regionIds){
		return regionMapper.removeByPrimaryKey(regionIds);
	}
	public int update(Region obj) {
		return regionMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Region> findRegions(RegionCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Map<String,Object> map = parseRegionParams(criteria);
		Page<Region> page = (Page<Region>) regionMapper.selectRegions(map);
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}

	@Override
	public int countRegions(RegionCriteria criteria) {
		return regionMapper.countRegions(parseRegionParams(criteria));
	}
	
	protected Map<String, Object> parseRegionParams(RegionCriteria criteria) {
		if (!StringUtil.isNullOrEmpty(criteria.districtCode)) {
			String districtCode = StringUtil.trimEnd(criteria.districtCode, '0');
			if (districtCode.length() % 2 != 0) {
				districtCode = districtCode + "0";
			}
			criteria.districtCode = districtCode;
		}
		return ParamUtil.parseParams(criteria);
	}
}
