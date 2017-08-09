package rabbitmq.util;

import java.util.Map;

import rabbitmq.enums.QueueArguments;

public class SetQueueArguments {
	public Map<String, Object> arguments;
	
	public void setMessageExpired(long interval){
		arguments.put(QueueArguments.X_MESSAGE_TTL.getKey(), interval);
	}
	
	public void setMaxLength(int max){
		arguments.put(QueueArguments.X_MAX_LENGTH.getKey(), max);
	}
	
	public void setMaxLengthBytes(long max){
		arguments.put(QueueArguments.X_MAX_LENGTH_BYTES.getKey(), max);
	}
	
	public Map<String, Object> getArguments(){ return arguments; }
}
