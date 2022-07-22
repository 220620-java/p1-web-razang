package com.revature.razang.servlets;

import java.io.IOException;

import org.apache.catalina.servlets.DefaultServlet;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.revature.razang.delegates.AuthDelegate;
import com.revature.razang.delegates.FrontControllerDelegate;
import com.revature.razang.delegates.RequestMapper;
import com.revature.razang.delegates.UserDelegate;
import com.revature.razang.utilities.WebUtils;

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

			// JWT AUTHENTICATION, 
			// Allow users to register/login without authentication

			// if delegate is AuthDelegate then it doesn't need authentication for logging in
			if (delegate instanceof AuthDelegate) {
				// Handle the request without authentication
				delegate.handle(req, resp);
			}

			// if delegate is UserDelegate then it might not need authentication
			if (delegate instanceof UserDelegate) {

				// if UserDelegate is posting then it doesn't need authentication for registering
				if (req.getMethod() != "POST") {
					delegate.handle(req, resp);
				}
			}

			// REQUEST REQUIRES AUTHENTICATION
			// Grab token from the header
			String token = req.getHeader("token");

			// check if user has JWT
			if (token != null) {
				try {
					// VERIFY JWT, IF IT'S NOT ISSUED BY RAZANG, 403
					WebUtils.VerifyJWT(token);
				} catch (JWTVerificationException e){
					resp.sendError(403, "User has invalid token! Please login at /revature-web/auth!\n" + e.toString());
					resp.getWriter().flush();
					return;
				}
			}
			else {
				resp.sendError(403, "User is not authenticated. Please login at /revature-web/auth!");
				return;
			}

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

	protected void askAuthentication (HttpServletRequest req, HttpServletResponse resp) {

	}
}
