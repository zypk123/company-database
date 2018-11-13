package com.huntkey.rx.purchase.common.model.balanceinito;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 应收应付初始化单据管理
 * 保存时 提交的参数
 * @author yaoss
 */
public class BalanceinitoDTO {

    private List<BalanceinitoSetDTO> blioInitBalaSet;

    public List<BalanceinitoSetDTO> getBlioInitBalaSet() {
        return blioInitBalaSet;
    }

    public void setBlioInitBalaSet(List<BalanceinitoSetDTO> blioInitBalaSet) {
        this.blioInitBalaSet = blioInitBalaSet;
    }

    public  static  class BalanceinitoSetDTO{
        /**
         * 伙伴id
         */
        private String blioiRelation;
        /**
         * 结算法人
         */
        private String blioiCorp;
        /**
         * 结算园区
         */
        private String blioiPark;
        /**
         * 结算币别
         */
        private String blioiCurrency;
        /**
         * 类型
         */
        private Integer blioiType;
        /**
         * 期初余额
         */
        private BigDecimal blioiBalaAmt;
        /**
         * 开账日期
         */
        private Date blioiAcctDate;
        /**
         * 备注
         */
        private String blioiRemark;

        public String getBlioiRelation() {
            return blioiRelation;
        }

        public void setBlioiRelation(String blioiRelation) {
            this.blioiRelation = blioiRelation;
        }

        public String getBlioiCorp() {
            return blioiCorp;
        }

        public void setBlioiCorp(String blioiCorp) {
            this.blioiCorp = blioiCorp;
        }

        public String getBlioiPark() {
            return blioiPark;
        }

        public void setBlioiPark(String blioiPark) {
            this.blioiPark = blioiPark;
        }

        public String getBlioiCurrency() {
            return blioiCurrency;
        }

        public void setBlioiCurrency(String blioiCurrency) {
            this.blioiCurrency = blioiCurrency;
        }

        public Integer getBlioiType() {
            return blioiType;
        }

        public void setBlioiType(Integer blioiType) {
            this.blioiType = blioiType;
        }

        public BigDecimal getBlioiBalaAmt() {
            return blioiBalaAmt;
        }

        public void setBlioiBalaAmt(BigDecimal blioiBalaAmt) {
            this.blioiBalaAmt = blioiBalaAmt;
        }

        public Date getBlioiAcctDate() {
            return blioiAcctDate;
        }

        public void setBlioiAcctDate(Date blioiAcctDate) {
            this.blioiAcctDate = blioiAcctDate;
        }

        public String getBlioiRemark() {
            return blioiRemark;
        }

        public void setBlioiRemark(String blioiRemark) {
            this.blioiRemark = blioiRemark;
        }

    }


}
