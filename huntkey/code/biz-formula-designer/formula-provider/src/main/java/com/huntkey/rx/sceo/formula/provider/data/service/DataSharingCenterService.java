package com.huntkey.rx.sceo.formula.provider.data.service;

import com.huntkey.rx.sceo.formula.provider.data.entity.DataSharingCenterParam;

/**
 * @author chenfei on 2017/8/1.
 */
public interface DataSharingCenterService {

    /**
     * search
     * @param param
     * @return
     */
    Object search(DataSharingCenterParam param);

    /**
     * load
     * @param param
     * @return
     */
    Object load(DataSharingCenterParam param);
}
