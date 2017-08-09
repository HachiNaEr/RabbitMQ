package rabbitmq.util;

import java.util.Date;
import java.util.Map;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.AMQP.BasicProperties.Builder;

public class MessageBasicProp {
	private Builder builder = new BasicProperties().builder();
	
	public Builder contentEncoding(String contentEncoding){
		return builder.contentEncoding(contentEncoding);
	}
	
	public Builder contentType(String contentType){
		return builder.contentType(contentType);
	}

	public Builder deliveryMode(Integer deliveryMode){
		return builder.deliveryMode(deliveryMode);
	}

	public Builder expiration(String expiration){
		return builder.expiration(expiration);
	}

	public Builder messageId(String messageId){
		return builder.messageId(messageId);
	}

	public Builder priority(Integer priority){
		return builder.priority(priority);
	}

	public Builder type(String type){
		return builder.type(type);
	}

	public Builder contentEncoding(Map<String, Object> headers){
		return builder.headers(headers);
	}

	public Builder appId(String appId){
		return builder.appId(appId);
	}

	public Builder clusterId(String clusterId){
		return builder.clusterId(clusterId);
	}

	public Builder correlationId(String correlationId){
		return builder.correlationId(correlationId);
	}

	public Builder replyTo(String replyTo){
		return builder.replyTo(replyTo);
	}

	public Builder userId(String userId){
		return builder.userId(userId);
	}

	public Builder timestamp(Date timestamp){
		return builder.timestamp(timestamp);
	}
}
