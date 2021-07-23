package com.example.storecode_android.entidades;

public class ProductoCarrito {
    Integer idProducto;
    Integer idCarrito;
    Integer cantidadProducto;
    public ProductoCarrito(){

    }
    public ProductoCarrito(Integer idProducto, Integer idCarrito, Integer cantidadProducto) {
        this.idProducto = idProducto;
        this.idCarrito = idCarrito;
        this.cantidadProducto = cantidadProducto;
    }



    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getCantidadProducto() {
        return cantidadProducto;
    }

    public void setCantidadProducto(Integer cantidadProducto) {
        this.cantidadProducto = cantidadProducto;
    }

    @Override
    public String toString() {
        return "{" +
                "idProducto=" + idProducto +
                ", idCarrito=" + idCarrito +
                ", cantidadProducto=" + cantidadProducto +
                '}';
    }




    /*
    {
    "idProducto":36,
    "idCarrito": 3,
    "cantidadProducto":2
}
     */

}
