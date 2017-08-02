package rabbitmq.mq;

import java.io.IOException;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class RabbitMQUtil {
	public static Connection conn ;
	public static Channel channel ;
	
	public static final String DEFAULT_EXCHANGE = "DEFAULT_EXCHANGE";
	public static final String DEFAULT_ROUTING = "DEFAULT_ROUTING";
	
	public static void bindQueue(String queue) {
		try {
			channel.queueDeclare(queue, true, false, false, null);
			channel.queueBind(queue, DEFAULT_EXCHANGE, DEFAULT_ROUTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean publishMSG(String msg){
		try {
			// 防止
			channel.exchangeDeclare(DEFAULT_EXCHANGE, BuiltinExchangeType.DIRECT, true);
			channel.basicPublish(DEFAULT_EXCHANGE, DEFAULT_ROUTING, null, msg.getBytes());
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false; 
		}
	}
}
