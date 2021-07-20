package com.example.storecode_android.entidades;

public class ReqItemProduct {

    String description;
    Double price;
    Integer quantity;

    public ReqItemProduct(){

    }

    public ReqItemProduct(String description, Double price, Integer quantity) {
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }


    /*
    {
    "description":"Audifonos",
    "price":100.0,
    "quantity": 2
}
     */
}
