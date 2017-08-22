package rabbitmq.controller;

import com.jfinal.core.Controller;

import rabbitmq.producer.Producer;
import rabbitmq.util.JsonUtil;


public class RabbitMQController extends Controller{
	public void sendMsg(){
		String message = getPara("message");
		
		Producer producer = new Producer();
		producer.init("QUEUE2", "EXCHANGE", "ROUTE").sendMsg(message);
		
		renderJson(JsonUtil.switchToJsonObj(1, "发送消息成功", null));
	}
	
}	
