package rabbitmq.conn;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class ChannelGroup {
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static Channel channel ;
	
	public static Channel getChannel(){
		if (Optional.ofNullable(channel).isPresent()) {
			return channel;
		}
		synchronized (ChannelGroup.class) {
			new ChannelGroup();
		}
		return channel;
	}
	
	private ChannelGroup() { create(); }

	private void create(){
		Connection connection = Conn.getConn();
		try {
			channel = connection.createChannel();
			if (!Optional.ofNullable(channel).isPresent()) {
				logger.debug(" create channel fail !");
			}
		} catch (IOException exception) {
			logger.debug(ExceptionUtils.getStackTrace(exception));
		}
	}
}
