package com.revature.raza.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
// import com.revature.petapp.models.Pet;
// import com.revature.petapp.models.Species;
// import com.revature.petapp.services.AdminService;
// import com.revature.petapp.services.AdminServiceImpl;
// import com.revature.petapp.services.UserService;
// import com.revature.petapp.services.UserServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Deprecated
/**
 * not using this anymore because of the switch to Front Controller Design Pattern,
 * but keeping it as an example for those who want to use multiple servlets on p1.
 * 
 * @author SierraNicholes
 *
 */
public class PetServlet extends HttpServlet {
	// private UserService userServ = new UserServiceImpl();
	// private AdminService adminServ = new AdminServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get available pets
		// List<Pet> pets = userServ.viewAllPets();
		
		// PrintWriter writer = resp.getWriter();
		
		// // the object mapper writes the pets list as a JSON string to the response body
		// writer.write(objMapper.writeValueAsString(pets));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// add pet
		// reading the request body and parsing it into a Pet object from the JSON string
		// Pet pet  = objMapper.readValue(req.getInputStream(), Pet.class);
		
		// adminServ.addPet(pet);
		// System.out.println(pet);
	}
}
