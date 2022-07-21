package com.revature.razang.data;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

	@Override
	public User create(User user) throws SQLException {
		User createdUser = (User) orm.create(user, "bank.users");
		return createdUser;
	}

	@Override
	public User findById(int id) {
		User foundUser = (User) orm.findByName(id, "bank.users");
		return foundUser;
	}

	@Override
	public List<User> findAll() {
		List<Object> retrievedObjects = orm.findAll(User.class, "users");
		List<User> createdUsers = retrievedObjects.stream().map(user -> (User)user).toList();
		return createdUsers;
	}

	@Override
	public void update(User t) {
		User createdUser = (User) orm.update(t, "users");
	}

	@Override
	public void delete(User t) {
		orm.delete(t, "users");
	}

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
