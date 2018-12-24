package cy.its.service.standardization.dataMaker;

import cy.its.service.common.dataModel.Model;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

import java.util.Map;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.standardization.IMaker;
import cy.its.service.standardization.dataMaker.originalModel.BaseOrginalModel;
import cy.its.service.standardization.dictionary.CacheManager;
import cy.its.service.standardization.validator.BaseValidator;

abstract class DataMaker<I extends BaseOrginalModel, O extends Model> implements IMaker {
	@Import
	CacheManager cacheManager;

	private Class<I> clazzI;
	@SuppressWarnings("unused")
	private Class<O> clazzO;
	private String pubRouteKey;
	private String subRouteKey;
	private String queueName;
	private BaseValidator<O> validator;
	private String makerType;
	private boolean durable = true;

	public DataMaker(String makerType, Class<I> clazzI, Class<O> clazzO, String subRouteKey, String pubRouteKey,
			String queueName) {
		try {
			this.makerType = makerType;
			this.clazzI = clazzI;
			this.clazzO = clazzO;
			this.pubRouteKey = pubRouteKey;
			this.subRouteKey = subRouteKey;
			this.queueName = queueName;
			this.validator = validator();
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}
	
	public DataMaker(String makerType, Class<I> clazzI, Class<O> clazzO, String subRouteKey, String pubRouteKey,
			String queueName, boolean durable) {
		this(makerType, clazzI, clazzO, subRouteKey, pubRouteKey, queueName);
		this.durable = durable;
	}

	public QueueHandler getQueueHandler() {
		return new QueueHandler(this.queueName, this.durable, !this.durable, this.subRouteKey, this);
	}

	static final String FMT_UNREG = "%s设备未注册, 数据:%s";
	static final String FMT_UNVALIDATE = "%s验证不通过:%s;数据:%s;";
	static final String FMT_ERROR = "%s数据规范化出错,%s;原始数据:%s;";
	static final String FMT_RECV = "Receive %s : %s";
	static final String FMT_UNVALIDATE_ORIG = "%s数据转换未通过:%s;数据:%s;";
	static final String STR_DEVICE_UNREG = "设备信息未注册";
	static final String STR_EXT_NOKEY = "前端数据扩展属性无deviceID";
	static final String STR_EXT_NULL = "前端违法扩展属性为空,无法继续处理";

	@Override
	public Boolean receive(String routeKey, String message) {
		try {
			if (cacheManager.isLoaded()) {
				LogManager.debug(String.format(FMT_RECV, routeKey, message));
				I input = JsonUtil.parseObject(message, this.clazzI);
				O output = translate(input);
				
				if(!StringUtil.isNullOrEmpty(input.validateResult)){
					LogManager.warn(String.format(FMT_UNVALIDATE_ORIG, this.makerType, input.validateResult,
							message));
					return ConstValue.BOOL_TRUE;
				} else if (validator != null && !validator.check(output)) {
					LogManager.warn(String.format(FMT_UNVALIDATE, this.makerType, output.validateResult,
							JsonUtil.serialize(output)));
					return ConstValue.BOOL_TRUE;
				}
				input = null;
				return MQGateWay.publish(this.pubRouteKey, JsonUtil.serialize(output));
			}
		} catch (Throwable e) {
			LogManager.error(String.format(FMT_ERROR, this.makerType, StringUtil.getErrorDetail(e), message));
		}

		return ConstValue.BOOL_TRUE;
	}
	
	public String getMapValue(String key, Map<String, String> map){
		if(map != null){
			return map.get(key);
		}
		
		return null;
	}

	abstract O translate(I input);

	abstract BaseValidator<O> validator();
}
