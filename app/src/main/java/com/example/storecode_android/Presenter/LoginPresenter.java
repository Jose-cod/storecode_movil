package com.example.storecode_android.Presenter;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.service.autofill.UserData;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.storecode_android.entidades.RespMensaje;
import com.example.storecode_android.entidades.RespObtenerProducto;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.LoginActivity;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ReqLoginDto;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.view.MainDrawerActivity;
import com.google.android.material.textfield.TextInputEditText;
import com.subhrajyoti.passwordview.PasswordView;

import org.apache.log4j.Logger;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;



/**
 * Description: Clase encargada de tener las reglas del negocio para el Login
 */
public class LoginPresenter {
    private static final Logger log = LogFile.getLogger(LoginPresenter.class);
    //private final LoginActivity view;
    private final FragmentActivity mContext;
    EditText etIdUsuario;
    EditText etContrasenia;
    Button btnEntrar;

    public MutableLiveData<RespUserData> vendedor= new MutableLiveData();

    public LoginPresenter(){
        //view=null;
        mContext=null;
    }
    /*public LoginPresenter(LoginActivity view) {
        //this.view = view;
        this.mContext=null;
    }*/

    public LoginPresenter(FragmentActivity mContext) {
        //this.view = null;
        this.mContext = mContext;


    }




    /**
     * Description: Función encargada de cargar las preferencias de de sessión
     */
    /*
    public void cargarPreferenciasSession() {
        try {
            String idUsuario = SharedPref.getString(Constantes.SHAR_PREF_USUARIO);
            if (idUsuario != null) {
                view.etIdUsuario.setText(idUsuario);
                view.mSwitch.setChecked(true);
            }
        } catch (Exception e) {
            log.error("Error:" + e.getMessage());
        }
    }*/



    public void cambiarColorBoton() {
        etIdUsuario= mContext.findViewById(R.id.editTextUser);
        etContrasenia = mContext.findViewById(R.id.editTextPass);
        btnEntrar = mContext.findViewById(R.id.buttonLogin);
        if (etIdUsuario != null && etContrasenia != null && !etIdUsuario.getText().toString().isEmpty() && !etContrasenia.getText().toString().isEmpty()) {
            btnEntrar.setEnabled(true);
        } else {
            btnEntrar.setEnabled(false);
        }
    }

    /**
     * Description: Función encargada de validar que los campos de CreateAccount se encuentren llenos
     */
    private boolean validaCamposLlenosCreateAccount() {
        EditText etNombreUsuario= mContext.findViewById(R.id.editTextUserName);
        EditText etApellidoPaterno = mContext.findViewById(R.id.editTextApellidoPaterno);
        EditText etUserEmail = mContext.findViewById(R.id.editTextUserEmail);
        PasswordView etContrasenia = mContext.findViewById(R.id.editTextPass);
        PasswordView etConfirmContrasenia = mContext.findViewById(R.id.editTextPassConfirm);

        return (!etNombreUsuario.getText().toString().isEmpty() && !etApellidoPaterno.getText().toString().isEmpty()
                && !etUserEmail.getText().toString().isEmpty() &&!etContrasenia.getText().toString().isEmpty()  && !etConfirmContrasenia.getText().toString().isEmpty());
    }
    /**
     * Description: Función encargada de validar que las contraseñas coincidan
     */
    private boolean validaContraseniasIguales(){
        PasswordView etContrasenia = mContext.findViewById(R.id.editTextPass);
        PasswordView etConfirmContrasenia = mContext.findViewById(R.id.editTextPassConfirm);

        return (etContrasenia.getText().toString().equals(etConfirmContrasenia.getText().toString()));
    }

    /**
     * Description: Función encargada de validar que los campos usuario y contraseña se encuentren llenos
     */
    private boolean validaCamposLlenos() {
        etIdUsuario= mContext.findViewById(R.id.editTextUser);
        etContrasenia = mContext.findViewById(R.id.editTextPass);

        return (!etIdUsuario.getText().toString().isEmpty() && !etContrasenia.getText().toString().isEmpty());
    }

