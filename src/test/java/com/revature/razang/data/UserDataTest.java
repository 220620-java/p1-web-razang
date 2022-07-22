package com.revature.razang.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.User;
import com.revature.razang.utilities.WebUtils;

//@Disabled("Disabled until I mock everything")
@ExtendWith(MockitoExtension.class)
class UserDataTest {

	//Unit under test
	@InjectMocks 
	private UserDAO userDAO = new UserDAOImpl(); 
	
	//dependency
	@Mock
	private User customer;
	
	@Mock
	private WebUtils webUtil; 
	
	@Test
	public void testCreateCustomerSuccessfully() {
		User user = new User(3, "ctang2", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		User createdCustomer;
		try {
			createdCustomer = userDAO.create(user);
			assertEquals(user, createdCustomer);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void testCreateCustomerFailed() {
		customer = new User(); 
		try {
			assertNull(userDAO.create(customer));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	@Test
	public void testFindByUsername() {
		String username = "ctang";
		User user = new User();
		user.setUsername(username);
		User customer = userDAO.findByUsername(user); 
		assertNotNull(customer); 
	}
	@Test
	public void testFindByUsernameFailed() {
		customer.setUsername("Trump");
		customer = userDAO.findByUsername(customer); 
		assertNull(customer);
	}
	
	@Test
	public void testUpdateSuccessfully() {
		User user = new User(3, "ctangUPDATE", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		try {
			userDAO.update(user);
		} catch (RecordNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	public void testUpdateFailed() {
//		customer = new User(); 
//		try {
//			assertNull(userDAO.update(customer));
//		} catch (RecordNotFound e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
//	}
	
	@Test
	public void testDeleteCustomerSuccessfully() {
		User user = new User(3, "ctangUPDATE", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		try {
			assertEquals(user, userDAO.delete(user));
		} catch (RecordNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test 
	public void testDeleteCustomerFailed ()
	{
		User user = new User(100, "ctangUPDATE", new Date(Date.valueOf("1994-03-31").getTime()),
				"ctang@gmail.com", "5121231234", "pass");
		try {
			assertNull(userDAO.delete(user));
		} catch (RecordNotFound e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	@Test
	public void getAllUsers () {
		List<User> allUsers = userDAO.findAll();
		allUsers.stream().forEach(user -> System.out.println(user));
		assertNotNull(allUsers);
	}
	
	@Test 
	public void testFindByIdSuccessfully() throws SQLException {
		customer = new User(); 
		customer.setUserId(2);
		customer = userDAO.findById(customer);
		assertNotNull(customer);
	}
	@Test
	public void testFindByIdFfailed() throws SQLException {
		customer = new User(); 
		customer.setUserId(100);
		assertNull(userDAO.findById(customer)); 
	}
	
	@Test
	public void testValidatePasswordSuccessfully() {
		
		String passwd = "123456"; 
		String returnPass = "123456"; 
		byte[] salt = {'a','b', '2','5'}; 
		webUtil = new WebUtils(); 
		Mockito.when(customer.getPassword()).thenReturn(passwd); 
		Mockito.when(customer.getSalt()).thenReturn(salt);
		Mockito.when(webUtil.generateEncryptedPassword(passwd, salt)).thenReturn(returnPass); 
		assertTrue(userDAO.validatePassword(customer, passwd));
	}
	@Test	
	public void testValidatePasswordFailed() {
		
		String passwd = "123456"; 
		byte[] salt = {'a','b', '2','5'}; 
		Mockito.when(customer.getPassword()).thenReturn(passwd); 
		Mockito.when(customer.getSalt()).thenReturn(salt);
		Mockito.when(webUtil.generateEncryptedPassword(passwd, salt)).thenReturn("abcabc"); 
		assertFalse(userDAO.validatePassword(customer, passwd));
	}




}
