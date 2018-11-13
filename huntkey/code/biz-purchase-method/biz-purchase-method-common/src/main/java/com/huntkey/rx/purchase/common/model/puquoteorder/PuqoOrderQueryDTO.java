package com.huntkey.rx.purchase.common.model.puquoteorder;

/**
 *  采购报价单 查询 条件
 * @author yaoss
 */
public class PuqoOrderQueryDTO {

    /**
     * 单据单号 模糊查询关键字
     */
    private String orderNbrKey;

    /**
     * 供应商模糊查询关键字
     */
    private String suppKey;

    /**
     * 申请时间开始日期
     */
    private String startTime;

    /**
     * 申请时间结束时间
     */
    private String endTime;

    /**
     * 报价单状态
     */
    private String ordeStatus;

    /**
     * 币别id
     */
    private String currenyId;

    /**
     * 申请人模糊查询关键字
     */
    private String ordeUserNameKey;

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

    public String getOrderNbrKey() {
        return orderNbrKey;
    }

    public void setOrderNbrKey(String orderNbrKey) {
        this.orderNbrKey = orderNbrKey;
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

    public String getOrdeStatus() {
        return ordeStatus;
    }

    public void setOrdeStatus(String ordeStatus) {
        this.ordeStatus = ordeStatus;
    }

    public String getCurrenyId() {
        return currenyId;
    }

    public void setCurrenyId(String currenyId) {
        this.currenyId = currenyId;
    }

    public String getOrdeUserNameKey() {
        return ordeUserNameKey;
    }

    public void setOrdeUserNameKey(String ordeUserNameKey) {
        this.ordeUserNameKey = ordeUserNameKey;
    }
}
