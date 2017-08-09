package rabbitmq.util;

import java.util.Map;

import rabbitmq.enums.QueueArguments;

public class SetQueueArguments {
	public Map<String, Object> arguments;
	
	public void setDeadLetterExchange(String exchange){
		arguments.put(QueueArguments.X_DEAD_LETTER_EXCHANGE.getContent(), exchange);
	}
	
	public void setDeadLetterRoute(String route){
		arguments.put(QueueArguments.X_DEAD_LETTER_KEY.getContent(), route);
	}
	
	public void setMessageExpired(long interval){
		arguments.put(QueueArguments.X_MESSAGE_TTL.getContent(), interval);
	}
	
	public void setMaxLength(int max){
		arguments.put(QueueArguments.X_MAX_LENGTH.getContent(), max);
	}
	
	public void setMaxLengthBytes(long max){
		arguments.put(QueueArguments.X_MAX_LENGTH_BYTES.getContent(), max);
	}
	
	public Map<String, Object> getArguments(){ return arguments; }
}
