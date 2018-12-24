package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.Weather;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class WeatherBulkDAL  extends BulkDAL<Weather>  {

	public WeatherBulkDAL() {
		super(Weather.class);
	}

	@Override
	void extendCols(List<String[]> lst) { 
		lst.add(new String[] { "METEOROLOGIC_DATA_ID", "SEQ_DEFAULT.NEXTVAL" }); 
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_TRAFFIC_WEATHER";
		String dataName = "WEATHER";
		int cacheMaxSize = 200;
		int maxBatchSize = 100;
		int readMaxPeriod = 3000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_WEATHER;
		String queueName = "Data2DB_WEATHER";

		return new BulkParam(tableName, dataName, cacheMaxSize,
				maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
