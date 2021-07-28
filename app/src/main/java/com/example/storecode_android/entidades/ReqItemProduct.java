package com.example.storecode_android.entidades;

public class ReqItemProduct {

    Integer idProducto;
    Integer idProductoCarrito;
    Integer idCarrito;
    Integer idVendedor;
    String description;
    Double price;
    Integer quantity;
    String clientEmail;

    public ReqItemProduct(){

    }

    public ReqItemProduct(Integer idProducto, Integer idProductoCarrito, Integer idCarrito, Integer idVendedor, String description, Double price, Integer quantity, String clientEmail) {
        this.idProducto = idProducto;
        this.idProductoCarrito = idProductoCarrito;
        this.idCarrito = idCarrito;
        this.idVendedor = idVendedor;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.clientEmail = clientEmail;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdProductoCarrito() {
        return idProductoCarrito;
    }

    public void setIdProductoCarrito(Integer idProductoCarrito) {
        this.idProductoCarrito = idProductoCarrito;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
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
                "idProducto=" + idProducto +
                ", idProductoCarrito=" + idProductoCarrito +
                ", idCarrito=" + idCarrito +
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
