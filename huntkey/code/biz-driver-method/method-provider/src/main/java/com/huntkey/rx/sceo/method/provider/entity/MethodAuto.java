package com.huntkey.rx.sceo.method.provider.entity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.feign.FormulaService;
import com.huntkey.rx.sceo.method.provider.util.MsgUtil;
import com.huntkey.rx.sceo.method.provider.util.RestUtil;
import com.huntkey.rx.sceo.mpf.common.MsgClient;
import com.huntkey.rx.sceo.mpf.common.MsgClientFactory;
import com.huntkey.rx.sceo.mpf.common.MsgConsumerInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;
import java.util.Set;

/**
 * Created by lulx on 2017/10/24 0024 上午 11:48
 */
public class MethodAuto implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(MethodAuto.class);

    private String formulaId;
    private String methodUrl;
    private String reqType;
    private String params;
    private String execRate;
    private Set<ObjVo> objSets;
    private FormulaService formulaService;
    private String topicMethodResult;

    public MethodAuto(Set<ObjVo> objSets, FormulaService formulaService, String topicMethodResult, String formulaId, String methodUrl, String reqType, String params, String execRate) {
        this.objSets = objSets;
        this.formulaService = formulaService;
        this.topicMethodResult = topicMethodResult;
        this.formulaId = formulaId;
        this.methodUrl = methodUrl;
        this.reqType = reqType;
        this.params = params;
        this.execRate = execRate;
    }

    @Override
    public void run() {
        logger.info("execAuto开始运行");
        try {
            //创建对象池,保存对象
            objSets.add(new ObjVo(formulaId,methodUrl,reqType,params,execRate));

            //消费kafka消息,判断kafka中的Id是否包含在list之中
            String topic = "messageData";
            Properties prop = new Properties();
            prop.put("group.id","myGroup13334");
            final MsgClient client = MsgClientFactory.createClient(prop);
            client.receiveMessage(topic, new MsgConsumerInterface() {
                @Override
                public void consume(String topic, String message) {
                    logger.info("execAuto开始消费kafka消息");
                    JSONObject msgObjects = JSON.parseObject( message);
                    JSONArray jsarry = msgObjects.getJSONArray("ids");
                    for(int i=0;i<jsarry.size();i++){
                        JSONObject msgObject =  jsarry.getJSONObject(i);
                        String msgId =(String) msgObject.get("id");
                        //若kafka中的Id包含在list之中,则调用rest接口
                        for (ObjVo obj : objSets) {
                            JSONObject jsonParam= JSON.parseObject(obj.getParams());
                            String objId = (String) jsonParam.get("id");
                            if(objId.equals(msgId)){
                                //调用解析公式rest接口
                                logger.info("execAuto开始调用接口！");
                                Result result = formulaService.analysisFormula(formulaId,msgId);
                                if(null!=result.getData()){
                                    if("true".equals(result.getData().toString())){
                                        Result resultAuto = RestUtil.doExec(methodUrl, reqType, params);
                                        MsgUtil.sendMsg(topicMethodResult, resultAuto.toString());
                                    }
                                }
                                logger.info("execAuto->自动方法执行成功！");
                            }
                            //判断执行一次还是多次 0是一次、1是反复
                            if("0".equals(obj.getExecRate())){
                                objSets.remove(obj);
                                client.closeConsumer();
                            }
                        }
                    }
                }
            });
        }catch (Exception e){
            logger.error("自动方法 : " + methodUrl + "执行出错 ：" + e.getMessage(), e);
        }
    }
}
