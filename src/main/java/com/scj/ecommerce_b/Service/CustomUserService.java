package com.scj.ecommerce_b.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;

import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.UserRepo;

@Service
public class CustomUserService implements UserDetailsService {

    private UserRepo userRepo;

    public CustomUserService(UserRepo u)
    {
        this.userRepo=u;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        user user=userRepo.findByEmail(username);
        if(user==null)
        {
            throw new UsernameNotFoundException("User not found");
        }

        List<GrantedAuthority> authorities=new ArrayList<>();
        return new User(user.getEmail(),user.getPassword(),authorities);
    }
    
}