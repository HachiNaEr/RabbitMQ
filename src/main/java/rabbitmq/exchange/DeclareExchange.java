package rabbitmq.exchange;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;

import rabbitmq.conn.ChannelGroup;

public class DeclareExchange {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private boolean isInit = false;
	private Channel channel;
	
	public static final BuiltinExchangeType HEADERS = BuiltinExchangeType.HEADERS;
	public static final BuiltinExchangeType FANOUT = BuiltinExchangeType.FANOUT;
	public static final BuiltinExchangeType DIRECT = BuiltinExchangeType.DIRECT;
	public static final BuiltinExchangeType TOPIC = BuiltinExchangeType.TOPIC;
	
	public DeclareExchange(){
		if (isInit)  { return; }
		channel = ChannelGroup.getChannel();
		
		try {
			declareFanoutExchange(FANOUT.getType());
			declareHeadersExchange(HEADERS.getType());
			declareDirectExchange(DIRECT.getType());
			declareTopicExchange(TOPIC.getType());
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		isInit = true;
	}
	
	public DeclareExchange(Map<String, Object> fanoutMap, Map<String, Object> headerMap, 
						   Map<String, Object> directMap, Map<String, Object> topicMap){
		if (isInit)  { return; }
		channel = ChannelGroup.getChannel();
		
		try {
			if (Optional.ofNullable(fanoutMap).isPresent()) {
				declareFanoutExchange(FANOUT.getType(), fanoutMap);
			} else {
				declareFanoutExchange(FANOUT.getType());
			}
			
			if (Optional.ofNullable(headerMap).isPresent()) {
				declareHeardersExchange(HEADERS.getType(), headerMap);
			} else {
				declareHeadersExchange(HEADERS.getType());
			}
			
			if (Optional.ofNullable(fanoutMap).isPresent()) {
				declareDirectExchange(DIRECT.getType(), directMap);
			} else {
				declareDirectExchange(DIRECT.getType());
			}
		
			if (Optional.ofNullable(topicMap).isPresent()) {
				declareTopicExchange(TOPIC.getType(), topicMap);
			} else {
				declareTopicExchange(TOPIC.getType());
			}
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
		isInit = true;
	}
	
	private void declareFanoutExchange(String exchange) throws IOException{
		channel.exchangeDeclare(exchange, FANOUT, true, false, null);
	}
	
	private void declareHeadersExchange(String exchange) throws IOException{
		channel.exchangeDeclare(exchange, HEADERS, true, false, null);
	}
	
	private void declareDirectExchange(String exchange) throws IOException{
		channel.exchangeDeclare(exchange, DIRECT, true, false, null);
	}
	
	private void declareTopicExchange(String exchange) throws IOException{
		channel.exchangeDeclare(exchange, TOPIC, true, false, null);
	}
	
	private void declareFanoutExchange(String exchange, Map<String, Object> arguments) throws IOException{
		channel.exchangeDeclare(exchange, FANOUT, true, false, arguments);
	}
	
	private void declareHeardersExchange(String exchange, Map<String, Object> arguments) throws IOException{
		channel.exchangeDeclare(exchange, HEADERS, true, false, arguments);
	}
	
	private void declareDirectExchange(String exchange, Map<String, Object> arguments) throws IOException{
		channel.exchangeDeclare(exchange, DIRECT, true, false, arguments);
	}
	
	private void declareTopicExchange(String exchange, Map<String, Object> arguments) throws IOException{
		channel.exchangeDeclare(exchange, TOPIC, true, false, arguments);
	}
}
