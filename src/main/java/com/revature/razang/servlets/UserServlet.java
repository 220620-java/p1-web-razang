package com.revature.razang.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.razang.models.User;
import com.revature.razang.services.AccountService;
import com.revature.razang.services.AccountServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Deprecated
/**
 * not using this anymore because of the switch to Front Controller Design Pattern,
 * but keeping it as an example for those who want to use multiple servlets on p1.
 * 
 * @author Team Razang
 * @author Colby Tang
 */
public class UserServlet extends HttpServlet {
	private AccountService bankService = new AccountServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// get available account holders
		List<User> customers = bankService.viewAccountHolders();
		
		PrintWriter writer = resp.getWriter();
		
		// // the object mapper writes the pets list as a JSON string to the response body
		writer.write(objMapper.writeValueAsString(customers));
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Add Customer
		// reading the request body and parsing it into a Pet object from the JSON string
		// Pet pet  = objMapper.readValue(req.getInputStream(), Pet.class);
		
		// adminServ.addPet(pet);
		// System.out.println(pet);
	}
}
