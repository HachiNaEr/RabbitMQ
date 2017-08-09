package rabbitmq.enums;

public enum QueueArguments {
	X_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange"), // dead letter 
	X_DEAD_LETTER_KEY("x-dead-letter-routing-key"), // dead letter
	X_MESSAGE_TTL("x-message_ttl"), // all message time
	X_MAX_LENGTH("x-max-length"), // message max count 
	X_MAX_LENGTH_BYTES("x-max-length-bytes"); // message max length bytes
	
	private String content;
	
	private QueueArguments(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
