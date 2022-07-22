package com.revature.razang.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Stream;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
import com.revature.razang.models.User;
import com.revature.razang.models.dtos.UserDTO;
import com.revature.razang.services.UserService;
import com.revature.razang.services.UserServiceImpl;
import com.revature.razang.utilities.WebUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/** Authorizes the user with a Java Web Token.
 * @author Colby Tang
 */
public class AuthDelegate implements FrontControllerDelegate {
	private UserService userServ = new UserServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

	
	@Override
	public void handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String method = req.getMethod();
		
		switch (method) {
		case "POST":
			post(req, resp);
			break;
		default:
			resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
		}
	}

	/**
	 * Handles requests to log in. Accepts an object with username and password as keys.
	 * 
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter writerOut = resp.getWriter();
		try {
			// Map input json to a map
			Map<String, String> credentials = objMapper.readValue(req.getInputStream(), Map.class);

			// if jackson is unable to create a map then runtime exception
			if (credentials == null) throw new RuntimeException();

			// if map has username and password key/value pairs, then log in
			if (credentials.containsKey("username") && credentials.containsKey("password")) {

				// send in the username to find the user and the password to verify
				User user = userServ.loginUser(credentials.get("username"), credentials.get("password"));

				// if there is a user, then proceed. An invalid password will return a null user.
				if (user != null) {

					// empty username
					if (user.getUsername() == null) {
						resp.sendError(401, "Incorrect credentials.");
					}

					// everything is good, convert user to UserDTO to hide password
					// send 200 response with UserDTO as json object and JWT token
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");

					// CREATE JWT token
					String token = WebUtils.CreateJWT();
					UserDTO userDTO = new UserDTO(user);
					userDTO.setToken (token);
					String tokenString = objMapper.writeValueAsString(userDTO);
					writerOut.print(tokenString);
					writerOut.flush();
				} else {
					resp.sendError(401, "Incorrect credentials.");
				}
			} else {
				resp.sendError(400, "The request body must contain username and password, like so: \n"
						+ "{\n"
						+ "\t \"username\":\"value\"\n"
						+ "\t \"password\":\"value\"\n"
						+ "}");
			}
		} catch (MismatchedInputException e) {
			Stream.of(e.getStackTrace()).forEach(x -> {
				writerOut.println(x);
			});
			
			resp.sendError(400, e.toString());
		}
		catch (RuntimeException e) {
			Stream.of(e.getStackTrace()).forEach(x -> {
				writerOut.println(x);
			});
			resp.sendError(400, e.toString());
		}
	}
}
