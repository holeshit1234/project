package com.example.project.model;

import java.util.Date;

public class Order {
    private int idBill;
    private double priceTotal;
    private String dateBill;
    private String nameUser;
    private String idMasterCard;

    public Order(int idBill, double priceTotal, String dateBill, String nameUser, String idMasterCard) {
        this.idBill = idBill;
        this.priceTotal = priceTotal;
        this.dateBill = dateBill;
        this.nameUser = nameUser;
        this.idMasterCard = idMasterCard;
    }

    public int getIdBill() {
        return idBill;
    }

    public void setIdBill(int idBill) {
        this.idBill = idBill;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    public String getDateBill() {
        return dateBill;
    }

    public void setDateBill(String dateBill) {
        this.dateBill = dateBill;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getIdMasterCard() {
        return idMasterCard;
    }

    public void setIdMasterCard(String idMasterCard) {
        this.idMasterCard = idMasterCard;
    }
}
