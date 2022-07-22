package com.revature.razang.delegates;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.Account;
import com.revature.razang.models.User;
import com.revature.razang.services.AccountService;
import com.revature.razang.services.AccountServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * @author Colby Tang
 */
public class AccountDelegate implements FrontControllerDelegate {
	private AccountService accountService = new AccountServiceImpl();
	private ObjectMapper objMapper = new ObjectMapper();

	/** Handle the request through its verbs
	 * 
	 */
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
			resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
		}
	}

	/**
	 * Get all accounts /accounts
	 * Get a specific account /accounts/{id}
	 * @param req
	 * @param resp
	 * @throws ServletException
	 * @throws IOException
	 * @author Colby Tang
	 */
	public void get(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			List<Account> accounts = accountService.getAllAccounts();
			resp.setStatus(200);
			resp.setContentType("application/json");

			// the object mapper writes the pets list as a JSON string to the response body
			resp.getWriter().write(objMapper.writeValueAsString(accounts));
		} else {
			try {
				int id = Integer.valueOf(path);
				Account account = new Account();
				account.setAccountNumber(id);

				account = accountService.getAccountById(account);
				if (account != null) {
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.getWriter().write(objMapper.writeValueAsString(account));
				} else {
					resp.sendError(404, "Account with that ID not found.");
				}
			} catch (NumberFormatException e) {
				resp.sendError(400, e.getMessage());
			}
		}
	}

	// Create an account with the request body
	public void post(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		if (path==null || "".equals(path)) {
			try {
				Account account = objMapper.readValue(req.getInputStream(), Account.class);
				if (account!=null) {
					account = accountService.createAccount(account);
					resp.setStatus(200);
					resp.setContentType("application/json");
					resp.getWriter().write(objMapper.writeValueAsString(account));
				} else {
					resp.sendError(400, "The request body was empty.");
				}
			} catch (IOException e) {
				resp.sendError(400, "Error reading the body. " + e.toString());
			}
		} else {
			if (path.equals("user")) {
				try {
					Map<String, String> credentials = objMapper.readValue(req.getInputStream(), Map.class);
					if (credentials.containsKey("userid") && credentials.containsKey("accountNumber")) {
						Account acc = new Account();
						acc.setAccountNumber(Long.valueOf(credentials.get("accountNumber")));
						User user = new User();
						user.setUserId(Integer.parseInt(credentials.get("userid")));
						try {
							accountService.setAccountUser(acc, user);
						} catch (RecordNotFound | SQLException e) {
							resp.sendError(400, e.toString());
							return;
						}
						resp.setStatus(200);
						resp.setContentType("application/json");
						// resp.getWriter().write(objMapper.writeValueAsString(acc));
					}
					else {
						resp.sendError(400, "No userid or accountNumber in the body. Try sending a JSON object with a userid/accountNumber field.");
					}
				} catch (IOException e) {
					resp.sendError(400, "Error reading the body. " + e.toString());
				}
			}
			else {
				resp.sendError(400, "Cannot POST to this URI. Try sending the request to /accounts");
			}
		}
	}
	
	public void put(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
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
	};

	public void delete (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
	}
}
