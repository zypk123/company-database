package cy.its.device.rest.action;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.TableSpaceDto;

public interface IMonitorManageAction {
	public DataGridResult<TableSpaceDto> queryTableSpace() throws Exception;
}
