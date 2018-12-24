package cy.its.road.repository.highway;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.road.domain.criteria.SpecialSectionCriteria;
import cy.its.road.domain.model.highway.SpecialSection;
import cy.its.road.domain.repository.highway.ISpecialSectionRepository;
import cy.its.road.mybatis.client.SpecialSectionMapper;

@Service
public class SpecialSectionRepository implements ISpecialSectionRepository {

	@Autowired
	SpecialSectionMapper specialSectionMapper;

	public SpecialSection aggregateOfId(String id) throws Exception {
		return specialSectionMapper.selectByPrimaryKey(id);
	}

	public String save(SpecialSection obj) {
		obj.setSpecialSectionId(StringUtil.generateUUID());
		specialSectionMapper.insertSelective(obj);
		return obj.getSpecialSectionId();
	}

	public int delete(String id) {
		return specialSectionMapper.deleteByPrimaryKey(id);
	}

	public int update(SpecialSection obj) {
		return specialSectionMapper.updateByPrimaryKeySelective(obj);
	}

	public List<SpecialSection> findHighwaySpecialSections(
			SpecialSectionCriteria specialSectionCriteria) {
		return specialSectionMapper.selectSpecialSections(ParamUtil
				.parseParams(specialSectionCriteria));
	}

	@Override
	public int deleteByRoadId(String roadId) {
		return specialSectionMapper.deleteByRoadId(roadId);
	}

	@Override
	public int countHighwaySpecialSections(
			SpecialSectionCriteria specialSectionCriteria) {
		return specialSectionMapper.countSpecialSections(ParamUtil
				.parseParams(specialSectionCriteria));
	}
}
