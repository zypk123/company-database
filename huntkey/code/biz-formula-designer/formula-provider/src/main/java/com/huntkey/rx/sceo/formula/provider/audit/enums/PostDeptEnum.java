package com.huntkey.rx.sceo.formula.provider.audit.enums;

/**
 * @author zhouyou on 2017/10/24.
 */
public enum PostDeptEnum {

    /**
     * 指定岗位
     */
    APPOINTED_POST("appointed_post", "指定岗位"),

    /**
     * 指定部门
     */
    APPOINTED_DEPT("appointed_dept", "指定部门"),

    /**
     * 任职岗位
     */
    IN_POST("in_post", "任职岗位"),

    /**
     * 任职部门
     */
    IN_DEPT("in_dept", "任职部门"),

    /**
     * 制单岗位
     */
    POST_OFFICE("post_office", "制单岗位"),;

    private String name;

    private String information;

    PostDeptEnum(String name, String information) {
        this.name = name;
        this.information = information;
    }
}
