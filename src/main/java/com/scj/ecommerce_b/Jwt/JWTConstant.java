package com.scj.ecommerce_b.Jwt;

import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JWTConstant {

    
    public static final String JWT_HEADER="Authorization";
    //public static final SecretKey JWT_KEY=Keys.secretKeyFor(SignatureAlgorithm.HS256);
    
    public static final String JWT_KEY="bU8HBduVqfJlkdZqZ9TpUthiaNHL5w1iQDJMObWtCpU=";
    //public static byte[] decodedKey = Base64.getDecoder().decode("bU8HBduVqfJlkdZqZ9TpUthiaNHL5w1iQDJMObWtCpU=");

        
    //public static final SecretKey JWT_KEY = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
}