package cy.its.service.common.rabbitmqClient.core;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.common.rabbitmqClient.Logs;

class MQConsumer implements Consumer {

	private Channel channel;
	@SuppressWarnings("unused")
	private volatile String _consumerTag;

	public String PLAIN_TEXT = "text/plain";
	public String _defaultCharSet = "utf-8";
	private String consumerTag;
	IReceiver receiver;

	public MQConsumer(Channel channel, IReceiver receiver, String consumerTag) {
		this.channel = channel;
		this.receiver = receiver;
		this.consumerTag = consumerTag;
	}

	@Override
	public void handleConsumeOk(String consumerTag) {
		this._consumerTag = consumerTag;
	}

	@Override
	public void handleCancelOk(String consumerTag) {
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
	}

	@Override
	public void handleDelivery(String arg0, Envelope envelope, BasicProperties properties, byte[] body)
			throws IOException {
		try {
			synchronized (this) {
				if (channel == null) {
					return;
				}
				
				if (PLAIN_TEXT.equals(properties.getContentType())) {
					if (receiver != null) {
						boolean result = false;
						try {
							result = receiver.receive(envelope.getRoutingKey(), MQUtil.decode(body));
						} catch (Exception e) {
							Logs.info("", e);
						}

						if (result) {
							channel.basicAck(envelope.getDeliveryTag(), false);
							return;
						}
					}
				}

				channel.basicReject(envelope.getDeliveryTag(), true);
			}
		} catch (Exception e) {
			Logs.info("", e);
		}
	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
	}

	public void stopConsumer() {
		synchronized (this) {
			try {
				channel.basicCancel(this.consumerTag);
			} catch (IOException e) {
				e.printStackTrace();
			}
			channel = null;
		}
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
	}

}
