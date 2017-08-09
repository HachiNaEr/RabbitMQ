package rabbitmq.enums;

public enum QueueArguments {
	X_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange", "dead exchange name"),
	X_DEAD_LETTER_KEY("x-dead-letter-routing-key", "dead exchange route"),
	X_MESSAGE_TTL("x-message_ttl", "message expired time");
	
	private String key;
	private String description;
	
	private QueueArguments(String key, String description) {
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
