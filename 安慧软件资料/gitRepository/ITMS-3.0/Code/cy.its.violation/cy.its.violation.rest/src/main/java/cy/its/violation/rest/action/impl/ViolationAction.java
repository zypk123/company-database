package cy.its.violation.rest.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.common.offLine.domain.OffLineExport;
import cy.its.common.offLine.service.IOffLineExportService;
import cy.its.platform.common.utils.Log4jFactoryProxy;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.service.IDutyService;
import cy.its.syscfg.domain.service.ISysCodeService;
import cy.its.violation.domain.criteria.ConstantCode;
import cy.its.violation.domain.criteria.ViolationCriteria;
import cy.its.violation.domain.model.violation.FilterStatistic;
import cy.its.violation.domain.model.violation.UnFilterStatistic;
import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.domain.model.violation.ViolationInputConfirm;
import cy.its.violation.domain.model.violation.ViolationInputDigital;
import cy.its.violation.domain.service.IVioActionService;
import cy.its.violation.domain.service.IViolationService;
import cy.its.violation.rest.action.IViolationAction;
import cy.its.violation.rest.dto.Data;
import cy.its.violation.rest.dto.DataGridResult;
import cy.its.violation.rest.dto.OperationCondition;
import cy.its.violation.rest.dto.OperationParam;
import cy.its.violation.rest.dto.OperationResult;
import cy.its.violation.rest.dto.Results;
import cy.its.violation.rest.dto.Series;
import cy.its.violation.rest.dto.StatisticChartData;
import cy.its.violation.rest.dto.StatisticCondition;
import cy.its.violation.rest.dto.ViolationDto;
import cy.its.violation.rest.util.ViolationExport;

/**
 * @title:ViolationAction.java
 * @Package:cy.its.violation.rest.action.impl
 * @Description:TODO
 * @author:jinhb@cychina.cn
 * @date: 2015年10月9日 上午10:24:08
 * @version v1.0
 * @Revsion: $Rev: 6696 $
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015
 */

