package com.example.storecode_android.entidades;

public class Venta {

    String claveTransaccion;
    String paypalDatos;
    String correo;
    Double totalVendido;

    public Venta() {
    }

    public Venta(String claveTransaccion, String paypalDatos, String correo, Double totalVendido) {

        this.claveTransaccion = claveTransaccion;
        this.paypalDatos = paypalDatos;
        this.correo = correo;
        this.totalVendido = totalVendido;
    }

    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public void setClaveTransaccion(String claveTransaccion) {
        this.claveTransaccion = claveTransaccion;
    }

    public String getPaypalDatos() {
        return paypalDatos;
    }

    public void setPaypalDatos(String paypalDatos) {
        this.paypalDatos = paypalDatos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    @Override
    public String toString() {
        return "{" +
                "claveTransaccion='" + claveTransaccion + '\'' +
                ", paypalDatos='" + paypalDatos + '\'' +
                ", correo='" + correo + '\'' +
                ", totalVendido=" + totalVendido +
                '}';
    }

    /*
    {
    "claveTransaccion": "hdadh222",
    "paypalDatos":"usdhsaudhsa",
    "correo" : "chend@gmail.com",
    "totalVendido" : 200
}
     */
}
