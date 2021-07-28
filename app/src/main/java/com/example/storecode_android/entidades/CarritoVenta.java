package com.example.storecode_android.entidades;

public class CarritoVenta {
    Integer idCarrito;
    Integer FolioVenta;

    public CarritoVenta() {
    }

    public CarritoVenta(Integer idCarrito, Integer folioVenta) {
        this.idCarrito = idCarrito;
        FolioVenta = folioVenta;
    }

    public Integer getIdCarrito() {
        return idCarrito;
    }

    public void setIdCarrito(Integer idCarrito) {
        this.idCarrito = idCarrito;
    }

    public Integer getFolioVenta() {
        return FolioVenta;
    }

    public void setFolioVenta(Integer folioVenta) {
        FolioVenta = folioVenta;
    }

    @Override
    public String toString() {
        return "{" +
                "idCarrito=" + idCarrito +
                ", FolioVenta=" + FolioVenta +
                '}';
    }

    /*
    {
    "idCarrito": 8,
    "FolioVenta": 84
}
     */
}
