package com.scj.ecommerce_b.Model;

public class PaymentDetails {
    
    private String paymentMethod;
    private String status;
    private String paymentId;
    private String razorpay_payment_linkId;
    private String razorpay_payment_link_referenceId;
    private String razorpay_payment_link_status;
    private String razorpay_payment_Id;

    public PaymentDetails(){

    }

    public PaymentDetails(String paymentMethod, String status, String paymentId, String razorpay_payment_linkId,
            String razorpay_payment_link_referenceId, String razorpay_payment_link_status, String razorpay_payment_Id) {
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.paymentId = paymentId;
        this.razorpay_payment_linkId = razorpay_payment_linkId;
        this.razorpay_payment_link_referenceId = razorpay_payment_link_referenceId;
        this.razorpay_payment_link_status = razorpay_payment_link_status;
        this.razorpay_payment_Id = razorpay_payment_Id;
    }

    

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    
    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setRazorpay_payment_linkId(String razorpay_payment_linkId) {
        this.razorpay_payment_linkId = razorpay_payment_linkId;
    }
    
    public String getRazorpay_payment_linkId() {
        return razorpay_payment_linkId;
    }

    

    public String getRazorpay_payment_link_referenceId() {
        return razorpay_payment_link_referenceId;
    }

    public void setRazorpay_payment_link_referenceId(String razorpay_payment_link_referenceId) {
        this.razorpay_payment_link_referenceId = razorpay_payment_link_referenceId;
    }

    public String getRazorpay_payment_link_status() {
        return razorpay_payment_link_status;
    }

    public void setRazorpay_payment_link_status(String razorpay_payment_link_status) {
        this.razorpay_payment_link_status = razorpay_payment_link_status;
    }

    public String getRazorpay_payment_Id() {
        return razorpay_payment_Id;
    }

    public void setRazorpay_payment_Id(String razorpay_payment_Id) {
        this.razorpay_payment_Id = razorpay_payment_Id;
    }

    

}
