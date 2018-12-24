package cy.its.sysCfg.rest.action.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.sysCfg.rest.action.IOrgAction;
import cy.its.sysCfg.rest.dto.OrgDto;
import cy.its.sysCfg.rest.dto.OrgTreeDto;
import cy.its.syscfg.domain.model.duty.OrgHeader;
import cy.its.syscfg.domain.model.duty.OrgSeat;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/sysCfg/org")
public class OrgAction implements IOrgAction {
	
	@Autowired
	IDutyService dutyService;
	
	@Autowired
	ISysCodeService sysCodeService;	
	/**
	 * 获取机构根节点数据
	 * @throws Exception 
	 */
	@RequestMapping("/getOrgRoot")
	public List<OrgTreeDto> getOrgRoot(@RequestParam(value="currentOrgId",required=true) String currentOrgId ) throws Exception {
		Organization rootOrg = dutyService.organizationOfId(currentOrgId);
		List<Organization> childrenOrg = dutyService.findSonOrgsOfParent(currentOrgId);
		//排序
		childrenOrg.sort(new Comparator<Organization>() {
            public int compare(Organization org1, Organization org2) {
            	return org1.orgPrivilegeCode.compareTo(org2.orgPrivilegeCode);
            }
        });
		List<OrgTreeDto> returnList = new ArrayList<OrgTreeDto>();
		OrgTreeDto org = convertToTreeDto(rootOrg);
		org = convertToDtoList(org,childrenOrg,rootOrg.orgPrivilegeCode,rootOrg.orgPrivilegeCode.length() + 2);
		returnList.add(org);
		return returnList;
	}
	
	
	/**
	 * 将领域对象转成dto
	 * @param org
	 * @return
	 */
	private OrgTreeDto convertToTreeDto(Organization org){
		OrgTreeDto dto = new OrgTreeDto();
		dto.setId(org.getIdentityId());
		dto.setText(org.orgName);
		Map<String,String> attribute = new HashMap<String,String>();
		attribute.put("code", org.orgCode);
		attribute.put("orgPrivCode", org.orgPrivilegeCode);
		dto.setAttribute(attribute);
		return dto;
	}
	
	/**
	 * 根据机构权限编码，转成树结构
	 * @param orgDto 树节点对象
	 * @param orgList 机构列表数据源
	 * @param orgPrivilegeCode 查找的机构ID
	 * @param length 机构ID长度
	 * @return 
	 */
	private OrgTreeDto convertToDtoList(OrgTreeDto orgDto,List<Organization> orgList, String orgPrivilegeCode,int length){
		List<OrgTreeDto> orgDtoList = new ArrayList<OrgTreeDto>();
		for(int index=0 ; index<orgList.size(); index++){
			Organization org = orgList.get(index);
			if(org.orgPrivilegeCode.startsWith(orgPrivilegeCode) && org.orgPrivilegeCode.length() == length){
				orgList.remove(index--);
				OrgTreeDto orgDtoItem = convertToTreeDto(org);
				orgDtoItem = convertToDtoList(orgDtoItem,orgList,org.orgPrivilegeCode,length + 2);
				orgDtoList.add(orgDtoItem);
			}
		}
		if(orgDtoList.size() != 0){
			orgDto.setChildren(orgDtoList);
		}
		return orgDto;
	}
	/**
	 * 创建机构信息操作
	 * @throws Excepti'on 
	 */
	@RequestMapping("/save")
	public int goSaveOrg(OrgDto orgDto) throws Exception {
		int flag=0;
		List<Organization> orgList=dutyService.organizationsOfCode(orgDto.getOrgCode());
		 if(orgList==null||orgList.size()==0){
			flag=addOrg(orgDto);
		 }
		 return flag;
	}
	//添加机构方法	
	private  int addOrg(OrgDto orgDto) throws Exception {
				// TODO Auto-generated method stub
				// 创建机构负责人对象初始化
				OrgHeader orgHeader = new OrgHeader(orgDto.getOrgHeaderName(), orgDto.getOrgHeaderPhone());
				// 创建机构驻地信息对象并赋值
				OrgSeat orgSeat = new OrgSeat();
				orgSeat.city = orgDto.getCity();
				orgSeat.addressDesc = orgDto.getAddressDesc();
				orgSeat.districtCode = orgDto.getDistrictCode();
				// 判断经度是否为空
				if (!StringUtil.isNullOrEmpty(orgDto.getOrgLongitude())) {
					orgSeat.orgLongitude = new BigDecimal(orgDto.getOrgLongitude());
				}
				// 判断纬度是否为空
				if (!StringUtil.isNullOrEmpty(orgDto.getOrgLatitude())) {
					orgSeat.orgLatitude = new BigDecimal(orgDto.getOrgLatitude());
				}

				// 创建机构信息对象实例化
				Organization org = new Organization(orgHeader, orgSeat, orgDto.getOrgId(), orgDto.getUserId(),orgDto.getUserId());
				org.orgCode = orgDto.getOrgCode();
				org.orgName = orgDto.getOrgName();
				org.parentOrgId = orgDto.getParentOrgId();
				org.parentInstructOrgId = orgDto.getParentInstructOrgId();
				org.orgType = orgDto.getOrgType();
				org.isDepartment = orgDto.getIsDepartment();
				org.isHighwayOrg = orgDto.getIsHighwayOrg();
				org.orgLevel = orgDto.getOrgLevel();
				org.orgPhoneNbr = orgDto.getOrgPhoneNbr();
				org.sponsorDistributes = orgDto.getSponsorDistributes();
				org.remark = orgDto.getRemark();
				Organization parentOrg = dutyService.organizationOfId(orgDto.getParentOrgId());
				org.orgPrivilegeCode = orgDto.getOrgCode().substring(0, parentOrg.orgPrivilegeCode.length() + 2);

				saveOrg(org);// 调用saveOrg方法,创建机构信息
			
				dutyService.organizationChanged();// 变更通知

				return 1;
			}
		private void saveOrg(Organization org) {
			dutyService.createOrganization(org);
		}

