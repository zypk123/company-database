package cy.its.violation.mybatis.model;

import java.util.Date;

public class T_Vio_Upload_Log {
    private String uploadLogId;

    private String violationId;

    private Date uploadTime;

    private String uploadResult;

    private String uploadContent;

    private String interfaceId;

    public String getUploadLogId() {
        return uploadLogId;
    }

    public void setUploadLogId(String uploadLogId) {
        this.uploadLogId = uploadLogId;
    }

    public String getViolationId() {
        return violationId;
    }

    public void setViolationId(String violationId) {
        this.violationId = violationId;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public String getUploadResult() {
        return uploadResult;
    }

    public void setUploadResult(String uploadResult) {
        this.uploadResult = uploadResult;
    }

    public String getUploadContent() {
        return uploadContent;
    }

    public void setUploadContent(String uploadContent) {
        this.uploadContent = uploadContent;
    }

    public String getInterfaceId() {
        return interfaceId;
    }

    public void setInterfaceId(String interfaceId) {
        this.interfaceId = interfaceId;
    }
}