package com.revature.razang.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.razang.data.UserDAO;
import com.revature.razang.data.UserDAOImpl;
import com.revature.razang.exceptions.ObjectAlreadyExistsException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.models.User;

public class UserServiceImpl implements UserService {
    private UserDAO userDAO = new UserDAOImpl();

    /** 
     * @param username
     * @param password
     * @return User
     */
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

    
    /** 
     * @param user
     * @return User
     * @throws SQLException
     * @throws ObjectAlreadyExistsException
     */
    @Override
    public User registerUser(User user) throws SQLException, ObjectAlreadyExistsException {
        try {
            user.setEncryptedPassword();
            return userDAO.create(user);
        } catch (SQLException | ObjectAlreadyExistsException e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    /** 
     * @param user
     * @return User
     */
    @Override
    public User findUserById(User user) {
        try {
            return userDAO.findById(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    /** 
     * @param user
     * @return User
     */
    @Override
    public User findUserByUsername(User user) {
        return userDAO.findByUsername(user);
    }

    
    /** 
     * @param user
     * @return User
     * @throws RecordNotFound
     */
    @Override
    public User updateUser(User user) throws RecordNotFound {
        try {
            return userDAO.update(user);
        } catch (RecordNotFound e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    /** 
     * @param user
     * @return User
     * @throws RecordNotFound
     */
    @Override
    public User deleteUser(User user) throws RecordNotFound {
        try {
            return userDAO.delete(user);
        } catch (RecordNotFound e) {
            e.printStackTrace();
            throw e;
        }
    }

    
    /** 
     * @return List<User>
     */
    @Override
    public List<User> getAllusers() {
        return userDAO.findAll();
    }
    
}
