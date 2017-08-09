package rabbitmq.enums;

import java.util.Map;

public class SetArguments {
	public Map<String, Object> arguments;
	
	public void setDeadLetterExchange(String name){
		arguments.put(QueueArguments.X_DEAD_LETTER_EXCHANGE.getKey(), name);
	}
	
	public void setDeadLetterKey(String name){
		arguments.put(QueueArguments.X_DEAD_LETTER_KEY.getKey(), name);
	}
	
	public void setMessageExpired(long interval){
		arguments.put(QueueArguments.X_MESSAGE_TTL.getKey(), interval);
	}
	
	public Map<String, Object> getArguments(){ return arguments; }
}
