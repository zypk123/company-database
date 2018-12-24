package cy.its.vehTrack.mybatis.client;

import cy.its.vehTrack.mybatis.model.BigdataAnalysis;
import cy.its.vehTrack.mybatis.model.BigdataAnalysisWithBLOBs;

public interface BigdataAnalysisMapper {
    int deleteByPrimaryKey(Short VIO_ANALYSIS_ID);

    int insert(BigdataAnalysisWithBLOBs record);

    int insertSelective(BigdataAnalysisWithBLOBs record);

    BigdataAnalysisWithBLOBs selectByPrimaryKey(Long VIO_ANALYSIS_ID);

    int updateByPrimaryKeySelective(BigdataAnalysisWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BigdataAnalysisWithBLOBs record);

    int updateByPrimaryKey(BigdataAnalysis record);
}