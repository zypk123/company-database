package cy.its.web.aop;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cy.its.platform.common.utils.NetworkUtil;
import cy.its.sysLog.domain.SyOperationLog;
import cy.its.sysLog.service.SyOperationLogI;
import cy.its.sysLog.task.LogTask;
import cy.its.sysLog.task.LogTaskThroadManager;

@Component
public class SystemLog {
	
    /** 
    * @Title: doBefore 
    * @Description: 方法前之记录时间
    * @param @param point    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void doBefore(JoinPoint point) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	request.setAttribute("beForeTime", new Date().getTime());
    }
    
    /** 
    * @Title: doAfter 
    * @Description: 方法后操作时间
    * @param @param point    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void doAfter(JoinPoint point) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	Object   errorStatus = request.getAttribute("error");
    	if(errorStatus==null){
    		//新建LOG类
        	SyOperationLog  log  = setSysLog(request,"1",null);
        	ApplicationContext springBeanFactory = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
        	//获取spring bean 
        	SyOperationLogI imp = (SyOperationLogI) springBeanFactory.getBean("syOperationLogImpl");
        	LogTask   task  = new LogTask(imp,log);
        	//加入任务列表
        	LogTaskThroadManager.addTaskPool(task);
    	}
    }
    
    private SyOperationLog  setSysLog(HttpServletRequest request,String status,String message) {
    	//限制长度
    	if(message != null &&  message.length() > 1001){
    		message = message.substring(0, 100);
    	}
    	long beforeTime = (long) request.getAttribute("beForeTime");
    	String orgCode=request.getParameter("currentOrgPrivilegeCode")==null?"":request.getParameter("currentOrgPrivilegeCode");
    	Date  today = new Date();
    	double diff = 0.00;
		diff = 1.00*(today.getTime() - beforeTime)/1000;
    	SyOperationLog log  =  new SyOperationLog();
    	log.setComputerIp(getIp(request));
    	log.setConsumeSeconds(diff);
    	log.setOrgPrivilegeCode(orgCode);
    	log.setOpeLogId(UUID.randomUUID().toString().replace("-", ""));
    	log.setOperateRecordCounts(null);
    	log.setOperateResult(status);
    	log.setErrorDesc(message);
    	log.setOperateTime(new Date(beforeTime));
    	log.setOpeUserId(request.getParameter("currentUserId")==null?"":request.getParameter("currentUserId"));
    	log.setRebackable(null);
    	log.setReturnResultTime(today);
    	log.setServiceUrl(getServiceUrl(request));
    	log.setSysFunctionCode(request.getParameter("func_code")==null?"test":request.getParameter("func_code"));
		return log;
	}

	private String getServiceUrl(HttpServletRequest request) {
		String  url   = request.getRequestURI();
		if(url.indexOf("service/")>-1){
			url = url.split("service/")[1];
			if(url.indexOf("?")>-1){
				String[]  tmp = url.split("?");
				url = tmp[0];
			}
			return url;
		}
		return "";
	}

	/** 
	* @Title: getIp 
	* @Description: 获取IP
	* @param @param request
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getIp(HttpServletRequest request) {
		String ip = "";
		try {
			ip=NetworkUtil.getIpAddress(request);
		} catch (IOException e) {
			ip = "";
		}
		return ip;
	}

	/** 
    * @Title: doError 
    * @Description: 错误抛出时间 
    * @param @param point    设定文件 
    * @return void    返回类型 
    * @throws 
    */
    public void doError(JoinPoint point,Exception ex) {
    	HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
    	request.setAttribute("error", "1");
    	SyOperationLog  log  = setSysLog(request,"2",ex.getMessage());
    	ApplicationContext springBeanFactory = WebApplicationContextUtils.getWebApplicationContext(request.getServletContext());
    	SyOperationLogI imp = (SyOperationLogI) springBeanFactory.getBean("syOperationLogImpl");
    	LogTask   task  = new LogTask(imp,log);
    	//加入任务列表
    	LogTaskThroadManager.addTaskPool(task);
    }
}
