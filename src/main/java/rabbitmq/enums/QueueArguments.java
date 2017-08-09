package rabbitmq.enums;

public enum QueueArguments {
	X_DEAD_LETTER_EXCHANGE("x-dead-letter-exchange"), // 死信交换器
	X_DEAD_LETTER_KEY("x-dead-letter-routing-key"), // 死信route
	
	X_MESSAGE_TTL("x-message-ttl"), // 队列中所有消息过期时间
	
	X_MAX_LENGTH("x-max-length"), // 队列能存储消息的最大数量
	X_MAX_LENGTH_BYTES("x-max-length-bytes"), // 队列中能存储消息的最大空间
	
	X_MAX_PRIORITY("x-max-priority"), // 消息优先级范围
	
	X_EXPIRES("x-expires"); // 如果没有消费者情况下，队列多久过期
	
	private String content;
	
	private QueueArguments(String content) {
		this.content = content;
	}

	public String getContent() {
		return content;
	}
}
