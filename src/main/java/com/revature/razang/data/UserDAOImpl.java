package com.revature.razang.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.exceptions.UsernameAlreadyExistsException;
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
	 * @throws UsernameAlreadyExistsException
	 */
	@Override
	public User create(User user) throws SQLException, UsernameAlreadyExistsException {
		if (findByUsername(user) != null) {
			throw new UsernameAlreadyExistsException();
		}
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
	 * @param user
	 * @return User
	 * @throws RecordNotFound
	 */
	@Override
	public User update(User user) throws RecordNotFound {
		if (findById(user) == null) {
			throw new RecordNotFound(user);
		}
		User createdUser = (User) orm.update(user, "bank.users");
		return createdUser;
	}

	
	/** 
	 * @param user
	 * @return User
	 * @throws RecordNotFound
	 */
	@Override
	public User delete(User user) throws RecordNotFound {
		if (findById(user) == null) {
			throw new RecordNotFound(user);
		}
		return (User) orm.delete(user, "bank.users");
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
