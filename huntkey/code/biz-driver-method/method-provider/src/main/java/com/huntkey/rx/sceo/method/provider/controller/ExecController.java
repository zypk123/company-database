package com.huntkey.rx.sceo.method.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.entity.ParamsVo;
import com.huntkey.rx.sceo.method.provider.service.ExecService;
import com.huntkey.rx.sceo.mpf.common.MsgClient;
import com.huntkey.rx.sceo.mpf.common.MsgClientFactory;
import com.huntkey.rx.sceo.mpf.exception.MsgException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

/**
 * Created by lulx on 2017/10/19 0019 上午 9:39
 */
@RestController
@RequestMapping("/methodExec")
public class ExecController {

    @Autowired
    private ExecService execService;

    private static Logger log = LoggerFactory.getLogger(ExecController.class);

    /**
     *
     * @param params
     * @return
     */
    @RequestMapping(value = "/exec", method = RequestMethod.POST)
    public Result execObject(@RequestBody ParamsVo params){
        Result result = null;
        try {
            result = execService.execObject(params);
        }catch (Exception e){
            log.error("方法执行出错", e);
            throw new RuntimeException("方法执行接口出错", e);
        }
        return result;
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/sendMessage", method = RequestMethod.GET)
    public  Result execTimeSchedule() throws MsgException {
        Result result = new Result();
        Properties prop = new Properties();
        MsgClient client = MsgClientFactory.createClient(prop);//建立连接
        // client.sendMessage("myTopic",convertScheduleJson("11:00:00","12:00:00").toJSONString());//发送消息
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }
}
