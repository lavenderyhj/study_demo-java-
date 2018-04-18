package com.example.jettydemo;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by yuhuijuan on 2018/2/22
 */
public class HelloServlet extends HttpServlet {
	private final String greeting;

	public HelloServlet(){
		this("Hello");
	}

	public HelloServlet(String greeting){
		this.greeting=greeting;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		response.setStatus(HttpServletResponse.SC_OK);
		response.getWriter().println("<h1>"+greeting+" from HelloServerlet</h1>");
	}
}
