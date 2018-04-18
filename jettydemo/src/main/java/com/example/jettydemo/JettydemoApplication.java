package com.example.jettydemo;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.DefaultHandler;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JettydemoApplication {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(JettydemoApplication.class, args);
		Server server =new Server();

		ServerConnector connector=new ServerConnector(server);
		connector.setPort(8080);
		server.setConnectors(new Connector[]{connector});

		ServletContextHandler context=new ServletContextHandler();
		context.setContextPath("/");
		context.addServlet(HelloServlet.class,"/hello");
		context.addServlet(AsyncEchoServlet.class,"/echo/*");

		HandlerCollection handlers=new HandlerCollection();
		handlers.setHandlers(new Handler[]{context,new DefaultHandler()});
		server.setHandler(handlers);

		server.start();
		server.join();
	}
}
