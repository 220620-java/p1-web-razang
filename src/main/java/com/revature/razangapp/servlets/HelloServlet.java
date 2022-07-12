package com.revature.razangapp.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// our servlets should extend HttpServlet
public class HelloServlet extends HttpServlet {
	// the servlet container (tomcat catalina) will call this method when
	// a GET request comes in to the right path and it will create the objects
	// for the request and response and pass those in
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// gets the response body writer object so that we can write to the response body
		PrintWriter writer = resp.getWriter();
		writer.write("Hello! :)");
	}
}
