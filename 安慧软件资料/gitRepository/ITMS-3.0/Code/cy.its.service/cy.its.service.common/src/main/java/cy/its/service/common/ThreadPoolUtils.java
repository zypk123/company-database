package cy.its.service.common;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolUtils {

//	private final static ExecutorService TreadPool;
	private final static ThreadPoolExecutor ThreadPool;

	static {
//		TreadPool = Executors.newCachedThreadPool();
		ThreadPool = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
	}

	public static void execute(Runnable command) {
		ThreadPool.execute(command);
	}

	public static <T> Future<T> submit(Callable<T> task) {
		return ThreadPool.submit(task);
	}

	public static <T> Future<T> submit(Runnable task, T result) {
		return ThreadPool.submit(task, result);
	}

	public static Future<?> submit(Runnable task) {
		return ThreadPool.submit(task);
	}
	
	public static String status(){
		return ThreadPool.toString();
	}
}
