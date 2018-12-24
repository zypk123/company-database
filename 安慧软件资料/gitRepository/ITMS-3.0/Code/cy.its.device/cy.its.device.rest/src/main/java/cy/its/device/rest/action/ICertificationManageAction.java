package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.CertificationInfoDto;

public interface ICertificationManageAction {
	
	/**
	 * 添加保存年检信息
	 * @param form 年检信息(包含设备编码)
	 *  @return int  0:添加失败；1：添加成功；2：证书编号库中已存在；
	 */
	public int addCertificationInfo(CertificationInfoDto form) throws Exception;
	
	/**
	 * 查询设备年检信息
	 * 
	 * @param equipmentNbr 设备编码
	 * @param order  设备列表按有效期排序方式  不传 默认为降序
	 * @throws Exception
	 * @return List<CertificationInfoDto>
	 */
	public List<CertificationInfoDto> queryAllCertification(@RequestParam("equipmentNbr") String equipmentNbr,@RequestParam(value="order",required=false,defaultValue="1") String order) throws Exception;
	
	/**
	 * 查询某年检信息
	 * @param certificationId 年检记录ID
	 */
	public CertificationInfoDto queryCertificationById(String certificationId) throws Exception;
	
	/**
	 * 编辑某年检信息
	 * @param certificationId 年检记录ID
	 */
	public int editCertification(CertificationInfoDto form) throws Exception;
	
	/**
	 * 删除某年检信息
	 * @param certificationId 年检记录ID
	 */
	public int removeCertification(String certificationId) throws Exception;
	
	/**
	 * 查询已登记设备的最新年检信息
	 * @param certificationInfoDto  设备年检证书信息
	 * @throws Exception
	 * @return Object  设备年检列表 jsond对象
	 */
	public Object getEquipmentCertificationList(CertificationInfoDto certificationInfoDto) throws Exception;
	
	/**
	 * 查询未登记设备的最新年检信息
	 * @param certificationInfoDto  设备年检证书信息
	 * @throws Exception
	 * @return Object  设备年检列表 jsond对象
	 */
	public Object getNoEquipmentCertificationList(CertificationInfoDto certificationInfoDto) throws Exception;
	
	/**
	 * 查询批量导入的各个批次的年检信息
	 * @param certificationInfoDto  设备年检证书信息
	 * @throws Exception
	 * @return Object  设备年检列表 jsond对象
	 */
	public Object getCertificatioRecordnList(CertificationInfoDto certificationInfoDto) throws Exception;
	
	/**
	 * 获取上传的excel路径 解析excel 导入excel中的设备检定证书信息到数据库中
	 * @param certificationInfoDto  包含上传路径
	 * @throws Exception
	 * @return CertificationInfoDto  导入结果
	 */
	public CertificationInfoDto addImportCertification(CertificationInfoDto certificationInfoDto) throws Exception;
	
	/**
	 * 查询导入批次列表
	 *
	 * @throws Exception
	 * @return List<String>
	 */
	public List<String> getImportDateList() throws Exception;

	
	/**
	 * 根据传值查询检定证书导入记录信息 生成execl，返回下载路径
	 *
	 * @param certificationInfoDto
	 * @return 
	 * @throws Exception
	 */
	public Object exportCertificatioRecordExcel(CertificationInfoDto certificationInfoDto) throws Exception;
	
	
	/**
	 * 下载检定同步excel模板
	 *
	 * @Title: downLoadExcelTemplate
	 * @return Object
	 * @throws Exception
	 */
	public Object downLoadExcelTemplate() throws Exception;
	
}
