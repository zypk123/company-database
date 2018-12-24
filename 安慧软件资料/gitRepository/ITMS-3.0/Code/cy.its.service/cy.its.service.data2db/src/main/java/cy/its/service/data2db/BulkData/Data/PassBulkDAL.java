package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.dataModel.VehTrackPass;
import cy.its.service.common.ioc.Export;
import cy.its.service.common.ConstValue;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class PassBulkDAL extends BulkDAL<VehTrackPass> {

	public PassBulkDAL() {
		super(VehTrackPass.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "VEHICLE_PASS_ID", "SEQ_DEFAULT.NEXTVAL" });
		lst.add(new String[] { "CREATE_TIME", "SYSDATE" });
	}	

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_VEHTRACK_PASS";
		String dataName = "PASS";
		int cacheMaxSize = 1000;
		int maxBatchSize = 100;
		int readMaxPeriod = 5000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_PASS;
		String queueName = "Data2DB_PASS";

		return new BulkParam(tableName, dataName, cacheMaxSize,
        maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
