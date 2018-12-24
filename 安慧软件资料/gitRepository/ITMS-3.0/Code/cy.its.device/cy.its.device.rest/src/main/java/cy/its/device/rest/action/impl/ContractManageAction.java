package cy.its.device.rest.action.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.model.Contract;
import cy.its.device.domain.service.IContractService;
import cy.its.device.rest.action.IContractManageAction;
import cy.its.device.rest.dto.ContractDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;
import cy.its.device.rest.dto.TreeDto;

@RestController
@RequestMapping("/device/contractManage")
public class ContractManageAction implements IContractManageAction{
	
	@Autowired
	IContractService contractService;
	
	
	/**
	 * 条件查询合同信息列表
	 */
	@Override
	@RequestMapping(value = "/queryContractList")
	public DataGridResult<ContractDto> queryContract(@ModelAttribute("form") ContractDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ContractCriteria criteria = new ContractCriteria();
		ObjectMapUtils.parseObject(criteria, form);
		criteria.setOrderName(form.getOrderName());
		criteria.setOrderType(form.getOrderType());
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		if("null".equals(form.getContractTimeFrom()) || "".equals(form.getContractTimeFrom())){
			
		}else{
			criteria.contractTimeFrom = sdf.parse(form.getContractTimeFrom());
		}
		if("null".equals(form.getContractTimeTo()) || "".equals(form.getContractTimeTo())){
			
		}else{
			criteria.contractTimeTo = sdf.parse(form.getContractTimeTo());
		}
		List<Contract> list = contractService.findContracts(criteria);
		long total = criteria.getTotal();//获取合同总数
		List<ContractDto> lst = new ArrayList<ContractDto>();
		for (int i = 0; i < list.size(); i++) {
			ContractDto contractDto = new ContractDto();
			ObjectMapUtils.parseObject(contractDto, list.get(i));
			if(!StringUtil.isNullOrEmpty(list.get(i).getContractTime())){
				contractDto.setContractTime(sdf.format(list.get(i).getContractTime()));//合同签署时间
			}
			if(!StringUtil.isNullOrEmpty(list.get(i).getBeginServiceDate())){
				contractDto.setBeginServiceDate(sdf.format(list.get(i).getBeginServiceDate()));//售后服务期始
			}
			if(!StringUtil.isNullOrEmpty(list.get(i).getEndServiceDate())){
				contractDto.setEndServiceDate(sdf.format(list.get(i).getEndServiceDate()));//售后服务期至
			}
			lst.add(contractDto);
		} 
		Results<ContractDto> res = new Results<ContractDto>();
		res.setRows(lst);
		res.setTotal(total);
		DataGridResult<ContractDto> dgr = new DataGridResult<ContractDto>();
		dgr.setErro("");
		dgr.setResult(res);
		return dgr;
	}
	
	/**
	 * 添加合同信息
	 */
	@Override
	@RequestMapping(value = "/addContract")
	public int addContract(@ModelAttribute("form") ContractDto form) throws Exception {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Contract deviceContract = new Contract();
		ObjectMapUtils.parseObject(deviceContract, form);
		if(!StringUtil.isNullOrEmpty(form.getBeginServiceDate())){
			deviceContract.setBeginServiceDate(sdf.parse(form.getBeginServiceDate()));//售后服务期始
		}
		if(!StringUtil.isNullOrEmpty(form.getEndServiceDate())){
			deviceContract.setEndServiceDate(sdf.parse(form.getEndServiceDate()));//售后服务期至
		}
		if(!StringUtil.isNullOrEmpty(form.getContractTime())){
			deviceContract.setContractTime(sdf.parse(form.getContractTime()));//合同签署时间
		}
		deviceContract.setCreateBy(form.getCurrentUserId());//创建人
		deviceContract.setCreateTime(new Date());//创建时间
		contractService.createDeviceContract(deviceContract);
		return 1;
	}
	
	/**
	 * 删除合同信息
	 */
	@Override
	@RequestMapping(value = "/removeContract")
	public int removeContract(@RequestParam("contractId") String contractId) throws Exception{
		// TODO Auto-generated method stub
		contractService.deleteDeviceContract(contractId);
		return 1;
	}

	/**
	 * 批量删除合同信息
	 */
	@Override
	@RequestMapping(value = "/removeSomeContract")
	public int removeSomeContract(@RequestParam("contractIdStr") String contractIdStr) throws Exception{
		// TODO Auto-generated method stub
		String contractId[] = contractIdStr.split(",");
		for (int i = 0; i < contractId.length; i++) {
			contractService.deleteDeviceContract(contractId[i]);
		}
		return 1;
	}
	
	/**
	 * 编辑合同信息
	 * @throws Exception 
	 */
	@Override
	@RequestMapping(value = "/updateContract")
	public int updateContract(@ModelAttribute("form") ContractDto form) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Contract deviceContract = new Contract();
		ObjectMapUtils.parseObject(deviceContract, form);
		
		if(!StringUtil.isNullOrEmpty(form.getBeginServiceDate())){
			deviceContract.setBeginServiceDate(sdf.parse(form.getBeginServiceDate()));//售后服务期始
		}
		if(!StringUtil.isNullOrEmpty(form.getEndServiceDate())){
			deviceContract.setEndServiceDate(sdf.parse(form.getEndServiceDate()));//售后服务期至
		}
		if(!StringUtil.isNullOrEmpty(form.getContractTime())){
			deviceContract.setContractTime(sdf.parse(form.getContractTime()));//合同签署时间
		}
		deviceContract.setCreateBy(form.getCurrentUserId());//创建人
		deviceContract.setCreateTime(new Date());//创建时间
		contractService.updateContract(deviceContract);
		return 1;
	}
	
	/**
	 * 查询所有合同
	 */
	@Override
	@RequestMapping(value = "/queryAllContract")
	public List<TreeDto> queryAllContract() throws Exception {
		// TODO Auto-generated method stub
		ContractCriteria criteria = new ContractCriteria();
		List<Contract> list = contractService.findContracts(criteria);
		List<TreeDto> lst = new ArrayList<TreeDto>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				TreeDto treeDto = new TreeDto();
				treeDto.setId(list.get(i).getContractId());
				treeDto.setText(list.get(i).getContractName());
				lst.add(treeDto);
			}
		}
		return lst;
	}
	
	/**
	 * 根据厂商ID查询该厂商的合同数
	 */
	@Override
	@RequestMapping(value = "/queryContractNum")
	public int queryContractNum(@RequestParam("contractorId") String contractorId) throws Exception {
		// TODO Auto-generated method stub
		ContractCriteria criteria = new ContractCriteria();
		String contractorIds[] = contractorId.split(",");
		int number = 0;
		for (int i = 0; i < contractorIds.length; i++) {
			criteria.contractorId = contractorIds[i];
			List<Contract> list = contractService.findContracts(criteria);
			number = number + list.size();
		}
		return number;
	}
	
}
