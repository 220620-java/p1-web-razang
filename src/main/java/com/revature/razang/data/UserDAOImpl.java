package com.revature.razang.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.razang.models.User;
import com.revature.razang.utilities.WebUtils;
import com.revature.razangorm.orm.ObjectRelationalMapper;
import com.revature.razangorm.orm.ObjectRelationalMapperImpl;

public class UserDAOImpl implements UserDAO {
	List<User> customers = new ArrayList<>();
	ObjectRelationalMapper orm = new ObjectRelationalMapperImpl();
	
	/** 
	 * @param user
	 * @return User
	 */
	@Override
	public User findByUsername(User user) {
		User foundUser = (User) orm.findByName(user, "bank.users");
		return foundUser;
	}

	
	/** 
	 * @param user
	 * @return User
	 * @throws SQLException
	 */
	@Override
	public User create(User user) throws SQLException {
		User createdUser = (User) orm.create(user, "bank.users");
		return createdUser;
	}

	
	/** 
	 * @param user
	 * @return User
	 */
	@Override
	public User findById(User user) {
		User foundUser = (User) orm.findById(user, "bank.users");
		return foundUser;
	}

	/** 
	 * @return List<User>
	 */
	@Override
	public List<User> findAll() {
		List<Object> retrievedObjects = orm.findAll(User.class, "bank.users");
		List<User> createdUsers = retrievedObjects.stream().map(user -> (User)user).collect(Collectors.toList());
		return createdUsers;
	}

	
	/** 
	 * @param t
	 * @return User
	 */
	@Override
	public User update(User t) {
		User createdUser = (User) orm.update(t, "bank.users");
		return createdUser;
	}

	
	/** 
	 * @param t
	 * @return User
	 */
	@Override
	public User delete(User t) {
		return (User) orm.delete(t, "bank.users");
		
	}

	
	/** 
	 * @param user
	 * @param password
	 * @return boolean
	 */
	@Override
	public boolean validatePassword(User user, String password) {
		String dbPass = user.getPassword();
		byte[] dbSalt = user.getSalt();
		if (dbPass == null) {
			System.out.println("Password or salt is empty!");
			return false;
		}
		else if (dbSalt != null) {
			password = WebUtils.generateEncryptedPassword(password, dbSalt);
		}

		return password.equals(dbPass);
	}

}
