package cy.its.service.data2db.BulkData.Utils;

public class BulkParam {
	public BulkParam(String tableName, String dataName, int cacheMaxSize, int maxBatchSize, int readMaxPeriod, String routeKey,
			String queueName) {
		TableName = tableName;
		DataName = dataName;
		CacheMaxSize = cacheMaxSize;
		MaxBatchSize = maxBatchSize;
		ReadMaxPeriod = readMaxPeriod;
		RouteKey = routeKey;
		QueueName = queueName;
	}

	public String TableName;
	public String DataName;
	public int CacheMaxSize;
	public int MaxBatchSize;
	public int ReadMaxPeriod;
	public String RouteKey;
	public String QueueName;
}
