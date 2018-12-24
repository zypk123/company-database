package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.TrafficRegionFlow;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class RegionFlowDAL extends BulkDAL<TrafficRegionFlow> {

	public RegionFlowDAL() {
		super(TrafficRegionFlow.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "REGION_STATE_ID", "SEQ_DEFAULT.NEXTVAL" });
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_TRAFFIC_REGION_FLOW";
		String dataName = "REGION_FLOW";
		int cacheMaxSize = 1000;
		int maxBatchSize = 100;
		int readMaxPeriod = 3000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_REGION_FLOW;
		String queueName = "Data2DB_REGION_FLOW";

		return new BulkParam(tableName, dataName, cacheMaxSize,
        maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
