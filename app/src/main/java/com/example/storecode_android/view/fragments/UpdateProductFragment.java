package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UpdateProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UpdateProductFragment extends Fragment {

   RespGetProductByUser producto;

   ImageView ivPhotoProduct;
   Button btnChooseImage;
   EditText editTextNameProduct;

   EditText editTextDescriptionProduct;
   EditText editTextPriceProduct;
   EditText editTextQuantityProduct;
   ImageButton btnReturn;



    public UpdateProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UpdateProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UpdateProductFragment newInstance(String param1, String param2) {
        UpdateProductFragment fragment = new UpdateProductFragment();
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_update_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        producto = UpdateProductFragmentArgs.fromBundle(getArguments()).getProduct();
        ivPhotoProduct = view.findViewById(R.id.ivPhotoProduct);
        btnChooseImage = view.findViewById(R.id.btnChooseImage);
        editTextNameProduct = view.findViewById(R.id.editTextNameProduct);
        editTextDescriptionProduct = view.findViewById(R.id.editTextDescriptionProduct);
        editTextPriceProduct = view.findViewById(R.id.editTextPriceProduct);
        editTextQuantityProduct = view.findViewById(R.id.editTextQuantityProduct);
        producto= UpdateProductFragmentArgs.fromBundle(getArguments()).getProduct();
        btnReturn = view.findViewById(R.id.btnReturn);

        if(producto!=null){
            Picasso.get().load(producto.getImagenProducto()).into(ivPhotoProduct);
            editTextNameProduct.setText(producto.getNombreProducto());
            editTextDescriptionProduct.setText(producto.getDesProducto());
            editTextPriceProduct.setText(String.valueOf(producto.getPrecioUnitarioProducto()));
            editTextQuantityProduct.setText(String.valueOf(producto.getStockRealProducto()));
        }

        btnReturn.setOnClickListener(v->{
            //Navigation.findNavController(getView()).navigate();
            Navigation.findNavController(getView()).navigate(UpdateProductFragmentDirections.actionUpdateProductFragmentToProductsOnSaleFragment());
        });


    }
}