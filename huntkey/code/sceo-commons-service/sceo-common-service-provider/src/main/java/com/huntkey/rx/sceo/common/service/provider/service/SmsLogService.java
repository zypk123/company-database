package com.huntkey.rx.sceo.common.service.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.SmsLog;
import com.huntkey.rx.sceo.common.service.common.model.UserPass;
import com.huntkey.rx.sceo.common.service.common.model.to.SimpleSmsTO;
import com.huntkey.rx.sceo.common.service.provider.dao.SmsLogMapper;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaomj on 2017/4/27.
 */
@Service
@Transactional
public class SmsLogService {
    @Autowired
    private UserPass userPass;
    @Autowired
    private SmsLogMapper smsLogMapper;
    
    private void insertSmsInfo(SmsLog smsLog) {
		// TODO Auto-generated method stub
    	smsLogMapper.insert(smsLog);
	}

	//post方式发送短信
    public Result sendMessageByPost(SimpleSmsTO to) {
    	String HttpURL="http://www.jc-chn.cn/";
    	String content = to.getSmlgContent();
    	if(content!=null && content.length()>0)
    	{
    		content+=userPass.getVisa();
    	}
    	String password=userPass.getPassword();
    	String username=userPass.getUsername();
    	String strMd5Password=Md5Encryption(username, password);
    	String smlgId=UUID.randomUUID().toString();
    	String result = "";
    	Result retRes=new Result();
		StringBuffer sb = new StringBuffer();
		BufferedReader in=null;
		URLConnection conn=null;
		OutputStreamWriter out = null;
		SmsLog smsLog=new SmsLog(to);
		try {
			sb.append("username=").append(username)
			.append("&password=").append(strMd5Password)
			.append("&mobile=").append(to.getSmlgPhone())
			.append("&content=").append(URLEncoder.encode(content, "UTF-8"))
			.append("&dstime=").append(to.getSmlgSendTime())//短信发送时间 为空时立即发送
			.append("&ext=").append(userPass.getExt()) //用户自定义扩展（选填）需要和后台人员确认权限
			.append("&msgid=").append(smlgId)		//发送消息的任务id 为空时平台端自动生成		
			.append("&msgfmt=").append(userPass.getMsgfmt());//提交消息的编码格式 为空时默认utf-8
			URL realUrl = new URL(HttpURL + "smsSend.do");
			conn = realUrl.openConnection();
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setDoOutput(true);
			conn.setDoInput(true);
			System.setProperty("http.proxyHost","127.0.0.1");
			System.setProperty("http.proxyPort","3128");
			out = new OutputStreamWriter(conn.getOutputStream(), "UTF8");
			out.write(sb.toString());   
			out.flush();
			in = new BufferedReader(new InputStreamReader(
					conn.getInputStream(), "UTF8"));
			String line = "";
			while ((line = in.readLine()) != null) {
				result += line;
			}
			retRes=getResStatus(result);
		} catch (Exception e) {
			result="0";//由于发送短信返回《=0也会进入异常
			retRes.setRetCode(0);
			retRes.setErrMsg("发送失败！");
			e.printStackTrace();
		}finally {
			smsLog.setSmlgid(smlgId);
			smsLog.setSmlgresstatus(result);
			smsLog.setSmlgresstatustext(retRes.getErrMsg());
			insertSmsInfo(smsLog);
			if(out!=null){
				try {
					out.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return retRes;
    }
    /**
     * MD5 加密算法
     * @return
     */
    private String Md5Encryption(String username,String password){
    	String strMd5="";
    	MessageDigest md=null;
		try {
			md = MessageDigest.getInstance("MD5");
			// 计算md5函数
	    	md.update(password.getBytes());
	    	// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
	    	// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
	    	password= new BigInteger(1, md.digest()).toString(16);
	    	md.update((username+password).getBytes());
	    	strMd5=new BigInteger(1, md.digest()).toString(16);
		} catch (NoSuchAlgorithmException e) {			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return strMd5;
    }
    /****
     * 获得短信接口返回状态
     * @return
     */
    private Result getResStatus(String result){
    	Result retRes=new Result();
    	Long status=Long.parseLong(result);
    	if(status>0){
    		retRes.setRetCode(1);
    	}else{
    		retRes.setRetCode(0);
    		if(status==0){
    			retRes.setErrMsg("短信发送失败");
    		}else if(status==-1){
    			retRes.setErrMsg("用户名或者密码不正确");
    		}else if(status==-2){
    			retRes.setErrMsg("必填选项为空");
    		}else if(status==-3){
    			retRes.setErrMsg("短信内容0个字节");
    		}else if(status==-4){
    			retRes.setErrMsg("0个有效号码");
    		}else if(status==-5){
    			retRes.setErrMsg("余额不够");
    		}else if(status==-10){
    			retRes.setErrMsg("用户被禁用");
    		}else if(status==-11){
    			retRes.setErrMsg("短信内容超过500字");
    		}else if(status==-12){
    			retRes.setErrMsg("无扩展权限（ext字段需填空）");
    		}else if(status==-13){
    			retRes.setErrMsg("IP校验错误");
    		}else if(status==-14){
    			retRes.setErrMsg("内容解析异常");
    		}else if(status==-990){
    			retRes.setErrMsg("未知错误");
    		}

    	}
    	return retRes;
    }
}
