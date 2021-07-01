package com.example.storecode_android.view.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.models.SlideModel;
import com.example.storecode_android.Presenter.ProductPresenter;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductDetailFragment extends Fragment {


    ImageButton btnSalir;
    ImageSlider sliderImageProducts;
    private ProductPresenter productPresenter;
    RespObtenerProducto producto;


    public ProductDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.

     */
    // TODO: Rename and change types and number of parameters
    public static ProductDetailFragment newInstance(String param1, String param2) {
        ProductDetailFragment fragment = new ProductDetailFragment();
        Bundle args = new Bundle();


        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        productPresenter = new ProductPresenter(getContext());
        sliderImageProducts = view.findViewById(R.id.sliderImageProducts);
        btnSalir= view.findViewById(R.id.btnSalir);
        TextView tv = view.findViewById(R.id.txtNameProductDetailFragment);
        producto = ProductDetailFragmentArgs.fromBundle(getArguments()).getRespObtenerProducto();
        tv.setText("Nombre del producto:"+producto.getNombreProducto());

        productPresenter.getImagesCompl(producto.getIdProducto().toString());
        observePresenter();



        btnSalir.setOnClickListener(v -> {
            //Navegacion hacia atras
            //NavDirections navDirection= ProductDetailFragmentDirections.productDetailToHomeLoged();
            //Navigation.findNavController(getView()).navigate(navDirection);

            Navigation.findNavController(getView()).navigate(ProductDetailFragmentDirections.productDetailToHomeLoged());
        });

    }

    public void observePresenter(){

        productPresenter.imagesCompl.observe(getViewLifecycleOwner(), respObtenerImagesDto -> {
            List<SlideModel> slideModels = new ArrayList();


            if(respObtenerImagesDto!=null){
                slideModels.add(new SlideModel(respObtenerImagesDto.getImagenProducto(),"Imagen 1"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg1(),"Imagen 2"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg2(),"Imagen 3"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg3(),"Imagen 4"));
                slideModels.add(new SlideModel(respObtenerImagesDto.getImg4(),"Imagen 5"));
            }else{
                slideModels.add(new SlideModel(producto.getImagenProducto(),"Imagen 1"));
                slideModels.add(new SlideModel("no image","Imagen 2"));
                slideModels.add(new SlideModel("no image","Imagen 3"));
                slideModels.add(new SlideModel("no image","Imagen 4"));
                slideModels.add(new SlideModel("no image","Imagen 5"));
            }



            sliderImageProducts.setImageList(slideModels, true);
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_detail, container, false);
    }
}