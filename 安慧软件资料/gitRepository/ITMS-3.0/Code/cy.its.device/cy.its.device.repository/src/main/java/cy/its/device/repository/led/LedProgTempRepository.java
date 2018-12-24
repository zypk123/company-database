package cy.its.device.repository.led;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.device.domain.criteria.LedProgTemplateCriteria;
import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.domain.repository.led.ILedProgTempRepository;
import cy.its.device.mybatis.client.led.LedProgAndTemplateMapper;
import cy.its.device.mybatis.client.led.TDeviceLedProgTemplateMapper;
import cy.its.device.mybatis.model.TDeviceLedProgTemplate;

@Service
public class LedProgTempRepository implements ILedProgTempRepository {

	@Autowired
	TDeviceLedProgTemplateMapper tDeviceLedProgTemplateMapper;

	@Autowired
	LedProgAndTemplateMapper ledProgAndTemplateMapper;

	@Override
	public LedProgTemplate aggregateOfId(String id) throws Exception {
		LedProgTemplateCriteria c = new LedProgTemplateCriteria();
		c.progTemplateId = id;
		List<LedProgTemplate> lst = findLedProgTemplates(c);
		if (lst != null && lst.size() > 0) {
			return lst.get(0);
		}

		return null;
	}

	@Override
	public String save(LedProgTemplate pTemplate) {
		tDeviceLedProgTemplateMapper.insertSelective(convert(pTemplate));
		return pTemplate.getProgTemplateId();
	}	

	@Override
	public int delete(String id) {
		return tDeviceLedProgTemplateMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(LedProgTemplate pTemplate) {	
		return tDeviceLedProgTemplateMapper.updateByPrimaryKey(convert(pTemplate));
	}	

	@Override
	public int templateCountOfSpecId(String specId) {
		LedProgTemplateCriteria c = new LedProgTemplateCriteria();
		c.specId = specId;
		List<LedProgTemplate> lst = findLedProgTemplates(c);
		return lst != null ? lst.size() : 0;
	}

	@Override
	public List<LedProgTemplate> findLedProgTemplates(LedProgTemplateCriteria criteria) {
		return ledProgAndTemplateMapper.selectProgTemplate(ParamUtil.parseParams(criteria));
	}

	private TDeviceLedProgTemplate convert(LedProgTemplate pTemplate) {
		return new TDeviceLedProgTemplate(pTemplate.getProgTemplateId(),
				pTemplate.getSpecId(), pTemplate.getTemplateName(), pTemplate.getTemplateDesc(),
				pTemplate.getMessageType(), pTemplate.getProgramContent());
	}
	
//	private void deleteContent(LedProgTemplate pTemplate) {
//		Map<String, Object> params = new HashMap<String, Object>(1);
//		params.put("progTemplateId", pTemplate.getProgTemplateId());		
//		tDeviceLedMediaMapper.deleteByParams(params);		
//		tDeviceLedTextMapper.deleteByParams(params);
//	}

//	private void createLedContent(LedProgTemplate pTemplate) {
//		if (pTemplate.getLstLedMedia() != null && pTemplate.getLstLedMedia().size() > 0) {
//			TDeviceLedMedia media;
//			for (LedProgContentMedia m : pTemplate.getLstLedMedia()) {
//				media = new TDeviceLedMedia(StringUtil.generateUUID(), null, pTemplate.getProgTemplateId(),
//						m.getMediaNo(), m.getMediaXml());
//				tDeviceLedMediaMapper.insertSelective(media);
//			}
//		}
//
//		if (pTemplate.getLstLedText() != null && pTemplate.getLstLedText().size() > 0) {
//			TDeviceLedText text;
//			for (LedProgContentText t : pTemplate.getLstLedText()) {
//				text = new TDeviceLedText(StringUtil.generateUUID(), null, pTemplate.getProgTemplateId(), t.getTextNo(),
//						t.getIsTempletText(), t.getTextXml());
//				tDeviceLedTextMapper.insertSelective(text);
//			}
//		}
//	}
}
