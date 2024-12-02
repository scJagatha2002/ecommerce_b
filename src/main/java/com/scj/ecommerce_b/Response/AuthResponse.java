package com.scj.ecommerce_b.Response;

public class AuthResponse {

    private String jwt;
    private String message;
    private String role;

    public AuthResponse(){
        
    }

    public AuthResponse(String jwt,String message,String role){
        
        this.jwt=jwt;
        this.message=message;
        this.role = role;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    public String getMessage() {
        return message;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}