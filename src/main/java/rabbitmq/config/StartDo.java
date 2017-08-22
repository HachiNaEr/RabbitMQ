package rabbitmq.config;

import rabbitmq.conn.ChannelGroup;

public class StartDo{
	public static void start(){
		ChannelGroup.getChannel();
	}
}
