package cy.its.vehTrack.domain.service;

public interface ISparkService {
	
	/**
	 * 根据serviceName去调用大数据spark任务并返回jobid
	 * @param query
	 * @param serviceName
	 * @return
	 * @throws Exception
	 */
	public String  getSparkJobId(String query,String serviceName) throws Exception;

}
