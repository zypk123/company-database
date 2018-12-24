package cy.its.vehTrack.mybatis.model;

import java.util.Date;

public class BigdataAnalysis {
    private Long VIO_ANALYSIS_ID;

    private String ANALYSIS_APPNAME;

    private String ANALYSIS_ALAIS;

    private Short ANALYSIS_FLAG;

    private String CREATOR_ID;

    private Date CREATE_TIME;

    private Date UPDATE_TIME;

    private Date END_TIME;

    public Long getVIO_ANALYSIS_ID() {
        return VIO_ANALYSIS_ID;
    }

    public void setVIO_ANALYSIS_ID(Long VIO_ANALYSIS_ID) {
        this.VIO_ANALYSIS_ID = VIO_ANALYSIS_ID;
    }

    public String getANALYSIS_APPNAME() {
        return ANALYSIS_APPNAME;
    }

    public void setANALYSIS_APPNAME(String ANALYSIS_APPNAME) {
        this.ANALYSIS_APPNAME = ANALYSIS_APPNAME;
    }

    public String getANALYSIS_ALAIS() {
        return ANALYSIS_ALAIS;
    }

    public void setANALYSIS_ALAIS(String ANALYSIS_ALAIS) {
        this.ANALYSIS_ALAIS = ANALYSIS_ALAIS;
    }

    public Short getANALYSIS_FLAG() {
        return ANALYSIS_FLAG;
    }

    public void setANALYSIS_FLAG(Short ANALYSIS_FLAG) {
        this.ANALYSIS_FLAG = ANALYSIS_FLAG;
    }

    public String getCREATOR_ID() {
        return CREATOR_ID;
    }

    public void setCREATOR_ID(String CREATOR_ID) {
        this.CREATOR_ID = CREATOR_ID;
    }

    public Date getCREATE_TIME() {
        return CREATE_TIME;
    }

    public void setCREATE_TIME(Date CREATE_TIME) {
        this.CREATE_TIME = CREATE_TIME;
    }

    public Date getUPDATE_TIME() {
        return UPDATE_TIME;
    }

    public void setUPDATE_TIME(Date UPDATE_TIME) {
        this.UPDATE_TIME = UPDATE_TIME;
    }

    public Date getEND_TIME() {
        return END_TIME;
    }

    public void setEND_TIME(Date END_TIME) {
        this.END_TIME = END_TIME;
    }
}