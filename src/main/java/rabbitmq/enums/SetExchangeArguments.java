package rabbitmq.enums;

import java.util.Map;

public class SetExchangeArguments {
	public Map<String, Object> arguments;
	
	public void setDeadLetterExchange(String name){
		arguments.put(ExchangeArguments.X_DEAD_LETTER_EXCHANGE.getKey(), name);
	}
	
	public void setDeadLetterKey(String name){
		arguments.put(ExchangeArguments.X_DEAD_LETTER_KEY.getKey(), name);
	}
	
	public Map<String, Object> getArguments(){ return arguments; }
}
