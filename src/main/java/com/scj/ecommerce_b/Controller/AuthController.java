package com.scj.ecommerce_b.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.scj.ecommerce_b.Exceptions.UserException;
import com.scj.ecommerce_b.Jwt.JWTProvider;
import com.scj.ecommerce_b.Model.Cart;
import com.scj.ecommerce_b.Model.user;
import com.scj.ecommerce_b.Repository.UserRepo;
import com.scj.ecommerce_b.Request.LoginRequest;
import com.scj.ecommerce_b.Response.AuthResponse;
import com.scj.ecommerce_b.Service.CartService;
import com.scj.ecommerce_b.Service.CustomUserService;



@RestController
@RequestMapping("/auth")


public class AuthController {

    private UserRepo userrepo;
    private JWTProvider jwtProvider;
    private PasswordEncoder passwordEncoder;
    private CustomUserService customUserService;
    private CartService cartService;

    public AuthController(UserRepo u,JWTProvider j,PasswordEncoder p,CustomUserService c,CartService cartService)
    {
        this.userrepo=u;
        this.jwtProvider=j;
        this.passwordEncoder=p;
        this.customUserService=c;
        this.cartService=cartService;
    }

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse>createUserHandler(@RequestBody user u) throws UserException{

        String email=u.getEmail();
        String password=u.getPassword();
        String firstname=u.getFirstName();
        String lastname=u.getLastName();
        String role = u.getRole();
        String mobile = u.getMobile();

        user isEmailExist=userrepo.findByEmail(email);
        

        if(isEmailExist!=null)
        {
            throw new UserException("Email is already used");
        }

        user createdUser=new  user();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFirstName(firstname);
        createdUser.setLastName(lastname);
        createdUser.setRole(role);
        createdUser.setMobile(mobile);

        user saveduser=userrepo.save(createdUser);
        Cart cart = cartService.CreateCart(saveduser);

        Authentication authentication=new UsernamePasswordAuthenticationToken(saveduser.getEmail(),saveduser.getPassword());    //Creates an authentication token
        SecurityContextHolder.getContext().setAuthentication(authentication);                                                   //Sets it for authentication token for authentication
        String token=jwtProvider.generateToken(authentication);

        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setRole(role);
        authResponse.setMessage("SignUp Successful");
        
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse>LoginHandler(@RequestBody LoginRequest loginRequest) throws UserException {
        String username=loginRequest.getEmail();
        String password=loginRequest.getPassword();
        Authentication authentication=authenticate(username,password);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token=jwtProvider.generateToken(authentication);
        AuthResponse authResponse=new AuthResponse();
        authResponse.setJwt(token);
        authResponse.setMessage("Login Successful");
        return new ResponseEntity<AuthResponse>(authResponse,HttpStatus.CREATED);
    }


    private Authentication authenticate(String username, String password) {
        UserDetails userDetails=customUserService.loadUserByUsername(username);
        if(userDetails==null)
        {
            throw new BadCredentialsException("Invalid UserName");
        }
        if(!passwordEncoder.matches(password, userDetails.getPassword()))
        {
            throw new BadCredentialsException("Invalid Password");
        }
        return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
    }
}