package com.revature.raza.servlets;

import java.io.IOException;
import java.io.Writer;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
	{
		Writer writer;
		try {
			writer = resp.getWriter();
			writer.write("Sallam Alykum");
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.getMessage();
		} 
	}
		
}
