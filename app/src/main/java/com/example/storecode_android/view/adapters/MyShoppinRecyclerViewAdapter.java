package com.example.storecode_android.view.adapters;

import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespMyShopping;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyShoppinRecyclerViewAdapter extends RecyclerView.Adapter<MyShoppinRecyclerViewAdapter.ViewHolder> {

    private final ArrayList<RespMyShopping> mValues= new ArrayList();

    /*public MyShoppinRecyclerViewAdapter(ArrayList<RespMyShopping> items) {
        mValues = items;
    }*/

    public  MyShoppinRecyclerViewAdapter(){

    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item_myshopping, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);

        holder.tvClaveTransaccion.setText(holder.mItem.getClaveTransaccion());
        holder.tvNombreProducto.setText(holder.mItem.getNombreProducto());
        holder.tvDesProducto.setText(holder.mItem.getDesProducto());
        //holder.tvQuantityProducto.setText(holder.mItem.getCantidadProducto());
        //holder.tvTotalVendido.setText(holder.mItem.getTotalVendido().toString());

        Picasso.get().load(holder.mItem.getImagenProducto()).into(holder.ivModeloMyShopping);

    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public void updateData(List<RespMyShopping> data) {
        System.out.println("------------en el update data de RespMyShopping---------");

        mValues.clear();
        mValues.addAll(data);
        System.out.println(mValues);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView tvNombreProducto;
        public final TextView tvDesProducto;
        public final TextView tvQuantityProducto;
        public final TextView tvClaveTransaccion;
        public final TextView tvTotalVendido;
        public final ImageView ivModeloMyShopping;

        public RespMyShopping mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            tvNombreProducto= view.findViewById(R.id.tvNameMyShopping);
            tvDesProducto= view.findViewById(R.id.tvDescriptionMyShopping);
            tvQuantityProducto = view.findViewById(R.id.tvQuantityMyShopping);
            tvClaveTransaccion = view.findViewById(R.id.tvClaveTransaccion);
            tvTotalVendido = view.findViewById(R.id.tvTotalSelled);
            ivModeloMyShopping = view.findViewById(R.id.ivModeloMyShopping);
        }

        @Override
        public String toString() {

            return super.toString() + " '" + tvNombreProducto.getText() + "'";
        }
    }
}