package rabbitmq.controller;

import java.io.IOException;

import com.jfinal.core.Controller;

import rabbitmq.mq.RabbitMQUtil;

public class RabbitMQController extends Controller{
	public void registerQueue(){
		RabbitMQUtil.bindQueue("LYJ");
		renderJson("success");
	}
	
	public void publishMSG() throws IOException{
		boolean isSuc = RabbitMQUtil.publishMSG("这是一个消息");
		renderJson(isSuc);
	}
}	
