package rabbitmq.conn;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

public class CreateChannel {
	Logger logger = LoggerFactory.getLogger(getClass());
	
	public Map<Integer, Channel> channelMap = new HashMap<>();
	
	public void create(int count){
		Connection connection = new TCPConn().conn();
		if (count > connection.getChannelMax()) {
			logger.debug("max channel : " + connection.getChannelMax());
			return;
		}
		for (int index = 0; index < count; index++) {
			try {
				Channel channel = connection.createChannel();
				if (channel != null) 
					channelMap.put(channel.getChannelNumber(), channel);
				else 
					logger.debug("channel == null : fail allocate, count : " + count);
			} catch (IOException exception) {
				logger.debug(ExceptionUtils.getStackTrace(exception));
			}
		}
	}
}
