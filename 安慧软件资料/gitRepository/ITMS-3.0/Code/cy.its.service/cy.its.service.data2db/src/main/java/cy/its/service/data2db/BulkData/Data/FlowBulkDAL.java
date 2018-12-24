package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.TrafficFlow;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class FlowBulkDAL extends BulkDAL<TrafficFlow> {

	public FlowBulkDAL() {
		super(TrafficFlow.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "FLOW_ID", "SEQ_DEFAULT.NEXTVAL" });
		lst.add(new String[] { "CREATE_TIME", "SYSDATE" });
	}


	@Override
	public BulkParam getBulkParam() {

		String tableName = "T_FLOW_FIVE_MIN";
		String dataName = "FLOW";
		int cacheMaxSize = 200;
		int maxBatchSize = 100;
		int readMaxPeriod = 5000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_FLOW;
		String queueName = "Data2DB_FLOW";

		return new BulkParam(tableName, dataName, cacheMaxSize, maxBatchSize, readMaxPeriod, routeKey, queueName);
	}
}
