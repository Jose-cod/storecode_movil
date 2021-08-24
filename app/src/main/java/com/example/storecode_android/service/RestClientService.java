package com.example.storecode_android.service;

import com.example.storecode_android.entidades.Brand;
import com.example.storecode_android.entidades.CarritoVenta;
import com.example.storecode_android.entidades.Category;
import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ProductInCard;
import com.example.storecode_android.entidades.ProductoCarrito;
import com.example.storecode_android.entidades.ReqCarrito;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.entidades.ReqLoginDto;
import com.example.storecode_android.entidades.ReqMercadoPago;
import com.example.storecode_android.entidades.ReqUpdateProduct;
import com.example.storecode_android.entidades.ReqUpdateStock;
import com.example.storecode_android.entidades.RespFolioVenta;
import com.example.storecode_android.entidades.RespGetCarrito;
import com.example.storecode_android.entidades.RespDetaProductoComen;
import com.example.storecode_android.entidades.RespGetProductByUser;
import com.example.storecode_android.entidades.RespIdCarritoVenta;
import com.example.storecode_android.entidades.RespIdPreference;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.entidades.RespMensaje;
import com.example.storecode_android.entidades.RespMessage;
import com.example.storecode_android.entidades.RespMyShopping;
import com.example.storecode_android.entidades.RespObtenerImagesDto;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.entidades.Venta;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;

import static com.example.storecode_android.utils.Constantes.REST_SERVICE_COMENTS_GEN;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_COMENT_CLIENT;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_CREATE_CARRITO_VENTA;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_CREATE_PREFERENCE;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_CREATE_VENTA;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_DELETE_PRODUCTS;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_DELETE_PRODUCT_CART;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_GET_BRANDS;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_GET_CARRITO;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_GET_CATEGORIES;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_GET_PRODUCTS_IN_CART;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_IMAGES_COMPLE;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_IMERCADOPAGO;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_INSERT_CART;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_LOGIN;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_MYSHOPPING;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCTS;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCTS_BY_USER;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCTS_ON_SALE;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PRODUCT_CART;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PUSH_TO_DEVICE;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_PUSH_TO_TOPICS;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_TOKEN_FCM;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_UPDATE_STOCK;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_UPLOAD_PRODUCT;
import static com.example.storecode_android.utils.Constantes.REST_SERVICE_USER_BY_ID;



/**
 * Description:Interface encargada de tener los métodos/servicios a los que se conectará la aplicación
 * así como sus request y response
 * Created by EX383473 on 04/01/2019.
 */
public interface RestClientService {

    @POST(REST_SERVICE_LOGIN)
    Call<RespLoginDto> login(@Body ReqLoginDto request);

    // Servicio para registrar un nuevo usuario
    @POST(REST_SERVICE_USER_BY_ID)
    Call<RespMensaje> createAccount(@Body RespUserData respUserData);

    @GET(REST_SERVICE_PRODUCTS_BY_USER+"/{id}")
    Call<List<RespObtenerProducto>> cargarProductos(@Path("id") String id);

    @GET(REST_SERVICE_USER_BY_ID+"/{id}")
    Call<RespUserData> getUserById(@Path("id") String id);

    @GET(REST_SERVICE_PRODUCTS)
    Call<List<RespObtenerProducto>> cargarAllProductos();

    //obtener los productos que estan en venta
    @GET(REST_SERVICE_PRODUCTS_ON_SALE+"/{id}")
    Call<List<RespGetProductByUser>> getProductsOnSale(@Path("id") String id);

    //Servicio para obtener las imagenes complementarias
    @GET(REST_SERVICE_IMAGES_COMPLE+"/{id}")
    Call<RespObtenerImagesDto> obtenerImages(@Path("id") String id);
    //Servicio para obtener los comentarios generales

    @GET(REST_SERVICE_COMENTS_GEN+"/{id}")
    Call<List<RespDetaProductoComen>> getComentsGeneral(@Path("id") String id);

    //Servicio para obtener los comentarios de clientes

    @GET(REST_SERVICE_COMENT_CLIENT+"/{id}")
    Call<List<RespDetaProductoComen>> getComentsClient(@Path("id") String id);

