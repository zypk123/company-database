package com.huntkey.rx.sceo.formula.provider.related.condition.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.common.model.vo.PropertyRelatedVo;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import com.huntkey.rx.sceo.formula.provider.related.condition.service.PropRelatedService;
import com.huntkey.rx.sceo.formula.provider.related.condition.service.RelatedConditionSettingService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExecType;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodStatus;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodType;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 关联条件设置Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/relatedConditions")
@CrossOrigin
public class RelatedConditionSettingController {

	private static Logger log = LoggerFactory.getLogger(RelatedConditionSettingController.class);

	@Autowired
	private RelatedConditionSettingService relatedConditionSettingService;

	@Autowired
	private RecordMappingService recordMappingService;

	@Autowired
	private PropRelatedService propRelatedService;

	@Autowired
	private BizModelerService bizModelerService;

	/**
	 * 新增关联条件的条件
	 *
	 * @param tacConditionRelated
	 * @return
	 */
	@RequestMapping(value = "/saveConditionRelated", method = RequestMethod.POST)
	public Result saveConditionRelated(@RequestBody TacConditionRelated tacConditionRelated) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.saveConditionRelated(tacConditionRelated);
			result.setData(tacConditionRelated.getCndrId());
		} catch (Exception e) {
			log.error("新增关联条件的条件发生错误", e);
			throw new RuntimeException("新增关联条件的条件发生错误", e);
		}
		return result;
	}

	/**
	 * 修改关联条件的条件
	 *
	 * @param tacConditionRelated
	 * @return
	 */
	@RequestMapping(value = "/updateConditionRelated", method = RequestMethod.PUT)
	public Result updateConditionRelated(@RequestBody TacConditionRelated tacConditionRelated) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.updateConditionRelated(tacConditionRelated);
			result.setData(tacConditionRelated.getCndrId());
		} catch (Exception e) {
			log.error("修改关联条件的条件发生错误", e);
			throw new RuntimeException("修改关联条件的条件条件发生错误", e);
		}
		return result;
	}

	/**
	 * 查询关联条件的条件列表
	 *
	 * @return
	 */
	@RequestMapping(value = "/queryAllConditionsRelated", method = RequestMethod.GET)
	public Result queryAllConditionsRelated() {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			List<TacConditionRelated> tacConditionRelateds = relatedConditionSettingService.queryAllConditionsRelated();
			result.setData(tacConditionRelateds);
		} catch (Exception e) {
			log.error("查询关联条件的条件列表发生错误", e);
			throw new RuntimeException("查询关联条件的条件列表发生错误", e);
		}
		return result;
	}

	/**
	 * 通过id查询关联条件的条件
	 *
	 * @param cndrId
	 * @return
	 */
	@RequestMapping(value = "/queryConditionsRelatedByCndrId/{cndrId}", method = RequestMethod.GET)
	public Result queryConditionsRelatedByCndrId(@PathVariable("cndrId") String cndrId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			TacConditionRelated tacConditionRelated = relatedConditionSettingService
					.queryConditionsRelatedByCndrId(cndrId);
			result.setData(tacConditionRelated);
		} catch (Exception e) {
			log.error("通过id查询关联条件的条件发生错误", e);
			throw new RuntimeException("通过id查询关联条件的条件发生错误", e);
		}
		return result;
	}

	/**
	 * 删除关联条件的条件接口(逻辑删除)
	 *
	 * @param cndrId
	 * @return
	 */
	@RequestMapping(value = "/removeConditionRelated/{cndrId}", method = RequestMethod.DELETE)
	public Result removeConditionRelated(@PathVariable("cndrId") String cndrId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.removeConditionRelated(cndrId);
		} catch (Exception e) {
			log.error("删除关联条件的条件接口发生错误", e);
			throw new RuntimeException("删除关联条件的条件接口发生错误", e);
		}
		return result;
	}

	/**
	 * 新增关联条件
	 *
	 * @param tacPropertyRelated
	 * @return
	 */
	@RequestMapping(value = "/savePropRelated", method = RequestMethod.POST)
	public Result savePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.savePropRelated(tacPropertyRelated);
			result.setData(tacPropertyRelated.getPrplId());
		} catch (Exception e) {
			log.error("新增关联条件发生错误", e);
			throw new RuntimeException("新增关联条件发生错误", e);
		}
		return result;
	}

	/**
	 * 修改关联条件
	 *
	 * @param tacPropertyRelated
	 * @return
	 */
	@RequestMapping(value = "/updatePropRelated", method = RequestMethod.PUT)
	public Result updatePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.updatePropRelated(tacPropertyRelated);
			result.setData(tacPropertyRelated.getPrplId());
		} catch (Exception e) {
			log.error("修改关联条件发生错误", e);
			throw new RuntimeException("修改关联条件发生错误", e);
		}
		return result;
	}

	/**
	 * 通过属性关联ID查找关联属性及其条件列表
	 *
	 * @param prplId
	 * @return
	 */
	@RequestMapping(value = "/getPropertyRelatedCondition/{prplId}", method = RequestMethod.GET)
	public Result getPropertyRelatedCondition(@PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			PropertyRelatedVo propertyRelatedVo = relatedConditionSettingService.getPropertyRelatedCondition(prplId);
			result.setData(propertyRelatedVo);
		} catch (Exception e) {
			log.error("通过属性关联ID查找关联属性及其条件列表发生错误", e);
			throw new RuntimeException("通过属性关联ID查找关联属性及其条件列表发生错误", e);
		}
		return result;
	}

	/**
	 * 保存关联属性及其条件(关联条件)
	 *
	 * @param propertyRelatedVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForRelCon", method = RequestMethod.POST)
	public Result saveOrUpdatePropRelatedAndConditionsForRelCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			String prplId = propertyRelatedVo.getRelatedProperty().getPrplId();
			// 新增时保存映射表
			if (StringUtil.isNullOrEmpty(prplId)) {
				relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
				this.savePropIDAndPrplID(propertyRelatedVo.getPropId(),
						propertyRelatedVo.getRelatedProperty().getPrplId(), "3");
			} else {
				relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
			}
			result.setData(propertyRelatedVo.getRelatedProperty().getPrplId());
		} catch (Exception e) {
			log.error("保存关联属性及其条件发生错误", e);
			throw new RuntimeException("保存关联属性及其条件发生错误", e);
		}
		return result;
	}

	/**
	 * 保存关联属性及其条件(关联触发条件)
	 *
	 * @param propertyRelatedVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForTriCon", method = RequestMethod.POST)
	public Result saveOrUpdatePropRelatedAndConditionsForTriCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
			result.setData(propertyRelatedVo.getRelatedProperty().getPrplId());
		} catch (Exception e) {
			log.error("保存关联属性及其条件发生错误", e);
			throw new RuntimeException("保存关联属性及其条件发生错误", e);
		}
		return result;
	}

	/**
	 * 保存关联属性及其条件(关联触发条件)
	 *
	 * @param propertyRelatedVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForClassCon", method = RequestMethod.POST)
	public Result saveOrUpdatePropRelatedAndConditionsForClassCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
			result.setData(propertyRelatedVo.getRelatedProperty().getPrplId());
		} catch (Exception e) {
			log.error("保存类及其条件发生错误", e);
			throw new RuntimeException("保存类及其条件发生错误", e);
		}
		return result;
	}


	/**
	 * 保存关联属性及其条件(方法关联触发条件)
	 *
	 * @param propertyRelatedVo
	 * @return
	 */
	@RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForMethodTriCon", method = RequestMethod.POST)
	public Result saveOrUpdatePropRelatedAndConditionsForMethodTriCon(
			@RequestBody PropertyRelatedVo propertyRelatedVo) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			String prplId = propertyRelatedVo.getRelatedProperty().getPrplId();
			// 新增时保存映射表
			if (StringUtil.isNullOrEmpty(prplId)) {
				relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
				this.savePropIDAndPrplID(propertyRelatedVo.getPropId(),
                // 5 :方法关联触发条件
                propertyRelatedVo.getRelatedProperty().getPrplId(), "5");
			} else {
				relatedConditionSettingService.saveOrUpdatePropRelatedAndConditions(propertyRelatedVo);
			}
			result.setData(propertyRelatedVo.getRelatedProperty().getPrplId());
		} catch (Exception e) {
			log.error("保存关联属性及其条件发生错误", e);
			throw new RuntimeException("保存关联属性及其条件发生错误", e);
		}
		return result;
	}

	/**
	 * 根据关联属性ID查找公式
	 *
	 * @param prplId
	 * @return
	 */
	@RequestMapping(value = "/getPrplConditionDescByPrplId/{prplId}", method = RequestMethod.GET)
	public Result getPrplConditionDescByPrplId(@PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			String prplConditionDesc = relatedConditionSettingService.getPrplConditionDescByPrplId(prplId);
			result.setData(prplConditionDesc);
		} catch (Exception e) {
			log.error("根据关联属性ID查找公式发生错误", e);
			throw new RuntimeException("根据关联属性ID查找公式发生错误", e);
		}
		return result;
	}

	/**
	 * 根据关联属性ID数组查找公式
	 *
	 * @param prplIdArr1
	 * @param prplIdArr2
	 * @return
	 */
	@RequestMapping(value = "/getPrplConditionDescByPrplIdArr", method = RequestMethod.GET)
	public Result getPrplConditionDescByPrplIdArr(
			@RequestParam(required = false, value = "prplIdArr1") List<String> prplIdArr1,
			@RequestParam(required = false, value = "prplIdArr2") List<String> prplIdArr2) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			Map<String, Object> conditionDescByMap = relatedConditionSettingService
					.getPrplConditionDescByPrplIdArr(prplIdArr1, prplIdArr2);
			result.setData(conditionDescByMap);
		} catch (Exception e) {
			log.error("根据关联属性ID数组查找公式发生错误", e);
			throw new RuntimeException("根据关联属性ID数组查找公式发生错误", e);
		}
		return result;
	}

	/**
	 * 新增时保存映射表
	 *
	 * @param propId 属性ID
	 * @param prplId 关联条件或者触发条件ID
	 * @param type 类型
	 * @return
	 */
	private int savePropIDAndPrplID(String propId, String prplId, String type) {
		SourceSystemRecordMapping sourceSystemRecordMapping = new SourceSystemRecordMapping();
		sourceSystemRecordMapping.setSourceName("modeler");
		sourceSystemRecordMapping.setInsideId(prplId);
		sourceSystemRecordMapping.setInsideType(type);
		sourceSystemRecordMapping.setSourceMappingId(propId);
		sourceSystemRecordMapping.setSourceMappingType(type);
		return recordMappingService.insert(sourceSystemRecordMapping);
	}

	/**
	 * 初始化关联触发条件
	 *
	 * @param propId 属性id
	 * @param prplId 触发条件id
	 * @return
	 */
	@RequestMapping(value = "/initRelTriggerCond/{propId}/{prplId}", method = RequestMethod.GET)
	public Result initRelTriggerCond(@PathVariable("propId") String propId, @PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			PropertyRelatedVo initPropertyRelatedVo = relatedConditionSettingService
					.getPropertyRelatedCondition(prplId);
			result.setData(initPropertyRelatedVo);
		} catch (Exception e) {
			log.error("初始化关联触发条件发生错误", e);
			throw new RuntimeException("初始化关联触发条件发生错误", e);
		}
		return result;
	}



	/**
	 * 初始化方法关联触发条件
	 *
	 * @param propId 属性ID
	 * @return
	 */
	@RequestMapping(value = "/initMethodRelTriggerCond/{propId}", method = RequestMethod.GET)
	public Result initMethodRelTriggerCond(@PathVariable("propId") String propId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			SourceSystemRecordMapping sourceSystemRecordMapping = recordMappingService.queryRecord(propId, "5", null);
			if (null != sourceSystemRecordMapping) {
				PropertyRelatedVo initPropertyRelatedVo = relatedConditionSettingService
						.getPropertyRelatedCondition(sourceSystemRecordMapping.getInsideId());
				result.setData(initPropertyRelatedVo);
			}
		} catch (Exception e) {
			log.error("初始化方法关联触发条件发生错误", e);
			throw new RuntimeException("初始化方法关联触发条件发生错误", e);
		}
		return result;
	}

	/**
	 * 初始化关联条件
	 *
	 * @param propId 属性ID
	 * @param propId2 属性2ID
	 * @return
	 */
	@RequestMapping(value = "/initRelCond/{propId}/{propId2}", method = RequestMethod.GET)
	public Result initRelCond(@PathVariable("propId") String propId, @PathVariable("propId2") String propId2) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			SourceSystemRecordMapping sourceSystemRecordMapping = recordMappingService
					.queryRecord(propId + "." + propId2, "3", null);
			if (null != sourceSystemRecordMapping) {
				PropertyRelatedVo initPropertyRelatedVo = relatedConditionSettingService
						.getPropertyRelatedCondition(sourceSystemRecordMapping.getInsideId());
				result.setData(initPropertyRelatedVo);
			}
		} catch (Exception e) {
			log.error("初始化关联条件发生错误", e);
			throw new RuntimeException("初始化关联条件发生错误", e);
		}
		return result;
	}

	/**
	 * @return com.huntkey.rx.commons.utils.rest.Result
	 * @desc modeler 根据属性id及对应的orm数据id查询关联类的数据
	 * @pars [prplId, dataId] 属性ID orm数据ID
	 * @date 2017/8/18 0018 上午 9:58 lulx
	 **/
	@RequestMapping(value = "/relCondDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
	public Result relCondDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId) {
		Result result = null;
		try {
			result = relatedConditionSettingService.relCondDataByPro(prplId, dataId);
		} catch (Exception e) {
			log.error("modeler 根据属性id及对应的orm数据id查询关联类的数据发生错误", e);
			throw new RuntimeException("modeler 根据属性id及对应的orm数据id查询关联类的数据发生错误", e);
		}
		return result;
	}

	/**
	 * 通过ID查找关联属性(触发条件)
	 *
	 * @param prplId
	 * @return
	 */
	@RequestMapping(value = "/selectByPrimaryKey/{prplId}", method = RequestMethod.GET)
	public Result selectByPrimaryKey(@PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			TacPropertyRelated tacPropertyRelated = propRelatedService.selectByPrimaryKey(prplId);
			result.setData(tacPropertyRelated);
		} catch (Exception e) {
			log.error("通过ID查找关联属性(触发条件)发生错误", e);
			throw new RuntimeException("通过ID查找关联属性(触发条件)发生错误", e);
		}
		return result;
	}

	/**
	 * @return com.huntkey.rx.commons.utils.rest.Result
	 * @desc modeler 关联触发条件根据orm数据获取结果
	 * @pars [prplId, dataId] 关联公式ID orm数据ID
	 * @date 2017/8/21 0021 下午 5:08 lulx
	 **/
	@RequestMapping(value = "/relCondConfDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
	public Result relCondConfDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId) {
		Result result = null;
		try {
			result = relatedConditionSettingService.relCondConfDataByPro(prplId, dataId);
		} catch (Exception e) {
			log.error("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
			throw new RuntimeException("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
		}
		return result;
	}

	/**
	 * @return com.huntkey.rx.commons.utils.rest.Result
	 * @desc modeler 关联触发条件根据orm数据获取object
	 * @pars [prplId, dataId] 关联公式ID orm数据ID
	 * @date 2017/10/27 0021 下午 5:08 cjq
	 **/
	@RequestMapping(value = "/getObjectRelCondConfDataByPro/{prplId}", method = RequestMethod.GET)
	@MethodRegister(
			edmClass = "formula",
			methodCate = "公式",
			programCate = ProgramCate.Java,
			methodType = MethodType.GeneralMethod,
			methodExecType = MethodExecType.SyncMethod,
			methodStatus = MethodStatus.Enable,
			methodDesc = "监管树筛选条件解析"
	)
	public Result getObjectRelCondConfDataByPro(@PathVariable("prplId") String prplId) {
		Result result = null;
		try {
			result = relatedConditionSettingService.getObjectRelCondConfDataByPro(prplId);
		} catch (Exception e) {
			log.error("modeler 关联触发条件根据orm数据获取obejct 接口错误", e);
			throw new RuntimeException("modeler 关联触发条件根据orm数据获取object 接口错误", e);
		}
		return result;
	}

	/**
	 * @return true false
	 * @desc modeler 关联触发条件根据orm数据获取结果
	 * @pars [prplId] 关联公式ID
	 * @date 2017/10/26 0021 下午 5:08
	 * @author liujia
	 **/
	@RequestMapping(value = "/relCondConfDataByPrplIdForClass/{prplId}/{dataId}", method = RequestMethod.GET)
	@MethodRegister(
			edmClass = "formula",
			methodCate = "公式",
			programCate = ProgramCate.Java,
			methodType = MethodType.GeneralMethod,
			methodExecType = MethodExecType.SyncMethod,
			methodStatus = MethodStatus.Enable,
			methodDesc = "流程条件公式解析"
	)
	public Result relCondConfDataByPrplIdForClass(@PathVariable("prplId") String prplId,@PathVariable("dataId") String dataId) {

		Result result = null;
		try {
			result = relatedConditionSettingService.relCondConfDataByPrplIdForClass(prplId,dataId);
		} catch (Exception e) {
			log.error("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
			throw new RuntimeException("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
		}
		return result;
	}

	/**
	 * 删除关联条件(条件也删除)
	 *
	 * @param prplId
	 * @return
	 */
	@RequestMapping(value = "/delRelCond/{prplId}", method = RequestMethod.DELETE)
	public Result delRelCond(@PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			propRelatedService.delRelCondOrRelTrigger(prplId);
			recordMappingService.delRecordMapping(prplId);
			Result modelerResult = bizModelerService.deleteRelCond(prplId);
			if (modelerResult.getRetCode() == 0) {
				result.setRetCode(Result.RECODE_ERROR);
				result.setErrMsg("Modeler接口出现错误...");
			}
		} catch (Exception e) {
			log.error("删除关联条件接口错误", e);
			throw new RuntimeException("删除关联条件接口错误", e);
		}
		return result;
	}

	/**
	 * 删除触发条件(条件也删除)
	 *
	 * @param prplId
	 * @return
	 */
	@RequestMapping(value = "/delRelTrigger/{prplId}", method = RequestMethod.DELETE)
	public Result delRelTrigger(@PathVariable("prplId") String prplId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			propRelatedService.delRelCondOrRelTrigger(prplId);
		} catch (Exception e) {
			log.error("删除触发条件接口错误", e);
			throw new RuntimeException("删除触发条件接口错误", e);
		}
		return result;
	}
}
