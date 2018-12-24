package cy.its.platform.common.exception;

import cy.its.platform.common.utils.Log4jFactoryProxy;

/**
* @Title: ItmsAppException.java 
* @Package cy.its.platfrom.common.exception 
* @Description: 整个系统异常基类，继承于
*rumTimeException 
* @author lil@cychina.cn
* @date 2015年9月28日 下午1:48:32 
* @version V1.0   
 */
public class ItmsAppException extends RuntimeException { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 5309113777707711417L;
	
	Log4jFactoryProxy log4jFactoryProxy=Log4jFactoryProxy.getSingleton(ItmsAppException.class);
	
    /**
     * 无参的构造函数
     * 不需要输出任何信息，基本不用
     */
    public ItmsAppException() {
        super();  
    }  
 
    /**
     * @param msg
     * 根据传入信息，传递给相关人员，同时控制台打印相关信息
     */
    public ItmsAppException(String msg) {
        super(msg);  
        printAppStackTrace(msg);
    }  
	
    /** 
    * @Title: printAppStackTrace 
    * @Description: 打印自定义消息，并且打印错误源信息
    * @param @param msg    设定文件 
    * @return void 返回类型 
    * @throws 
    */
    private void printAppStackTrace(String msg) {
    	log4jFactoryProxy.error(msg);
    	StackTraceElement[] stacks =this.getStackTrace();
    	for(StackTraceElement st: stacks){
    		log4jFactoryProxy.error("程序代码异常信息:"+st.getMethodName()+" : "+st);
    	}
	}
}
