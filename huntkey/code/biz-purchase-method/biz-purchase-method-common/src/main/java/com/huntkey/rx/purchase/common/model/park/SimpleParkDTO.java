package com.huntkey.rx.purchase.common.model.park;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.util.NullUtils;

/**
 * Created by zhoucj on 2018/1/25.
 */
public class SimpleParkDTO {
    //园区id
    @JSONField(name = "id")
    private String puqoPark;

    @JSONField(name="rpak_name")
    private String parkName;//园区名称

    public String getPuqoPark() {
        return puqoPark;
    }

    public void setPuqoPark(String puqoPark) {
        this.puqoPark = NullUtils.valueOf(puqoPark);
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String rpakName) {
        this.parkName = NullUtils.valueOf(rpakName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleParkDTO that = (SimpleParkDTO) o;

        if (!puqoPark.equals(that.puqoPark)) return false;
        return parkName.equals(that.parkName);
    }

    @Override
    public int hashCode() {
        int result = puqoPark.hashCode();
        result = 31 * result + parkName.hashCode();
        return result;
    }
}
