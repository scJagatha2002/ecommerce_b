package com.scj.ecommerce_b.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Model.Order;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.UserRepo;
import com.scj.ecommerce_b.Service.UserService;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;
    

    
   
    
    @GetMapping("/profile")
    public ResponseEntity<user> UserProfile(@RequestHeader("Authorization") String jwt) throws UserException{
        user u = userService.findUserByJwt(jwt);
        return new ResponseEntity<>(u,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity <List<user>> getAllUsersHandler() throws UserException{
        List<user> users = userService.getAllUsers();
        return new ResponseEntity<List<user>>(users,HttpStatus.ACCEPTED);
    }

    @GetMapping("/email/{email}")
    public ResponseEntity <user> getUserByEmailHandler(@PathVariable String email) throws UserException{
        user u = userService.AlreadyExist(email);
        if (u != null) {
            return new ResponseEntity<user>(u, HttpStatus.ACCEPTED);
        } else {
            // Return a 404 Not Found response when no user is found
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
