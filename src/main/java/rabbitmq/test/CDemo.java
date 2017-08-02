package rabbitmq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ShutdownListener;

public class CDemo{
	private static Connection connection;
	private static Channel channel;
	
	public static Connection getConnection() {
		synchronized (CDemo.class) {
			return connection;
		}
	}

	public static Channel getChannel() {
		synchronized (CDemo.class) {
			return channel;
		}
	}

	public static void conn() throws IOException, TimeoutException{
		ConnectionFactory factory = new ConnectionFactory();
		factory.setHost("192.168.0.14");
		factory.setPort(5672);
		factory.setUsername("liuyongjian");
		factory.setPassword("123456");
		connection = factory.newConnection();
		channel = connection.createChannel(10);
	}
	
	public static void main(String[] args) {
		try {
			conn();
			ShutdownListener listener = new ShutdownListenerImp();
			channel.addShutdownListener(listener);
			
			init();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void init() throws IOException{
		CONDemo consumer = new CONDemo(channel);
		
		Map<String, Object> arguments = new HashMap<>();
		arguments.put("x-dead-letter-exchange","DLX_exchange");
		arguments.put("x-dead-letter-routing-key", "dlx");
		
		// 创建业务队列, 业务交换机
		channel.exchangeDeclare("EXCHANGE", BuiltinExchangeType.DIRECT, true);
		channel.queueDeclare("QUEUE", true, false, false, arguments);
		channel.queueBind("QUEUE", "EXCHANGE", "QUEUE");
		channel.basicConsume("QUEUE", false, consumer);
		
		// 创建死信队列, 死信交换机
		channel.exchangeDeclare("DLX_exchange", BuiltinExchangeType.DIRECT, true);
		channel.queueDeclare("DLX_queue", true, false, false, null);
		channel.queueBind("DLX_queue", "DLX_exchange", "dlx");
	}
}
