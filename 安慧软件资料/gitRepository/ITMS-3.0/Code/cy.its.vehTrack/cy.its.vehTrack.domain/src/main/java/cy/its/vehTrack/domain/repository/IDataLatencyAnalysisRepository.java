package cy.its.vehTrack.domain.repository;

public interface IDataLatencyAnalysisRepository {
	public String findDataLatencyList(String query) throws Exception;
}
