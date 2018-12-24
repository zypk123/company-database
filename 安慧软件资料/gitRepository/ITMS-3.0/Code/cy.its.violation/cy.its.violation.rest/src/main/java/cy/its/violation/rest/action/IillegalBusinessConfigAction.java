package cy.its.violation.rest.action;

import java.util.Map;

import cy.its.violation.rest.dto.IllegalBusinessConfigDto;


public interface IillegalBusinessConfigAction {
		//²éÑ¯
		Map searchIllConfig(IllegalBusinessConfigDto illegalBusinessConfigDto);
		//¸üÐÂ
		public String goUpdateIllConfig(IllegalBusinessConfigDto illegalBusinessConfigDto);

}
