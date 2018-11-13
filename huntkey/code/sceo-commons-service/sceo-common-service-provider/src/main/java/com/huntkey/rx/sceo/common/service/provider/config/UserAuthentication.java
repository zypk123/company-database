package com.huntkey.rx.sceo.common.service.provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huntkey.rx.sceo.common.service.common.model.UserPass;

/***
 * 短信发送用户验证配置
 * @author fangkun
 *
 */
@Configuration
public class UserAuthentication {
	@Value("${spring.userauthentication.usernane}")
	private String username;//用户名
	
	@Value("${spring.userauthentication.password}")
	private String password;//密码
	
	@Value("${spring.userauthentication.visa}")
	private String visa;//短信签名
	
	@Value("${spring.userauthentication.msgfmt}")
	private String msgfmt;//短信的编码格式
	
	@Value("${spring.userauthentication.ext}")
	private String ext;//短信扩展
	@Bean
	public UserPass userPass(){
		UserPass userpassword=new  UserPass();
		userpassword.setUsername(username);
		userpassword.setPassword(password);    
		userpassword.setVisa(visa);
		userpassword.setExt(ext);
		userpassword.setMsgfmt(msgfmt);
		return userpassword;
	}
}
