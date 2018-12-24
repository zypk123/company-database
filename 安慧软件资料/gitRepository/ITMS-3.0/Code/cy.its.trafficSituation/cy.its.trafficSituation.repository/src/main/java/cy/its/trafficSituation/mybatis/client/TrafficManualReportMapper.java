package cy.its.trafficSituation.mybatis.client;

import cy.its.trafficSituation.domain.model.TrafficManualReport;

public interface TrafficManualReportMapper {
    int deleteByPrimaryKey(String manualReportTraId);

    int insert(TrafficManualReport record);

    int insertSelective(TrafficManualReport record);

    TrafficManualReport selectByPrimaryKey(String manualReportTraId);

    int updateByPrimaryKeySelective(TrafficManualReport record);

    int updateByPrimaryKey(TrafficManualReport record);
}