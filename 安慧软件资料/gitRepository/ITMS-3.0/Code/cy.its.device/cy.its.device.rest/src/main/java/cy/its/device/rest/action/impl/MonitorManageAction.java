package cy.its.device.rest.action.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;

import cy.its.com.util.ObjectMapUtils;
import cy.its.device.domain.model.TableSpaceMonitor;
import cy.its.device.domain.service.IMonitorService;
import cy.its.device.rest.action.IMonitorManageAction;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.TableSpaceDto;

@RestController
@RequestMapping("/device/monitorManage")
@Api(description="监控管理", value = "MonitorManageAction")
public class MonitorManageAction implements IMonitorManageAction {
	
	@Autowired
	IMonitorService monitorService;
	
	/**
	 * 表空间监控
	 * @return 表空间数据
	 * @throws Exception 异常
	 */
	@Override
	@RequestMapping(value = "/queryTableSpace",method = RequestMethod.POST)
	@ApiOperation(value="queryTableSpace",notes="表空间监控",httpMethod="POST")
	public DataGridResult<TableSpaceDto> queryTableSpace() throws Exception {
		DataGridResult<TableSpaceDto> grdResult = new DataGridResult<TableSpaceDto>();
		List<TableSpaceDto> list = new ArrayList<TableSpaceDto>();
		Results<TableSpaceDto> res = new Results<TableSpaceDto>();
		List<TableSpaceMonitor> lst = monitorService.findTableSpace();
		if(lst != null && lst.size() != 0){
			for (int i = 0; i < lst.size(); i++) {
				TableSpaceDto tableSpaceDto = new TableSpaceDto();
				ObjectMapUtils.parseObject(tableSpaceDto, lst.get(i));
				TableSpaceMonitor tableSpaceMonitor = lst.get(i);
				if(tableSpaceMonitor != null){
					tableSpaceDto.setTableSpaceSize(String.valueOf(tableSpaceMonitor.getTableSpaceSize()));
					tableSpaceDto.setUsedSpace(String.valueOf(tableSpaceMonitor.getUsedSpace()));
					tableSpaceDto.setFreeSpace(String.valueOf(tableSpaceMonitor.getFreeSpace()));
					tableSpaceDto.setLargestChunk(String.valueOf(tableSpaceMonitor.getLargestChunk()));
				}
				list.add(tableSpaceDto);
			}
		}
		res.setRows(list);
		grdResult.setErro("");
		grdResult.setResult(res);
		return grdResult;
	}
}
