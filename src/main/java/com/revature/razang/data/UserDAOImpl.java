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
		if (findById(user) != null) {
			throw new UsernameAlreadyExistsException();
		}
		User createdUser = (User) orm.create(user, "bank.users");
		return createdUser;
	}

	@Override
	public User findById(int id) {
		User foundUser = (User) orm.findById(id, "bank.users");
		return foundUser;
	}

	@Override
	public List<User> findAll() {
		List<Object> retrievedObjects = orm.findAll(User.class, "users");
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

	@Override
	public User update(User t) {
		User createdUser = (User) orm.update(t, "users");
		return createdUser;
	}

	@Override
	public void delete(User t) {
		orm.delete(t, "users");

	}

	@Override
	public boolean validatePassword(User user, String password) {
		String dbPass = user.getPassword();
		byte[] dbSalt = user.getSalt();
		String ePass = WebUtils.generateEncryptedPassword(password, dbSalt);

		if (!ePass.equals(dbPass)) {
			System.out.println("Passwords do not match! ");
			return false;
		}
		return ePass.equals(dbPass);
	}

}
