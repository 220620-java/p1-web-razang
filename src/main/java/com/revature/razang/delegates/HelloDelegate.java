package com.revature.razang.delegates;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloDelegate implements FrontControllerDelegate {

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();

		switch (method) {
		case "GET":
			get(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}
	
	private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// query/request parameters
		// localhost:8080/pet-app1/hello?language=en
		String language = req.getParameter("language");
		
		// this gets a reader that will read the HTTP request body
		BufferedReader reader = req.getReader();
	
		String requestBody = "";
		String line = "";
		while ((line=reader.readLine())!=null) {
			requestBody += line;
		}
		
		PrintWriter writer = resp.getWriter();
		
		if (language==null) language="";
		switch (language) {
		case "en":
			writer.write("Hello, " + requestBody + "! :)");
			break;
		case "fr":
			writer.write("Bonjour, " + requestBody + "! :)");
			break;
		case "es":
			writer.write("Hola, " + requestBody + "! :)");
			break;
		default:
			writer.write(requestBody + "! :)");
		}
		
	}
}
