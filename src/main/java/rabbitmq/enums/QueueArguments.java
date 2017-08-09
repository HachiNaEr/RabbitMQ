package rabbitmq.enums;

public enum QueueArguments {
	X_MESSAGE_TTL("x-message_ttl", "message expired time"),
	X_MAX_LENGTH("x-max-length", "message max count"), 
	X_MAX_LENGTH_BYTES("x-max-length-bytes", "message max bytes");
	
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
