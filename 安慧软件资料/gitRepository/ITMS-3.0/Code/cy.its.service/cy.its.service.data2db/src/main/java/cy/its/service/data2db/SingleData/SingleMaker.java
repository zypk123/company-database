package cy.its.service.data2db.SingleData;

import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.common.rabbitmqClient.QueueHandler;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.dataAccess.DiscardedDbError;
import cy.its.service.common.dataModel.Mapper;
import cy.its.service.common.log.LogManager;
import cy.its.service.data2db.IMaker;
import cy.its.service.data2db.BulkData.Utils.InsertParam;

public abstract class SingleMaker<I> implements IMaker {

	String queueName;
	String recvRouteKey;
	String pubRouteKey;
	String dataName;

	InsertParam<I> insertParam;

	abstract void extendCols(List<String[]> lst);

	public SingleMaker(Class<I> clazz, String tableName, String queueName, String recvRouteKey, String pubRouteKey,
			String dataName) {
		createInsertParam(clazz, tableName);
		this.queueName = queueName;
		this.recvRouteKey = recvRouteKey;
		this.dataName = dataName;
		this.pubRouteKey = pubRouteKey;
	}

	@Override
	public Boolean receive(String routingKey, String message) {
		try {
			List<Object> params = fetchList(message);
			DbExecuter.singleInsert(this.insertParam.instSQL, params);
		} catch (Throwable e) {
			if (DiscardedDbError.isDiscardedError(e)) {
				LogManager.error(this.insertParam.tableName + "表单条入库失败, 数据将废弃, 失败原因:" + StringUtil.getErrorDetail(e));
				return true;
			} else {
				LogManager.error(
						this.insertParam.tableName + "表单条入库失败, 数据将保留,等待下次入库,  失败原因:" + StringUtil.getErrorDetail(e));
				return false;
			}
		}

		try {
			if (!StringUtil.isNullOrEmpty(pubRouteKey)) {
				MQGateWay.publish(pubRouteKey, message);
			}
		} catch (Throwable e) {
			LogManager.error("发布失败");
		}

		return true;
	}

	@Override
	public QueueHandler getQueueHandler() {
		return new QueueHandler(this.queueName, true, false, this.recvRouteKey, this);
	}

	List<Object> fetchList(String json) {
		try {
			List<Object> lstCObj = new ArrayList<Object>(this.insertParam.fieldSize);
			I tInfo = JsonUtil.parseObject(json, insertParam.clazz);
			for (Field f : insertParam.fields) {
				try {
					lstCObj.add(f.get(tInfo));
				} catch (Exception e) {
					e.printStackTrace();
					lstCObj.add(null);
				}
			}

			return lstCObj;
		} catch (Exception e) {
			LogManager.error(this.dataName + "反序列化出错:" + StringUtil.getErrorDetail(e) + " 数据:" + json);
			throw e;
		}
	}

	@Override
	public void close() {
		return;
	}

	private void createInsertParam(Class<I> clazz, String tableName) {
		insertParam = new InsertParam<I>();
		insertParam.clazz = clazz;
		Field[] fs = clazz.getDeclaredFields();
		insertParam.tableName = tableName;
		insertParam.fields = new ArrayList<Field>(fs.length);

		List<String[]> cols = new ArrayList<String[]>();
		extendCols(cols);
		List<String> lstCol = new ArrayList<String>(fs.length);
		List<String> lstVal = new ArrayList<String>(fs.length);

		if (cols != null) {
			for (String[] c : cols) {
				lstCol.add(c[0]);
				lstVal.add(c[1]);
			}
		}

		for (Field f : fs) {
			if (f.isAnnotationPresent(Mapper.class)) {
				f.setAccessible(true);
				Mapper m = f.getAnnotation(Mapper.class);
				lstCol.add(m.value());
				lstVal.add("?");
				insertParam.fields.add(f);
			}
		}

		insertParam.instSQL = String.format("insert into %s (%s) values (%s) ", tableName, String.join(",", lstCol),
				String.join(",", lstVal));

		insertParam.fieldSize = insertParam.fields.size();
	}
}