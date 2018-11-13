package com.huntkey.rx.sceo.formula.provider.data.service;

import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.engine.DataProvider;

/**
 * @author lulx on 2017/6/14 0014.
 */
public interface DataGraperService {

    /**
     * grapData
     * @param variant
     * @param provider
     */
    void grapData(TvmVariant variant, DataProvider provider);
}
