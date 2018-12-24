package cy.its.service.data2db.BulkData.Data;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import cy.its.service.common.dataAccess.DbExecuter;
import cy.its.service.common.dataAccess.DiscardedDbError;
import cy.its.service.common.dataModel.Mapper;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.JsonUtil;
import cy.its.service.common.StringUtil;
import cy.its.service.data2db.BulkData.BulkMaker;
import cy.its.service.data2db.BulkData.Utils.InsertResult;

abstract class BulkDAL<T> extends BulkMaker {

	String instSQL;
	List<Field> fields;
	int fieldSize;
	Class<T> clazz;
	String tableName;

	abstract void extendCols(List<String[]> lst);

	BulkDAL(Class<T> clazz) {
		this.clazz = clazz;
		Field[] fs = clazz.getDeclaredFields();
		this.tableName = this.param.TableName;
		this.fields = new ArrayList<Field>(fs.length);

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
				this.fields.add(f);
			}
		}

		this.fieldSize = this.fields.size();

		instSQL = String.format("insert into %s (%s) values (%s) ", tableName, String.join(",", lstCol),
				String.join(",", lstVal));
	}

	@Override
	public void batchInsert(List<String> infoList, InsertResult insertResult) {
		insertResult.reset();
		List<List<Object>> lstObj = fetchList(infoList);

		long t1 = System.currentTimeMillis();
		try {
			DbExecuter.batchInsert(this.instSQL, lstObj);
			insertResult.add(infoList.size());
		} catch (Throwable e) {
			LogManager.error(this.tableName + "表批量入库失败,改为单条入库, 失败详细:" + StringUtil.getErrorDetail(e));
			for (int i = 0; i < lstObj.size(); i++) {
				singleInsert(lstObj.get(i), infoList.get(i), insertResult);
			}
		}

		long t2 = System.currentTimeMillis();

		LogManager.info(String.format("%s入库统计 - 总记录数:%d条, 成功:%d条, 失败:%d条[其中丢弃:%d条], 供耗时:%d毫秒", tableName,
				infoList.size(), insertResult.succesCount, infoList.size() - insertResult.succesCount,
				insertResult.discardCount, t2 - t1));
	}

	void singleInsert(List<Object> params, String json, InsertResult insertResult) {
		try {
			DbExecuter.singleInsert(this.instSQL, params);
			insertResult.increase();
		} catch (Throwable e) {
			if (DiscardedDbError.isDiscardedError(e)) {
				insertResult.increaseDiscard();
				LogManager.error(this.tableName + "表单条入库失败, 数据将废弃, 失败原因:" + StringUtil.getErrorDetail(e));
			} else {
				insertResult.avaliableList.add(json);
				LogManager.error(this.tableName + "表单条入库失败, 数据将保留,等待下次入库,  失败原因:" + StringUtil.getErrorDetail(e));
			}
		}

	}

	List<List<Object>> fetchList(List<String> infoList) {
		List<List<Object>> lstObj = new ArrayList<List<Object>>(infoList.size());
		List<Object> lstCObj;
		T tInfo;
		for (String json : infoList) {
			try {
				lstCObj = new ArrayList<Object>(this.fieldSize);
				tInfo = JsonUtil.parseObject(json, clazz);
				for (Field f : fields) {
					try {
						lstCObj.add(f.get(tInfo));
					} catch (Exception e) {
						e.printStackTrace();
						lstCObj.add(null);
					}
				}

				lstObj.add(lstCObj);
			} catch (Exception e) {
				LogManager.error(this.param.DataName + "反序列化出错:" + StringUtil.getErrorDetail(e) + " 数据:" +json);
				throw e;
			}
		}

		return lstObj;
	}

}