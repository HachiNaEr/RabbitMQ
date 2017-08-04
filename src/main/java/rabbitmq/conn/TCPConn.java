package rabbitmq.conn;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.ConnectionFactory;

import rabbitmq.mq.RabbitMQUtil;
import rabbitmq.util.PropUtil;

public class TCPConn {
	Logger logger = LoggerFactory.getLogger(getClass());
	public static ConnectionFactory factory = new ConnectionFactory();
	
	public void conn() throws IOException, TimeoutException{
		Properties prop = PropUtil.readProp("conn.properties");
		factory.setHost(prop.getProperty("HOST"));
		factory.setPort(Integer.valueOf(prop.getProperty("PORT")));
		factory.setUsername(prop.getProperty("USERNAME"));
		factory.setPassword(prop.getProperty("PASSWORD"));
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

