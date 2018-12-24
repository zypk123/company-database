package cy.its.service.data2db.BulkData.Data;

import java.util.List;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.MsgInfo;
import cy.its.service.common.ioc.Export;
import cy.its.service.data2db.BulkData.Utils.BulkParam;

@Export
public class MsgBulkDAL extends BulkDAL<MsgInfo>{

	public MsgBulkDAL() {
		super(MsgInfo.class);
	}

	@Override
	void extendCols(List<String[]> lst) {
		lst.add(new String[] { "ID", "SEQ_DEFAULT.NEXTVAL" });
	}

	@Override
	public BulkParam getBulkParam() {
		String tableName = "T_MSG_INFO";
		String dataName = "MSG";
		int cacheMaxSize = 200;
		int maxBatchSize = 100;
		int readMaxPeriod = 10000;
		String routeKey = ConstValue.ROUTE_KEY_STANDARD_SMS;
		String queueName = "Data2DB_SMS";

		return new BulkParam(tableName, dataName, cacheMaxSize, maxBatchSize, readMaxPeriod, routeKey, queueName);
	}

}
