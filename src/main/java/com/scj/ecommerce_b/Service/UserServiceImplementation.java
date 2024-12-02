package com.scj.ecommerce_b.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Jwt.JWTProvider;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.UserRepo;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepo user_repo;
    @Autowired
    private JWTProvider jwt_provider;



    @Override
    public user findUserById(Long userId) throws UserException {
        Optional<user> user = user_repo.findById(userId);
        if (user.get() == null) {
            throw new UserException("User not found");
        } else {
            return user.get();
        }
    }

    @Override
    public user findUserByJwt(String Jwt) throws UserException {
        String email = jwt_provider.getEmail(Jwt);
        user user = user_repo.findByEmail(email);
        if (user == null) {
            throw new UserException("User not found");
        } else {
            return user;
        }
    }

    @Override
    public List<user> getAllUsers() throws UserException {
        return user_repo.get_all_users();
    }

    @Override
    public user AlreadyExist(String Email) throws UserException {
        return user_repo.findByEmail(Email);
    }

}
