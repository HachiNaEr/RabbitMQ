package rabbitmq.config;

import rabbitmq.conn.ChannelGroup;

public class StopDo {
	public static void stop(){
		ChannelGroup.close();
	}
}
