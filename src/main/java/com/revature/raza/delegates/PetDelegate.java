package com.revature.raza.delegates;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.MismatchedInputException;
// import com.revature.petapp.models.Pet;
// import com.revature.petapp.services.AdminService;
// import com.revature.petapp.services.AdminServiceImpl;
// import com.revature.petapp.services.UserService;
// import com.revature.petapp.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class PetDelegate implements FrontControllerDelegate {
	// private UserService userServ = new UserServiceImpl();
	// private AdminService adminServ = new AdminServiceImpl();
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

	public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String path = (String) req.getAttribute("path");
		// if (path==null || "".equals(path)) {
		// 	resp.getWriter().write(objMapper.writeValueAsString(userServ.viewAllPets()));
		// } else {
		// 	try {
		// 		int id = Integer.valueOf(path);
		// 		Pet pet = userServ.getPet(id);
		// 		if (pet!=null) {
		// 			resp.getWriter().write(objMapper.writeValueAsString(pet));
		// 		} else {
		// 			resp.sendError(404, "Pet with that ID not found.");
		// 		}
		// 	} catch (NumberFormatException e) {
		// 		resp.sendError(400, e.getMessage());
		// 	}
		// }
	}

	public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String path = (String) req.getAttribute("path");
		// if (path==null || "".equals(path)) {
		// 	Pet pet = objMapper.readValue(req.getInputStream(), Pet.class);
		// 	if (pet!=null) {
		// 		pet = adminServ.addPet(pet);
		// 		resp.getWriter().write(objMapper.writeValueAsString(pet));
		// 	} else {
		// 		resp.sendError(400, "The request body was empty.");
		// 	}
		// } else {
		// 	resp.sendError(400, "Cannot POST to this URI. Try sending the request to /pets.");
		// }
	}
	
	public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// String path = (String) req.getAttribute("path");
		// if (path==null || "".equals(path)) {
		// 	resp.sendError(400, "Cannot PUT to this URI. Try sending the request to /pets/{id}.");
		// } else {
		// 	try {
		// 		int id = Integer.valueOf(path);
		// 		Pet pet = userServ.getPet(id);
		// 		if (pet!=null) {
		// 			pet = objMapper.readValue(req.getInputStream(), Pet.class);
					
		// 			try {
		// 				if (pet==null) throw new RuntimeException();
		// 				if (pet.getId()==id) {
		// 					pet = adminServ.editPet(pet);
		// 					resp.getWriter().write(objMapper.writeValueAsString(pet));
		// 				} else {
		// 					resp.sendError(409, "The ID in the URI did not match the ID in the body.");
		// 				}
		// 			} catch (MismatchedInputException | RuntimeException e) {
		// 				resp.sendError(400, "The request body was empty.");
		// 			}
		// 		} else {
		// 			resp.sendError(404, "Pet with that ID not found.");
		// 		}
		// 	} catch (NumberFormatException e) {
		// 		resp.sendError(400, e.getMessage());
		// 	}
		// }
	}
}
