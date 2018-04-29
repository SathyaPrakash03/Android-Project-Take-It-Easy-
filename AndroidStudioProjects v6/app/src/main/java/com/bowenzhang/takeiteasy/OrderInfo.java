package com.bowenzhang.takeiteasy;

public class OrderInfo extends Thread {
    String burgerquantity;
    String chickenquantity;
    String frenchquantity;
    String onionquantity;
    String username;

    public OrderInfo() {
    }

    public String getBurgerquantity() {
        return burgerquantity;
    }

    public void setBurgerquantity(String burgerquantity) {
        this.burgerquantity = burgerquantity;
    }

    public String getChickenquantity() {
        return chickenquantity;
    }

    public void setChickenquantity(String chickenquantity) {
        this.chickenquantity = chickenquantity;
    }

    public String getFrenchquantity() {
        return frenchquantity;
    }

    public void setFrenchquantity(String frenchquantity) {
        this.frenchquantity = frenchquantity;
    }

    public String getOnionquantity() {
        return onionquantity;
    }

    public void setOnionquantity(String onionquantity) {
        this.onionquantity = onionquantity;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
