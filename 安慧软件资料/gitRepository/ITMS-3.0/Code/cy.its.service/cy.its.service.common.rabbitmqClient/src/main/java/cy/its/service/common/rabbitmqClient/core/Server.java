package cy.its.service.common.rabbitmqClient.core;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

import cy.its.service.common.rabbitmqClient.BindRelation;
import cy.its.service.common.rabbitmqClient.Logs;
import cy.its.service.common.rabbitmqClient.MQAddress;

public class Server {
	String serverIp;
	int port;
	String vHostName;
	String user;
	String passWord;
	private List<Exchange> exchanges;
	private Connection conn;
	private ConnectionFactory factory;
	private Boolean isStop = false;
	private ShutdownListener shutdownListener;
	ExecutorService executor;

	public Server(MQAddress mqAddress, List<BindRelation> bindRelations) {
		executor = Executors.newCachedThreadPool();
		init(mqAddress, bindRelations);
		this.factory = MQUtil.createConnFactory(this, executor);
		this.shutdownListener = new ShutdownListener() {
			@Override
			public void shutdownCompleted(ShutdownSignalException cause) {
				retry();
			}
		};
	}

	public void start() throws Exception {
		executor.submit(() -> {
			try {
				connect();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public void synStart() throws Exception {
		try {
			connect();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void connect() throws InterruptedException {
		synchronized (this) {
			while (!isStop) {
				try {
					Logs.info("MQ连接开始");
					this.conn = factory.newConnection();
					this.conn.addShutdownListener(shutdownListener);
					
					if(exchanges != null) {
						for (Exchange exchange : exchanges) {
							exchange.start(conn);
						}
					}
					
					Logs.info("MQ连接成功");
					break;
				} catch (Exception e) {
					closeConn();
					Logs.info("MQ连接失败5秒后重试, ", e);
					try {
						Thread.sleep(5000);
					} catch (Exception e2) {
					}
				}
			}

		}
	}

	private void retry() {
		executor.submit(() -> {
			synchronized (this) {
				try {
					Logs.info("检测到MQ连接断开, 准备重试！");
					if(!isStop) {
						if(!this.conn.isOpen()){
							closeConn();
							connect();
							Logs.info("MQ连接 重试连接结束");
						}else{
							Logs.info("MQ连接已经恢复, 重试连接终止");
						}
					}else{
						Logs.info("程序停止, 重试连接终止");
					}
				} catch (Exception e) {
					Logs.info("MQ连接断开重试出错", e);
				}
			}
		});
		
		
	}

	private void closeConn() {
		try {
			if (this.conn != null) {				
				if(exchanges != null) {
					exchanges.forEach(c->c.disablePublish());
				}						
				this.conn.removeShutdownListener(shutdownListener);
				this.conn.abort();
			}
		} catch (Exception e) {
			Logs.info("MQ连接关闭出错", e);
		}
	}

	public void stop() {
		isStop = true;
		synchronized (this) {
			try {
				Logs.info("停止MQ连接");
				if (exchanges != null) {
					exchanges.forEach(c->c.close());
				}
				
				if (conn != null) {
					conn.close();
				}
				

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		try {			
			executor.shutdown();
			executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	boolean TRUE = true;
	boolean FALSE = false;
	public boolean publish(String routingKey, String message) {
		boolean rslt = FALSE;
		if (exchanges != null) {
			for (Exchange exchange : exchanges) {
				if(exchange.publish(routingKey, message)) {
					rslt = TRUE;
				}
			}
		}
		return rslt;
	}

	private void init(MQAddress mqAddress, List<BindRelation> bindRelations) {
		this.serverIp = mqAddress.mqIp;
		this.port = mqAddress.port;
		this.vHostName = mqAddress.vHost;
		this.user = mqAddress.user;
		this.passWord = mqAddress.passWord;
		if (bindRelations != null) {
			this.exchanges = bindRelations.stream().filter(br -> br != null)
					.map(br -> new Exchange(br.bindToExchange, br.queueHandlers)).collect(Collectors.toList());
		}
	}
}
