package cy.its.violation.repository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.violation.data.VioActionData;
import cy.its.violation.domain.criteria.VioActionCriteria;
import cy.its.violation.domain.model.config.VioActionCode;
import cy.its.violation.mybatis.client.T_Vio_ActionMapper;
import cy.its.violation.mybatis.model.T_Vio_Action;

@Service
public class VioActionRepository implements IVioActionRepository {

	@Autowired
	T_Vio_ActionMapper t_Vio_ActionMapper;

	@Autowired
	VioActionData vioActionData;

	@Autowired
	EventBus eventBus;

	public VioActionCode aggregateOfId(String id) throws Exception {
		T_Vio_Action obj = t_Vio_ActionMapper.selectByPrimaryKey(id);
		VioActionCode vioAction = new VioActionCode();
		ObjectMapUtils.parseObject(vioAction, obj);
		return vioAction;
	}

	public String save(VioActionCode obj) {
		T_Vio_Action vioAction = new T_Vio_Action();

		ObjectMapUtils.parseObject(vioAction, obj);
		t_Vio_ActionMapper.insertSelective(vioAction);

		return vioAction.getVioActionCode();
	}

	public int delete(String id) {
		return t_Vio_ActionMapper.deleteByPrimaryKey(id);
	}

	public int update(VioActionCode obj) {
		T_Vio_Action vioAction = new T_Vio_Action();

		ObjectMapUtils.parseObject(vioAction, obj);
		return t_Vio_ActionMapper.updateByPrimaryKey(vioAction);
	}

	public void vioActionChanged() {
		eventBus.publish(vioActionData.getTopic(), "");
	}

	@Override
	public List<VioActionCode> findVioAction(VioActionCriteria crieria) {
		Map<String, Object> map = parseParams(crieria);
		List<T_Vio_Action> lstResult = t_Vio_ActionMapper.selectVioActions(map);
		// System.out.println(SqlHelper.getMapperSql(customForVioMapper,
		// "selectViolations", map));
		return lstResult.stream().map((c) -> {
			VioActionCode obj = new VioActionCode();
			ObjectMapUtils.parseObject(obj, c);
			return obj;
		}).collect(Collectors.toList());
	}

	private Map<String, Object> parseParams(VioActionCriteria criteria) {

		Map<String, Object> map = ParamUtil.parseParams(criteria);

		if (!StringUtil.isNullOrEmpty(criteria.fuzzySummary)) {
			map.replace("fuzzySummary", "%" + criteria.fuzzySummary + "%");
		}
		return map;
	}

}
