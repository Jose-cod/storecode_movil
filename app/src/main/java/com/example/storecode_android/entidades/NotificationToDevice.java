package com.example.storecode_android.entidades;

import java.util.ArrayList;
import java.util.List;

public class NotificationToDevice extends TokenFCM {
    private String claveTransaccion;
    private Double totalVendido;
    private String items;

    public NotificationToDevice(Integer idUsuario, String tokenFCM,String claveTransaccion, Double totalVendido,String items) {
        super(idUsuario, tokenFCM);
        this.claveTransaccion = claveTransaccion;
        this.totalVendido = totalVendido;
        this.items = items;

    }

    public String getClaveTransaccion() {
        return claveTransaccion;
    }

    public void setClaveTransaccion(String claveTransaccion) {
        this.claveTransaccion = claveTransaccion;
    }

    public Double getTotalVendido() {
        return totalVendido;
    }

    public void setTotalVendido(Double totalVendido) {
        this.totalVendido = totalVendido;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "{" +
                "claveTransaccion='" + claveTransaccion + '\'' +
                ", totalVendido=" + totalVendido +
                ", items='" + items +""+ '\'' +
                ", idUsuario=" + idUsuario +
                ", tokenFCM='" + tokenFCM + '\'' +
                '}';
    }
}
