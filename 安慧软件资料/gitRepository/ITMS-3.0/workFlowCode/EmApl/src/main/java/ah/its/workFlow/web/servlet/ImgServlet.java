package ah.its.workFlow.web.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.impl.ProcessEngineImpl;
import org.activiti.engine.impl.interceptor.Command;
import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.web.context.WebApplicationContext;

import ah.its.wrokflow.action.impl.ProcessInstanceDiagramCmd;

/**
* @Title: ImgServlet.java 
* @Package ah.its.workFlow.web.servlet 
* @Description:处理图片请求
* @author lil@cychina.cn
* @date 2016年4月20日 下午9:24:41
* @version V1.0   
 */
@WebServlet("/imgServlet")
public class ImgServlet extends HttpServlet{
	/**
	 * @Description:属性
	 */
	private static final long serialVersionUID = 3841675807862073145L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		
		ServletContext servletContext=request.getSession().getServletContext();
		WebApplicationContext webApplicationContext = (WebApplicationContext)servletContext.getAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE);
		RepositoryService repositoryService=(RepositoryService)webApplicationContext.getBean("repositoryService");
	     
		String imgType = request.getParameter("imgType");
		String processDefinitionId = request.getParameter("processDefinitionId");
		byte[]  bytes  = null;
		if("1".equals(imgType)){
			ProcessDefinition  processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionId(processDefinitionId).singleResult();  
	        String diagramResourceName = processDefinition.getDiagramResourceName();  
	        InputStream imageStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), diagramResourceName);
	        bytes = readInputStream(imageStream);
		}else{
			RuntimeService runtimeService=(RuntimeService)webApplicationContext.getBean("runtimeService");
		    HistoryService historyService=(HistoryService)webApplicationContext.getBean("historyService");
		    ManagementService managementService=(ManagementService)webApplicationContext.getBean("managementService");
		    ProcessEngineImpl processEngine=(ProcessEngineImpl)webApplicationContext.getBean("processEngine");
		    Command<InputStream> cmd = new ProcessInstanceDiagramCmd(processDefinitionId, 
		    		runtimeService, repositoryService, processEngine, historyService);
			    InputStream imageStream =  managementService.executeCommand(cmd);
			bytes = readInputStream(imageStream);
		}
		response.setHeader("Content-Type","image/jped");
		OutputStream os = response.getOutputStream();  
		os.write(bytes);//注意这里向jsp输出的流，在script中的截获方法
		os.flush();  
		os.close();
	}
	
	private static byte[] readInputStream(InputStream inputStream) throws IOException {
		ByteArrayOutputStream outputStream = null;
		try {
			
			byte[] buffer = new byte[1024];
			int len = -1;
			outputStream = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, len);
			}
			return outputStream.toByteArray();
		} finally {
			if (outputStream != null) {
				outputStream.close();
			}
		}
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
}
