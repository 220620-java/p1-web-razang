package com.revature.raza.delegates;

import java.io.IOException;
import java.util.Map;

// import com.fasterxml.jackson.databind.ObjectMapper;
// import com.fasterxml.jackson.databind.exc.MismatchedInputException;
// import com.revature.raza.models.User;
// import com.revature.petapp.models.dtos.UserDTO;
// import com.revature.petapp.services.UserService;
// import com.revature.petapp.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthDelegate implements FrontControllerDelegate {
	// private UserService userServ = new UserServiceImpl();
	// private ObjectMapper objMapper = new ObjectMapper();

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
	private void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// try {
		// 	Map<String, String> credentials = objMapper.readValue(req.getInputStream(), Map.class);
		// 	if (credentials == null) throw new RuntimeException();
		// 	if (credentials.containsKey("username") && credentials.containsKey("password")) {
		// 		User user = userServ.logIn(credentials.get("username"), credentials.get("password"));
		// 		if (user!=null) {
		// 			UserDTO userResp = new UserDTO(user);
		// 			resp.getWriter().write(objMapper.writeValueAsString(userResp));
		// 		} else {
		// 			resp.sendError(401, "Incorrect credentials.");
		// 		}
		// 	} else {
		// 		resp.sendError(400, "The request body must contain username and password, like so: \n"
		// 				+ "{\n"
		// 				+ "\t \"username\":\"value\"\n"
		// 				+ "\t \"password\":\"value\"\n"
		// 				+ "}");
		// 	}
		// } catch (MismatchedInputException | RuntimeException e) {
		// 	resp.sendError(400, "The request body was empty.");
		// }
	}
}
