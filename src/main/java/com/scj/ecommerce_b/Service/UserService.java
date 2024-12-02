package com.scj.ecommerce_b.Service;



import java.util.List;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.user;


public interface UserService {;
    
    public user findUserById(Long userId) throws UserException;
    public user findUserByJwt(String Jwt) throws UserException;
    public List<user> getAllUsers() throws UserException;
    public user AlreadyExist(String Email) throws UserException;
    
    
}
