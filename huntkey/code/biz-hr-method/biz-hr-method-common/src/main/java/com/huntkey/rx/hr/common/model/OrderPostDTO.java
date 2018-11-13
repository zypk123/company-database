package com.huntkey.rx.hr.common.model;

/**
 * @author zhanggj
 * @createTime 2018/1/22
 * @desc 该类用于岗位是否存在待审单校验
 */

public class OrderPostDTO {
    String orderId;
    String orderNbr;
    String postId;
    String postName;

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

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName;
    }

    public String getPostNbr() {
        return postNbr;
    }

    public void setPostNbr(String postNbr) {
        this.postNbr = postNbr;
    }

    String postNbr;
}
