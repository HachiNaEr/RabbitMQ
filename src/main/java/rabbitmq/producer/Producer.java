package rabbitmq.producer;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import rabbitmq.conn.ChannelGroup;

public class Producer {
	Logger logger = LoggerFactory.getLogger(getClass());
	private Channel channel;
	private String queue;
	private String exchange;
	private String route;
	
	public static final BuiltinExchangeType HEADERS = BuiltinExchangeType.HEADERS;
	public static final BuiltinExchangeType FANOUT = BuiltinExchangeType.FANOUT;
	public static final BuiltinExchangeType DIRECT = BuiltinExchangeType.DIRECT;
	public static final BuiltinExchangeType TOPIC = BuiltinExchangeType.TOPIC;
	
	public Producer(){
		channel = ChannelGroup.getChannel();
	}
	
	public Producer declareQ(String name){
		try {
			this.queue = name;
			channel.queueDeclare(name, true, false, false, null);
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		return this;
	}
	
	public Producer declareE(String exchange, String route){
		try {
			this.exchange = exchange;
			this.route = route;
			channel.exchangeDeclare(exchange, DIRECT, true, false, null);
			channel.queueBind(queue, exchange, route);
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		return this;
	}
	
	public void publishMsg(Object o){
		try {
			channel.basicPublish(exchange, route, null, o.toString().getBytes());
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
	}
	
	public static void main(String[] args) {
		Producer producer = new Producer().declareQ("QUEUE").declareE("EXCHANGE", "ROUTE");
		
		producer.publishMsg("ONE");
		producer.publishMsg("TWO");
		producer.publishMsg("THREE");
		producer.publishMsg("FOUR");
		producer.publishMsg("FIVE");
	}
}