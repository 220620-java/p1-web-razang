package com.revature.razang.delegates;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

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
		try {
			Map<String, String> credentials = objMapper.readValue(req.getInputStream(), Map.class);
			if (credentials == null) throw new RuntimeException();
			if (credentials.containsKey("username") && credentials.containsKey("password")) {
				User user = userServ.loginUser(credentials.get("username"), credentials.get("password"));
				if (user != null) {
					PrintWriter writerOut = resp.getWriter();
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.setCharacterEncoding("UTF-8");
					String jsonString = objMapper.writeValueAsString(new UserDTO(user));
					writerOut.print(jsonString);

					String token = WebUtils.CreateJWT();
					writerOut.print(token);
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
			resp.sendError(400, e.toString() + " " + e.getStackTrace());
		}
		catch (RuntimeException e) {
			resp.sendError(400, e.toString() + " " + e.getStackTrace());
		}
	}
}
