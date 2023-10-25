package com.example.project.model;

public class Clothes {
    private  int idClothes;
    private String nameClothes;
    private String detailsClothes;
    private int imageClothes;
    private int quantityClothes;
    private double priceClothes;

    public Clothes(int idClothes, String nameClothes, String detailsClothes, int imageClothes, int quantityClothes, double priceClothes) {
        this.idClothes = idClothes;
        this.nameClothes = nameClothes;
        this.detailsClothes = detailsClothes;
        this.imageClothes = imageClothes;
        this.quantityClothes = quantityClothes;
        this.priceClothes = priceClothes;
    }

    public int getIdClothes() {
        return idClothes;
    }

    public void setIdClothes(int idClothes) {
        this.idClothes = idClothes;
    }

    public String getNameClothes() {
        return nameClothes;
    }

    public void setNameClothes(String nameClothes) {
        this.nameClothes = nameClothes;
    }

    public String getDetailsClothes() {
        return detailsClothes;
    }

    public void setDetailsClothes(String detailsClothes) {
        this.detailsClothes = detailsClothes;
    }

    public int getImageClothes() {
        return imageClothes;
    }

    public void setImageClothes(int imageClothes) {
        this.imageClothes = imageClothes;
    }

    public int getQuantityClothes() {
        return quantityClothes;
    }

    public void setQuantityClothes(int quantityClothes) {
        this.quantityClothes = quantityClothes;
    }

    public double getPriceClothes() {
        return priceClothes;
    }

    public void setPriceClothes(double priceClothes) {
        this.priceClothes = priceClothes;
    }
}
