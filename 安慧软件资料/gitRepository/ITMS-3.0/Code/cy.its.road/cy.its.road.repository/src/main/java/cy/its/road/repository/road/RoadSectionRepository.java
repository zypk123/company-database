package cy.its.road.repository.road;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;

import cy.its.road.domain.criteria.RoadSectionCriteria;
import cy.its.road.domain.model.road.RoadSection;
import cy.its.road.domain.repository.road.IRoadSectionRepository;
import cy.its.road.mybatis.client.RoadSectionMapper;

@Service
public class RoadSectionRepository implements IRoadSectionRepository {
	
	@Autowired
	RoadSectionMapper roadSectionMapper;
	
	public RoadSection aggregateOfId(String id) throws Exception {
		return roadSectionMapper.selectByPrimaryKey(id);
	}

	public String save(RoadSection obj) {
		obj.setRoadSectionId(StringUtil.generateUUID());
		roadSectionMapper.insertSelective(obj);
		return obj.getRoadSectionId();
	}

	public int delete(String id) {
		return roadSectionMapper.deleteByPrimaryKey(id);
	}
	public int remove(Map<String,Object> roadSectionIds){
		return roadSectionMapper.removeByPrimaryKey(roadSectionIds);
	}
	public int update(RoadSection obj) {
		return roadSectionMapper.updateByPrimaryKey(obj);
	}

	public List<RoadSection> findRoadSections(
		RoadSectionCriteria roadSectionCriteria) {
		PageHelper.startPage(roadSectionCriteria.getPageNum(), roadSectionCriteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(roadSectionCriteria.getOrderName())){
			PageHelper.orderBy(roadSectionCriteria.getOrderName() + " " + roadSectionCriteria.getOrderType());
		}
		Page<RoadSection> page = (Page<RoadSection>) roadSectionMapper.selectRoadSections(ParamUtil.parseParams(roadSectionCriteria));
		roadSectionCriteria.setTotal(page.getTotal());
		return page.getResult();
//		return roadSectionMapper.selectRoadSections(
//				ParamUtil.parseParams(roadSectionCriteria));
	}

	@Override
	public int countRoadSections(RoadSectionCriteria roadSectionCriteria) {
		return roadSectionMapper.countRoadSections(
				ParamUtil.parseParams(roadSectionCriteria));
	}
}
