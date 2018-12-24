package cy.its.syscfg.data;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.Data;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Code;
import cy.its.syscfg.mybatis.model.T_Sys_Code_Type;
import cy.its.syscfg.util.Convert;

@Service
public class SysCodeData extends Data<CustomMapper, CodeType> {

	@Autowired(required = true)
	private SysCodeData(
			@Qualifier(value = "customMapper") CustomMapper customMapper,
			@Qualifier(value = "eventBus") EventBus eventBus) {
		super(customMapper, ConstValue.ROUTE_KEY_CACHECHANGE_SYSCODE, eventBus);
	}

	public List<CodeType> findCodeTypes() {
		return allDatas();
	}

//	@Override
//	public void accept(Event event) {
//		load();
//	}

	@Override
	protected List<CodeType> readMapper(CustomMapper mapper) {
		List<T_Sys_Code_Type> lstCodeTypes = mapper.selectCodeTypes();
		List<T_Sys_Code> lstCodes = mapper.selectCodes();

		Map<String, List<Code>> mapCodes = lstCodes.stream()
				.map(c -> Convert.convert(c))
				.collect(Collectors.groupingBy(Code::getCodeType));

		return lstCodeTypes
				.stream()
				.map((ct) -> Convert.convert(mapCodes.get(ct.getCodeType()), ct))
				.collect(Collectors.toList());
	}

	@Override
	protected Map<String, CodeType> groupList(List<CodeType> lstData) {
		return lstData.stream().collect(
				Collectors.toMap(CodeType::getIdentityId, (ct) -> ct));
	}
}
