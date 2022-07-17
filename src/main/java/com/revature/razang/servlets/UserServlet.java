package com.revature.razang.servlets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.razang.exceptions.UsernameAlreadyExistsException;
import com.revature.razang.models.User;
import com.revature.razang.models.dtos.UserDTO;
import com.revature.razang.services.UserService;
import com.revature.razang.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserServlet extends HttpServlet {
	// the servlet container (tomcat catalina) will call this method when
	// a GET request comes in to the right path and it will create the objects
	// for the request and response and pass those in
	private UserService userService = new UserServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StringBuilder uriString = new StringBuilder(req.getRequestURI()); // /pet-app1/hello/6
		uriString.replace(0, req.getContextPath().length()+1, ""); // hello/6
		String path = (String) req.getAttribute("path");
		
		// if there is a slash
		if (uriString.indexOf("/") != -1) {
			// resp.sendError(403, "Access to all users is forbidden.");
			// get available account holders
			List<User> customers = userService.getAllusers();

			// // the object mapper writes the list as a JSON string to the response body
			resp.getWriter().write(objMapper.writeValueAsString(customers));
		} else {
			try {
				int id = Integer.valueOf(path);
				User user = userService.findUserById(id);
				if (user!=null) {
					// this DTO (data transfer object) prepares the user to be sent in
					// the response by removing the password
					UserDTO userResp = new UserDTO(user);
					resp.getWriter().write(objMapper.writeValueAsString(userResp));
				} else {
					resp.sendError(404, "User with that ID not found.");
				}
			} catch (NumberFormatException e) {
				resp.sendError(400, e.getMessage());
			}
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			try {
			User user = objMapper.readValue(req.getInputStream(), User.class);
				if (user==null) throw new RuntimeException();
				try {
					user = userService.registerUser(user);
					// this DTO (data transfer object) prepares the user to be sent in
					// the response by removing the password
					UserDTO userResp = new UserDTO(user);
					resp.getWriter().write(objMapper.writeValueAsString(userResp));
				} catch (UsernameAlreadyExistsException e) {
					resp.sendError(409, "A user with that username already exists.");
				}
			} catch (MismatchedInputException | RuntimeException e) {
				resp.sendError(400, "The request body was empty.");
			}
		} else {
			resp.sendError(400, "Cannot POST to this URI. Try sending the request to /users.");
		}
	}
	
	@Override
	protected void doPut (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	} 
}
