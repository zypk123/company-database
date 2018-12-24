package cy.its.device.repository.led;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.device.domain.criteria.LedContentLibCriteria;
import cy.its.device.domain.model.led.LedContentLib;
import cy.its.device.domain.repository.led.IContentLibRepository;
import cy.its.device.mybatis.client.led.LedContentLibMapper;

@Service
public class ContentLibRepository implements IContentLibRepository {

	@Autowired
	LedContentLibMapper ledContentLibMapper;
	
	@Override
	public LedContentLib aggregateOfId(String id) throws Exception {
		return ledContentLibMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(LedContentLib obj) {
		ledContentLibMapper.insertSelective(obj);
		return obj.getContentId();
	}

	@Override
	public int delete(String id) {
		return ledContentLibMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(LedContentLib obj) {
		return ledContentLibMapper.updateByPrimaryKey(obj);
	}

	@Override
	public long countContentLibs(LedContentLibCriteria criteria) {
		return ledContentLibMapper.countContentLibs(ParamUtil.parseParams(criteria));
	}

	@Override
	public List<LedContentLib> findContentLibs(LedContentLibCriteria criteria) {
		return ledContentLibMapper.selectContentLibs(ParamUtil.parseParams(criteria));
	}

}
