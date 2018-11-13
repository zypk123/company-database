package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;

import java.util.List;

/**
 * @author chenfei on 2017/5/15.
 */
public interface FunctionDescriberService {

    /**
     * loadAllFunctions
     * @return
     */
    List<FunctionDescriber> loadAllFunctions();

    /**
     * loadCustomizeFunctions
     * @return
     */
    List<FunctionDescriber> loadCustomizeFunctions();

    /**
     * addClassify
     * @param classify
     */
    void addClassify(String classify);

    /**
     * the remove classify is unnecessary.
     * if you update some classify to unusable,
     * and the sort function will not to use this item.
     *
     * @param classify
     */
    void removeClassify(String classify);
}
