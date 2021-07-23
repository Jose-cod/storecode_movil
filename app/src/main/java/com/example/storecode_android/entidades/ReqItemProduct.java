package com.example.storecode_android.entidades;

public class ReqItemProduct {

    Integer idProductoCarrito;
    Integer idVendedor;
    String description;
    Double price;
    Integer quantity;
    String clientEmail;

    public ReqItemProduct(){

    }

    public ReqItemProduct(Integer idProductoCarrito, Integer idVendedor, String description, Double price, Integer quantity, String clientEmail) {
        this.idProductoCarrito = idProductoCarrito;
        this.idVendedor = idVendedor;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.clientEmail = clientEmail;
    }

    public Integer getIdProductoCarrito() {
        return idProductoCarrito;
    }

    public void setIdProductoCarrito(Integer idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public Integer getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(Integer idVendedor) {
        this.idVendedor = idVendedor;
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

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    @Override
    public String toString() {
        return "{" +
                "idProductoCarrito=" + idProductoCarrito +
                ", idVendedor=" + idVendedor +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", clientEmail='" + clientEmail + '\'' +
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
