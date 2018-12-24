package cy.its.device.rest.action.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.util.DateUtil;
import cy.its.com.util.ExcelUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.CertificationCriteria;
import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.CertificationRecord;
import cy.its.device.domain.service.IDeviceCertificationService;
import cy.its.device.rest.action.ICertificationManageAction;
import cy.its.device.rest.dto.CertificationInfoDto;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/device/certificationManage")
public class CertificationManageAction implements ICertificationManageAction {

	@Autowired
	IDeviceCertificationService certificationService;

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpServletRequest request;

	// @ModelAttribute
	// public void setReqAndRes(HttpServletResponse response,HttpServletRequest
	// request){
	// this.response = response;
	// this.request = request;
	// }

	/**
	 * 添加保存年检信息
	 * 
	 * @param form
	 *            年检信息
	 */
	@RequestMapping(value = "/addCertificationInfo")
	@ApiOperation(value = "addCertificationInfo", notes = "添加检定证书信息", httpMethod = "POST")
	public int addCertificationInfo(@ModelAttribute("form") CertificationInfoDto form) throws Exception {
		// 校验证书是否已经存在
		int count = certificationService.getCertificationCountByNbrId(form.getCertificationNbr(), null);
		if (count >= 1) {
			return 2;
		}
		Certification certification = new Certification();
		ObjectMapUtils.parseObject(certification, form);
		if (StringUtil.isNullOrEmpty(form.getCertificatedDate())) {
			certification.setCertificatedDate(null);// 检定时间
		} else {
			certification.setCertificatedDate(DateUtil.parseFormatDate(form.getCertificatedDate()));
		}
		if (StringUtil.isNullOrEmpty(form.getExpireDate())) {
			certification.setExpireDate(null);// 有效期止
		} else {
			certification.setExpireDate(DateUtil.parseFormatDate(form.getExpireDate()));
		}
		certification.setCertificatedResult("1");// 检定证书中的结果应该全部是合格。建议这个字段可以弃用。
		certification.setCreateTime(new Date());// 创建时间
		certification.setCreateBy(form.getCurrentUserId());// 设置创建人员为当前用户
		// certification.setUpdateBy(form.getCurrentUserId());//设置修改人员为当前用户
		// systemAttachService.createCertification(certification);
		// 获取设备对应的设备编号 设备类型编码
		String equipemntType = certificationService.getVerificationEquipmentTypeByNbr(certification.getEquipmentNbr());
		// 设备编码、设备型号不为空时插入到检定信息表中
		certification.setEquipmentType(equipemntType);

		String result = certificationService.addCertifition(certification);
		return StringUtil.isNullOrEmpty(result) ? 0 : 1;
	}

	/**
	 * 查询设备年检信息
	 * 
	 * @param equipmentNbr
	 *            设备编码
	 * @param order
	 *            设备列表按有效期排序方式 不传 默认为降序
	 * @throws Exception
	 * @return List<CertificationInfoDto>
	 */
	@RequestMapping(value = "/queryAllCertification")
	@ApiOperation(value = "isExistCertification", notes = "根据设备编号查询对应的检定证书信息", httpMethod = "GET")
	public List<CertificationInfoDto> queryAllCertification(
			@ApiParam(value = "equipmentNbr", required = true) @RequestParam("equipmentNbr") String equipmentNbr,
			@RequestParam(value = "order", required = false, defaultValue = "1") String order) throws Exception {
		// List<Certification> lst =
		// systemAttachService.certificationsOfSystem(deviceId);
		List<Certification> lst = certificationService.queryCertifitionOrder(equipmentNbr, order);
		List<CertificationInfoDto> list = new ArrayList<CertificationInfoDto>();
		for (int i = 0; i < lst.size(); i++) {
			CertificationInfoDto certificationInfoDto = new CertificationInfoDto();
			ObjectMapUtils.parseObject(certificationInfoDto, lst.get(i));
			if (lst.get(i).getCertificatedDate() != null) {
				certificationInfoDto.setCertificatedDate(DateUtil.parseFormatDate(lst.get(i).getCertificatedDate()));
			}
			if (lst.get(i).getExpireDate() != null) {
				certificationInfoDto.setExpireDate(DateUtil.parseFormatDate(lst.get(i).getExpireDate()));
			}
			list.add(certificationInfoDto);
		}
		return list;
	}

