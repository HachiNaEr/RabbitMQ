package rabbitmq.enums;

public enum MessageArguments {
	X_EXPIRES("x-expires", "only one message expire time");
	
	private String key;
	private String description;
	
	private MessageArguments(String key, String description) {
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
