package com.hunkey.service;

import com.huntkey.rx.commons.utils.rest.Result;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lulx on 2017/10/18 0018 下午 1:47
 */
public interface JobPositionService {

    Result loadJobPosition(Map map, HttpServletRequest request);

    Result reverseCall(String params);

    Result creatSession(Map map);

    Result execAuto(String params);

    Result execAsync(String params);

    Result execReverseCall(String params);

    Result execSync(String params);
}
