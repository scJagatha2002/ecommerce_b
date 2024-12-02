package com.scj.ecommerce_b.Model;

import jakarta.persistence.Column;




public class PaymentInformation {
    
    @Column(name="cardholder_name")
    private String cardholderName;

    @Column(name="card_no")
    private String cardNumber;

    @Column(name="expiration_Date")
    private String expirationDate;

    @Column(name="cvv")
    private String cvv;

    public PaymentInformation() {
    }

    public PaymentInformation(String cardHolderName, String cardNumber, String expirationDate, String cvv) {
        this.cardholderName = cardHolderName;
        this.cardNumber = cardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardholderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardholderName = cardHolderName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