	/**
	 * 更新机构信息
	 */
	@RequestMapping("/update")
	public int goUpdate(@ModelAttribute(value = "orgDto") OrgDto orgDto) throws Exception {
		int flag=0;
		if(orgDto.getOrgCode().equals(orgDto.getOldOrgCode())){
			flag=goUpdateOrg(orgDto);
		}else{
			List<Organization> orgList=dutyService.organizationsOfCode(orgDto.getOrgCode());
			 if(orgList ==null || orgList.size() ==0 ){
				 flag=goUpdateOrg(orgDto);
			 }else{
					return flag; 
			 }
		}
		if(flag==1){
			dutyService.organizationChanged();// 变更通知
		}
		return flag;
				
	}
	//更新机构方法
	private int goUpdateOrg(OrgDto orgDto) throws Exception {
		// TODO Auto-generated method stub
		// 创建机构负责人对象初始化
		OrgHeader orgHeader = new OrgHeader(orgDto.getOrgHeaderName(), orgDto.getOrgHeaderPhone());
		// 创建机构驻地信息对象并赋值
		OrgSeat orgSeat = new OrgSeat();
		orgSeat.city = orgDto.getCity();
		orgSeat.addressDesc = orgDto.getAddressDesc();
		orgSeat.districtCode = orgDto.getDistrictCode();
		// 判断经度是否为空
		if (!StringUtil.isNullOrEmpty(orgDto.getOrgLongitude())) {
			orgSeat.orgLongitude = new BigDecimal(orgDto.getOrgLongitude());
		}
		// 判断纬度是否为空
		if (!StringUtil.isNullOrEmpty(orgDto.getOrgLatitude())) {
			orgSeat.orgLatitude = new BigDecimal(orgDto.getOrgLatitude());
		}

		// 创建机构信息对象实例化
		Organization org = new Organization(orgHeader, orgSeat, orgDto.getOrgId(), orgDto.getUserId(),orgDto.getUserId());
		org.orgCode = orgDto.getOrgCode();
		org.orgName = orgDto.getOrgName();
		org.parentOrgId = orgDto.getParentOrgId();
		org.parentInstructOrgId = orgDto.getParentInstructOrgId();
		org.orgType = orgDto.getOrgType();
		org.isDepartment = orgDto.getIsDepartment();
		org.isHighwayOrg = orgDto.getIsHighwayOrg();
		org.orgLevel = orgDto.getOrgLevel();
		org.orgPhoneNbr = orgDto.getOrgPhoneNbr();
		org.sponsorDistributes = orgDto.getSponsorDistributes();
		org.remark = orgDto.getRemark();
		Organization parentOrg = dutyService.organizationOfId(orgDto.getParentOrgId());
		org.orgPrivilegeCode = orgDto.getOrgCode().substring(0, parentOrg.orgPrivilegeCode.length() + 2);

		updateOrg(org);// 调用saveOrg方法,创建机构信息
	
		return 1;
		
	}

