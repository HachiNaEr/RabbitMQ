package rabbitmq.enums;

public enum QueueBindArguments {
	DEMO("");
	
	private String content;
	
	private QueueBindArguments(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
