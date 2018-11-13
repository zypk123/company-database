package com.huntkey.rx.purchase.common.model.taxrate;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 税率类DTO
 *
 * @author zhoucj
 * @date 2018/1/19
 */
public class TaxrateDTO implements Serializable {
    //税率类id
    @JSONField(name = "id")
    private String id;

    //税率类名称
    @JSONField(name = "taxr_name")
    private String taxrName;

    //税率类-是否可抵扣
    @JSONField(name = "taxr_isdeduct")
    private String taxrIsDeduct;

    //税率类-详细税率
    @JSONField(name = "taxr_detail")
    private BigDecimal taxrDetail;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaxrName() {
        return taxrName;
    }

    public void setTaxrName(String taxrName) {
        this.taxrName = taxrName;
    }

    public String getTaxrIsDeduct() {
        return taxrIsDeduct;
    }

    public void setTaxrIsDeduct(String taxrIsDeduct) {
        this.taxrIsDeduct = taxrIsDeduct;
    }

    public BigDecimal getTaxrDetail() {
        return taxrDetail;
    }

    public void setTaxrDetail(BigDecimal taxrDetail) {
        this.taxrDetail = taxrDetail;
    }
}
