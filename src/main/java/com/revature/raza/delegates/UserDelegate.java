package com.revature.raza.delegates;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
// import com.revature.petapp.exceptions.UsernameAlreadyExistsException;
// import com.revature.petapp.models.User;
// import com.revature.petapp.models.dtos.UserDTO;
// import com.revature.petapp.services.UserService;
// import com.revature.petapp.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserDelegate implements FrontControllerDelegate {
	// private UserService userServ = new UserServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();

		switch (method) {
		case "GET":
			get(req, resp);
			break;
		case "POST":
			post(req, resp);
			break;
		case "PUT":
			put(req, resp);
			break;
		case "DELETE":
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			break;
		default:
		}
	}

	private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String path = (String) req.getAttribute("path");
		// if (path==null || "".equals(path)) {
		// 	resp.sendError(403, "Access to all users is forbidden.");
		// } else {
		// 	try {
		// 		int id = Integer.valueOf(path);
		// 		User user = userServ.getUser(id);
		// 		if (user!=null) {
		// 			// this DTO (data transfer object) prepares the user to be sent in
		// 			// the response by removing the password
		// 			UserDTO userResp = new UserDTO(user);
		// 			resp.getWriter().write(objMapper.writeValueAsString(userResp));
		// 		} else {
		// 			resp.sendError(404, "User with that ID not found.");
		// 		}
		// 	} catch (NumberFormatException e) {
		// 		resp.sendError(400, e.getMessage());
		// 	}
		// }
	}

	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String path = (String) req.getAttribute("path");
		// if (path==null || "".equals(path)) {
		// 	try {
		// 	User user = objMapper.readValue(req.getInputStream(), User.class);
		// 		if (user==null) throw new RuntimeException();
		// 		try {
		// 			user = userServ.registerUser(user);
					
		// 			// this DTO (data transfer object) prepares the user to be sent in
		// 			// the response by removing the password
		// 			UserDTO userResp = new UserDTO(user);
		// 			resp.getWriter().write(objMapper.writeValueAsString(userResp));
		// 		} catch (UsernameAlreadyExistsException e) {
		// 			resp.sendError(409, "A user with that username already exists.");
		// 		}
		// 	} catch (MismatchedInputException | RuntimeException e) {
		// 		resp.sendError(400, "The request body was empty.");
		// 	}
		// } else {
		// 	resp.sendError(400, "Cannot POST to this URI. Try sending the request to /users.");
		// }
	}

	private void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO allow update user
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}
}
