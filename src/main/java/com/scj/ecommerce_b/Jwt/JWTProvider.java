package com.scj.ecommerce_b.Jwt;

import java.security.Key;
import java.util.Base64;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTProvider {

    private Key key;
    private byte[] decodedKey;

    

    public JWTProvider() {
        //this.decodedKey = Base64.getDecoder().decode(JWTConstant.JWT_KEY);
        //this.key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
        this.decodedKey = Decoders.BASE64.decode(JWTConstant.JWT_KEY);
        this.key=Keys.hmacShaKeyFor(decodedKey);
   
    }

    
    public String generateToken(Authentication authentication) {
        long expirationTimeMillis = System.currentTimeMillis() + 900000;
        String jwt = Jwts.builder()
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTimeMillis))
                .claim("email", authentication.getName())
                .signWith(key,SignatureAlgorithm.HS256).compact();
        return jwt;
    }

    public String getEmail(String jwt) {
        jwt = jwt.substring(7);
        Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();
        String email = String.valueOf(claims.get("email"));
        return email;
    }
}
