package rabbitmq.enums;

public enum Arguments {

	X_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange", "dead exchange name"),
	X_DEAD_LETTER_KEY("x-dead-letter-routing-key", "dead exchange route");

	String key;
	String description;

	private Arguments(String key, String description) {
		this.key = key;
		this.description = description;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
}
