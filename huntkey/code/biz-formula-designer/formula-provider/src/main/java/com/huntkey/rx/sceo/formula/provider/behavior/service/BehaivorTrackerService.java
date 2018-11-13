package com.huntkey.rx.sceo.formula.provider.behavior.service;

import com.huntkey.rx.sceo.formula.common.model.TdfUserBehavior;
import java.util.Map;

/**
 * @author lulx on 2017/6/15 0015.
 */
public interface BehaivorTrackerService {

    /**
     * loadCommonlyFormulasforDb
     * @param userId
     * @return
     */
    Map<String,Object> loadCommonlyFormulasforDb(String userId);

    /**
     * updateCommonlyFormulas
     * @param userId
     * @param bytes
     * @return
     */
    int updateCommonlyFormulas(String userId, byte[] bytes);

    /**
     * saveTdfUserBehavior
     * @param tdfUserBehavior
     * @return
     */
    int saveTdfUserBehavior(TdfUserBehavior tdfUserBehavior);

    /**
     * getUserBehaviorFromRedis
     * @param userId
     * @return
     */
    Map<String,Object> getUserBehaviorFromRedis(String userId);

    /**
     * initUserBehavior
     * @return
     */
    Map<String,Object> initUserBehavior();

    /**
     * saveTdfUserBehaviorToRedisAndDb
     * @param map
     * @param userId
     */
    void saveTdfUserBehaviorToRedisAndDb(Map<String,Object> map,String userId);

    /**
     * updateTdfUserBehaviorToRedisAndDb
     * @param map
     * @param userId
     * @param funcName
     */
    void updateTdfUserBehaviorToRedisAndDb(Map<String,Object> map,String userId,String funcName);

    /**
     * loadCommonlyFormulas
     * @param userId
     * @return
     */
    Map<String,Object> loadCommonlyFormulas(String userId);
}
