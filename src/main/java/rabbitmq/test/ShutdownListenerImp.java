package rabbitmq.test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.ShutdownListener;
import com.rabbitmq.client.ShutdownSignalException;

public class ShutdownListenerImp implements ShutdownListener {
	
	@Override
	public void shutdownCompleted(ShutdownSignalException cause) {
		try {
			if (CDemo.getConnection().isOpen()) {
				CDemo.getConnection().close();
			}
			CDemo.conn();
			CDemo.getChannel().addShutdownListener(this);
			CDemo.init();
		} catch (IOException | TimeoutException e) {
			e.printStackTrace();
		}
	}
}
