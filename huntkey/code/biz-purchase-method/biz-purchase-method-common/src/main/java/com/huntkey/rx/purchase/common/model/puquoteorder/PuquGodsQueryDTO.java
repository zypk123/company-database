package com.huntkey.rx.purchase.common.model.puquoteorder;

public class PuquGodsQueryDTO {

    /**
     * 供应商(编号或简称)模糊查询关键字
     */
    private String suppKey;

    /**
     * 生效日期开始日期
     */
    private String startTime;

    /**
     * 生效日期结束时间
     */
    private String endTime;

    /**
     * 物品编号或名称关键字
     */
    private String godsKey;

    /**
     * 物品状态
     */
    private String godsStatus;

    /**
     * 物品分类
     */
    private String godsClassId;

    /**
     * 园区
     */
    private String parkId;

    /**
     * 币别
     */
    private String currId;

    /**
     * 当前页
     */
    private int page=1;

    /**
     * 每页返回记录数
     */
    private int rows=10;

    public int getPage() {
        if(page<1){
            page= 1;
        }
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        if(rows<1){
            rows= 1;
        }
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public String getSuppKey() {
        return suppKey;
    }

    public void setSuppKey(String suppKey) {
        this.suppKey = suppKey;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getGodsKey() {
        return godsKey;
    }

    public void setGodsKey(String godsKey) {
        this.godsKey = godsKey;
    }

    public String getGodsStatus() {
        return godsStatus;
    }

    public void setGodsStatus(String godsStatus) {
        this.godsStatus = godsStatus;
    }

    public String getGodsClassId() {
        return godsClassId;
    }

    public void setGodsClassId(String godsClassId) {
        this.godsClassId = godsClassId;
    }

    public String getParkId() {
        return parkId;
    }

    public void setParkId(String parkId) {
        this.parkId = parkId;
    }

    public String getCurrId() {
        return currId;
    }

    public void setCurrId(String currId) {
        this.currId = currId;
    }

}
