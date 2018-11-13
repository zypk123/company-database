package com.huntkey.rx.purchase.common.model.goods;

import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.purchase.common.model.park.SimpleParkDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoLadderPriceSetDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoLadderPriceSetOldDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.SimplePuqoPriceSetDTO;
import com.huntkey.rx.purchase.common.util.NullUtils;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  价格异动明细DTO 用于返回采购报价单的价格异动明细数据
 * @author zhoucj
 * @date 2018/1/20
 */
public class GoodsInfoAndParkDTO implements Serializable {
    /**
     * 物品id
     */
    private String puqoGoods;

    /**
     * 物品编码
     */
    private String godsCode;

    /**
     * 物品名称
     */
    private String godsName;

    /**
     * 规格型号
     */
    private String godsModel;

    /**
     * 单位
     */
    private String puqoUm;

    /**
     * 单号
     */
    private String order;

    /**
     * 默认园区id
     */
    private String puqoPark;

    /**
     * 默认园区名称
     */
    private String parkName;

    /**
     * 涨跌率
     */
    private String puqoUpdownRate;
    /**
     * 单价
     */
    private String puqoPrice;
    /**
     * 返利额
     */
    private String puqoRebate;
    /**
     * 生效日期
     */
    private String puqoDateBeg;
    /**
     * 失效日期
     */
    private String puqoDateEnd;
    /**
     * 是否是阶梯报价
     */
    private String puqoIsladder;
    /**
     * 单价old
     */
    private String puqoPriceOld;
    /**
     * 返利额old
     */
    private String puqoRebateOld;
    /**
     * 生效日期old
     */
    private String puqoDateBegOld;
    /**
     * 失效日期old
     */
    private String puqoDateEndOld;
    /**
     * 是否是阶梯报价old
     */
    private String puqoIsladderOld;

    /**
     * 阶梯报价明细
     */
    private List<PuqoLadderPriceSetDTO> puqoLadderSet;

    /**
     * 阶梯报价明细old
     */
    private List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld;

    //可选园区集合
    private List<SimplePuqoPriceSetDTO> selectableParks;

    public GoodsInfoAndParkDTO() {
        this.puqoGoods = "";
        this.godsCode = "";
        this.godsName = "";
        this.godsModel = "";
        this.puqoUm = "";
        this.puqoPark = "";
        this.parkName = "";
        this.order = "";
        this.puqoUm = "";
        this.puqoUpdownRate = "";
        this.puqoPrice = "";
        this.puqoRebate = "";
        this.puqoDateBeg = "";
        this.puqoDateEnd = "";
        this.puqoIsladder = "";
        this.puqoPriceOld = "";
        this.puqoRebateOld = "";
        this.puqoDateBegOld = "";
        this.puqoDateEndOld = "";
        this.puqoIsladderOld = "0";
        this.puqoLadderSet = new ArrayList<>();
        this.puqoLadderSetOld = new ArrayList<>();
        this.selectableParks = new ArrayList<>();
    }

    public String getPuqoGoods() {
        return puqoGoods;
    }

    public void setPuqoGoods(String puqoGoods) {
        this.puqoGoods = NullUtils.valueOf(puqoGoods);
    }

    public String getGodsCode() {
        return godsCode;
    }

    public void setGodsCode(String godsCode) {
        this.godsCode = NullUtils.valueOf(godsCode);
    }

    public String getGodsName() {
        return godsName;
    }

    public void setGodsName(String godsName) {
        this.godsName = NullUtils.valueOf(godsName);
    }

    public String getGodsModel() {
        return godsModel;
    }

    public void setGodsModel(String godsModel) {
        this.godsModel = NullUtils.valueOf(godsModel);
    }

    public String getPuqoUm() {
        return puqoUm;
    }

    public void setPuqoUm(String puqoUm) {
        this.puqoUm = NullUtils.valueOf(puqoUm);
    }

    public String getPuqoPark() {
        return puqoPark;
    }

    public void setPuqoPark(String puqoPark) {
        this.puqoPark = NullUtils.valueOf(puqoPark);
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = NullUtils.valueOf(parkName);
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = NullUtils.valueOf(order);
    }

    public String getPuqoUpdownRate() {
        return puqoUpdownRate;
    }

    public void setPuqoUpdownRate(BigDecimal puqoUpdownRate) {
        this.puqoUpdownRate = NullUtils.valueOf(puqoUpdownRate);
    }

    public void setPuqoUpdownRate(String puqoUpdownRate) {
        this.puqoUpdownRate = NullUtils.valueOf(puqoUpdownRate);
    }

    public String getPuqoPrice() {
        return puqoPrice;
    }

    public void setPuqoPrice(String puqoPrice) {
        this.puqoPrice = NullUtils.valueOf(puqoPrice);
    }

