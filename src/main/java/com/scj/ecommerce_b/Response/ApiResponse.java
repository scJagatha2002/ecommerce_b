package com.scj.ecommerce_b.Response;

public class ApiResponse {

    private String message;
    private boolean Status;

    public ApiResponse() {
    }

    public ApiResponse(String message, boolean status) {
        super();
        this.message = message;
        Status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }

    

    
    
}
