package com.huntkey.rx.purchase.common.model.relation;

/**
 * Created by xuyf on 2018/2/10 0010.
 */
public class RelationOrderUniqueCheckDTO {

    private String orderId;

    private String orderNbr;

    private String orderStatus;

    private String remoUscc;

    private String remoCode;

    private String remoShortName;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNbr() {
        return orderNbr;
    }

    public void setOrderNbr(String orderNbr) {
        this.orderNbr = orderNbr;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getRemoUscc() {
        return remoUscc;
    }

    public void setRemoUscc(String remoUscc) {
        this.remoUscc = remoUscc;
    }

    public String getRemoCode() {
        return remoCode;
    }

    public void setRemoCode(String remoCode) {
        this.remoCode = remoCode;
    }

    public String getRemoShortName() {
        return remoShortName;
    }

    public void setRemoShortName(String remoShortName) {
        this.remoShortName = remoShortName;
    }
}
