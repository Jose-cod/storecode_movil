package com.example.storecode_android.service;

import com.example.storecode_android.entidades.ReqLoginDto;
import com.example.storecode_android.entidades.RespDetaProductoComen;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.entidades.ResponseMasterDto;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import static com.example.storecode_android.utils.Constantes.REST_SERVICE_COMENTS_GEN;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_COMENT_CLIENT;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_IMAGES_COMPLE;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_LOGIN;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCTS;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCTS_BY_USER;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_USER_BY_ID;

/*
import io.reactivex.Observable;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.PreciadorDispositivoDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqBitacoraColocacionDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqBitacoraLoginAppDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqConsultaVideosDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqConsultaVideosDto2;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqFlagStock;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqLogin_AutorizadoDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqObtenerColoresDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqObtenerMarcaDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqObtenerModelosDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqObtenerPlanDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqPreciadorDispositivoDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RequestConsultaVideosPorMod;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ResObtenerMarcaDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespBitacoraLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespColor;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespConsultaVideosDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespConsultaVideosDto2;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespConsultaVideosPorMod;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespFlagStock;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespLogin_AutorizadoDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespObtenerModelosDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespObtenerPlanDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ResponseMasterDto;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.BITACORA_COLOCACION_APP;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.BITACORA_LOGIN_APP;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.CONSTRUYE_PRECIADOR;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.CONSTRUYE_PRECIADORES;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.CONSULTA_INFORMACION_VIDEOS;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.CONSULTA_VIDEOS_POR_MODELO;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.GET_FLAG_STOCK;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.OBTENER_COLORES_SERVICE;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.OBTENER_MARCAS_SERVICE;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.OBTENER_MODELOS_SERVICE;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.OBTENER_PLANES_SERVICE;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.REST_SERVICE_LOGIN;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.REST_SERVICE_LOGIN_AUTORIZADO;
*/

/**
 * Description:Interface encargada de tener los métodos/servicios a los que se contectará la aplicación
 * así como sus request y response
 * Created by EX383473 on 04/01/2019.
 */
public interface RestClientService {

    @POST(REST_SERVICE_LOGIN)
    Call<RespLoginDto> login(@Body ReqLoginDto request);

    @GET(REST_SERVICE_PRODUCTS_BY_USER+"/{id}")
    Call<List<RespObtenerProducto>> cargarProductos(@Path("id") String id);

    @GET(REST_SERVICE_USER_BY_ID+"/{id}")
    Call<RespUserData> getUserById(@Path("id") String id);

    @GET(REST_SERVICE_PRODUCTS)
    Call<List<RespObtenerProducto>> cargarAllProductos();

    //Servicio para obtener las imagenes complementarias
    @GET(REST_SERVICE_IMAGES_COMPLE+"/{id}")
    Call<RespObtenerImagesDto> obtenerImages(@Path("id") String id);
    //Servicio para obtener los comentarios generales

    @GET(REST_SERVICE_COMENTS_GEN+"/{id}")
    Call<List<RespDetaProductoComen>> getComentsGeneral(@Path("id") String id);

    //Servicio para obtener los comentarios de clientes

    @GET(REST_SERVICE_COMENT_CLIENT+"/{id}")
    Call<List<RespDetaProductoComen>> getComentsClient(@Path("id") String id);



    /*@POST(REST_SERVICE_LOGIN_AUTORIZADO)
    Call<ResponseMasterDto<RespLogin_AutorizadoDto>> login_autorizado(@Body ReqLogin_AutorizadoDto request);

    @POST(REST_SERVICE_LOGIN)
    Call<ResponseMasterDto<RespLoginDto>> login(@Body ReqLoginDto request);

    @POST(OBTENER_MARCAS_SERVICE)
    Call<ResponseMasterDto<List<ResObtenerMarcaDto>>> obtenerMarcas(@Body ReqObtenerMarcaDto request);

    @POST(OBTENER_MODELOS_SERVICE)
    Call<ResponseMasterDto<List<RespObtenerModelosDto>>> obtenerModelos(@Body ReqObtenerModelosDto request);

    @POST(OBTENER_COLORES_SERVICE)
    Call<ResponseMasterDto<List<RespColor>>> obtenerColores(@Body ReqObtenerColoresDto request);

    @POST(OBTENER_PLANES_SERVICE)
    Call<ResponseMasterDto<List<RespObtenerPlanDto>>> obtenerPlanes(@Body ReqObtenerPlanDto request);

    @POST(CONSTRUYE_PRECIADOR)
    Call<ResponseMasterDto<PreciadorDispositivoDto>> construyePreciador(@Body ReqPreciadorDispositivoDto request);

    @POST(CONSTRUYE_PRECIADORES)
    Call<ResponseMasterDto<List<PreciadorDispositivoDto>>> construyePreciadores(@Body List<ReqPreciadorDispositivoDto> request);

    *//*@POST(CONSULTA_VIDEOS_POR_MODELO)
    Call<ResponseMasterDto<RespConsultaVideosDto>> consultaVideosPorModelo(@Body ReqConsultaVideosDto request);*//*

    @POST(CONSULTA_VIDEOS_POR_MODELO)
    Call<ResponseMasterDto<RespConsultaVideosDto2>> consultaVideosPorModelo(@Body ReqConsultaVideosDto2 request);

    @POST(CONSULTA_VIDEOS_POR_MODELO)
    Observable<ResponseMasterDto<RespConsultaVideosDto2>> consultaVideosPorModeloObserver(@Body ReqConsultaVideosDto2 request);

    @POST(CONSULTA_INFORMACION_VIDEOS)
    Call<ResponseMasterDto<RespConsultaVideosPorMod>> consultaInformacionVideos(@Body RequestConsultaVideosPorMod request);

    @POST(BITACORA_LOGIN_APP)
    Call<ResponseMasterDto<RespBitacoraLoginDto>> guardaLoginApp(@Body ReqBitacoraLoginAppDto request);

    @POST(BITACORA_COLOCACION_APP)
    Call<ResponseMasterDto<RespBitacoraLoginDto>> guardaColocacionApp(@Body ReqBitacoraColocacionDto request);

    @POST(GET_FLAG_STOCK)
    Call<ResponseMasterDto<RespFlagStock>> getFlagStock(@Body ReqFlagStock request);*/

}
