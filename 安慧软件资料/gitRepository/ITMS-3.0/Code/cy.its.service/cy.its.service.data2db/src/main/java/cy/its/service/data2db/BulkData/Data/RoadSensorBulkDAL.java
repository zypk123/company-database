package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.RoadSensor;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class RoadSensorBulkDAL extends BulkDAL<RoadSensor> {

	public RoadSensorBulkDAL() {
		super(RoadSensor.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "ROADSENSOR_DATA_ID", "SEQ_DEFAULT.NEXTVAL" });
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_TRAFFIC_ROADSENSOR";
		String dataName = "ROADSENSOR";
		int cacheMaxSize = 100;
		int maxBatchSize = 30;
		int readMaxPeriod = 2000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_ROAD_SENSOR;
		String queueName = "Data2DB_ROADSENSOR";

		return new BulkParam(tableName, dataName, cacheMaxSize,
				maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
