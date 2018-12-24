package cy.its.vehTrack.domain.service;

public interface IBigDataService {
	/**
	 * 调用大数据
	 * @param serviceName
	 * @param query
	 * @return
	 * @throws Exception
	 */
	public String loaddBigData(String serviceName,String query) throws Exception;

}
