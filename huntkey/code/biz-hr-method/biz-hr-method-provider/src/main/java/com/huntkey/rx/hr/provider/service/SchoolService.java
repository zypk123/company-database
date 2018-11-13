package com.huntkey.rx.hr.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;

/**
 * Created by weijian on 2017/12/14.
 */
public interface SchoolService {
    /**
     * 高校查询
     * @return
     */
    Result getSchools(String serchContent);
}
