package cy.its.violation.rest.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;

import cy.its.violation.rest.dto.DataGridResult;
import cy.its.violation.rest.dto.OperationCondition;
import cy.its.violation.rest.dto.OperationResult;
import cy.its.violation.rest.dto.StatisticChartData;
import cy.its.violation.rest.dto.StatisticCondition;

/**
 * @title:IViolationAction.java
 * @Package:cy.its.violation.rest.action
 * @Description:TODO
 * @author:jinhb@cychina.cn
 * @date: 2015年10月9日 上午10:24:21
 * @version v1.0
 */
public interface IViolationAction {

	/**
	 * 查询分页数据
	 * 
	 * @param form
	 * @return
	 * @throws Exception
	 */
	public DataGridResult queryViolationSet(HttpServletRequest request, OperationCondition form) throws Exception;

	/**
	 * 查询分页数据
	 * 
	 * @param form
	 * @return
	 */
	public JSONObject exportViolationSet(HttpServletRequest request) throws Exception;

	/**
	 * 查询上传日志
	 * 
	 * @param form
	 * @return
	 */
	public JSONObject queryUploadLog(HttpServletRequest request);

	/**
	 * 获取统计数据
	 * 
	 * @param form
	 * @return
	 */
	public StatisticChartData getStatisticChartData(HttpServletRequest request);

	/**
	 * 获取违法数据列表
	 * 
	 * @param form
	 * @return
	 */
	public List getStatisticDataForCollateFilter(StatisticCondition opCondition);

	/**
	 * 录入新违法记录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult inputConfirmDigitalViolation(HttpServletRequest request) throws Exception;

	/**
	 * 录入新违法记录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult inputBulkImportViolation(HttpServletRequest request) throws Exception;

	/**
	 * 记录修改操作
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult abandonViolation(HttpServletRequest request) throws Exception;

	/**
	 * 记录修改操作
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult filterViolation(HttpServletRequest request) throws Exception;

	/**
	 * 记录修改操作
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult viewViolationOfIdWithLock(HttpServletRequest request) throws Exception;

	/**
	 * 记录修改操作
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult isWhiteVeh(HttpServletRequest request) throws Exception;

	/**
	 * 记录修改操作
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult unLockViolation(HttpServletRequest request) throws Exception;

	/**
	 * 重录
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult reInputViolation(HttpServletRequest request) throws Exception;

	/**
	 * 录入
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult inputViolationForConfirm(HttpServletRequest request) throws Exception;

	/**
	 * 上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult uploadViolation(HttpServletRequest request) throws Exception;

	/**
	 * 删除
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult deleteViolation(HttpServletRequest request) throws Exception;

	/**
	 * 还原
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult restoreViolation(HttpServletRequest request) throws Exception;

	/**
	 * 修改并重新上传
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	public OperationResult verifyUploadFailViolation(HttpServletRequest request) throws Exception;

}
