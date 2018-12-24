package cy.its.syscfg.mybatis.model;

import java.math.BigDecimal;

public class T_Sys_Service_Resource {
    private String serviceResourceId;

    private String serviceResourceName;

    private String serviceResourceType;

    private String contactTel;

    private String contactPerson;

    private String resourceAddress;

    private BigDecimal longitude;

    private BigDecimal latitude;

    private String nearestGateway;

    private Double distanceFromGateway;

    public String getServiceResourceId() {
        return serviceResourceId;
    }

    public void setServiceResourceId(String serviceResourceId) {
        this.serviceResourceId = serviceResourceId;
    }

    public String getServiceResourceName() {
        return serviceResourceName;
    }

    public void setServiceResourceName(String serviceResourceName) {
        this.serviceResourceName = serviceResourceName;
    }

    public String getServiceResourceType() {
        return serviceResourceType;
    }

    public void setServiceResourceType(String serviceResourceType) {
        this.serviceResourceType = serviceResourceType;
    }

    public String getContactTel() {
        return contactTel;
    }

    public void setContactTel(String contactTel) {
        this.contactTel = contactTel;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getResourceAddress() {
        return resourceAddress;
    }

    public void setResourceAddress(String resourceAddress) {
        this.resourceAddress = resourceAddress;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public String getNearestGateway() {
        return nearestGateway;
    }

    public void setNearestGateway(String nearestGateway) {
        this.nearestGateway = nearestGateway;
    }

    public Double getDistanceFromGateway() {
        return distanceFromGateway;
    }

    public void setDistanceFromGateway(Double distanceFromGateway) {
        this.distanceFromGateway = distanceFromGateway;
    }
}