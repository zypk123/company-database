package cy.its.violation.util;

import cy.its.com.util.ObjectMapUtils;
import cy.its.violation.domain.model.config.VioActionCode;
import cy.its.violation.mybatis.model.T_Vio_Action;

public class Convert {
	public static VioActionCode convert(T_Vio_Action c) {
		try {
			VioActionCode vioAct = new VioActionCode(c
					.getVioActionCode(), c.getVioType(), c
					.getVioSummary(), c.getIsGb(), c.getIssueDay(),
					c.getIssueEndDay());
			ObjectMapUtils.parseObject(vioAct, c);
			return vioAct;
		} catch (Exception e) {
			return null;
		}
	}
}
