package com.huntkey.rx.framework;


import org.apache.logging.log4j.Logger;

import com.huntkey.rx.framework.context.ApplicationContext;
import com.huntkey.rx.log.LogFactory;
import com.huntkey.rx.util.PropertyUtil;

public abstract class AbstractApplicationTask implements ApplicationTask {

    /**
     * 日志类
     */
    protected Logger              logger;

    /**
     * VAR_SKIP_NEXT: 全局变量：是否跳过以下一个节点.
     * 
     * @since JDK 1.6
     */
    protected final static String VAR_SKIP_NEXT = "isSkipNext";

    /**
     * nextTask: 下一个应用程序任务.
     * 
     * @since JDK 1.6
     */
    private ApplicationTask       nextTask      = null;

    /**
     * hasNext: 是否有下一个应用程序任务.
     * 
     * @since JDK 1.6
     */
    private boolean               hasNext       = false;

    /**
     * isSkipNext: 是否跳过下一个任务.
     * 
     * @since JDK 1.6
     */
    private boolean               isSkipNext    = false;

    /**
     * Creates a new instance of AbstractApplicationTask.
     */
    public AbstractApplicationTask(){
        super();
    }

    /**
     * initLogger:初始化日志. <br/>
     *
     * @author qiyongkang
     * @param applicationTaskId
     * @param applicationId
     * @since JDK 1.6
     */
    public void initLogger(String applicationTaskId, String applicationId) {
        this.logger = LogFactory.getApplicationTaskLogger(applicationTaskId, applicationId);
    }

    public boolean perform(ApplicationContext context) throws Exception {
        // 如果在执行应用程序任务逻辑之前的操作成功，那么进入执行应用程序任务
        if (doBefore(context)) {
            boolean isReturnAll = doInternal(context);
            doAfter(context);
            return isReturnAll;
        } else {
            return false;
        }
    }

    public boolean hasNext() {
        return this.hasNext;
    }

    public void registerNextTask(ApplicationTask nextTask) {
        this.nextTask = nextTask;
        this.hasNext = !(null == nextTask);
    }

    public ApplicationTask next() {
        return this.nextTask;
    }

    protected boolean doBefore(ApplicationContext context) throws Exception {
        PropertyUtil.setLogger(this.logger);
        return true;
    }

    protected abstract boolean doInternal(ApplicationContext context) throws Exception;

    protected void doAfter(ApplicationContext context) throws Exception {
        // 设置是否跳过下一个变量
        if (isSkipNext) {
            String skipMessage = "跳过此应用程序任务的下一个任务！";
            logger.info(skipMessage);
        }
    }

    public void skipNext() {
        this.isSkipNext = true;
    }
}