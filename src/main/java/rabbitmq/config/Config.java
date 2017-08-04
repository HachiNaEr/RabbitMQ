package rabbitmq.config;

import com.jfinal.config.Constants;
import com.jfinal.config.Handlers;
import com.jfinal.config.Interceptors;
import com.jfinal.config.JFinalConfig;
import com.jfinal.config.Plugins;
import com.jfinal.config.Routes;
import com.jfinal.core.JFinal;
import com.jfinal.template.Engine;

import rabbitmq.controller.RabbitMQController;

public class Config extends JFinalConfig{
	public static void main(String[] args) {
		JFinal.start("WebContent", 8888, "/", 5);
	}
	
	@Override
	public void configConstant(Constants me) {
		me.setDevMode(true);
	}

	@Override
	public void configRoute(Routes me) {
		me.add("/rabbit", RabbitMQController.class);
	}

	@Override
	public void configEngine(Engine me) {}

	@Override
	public void configPlugin(Plugins me) {}

	@Override
	public void configInterceptor(Interceptors me) {}

	@Override
	public void configHandler(Handlers me) {}
}
