package com.huntkey.rx.sceo.formula.provider.behavior.service.impl;

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.TdfUserBehavior;
import com.huntkey.rx.sceo.formula.common.model.TdfUserBehaviorExample;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.common.util.SerializeUtils;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.behavior.dao.TdfUserBehaviorMapper;
import com.huntkey.rx.sceo.formula.provider.behavior.service.BehaivorTrackerService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;
import com.huntkey.rx.sceo.formula.provider.redis.service.IRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author lulx on 2017/6/15 0015.
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class BehaivorTrackerServiceImpl implements BehaivorTrackerService {

    /**
     * redis key 用户常用函数 +利用用户ID
     */
    public static final String REDIS_KEY_BEHAIVOR_USERID = "FORMULA_BEHAIVOR_USERID_";
    /**
     * 用户常用函数返回给界面的个数
     */
    public static final int BEHAIVOR_FUNCTION_SIZE = 12;
    /**
     * redis key 用户常用函数更新时间 +利用用户ID
     */
    public static final String REDIS_KEY_BEHAIVOR_USERID_UPDATETIME = "REDIS_KEY_BEHAIVOR_USERID_UPDATETIME_";

    @Autowired
    private TdfUserBehaviorMapper tdfUserBehaviorMapper;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private FunctionDescriberService functionDescriberService;

    @Override
    @Transactional(readOnly = true, rollbackFor = RuntimeException.class)
    public Map<String,Object> loadCommonlyFormulasforDb(String userId){
        TdfUserBehaviorExample tdfUserBehaviorExample = new TdfUserBehaviorExample();
        TdfUserBehaviorExample.Criteria criteria = tdfUserBehaviorExample.createCriteria();
        criteria.andUsrbUserIdEqualTo(userId);
        List<TdfUserBehavior> list = tdfUserBehaviorMapper.selectByExampleWithBLOBs(tdfUserBehaviorExample);
        TdfUserBehavior tdfUserBehavior = null;
        if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
            tdfUserBehavior = list.get(0);
        }
        Map<String,Object> map = null;
        if(!StringUtil.isNullOrEmpty(tdfUserBehavior)&&!StringUtil.isNullOrEmpty(tdfUserBehavior.getUsrbRecentUse())) {
            //数据库不为空
            byte[] bytes = tdfUserBehavior.getUsrbRecentUse();
            map = (Map<String,Object>) SerializeUtils.deserialize(bytes);
        }
        return map;
    }

    public TdfUserBehavior getTdfUserBehaviorByUserId(String userId){
        TdfUserBehaviorExample tdfUserBehaviorExample = new TdfUserBehaviorExample();
        TdfUserBehaviorExample.Criteria criteria = tdfUserBehaviorExample.createCriteria();
        criteria.andUsrbUserIdEqualTo(userId);
        List<TdfUserBehavior> list = tdfUserBehaviorMapper.selectByExampleWithBLOBs(tdfUserBehaviorExample);
        if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateCommonlyFormulas(String userId,byte[] bytes) {
        TdfUserBehavior tdfUserBehavior = getTdfUserBehaviorByUserId(userId);
        tdfUserBehavior.setUsrbRecentUse(bytes);
        tdfUserBehavior.setModuser(Constant.MODUSER);
        tdfUserBehavior.setModtime(new Date());
        return tdfUserBehaviorMapper.updateByPrimaryKeyWithBLOBs(tdfUserBehavior);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveTdfUserBehavior(TdfUserBehavior tdfUserBehavior) {
        tdfUserBehavior.setUsrbId(StringUtil.generateUUID());
        tdfUserBehavior.setAddtime(new Date());
        tdfUserBehavior.setAdduser(Constant.ADDUSER);
        tdfUserBehavior.setModtime(new Date());
        tdfUserBehavior.setModuser(Constant.ADDUSER);
        tdfUserBehavior.setIsenable((byte)1);
        return tdfUserBehaviorMapper.insertSelective(tdfUserBehavior);
    }

    public <K, V> Map<K, V> sortByValue(Map<K, V> map){
        List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>( map.entrySet() );
        Collections.sort(list, new Comparator<Map.Entry<K, V>>(){
            @Override
            public int compare( Map.Entry<K, V> o1, Map.Entry<K, V> o2 ){
                return ((((FunctionDescriber)o2.getValue()).getBehaivorOrder()) - (((FunctionDescriber)o1.getValue()).getBehaivorOrder())) ;
            }
        } );

        Map<K, V> result = new LinkedHashMap<K, V>();
        int i = 1;
        for (Map.Entry<K, V> entry : list){
            if(i > BEHAIVOR_FUNCTION_SIZE && 0==((FunctionDescriber)entry.getValue()).getBehaivorOrder()) {
                break;
            }
            result.put( entry.getKey(), entry.getValue() );
            i++;
        }
        return result;
    }

    @Override
    public Map<String,Object> getUserBehaviorFromRedis(String userId){
        Map<String,Object> map = null;
        try{
            byte[] behaivor = redisService.get((REDIS_KEY_BEHAIVOR_USERID+userId).getBytes("UTF-8"));
            if(!StringUtil.isNullOrEmpty(behaivor)&&behaivor.length>0){
                map = (Map<String,Object>) SerializeUtils.deserialize(behaivor);
            }
            return map;
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Map<String,Object> initUserBehavior() {
        Map<String,Object> map = null;
        FunctionHandlerBuildIn functionHelper = new FunctionHandlerBuildIn();
        Map<String, Integer> funcMap =  functionHelper.getStaticMappingsUserBehavior();
        int mapSize = funcMap.size();
        //函数池中随机取出12个函数
        Iterator<Map.Entry<String, Integer>> entries = funcMap.entrySet().iterator();
        int i = 0;
        Map.Entry<String, Integer> entry =null;
        map = new HashMap<String,Object>();
        while (entries.hasNext()&&i<BEHAIVOR_FUNCTION_SIZE) {
            entry = entries.next();
            if(StringUtil.isNullOrEmpty(entry.getKey())) {
                continue;
            }
            FunctionDescriber functionDescriber = getFunctionDescriber(entry.getKey());
            if(!StringUtil.isNullOrEmpty(functionDescriber)){
                map.put(entry.getKey(),functionDescriber);
                i++;
            }
        }
        return map;
    }

    private FunctionDescriber getFunctionDescriber(String funcName){
        List<FunctionDescriber> functionDescribers = functionDescriberService.loadAllFunctions();

        for(FunctionDescriber functionDescriber : functionDescribers){
            if(funcName.equals(functionDescriber.getFunName())){
                functionDescriber.setBehaivorOrder(0);
                return functionDescriber;
            }
        }

        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void saveTdfUserBehaviorToRedisAndDb(Map<String,Object> map, String userId){
        byte[] bytes = SerializeUtils.serialize(map);
        redisService.set(REDIS_KEY_BEHAIVOR_USERID_UPDATETIME+userId,String.valueOf(System.currentTimeMillis()));
        redisService.set((REDIS_KEY_BEHAIVOR_USERID+userId).getBytes(),bytes);
        TdfUserBehavior tdfUserBehavior = new TdfUserBehavior();
        tdfUserBehavior.setUsrbUserId(userId);
        tdfUserBehavior.setUsrbRecentUse(bytes);
        saveTdfUserBehavior(tdfUserBehavior);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void updateTdfUserBehaviorToRedisAndDb(Map<String,Object> map, String userId, String funcName){
        if(map.containsKey(funcName)){
            FunctionDescriber functionDescriber = (FunctionDescriber)map.get(funcName);
            functionDescriber.setBehaivorOrder(functionDescriber.getBehaivorOrder()+1);
            map.remove(funcName);
            map.put(funcName,functionDescriber);
        }else{
            FunctionDescriber functionDescriber = getFunctionDescriber(funcName);
            functionDescriber.setBehaivorOrder(1);
            map.put(funcName,functionDescriber);
        }

        String updatetime = redisService.get(REDIS_KEY_BEHAIVOR_USERID_UPDATETIME+userId);

        redisService.delete(REDIS_KEY_BEHAIVOR_USERID_UPDATETIME+userId);
        redisService.delete((REDIS_KEY_BEHAIVOR_USERID+userId).getBytes());

        map = sortByValue(map);

        byte[] bytes = SerializeUtils.serialize(map);

        Date nowDate = new Date();
        //redis数据每六小时更新一次到数据库
        if(!StringUtil.isNullOrEmpty(updatetime)&&(((Long.valueOf(updatetime)-nowDate.getTime())/(1000 * 60 * 60))>6)){
            updateCommonlyFormulas(userId,bytes);
        }
        redisService.set(REDIS_KEY_BEHAIVOR_USERID_UPDATETIME+userId,String.valueOf(System.currentTimeMillis()));
        redisService.set((REDIS_KEY_BEHAIVOR_USERID+userId).getBytes(),bytes);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Map loadCommonlyFormulas(final String userId){
        Map<String,Object> map = null;
        //redis中取
        map = getUserBehaviorFromRedis(userId);
        if(StringUtil.isNullOrEmpty(map)){
            //redis为空 数据库查询
            map = loadCommonlyFormulasforDb(userId);

            if(!StringUtil.isNullOrEmpty(map)&&!map.isEmpty()){
                //将数据存入redis并保存更新的时间
                redisService.set(REDIS_KEY_BEHAIVOR_USERID_UPDATETIME+userId,String.valueOf(System.currentTimeMillis()));
                redisService.set((REDIS_KEY_BEHAIVOR_USERID+userId).getBytes(), SerializeUtils.serialize(map));
            }
        }
        if(StringUtil.isNullOrEmpty(map)){
            //随机取出
            map = initUserBehavior();
            //存入数据库、redis
            final Map<String,Object> saveMap = map;
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    saveTdfUserBehaviorToRedisAndDb(saveMap,userId);
                }
            });
            t.start();
        }
        return map;
    }

}