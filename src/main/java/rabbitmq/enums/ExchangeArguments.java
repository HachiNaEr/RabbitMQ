package rabbitmq.enums;

public enum ExchangeArguments {
	DEMO("");
	
	private String content;
	
	private ExchangeArguments(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
