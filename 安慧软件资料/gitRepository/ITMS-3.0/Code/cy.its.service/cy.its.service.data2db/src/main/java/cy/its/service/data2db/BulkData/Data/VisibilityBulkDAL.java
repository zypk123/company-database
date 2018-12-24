package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.Visibility;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class VisibilityBulkDAL extends BulkDAL<Visibility>{

	public VisibilityBulkDAL() {
		super(Visibility.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "VISIBILITY_DATA_ID", "SEQ_DEFAULT.NEXTVAL" });
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_TRAFFIC_VISIBILITY";
		String dataName = "VISIBILITY";
		int cacheMaxSize = 100;
		int maxBatchSize = 30;
		int readMaxPeriod = 2000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_VISIBILITY;
		String queueName = "Data2DB_VISIBILITY";

		return new BulkParam(tableName, dataName, cacheMaxSize,
				maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
