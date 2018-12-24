package cy.its.device.repository.site;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.site.Section;
import cy.its.device.domain.repository.site.ISectionRepository;
import cy.its.device.mybatis.client.SectionMapper;

@Service
public class SectionRepository implements ISectionRepository {

	@Autowired
	SectionMapper sectionMapper;
	
	@Override
	public List<Section> findSectionBySiteId(String siteId) {
		return sectionMapper.selectSectionBySiteIds(Arrays.asList(siteId));
	}

	@Override
	public Section aggregateOfId(String id) throws Exception {
		return sectionMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(Section obj) {
		obj.setSectionId(StringUtil.generateUUID());
		sectionMapper.insertSelective(obj);
		return obj.getSectionId();
	}

	@Override
	public int delete(String id) {
		return sectionMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Section obj) {
		return sectionMapper.updateByPrimaryKeySelective(obj);
	}

	@Override
	public List<Section> findSectionBySiteIds(List<String> siteIds) {
		return sectionMapper.selectSectionBySiteIds(siteIds);
	}


	@Override
	public int countSectionBySiteIds(List<String> siteIds) {
		return sectionMapper.countSectionBySiteIds(siteIds);
	}
}
