package rabbitmq.mq;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.IPlugin;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;

public class MQPlugin implements IPlugin{
	Logger logger = LoggerFactory.getLogger(getClass());
	public static ConnectionFactory factory = new ConnectionFactory();
	
	private void conn() throws IOException, TimeoutException{
		factory.setHost("localhost");
		factory.setPort(5672);
		factory.setUsername("liuyongjian");
		factory.setPassword("123456");
		factory.setAutomaticRecoveryEnabled(true);

		RabbitMQUtil.conn = factory.newConnection();
		RabbitMQUtil.conn.addShutdownListener((cause) -> {
			System.err.println("shutdown signal received " + cause);
			boolean isHard = cause.isHardError();
			System.err.println(isHard); 
			if (RabbitMQUtil.conn == null) {
				try {
					RabbitMQUtil.conn = factory.newConnection();
				} catch (IOException | TimeoutException e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public boolean start() {
		try {
			conn();
			RabbitMQUtil.channel = RabbitMQUtil.conn.createChannel();
			RabbitMQUtil.channel.addShutdownListener((cause) -> {
				try {
					RabbitMQUtil.channel = RabbitMQUtil.conn.createChannel();
				} catch (IOException e) {
					e.printStackTrace();
			}
		});
		RabbitMQUtil.channel.exchangeDeclare(RabbitMQUtil.DEFAULT_EXCHANGE, BuiltinExchangeType.DIRECT, true);
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean stop() {
		try {
			RabbitMQUtil.channel.close();
			RabbitMQUtil.conn.close();
		} catch (IOException | TimeoutException exception) {
			exception.printStackTrace();
		}
		return true;
	}
}

