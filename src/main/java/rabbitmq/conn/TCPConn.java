package rabbitmq.conn;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmq.util.ReadResourceFile;

public class TCPConn {
	Logger logger = LoggerFactory.getLogger(getClass());
	public static ConnectionFactory factory = new ConnectionFactory();
	
	public Connection conn(){
		Properties prop = ReadResourceFile.readProp("conn.properties");
		factory.setHost(prop.getProperty("HOST"));
		factory.setPort(Integer.valueOf(prop.getProperty("PORT")));
		factory.setUsername(prop.getProperty("USERNAME"));
		factory.setPassword(prop.getProperty("PASSWORD"));
		factory.setAutomaticRecoveryEnabled(true);
		
		Connection conn = null;
		try {
			conn = factory.newConnection();
		} catch (IOException | TimeoutException e) {
			logger.debug("创建TCP连接异常：" + ExceptionUtils.getStackTrace(e));
		}
		/*RabbitMQUtil.conn.addShutdownListener((cause) -> {
			if (RabbitMQUtil.conn == null) {
				try {
					RabbitMQUtil.conn = factory.newConnection();
				} catch (IOException | TimeoutException e) {
					e.printStackTrace();
				}
			}
		});*/
		return conn;
	}
}