	public void updateOrg(Organization org) throws Exception {
		dutyService.updateOrganiztion(org);

	}
	/**
	 * 删除操作
	 * @throws Exception 
	 */
	@RequestMapping("/deleteOrg")
	public String goDelete(@RequestParam("orgIdStr") String orgIdStr) throws Exception {
		String orgId[] = orgIdStr.split(",");//去除",";
		for (int i = 0; i < orgId.length; i++) {
				deleteOrgById(orgId[i]);
		}
		dutyService.organizationChanged();//变更通知
		return "success";
	}
	
	private void deleteOrgById(String id) throws Exception{
		dutyService.deleteOrganization(id);
	}
	/**
	 * 查询机构信息
	 */
	@RequestMapping("/findOrgInfo")
	public Map<String,Object> findOrgInfo(String orgId) throws Exception {
		// System.out.println(orgId);
		// 获取父机构下所有子机构的信息
		List<Organization> list = dutyService.findOrgsOfParent(orgId);
		// 把查询结果转换成查询列表行
		ArrayList<OrgDto> lstView = new ArrayList<OrgDto>();
		//遍历list
		if (list != null) {
			for (Organization u : list) {
				OrgDto orgDto = new OrgDto();
				orgDto.setOrgHeaderPhone(u.orgHeader.getOrgHeaderPhone());
				orgDto.setOrgHeaderName(u.orgHeader.getOrgHeaderName());
				orgDto.setAddressDesc(u.orgSeat.addressDesc);
				orgDto.setDistrictCode(u.orgSeat.districtCode);
				orgDto.setCity(u.orgSeat.city);
				orgDto.setOldOrgCode(u.orgCode);
				ObjectMapUtils.parseObject(orgDto, u);
				// 判断经纬是否为空
				if (u.orgSeat.orgLongitude != null) {
					orgDto.setOrgLongitude((u.orgSeat.orgLongitude).toString());
				}
				// 判断经纬是否为空
				if (u.orgSeat.orgLatitude != null) {
					orgDto.setOrgLatitude((u.orgSeat.orgLatitude).toString());
				}
				lstView.add(orgDto);
			}
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("error", "");
		Map<String,Object> maprow = new HashMap<String,Object>();
		maprow.put("rows", lstView);
		maprow.put("total", lstView.size());
		map.put("result", maprow);
		
		return map;
	}
	
	@RequestMapping("/sync")
	public int syncOrg(HttpServletRequest request){
		String jarPath = request.getRealPath("") + "file" + File.separator +"orgSync.jar";
		Process p = null;
		try {
			p = Runtime.getRuntime().exec("java -jar " + jarPath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		BufferedInputStream errIn = new BufferedInputStream(p.getErrorStream());  
        BufferedReader errInBr = new BufferedReader(new InputStreamReader(errIn));
        BufferedInputStream in = new BufferedInputStream(p.getInputStream());  
        BufferedReader inBr = new BufferedReader(new InputStreamReader(in));  
        //检查命令是否执行失败。  
        try {
        	String lineStr;  
            while ((lineStr = errInBr.readLine()) != null)  {
                //获得命令执行后在控制台的错误信息
                System.out.println(lineStr);// 打印输出信息  
            }
            while ((lineStr = inBr.readLine()) != null)  {
                //获得命令执行后在控制台的输出信息  
                System.out.println(lineStr);// 打印输出信息  
            }
			if (p.waitFor() != 0) {  
			    if (p.exitValue() == 1){
			   	 //p.exitValue()==0表示正常结束，1：非正常结束  
			   	 return 0;  
			    }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  
        try {
			errInBr.close();
			 errIn.close();  
		} catch (IOException e) {
			e.printStackTrace();
		}  
       
        return 1;
	}
	/**
	 * 根据机构ID查询机构信息
	 */
	@RequestMapping("/queryOrgInfoById")
	public Organization queryOrgInfoById(String orgId) throws Exception{
		Organization organization = dutyService.organizationOfId(orgId);
		return organization;
	}
}
