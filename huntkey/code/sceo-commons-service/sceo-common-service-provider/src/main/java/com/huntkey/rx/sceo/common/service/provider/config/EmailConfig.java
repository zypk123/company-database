package com.huntkey.rx.sceo.common.service.provider.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.huntkey.rx.sceo.common.service.common.model.EmailSetting;
import com.huntkey.rx.sceo.common.service.common.model.UserPass;

/***
 * 邮件配置
 * @author fangkun
 *
 */
@Configuration
public class EmailConfig {
	@Value("${email.host}")
	private String host;//邮箱服务器
	
	@Value("${email.username}")
	private String username;//发送邮箱用户名
	
	@Value("${email.password}")
	private String password;//发送邮箱密码
	
	@Value("${email.contenttype}")
	private String contenttype;//发送内容编码类型
	
	@Value("${email.auth}")
	private String auth;//发送邮件是否需要账户认证
	@Bean
	public EmailSetting emailSetting(){
		EmailSetting emaliset=new  EmailSetting();
		emaliset.setHost(host);
		emaliset.setUsername(username);
		emaliset.setPassword(password);
		emaliset.setContenttype(contenttype);
		emaliset.setAuth(auth);
		return emaliset;
	}
}
