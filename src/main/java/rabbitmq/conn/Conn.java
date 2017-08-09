package rabbitmq.conn;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.TimeoutException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import rabbitmq.util.ReadResourceFile;

public class Conn {
	Logger logger = LoggerFactory.getLogger(getClass());
	private static Connection conn;
	
	public static Connection getConn(){
		if (Optional.ofNullable(conn).isPresent()) {
			return conn;
		} 
		synchronized (Conn.class) {
			new Conn();
		}
		return conn;
	}
	
	private Conn(){ conn(); }
	
	private void conn(){
		ConnectionFactory factory = new ConnectionFactory();
		Properties prop = ReadResourceFile.readProp("conn.properties");
		factory.setHost(prop.getProperty("HOST"));
		factory.setPort(Integer.valueOf(prop.getProperty("PORT")));
		factory.setUsername(prop.getProperty("USERNAME"));
		factory.setPassword(prop.getProperty("PASSWORD"));
		factory.setAutomaticRecoveryEnabled(true);
		
		try {
			 conn = factory.newConnection();
		} catch (IOException | TimeoutException e) {
			logger.debug("create TCP connection exception ：" + ExceptionUtils.getStackTrace(e));
		}
	}
}