    /**
     * Description: Función encargada de traer los productos dependiendo del usuario
     */




    /**
     * Description: Función encargada de consumir el servicio Login
     */

    private void consumirServicioLogin() {
        etIdUsuario= mContext.findViewById(R.id.editTextUser);
        etContrasenia = mContext.findViewById(R.id.editTextPass);
        btnEntrar = mContext.findViewById(R.id.buttonLogin);
        log.info("--consumirServicioLogin--");
        AnimacionesGenerales.mostrarLoader(true, mContext, "Validando credenciales", "Espera un momento...");
        ReqLoginDto reqLoginDto = new ReqLoginDto();
        reqLoginDto.setEmailUsuario(etIdUsuario.getText().toString().replaceAll("\\s", ""));
        reqLoginDto.setContraUsuario(etContrasenia.getText().toString().replaceAll("\\s", ""));
        RestClientService api = new RestClientServiceImpl();
        Call<RespLoginDto> call = api.login(reqLoginDto);
        log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("LOGIN APP PRESENTER REQUEST: ",reqLoginDto.toString());
        System.out.println(reqLoginDto.toString());
        //guardar idUsuario en un shared preference
        call.enqueue(new Callback<RespLoginDto>() {
            @Override
            public void onResponse(Call<RespLoginDto> call, retrofit2.Response<RespLoginDto> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                    /*usuarioSession = response.body().getPayLoad();
                    usuarioSession.setIdUsuario(view.etIdUsuario.getText().toString());
                    log.info("RESPONSE:" + usuarioSession.toString());
                    //Si está chequeado seteamos el usuario, en caso contrario seteamos null para olvidar el usuario
                    SharedPref.setString(Constantes.SHAR_PREF_USUARIO, view.mSwitch.isChecked() ? view.etIdUsuario.getText().toString() : null);
                    Ocultamos el diálogo
                    */

                    //Si el inicio de sesión es exitoso entonces guardamos el Primer evento de LOG
                    try{
                        System.out.println("");
                        Log.d("LOGIN APP PRESENTER","RESPONSE EXITOSO");
                        System.out.println(response.body());
                        if(response.body().getNombre().contains("activar")){
                            Toast.makeText(mContext,response.body().getNombre(),Toast.LENGTH_SHORT).show();
                        }else{
                            SharedPref.guardarIdUsuario(mContext,response.body().getIdUsuario());
                            Toast.makeText(mContext,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                            cargarDatosUsuario();
                            // Cambia de activity
                            Intent intent = new Intent(mContext, MainDrawerActivity.class);
                            mContext.startActivity(intent);
                            mContext.finish();

                            mContext.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        }

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }


                    //Cambiar el Navigation

                    /*NavController navController = Navigation.findNavController(view, R.id.fragContentLoged);
                    navController.navigate(R.id.toMainDrawerActivity);*/



                    
                } else {
                    etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    etIdUsuario.requestFocus();
                    AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                    //log.info("Response:"+response.body().toString());
                    /*if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                        log.error(response.body().getDetailResponse().getCode() + " " + response.body().getDetailResponse().getBusinessMeaning());
                        AnimacionesGenerales.mostrarAlertDialogError(view, response.body().getDetailResponse().getBusinessMeaning(), response.body().getDetailResponse().getCode());
                    } else {
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
                        Toast.makeText(view, view.getResources().getString(R.string.msj_password_incorrecto), Toast.LENGTH_LONG).show();
                    }*/
                }
            }

            @Override
            public void onFailure(Call<RespLoginDto> call, final Throwable t) {
                t.printStackTrace();
                AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
            }
        });
    }

    /**
     * Description: Función encargada de consumir el servicio para crear una cuenta
     */

