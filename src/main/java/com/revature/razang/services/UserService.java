package com.revature.razang.services;

import java.util.List;

import com.revature.razang.exceptions.UsernameAlreadyExistsException;
import com.revature.razang.models.User;

public interface UserService {
    public User loginUser (String username, String password);
    public User registerUser (User user) throws UsernameAlreadyExistsException;
    public User findUserById (int id);
    public User findUserByUsername (String username);
    public User updateUser (User user);
    public User deleteUser (User user);
    public List<User> getAllusers ();
}