	/**
	 * 查询某年检信息
	 * 
	 * @param certificationId
	 *            年检证书ID
	 */
	@RequestMapping(value = "/queryCertificationById")
	@ApiOperation(value = "queryCertificationById", notes = "查询年检信息", httpMethod = "GET")
	public CertificationInfoDto queryCertificationById(@RequestParam("certificationId") String certificationId)
			throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		CertificationInfoDto certificationInfoDto = new CertificationInfoDto();
		Certification certification = new Certification();
		// certification =
		// systemAttachService.certificationOfId(certificationId);
		certification = certificationService.queryCertifition(certificationId);
		ObjectMapUtils.parseObject(certificationInfoDto, certification);
		if (certification.getCertificatedDate() != null) {
			certificationInfoDto.setCertificatedDate(sdf.format(certification.getCertificatedDate()));
		}
		if (certification.getExpireDate() != null) {
			certificationInfoDto.setExpireDate(sdf.format(certification.getExpireDate()));
		}
		return certificationInfoDto;
	}

	/**
	 * 编辑某年检信息
	 * 
	 * @param certificationId
	 *            年检记录ID
	 */
	@RequestMapping(value = "/editCertification")
	@ApiOperation(value = "editCertification", notes = "修改年检信息", httpMethod = "PUT")
	public int editCertification(@ModelAttribute("form") CertificationInfoDto form) throws Exception {
		Certification certification = new Certification();
		ObjectMapUtils.parseObject(certification, form);

		// 校验证书是否已经存在
		int count = certificationService.getCertificationCountByNbrId(form.getCertificationNbr(),
				form.getCertificationId());
		if (count >= 1) {
			return 2;
		}

		if (StringUtil.isNullOrEmpty(form.getCertificatedDate())) {
			certification.setCertificatedDate(null);// 检定时间
		} else {
			certification.setCertificatedDate(DateUtil.parseFormatDate(form.getCertificatedDate()));
		}
		if (StringUtil.isNullOrEmpty(form.getExpireDate())) {
			certification.setExpireDate(null);// 有效期止
		} else {
			certification.setExpireDate(DateUtil.parseFormatDate(form.getExpireDate()));
		}
		certification.setUpdateBy(form.getCurrentUserId());// 设置修改人员为当前用户
		certification.setUpdateTime(new Date());
		// systemAttachService.updateCertification(certification);
		int i = certificationService.updateCertifition(certification);
		return i;
	}

	/**
	 * 删除某年检信息
	 * 
	 * @param certificationId
	 *            年检记录ID
	 */
	@RequestMapping(value = "/removeCertification")
	@ApiOperation(value = "removeCertification", notes = "删除年检信息", httpMethod = "DELETE")
	public int removeCertification(@RequestParam("certificationId") String certificationId) throws Exception {
		// systemAttachService.removeCertification(certificationId);
		int i = certificationService.deleteCertifition(certificationId);
		return i;
	}

	/**
	 * 获取已登记设备的最新年检信息
	 * 
	 * @param certificationInfoDto
	 *            设备年检信息相关查询条件
	 */
	@RequestMapping(value = "/getEquipmentCertificationList")
	@ApiOperation(value = "getEquipmentCertificationList", notes = "查询已登记设备的最新年检信息", httpMethod = "GET")
	public Object getEquipmentCertificationList(CertificationInfoDto certificationInfoDto) throws Exception {
		// 管理单位为空时 查询当前用户所在单位及其下级单位的数据
		if (StringUtil.isNullOrEmpty(certificationInfoDto.getOrgId())) {
			certificationInfoDto.setOrgId(certificationInfoDto.getCurrentOrgId());
		}
		CertificationCriteria certificationCriteria = new CertificationCriteria();
		ObjectMapUtils.parseObject(certificationCriteria, certificationInfoDto);
		certificationCriteria.setOrderName(certificationInfoDto.getOrderName());
		certificationCriteria.setOrderType(certificationInfoDto.getOrderType());
		PageHelper.startPage(certificationInfoDto.getPageNumber(), certificationInfoDto.getPageSize());
		Page<Map<String, String>> page = (Page<Map<String, String>>) certificationService
				.getEquipmentCertificationList(certificationCriteria);
		List<Map<String, String>> certificationList = page.getResult();
		// ArrayList<CertificationInfoDto> certificationReturnList = new
		// ArrayList<CertificationInfoDto>();
		// if(certificationList != null && certificationList.size() > 0){
		// for (int i = 0; i < certificationList.size(); i++) {
		// CertificationInfoDto resultCertificationInfoDto = new
		// CertificationInfoDto();
		// ObjectMapUtils.parseObject(resultCertificationInfoDto,
		// certificationList.get(i));
		// if(certificationList.get(i).getCertificatedDate() != null){
		// resultCertificationInfoDto.setCertificatedDate(DateUtil.parseFormatDate(certificationList.get(i).getCertificatedDate()));
		// }
		// if(certificationList.get(i).getExpireDate() != null){
		// resultCertificationInfoDto.setExpireDate(DateUtil.parseFormatDate(certificationList.get(i).getExpireDate()));
		// }
		// if(certificationList.get(i).getEquipmentType() != null){
		// resultCertificationInfoDto.setEquipmentTypeName(certificationList.get(i).getEquipmentType());
		// }
		// certificationReturnList.add(resultCertificationInfoDto);
		// }
		// }

		return parseToJson(page, certificationList);

	}

	@RequestMapping(value = "/getNoEquipmentCertificationList")
	@ApiOperation(value = "getNoEquipmentCertificationList", notes = "查询未登记设备的最新年检信息", httpMethod = "GET")
	public Object getNoEquipmentCertificationList(CertificationInfoDto certificationInfoDto) throws Exception {
		CertificationCriteria certificationCriteria = new CertificationCriteria();
		ObjectMapUtils.parseObject(certificationCriteria, certificationInfoDto);
		certificationCriteria.setOrderName(certificationInfoDto.getOrderName());
		certificationCriteria.setOrderType(certificationInfoDto.getOrderType());
		PageHelper.startPage(certificationInfoDto.getPageNumber(), certificationInfoDto.getPageSize());
		Page<Map<String, String>> page = (Page<Map<String, String>>) certificationService
				.getNoEquipmentCertificationList(certificationCriteria);
		List<Map<String, String>> certificationList = page.getResult();
		// ArrayList<CertificationInfoDto> certificationReturnResultDtoList =
		// new ArrayList<CertificationInfoDto>();
		// if(certificationList != null && certificationList.size() > 0){
		// for (int i = 0; i < certificationList.size(); i++) {
		// CertificationInfoDto resultCertificationInfoDto = new
		// CertificationInfoDto();
		// ObjectMapUtils.parseObject(resultCertificationInfoDto,
		// certificationList.get(i));
		// if(certificationList.get(i).getCertificatedDate() != null){
		// resultCertificationInfoDto.setCertificatedDate(DateUtil.parseFormatDate(certificationList.get(i).getCertificatedDate()));
		// }
		// if(certificationList.get(i).getExpireDate() != null){
		// resultCertificationInfoDto.setExpireDate(DateUtil.parseFormatDate(certificationList.get(i).getExpireDate()));
		// }
		// if(certificationList.get(i).getEquipmentType() != null){
		// resultCertificationInfoDto.setEquipmentTypeName(certificationList.get(i).getEquipmentType());
		// }
		// certificationReturnResultDtoList.add(resultCertificationInfoDto);
		// }
		// }

		return parseToJson(page, certificationList);
	}

	@RequestMapping(value = "/getCertificatioRecordnList")
	@ApiOperation(value = "getCertificatioRecordnList", notes = "查询批量导入的年检证书信息记录", httpMethod = "GET")
	public Object getCertificatioRecordnList(CertificationInfoDto certificationInfoDto) throws Exception {
		CertificationCriteria certificationCriteria = new CertificationCriteria();
		ObjectMapUtils.parseObject(certificationCriteria, certificationInfoDto);
		certificationCriteria.setOrderName(certificationInfoDto.getOrderName());
		certificationCriteria.setOrderType(certificationInfoDto.getOrderType());
		PageHelper.startPage(certificationInfoDto.getPageNumber(), certificationInfoDto.getPageSize());
		Page<Map<String, String>> page = (Page<Map<String, String>>) certificationService
				.getCertificationRecordList(certificationCriteria);
		List<Map<String, String>> certificationList = page.getResult();
		// ArrayList<CertificationInfoDto> certificationReturnResultDtoList =
		// new ArrayList<CertificationInfoDto>();
		// if(certificationList != null && certificationList.size() > 0){
		// for (int i = 0; i < certificationList.size(); i++) {
		// CertificationInfoDto resultcertificationInfoDto = new
		// CertificationInfoDto();
		// ObjectMapUtils.parseObject(resultcertificationInfoDto,
		// certificationList.get(i));
		// if(certificationList.get(i).getCertificatedDate() != null){
		// resultcertificationInfoDto.setCertificatedDate(DateUtil.parseFormatDate(certificationList.get(i).getCertificatedDate()));
		// }
		// if(certificationList.get(i).getExpireDate() != null){
		// resultcertificationInfoDto.setExpireDate(DateUtil.parseFormatDate(certificationList.get(i).getExpireDate()));
		// }
		// certificationReturnResultDtoList.add(resultcertificationInfoDto);
		// }
		// }

		return parseToJson(page, certificationList);
	}

	@RequestMapping(value = "/exportCertificatioRecordExcel")
	@ApiOperation(value = "exportCertificatioRecordExcel", notes = "根据传值查询检定证书导入记录信息 生成execl，返回下载路径", httpMethod = "GET")
	public Object exportCertificatioRecordExcel(CertificationInfoDto certificationInfoDto) throws Exception {
		OutputStream outputStream = null;
		JSONObject obj = new JSONObject();
		try {
			CertificationCriteria certificationCriteria = new CertificationCriteria();
			ObjectMapUtils.parseObject(certificationCriteria, certificationInfoDto);
			List<Map<String, String>> certificationList = null;
			if (certificationInfoDto.getRecordIdArray() == null
					|| certificationInfoDto.getRecordIdArray().length <= 0) {
				certificationList = certificationService.getCertificationRecordList(certificationCriteria);
			} else {
				certificationList = certificationService
						.getCertificationRecordListByIds(certificationInfoDto.getRecordIdArray());
			}

			if (certificationList != null && certificationList.size() > 0) {
				String[] headers = new String[] { "证书编号", "设备编号/出厂编号", "委托单位", "器具名称", "设备型号", "检定日期", "有效日期", "同步批次",
						"同步标记" };
				String[] fieldNameArr = new String[] { "certificationNbr", "equipmentNbr", "orgName", "deviceName",
						"modelName", "certificatedDate", "expireDate", "importDate", "importMark" };
				String excelName = "检定记录" + DateUtil.parseFormatDate(new Date(), "yyyyMMddHHmmssS") + ".xlsx";
				File file = new File(request.getServletContext().getRealPath("/FileDir") + "/"
						+ DateUtil.parseFormatDate(new Date(), "yyyyMMdd") + "/" + excelName);
				// InetAddress inetAddress = InetAddress.getLocalHost();
				// String ip = inetAddress.getHostAddress();
				String ip = getRealIp();
				String basePath = request.getScheme() + "://" + ip + ":" + request.getServerPort();
				Map<String, String> title = new HashMap<String,String>();
				title = getTitleAndParam(certificationInfoDto);
				File fileDir = file.getParentFile();
				if (!fileDir.exists()) {
					fileDir.mkdirs();
				}
				outputStream = new FileOutputStream(file);
				obj.put("url", basePath + request.getContextPath() + "/FileDir/"
						+ DateUtil.parseFormatDate(new Date(), "yyyyMMdd") + "/" + excelName);
				ExcelUtil.exportExcel(certificationList, fieldNameArr, headers, outputStream, title);
			}

			// if(certificationList != null && certificationList.size() > 0){
			// String[] headers = new
			// String[]{"证书编号","设备编号/出厂编号","委托单位","器具名称","设备型号","检定日期","有效日期","同步批次","同步标记"};
			// String[] fieldNameArr = new
			// String[]{"certificationNbr","equipmentNbr","orgName","deviceName","modelName","certificatedDate","expireDate","importDate","importMark"};
			// String excelName = "CertificationRecord" +
			// DateUtil.parseFormatDate(new Date(), "yyyyMMddHHmmssS") +
			// ".xlsx";
			// File file = new
			// File(request.getServletContext().getRealPath("/FileDir")+"/"+
			// DateUtil.parseFormatDate(new Date(), "yyyyMMdd")+"/"+excelName);
			// InetAddress inetAddress = InetAddress.getLocalHost();
			// String ip = inetAddress.getHostAddress();
			// String basePath = request.getScheme() + "://" + ip + ":" +
			// request.getServerPort();
			// File fileDir = file.getParentFile();
			// if(!fileDir.exists()){
			// fileDir.mkdirs();
			// }
			// outputStream = new FileOutputStream(file);
			// obj.put("url",basePath+request.getContextPath()+"/FileDir/"+
			// DateUtil.parseFormatDate(new Date(), "yyyyMMdd")+"/"+excelName);
			// new
			// ExcelUtil<CertificationRecord>().exportExcel(certificationList,
			// fieldNameArr, headers, outputStream);
			// }

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return obj;

	}

	private Map<String, String> getTitleAndParam(CertificationInfoDto certificationInfoDto) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		map.put("title", "检定记录导出一览");
		CertificationInfoDto dto = new CertificationInfoDto();
		ObjectMapUtils.parseObject(dto, certificationInfoDto);
		String queryParam = "";
		if (dto.getImportDate() != "") {
			queryParam += "导入批次：" + dto.getImportDate() + "，";
		}
		if (dto.getOrgName() != "") {
			queryParam += "委托单位：" + dto.getOrgName() + "，";
		}
		if (dto.getBeginCertificatedDate() != "" && dto.getEndCertificatedDate() != "") {
			queryParam += "检定日期：" + dto.getBeginCertificatedDate() + "日至：" + dto.getEndCertificatedDate() + "日，";
		} else {
			if (dto.getBeginCertificatedDate() != "") {
				queryParam += "检定日期：" + dto.getBeginCertificatedDate() + "之后，";
			}
			if (dto.getEndCertificatedDate() != "") {
				queryParam += "检定日期：" + dto.getEndCertificatedDate() + "之前，";
			}
		}
		if (dto.getEquipmentTypeArray() != null && dto.getEquipmentTypeArray().length != 0) {
			String deviceTypeName = "";
			List<Code> list = sysCodeService.codeListOfType("488");
			Map<String, Code> mappedCodes = list.stream().collect(Collectors.toMap(Code::getCodeNo, (p) -> p));
			for (String deviceType : dto.getEquipmentTypeArray()) {
				if (!deviceType.equals("")) {
					deviceTypeName += mappedCodes.get(deviceType).codeName + "、";
				}
			}
			queryParam += "设备类型：" + deviceTypeName.substring(0, deviceTypeName.length() - 1) + "，";
		}
		if (dto.getEquipmentNbr() != "") {
			queryParam += "设备编号：" + dto.getEquipmentNbr() + "，";
		}
		if (dto.getImportMarkArray() != null && dto.getImportMarkArray().length != 0) {
			String importMark = "同步标记：";
			Map<String, String> mapMark = new HashMap<String, String>();
			mapMark.put("1", "导入成功");
			mapMark.put("2", "未登记");
			mapMark.put("3", "非交警设备");
			mapMark.put("4", "其它原因");
			for (String mark : dto.getImportMarkArray()) {
				importMark += mapMark.get(mark) + "、";
			}
			queryParam += importMark.substring(0, importMark.length() - 1) + ",";
		}
		if (queryParam.length() >= 1) {
			queryParam = queryParam.substring(0, queryParam.length() - 1);
		} else {
			queryParam += "导入批次：全部，同步标记：导入成功、未登记、非交警设备、其他原因";
		}
		map.put("param", queryParam);
		return map;
	}

	/**
	 * 获取真实的IP地址
	 * 
	 * @return
	 * @throws SocketException
	 */
	public String getRealIp() throws SocketException {
		String localIP = null;// 本地IP，如果没有配置外网IP则返回它
		String netIP = null;// 外网IP

		// 返回此机器上的所有接口
		Enumeration<NetworkInterface> netInterfaces = NetworkInterface.getNetworkInterfaces();
		InetAddress ip = null;
		boolean finded = false;// 是否找到外网IP
		while (netInterfaces.hasMoreElements() && !finded) {
			NetworkInterface ni = netInterfaces.nextElement();
			Enumeration<InetAddress> address = ni.getInetAddresses();
			while (address.hasMoreElements()) {
				ip = address.nextElement();
				if (!ip.isSiteLocalAddress() && !ip.isLoopbackAddress() && ip.getHostAddress().indexOf(":") == -1) {// 外网IP
					netIP = ip.getHostAddress();
					finded = true;
					break;
				} else if (ip.isSiteLocalAddress() && !ip.isLoopbackAddress()
						&& ip.getHostAddress().indexOf(":") == -1) {// 内网IP
					localIP = ip.getHostAddress();
				}
			}
		}

		if (netIP != null && !"".equals(netIP)) {
			return netIP;
		} else {
			return localIP;
		}
	}

	/**
	 * 解析excel 导入设备年检证书信息 支持解析多个excel、每个excel多sheet 每个excel路径以逗号（,）分割
	 * 导入excel的列：序号 证书编号 委托日期 委托单位 器具名称 器具型号 出厂编号 设备编号 检定日期 有效日期 检定结论 检定员 核验员
	 * 批准人 打印日期 部门 制造厂商 添加人 打印人 检定费 修理费 校准费 配件费 服务费 工作量 用到的列：证书编号 委托单位 器具名称 器具型号
	 * 出厂编号/设备编号 检定日期 有效日期 检定结论 检定员 部门
	 * 
	 * @Title: importCertification
	 * @param certificationInfoDto
	 *            包含上传文件的网络路径
	 * @return CertificationInfoDto
	 * @throws Exception
	 */
	@RequestMapping(value = "/importCertification")
	@ApiOperation(value = "importCertification", notes = "解析excel 导入设备年检证书信息", httpMethod = "GET")
	public CertificationInfoDto addImportCertification(CertificationInfoDto certificationInfoDto) throws Exception {
		CertificationInfoDto resultCertificationInfoDto = new CertificationInfoDto();

		try {
			// 存放excel解析出的数据
			List<String[]> exceList = new ArrayList<String[]>();
			// 解析excel到list
			this.resolveExcel(exceList, certificationInfoDto);
			// 解析出现错误返回错误信息到页面
			if (!StringUtil.isNullOrEmpty((Object) certificationInfoDto.getErrMsg())) {
				resultCertificationInfoDto.setErrMsg(certificationInfoDto.getErrMsg());
				return resultCertificationInfoDto;
			}
			Date createDate = new Date();
			// 导入批次
			String importDate = DateUtil.parseFormatDate(new Date());
			// 生成操作流水
			String importAccept = StringUtil.generateUUID();
			// 存放解析后的excel中的检定证书信息转换成的检定证书记录对象
			List<CertificationRecord> certificationList = new ArrayList<CertificationRecord>();
			// 将excel解析出的list中数据放入到CertificationRecord中
			this.listArrToCertificationRecord(certificationList, exceList, createDate, importDate, importAccept,
					certificationInfoDto);
			// 解析出现错误返回错误信息到页面
			if (!StringUtil.isNullOrEmpty((Object) certificationInfoDto.getErrMsg())) {
				resultCertificationInfoDto.setErrMsg(certificationInfoDto.getErrMsg());
				return resultCertificationInfoDto;
			}

			// 分析结果map key为证书编码 用于去重
			Map<String, CertificationRecord> analyzeResultMap = new HashMap<String, CertificationRecord>();
			// 证书编号为空存list
			List<CertificationRecord> nbrIsNullList = new ArrayList<CertificationRecord>();
			// 校验excel中的数据是否符合要求
			this.checkCertificationConform(certificationList, analyzeResultMap, nbrIsNullList);

			// 获取本批次入库记录的证书编号 用于与本次导入的数据做比对 已存在则进行更新操作
			List<String> certificationRecordImportDateList = certificationService
					.getCertificationNbrListByImportDate(importDate);
			// 存放用于插入检定证书信息记录表的数据
			List<CertificationRecord> insertCertificationRecordList = new ArrayList<CertificationRecord>();
			// 存放用于更新检定证书信息记录表的数据
			List<CertificationRecord> updateCertificationRecordList = new ArrayList<CertificationRecord>();
			// 判断该证书在本批次是否已经入库，未入库放入到插入列表insertCertificationRecordList中，已入库放入到更新列表updateCertificationRecordList中
			for (Map.Entry<String, CertificationRecord> entry : analyzeResultMap.entrySet()) {
				// 判断是否该证书在本批次是否已经入库
				if (!certificationRecordImportDateList.contains(entry.getKey())) {
					entry.getValue().setRecordId(StringUtil.generateUUID());
					insertCertificationRecordList.add(entry.getValue());
				} else {
					entry.getValue().setUpdatePerson(entry.getValue().getCreatePerson());
					entry.getValue().setUpdateTime(entry.getValue().getCreateTime());
					entry.getValue().setCreatePerson(null);
					entry.getValue().setCreateTime(null);
					updateCertificationRecordList.add(entry.getValue());
				}
			}

			insertCertificationRecordList.addAll(nbrIsNullList);
			// 每次插入50条
			for (int i = 0; i < insertCertificationRecordList.size(); i += 50) {
				certificationService.insertCertificationRecordBatch(
						insertCertificationRecordList.subList(i, i + 50 <= insertCertificationRecordList.size() ? i + 50
								: insertCertificationRecordList.size()));
			}
			// 每次更新50条
			for (int i = 0; i < updateCertificationRecordList.size(); i += 50) {
				certificationService.updateCertificationRecordBatchByNbrImportDate(
						updateCertificationRecordList.subList(i, i + 50 <= updateCertificationRecordList.size() ? i + 50
								: updateCertificationRecordList.size()));
				// certificationService.insertCertificationRecordBatch(
				// updateCertificationRecordList.subList(i, i + 50 <=
				// updateCertificationRecordList.size() ? i + 50 :
				// updateCertificationRecordList.size()));
			}
			Map<String, Object> paramsMap = new HashMap<String, Object>();
			paramsMap.put("importDate", importDate);
			paramsMap.put("importAccept", importAccept);
			paramsMap.put("createDate", createDate);
			paramsMap.put("currentUserId", certificationInfoDto.getCurrentUserId());
			// 添加修设备检定证书信息表
			certificationService.insertCertificationByRecord(paramsMap);
			certificationService.updateCertificationByRecord(paramsMap);
			Map<String, Object> importResultMap = certificationService.getImportResult(importDate, importAccept);
			if (importResultMap == null) {
				resultCertificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
				return resultCertificationInfoDto;
			} else {
				resultCertificationInfoDto.setImportSucessCount(
						Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("IMPORTSUCESSCOUNT")) ? "0"
								: String.valueOf(importResultMap.get("IMPORTSUCESSCOUNT"))));
				resultCertificationInfoDto.setUnRegisteredCount(
						Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("UNREGISTEREDCOUNT")) ? "0"
								: String.valueOf(importResultMap.get("UNREGISTEREDCOUNT"))));
				resultCertificationInfoDto.setUnPoliceCount(
						Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("UNPOLICECOUNT")) ? "0"
								: String.valueOf(importResultMap.get("UNPOLICECOUNT"))));
				resultCertificationInfoDto
						.setOtherCount(Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("OTHERCOUNT"))
								? "0" : String.valueOf(importResultMap.get("OTHERCOUNT"))));
				resultCertificationInfoDto
						.setRepeatCount(certificationList.size() - analyzeResultMap.size() - nbrIsNullList.size());
				resultCertificationInfoDto.setErrMsg("SUCCESS");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("解析文件异常，请检查文件中数据是否有异常！");
		}

		return resultCertificationInfoDto;
	}

	/**
	 * 解析excel，将excel中所有sheet页中数据放入到list中
	 * 
	 * @param path
	 * @return List<String[]>
	 */
	private void resolveExcel(List<String[]> excelList, CertificationInfoDto certificationInfoDto) {
		// excel路径
		String path = certificationInfoDto.getUpLoadPath();
		if (path == null || "".equals(path.trim())) {
			certificationInfoDto.setErrMsg("上传文件异常，请重新上传");
			return;
		}
		// 上传多个excel时以逗号分割
		String[] excelPathArr = path.split(",");
		// 分别解析上传的excel
		for (String excelPath : excelPathArr) {

			// 转义文件路径（网络路径）中的特殊字符
			excelPath = excelPath.replaceAll("%", "%25");
			excelPath = excelPath.replaceAll(" ", "%20");
			excelPath = excelPath.replaceAll("\\+", "%2B");
			excelPath = excelPath.replaceAll("\\?", "%3F");
			excelPath = excelPath.replaceAll("#", "%23");
			excelPath = excelPath.replaceAll("&", "%26");
			// 对路径中的中文及中文符号进行转码
			char[] charArray = excelPath.toCharArray();
			try {
				for (char c : charArray) {
					if (isChinese(c)) {
						excelPath = excelPath.replaceAll(String.valueOf(c),
								URLEncoder.encode(String.valueOf(c), "UTF-8"));
					}
				}
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				certificationInfoDto.setErrMsg("上传文件异常，请检查文件名是否包含特殊字符");
				return;
			}
			URL url;
			try {
				url = new URL(excelPath);
			} catch (Exception e) {
				e.printStackTrace();
				certificationInfoDto.setErrMsg("上传文件异常，请检查文件名是否包含特殊字符");
				return;
			}
			try {
				URLConnection conn = url.openConnection();
				// 获取一个excel的解析值
				List<List<String[]>> oneExcelList = ExcelUtil.readExcel(conn.getInputStream());
				// 判断解析的excel是否为空 为空则跳过
				if (oneExcelList == null || oneExcelList.size() <= 0) {
					continue;
				}
				// 判断解析的excel的每个sheet数据是否正常
				for (List<String[]> list : oneExcelList) {
					// 判断解析的excel的每个sheet是否为空
					if (list == null || list.size() <= 0) {
						continue;
					}
					// sheet不为空 则判断长度是否正常
					if (list.get(0).length < 16) {
						certificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
						return;
					}
					// 将每个sheet的值放入统一的List中
					excelList.addAll(list);
				}
			} catch (Exception e) {
				e.printStackTrace();
				certificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
				return;
			}
		}

		// 判断总体上传的excel中是否有数据
		if (excelList == null || excelList.size() <= 0 || excelList.get(0) == null || excelList.get(0).length < 16) {
			certificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
			return;
		}
		return;
	}

	/**
	 * 将excel解析到list的数据放入CertificationRecord对象中
	 * 
	 * @param list
	 * @param importDate
	 * @param importAccept
	 * @return List<CertificationRecord>
	 */
	private void listArrToCertificationRecord(List<CertificationRecord> certificationList, List<String[]> exceList,
			Date createDate, String importDate, String importAccept, CertificationInfoDto certificationInfoDto) {
		// 日期格式正则表达式
		String regex = "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}";
		// 将解析后的excel中有用的信息转成检定证书对象
		for (String[] row : exceList) {

			CertificationRecord certificationRecord = new CertificationRecord();

			certificationRecord.setCertificationNbr(row[1]); // 证书编号
			certificationRecord.setOrgName(row[3]); // 委托单位
			certificationRecord.setDeviceName(row[4]); // 器具名称
			certificationRecord.setModelName(row[5]); // 器具型号
			certificationRecord.setEquipmentNbr(row[6] == null || "".equals(row[6]) ? row[7] : row[6]); // 出厂编号/设备编号
			certificationRecord.setCertificatedDate(DateUtil.parseFormatDate(row[8])); // 检定日期
			certificationRecord.setExpireDate(DateUtil.parseFormatDate(row[9])); // 有效日期
			certificationRecord.setCertificatedResult(row[10]); // 检定结论
			certificationRecord.setCertificatedPerson(row[11]); // 检定员
			certificationRecord.setCertificatedOrg(row[15]); // 部门
			certificationRecord.setCreatePerson(certificationInfoDto.getCurrentUserId());
			certificationRecord.setCreateTime(createDate);
			certificationRecord.setImportAccept(importAccept);
			certificationRecord.setImportDate(importDate);
			certificationList.add(certificationRecord);

			// 校验excel值是否超长 超长结束导入
			if (certificationRecord.getCertificationNbr().length() > 32) {
				certificationInfoDto.setErrMsg("[证书编号]列有值超长");
				return;
			} else if (certificationRecord.getOrgName().length() > 120) {
				certificationInfoDto.setErrMsg("[委托单位]列有值超长");
				return;
			} else if (certificationRecord.getDeviceName().length() > 120) {
				certificationInfoDto.setErrMsg("[器具名称]列有值超长");
				return;
			} else if (certificationRecord.getModelName().length() > 120) {
				certificationInfoDto.setErrMsg("[器具型号]列有值超长");
				return;
			} else if (certificationRecord.getEquipmentNbr().length() > 32) {
				certificationInfoDto.setErrMsg("[出厂编号/设备编号]列有值超长");
				return;
			} else if (certificationRecord.getCertificatedResult().length() > 5) {
				certificationInfoDto.setErrMsg("[检定结论]列有值超长");
				return;
			} else if (certificationRecord.getCertificatedPerson().length() > 20) {
				certificationInfoDto.setErrMsg("[检定员]列有值超长");
				return;
			} else if (certificationRecord.getCertificatedOrg().length() > 120) {
				certificationInfoDto.setErrMsg("[检定部门]列有值超长");
				return;
			} else if (!StringUtil.isNullOrEmpty((Object) row[8]) && !Pattern.matches(regex, row[8])) {
				certificationInfoDto.setErrMsg("[检定日期]列有值不是日期格式");
				return;
			} else if (!StringUtil.isNullOrEmpty((Object) row[9]) && !Pattern.matches(regex, row[9])) {
				certificationInfoDto.setErrMsg("[有效日期]列有值不是日期格式");
				return;
			}
		}

	}

	/**
	 * 校验excel中的数据是否符合标准
	 * 
	 * @param certificationList
	 * @param certificationInfoDto
	 * @return void
	 * @throws Exception
	 */
	private void checkCertificationConform(List<CertificationRecord> certificationList,
			Map<String, CertificationRecord> analyzeResultMap, List<CertificationRecord> nbrIsNullList)
					throws Exception {

		// 将器具名称(器具名称在表t_sys_code的remark字段中用逗号分割)、设备类型编码放到一个map<器具名称,设备类型编码>中
		// 方便获取
		Map<String, String> equipmentTypeByDeviceNameMap = new HashMap<String, String>();
		List<Code> codeList = sysCodeService.codeListOfType("488");
		for (Code sysCode : codeList) {
			String deviceNames = sysCode.remark;
			if (!StringUtil.isNullOrEmpty(deviceNames)) {
				// (多个器具名称对应一个设备类型,在表t_sys_code的remark字段中用逗号分割)
				String[] deviceNameArr = deviceNames.split(",");
				for (String deviceName : deviceNameArr) {
					if (!StringUtil.isNullOrEmpty(deviceName)) {
						equipmentTypeByDeviceNameMap.put(deviceName, sysCode.codeNo);
					}
				}
			}
		}
		// (判断是否为交警设备的关键字配置在表t_sys_code的remark字段中用逗号分割)
		Code code = sysCodeService.codeOfId("490", "3");
		String[] policeMarkArr = StringUtil.isNullOrEmpty(code.remark) ? null : code.remark.split(",");
		// 获取所有需登记的设备编码
		List<String> verficationEquipmentNbrList = certificationService.getAllVerificationEquipmentNbr();
		// 循环处理证书信息是否符合导入条件
		for (CertificationRecord certificationRecord : certificationList) {
			// 判断证书编号是否为空 如为空 则放到nbrIsNullList
			if (StringUtil.isNullOrEmpty(certificationRecord.getCertificationNbr())) {
				// 证书编号为空 设置导入标记为 其他原因
				certificationRecord.setRemark("证书编号为空");
				certificationRecord.setImportMark("4");
				nbrIsNullList.add(certificationRecord);
			} else if (StringUtil.isNullOrEmpty(certificationRecord.getOrgName())
					|| StringUtil.isNullOrEmpty(certificationRecord.getDeviceName())
					|| StringUtil.isNullOrEmpty(certificationRecord.getModelName())
					|| StringUtil.isNullOrEmpty(certificationRecord.getEquipmentNbr())
					|| certificationRecord.getCertificatedDate() == null || certificationRecord.getExpireDate() == null
					|| StringUtil.isNullOrEmpty(certificationRecord.getCertificatedResult())
					|| StringUtil.isNullOrEmpty(certificationRecord.getCertificatedPerson())
					|| StringUtil.isNullOrEmpty(certificationRecord.getCertificatedOrg())) {
				// 判断委托单位、器具名称、器具型号、出厂编号/设备编号、检定日期、有效日期、检定结论、检定员、检定部门 是否有为空值的项
				// 如为空 则放到otherIsNullList
				// 设置导入标记为 其他原因
				certificationRecord.setImportMark("4");
				certificationRecord.setRemark("委托单位、器具名称、器具型号、出厂编号/设备编号、检定日期、有效日期、检定结论、检定员、检定部门 中有空值");
				analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
			} else if (!isPoliceMark(certificationRecord.getOrgName(), policeMarkArr)) {
				// 根据委托单位字段判断是否为交警设备
				// 设置导入标记为 非交警设备
				certificationRecord.setImportMark("3");
				certificationRecord.setRemark("非交警设备");
				analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
			} else if (StringUtil
					.isNullOrEmpty(equipmentTypeByDeviceNameMap.get(certificationRecord.getDeviceName()))) {
				// 判断器具名称是否有对应的设备类型
				// 设置导入标记为 其他原因
				certificationRecord.setImportMark("4");
				certificationRecord.setRemark("器具名称无对应的设备类型");
				analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
			} else {
				// 更新设备类型编码
				certificationRecord
						.setEquipmentType(equipmentTypeByDeviceNameMap.get(certificationRecord.getDeviceName()));
				// 判断证书是否合格
				if (!"合格".equals(certificationRecord.getCertificatedResult())) {
					// 设置导入标记为 其他原因
					certificationRecord.setImportMark("4");
					certificationRecord.setRemark("检定结论 不是合格");
					analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
				} else {
					// 判断设备编号长度
					if (certificationRecord.getEquipmentNbr().length() > 32) {
						// 设置导入标记为 其他原因
						certificationRecord.setImportMark("4");
						certificationRecord.setRemark("设备编号长度过长");
						analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
					} else if (!verficationEquipmentNbrList.contains(certificationRecord.getEquipmentNbr().trim())) {
						certificationRecord.setImportMark("2");
						certificationRecord.setRemark("未登记设备");
						analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
					} else {
						certificationRecord.setImportMark("1");
						certificationRecord.setRemark("导入成功");
						analyzeResultMap.put(certificationRecord.getCertificationNbr(), certificationRecord);
					}
					// 已登记与未登记的设备放入certification中

				}

			}
		}
	}

	// /**
	// * 解析excel 导入设备年检证书信息
	// * 支持解析多个excel、每个excel多sheet
	// * 每个excel路径以逗号（,）分割
	// * 导入excel的列：序号 证书编号 委托日期 委托单位 器具名称 器具型号 出厂编号 设备编号 检定日期 有效日期 检定结论 检定员 核验员
	// 批准人 打印日期 部门 制造厂商 添加人 打印人 检定费 修理费 校准费 配件费 服务费 工作量
	// * 用到的列：证书编号 委托单位 器具名称 器具型号 出厂编号/设备编号 检定日期 有效日期 检定结论 检定员 部门
	// *
	// * @Title: importCertification
	// * @param certificationInfoDto 包含上传文件的网络路径
	// * @return CertificationInfoDto
	// * @throws Exception
	// */
	// @RequestMapping(value = "/importCertification")
	// @ApiOperation(value="importCertification",notes="解析excel
	// 导入设备年检证书信息",httpMethod="GET")
	// public CertificationInfoDto addImportCertification(CertificationInfoDto
	// certificationInfoDto) throws Exception {
	//
	//
	// CertificationInfoDto resultCertificationInfoDto = new
	// CertificationInfoDto();
	//
	// try {
	// //excel路径
	// String path = certificationInfoDto.getUpLoadPath();
	// if (path == null || "".equals(path.trim())) {
	// resultCertificationInfoDto.setErrMsg("上传文件异常，请重新上传");
	// //System.out.println("上传文件异常，请重新上传");
	// return resultCertificationInfoDto;
	// }
	// //上传多个excel时以逗号分割
	// String[] excelPathArr = path.split(",");
	// List<String[]> exceList = new ArrayList<String[]>();
	// //分别解析上传的excel
	// for (String excelPath : excelPathArr) {
	//
	// //转义文件路径（网络路径）中的特殊字符
	// excelPath = excelPath.replaceAll("%", "%25");
	// excelPath = excelPath.replaceAll(" ", "%20");
	// excelPath = excelPath.replaceAll("\\+", "%2B");
	// excelPath = excelPath.replaceAll("\\?", "%3F");
	// excelPath = excelPath.replaceAll("#", "%23");
	// excelPath = excelPath.replaceAll("&", "%26");
	// //对路径中的中文及中文符号进行转码
	// char[] charArray = excelPath.toCharArray();
	// for (char c : charArray) {
	// if (isChinese(c)) {
	// excelPath =
	// excelPath.replaceAll(String.valueOf(c),URLEncoder.encode(String.valueOf(c),
	// "UTF-8"));
	// }
	// }
	// URL url;
	// try {
	// url = new URL(excelPath);
	// } catch (Exception e) {
	// e.printStackTrace();
	// resultCertificationInfoDto.setErrMsg("上传文件异常，请检查文件名是否包含特殊字符");
	// return resultCertificationInfoDto;
	// }
	// try {
	// URLConnection conn = url.openConnection();
	// //获取一个excel的解析值
	// List<List<String[]>> oneExcelList =
	// ExcelUtil.readExcel(conn.getInputStream());
	// //判断解析的excel是否为空 为空则跳过
	// if (oneExcelList == null || oneExcelList.size() <= 0) {
	// continue;
	// }
	// //判断解析的excel的每个sheet数据是否正常
	// for (List<String[]> list : oneExcelList) {
	// //判断解析的excel的每个sheet是否为空
	// if (list == null || list.size() <= 0) {
	// continue;
	// }
	// //sheet不为空 则判断长度是否正常
	// if (list.get(0).length < 16) {
	// resultCertificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
	// return resultCertificationInfoDto;
	// }
	// //将每个sheet的值放入统一的List中
	// exceList.addAll(list);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// resultCertificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
	// return resultCertificationInfoDto;
	// }
	// }
	// //判断总体上传的excel中是否有数据
	// if (exceList == null || exceList.size() <= 0 || exceList.get(0) == null
	// || exceList.get(0).length < 16) {
	// resultCertificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
	// return resultCertificationInfoDto;
	// }
	// //存放解析后的excel中的检定证书信息转换成的检定证书记录对象
	// List<CertificationRecord> certificationList = new
	// ArrayList<CertificationRecord>();
	// Date createDate = new Date();
	// //导入批次
	// String importDate = DateUtil.parseFormatDate(new Date());
	// //生成操作流水
	// String importAccept = StringUtil.generateUUID();
	// //日期格式正则表达式
	// String regex = "[0-9]{1,4}-[0-9]{1,2}-[0-9]{1,2}";
	//
	// //将解析后的excel中有用的信息转成检定证书对象
	// for (String[] row : exceList) {
	//
	// CertificationRecord certificationRecord = new CertificationRecord();
	//
	// certificationRecord.setCertificationNbr(row[1]); //证书编号
	// certificationRecord.setOrgName(row[3]); //委托单位
	// certificationRecord.setDeviceName(row[4]); //器具名称
	// certificationRecord.setModelName(row[5]); //器具型号
	// certificationRecord.setEquipmentNbr(row[6] == null || "".equals(row[6]) ?
	// row[7] : row[6]); //出厂编号/设备编号
	// certificationRecord.setCertificatedDate(DateUtil.parseFormatDate(row[8]));
	// //检定日期
	// certificationRecord.setExpireDate(DateUtil.parseFormatDate(row[9]));
	// //有效日期
	// certificationRecord.setCertificatedResult(row[10]); //检定结论
	// certificationRecord.setCertificatedPerson(row[11]); //检定员
	// certificationRecord.setCertificatedOrg(row[15]); //部门
	// certificationRecord.setCreatePerson(certificationInfoDto.getCurrentUserId());
	// certificationRecord.setCreateTime(createDate);
	// certificationRecord.setImportAccept(importAccept);
	// certificationRecord.setImportDate(importDate);
	// certificationList.add(certificationRecord);
	//
	// //校验excel值是否超长 超长结束导入
	// if (certificationRecord.getCertificationNbr().length() > 32) {
	// resultCertificationInfoDto.setErrMsg("[证书编号]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getOrgName().length() > 120) {
	// resultCertificationInfoDto.setErrMsg("[委托单位]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getDeviceName().length() > 120) {
	// resultCertificationInfoDto.setErrMsg("[器具名称]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getModelName().length() > 120) {
	// resultCertificationInfoDto.setErrMsg("[器具型号]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getEquipmentNbr().length() > 32) {
	// resultCertificationInfoDto.setErrMsg("[出厂编号/设备编号]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getCertificatedResult().length() > 5) {
	// resultCertificationInfoDto.setErrMsg("[检定结论]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getCertificatedPerson().length() > 20) {
	// resultCertificationInfoDto.setErrMsg("[检定员]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (certificationRecord.getCertificatedOrg().length() > 120) {
	// resultCertificationInfoDto.setErrMsg("[检定部门]列有值超长");
	// return resultCertificationInfoDto;
	// }else if (!StringUtil.isNullOrEmpty((Object)row[8]) &&
	// !Pattern.matches(regex, row[8])) {
	// resultCertificationInfoDto.setErrMsg("[检定日期]列有值不是日期格式");
	// return resultCertificationInfoDto;
	// }else if (!StringUtil.isNullOrEmpty((Object)row[9]) &&
	// !Pattern.matches(regex, row[9])) {
	// resultCertificationInfoDto.setErrMsg("[有效日期]列有值不是日期格式");
	// return resultCertificationInfoDto;
	// }
	// }
	// //分析结果map key为证书编码 用于去重
	// Map<String, CertificationRecord> analyzeResultMap = new HashMap<String,
	// CertificationRecord>();
	// //证书编号为空存list
	// List<CertificationRecord> nbrIsNullList = new
	// ArrayList<CertificationRecord>();
	// // 将器具名称(器具名称在表t_sys_code的remark字段中用逗号分割)、设备类型编码放到一个map<器具名称,设备类型编码>中
	// 方便获取
	// Map<String, String> equipmentTypeByDeviceNameMap = new HashMap<String,
	// String>();
	// List<Code> codeList = sysCodeService.codeListOfType("488");
	// for (Code sysCode : codeList) {
	// String deviceNames = sysCode.remark;
	// if (!StringUtil.isNullOrEmpty(deviceNames)) {
	// // (多个器具名称对应一个设备类型,在表t_sys_code的remark字段中用逗号分割)
	// String[] deviceNameArr = deviceNames.split(",");
	// for (String deviceName : deviceNameArr) {
	// if (!StringUtil.isNullOrEmpty(deviceName)) {
	// equipmentTypeByDeviceNameMap.put(deviceName, sysCode.codeNo);
	// }
	// }
	// }
	// }
	// // (判断是否为交警设备的关键字配置在表t_sys_code的remark字段中用逗号分割)
	// Code code = sysCodeService.codeOfId("490", "3");
	// String[] policeMarkArr = StringUtil.isNullOrEmpty(code.remark) ? null :
	// code.remark.split(",");
	// //获取所有需登记的设备编码
	// List<String> verficationEquipmentNbrList =
	// certificationService.getAllVerificationEquipmentNbr();
	// //循环处理证书信息是否符合导入条件
	// for (CertificationRecord certificationRecord : certificationList) {
	// // 判断证书编号是否为空 如为空 则放到nbrIsNullList
	// if (StringUtil.isNullOrEmpty(certificationRecord.getCertificationNbr()))
	// {
	// // 证书编号为空 设置导入标记为 其他原因
	// certificationRecord.setRemark("证书编号为空");
	// certificationRecord.setImportMark("4");
	// nbrIsNullList.add(certificationRecord);
	// } else if (StringUtil.isNullOrEmpty(certificationRecord.getOrgName())
	// || StringUtil.isNullOrEmpty(certificationRecord.getDeviceName())
	// || StringUtil.isNullOrEmpty(certificationRecord.getModelName())
	// || StringUtil.isNullOrEmpty(certificationRecord.getEquipmentNbr())
	// || certificationRecord.getCertificatedDate() == null
	// || certificationRecord.getExpireDate() == null
	// || StringUtil.isNullOrEmpty(certificationRecord.getCertificatedResult())
	// || StringUtil.isNullOrEmpty(certificationRecord.getCertificatedPerson())
	// || StringUtil.isNullOrEmpty(certificationRecord.getCertificatedOrg())) {
	// // 判断委托单位、器具名称、器具型号、出厂编号/设备编号、检定日期、有效日期、检定结论、检定员、检定部门 是否有为空值的项 如为空
	// 则放到otherIsNullList
	// // 设置导入标记为 其他原因
	// certificationRecord.setImportMark("4");
	// certificationRecord.setRemark("委托单位、器具名称、器具型号、出厂编号/设备编号、检定日期、有效日期、检定结论、检定员、检定部门
	// 中有空值");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// }else if (!isPoliceMark(certificationRecord.getOrgName(), policeMarkArr))
	// {
	// // 根据委托单位字段判断是否为交警设备
	// // 设置导入标记为 非交警设备
	// certificationRecord.setImportMark("3");
	// certificationRecord.setRemark("非交警设备");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// } else if (StringUtil
	// .isNullOrEmpty(equipmentTypeByDeviceNameMap.get(certificationRecord.getDeviceName())))
	// {
	// // 判断器具名称是否有对应的设备类型
	// // 设置导入标记为 其他原因
	// certificationRecord.setImportMark("4");
	// certificationRecord.setRemark("器具名称无对应的设备类型");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// } else {
	// //更新设备类型编码
	// certificationRecord
	// .setEquipmentType(equipmentTypeByDeviceNameMap.get(certificationRecord.getDeviceName()));
	// // 判断证书是否合格
	// if (!"合格".equals(certificationRecord.getCertificatedResult())) {
	// // 设置导入标记为 其他原因
	// certificationRecord.setImportMark("4");
	// certificationRecord.setRemark("检定结论 不是合格");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// } else {
	// //判断设备编号长度
	// if (certificationRecord.getEquipmentNbr().length() > 32) {
	// // 设置导入标记为 其他原因
	// certificationRecord.setImportMark("4");
	// certificationRecord.setRemark("设备编号长度过长");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// } else if (!verficationEquipmentNbrList
	// .contains(certificationRecord.getEquipmentNbr().trim())) {
	// certificationRecord.setImportMark("2");
	// certificationRecord.setRemark("未登记设备");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// } else {
	// certificationRecord.setImportMark("1");
	// certificationRecord.setRemark("导入成功");
	// analyzeResultMap.put(certificationRecord.getCertificationNbr(),
	// certificationRecord);
	// }
	// //已登记与未登记的设备放入certification中
	//
	// }
	//
	// }
	// }
	// //System.out.println("excel analyzeResultMap : " +
	// analyzeResultMap.size());
	// //System.out.println("excel nbrIsNullList : " + nbrIsNullList.size());
	// //获取本批次入库记录的证书编号 用于与本次导入的数据做比对 已存在则进行更新操作
	// List<String> certificationRecordImportDateList = certificationService
	// .getCertificationNbrListByImportDate(importDate);
	// //存放用于插入检定证书信息记录表的数据
	// List<CertificationRecord> insertCertificationRecordList = new
	// ArrayList<CertificationRecord>();
	// //存放用于更新检定证书信息记录表的数据
	// List<CertificationRecord> updateCertificationRecordList = new
	// ArrayList<CertificationRecord>();
	// //循环单条导入到检定证书信息记录表
	// for (Map.Entry<String, CertificationRecord> entry :
	// analyzeResultMap.entrySet()) {
	// //判断是否该证书在本批次是否已经入库
	// if (!certificationRecordImportDateList.contains(entry.getKey())) {
	// entry.getValue().setRecordId(StringUtil.generateUUID());
	// insertCertificationRecordList.add(entry.getValue());
	// // certificationService.addCertifitionRecord(entry.getValue());
	// } else {
	// entry.getValue().setUpdatePerson(entry.getValue().getCreatePerson());
	// entry.getValue().setUpdateTime(entry.getValue().getCreateTime());
	// entry.getValue().setCreatePerson(null);
	// entry.getValue().setCreateTime(null);
	// updateCertificationRecordList.add(entry.getValue());
	// // certificationService.updateCertifitionRecord(entry.getValue());
	// }
	// }
	// insertCertificationRecordList.addAll(nbrIsNullList);
	// //每次插入50条
	// for (int i = 0; i < insertCertificationRecordList.size(); i += 50) {
	// certificationService.insertCertificationRecordBatch(
	// insertCertificationRecordList.subList(i, i + 50 <=
	// insertCertificationRecordList.size() ? i + 50
	// : insertCertificationRecordList.size()));
	// }
	// //每次更新50条
	// for (int i = 0; i < updateCertificationRecordList.size(); i += 50) {
	// certificationService.updateCertificationRecordBatchByNbrImportDate(
	// updateCertificationRecordList.subList(i, i + 50 <=
	// updateCertificationRecordList.size() ? i + 50
	// : updateCertificationRecordList.size()));
	// }
	// Map<String, Object> paramsMap = new HashMap<String, Object>();
	// paramsMap.put("importDate", importDate);
	// paramsMap.put("importAccept", importAccept);
	// paramsMap.put("createDate", createDate);
	// paramsMap.put("currentUserId", certificationInfoDto.getCurrentUserId());
	// //添加修设备检定证书信息表
	// certificationService.insertCertificationByRecord(paramsMap);
	// certificationService.updateCertificationByRecord(paramsMap);
	// Map<String, Object> importResultMap =
	// certificationService.getImportResult(importDate, importAccept);
	// if (importResultMap == null) {
	// resultCertificationInfoDto.setErrMsg("解析文件异常，请检查文件内容是否正常");
	// //System.out.println("解析文件异常，请检查文件内容是否正常");
	// return resultCertificationInfoDto;
	// } else {
	// resultCertificationInfoDto.setImportSucessCount(
	// Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("IMPORTSUCESSCOUNT"))
	// ? "0"
	// : String.valueOf(importResultMap.get("IMPORTSUCESSCOUNT"))));
	// resultCertificationInfoDto.setUnRegisteredCount(
	// Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("UNREGISTEREDCOUNT"))
	// ? "0"
	// : String.valueOf(importResultMap.get("UNREGISTEREDCOUNT"))));
	// resultCertificationInfoDto.setUnPoliceCount(
	// Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("UNPOLICECOUNT"))
	// ? "0"
	// : String.valueOf(importResultMap.get("UNPOLICECOUNT"))));
	// resultCertificationInfoDto
	// .setOtherCount(Integer.parseInt(StringUtil.isNullOrEmpty(importResultMap.get("OTHERCOUNT"))
	// ? "0" : String.valueOf(importResultMap.get("OTHERCOUNT"))));
	// resultCertificationInfoDto
	// .setRepeatCount(certificationList.size() - analyzeResultMap.size() -
	// nbrIsNullList.size());
	// resultCertificationInfoDto.setErrMsg("SUCCESS");
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// throw new RuntimeException("解析文件异常，请检查文件中数据是否有异常！");
	// }
	//
	// return resultCertificationInfoDto;
	//
	// }

	@RequestMapping(value = "/getImportDateList")
	@ApiOperation(value = "getImportDateList", notes = "查询导入批次列表", httpMethod = "GET")
	public List<String> getImportDateList() throws Exception {
		return certificationService.getImportDateList();
	}

	@RequestMapping(value = "/downLoadExcelTemplate")
	@ApiOperation(value = "downLoadExcelTemplate", notes = "下载检定同步excel模板", httpMethod = "GET")
	public Object downLoadExcelTemplate() throws Exception {
		JSONObject obj = new JSONObject();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
		obj.put("url", basePath + request.getContextPath() + "/file/检定同步导入excel模板.xls");
		return obj;
	}

	/**
	 * 校验器具名称中是否包含交警关键字
	 *
	 * @param deviceName
	 * @param policeMarkArr
	 * @return boolean
	 */
	private boolean isPoliceMark(String orgName, String[] policeMarkArr) {
		if (orgName == null || policeMarkArr == null || policeMarkArr.length <= 0) {
			return false;
		}
		for (String policeMark : policeMarkArr) {
			if (orgName.contains(policeMark)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @Title: parseToJson @Description: 转为Json @param pageRs @param obj @return
	 * JSONObject返回类型 @throws
	 */
	private JSONObject parseToJson(Page<?> pageRs, Object obj) {
		JSONObject jsonObj = new JSONObject();
		jsonObj.put("error", "");
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("total", pageRs.getTotal());
		jsonObject.put("rows", obj);
		jsonObj.put("result", jsonObject);
		return jsonObj;
	}

	// 根据Unicode编码完美的判断中文汉字和符号
	private static boolean isChinese(char c) {
		Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
		if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
				|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
				|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
				|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
				|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
			return true;
		}
		return false;
	}

}
