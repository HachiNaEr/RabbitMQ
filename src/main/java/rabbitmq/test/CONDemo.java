package rabbitmq.test;

import java.io.IOException;

import com.rabbitmq.client.AMQP.BasicProperties;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.Envelope;
import com.rabbitmq.client.ShutdownSignalException;

public class CONDemo implements Consumer{
	
    private final Channel channel;

    public CONDemo(Channel _channel) {
    	channel = _channel;
    }
	
	@Override
	public void handleConsumeOk(String consumerTag) {
		
	}

	@Override
	public void handleCancelOk(String consumerTag) {
		
	}

	@Override
	public void handleCancel(String consumerTag) throws IOException {
		
	}

	@Override
	public void handleDelivery(String str, Envelope env, BasicProperties prop, byte[] body) throws IOException {
		System.out.println(str);
		System.out.println(env);
		System.out.println(prop);
		String bodyStr = new String(body);
		System.err.println(bodyStr);
		try {
			String aString = bodyStr.substring(2, 5);
			aString.length();
			channel.basicAck(env.getDeliveryTag(), false);
		} catch (Exception e) {
			System.err.println(e);
			channel.basicReject(env.getDeliveryTag(), false);
		} finally {
		}

	}

	@Override
	public void handleShutdownSignal(String consumerTag, ShutdownSignalException sig) {
		
	}

	@Override
	public void handleRecoverOk(String consumerTag) {
		
	}

}
