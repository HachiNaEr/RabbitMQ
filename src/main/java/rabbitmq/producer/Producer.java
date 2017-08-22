package rabbitmq.producer;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.rabbitmq.client.Channel;

import rabbitmq.conn.ChannelGroup;

public class Producer {
	Logger logger = LoggerFactory.getLogger(getClass());
	private Channel channel;
	private String exchange;
	private String route;
	
	public Producer(){
		channel = ChannelGroup.getChannel();
	}
	
	public Producer init(String queue, String exchange, String route){
		try {
			this.exchange = exchange;
			this.route = route;
			channel.queueDeclare(queue, true, false, false, null);
			
			channel.queueBind(queue, exchange, route);
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		return this;
	}
	
	public void sendMsg(Object o){
		try {
 			channel.basicPublish(exchange, route, null, o.toString().getBytes());
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
	}
}