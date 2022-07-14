package com.revature.raza.servlets;

import java.io.IOException;

import org.apache.catalina.servlets.DefaultServlet;

import com.revature.raza.delegates.FrontControllerDelegate;
import com.revature.raza.delegates.RequestMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FrontControllerServlet extends DefaultServlet {
	private RequestMapper mapper = new RequestMapper();
	
	private void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// do any filtering/processing, then map the request to the appropriate delegate
		FrontControllerDelegate delegate = mapper.map(req, resp);
		
		// if the URI has an appropriate delegate
		if (delegate != null) {
			delegate.handle(req, resp);
		} else {
			resp.sendError(404);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		process(req, resp);
	}
}
