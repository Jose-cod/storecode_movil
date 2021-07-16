package com.example.storecode_android.utils;

public class Constantes {
    public static final Long LOG_SIZE_MB = 10485760L;
    public static final String LOG_FILE_NAME = "Storecode";
    public static final String SHAR_PREF_NAME = "share_storecode";
    public static String URL_BASE= "http://192.168.1.72:3000/storecode/";

    //Servicios generales
    public static final String REST_SERVICE_LOGIN_AUTORIZADO = "usuarioAutorizado";

    public static final String REST_SERVICE_LOGIN = "login";

    public static final String REST_SERVICE_USER_BY_ID = "users";

    public static final String REST_SERVICE_PRODUCTS = "products";

    public static final String REST_SERVICE_PRODUCTS_BY_USER = "products/byuser";

    public static final String REST_SERVICE_PRODUCTS_ON_SALE ="products/iduser";

    public static final String REST_SERVICE_IMAGES_COMPLE= "products/imagesc";

    public static final String REST_SERVICE_COMENTS_GEN ="detaproductocomen";

    public static final String REST_SERVICE_COMENT_CLIENT = "detaproductocomencliente";

    public static final String REST_SERVICE_UPLOAD_PRODUCT = "upload";

    public static final String REST_SERVICE_GET_CATEGORIES = "categories";

    public static final String REST_SERVICE_GET_BRANDS ="brands";

    public static final String REST_SERVICE_DELETE_PRODUCTS = "delete/products";

    public static final String REST_SERVICE_GET_PRODUCTS_IN_CART ="productsInCard";

    //Constantes del Share preferences
    public static final String PRODUCTOS="productos";
    public static final String ID_USER="idUsuario";
    public static final String USER="user";

    public static final short TIME_OUT_RETROFIT = 6000;

    public static final short RESP_CODE_WEB_OK = 200;
}
