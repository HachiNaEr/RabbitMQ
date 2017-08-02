package rabbitmq.test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class PDemo {
	public static void main(String[] args) {
		try {
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost("192.168.0.14");
			factory.setPort(5672);
			factory.setUsername("liuyongjian");
			factory.setPassword("123456");
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			
			Map<String, Object> arguments = new HashMap<>();
			arguments.put("x-dead-letter-exchange","DLX_exchange");
			arguments.put("x-dead-letter-routing-key", "dlx");
			channel.queueDeclare("QUEUE", true, false, false, arguments);
			
			channel.exchangeDeclare("EXCHANGE", BuiltinExchangeType.DIRECT, true);
			channel.queueBind("QUEUE", "EXCHANGE", "QUEUE");
			channel.basicPublish("EXCHANGE", "QUEUE", null, "TWO".getBytes());
			
			channel.close();
			connection.close();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