    public void setPuqoPrice(BigDecimal puqoPrice) {
        this.puqoPrice = NullUtils.valueOf(puqoPrice);
    }

    public void setPuqoPriceOld(String puqoPriceOld) {
        this.puqoPriceOld = NullUtils.valueOf(puqoPriceOld);
    }

    public String getPuqoRebate() {
        return puqoRebate;
    }

    public void setPuqoRebate(BigDecimal puqoRebate) {
        this.puqoRebate = NullUtils.valueOf(puqoRebate);
    }

    public void setPuqoRebate(String puqoRebate) {
        this.puqoRebate = NullUtils.valueOf(puqoRebate);
    }

    public String getPuqoDateBeg() {
        return puqoDateBeg;
    }

    public void setPuqoDateBeg(Date puqoDateBeg) {
        this.puqoDateBeg = puqoDateBeg == null ? "" : DateUtil.formatDate(puqoDateBeg);
    }

    public void setPuqoDateBeg(String puqoDateBeg) {
        this.puqoDateBeg = NullUtils.valueOf(puqoDateBeg);
    }

    public String getPuqoDateEnd() {
        return puqoDateEnd;
    }

    public void setPuqoDateEnd(Date puqoDateEnd) {
        this.puqoDateEnd = puqoDateEnd == null ? "" : DateUtil.formatDate(puqoDateEnd);
    }

    public void setPuqoDateEnd(String puqoDateEnd) {
        this.puqoDateEnd = NullUtils.valueOf(puqoDateEnd);
    }

    public String getPuqoIsladder() {
        return puqoIsladder;
    }

    public void setPuqoIsladder(Integer puqoIsladder) {
        this.puqoIsladder = NullUtils.valueOf(puqoIsladder);
    }

    public void setPuqoIsladder(String puqoIsladder) {
        this.puqoIsladder = NullUtils.valueOf(puqoIsladder);
    }

    public String getPuqoPriceOld() {
        return puqoPriceOld;
    }

    public void setPuqoPriceOld(BigDecimal puqoPriceOld) {
        this.puqoPriceOld = NullUtils.valueOf(puqoPriceOld);
    }

    public void setPuqoIsladderOld(String puqoIsladderOld) {
        this.puqoIsladderOld = NullUtils.valueOf(puqoIsladderOld);
    }

    public String getPuqoRebateOld() {
        return puqoRebateOld;
    }

    public void setPuqoRebateOld(BigDecimal puqoRebateOld) {
        this.puqoRebateOld = NullUtils.valueOf(puqoRebateOld);
    }

    public void setPuqoRebateOld(String puqoRebateOld) {
        this.puqoRebateOld = NullUtils.valueOf(puqoRebateOld);
    }

    public String getPuqoDateBegOld() {
        return puqoDateBegOld;
    }

    public void setPuqoDateBegOld(Date puqoDateBegOld) {
        this.puqoDateBegOld = puqoDateBegOld == null ? "" : DateUtil.formatDate(puqoDateBegOld);
    }

    public void setPuqoDateBegOld(String puqoDateBegOld) {
        this.puqoDateBegOld = NullUtils.valueOf(puqoDateBegOld);
    }

    public String getPuqoDateEndOld() {
        return puqoDateEndOld;
    }

    public void setPuqoDateEndOld(Date puqoDateEndOld) {
        this.puqoDateEndOld = puqoDateEndOld  == null ? "" : DateUtil.formatDate(puqoDateEndOld);
    }

    public void setPuqoDateEndOld(String puqoDateEndOld) {
        this.puqoDateEndOld = NullUtils.valueOf(puqoDateEndOld);
    }

    public String getPuqoIsladderOld() {
        return puqoIsladderOld;
    }

    public void setPuqoIsladderOld(Integer puqoIsladderOld) {
        this.puqoIsladderOld = NullUtils.valueOf(puqoIsladderOld);
    }

    public List<PuqoLadderPriceSetDTO> getPuqoLadderSet() {
        return puqoLadderSet;
    }

    public void setPuqoLadderSet(List<PuqoLadderPriceSetDTO> puqoLadderSet) {
        this.puqoLadderSet = puqoLadderSet;
    }

    public List<PuqoLadderPriceSetOldDTO> getPuqoLadderSetOld() {
        return puqoLadderSetOld;
    }

    public void setPuqoLadderSetOld(List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld) {
        this.puqoLadderSetOld = puqoLadderSetOld;
    }

    public List<SimplePuqoPriceSetDTO> getSelectableParks() {
        return selectableParks;
    }

    public void setSelectableParks(List<SimplePuqoPriceSetDTO> selectableParks) {
        this.selectableParks = selectableParks;
    }
}
