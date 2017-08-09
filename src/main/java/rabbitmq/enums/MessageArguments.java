package rabbitmq.enums;

public enum MessageArguments {
	X_EXPIRES("x-expires"); // one message expire
	
	private String content;
	
	private MessageArguments(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
