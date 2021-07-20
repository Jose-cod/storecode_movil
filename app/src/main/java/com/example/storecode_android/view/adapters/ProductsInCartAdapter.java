package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.fragments.ProductDetailFragment;
import com.mercadopago.android.px.core.MercadoPagoCheckout;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCartAdapter extends RecyclerView.Adapter<HolderProductsInCart> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);


    private final ArrayList<ProductInCard> modeloList = new ArrayList<ProductInCard>();

    private Double subtotal=0.0;
    private Double total= 0.0;
    public static final int REQUEST_CODE = 1;
    public static final String PUBLIC_KEY="TEST-3aa335c4-d833-47aa-b31b-638832e27e2a";

    public FragmentActivity context;

    private CarritoPresenter carritoPresenter;
    private List<ProductInCard> mFilteredList = modeloList;

    ProductPresenter productPresenter;


    public ProductsInCartAdapter(FragmentActivity context, CarritoPresenter carritoPresenter) {
        this.context = context;
        this.carritoPresenter = carritoPresenter;
    }

    @Override
    public Filter getFilter() {
        return null;
    }

    @NonNull
    @Override
    public HolderProductsInCart onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item_producs_in_cart, parent, false);

        //filtrarModelos();
        return new HolderProductsInCart(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderProductsInCart holder, int position) {
        productPresenter = new ProductPresenter();


        ProductInCard producto = modeloList.get(position);


        holder.tvNameOnCart.setText(producto.getNombreProducto());
        holder.tvDescriptionOnCart.setText(producto.getDesProducto());
        holder.tvPriceOnCart.setText("$ "+producto.getPrecioUnitarioProducto().toString());
        holder.tvStockOnCart.setText("Cantidad: ");

        ArrayList<Double> items= ProductDetailFragment.getArrayItems(producto.getStockRealProducto());
        //adapter
        SpinnerAdapter customAdapter = new SpinnerAdapter(context,R.layout.custom_spinner_adapter,items);
        holder.spinner_cantidad.setAdapter(customAdapter);
        Double cantidad = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString());
        System.out.println("Encargaste"+ cantidad+ producto.getNombreProducto());
        subtotal= producto.getPrecioUnitarioProducto()*cantidad;
        total =total+ subtotal;

        holder.tvSubtotal.setText("Subtotal $"+subtotal.toString());

        System.out.println("Total:"+ total);

        System.out.println("---------EN stock---------------");
        System.out.println("El stock es:"+producto.getStockRealProducto());

        holder.spinner_cantidad.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                /*Double cantidad = Double.parseDouble(holder.spinner_cantidad.getSelectedItem().toString());
                subtotal= producto.getPrecioUnitarioProducto()*cantidad;
                total =total+ subtotal;*/
                /*subtotal= producto.getPrecioUnitarioProducto()*cantidad;
                total =total+ subtotal;
                System.out.println("El total es:"+total);*/


                //total =0.0;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                System.out.println("No has seleccionado un item");
            }
        });

        //Manejar la lógica de visibilidad del boton para pagar
        //holder.btnPayment.setVisibility(View.VISIBLE);
        try{
            if(producto.getIdUsuario()!=modeloList.get(position+1).getIdUsuario()){
                System.out.println("----TOTAL-----");
                System.out.println(total);
                holder.tvTotal.setVisibility(View.VISIBLE);
                holder.tvTotal.setText("Total: $"+total.toString());
                holder.btnPayment.setVisibility(View.VISIBLE);
                holder.btnPayment.setOnClickListener(v->{
                    Double amount= Double.parseDouble(holder.tvTotal.getText().toString().substring(8));
                    System.out.println("El total es: "+amount);
                });


                total=0.0;
            }

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("----TOTAL-----");
            System.out.println(total);
            holder.tvTotal.setVisibility(View.VISIBLE);
            holder.tvTotal.setText("Total: $"+total.toString());

            holder.btnPayment.setVisibility(View.VISIBLE);
            holder.btnPayment.setOnClickListener(v->{
                Double amount= Double.parseDouble(holder.tvTotal.getText().toString().substring(8));
                System.out.println("El Total es: "+amount);
            });



            //total=0.0;
        }

        //Eliminar un producto del carrito
        holder.btnDeleteFromCart.setOnClickListener(v->{
            Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(context));
            carritoPresenter.deleteProductFromCart(producto.getIdproductocarrito().toString());
            carritoPresenter.refreshProductsInCart(idUser.toString());
        });

        Picasso.get().load(producto.getImagenProducto()).into(holder.ivProductOnCart);
        //
        holder.btnPayment.setOnClickListener(v->{
            carritoPresenter.createIdPreference(new ReqItemProduct(
                    "Audifonos",
                    50.0,
                    2
            ));

            String idPreference;
            idPreference= SharedPref.obtenerIdPreference(context);
            System.out.println("----------idPreference--------------");
            System.out.println("idPreference: "+idPreference);

            if(!idPreference.equals("idPreference")){
                startMercadoPagoCheckout(idPreference);
            }

        });

        
    }

    @Override
    public int getItemCount() {
        System.out.println("---------getItemCount de products in cart------------");
        System.out.println(modeloList.size());
        return modeloList.size();

    }


    public void updateData(List<ProductInCard> data) {
        System.out.println("en el update data de ProductInCart");

        modeloList.clear();
        modeloList.addAll(data);
        System.out.println(modeloList);
        notifyDataSetChanged();
    }

    //metodos de mercado pago



    public void startMercadoPagoCheckout(final String checkoutPreferenceId) {
        new MercadoPagoCheckout.Builder(PUBLIC_KEY, checkoutPreferenceId).build()
                .startPayment(context, REQUEST_CODE);
    }


}
