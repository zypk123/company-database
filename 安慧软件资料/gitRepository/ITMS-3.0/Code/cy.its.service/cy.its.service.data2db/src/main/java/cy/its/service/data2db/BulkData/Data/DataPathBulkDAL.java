package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.DeviceDataPath;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class DataPathBulkDAL extends BulkDAL<DeviceDataPath>{

	public DataPathBulkDAL() {
		super(DeviceDataPath.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "DATA_PATH_ID", "SEQ_DEFAULT.NEXTVAL" });
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_DEVICE_DATA_PATH";
		String dataName = "DATAPATH";
		int cacheMaxSize = 2000;
		int maxBatchSize = 100;
		int readMaxPeriod = 5000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_DEVICE_DATA_PATH;
		String queueName = "Data2DB_DATA_PATH";

		return new BulkParam(tableName, dataName, cacheMaxSize, maxBatchSize, readMaxPeriod, routeKey, queueName);
	
	}

}
