package cy.its.vehTrack.domain.repository;

public interface ISparkRepository {
	
	/**
	 * 根据serviceName去调用大数据spark任务并返回jobid
	 * @param query
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public String  getSparkJobId(String query,String serviceName) throws Exception;

}
