package com.revature.razang.services;

import java.sql.SQLException;
import java.util.List;

import com.revature.razang.exceptions.ObjectAlreadyExistsException;
import com.revature.razang.exceptions.RecordNotFound;
import com.revature.razang.exceptions.UsernameAlreadyExistsException;
import com.revature.razang.models.User;

public interface UserService {
    public User loginUser (String username, String password);
    public User registerUser (User user) throws UsernameAlreadyExistsException, SQLException, ObjectAlreadyExistsException;
    public User findUserById (User user);
    public User findUserByUsername(User user);
    public User updateUser (User user) throws RecordNotFound;
    public User deleteUser (User user) throws RecordNotFound;
    public List<User> getAllusers ();
}
