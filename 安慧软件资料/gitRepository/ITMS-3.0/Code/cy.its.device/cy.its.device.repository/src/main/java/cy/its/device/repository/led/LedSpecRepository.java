package cy.its.device.repository.led;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.device.domain.criteria.LedSpecCriteria;
import cy.its.device.domain.model.led.LedSpec;
import cy.its.device.domain.repository.led.ILedSpecRepository;
import cy.its.device.mybatis.client.led.LedSpecMapper;

@Service
public class LedSpecRepository implements ILedSpecRepository {

	@Autowired
	LedSpecMapper ledSpecMapper;
	
	@Override
	public LedSpec aggregateOfId(String id) throws Exception {
		return ledSpecMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(LedSpec obj) {
		ledSpecMapper.insertSelective(obj);
		return obj.getSpecId();
	}

	@Override
	public int delete(String id) {
		return ledSpecMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(LedSpec obj) {
		return ledSpecMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<LedSpec> findLedSpecs(LedSpecCriteria criteria) {
		return ledSpecMapper.selectLedSpecs(ParamUtil.parseParams(criteria));
	}

	@Override
	public int countLedSpecs(LedSpecCriteria criteria) {
		return ledSpecMapper.countLedSpecs(ParamUtil.parseParams(criteria));
	}
}
