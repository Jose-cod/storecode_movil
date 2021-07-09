package com.example.storecode_android.entidades;

import android.net.Uri;

public class Producto {
    Uri image;
    String nombreProducto;
    String desProducto;
    Double precioUnitario;
    Double cantidadProducto;
    Integer marca;
    Integer categoria;
    Integer idUsuario;

    public Producto(){

    }

    public Producto(Uri image, String nombreProducto, String desProducto, Double precioUnitario, Double cantidadProducto, Integer marca, Integer categoria, Integer idUsuario) {
        this.image = image;
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.precioUnitario = precioUnitario;
        this.cantidadProducto = cantidadProducto;
        this.marca = marca;
        this.categoria = categoria;
        this.idUsuario = idUsuario;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public String getDesProducto() {
        return desProducto;
    }

    public void setDesProducto(String desProducto) {
        this.desProducto = desProducto;
    }

    public Double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(Double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public Double getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Double cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    public Integer getMarca() {
        return marca;
    }

    public void setMarca(Integer marca) {
        this.marca = marca;
    }

    public Integer getCategoria() {
        return categoria;
    }

    public void setCategoria(Integer categoria) {
        this.categoria = categoria;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Override
    public String toString() {
        return "{" +
                "image=" + image +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", desProducto='" + desProducto + '\'' +
                ", precioUnitario=" + precioUnitario +
                ", cantidadProducto=" + cantidadProducto +
                ", marca=" + marca +
                ", categoria=" + categoria +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
