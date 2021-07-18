package com.example.storecode_android.view.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.storecode_android.Presenter.CarritoPresenter;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.fragments.ProductDetailFragment;
import com.squareup.picasso.Picasso;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class ProductsInCartAdapter extends RecyclerView.Adapter<HolderProductsInCart> implements Filterable {

    private static final Logger log = LogFile.getLogger(ModeloAdapterInstaladas.class);


    private final ArrayList<ProductInCard> modeloList = new ArrayList<ProductInCard>();

    private Double subtotal=0.0;

    private FragmentActivity context;

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
        subtotal =subtotal+ producto.getPrecioUnitarioProducto();

        holder.tvNameOnCart.setText(producto.getNombreProducto());
        holder.tvDescriptionOnCart.setText(producto.getDesProducto());
        holder.tvPriceOnCart.setText("$ "+producto.getPrecioUnitarioProducto().toString());
        holder.tvStockOnCart.setText("Cantidad: ");

        ArrayList<Double> items= ProductDetailFragment.getArrayItems(4.0);
        //adapter
        SpinnerAdapter customAdapter = new SpinnerAdapter(context,R.layout.custom_spinner_adapter,items);
        holder.spinner_cantidad.setAdapter(customAdapter);
        //Manejar la lógica de visibilidad del boton para pagar
        //holder.btnPayment.setVisibility(View.VISIBLE);
        try{
            if(producto.getIdUsuario()!=modeloList.get(position+1).getIdUsuario()){
                System.out.println("----SUBTOTAL-----");
                System.out.println(subtotal);
                holder.tvSubtotal.setVisibility(View.VISIBLE);
                holder.tvSubtotal.setText("Subtotal: $"+subtotal.toString());
                holder.btnPayment.setVisibility(View.VISIBLE);
                holder.btnPayment.setOnClickListener(v->{
                    Double amount= Double.parseDouble(holder.tvSubtotal.getText().toString().substring(11));
                    System.out.println("El subtotal es: "+amount);
                });


                subtotal=0.0;
            }

        }catch (IndexOutOfBoundsException e){
            e.printStackTrace();
            System.out.println("----SUBTOTAL-----");
            System.out.println(subtotal);
            holder.tvSubtotal.setVisibility(View.VISIBLE);
            holder.tvSubtotal.setText("Subtotal: $"+subtotal.toString());

            holder.btnPayment.setVisibility(View.VISIBLE);
            holder.btnPayment.setOnClickListener(v->{
                Double amount= Double.parseDouble(holder.tvSubtotal.getText().toString().substring(11));
                System.out.println("El subtotal es: "+amount);
            });



            subtotal=0.0;
        }

        //Eliminar un producto del carrito
        holder.btnDeleteFromCart.setOnClickListener(v->{
            Integer idUser = Integer.parseInt(SharedPref.obtenerIdUsuario(context));
            carritoPresenter.deleteProductFromCart(producto.getIdproductocarrito().toString());
            carritoPresenter.refreshProductsInCart(idUser.toString());
        });

        Picasso.get().load(producto.getImagenProducto()).into(holder.ivProductOnCart);
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
}
