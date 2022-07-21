package com.revature.razang.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.razang.data.UserDAO;
import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.models.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    @Override
    public User loginUser(String username, String password) {
        // Check user from the database
        User user = new User();
        user.setUsername(username);
        user = userDAO.findByUsername(user);
        System.out.println("User found! " + user.getUsername());
        if (user != null) {
            boolean isPasswordValid = userDAO.validatePassword(user, password);
            if (isPasswordValid) {
                return user;
            }
        }
        System.out.println("Password is not correct!");
        return null;
    }

    @Override
    public User registerUser(User user) {
        try {
            user.setEncryptedPassword();
            return userDAO.create(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserById(int id) {
        try {
            return userDAO.findById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public User updateUser(User user) {
        return userDAO.update(user);
    }

    @Override
    public User deleteUser(User user) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<User> getAllusers() {
        return userDAO.findAll();
    }
    
}
