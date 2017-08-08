package rabbitmq.consumer;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

public class ShutdownListenerImp implements ShutdownListener {

	@Override
	public void shutdownCompleted(ShutdownSignalException cause) {
		
	}
}
