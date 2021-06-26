package com.example.storecode_android.entidades;

import java.util.Date;

public class RespObtenerProducto {
    private Integer idProducto;
    private String nombreProducto;
    private String desProducto;
    private double precioUnitarioProducto;
    private String imagenProducto;
    //private Date fechaAlojadoProducto;
    private double stockRealProducto;
    private String statusProducto;
    private Integer idMarca;
    private Integer idCategoria;
    private Integer idUsuario;


     /*




     {
        "idProducto": 48,
        "Nombrw": "Laptop Dell",
        "Descripcion": "Laptop Dell nuevo",
        "$ Unitario": 4200,
        "Imagen": "http://192.168.1.72:3000/public/products/IMG-20210310-WA0004.jpeg-1617939165921.jpeg",
        "Fecha": "2021-04-06T05:00:00.000Z",
        "Stock": "3.00",
        "Estatus": "Activo",
        "Marca": "SONY",
        "Categoria": "ELECTRÓNICOS"
    }
    * */


    public RespObtenerProducto(){

    }

    public RespObtenerProducto(Integer idProducto, String nombreProducto, String desProducto, double precioUnitarioProducto, String imagenProducto, Date fechaAlojadoProducto, double stockRealProducto, String statusProducto, Integer idMarca, Integer idCategoria, Integer idUsuario) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.desProducto = desProducto;
        this.precioUnitarioProducto = precioUnitarioProducto;
        this.imagenProducto = imagenProducto;
        //this.fechaAlojadoProducto = fechaAlojadoProducto;
        this.stockRealProducto = stockRealProducto;
        this.statusProducto = statusProducto;
        this.idMarca = idMarca;
        this.idCategoria = idCategoria;
        this.idUsuario = idUsuario;
    }

    public Integer getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Integer idProducto) {
        this.idProducto = idProducto;
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

    public double getPrecioUnitarioProducto() {
        return precioUnitarioProducto;
    }

    public void setPrecioUnitarioProducto(double precioUnitarioProducto) {
        this.precioUnitarioProducto = precioUnitarioProducto;
    }

    public String getImagenProducto() {
        return imagenProducto;
    }

    public void setImagenProducto(String imagenProducto) {
        this.imagenProducto = imagenProducto;
    }

    /*public Date getFechaAlojadoProducto() {
        return fechaAlojadoProducto;
    }*/

    /*public void setFechaAlojadoProducto(Date fechaAlojadoProducto) {
        this.fechaAlojadoProducto = fechaAlojadoProducto;
    }*/

    public double getStockRealProducto() {
        return stockRealProducto;
    }

    public void setStockRealProducto(double stockRealProducto) {
        this.stockRealProducto = stockRealProducto;
    }

    public String getStatusProducto() {
        return statusProducto;
    }

    public void setStatusProducto(String statusProducto) {
        this.statusProducto = statusProducto;
    }

    public Integer getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(Integer idMarca) {
        this.idMarca = idMarca;
    }

    public Integer getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Integer idCategoria) {
        this.idCategoria = idCategoria;
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
                "idProducto=" + idProducto +
                ", nombreProducto='" + nombreProducto + '\'' +
                ", desProducto='" + desProducto + '\'' +
                ", precioUnitarioProducto=" + precioUnitarioProducto +
                ", imagenProducto='" + imagenProducto + '\'' +
                //", fechaAlojadoProducto=" + fechaAlojadoProducto.toString() +
                ", stockRealProducto=" + stockRealProducto +
                ", statusProducto='" + statusProducto + '\'' +
                ", idMarca=" + idMarca +
                ", idCategoria=" + idCategoria +
                ", idUsuario=" + idUsuario +
                '}';
    }
}
