package rabbitmq.util;

import java.util.HashMap;
import java.util.Map;

import rabbitmq.enums.QueueArguments;

public class QueueBasicProp {
	private Map<String, Object> arguments = new HashMap<>();
	
	public QueueBasicProp deadLetterExchange(String exchange){
		arguments.put(QueueArguments.X_DEAD_LETTER_EXCHANGE.getContent(), exchange);
		return this;
	}
	
	public QueueBasicProp deadLetterRoute(String route){
		arguments.put(QueueArguments.X_DEAD_LETTER_KEY.getContent(), route);
		return this;
	}
	
	public QueueBasicProp messageExpired(long interval){
		arguments.put(QueueArguments.X_MESSAGE_TTL.getContent(), interval);
		return this;
	}
	
	public QueueBasicProp maxLength(int max){
		arguments.put(QueueArguments.X_MAX_LENGTH.getContent(), max);
		return this;
	} 
	
	public QueueBasicProp maxLengthBytes(long max){
		arguments.put(QueueArguments.X_MAX_LENGTH_BYTES.getContent(), max);
		return this;
	}
	
	public QueueBasicProp priority(int max){
		arguments.put(QueueArguments.X_MAX_PRIORITY.getContent(), max);
		return this;
	}
	
	public QueueBasicProp expires(long expire){
		arguments.put(QueueArguments.X_EXPIRES.getContent(), expire);
		return this;
	}
	
	public Map<String, Object> build(){ return arguments; }
}
