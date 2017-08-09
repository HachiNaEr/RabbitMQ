package rabbitmq.producer;

import java.io.IOException;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.AMQP.BasicProperties;

import rabbitmq.conn.ChannelGroup;
import rabbitmq.util.MessageBasicProp;
import rabbitmq.util.QueueBasicProp;

public class Producer {
	Logger logger = LoggerFactory.getLogger(getClass());
	private Channel channel;
	
	public static final BuiltinExchangeType HEADERS = BuiltinExchangeType.HEADERS;
	public static final BuiltinExchangeType FANOUT = BuiltinExchangeType.FANOUT;
	public static final BuiltinExchangeType DIRECT = BuiltinExchangeType.DIRECT;
	public static final BuiltinExchangeType TOPIC = BuiltinExchangeType.TOPIC;
	
	public Producer(){ channel = ChannelGroup.getChannel(); }
	
	public Producer init(String queue, String exchange, String route){
		try {
			channel.queueDeclare(queue, true, false, false, new QueueBasicProp().priority(5).build());
			
			channel.exchangeDeclare(exchange, DIRECT, true, false, null);
			
			channel.queueBind(queue, exchange, route);
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		return this;
	}
	
	public void sendMsg(String exchange, String route, Object o){
		try {
			BasicProperties prop = new MessageBasicProp().priority(4).build();
			channel.basicPublish(exchange, route, prop, o.toString().getBytes());
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
	}
	
	public static void main(String[] args) {
		Producer producer = new Producer();
		producer.init("QUEUE", "EXCHANGE", "ROUTE");
		
//		producer.sendMsg("EXCHANGE", "ROUTE", "ONE");
//		producer.sendMsg("EXCHANGE", "ROUTE", "TWO");
//		producer.sendMsg("EXCHANGE", "ROUTE", "THREE");
//		producer.sendMsg("EXCHANGE", "ROUTE", "FOUR");
//		producer.sendMsg("EXCHANGE", "ROUTE", "FIVE");
		producer.sendMsg("EXCHANGE", "ROUTE", "SIX");
	}
}