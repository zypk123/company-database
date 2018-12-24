package cy.its.vehTrack.mybatis.model;

public class BigdataAnalysisWithBLOBs extends BigdataAnalysis {
    private String VIO_ANA_PARAMS;

    private String ANALYSIS_RESULT;

    public String getVIO_ANA_PARAMS() {
        return VIO_ANA_PARAMS;
    }

    public void setVIO_ANA_PARAMS(String VIO_ANA_PARAMS) {
        this.VIO_ANA_PARAMS = VIO_ANA_PARAMS;
    }

    public String getANALYSIS_RESULT() {
        return ANALYSIS_RESULT;
    }

    public void setANALYSIS_RESULT(String ANALYSIS_RESULT) {
        this.ANALYSIS_RESULT = ANALYSIS_RESULT;
    }
}