package cy.its.device.domain.model;

public class SurveySys {
    private String surveySystemId;

    private String surveySystemName;

    private String orgId;

    public String getSurveySystemId() {
        return surveySystemId;
    }

    public void setSurveySystemId(String surveySystemId) {
        this.surveySystemId = surveySystemId;
    }

    public String getSurveySystemName() {
        return surveySystemName;
    }

    public void setSurveySystemName(String surveySystemName) {
        this.surveySystemName = surveySystemName;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}