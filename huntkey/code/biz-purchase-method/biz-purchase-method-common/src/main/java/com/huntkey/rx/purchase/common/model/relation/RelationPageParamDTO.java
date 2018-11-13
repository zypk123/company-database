package com.huntkey.rx.purchase.common.model.relation;

import java.util.Arrays;

/**
 * 伙伴查询条件DTO
 *
 * @author xuyf on 2018/1/22 0022.
 */
public class RelationPageParamDTO {

    /**
     * 伙伴编号
     */
    private String relaCode;

    /**
     * 伙伴简称
     */
    private String relaShortName;

    /**
     * 伙伴类型
     */
    private String[] relaType;

    /**
     * 状态
     */
    private String relaStatus;

    /**
     * 当前页
     */
    private int currentPage;

    /**
     * 每页记录数
     */
    private int pageSize;

    public String getRelaCode() {
        return relaCode;
    }

    public void setRelaCode(String relaCode) {
        this.relaCode = relaCode;
    }

    public String getRelaShortName() {
        return relaShortName;
    }

    public void setRelaShortName(String relaShortName) {
        this.relaShortName = relaShortName;
    }

    public String[] getRelaType() {
        return relaType;
    }

    public void setRelaType(String[] relaType) {
        this.relaType = relaType;
    }

    public String getRelaStatus() {
        return relaStatus;
    }

    public void setRelaStatus(String relaStatus) {
        this.relaStatus = relaStatus;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    @Override
    public String toString() {
        return "RelationPageParamDTO{" +
                "relaCode='" + relaCode + '\'' +
                ", relaShortName='" + relaShortName + '\'' +
                ", relaType=" + Arrays.toString(relaType) +
                ", relaStatus='" + relaStatus + '\'' +
                ", currentPage=" + currentPage +
                ", pageSize=" + pageSize +
                '}';
    }
}
