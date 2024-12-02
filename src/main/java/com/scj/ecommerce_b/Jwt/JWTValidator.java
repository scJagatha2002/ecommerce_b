package com.scj.ecommerce_b.Jwt;

import java.util.Base64;
import java.util.List;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.io.IOException;
import java.security.Key;


@Component
public class JWTValidator extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = request.getHeader(JWTConstant.JWT_HEADER);

        if (jwt != null && jwt.startsWith("Bearer")) {
            jwt = jwt.substring(7).trim(); 

            try {
                //SecretKey key = JWTConstant.JWT_KEY;
                byte[] decodedKey = Decoders.BASE64.decode(JWTConstant.JWT_KEY);

        
                Key key = Keys.hmacShaKeyFor(decodedKey);
               
                Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

                String email = String.valueOf(claims.get("email"));
                String authorities = String.valueOf(claims.get("authorities"));
                List<GrantedAuthority> auths=AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);
                //list<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList(authorities);

                org.springframework.security.core.Authentication authentication = new UsernamePasswordAuthenticationToken(email, null,auths);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                
                throw new BadCredentialsException("Invalid token... from JWT validator");
            }
        }

        filterChain.doFilter(request, response);
    }
}