    //@Headers({"Content-Type: multipart/form-data"})
    @Multipart
    @POST(REST_SERVICE_UPLOAD_PRODUCT)
    Call<ResponseBody> uploadProduct(
            @Part("nombreProducto") RequestBody nombreProducto,
            @Part("desProducto") RequestBody desProducto,
            @Part("precioUnitario") RequestBody precioUnitario,
            @Part("cantidadProducto") RequestBody cantidadProducto,
            @Part("marca") RequestBody marca,
            @Part("categoria") RequestBody categoria,
            @Part("idUsuario") RequestBody idUsuario,
            @Part MultipartBody.Part image
    );

    //Obtener todas las categorias

    @GET(REST_SERVICE_GET_CATEGORIES)
    Call<List<Category>> getAllCategories();

    //Obtener todas las MARCAS
    @GET(REST_SERVICE_GET_BRANDS)
    Call<List<Brand>> getAllBrands();

    //Actualizar productos

    @PUT(REST_SERVICE_PRODUCTS+"/{id}")
    Call<RespMessage> updateProduct(@Path("id") String id, @Body ReqUpdateProduct product);

    //Eliminado lógico de un producto
    @PUT(REST_SERVICE_DELETE_PRODUCTS+"/{id}")
    Call<RespMessage> deleteProduct(@Path("id") String id);

    //Obtener los productos en el carrito
    @GET(REST_SERVICE_GET_PRODUCTS_IN_CART+"/{id}")
    Call<List<ProductInCard>> getProductsInCart(@Path("id") String id);
    //insertar carrito
    @POST(REST_SERVICE_INSERT_CART)
    Call<RespMensaje> insertCarrito(@Body ReqCarrito reqCarrito);

    //Obtener el id del carrito a traves del id del usuario
    @GET(REST_SERVICE_GET_CARRITO+"/{idUser}")
    Call<RespGetCarrito> getCarritoByIdUser(@Path("idUser") String idUser);

    //Insertar producto en el carrito
    @POST(REST_SERVICE_PRODUCT_CART)
    Call<RespMensaje> insertProductInCart(@Body ProductoCarrito productoCarrito);

    @PUT(REST_SERVICE_DELETE_PRODUCT_CART+"/{idProductoCarrito}")
    Call<RespMensaje> deleteProductFromCart(@Path("idProductoCarrito") String idProductoCarrito);

    //Request para crear el id preference del proceso de pago
    @POST(REST_SERVICE_CREATE_PREFERENCE)
    Call<RespIdPreference> createIdPreference(@Body List<ReqItemProduct> reqItemProduct);

    //Request para insertar una venta

    @POST(REST_SERVICE_CREATE_VENTA)
    Call<RespFolioVenta> createVenta(@Body Venta venta);

    //Request para insertar registro en productocarritoventa

    @POST(REST_SERVICE_CREATE_CARRITO_VENTA)
    Call<RespIdCarritoVenta> createCarritoVenta(@Body CarritoVenta carritoVenta);

    @PUT(REST_SERVICE_UPDATE_STOCK )
    Call<RespMensaje> updateProductStock(@Body ReqUpdateStock reqUpdateStock);

    //obtener mis compras
    @GET(REST_SERVICE_MYSHOPPING+"/{idUser}")
    Call<List<RespMyShopping>> getMyShopping(@Path("idUser") String idUser);

    //insertar o actualizar datos de mercado pago
    @PUT(REST_SERVICE_IMERCADOPAGO)
    Call<RespMensaje> guardarDatosMercadoPago(@Body ReqMercadoPago reqMercadoPago);

    //insertar o actualizar el token fcm
    @PUT(REST_SERVICE_TOKEN_FCM)
    Call<RespMensaje> guardarUsuarioTokenFCM(@Body TokenFCM tokenFCM);

    //enviar notificacion a un dispositivo
    @POST(REST_SERVICE_PUSH_TO_DEVICE)
    Call<String> sendNotificationTODevice(@Body NotificationToDevice notificationToDevice);

    //enviar notificacion por tema/topics

    @POST(REST_SERVICE_PUSH_TO_TOPICS)
    Call<String> sendNotificationToTopics(@Body RespObtenerProducto producto);







}
