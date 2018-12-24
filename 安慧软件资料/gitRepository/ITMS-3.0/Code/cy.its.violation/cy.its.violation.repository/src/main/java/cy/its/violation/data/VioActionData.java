package cy.its.violation.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.bus.Receiver;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.Data;
import cy.its.violation.domain.model.config.VioActionCode;
import cy.its.violation.mybatis.client.CustomForVioMapper;
import cy.its.violation.mybatis.model.T_Vio_Action;
import cy.its.violation.util.Convert;

@Service
public class VioActionData extends Data<CustomForVioMapper, VioActionCode> implements Receiver {

	@Autowired(required = true)
	public VioActionData(@Qualifier(value = "customForVioMapper") CustomForVioMapper customForVioMapper,
			@Qualifier(value = "eventBus") EventBus eventBus) {
		super(customForVioMapper, ConstValue.ROUTE_KEY_CACHECHANGE_VIOACTION, eventBus);
	}

	@Override
	protected List<VioActionCode> readMapper(CustomForVioMapper mapper) {
		List<T_Vio_Action> lstActions = mapper.selectVioActions();
		return lstActions.stream().map((c) -> {
			return Convert.convert(c);
		}).filter((c) -> c != null).collect(Collectors.toList());
	}

	@Override
	protected Map<String, VioActionCode> groupList(List<VioActionCode> lstData) {
		return lstData.stream().collect(Collectors.toMap(VioActionCode::getVioActionCode, (c) -> c));
	}

}
