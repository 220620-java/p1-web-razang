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
        boolean isPasswordValid = userDAO.validatePassword(username, password);
        if (isPasswordValid) {
            User user = new User();
            user.setUsername(username);
            return userDAO.findByUsername(user);
        }
        System.out.println("Password is not correct!");
        return null;
    }

    @Override
    public User registerUser(User user) {
        try {
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
        // TODO Auto-generated method stub
        return null;
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
