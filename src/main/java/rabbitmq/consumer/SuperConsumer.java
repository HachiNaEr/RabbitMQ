package rabbitmq.consumer;

import java.io.IOException;

import org.apache.commons.lang3.CharEncoding;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class SuperConsumer implements Consumer{
	Logger logger = LoggerFactory.getLogger(getClass());
	
	@SuppressWarnings("unused")
	private Channel channel;
	public SuperConsumer(Channel _channel) {
		channel = _channel;
	}

	public void handleConsumeOk(String consumerTag) {
		logger.debug(" handleConsumeOk.consumerTag : " + consumerTag);
	}

	public  void handleCancelOk(String consumerTag) {
		logger.debug(" handleCancelOk.consumerTag : " + consumerTag);
	}

	public void handleCancel(String consumerTag) throws IOException {
		logger.debug(" handleCancel.consumerTag : " + consumerTag);
	}

	public void handleDelivery(String str, Envelope env, BasicProperties prop, byte[] body) throws IOException {
		logger.debug(" handleDelivery.str : " + str);
		logger.debug(" handleDelivery.env.routingKey : " + env.getRoutingKey() +
				     " handleDelivery.env.deliveryTag : " + env.getDeliveryTag() + 
				     " handleDelivery.env.exchange : " + env.getExchange());
		logger.debug(" handleDelivery.prop : " + prop);
		logger.debug(" handleDelivery.body : " + new String(body, CharEncoding.UTF_8));
	}

	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		logger.debug(" handleShutdownSignal.consumerTag : " + consumerTag);
		logger.debug(" handleShutdownSignal.shutdownSignalException : " + sig.getMessage());
	}

	public void handleRecoverOk(String consumerTag) {
		logger.debug(" handleRecoverOk.consumerTag : " + consumerTag);
	}

}
