package cy.its.service.standardization.dataMaker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.Model;
import cy.its.service.common.ioc.Import;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;
import cy.its.service.standardization.IMaker;
import cy.its.service.standardization.dataMaker.originalModel.BaseOrginalModel;
import cy.its.service.standardization.dictionary.CacheManager;
import cy.its.service.standardization.validator.BaseValidator;

public abstract class DataArrayMaker<I extends BaseOrginalModel, O extends Model> implements IMaker {
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

	public DataArrayMaker(String makerType, Class<I> clazzI, Class<O> clazzO, String subRouteKey, String pubRouteKey,
			String queueName, boolean durable) {
		try {
			this.makerType = makerType;
			this.clazzI = clazzI;
			this.clazzO = clazzO;
			this.pubRouteKey = pubRouteKey;
			this.subRouteKey = subRouteKey;
			this.queueName = queueName;
			this.validator = validator();
			this.durable = durable;
		} catch (Throwable e) {
			LogManager.error(e);
		}
	}

	public QueueHandler getQueueHandler() {
		return new QueueHandler(this.queueName, this.durable, !this.durable, this.subRouteKey, this);
	}

	static final String FMT_UNREG = "%s,请排查,原始数据:%s";
	static final String FMT_UNVALIDATE = "验证不通过:%s;转换后数据:%s;";
	static final String FMT_ERROR = "%s数据规范化出错,%s;原始数据:%s;";
	static final String FMT_UN_ALL = "%s 批量数据规范化结果全部未通过验证, 验证结果如下: %s";
	static final String FMT_UN_PART = "%s 批量数据规范化结果部分未通过验证, 验证结果如下: %s";
	static final String FMT_RECV = "Receive %s : %s";
	static final String lineSeparator = System.lineSeparator();
	static final String FMT_PUB = "发布 %s: %s";

	@Override
	public Boolean receive(String routeKey, String message) {
		try {
			if (cacheManager.isLoaded()) {
				LogManager.debug(String.format(FMT_RECV, routeKey, message));
				List<I> lstIn = JsonUtil.parseList(message, this.clazzI);
				List<O> lstOut = lstIn.stream().map(i -> translate(i)).collect(Collectors.toList());
				check(lstIn, lstOut);
				lstIn = null;
				String pubJson;
				for (O ot : lstOut) {
					pubJson = JsonUtil.serialize(ot);
					MQGateWay.publish(this.pubRouteKey, pubJson);
					LogManager.debug(String.format(FMT_PUB, this.makerType, pubJson) );
				}
				
				return ConstValue.BOOL_TRUE;
			}
		} catch (Exception e) {
			LogManager.error(String.format(FMT_ERROR, this.makerType, StringUtil.getErrorDetail(e), message));
		}

		return ConstValue.BOOL_TRUE;
	}

	private void check(List<I> lstIn, List<O> lstOut) {
		List<String> lstWarn = new ArrayList<String>(lstIn.size());

		for (int j = lstOut.size() - ConstValue.INT_1; j >= ConstValue.INT_0; j--) {
			if (lstOut.get(j) == null) {
				lstWarn.add(String.format(FMT_UNREG, lstIn.get(j).validateResult, JsonUtil.serialize(lstIn.get(j))));
				lstOut.remove(j);
			}
		}

		lstIn = null;

		if (validator != null && lstOut.size() > ConstValue.INT_0) {
			O o;
			for (int j = lstOut.size() - ConstValue.INT_1; j >= ConstValue.INT_0; j--) {
				o = lstOut.get(j);
				if (!validator.check(o)) {
					lstWarn.add(String.format(FMT_UNVALIDATE, o.validateResult, JsonUtil.serialize(o)));
					lstOut.remove(j);
				}
			}
		}

		if (lstWarn.size() > ConstValue.INT_0) {
			LogManager.warn(String.format(lstOut.size() > ConstValue.INT_0 ? FMT_UN_PART : FMT_UN_ALL, this.makerType,
					String.join(System.lineSeparator(), lstWarn)));
		}
	}

	abstract O translate(I input);

	abstract BaseValidator<O> validator();

}
