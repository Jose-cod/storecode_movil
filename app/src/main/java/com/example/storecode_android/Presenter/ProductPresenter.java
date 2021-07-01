package com.example.storecode_android.Presenter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.LoginActivity;
import com.example.storecode_android.view.MainDrawerActivity;

import org.apache.log4j.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;

public class ProductPresenter {

    RestClientServiceImpl restClientService= new RestClientServiceImpl();
    public MutableLiveData<List<RespObtenerProducto>> listProducts = new MutableLiveData();
    public MutableLiveData<Boolean> isLoading= new MutableLiveData();
    //Variables para el home no loged
    public MutableLiveData<List<RespObtenerProducto>> listAllProducts = new MutableLiveData();
    public MutableLiveData<Boolean> isLoadingAll= new MutableLiveData();
    //Datos para las imagenes complementarias
    public MutableLiveData<RespObtenerImagesDto> imagesCompl= new MutableLiveData();

    private static final Logger log = LogFile.getLogger(LoginPresenter.class);
    private final Context view;

    public ProductPresenter(Context view) {
        this.view = view;
    }

    public void refreshAllProducts(){
        getAllProducts();
    }

    public void refreshProductsByUser(String id){
        getProductsByUser(id);
    }

    public void refreshImagesComple(String id){
        getImagesCompl(id);
    }

    public void getAllProducts(){
        log.info("--Obteniendo los productos por el id usuario---");
        AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<RespObtenerProducto>> call =restClientService.cargarAllProductos();
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET PRODUCTS APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET ALL PRODUCTS APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listAllProducts.postValue(response.body());
                        processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, Throwable t) {
                processFinished();
            }

            public void processFinished(){
                isLoadingAll.setValue(true);
            }
        });

    }

    /**
     * Description: Función encargada de traer todos los productos
     */

    public void getProductsByUser(String id){
        log.info("--Obteniendo los productos por el id usuario---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<List<RespObtenerProducto>> call =restClientService.cargarProductos(id);
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET PRODUCTS BY USER APP PRESENTER REQUEST: ","");

        call.enqueue(new Callback<List<RespObtenerProducto>>() {
            @Override
            public void onResponse(Call<List<RespObtenerProducto>> call, Response<List<RespObtenerProducto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try{
                        System.out.println("");
                        Log.d("GET PRODUCTS BY USER APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        listProducts.postValue(response.body());
                        processFinished();
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<List<RespObtenerProducto>> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener los productos"+t.getMessage());
                processFinished();
            }

            public void processFinished(){
                isLoading.setValue(true);
            }
        });

    }

    /**
     * Description: Función encargada de traer todos los productos
     */

    public void getImagesCompl(String id) {
        log.info("--Obteniendo las imagenes complementarias---");
        //AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.load_products), view.getString(R.string.waiting_products));

        Call<RespObtenerImagesDto> call = restClientService.obtenerImages(id);
        //log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("GET IMAGES COMPLE APP PRESENTER REQUEST: ", "");

        call.enqueue(new Callback<RespObtenerImagesDto>() {
            @Override
            public void onResponse(Call<RespObtenerImagesDto> call, Response<RespObtenerImagesDto> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK) {
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);


                    try {
                        System.out.println("");
                        Log.d("GET IMAGES PRODUCT APP PRESENTER", "RESPONSE EXITOSO");
                        System.out.println(response.body());
                        //SharedPref.guardarIdUsuario(view,response.body().getIdUsuario());
                        Toast.makeText(view, "Respuesta exitosa", Toast.LENGTH_SHORT).show();
                        imagesCompl.postValue(response.body());
                        //processFinished();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                } else {
                    /*view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();*/
                    //AnimacionesGenerales.mostrarLoader(false, view, null, null);

                }
            }

            @Override
            public void onFailure(Call<RespObtenerImagesDto> call, Throwable t) {
                System.err.println("Ocurrio un error al obtener las imagenes de los productos" + t.getMessage());

            }

            /*public void processFinished(){
                isLoading.setValue(true);
            }*/
        });
    }


}
