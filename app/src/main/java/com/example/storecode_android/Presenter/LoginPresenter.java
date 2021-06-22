package com.example.storecode_android.Presenter;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.util.Log;
import android.widget.Toast;

import com.example.storecode_android.view.LoginActivity;
import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ReqLoginDto;
import com.example.storecode_android.entidades.RespLoginDto;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;

import org.apache.log4j.Logger;

import retrofit2.Call;
import retrofit2.Callback;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;
/*
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.R;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.kotlin.ui.main.MainPrincipalActivity;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqBitacoraLoginAppDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ReqLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespBitacoraLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.RespLoginDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.model.dto.ResponseMasterDto;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.service.rest.RestClientService;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.service.rest.impl.RestClientServiceImpl;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.AnimacionesGenerales;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.LogFile;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.SharedPref;
import mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.view.LoginActivity;
import retrofit2.Call;
import retrofit2.Callback;

import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.RESP_CODE_OK;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.Constantes.RESP_CODE_WEB_OK;
import static mx.com.telcel.di.sds.gsac.dapmov.precio_inteligente.utils.VariablesSesion.usuarioSession;
*/


/**
 * Description: Clase encargada de tener las reglas del negocio para el Login
 */
public class LoginPresenter {
    private static final Logger log = LogFile.getLogger(LoginPresenter.class);
    private final LoginActivity view;

    public LoginPresenter(LoginActivity view) {
        this.view = view;
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
        if (view.etIdUsuario != null && view.etContrasenia != null && !view.etIdUsuario.getText().toString().isEmpty() && !view.etContrasenia.getText().toString().isEmpty()) {
            view.btnEntrar.setEnabled(true);
        } else {
            view.btnEntrar.setEnabled(false);
        }
    }

    /**
     * Description: Función encargada de validar que los campos usuario y contraseña se encuentren llenos
     */
    private boolean validaCamposLlenos() {
        return (!view.etIdUsuario.getText().toString().isEmpty() && !view.etContrasenia.getText().toString().isEmpty());
    }

    /**
     * Description: Función encargada de consumir el servicio Login
     */

    private void consumirServicioLogin() {
        log.info("--consumirServicioLogin--");
        AnimacionesGenerales.mostrarLoader(true, view, view.getString(R.string.validando_credenciales), view.getString(R.string.wait_retrofit));
        ReqLoginDto reqLoginDto = new ReqLoginDto();
        reqLoginDto.setEmailUsuario(view.etIdUsuario.getText().toString().replaceAll("\\s", ""));
        reqLoginDto.setContraUsuario(view.etContrasenia.getText().toString().replaceAll("\\s", ""));
        RestClientService api = new RestClientServiceImpl();
        Call<RespLoginDto> call = api.login(reqLoginDto);
        log.info("REQUEST:" + reqLoginDto.toString());
        Log.d("LOGIN APP PRESENTER REQUEST: ",reqLoginDto.toString());
        System.out.println(reqLoginDto.toString());
        call.enqueue(new Callback<RespLoginDto>() {
            @Override
            public void onResponse(Call<RespLoginDto> call, retrofit2.Response<RespLoginDto> response) {
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);
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
                        Toast.makeText(view,"Respuesta exitosa",Toast.LENGTH_SHORT).show();
                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    /*Intent intent = new Intent(view, MainPrincipalActivity.class);
                    view.startActivity(intent);
                    view.finish();
                    view.overridePendingTransition(R.anim.fade_in, R.anim.fade_out);*/
                } else {
                    view.etContrasenia.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.getBackground().mutate().setColorFilter(Color.RED, PorterDuff.Mode.SRC_ATOP);
                    view.etIdUsuario.requestFocus();
                    AnimacionesGenerales.mostrarLoader(false, view, null, null);
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
                AnimacionesGenerales.mostrarLoader(false, view, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
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
            Toast.makeText(view, view.getString(R.string.input_credentials), Toast.LENGTH_SHORT).show();
        }
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
