package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.DeviceDataPathToITMS;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class DataPathToItmsBulkDAL extends BulkDAL<DeviceDataPathToITMS>{

	public DataPathToItmsBulkDAL() {
		super(DeviceDataPathToITMS.class);
	}

	@Override
	void extendCols(List<String[]> lst) {		
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_DEVICE_DATA_PATH_ITMS";
		String dataName = "DATA_PATH_TO_ITMS";
		int cacheMaxSize = 2000;
		int maxBatchSize = 100;
		int readMaxPeriod = 5000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_DEVICE_DATA_PATH_TO_ITMS;
		String queueName = "Data2DB_DATA_PATH_TO_ITMS";

		return new BulkParam(tableName, dataName, cacheMaxSize, maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
