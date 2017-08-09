package rabbitmq.producer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import rabbitmq.conn.ChannelGroup;

public class Producer {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public void sendMSG(){
		try {
			Channel channel = ChannelGroup.getChannel();
			
			Map<String, Object> arguments = new HashMap<>();
			arguments.put("x-dead-letter-exchange","DLX_exchange");
			arguments.put("x-dead-letter-routing-key", "dlx");
			channel.queueDeclare("QUEUE", true, false, false, arguments);
			
			channel.exchangeDeclare("EXCHANGE", BuiltinExchangeType.DIRECT, true);
			channel.queueBind("QUEUE", "EXCHANGE", "QUEUE");
			channel.basicPublish("EXCHANGE", "QUEUE", null, "TWO".getBytes());
			
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
}
