package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.adapters.ModeloAdapterNotificaciones;
import com.example.storecode_android.view.adapters.ModeloAdapterPurchase;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleCompraFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleCompraFragment extends Fragment {

    NotificationToDevice notification;

    TextView tvVendedor;

    LinearLayout layout_notificaciones, layout_fondo_notificaciones_recycler, contenedor_principal;

    RecyclerView recyclerView;



    public DetalleCompraFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleCompraFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleCompraFragment newInstance(String param1, String param2) {
        DetalleCompraFragment fragment = new DetalleCompraFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detalle_compra,container,false);
        // Inflate the layout for this fragment

        try{

            //layout_notificaciones = view.findViewById(R.id.layout_fondo_notificaciones);
            contenedor_principal = view.findViewById(R.id.contenedor_principal);
            recyclerView = view.findViewById(R.id.rv_detallecompra_items);


            contenedor_principal.setBackgroundColor(getActivity().getColor(R.color.blanco));

            String notification = SharedPref.obtenerNotificacionCompra(getContext());

            NotificationToDevice notificationToDevice = new Gson().fromJson(notification, NotificationToDevice.class);

            String purchaseProduct= notificationToDevice.getItems();
            System.out.println("-------------purchase product-----------------------");

            System.out.println(purchaseProduct);

            Type typeList = new TypeToken<List<ReqItemProduct>>(){}.getType();
            ArrayList<ReqItemProduct> purchaseProductList= new Gson().fromJson(purchaseProduct, typeList);

            recyclerView.setVisibility(View.VISIBLE);
            recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
            ModeloAdapterPurchase adapter = new ModeloAdapterPurchase(purchaseProductList, getActivity(), recyclerView);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapter);

        }catch (Exception e){
            e.printStackTrace();
        }
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvVendedor =view.findViewById(R.id.tvVendedor);


        tvVendedor.setText("Nombre del vendedor");

    }
}