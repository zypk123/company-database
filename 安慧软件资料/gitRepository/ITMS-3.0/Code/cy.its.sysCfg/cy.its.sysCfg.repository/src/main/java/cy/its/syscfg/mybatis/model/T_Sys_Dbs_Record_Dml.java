package cy.its.syscfg.mybatis.model;

public class T_Sys_Dbs_Record_Dml {
    private String operateContentId;

    private String opeLogId;

    private String tableName;

    private Short dmlIndex;

    private String operateType;

    private String operateDataContent;

    public String getOperateContentId() {
        return operateContentId;
    }

    public void setOperateContentId(String operateContentId) {
        this.operateContentId = operateContentId;
    }

    public String getOpeLogId() {
        return opeLogId;
    }

    public void setOpeLogId(String opeLogId) {
        this.opeLogId = opeLogId;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Short getDmlIndex() {
        return dmlIndex;
    }

    public void setDmlIndex(Short dmlIndex) {
        this.dmlIndex = dmlIndex;
    }

    public String getOperateType() {
        return operateType;
    }

    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    public String getOperateDataContent() {
        return operateDataContent;
    }

    public void setOperateDataContent(String operateDataContent) {
        this.operateDataContent = operateDataContent;
    }
}