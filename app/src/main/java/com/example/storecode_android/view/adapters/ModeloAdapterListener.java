package com.example.storecode_android.view.adapters;


import com.example.storecode_android.entidades.RespGetProductByUser;

public interface ModeloAdapterListener {
    public void OnProductClicked(RespGetProductByUser producto, int position);
}
