package rabbitmq.conn;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jfinal.plugin.IPlugin;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmq.mq.RabbitMQUtil;

public class TCPConn {
	Logger logger = LoggerFactory.getLogger(getClass());
	public static ConnectionFactory factory = new ConnectionFactory();
	
	public void conn() throws IOException, TimeoutException{
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
}

