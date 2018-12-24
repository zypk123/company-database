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
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.service.IContractService;
import cy.its.device.rest.action.ICompanyManageAction;
import cy.its.device.rest.dto.CompanyDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.Results;

@RestController
@RequestMapping("/device/companyManage")
public class CompanyManageAction implements ICompanyManageAction {
	
	@Autowired
	IContractService contractService;
	
	/**
	 * 查询厂商列表
	 */
	@Override
	@RequestMapping(value = "/queryCompanyList")
	public DataGridResult<CompanyDto> queryCompany(@ModelAttribute("form") CompanyDto form) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat();
		ContractorCriteria criteria = new ContractorCriteria();
		ObjectMapUtils.parseObject(criteria,form);
		criteria.setPageNum(form.getPageNumber());
		criteria.setPageSize(form.getPageSize());
		criteria.setNeedTotal(true);//判断是否要厂商总数
		List<Contractor> list = contractService.findContractors(criteria);//查询厂商
		long total = criteria.getTotal();//获取总的厂商数
		DataGridResult<CompanyDto> gridResult = new DataGridResult<CompanyDto>();
		Results<CompanyDto> res = new Results<CompanyDto>();
		List<CompanyDto> l = new ArrayList<CompanyDto>();
		for (int i = 0; i < list.size(); i++) {
			CompanyDto company = new CompanyDto();
			ObjectMapUtils.parseObject(company, list.get(i));
			company.setCreateBy(form.getCurrentUserId());//创建人
			company.setCreateTime(sdf.format(new Date()));//创建时间
			
			l.add(company);
		}
		res.setRows(l);
		res.setTotal(total);
		gridResult.setErro("");
		gridResult.setResult(res);
		return gridResult;
	}
	
	/**
	 * 查询厂商
	 */
	@Override
	@RequestMapping(value = "/queryCompany")
	public List<CompanyDto> queryCompanyInfo() throws Exception {
		ContractorCriteria criteria = new ContractorCriteria();
		List<Contractor> list = contractService.findContractors(criteria);//查询厂商
		List<CompanyDto> lst = new ArrayList<CompanyDto>();
		if(list != null){
			for (int i = 0; i < list.size(); i++) {
				CompanyDto company = new CompanyDto();
				company.setContractorId(list.get(i).getContractorId());//承建商ID
				company.setContractorName(list.get(i).getContractorName());//承建商名称	
				lst.add(company);
			}
		}
		
		return lst;
	}
	
	/**
	 * 添加厂商信息
	 */
	@Override
	@RequestMapping(value = "/addCompany")
	public int addCompany(@ModelAttribute("form") CompanyDto form) throws Exception {
		// TODO Auto-generated method stub
		Contractor contractor = new Contractor();
		ObjectMapUtils.parseObject(contractor, form);
		contractor.setCreateBy(form.getCurrentUserId());//创建人
		contractor.setCreateTime(new Date());//创建时间
		contractService.createContractor(contractor);
		return 1;
	}
	
	/**
	 * 删除厂商信息
	 */
	@Override
	@RequestMapping(value = "/removeCompany")
	public int removeCompany(@RequestParam("contractorId") String contractorId) throws Exception {
		// TODO Auto-generated method stub
		contractService.deleteContractor(contractorId);
		return 1;
	}

	/**
	 * 批量删除厂商信息
	 */
	@Override
	@RequestMapping(value = "/removeSomeCompany")
	public int removeSomeCompany(@RequestParam("contractorIdStr") String contractorIdStr) throws Exception {
		// TODO Auto-generated method stub
		String contractorId[] = contractorIdStr.split(",");
		for (int i = 0; i < contractorId.length; i++) {
			contractService.deleteContractor(contractorId[i]);
		}
		return 1;
	}
	
	/**
	 * 修改厂商信息
	 */
	@Override
	@RequestMapping(value = "/updateCompany")
	public int updateCompany(@ModelAttribute("form") CompanyDto form) throws Exception {
		// TODO Auto-generated method stub
		Contractor contractor = new Contractor();
		ObjectMapUtils.parseObject(contractor, form);
		contractor.setCreateBy(form.getCurrentUserId());//创建人
		contractor.setCreateTime(new Date());//创建时间
		contractService.updateContractor(contractor);
		return 1;
	}
	
	/**
	 *  查询厂商对象
	 * @param contractorId 厂商ID
	 * @return
	 * @throws Exception
	 */
	@Override
	@RequestMapping(value = "/queryCompanyDto")
	public CompanyDto queryCompanyDto(String contractorId) throws Exception {
		// TODO Auto-generated method stub
		Contractor Contractor = contractService.getContractorById(contractorId);
		CompanyDto company = new CompanyDto();
		ObjectMapUtils.parseObject(company, Contractor);
		return company;
	}

}
