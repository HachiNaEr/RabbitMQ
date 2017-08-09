package rabbitmq.enums;

public enum ExchangeArguments {
	X_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange", "dead exchange name"),
	X_DEAD_LETTER_KEY("x-dead-letter-routing-key", "dead exchange route");
	
	private String key;
	private String description;
	
	private ExchangeArguments(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public String getDescription() {
		return description;
	}
}
