package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.UserInfo;
import com.huntkey.rx.modeler.provider.constant.Constant;
import com.huntkey.rx.modeler.provider.util.JwtUtil;
import com.huntkey.rx.modeler.provider.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

/**
 * Created by weijian on 2017/10/12.
 */
@RestController
@RequestMapping("/login")
public class LoginController {
//    @Autowired
//    private UserService userService;
    @Autowired
    private JwtUtil jwt;
    @Autowired
    private Md5Util md5;

    @Autowired
    private JedisCluster redisUtil;
    /**
     * 登录
     */
    @RequestMapping (method = RequestMethod.POST)
    public Result login(@RequestBody UserInfo userInfo){
        /*============用户密码Rsa加密，需先解密后Md5加密保存（未做）=============*/
        Result result = new Result();
        System.out.println("密码："+ md5.getMd5("12345"));
        try {
            //验证用户名密码
            if(!("admin".equals(userInfo.getEpeoCode())||"liuwei".equals(userInfo.getEpeoCode()))){
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("当前用户尚未注册！");
                return result;
            }else{
                /*String md5Password = md5.getMd5(userInfo.getEpeoPassword());
                if (md5Password.compareTo(userInfo.getEpeoPassword()) != 0) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("密码错误，请重新填写！");
                    return result;
                }*/
                if(!"12345".equals(userInfo.getEpeoPassword())){
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("密码错误，请重新填写！");
                    return result;
                }
                if("admin".equals(userInfo.getEpeoCode())){
                    userInfo.setStatus("1");
                }if("liuwei".equals(userInfo.getEpeoCode())){
                    userInfo.setStatus("2");
                }
            }

            //创建jwt并返回给前台
            JSONObject subject = JwtUtil.generalSubject(userInfo);
            String token = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_TTL);
            //String refreshToken = jwt.createJWT(Constant.JWT_ID, subject, Constant.JWT_REFRESH_TTL);
            JSONObject jo = new JSONObject();
            jo.put("token", token);
            jo.put("status",userInfo.getStatus());
            //jo.put("refreshToken", refreshToken);
            result.setData(jo);
            result.setRetCode(Result.RECODE_SUCCESS);
            return result;
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            return result;

        }
    }

    /**
     * 生成6位随机验证码
     * @return Result
     */
    @RequestMapping(value="/getVerificatCode",method = RequestMethod.GET)
    public Result getVerificatCode(@RequestParam(value = "phoneNumOrEmail") String phoneNumOrEmail){
        Result result = new Result();
        if (phoneNumOrEmail == null) {
            result.setErrMsg("请填写手机号码或邮箱！");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        int randNum = 0 + (int)(Math.random() * 1000000);
        result.setData(randNum); //返回ID
        result.setRetCode(Result.RECODE_SUCCESS);
        //待做将电话号码和验证码保存到redis
        if(redisUtil.get(phoneNumOrEmail)==null){
            redisUtil.del(phoneNumOrEmail);
        }
        redisUtil.set(phoneNumOrEmail,String.valueOf(randNum));
        return result;
    }

    @GetMapping("/unlogin")
    public Result unlogin(){
        Result result = new Result();
        result.setErrMsg("未登录，请重新登录！");
        result.setRetCode(Result.RECODE_UNLOGIN);
        return result;
    }
}