/**
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/violation")
public class ViolationAction implements IViolationAction {

	@Autowired
	IViolationService violationService;

	@Autowired
	ISysCodeService sysCodeService;

	@Autowired
	IVioActionService vioActionService;

	@Autowired
	IDutyService dutyService;

	@Autowired
	IOffLineExportService offLineExportService;

	/*
	 * 日志
	 */
	private Log4jFactoryProxy log = Log4jFactoryProxy.getSingleton(ViolationAction.class);
	//
	private SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * (non-Javadoc)
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#queryViolationSet(cy.its.
	 *      violation.rest.dto.ViolationQueryForm)
	 */
	@RequestMapping(value = "/queryViolationSet")
	public DataGridResult queryViolationSet(HttpServletRequest request,
			@ModelAttribute("opCondition") OperationCondition opCondition) throws Exception {
		DataGridResult resu = new DataGridResult();
		try {
			ViolationCriteria criteria = ConvertContionToCriteria(opCondition);
			// domain接口调用
			resu.result = new Results();

			List<Violation> lst = violationService.findViolations(criteria);

			resu.result.rows = lst.stream().map((c) -> {
				ViolationDto dto = new ViolationDto();
				ObjectMapUtils.parseObject(dto, c);
				// if (StringUtil.isNullOrEmpty(dto.image)) {
				// dto.image =
				// "data/violationMgr/img/overSpeed_0.jpg;data/violationMgr/img/overSpeed_1.jpg";
				// } else {
				// // 针对图片录入
				// // dto.image =
				// // dto.image.replace("127.0.0.1:8080/ControlPlatform",
				// // ServerUtil.getFileUrl(request));
				// String[] imagesArr = dto.image.split(";");
				// String newImageUrl = "";
				// for (String imageUrl : imagesArr) {
				// newImageUrl += ServerUtil.getFileUrl(request) +
				// "/VioImage?imageUrl=" + imageUrl + ";";
				// }
				// newImageUrl = newImageUrl.substring(0, newImageUrl.length() -
				// 1);
				// dto.image = newImageUrl;
				//
				// }
				dto.setViolationTime(longTimeFormat.format(c.getViolationTime()));
				if (c.updateTime != null) {
					dto.setUpdateTime(longTimeFormat.format(c.updateTime));
				}
				if (c.entryTime != null) {
					dto.setEntryTime(longTimeFormat.format(c.entryTime));
				}
				if (c.redLightBeginTime != null) {
					dto.setRedLightBeginTime(longTimeFormat.format(c.redLightBeginTime));
				}
				if (c.redLightEndTime != null) {
					dto.setRedLightEndTime(longTimeFormat.format(c.redLightEndTime));
				}
				return dto;
			}).collect(Collectors.toList());
			resu.result.total = violationService.countViolations(criteria);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e.getMessage());
			throw new Exception("查询异常");
		}
		return resu;
	}

	/**
	 * 根据传入的条件继续根据业务需求重置部分查询条件
	 * 
	 * @param opCondition
	 */
	private void ResetCondition(OperationCondition opCondition) {
		if (opCondition != null && !StringUtil.isNullOrEmpty(opCondition.operationName)) {

			switch (opCondition.operationName) {
			case "queryVioForTidy":// 一般录入
			case "queryVioForSpeedTidy":// 超速录入
			case "queryVioForRedlightTidy":// 闯红灯录入
			case "queryVioForLaneOccupyTidy":// 大车占道录入
			case "queryVioForVioParkTidy":// 违停录入
			case "queryVioForUnTiedTidy":// 未系安全带录入
			case "queryVioForNightDriveTidy":// 客车夜间禁行录入
			case "queryVioFormergencyOccupyTidy":// 占用应急车道录入
				if (StringUtil.isNullOrEmpty(opCondition.statusFlag)) {
					opCondition.statusFlag = ConstantCode.StatusFlag.NEW + "," + ConstantCode.StatusFlag.FILTERED + ","
							+ ConstantCode.StatusFlag.RECONFIRM;
				}
				break;
			case "queryViolationSetForUploadQuery":
				opCondition.statusFlag = ConstantCode.StatusFlag.CONFIRMED;
				break;
			case "queryViolationSetForSpecialManager":
				opCondition.statusFlag = ConstantCode.StatusFlag.DISCARDED;
				if (StringUtil.isNullOrEmpty(opCondition.specialVehType)) {
					opCondition.isIncludeSpecial = "1";
				}
				break;
			case "queryViolationSetForDiscardManager":
				opCondition.statusFlag = ConstantCode.StatusFlag.DISCARDED;
				break;
			default:
				break;
			}

			// 根据业务规则重置部分查询条件
			switch (opCondition.operationName) {
			case "queryVioForSpeedTidy":// 超速录入
				opCondition.violationType = ConstantCode.ViolationType.CHAO_GS + ","
						+ ConstantCode.ViolationType.QU_JCS;
				break;
			case "queryVioForRedlightTidy":// 闯红灯录入
				opCondition.violationType = ConstantCode.ViolationType.CHUANG_HD;
				break;
			case "queryVioForLaneOccupyTidy":// 大车占道录入
				opCondition.violationType = ConstantCode.ViolationType.DA_CZD;
				break;
			case "queryVioForVioParkTidy":// 违停录入
				opCondition.violationType = ConstantCode.ViolationType.WEI_ZTC;
				break;
			case "queryVioForUnTiedTidy":// 未系安全带录入
				opCondition.violationType = ConstantCode.ViolationType.WEI_JAQD;
				break;
			case "queryVioForNightDriveTidy":// 客车夜间禁行录入
				opCondition.violationType = ConstantCode.ViolationType.JIN_X;
				break;
			case "queryVioForEmergencyOccupyTidy":// 占用应急车道录入(违法占道)
				opCondition.violationType = ConstantCode.ViolationType.WEI_FZD;
				break;
			default:
				break;
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#GetViolationSet(cy.its.
	 * violation.rest.dto.ViolationQueryForm)
	 */
	@RequestMapping(value = "/getStatisticDataForCollateFilter")
	public List<Violation> getStatisticDataForCollateFilter(
			@ModelAttribute("opCondition") StatisticCondition opCondition) {
		// TODO Auto-generated method stub
		List<Violation> resu = new ArrayList<Violation>();

		try {
			ViolationCriteria criteria = new ViolationCriteria();
			ObjectMapUtils.parseObject(criteria, opCondition);

			opCondition.statusFlag = "0";

			if (!StringUtil.isNullOrEmpty(opCondition.statusFlag) && opCondition.statusFlag.contains(",")) {
				String[] arr = opCondition.statusFlag.split(",");

				criteria.lstStatusFlag = Arrays.asList(arr);
				criteria.statusFlag = "";
			}

			if (!StringUtil.isNullOrEmpty(opCondition.violationType)) {
				String[] arr = opCondition.violationType.split(",");

				criteria.lstViolationType = Arrays.asList(arr);
			}

			Date now = new Date();
			switch (opCondition.dataIndex) {
			case 0:// 近15天
				criteria.violationBeginTime = longTimeFormat.format(new Date(now.getTime() - 15 * 24 * 60 * 60 * 1000))
						+ " 00:00:00";
				criteria.violationEndTime = longTimeFormat.format(new Date());
				break;
			case 1:// 近7天
				criteria.violationBeginTime = longTimeFormat.format(new Date(now.getTime() - 7 * 24 * 60 * 60 * 1000))
						+ " 00:00:00";
				criteria.violationEndTime = longTimeFormat.format(new Date());
				break;
			case 2:// 第3天
				criteria.violationBeginTime = longTimeFormat.format(new Date(now.getTime() - 2 * 24 * 60 * 60 * 1000))
						+ " 00:00:00";
				criteria.violationEndTime = longTimeFormat.format(new Date(now.getTime() - 1 * 24 * 60 * 60 * 1000))
						+ " 00:00:00";
				break;
			case 3:// 第2天
				criteria.violationBeginTime = longTimeFormat.format(new Date(now.getTime() - 1 * 24 * 60 * 60 * 1000))
						+ " 00:00:00";
				criteria.violationEndTime = longTimeFormat.format(now) + " 23:59:59";
				break;
			case 4:// 今天
				criteria.violationBeginTime = longTimeFormat.format(now) + " 00:00:00";
				criteria.violationEndTime = longTimeFormat.format(new Date());
				break;
			default:
				break;
			}
			switch (opCondition.seriesName) {
			case "闯红灯":
				break;
			case "其他":
				break;
			case "超低速":
				break;
			case "超高速":
				break;
			case "区间超速":
				break;
			default:
				break;
			}
			resu = violationService.findViolations(criteria);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return resu;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#
	 * GetUnFilterStatisticChartData(cy.its.violation.rest.dto.
	 * ViolationQueryForm)
	 */
	@RequestMapping(value = "/getStatisticChartData")
	public StatisticChartData getStatisticChartData(HttpServletRequest request) {
		StatisticChartData data = new StatisticChartData();
		String param = request.getParameter("param");
		OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
		if (paramObj != null && !StringUtil.isNullOrEmpty(paramObj.opCondition.operationName)) {

			ResetCondition(paramObj.opCondition);
			// 与domian层查询实体转换
			ViolationCriteria criteria = new ViolationCriteria();

			ObjectMapUtils.parseObject(criteria, paramObj.opCondition);

			switch (paramObj.opCondition.operationName) {

			case "getUnfilterStatistic":

				// data.Legend = Arrays.asList("超速", "闯红灯", "大车占道", "违章停车",
				// "未系安全带", "占用应急车道");
				// data.Category = Arrays.asList("近15天", "近7天", "09-05",
				// "09-06", "09-07");
				//
				// data.Series.add(new Series("超速", "bar", Arrays.asList(1114,
				// 1102, 651, 114, 88), null));
				// data.Series.add(new Series("闯红灯", "bar", Arrays.asList(1200,
				// 650, 330, 150, 78), null));
				// data.Series.add(new Series("大车占道", "bar", Arrays.asList(654,
				// 320, 165, 88, 41), null));
				// data.Series.add(new Series("违章停车", "bar", Arrays.asList(788,
				// 432, 223, 141, 46), null));
				// data.Series.add(new Series("未系安全带", "bar", Arrays.asList(984,
				// 520, 265, 154, 88), null));
				// data.Series.add(new Series("占用应急车道", "bar",
				// Arrays.asList(326, 124, 65, 24, 8), null));
				//

				paramObj.opCondition.statusFlag = "0";
				if (!StringUtil.isNullOrEmpty(paramObj.opCondition.violationType)) {
					String[] arr = paramObj.opCondition.violationType.split(",");
					criteria.lstViolationType = Arrays.asList(arr);
				}
				List<UnFilterStatistic> lstResult = violationService.findUnFilterStatistic(criteria);
				List<String> legengLst = new ArrayList<>();
				for (UnFilterStatistic obj0 : lstResult) {
					data.Series.add(new Series(obj0.getViolationType(), "bar",
							Arrays.asList(obj0.getD15(), obj0.getD7(), obj0.getD3(), obj0.getD2(), obj0.getD1()),
							null));
					legengLst.add(obj0.getViolationType());
				}
				data.Legend = legengLst;
				SimpleDateFormat longTimeFormat = new SimpleDateFormat("MM-dd");
				Date now = new Date();
				data.Category = Arrays.asList("近15天", "近7天",
						longTimeFormat.format(new Date(now.getTime() - 2 * 24 * 60 * 60 * 1000)),
						longTimeFormat.format(new Date(now.getTime() - 1 * 24 * 60 * 60 * 1000)),
						longTimeFormat.format(new Date()));
				break;

			case "getFilterStatistic":

				// data.Text = "当日工作量";
				// data.Legend = Arrays.asList("确认有效", "已废弃", "车速不足", "无号牌",
				// "图片模糊", "遮挡号牌");
				//
				// data.Series.add(new Series(null, null, Arrays.asList(new
				// Data(2, "确认有效"), new Data(12, "已废弃")), null));
				//
				// data.Series.add(new Series(null, null, Arrays.asList(new
				// Data(2, "有效"), new Data(5, "已废弃"),
				// new Data(1, "车速不足"), new Data(1, "无号牌"), new Data(2, "图片模糊"),
				// new Data(1, "遮挡号牌")), null));

				paramObj.opCondition.statusFlag = "0";
				if (!StringUtil.isNullOrEmpty(paramObj.opCondition.violationType)) {
					String[] arr = paramObj.opCondition.violationType.split(",");
					criteria.lstViolationType = Arrays.asList(arr);
				}
				List<FilterStatistic> lstFilterResult = violationService.findFilterStatistic(criteria);
				List<Data> dataLst = new ArrayList<>();
				List<String> legendLst = new ArrayList<>();
				Long discardTotal = 0l;
				Long filterPassTotal = 0l;
				for (FilterStatistic obj0 : lstFilterResult) {
					if (obj0.getStatus_flag().equals("9")) {
						discardTotal += obj0.getTotal();
						dataLst.add(new Data(obj0.getTotal(), obj0.getDiscarded_reason_name()));
						legendLst.add(obj0.getDiscarded_reason_name());
					} else {
						filterPassTotal += obj0.getTotal();
					}
				}
				dataLst.add(new Data(filterPassTotal, "确认有效"));
				legendLst.add("确认有效");
				legendLst.add("已废弃");
				data.Series.add(new Series(null, null,
						Arrays.asList(new Data(filterPassTotal, "确认有效"), new Data(discardTotal, "已废弃")), null));
				data.Series.add(new Series(null, null, dataLst, null));
				data.Text = "当日工作量";
				data.Legend = legendLst;
				break;
			default:
				break;
			}
		}
		return data;
	}

	/*
	 * 导出
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#exportViolationSet(javax.
	 * servlet.http.HttpServletRequest)
	 */
	public JSONObject exportViolationSetBAk(HttpServletRequest request) {
		String zipFilePath = "";
		String errorMsg = "";
		String param = request.getParameter("param");

		try {

			if (!StringUtil.isNullOrEmpty(param)) {
				OperationParam paramObj = com.alibaba.fastjson.JSONObject.parseObject(param, OperationParam.class);

				if (paramObj.opCondition != null) {
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);

					criteria.setPageSize(1001);

					ObjectMapUtils.parseObject(criteria, paramObj.opCondition);

					// domain接口调用
					String sql = violationService.findViolationSql(criteria);

					int count = violationService.countViolations(criteria);
					if (count > 1000) {
						errorMsg = "最大导出数量为1000条，请缩短查询时间后重新导出!";
					} else {
						List<Violation> exportViolation = violationService.findViolations(criteria);
						zipFilePath = ViolationExport.CreateFileFromData(request, exportViolation);
					}
				} else if (paramObj.arrayData != null) {
					List list = Arrays.asList(paramObj.arrayData);
					List<Violation> lst = new ArrayList<Violation>();
					list.forEach(violationDto -> {
						Violation violation = new Violation();
						ObjectMapUtils.parseObject(violation, violationDto);
						// TODO time converse
						lst.add(violation);
					});
					zipFilePath = ViolationExport.CreateFileFromData(request, lst);
				}
			}

		} catch (ParseException e) {
			errorMsg = e.toString();
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			errorMsg = e.toString();
			e.printStackTrace();
		}
		JSONObject resultObj = new JSONObject();
		resultObj.put("error", errorMsg);
		resultObj.put("zipFilePath", zipFilePath);
		return resultObj;// .toJSONString();
	}

	/*
	 * 导出
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#exportViolationSet(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@RequestMapping(value = "/exportViolationSet")
	public JSONObject exportViolationSet(HttpServletRequest request) throws Exception {

		// Integer squeen = 3;
		// (Integer) cy.its.service.util.DBUtil
		// .queryData("select SEQ_T_SYS_EXPORT.nextval from dual",
		// Integer.class).get(0);
		String errorMsg = "";
		String param = request.getParameter("param");
		String currentUserLoginName = request.getParameter("currentUserLoginName");

		try {

			if (!StringUtil.isNullOrEmpty(param)) {
				OperationParam paramObj = com.alibaba.fastjson.JSONObject.parseObject(param, OperationParam.class);
				String sql = null;
				if (paramObj.opCondition != null) {
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);
					ObjectMapUtils.parseObject(criteria, paramObj.opCondition);
					List<Violation> exportViolation = violationService.findViolations(criteria);
					if (exportViolation.size() == 0) {
						errorMsg = "没有需要导出的记录";
					} else {
						sql = violationService.findViolationSql(criteria);
					}
					// if (count > 1000) {
					// errorMsg = "最大导出数量为1000条，请缩短查询时间后重新导出!";
					// } else {

					// zipFilePath = ViolationExport.CreateFileFromData(request,
					// exportViolation);
					// }
				} else if (paramObj.arrayData != null) {
					List<ViolationDto> list = Arrays.asList(paramObj.arrayData);
					// lst = new ArrayList<Violation>();
					String violationIds = "";

					for (Object obj : list) {
						violationIds += ",'" + ((ViolationDto) obj).getViolationId() + "'";
					}
					violationIds = violationIds.substring(1);
					sql = String.format("select * from t_vio_violation where violation_id in (%s)", violationIds);

				}
				if (!StringUtil.isNullOrEmpty(sql)) {
					String fileName = "违法导出" + (new SimpleDateFormat("yyyyMMddHHmmss")).format(new Date());
					// domain接口调用
					OffLineExport export = new OffLineExport(null, "xmlImgExport", fileName, sql, "1", new Date(),
							currentUserLoginName, "违法管理", null);

					offLineExportService.insert(export);
				}
			}

		} catch (ParseException e) {
			log.error(e.getMessage());
			throw new Exception("服务端异常");
		}
		JSONObject resultObj = new JSONObject();
		resultObj.put("error", errorMsg);
		return resultObj;// .toJSONString();
	}

	/*
	 * 查询上传日志
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#exportViolationSet(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@RequestMapping(value = "/queryUploadLog")
	public JSONObject queryUploadLog(HttpServletRequest request) {
		String zipFilePath = "";
		String errorMsg = "";

		try {
			// TODO

		} catch (Exception e) {
			errorMsg = e.toString();
			e.printStackTrace();
		}
		JSONObject resultObj = new JSONObject();
		resultObj.put("error", errorMsg);
		resultObj.put("zipFilePath", zipFilePath);
		return resultObj;// .toJSONString();
	}

	/*
	 * 图片录入
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#
	 * inputConfirmDigitalViolation(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/inputConfirmDigitalViolation")
	public OperationResult inputConfirmDigitalViolation(HttpServletRequest request) throws Exception {
		OperationResult result = new OperationResult();

		try {
			String param = request.getParameter("param");
			String currentUserId = request.getParameter("currentUserId");
			String userName = "";
			if (!StringUtil.isNullOrEmpty(currentUserId)) {
				User user = dutyService.userOfId(currentUserId);
				userName = user != null ? user.getLoginName() : "";
			}

			OperationParam paramObj = com.alibaba.fastjson.JSONObject.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				ViolationDto dto = paramObj.arrayData[0];
				dto.entryBy = userName;

				Date violationTime = null;
				if (!StringUtil.isNullOrEmpty(dto.getViolationTime())) {
					violationTime = longTimeFormat.parse(dto.getViolationTime());
				}
				//
				ViolationInputDigital inputDigital = new ViolationInputDigital(dto.deviceSysNbr, dto.snapNbr,
						dto.getOrgCode(), dto.collectionPolice, dto.getCollectionType(), dto.getDistrictCode(),
						dto.getVioSiteCode(), dto.getAddressDesc(), dto.getRoadCode(), dto.getJunctionCode(),
						dto.mileage, dto.getPlateNbr(), dto.getPlateType(), dto.getPlateColor(), violationTime,
						dto.getViolationType(), dto.getViolationCode(), dto.getViolationDesc(), dto.speed,
						dto.limitLarge, dto.limitSmall, dto.limitLower, dto.overSpeedPercent, dto.entryBy, new Date(),
						"0", "0", dto.image);

				violationService.inputViolationDigital(inputDigital);
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 批量导入
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#
	 * inputConfirmDigitalViolation(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/inputBulkImportViolation")
	public OperationResult inputBulkImportViolation(HttpServletRequest request) throws Exception {
		OperationResult result = new OperationResult();

		try {
			String param = request.getParameter("param");
			String currentUserId = request.getParameter("currentUserId");
			String userName = "";
			if (!StringUtil.isNullOrEmpty(currentUserId)) {
				User user = dutyService.userOfId(currentUserId);
				userName = user != null ? user.getLoginName() : "";
			}

			OperationParam paramObj = com.alibaba.fastjson.JSONObject.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				ViolationDto dto = paramObj.arrayData[0];
				// TODO
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 废弃(单条和批量)
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#abandonViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/abandonViolation")
	public OperationResult abandonViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();
		String param = request.getParameter("param");
		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}

		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {

				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {// 选择废弃
					List<String> idArray = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						idArray.add(dto.getViolationId());
					}
					String[] idStrings = idArray.toArray(new String[idArray.size()]);
					violationService.abandonViolations(idStrings, paramObj.opField.discardedReason, userName);
				} else if (paramObj.opCondition != null) {// 根据条件全部废弃
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);

					violationService.abandonViolations(criteria, paramObj.opField.specialVehType,
							paramObj.opField.discardedReason, userName);
				}
			}
		} catch (Exception e) {

			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 筛选通过
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#filterViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/filterViolation")
	public OperationResult filterViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {
					for (ViolationDto dto : paramObj.arrayData) {
						violationService.filterViolation(dto.getViolationId(), userName);
					}
				} else {
					result.Error("operationIds参数为空");
				}
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 锁定
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#viewViolationOfIdWithLock(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/viewViolationOfIdWithLock")
	public OperationResult viewViolationOfIdWithLock(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();
		String param = request.getParameter("param");
		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {
					List<ViolationDto> dtoLst = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						Violation vio = violationService.viewViolationOfIdWithLock(dto.getViolationId(), userName);
						ViolationDto dto1 = new ViolationDto();
						ObjectMapUtils.parseObject(dto1, vio);
						dtoLst.add(dto1);
					}
					result.Success(dtoLst);
				} else {
					result.Error("operationIds参数为空");
				}
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 解锁
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#unLockViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/unLockViolation")
	public OperationResult unLockViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				// TODO
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 重录
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#reInputViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/reInputViolation")
	public OperationResult reInputViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");

		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {// 选择废弃
					List<String> idArray = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						idArray.add(dto.getViolationId());
					}
					violationService.reInputViolation(idArray.toArray(new String[idArray.size()]), userName);
				} else if (paramObj.opCondition != null) {// 根据条件全部废弃
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);

					violationService.reInputViolation(criteria, userName);
				}
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 检查是不是白名单
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#reInputViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/isWhiteVeh")
	public OperationResult isWhiteVeh(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");

		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			// TODO
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 将页面条件转化为domain层可使用的条件
	 * 
	 * @param paramObj
	 * @return
	 * @throws Exception
	 */
	private ViolationCriteria ConvertContionToCriteria(OperationCondition opCondition) throws Exception {

		ResetCondition(opCondition);
		ViolationCriteria criteria = new ViolationCriteria();
		ObjectMapUtils.parseObject(criteria, opCondition);
		criteria.setPageNum(opCondition.pageNumber);
		criteria.setPageSize(opCondition.pageSize);
		// 状态列表转换
		if (!StringUtil.isNullOrEmpty(opCondition.statusFlag) && opCondition.statusFlag.contains(",")) {
			String[] arr = opCondition.statusFlag.split(",");

			criteria.lstStatusFlag = Arrays.asList(arr);
			criteria.statusFlag = "";
		}
		if (!StringUtil.isNullOrEmpty(opCondition.violationType)) {
			String[] arr = opCondition.violationType.split(",");

			criteria.lstViolationType = Arrays.asList(arr);
		}
		if (!StringUtil.isNullOrEmpty(opCondition.collectionType)) {
			String[] arr = opCondition.collectionType.split(",");
			
			criteria.lstCollectionType = Arrays.asList(arr);
		}
		if (!StringUtil.isNullOrEmpty(opCondition.siteCodes)) {
			String[] arr = opCondition.siteCodes.split(",");
			criteria.LstSiteCode = Arrays.asList(arr);
		}

		if (!StringUtil.isNullOrEmpty(opCondition.getOrgId())) {
			criteria.org_authority_code = dutyService.organizationOfId(opCondition.getOrgId()).orgPrivilegeCode;
		} else {
			criteria.org_authority_code = opCondition.getCurrentOrgPrivilegeCode();
		}
		// 时间转换
		if (!StringUtil.isNullOrEmpty(opCondition.violationBeginTime)) {
			criteria.violationBeginTime = opCondition.violationBeginTime;// +
																			// ":00";
		}
		if (!StringUtil.isNullOrEmpty(opCondition.violationEndTime)) {
			criteria.violationEndTime = opCondition.violationEndTime;// + ":00";
		}

		if (!StringUtil.isNullOrEmpty(opCondition.uploadBeginTime)) {
			criteria.uploadBeginTime = opCondition.uploadBeginTime;// + ":00";
		}
		if (!StringUtil.isNullOrEmpty(opCondition.uploadEndTime)) {
			criteria.uploadEndTime = opCondition.uploadEndTime;// + ":00";
		}

		// 速度转化
		if (!StringUtil.isNullOrEmpty(opCondition.speedMax)) {
			criteria.speedMax = Integer.parseInt(opCondition.speedMax);//
		}
		if (!StringUtil.isNullOrEmpty(opCondition.speedMin)) {
			criteria.speedMin = Integer.parseInt(opCondition.speedMin);//
		}
		// 号牌号码
		if (!StringUtil.isNullOrEmpty(opCondition.plateNbr)) {
			if (opCondition.plateNbr.length() < 7) {
				criteria.fuzzyPlateNbr = opCondition.plateNbr;
				criteria.plateNbr = null;
			}
		}
		return criteria;
	}

	/*
	 * 录入
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#inputViolationForConfirm(
	 * javax.servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/inputViolationForConfirm")
	public OperationResult inputViolationForConfirm(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");

		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {
				for (ViolationDto obj : paramObj.arrayData) {
					ViolationInputConfirm confirm = new ViolationInputConfirm(obj.getViolationId(), obj.getPlateNbr(),
							obj.getPlateType(), obj.getPlateColor(), obj.getViolationCode(), obj.getViolationDesc(),
							userName, (new Date()));
					violationService.inputViolationForConfirm(confirm);
				}
			} else {
				result.Error("operationIds参数为空");
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 上传
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#uploadViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/uploadViolation")
	public OperationResult uploadViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		String currentUserId = request.getParameter("currentUserId");
		String userName = "";
		if (!StringUtil.isNullOrEmpty(currentUserId)) {
			User user = dutyService.userOfId(currentUserId);
			userName = user != null ? user.getLoginName() : "";
		}
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {
					List<String> idArray = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						idArray.add(dto.getViolationId());
					}
					violationService.uploadViolation(idArray.toArray(new String[idArray.size()]), userName);
				} else if (paramObj.opCondition != null) {
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);
					violationService.uploadViolation(criteria, userName);
				} else {

					result.Error("opCondition参数为空");

				}
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 删除
	 * 
	 * @see cy.its.violation.rest.action.IViolationAction#deleteViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/deleteViolation")
	public OperationResult deleteViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {// 选择废弃
					List<String> idArray = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						idArray.add(dto.getViolationId());
					}
					violationService.deleteViolations(idArray.toArray(new String[idArray.size()]));
				} else if (paramObj.opCondition != null) {// 根据条件全部还原
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);

					violationService.deleteViolations(criteria);
				} else {
					result.Error("参数为空");
				}

			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 还原
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#restoreViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/restoreViolation")
	public OperationResult restoreViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
				if (paramObj.arrayData != null && paramObj.arrayData.length > 0) {// 选择废弃
					List<String> idArray = new ArrayList<>();
					for (ViolationDto dto : paramObj.arrayData) {
						idArray.add(dto.getViolationId());
					}
					violationService.restoreAbandons(idArray.toArray(new String[idArray.size()]));
				} else if (paramObj.opCondition != null) {// 根据条件全部还原
					ViolationCriteria criteria = ConvertContionToCriteria(paramObj.opCondition);

					violationService.restoreAbandons(criteria);
				}
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

	/*
	 * 修改并重新上传
	 * 
	 * @see
	 * cy.its.violation.rest.action.IViolationAction#restoreViolation(javax.
	 * servlet.http.HttpServletRequest)
	 */
	@Override
	@RequestMapping(value = "/verifyUploadFailViolation")
	public OperationResult verifyUploadFailViolation(HttpServletRequest request) throws Exception {

		OperationResult result = new OperationResult();

		String param = request.getParameter("param");
		try {
			OperationParam paramObj = com.alibaba.fastjson.JSONArray.parseObject(param, OperationParam.class);
			if (paramObj != null) {
			}
		} catch (Exception e) {
			result.Error(e.toString());
			e.printStackTrace();
		}
		return result;
	}

}