    public void createAccount(RespUserData userData){
        System.out.println("Creando cuenta");
        Log.i("CreateAccount", "Creando cuenta");
        AnimacionesGenerales.mostrarLoader(true,mContext, "Creando cuenta", "Por favor, espere...");

        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.createAccount(userData);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                System.out.println("code response:"+response.code());
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false,mContext, null, null);

                    try{
                        System.out.println(response.body().toString());
                        Toast.makeText(mContext,response.body().getMensaje(),Toast.LENGTH_LONG).show();
                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        AnimacionesGenerales.mostrarLoader(false,mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }



                } else {

                    System.out.println("No se obtuvieron los datos");
                    Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                    AnimacionesGenerales.mostrarLoader(false,mContext
                            , null, null);

                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                System.out.println("No se obtuvieron los datos");
                AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
            }
        });
    }

    /*
      Función para obtener los datos del vendedor
     */

    public void getUserById(String id){
        System.out.println("--consultar datos del vendedor--");
        //obtener el id con el shared preferences
        RestClientService api = new RestClientServiceImpl();
        Call<RespUserData> call = api.getUserById(id);

        call.enqueue(new Callback<RespUserData>() {
            @Override
            public void onResponse(Call<RespUserData> call, Response<RespUserData> response) {
                System.out.println("code response:"+response.code());
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                    try{
                        System.out.println("");
                        Log.d("GET VENDOR BY ID","RESPONSE EXITOSO");
                        System.out.println(response.body().toString());
                        vendedor.postValue(response.body());
                        SharedPref.guardarVendedor(mContext, response.body().toString());

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                } else {

                    System.out.println("No se obtuvieron los datos");
                    Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                }
            }

            @Override
            public void onFailure(Call<RespUserData> call, Throwable t) {
                t.printStackTrace();
                System.out.println("No se obtuvieron los datos");
                AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
            }
        });
    }

    public void cargarDatosUsuario(){
        System.out.println("--consultar datos del usuario--");
        //obtener el id con el shared preferences
        Integer idUsuario = Integer.parseInt(SharedPref.obtenerIdUsuario(mContext));
        RestClientService api = new RestClientServiceImpl();
        Call<RespUserData> call = api.getUserById(idUsuario.toString());

        call.enqueue(new Callback<RespUserData>() {
            @Override
            public void onResponse(Call<RespUserData> call, Response<RespUserData> response) {
                System.out.println("code response:"+response.code());
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false,mContext, null, null);

                    try{
                        System.out.println("");
                        Log.d("GET USER DATA BY ID","RESPONSE EXITOSO");
                        System.out.println(response.body().toString());
                        SharedPref.guardarUsuario(mContext,response.body().toString());
                        Toast.makeText(mContext,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                        AnimacionesGenerales.mostrarLoader(false,mContext, null, null);
                        AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
                    }



                } else {

                    System.out.println("No se obtuvieron los datos");
                    Log.d("errorMessage", "El response no fue exitoso al obtener los datos del usuario");

                    AnimacionesGenerales.mostrarLoader(false,mContext
                            , null, null);

                }
            }

            @Override
            public void onFailure(Call<RespUserData> call, Throwable t) {
                t.printStackTrace();
                System.out.println("No se obtuvieron los datos");
                AnimacionesGenerales.mostrarLoader(false, mContext, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(mContext);
            }
        });
    }

    /**
     * Description: Función encargada de validar que el usuario y contraseña existan
     */
    public void validaUsuarioYContrasenia() {
        log.info("--validaUsuarioYPassword--");
        if (validaCamposLlenos()) {
            consumirServicioLogin();
        } else {
            Toast.makeText(mContext,"Ingresa tus credenciales", Toast.LENGTH_SHORT).show();
        }
    }

    public void validaCreateAccount() {
        EditText etNombreUsuario= mContext.findViewById(R.id.editTextUserName);
        EditText etApellidoPaterno = mContext.findViewById(R.id.editTextApellidoPaterno);
        EditText etUserEmail = mContext.findViewById(R.id.editTextUserEmail);
        PasswordView etContrasenia = mContext.findViewById(R.id.editTextPass);
        PasswordView etConfirmContrasenia = mContext.findViewById(R.id.editTextPassConfirm);

        log.info("--CreateAccount--");
        if (validaCamposLlenosCreateAccount()) {
            if(validaContraseniasIguales()){

                RespUserData user= new RespUserData();
                user.setNombreUsuario(etNombreUsuario.getText().toString());
                user.setApellido1Usuario(etApellidoPaterno.getText().toString());
                user.setEmailUsuario(etUserEmail.getText().toString());
                user.setContraUsuario(etContrasenia.getText().toString());
                user.setConfirmaContraUsuario(etConfirmContrasenia.getText().toString());
                user.setCodeActive(getRandomString(6));

                createAccount(user);
                System.out.println("Las contraseñas son iguales");
            }else{
                Toast.makeText(mContext,"Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(mContext,"No debes dejar campos vacíos", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * Función que regresa un String aleatorio de 6 caracteres para usarlo como el codeActive
     */

    public String getRandomString(int i) {
        String theAlphaNumericS;
        StringBuilder builder;

        theAlphaNumericS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                + "0123456789";

        //create the StringBuffer
        builder = new StringBuilder(i);

        for (int m = 0; m < i; m++) {

            // generate numeric
            int myindex
                    = (int)(theAlphaNumericS.length()
                    * Math.random());

            // add the characters
            builder.append(theAlphaNumericS
                    .charAt(myindex));
        }

        return builder.toString();
    }

    /**
     * Description: Función encargada de consumir el servicio de Bitacora Login
     */

    /*
    private void consumirServicioBitacoraLogin(String fuerza_venta, String num_empleado) {
        log.info("--consumirServicioBitacoraLogin--");

        ReqBitacoraLoginAppDto reqLoginDto = new ReqBitacoraLoginAppDto();
        reqLoginDto.setFuerzaVentaM2k(fuerza_venta);
        reqLoginDto.setNumeroEmpleado(num_empleado);

        String imei="";
        try {
            imei = getDeviceUniqueID(view);
            log.info("IMEI: LOGIN "+ imei);
        }catch (Exception e){
            e.printStackTrace();
        }
        reqLoginDto.setImei(imei);
        reqLoginDto.setTipo(view.getString(R.string.name_login));
        reqLoginDto.setRegion(usuarioSession.getRegion());

        RestClientService api = new RestClientServiceImpl();
        Call<ResponseMasterDto<RespBitacoraLoginDto>> call = api.guardaLoginApp(reqLoginDto);
        log.info("REQUEST_BITACORA:" + reqLoginDto.toString());
        call.enqueue(new Callback<ResponseMasterDto<RespBitacoraLoginDto>>() {
            @Override
            public void onResponse(Call<ResponseMasterDto<RespBitacoraLoginDto>> call, retrofit2.Response<ResponseMasterDto<RespBitacoraLoginDto>> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK && response.body().getDetailResponse().getDescription().equals("Response exitoso")) {
                       log.info("Respuesta Bitacora: " + response.body().getDetailResponse().getDescription());
                } else {
                    if (response != null && response.code() == RESP_CODE_WEB_OK &&
                            !response.body().getDetailResponse().getCode().equals(RESP_CODE_OK)) {
                        log.error(response.body().getDetailResponse().getCode() + " " + response.body().getDetailResponse().getBusinessMeaning());
                    } else {
                       log.info("ERROR EN ALMACENAR BITACORA LOGIN");
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMasterDto<RespBitacoraLoginDto>> call, final Throwable t) {
                log.error(t.getMessage());
                t.printStackTrace();
                log.info("ERROR EN ALMACENAR BITACORA LOGIN_3");
            }
        });
    }*/


    /*
    @SuppressLint("HardwareIds")
    public static String getDeviceUniqueID(Context activity){
        return Settings.Secure.getString(activity.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }
    */

}
