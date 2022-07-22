package com.revature.razang.delegates;

import java.io.IOException;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import com.revature.razang.exceptions.UsernameAlreadyExistsException;
import com.revature.razang.models.User;
import com.revature.razang.models.dtos.UserDTO;
import com.revature.razang.services.UserService;
import com.revature.razang.services.UserServiceImpl;

public class UserDelegate implements FrontControllerDelegate {
	private UserService userService = new UserServiceImpl();
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
			delete(req, resp);
			break;
		default:
		}
	}

	// /users/ - grab all users
	// /users/{userid} - find user by id

	private void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		System.out.println(path.toString());
		
		
		if (path==null || "".equals(path)) {
			resp.sendError(403, "Access to all users is forbidden.");
			// get available account holders
			List<User> customers = userService.getAllusers();

			// // the object mapper writes the list as a JSON string to the response body
			resp.getWriter().write(objMapper.writeValueAsString(customers));
		} else {
			//resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
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
			 	resp.sendError(400, e.getMessage() + " ........");
			 }
		}
	}

	// /users/ - Registers a new user

	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
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

	// /users/{userId} - Updates user by userid
	private void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
		String path = req.getAttribute("path").toString(); 
		if (path == null) {
			try {
				User user = objMapper.readValue(req.getInputStream(), User.class); 
				if (user == null) throw new RuntimeException(); 
				try {
					user = userService.updateUser(user); 
				} catch(Exception e) {
					resp.sendError(400, "User can't not be updated");
				}
				
			}catch(MismatchedInputException | RuntimeException e) {
				resp.sendError(400, "Can't update the user");
			}
		}else {
			resp.sendError(400, "Cannot PUT to this URI. Try sending the request to /users.");
		}
	}

	// /users/{userId} - Deletes user by userid
	private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}
}
