package com.huntkey.rx.sceo.common.service.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.EmailLogWithBLOBs;
import com.huntkey.rx.sceo.common.service.common.model.EmailSetting;
import com.huntkey.rx.sceo.common.service.common.model.to.SimpleEmailTO;
import com.huntkey.rx.sceo.common.service.provider.dao.EmailLogMapper;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import javax.mail.Authenticator;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by zhaomj on 2017/4/27.
 */
@Service
@Transactional
public class EmailLogService {
	@Autowired
	private EmailLogMapper emailLogMapper;
	@Autowired
	private EmailSetting emailSetting;
	
	private String retMessage="";
	private String errorResponse="";
	private String sendStatus="";
	Result result=new Result();
	@SuppressWarnings("static-access")
	public Result sendSimpleEmail(SimpleEmailTO to) {
		String username=emailSetting.getUsername();//发送邮件邮箱
		String password=emailSetting.getPassword();//发送邮件邮箱密码
		String recipients=to.getMailRecipient();//邮件接收者[多个接受者分号隔开]
		String copyRecipients=to.getMailCopyRecipient();//邮件抄送者[多个接受者分号隔开]
		EmailLogWithBLOBs email=new EmailLogWithBLOBs(to);
		Date sendTime=null;
		String[] tos=recipients.split(";");//收件人数组
		String[] ccs=copyRecipients.split(";");//抄送人数组
		Properties props = new Properties();
		props.put("mail.smtp.host", emailSetting.getHost());//邮箱服务器
		props.put("mail.smtp.auth", emailSetting.getAuth()); // 使用验证
		Session mailSession = Session.getInstance(props, 
				new MyAuthenticator(username,password));
		// 第二步：编写消息
		try {
			
		InternetAddress fromAddress = new InternetAddress(username);
		InternetAddress[] toAddress=new InternetAddress[tos.length];//收件人
		for(int i=0;i<tos.length;i++){
			toAddress[i]=new InternetAddress(tos[i]);
		}
		
		InternetAddress[] ccAddress=new InternetAddress[ccs.length];//抄送人
		for(int i=0;i<ccs.length;i++){
			ccAddress[i]=new InternetAddress(ccs[i]);
		}
		
		MimeMessage message = new MimeMessage(mailSession);
		message.setFrom(fromAddress); 
		message.setRecipients(RecipientType.TO, toAddress);
		message.setRecipients(RecipientType.CC, ccAddress);
		sendTime=Calendar.getInstance().getTime();
		message.setSentDate(sendTime);
		message.setSubject(to.getMailSubject());
		message.setContent(to.getMailContent(),emailSetting.getContenttype());
		// 第三步：发送消息
		Transport transport = mailSession.getTransport("smtp");
		transport.connect(emailSetting.getHost(), username, password);
		transport.send(message);
		retMessage="邮件发送成功！";
		sendStatus="1";
		result.setRetCode(1);
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.setRetCode(0);
			retMessage="邮件发送失败！";
			sendStatus="0";
			errorResponse=e.getMessage();
		}finally {
			email.setMailsendtime(sendTime);
			email.setMailid(UUID.randomUUID().toString());
			email.setMailsendstauts(sendStatus);
			email.setMailerrorresponse(errorResponse);
			insertEmailLog(email);
		}
		return result;
	}

	class MyAuthenticator extends Authenticator {
		String userName = "";
		String password = "";

		public MyAuthenticator() {

		}

		public MyAuthenticator(String userName, String password) {
			this.userName = userName;
			this.password = password;
		}

		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}
	
	/**
	 *插入邮件发送日志 @Create by fangkun 2017/6/23 
	 */
	private void insertEmailLog(EmailLogWithBLOBs email){
		emailLogMapper.insert(email);
	}

}
