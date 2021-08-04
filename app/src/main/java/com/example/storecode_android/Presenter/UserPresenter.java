package com.example.storecode_android.Presenter;

import android.media.session.MediaSession;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.example.storecode_android.R;
import com.example.storecode_android.entidades.ReqMercadoPago;
import com.example.storecode_android.entidades.RespMensaje;
import com.example.storecode_android.entidades.RespUserData;
import com.example.storecode_android.entidades.TokenFCM;
import com.example.storecode_android.service.RestClientService;
import com.example.storecode_android.service.RestClientServiceImpl;
import com.example.storecode_android.utils.AnimacionesGenerales;
import com.example.storecode_android.utils.LogFile;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.LoginActivity;

import org.apache.log4j.Logger;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.storecode_android.utils.Constantes.RESP_CODE_WEB_OK;

public class UserPresenter {
    private static final Logger log = LogFile.getLogger(UserPresenter.class);
    private final FragmentActivity view;

    public UserPresenter(){
        this.view=null;
    }

    public UserPresenter(FragmentActivity view) {
        this.view = view;
    }


    /*
      Función para guardar datos de mercado pago del usuario
     */

    public void guardarDatosMercadoPago(ReqMercadoPago reqMercadoPago){
        System.out.println("--insertar o guardar datos de mercado pago--");
        AnimacionesGenerales.mostrarLoader(true, view, "Guardando datos", view.getString(R.string.wait_retrofit));
        //obtener el id con el shared preferences
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.guardarDatosMercadoPago(reqMercadoPago);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                System.out.println("code response:"+response.code());
                AnimacionesGenerales.mostrarLoader(false,view, null, null);
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                    try{
                        System.out.println("");
                        Log.d("GUARDAR DATOS DE MERCADO PAGO","RESPONSE EXITOSO");
                        Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();
                        EditText publickey= view.findViewById(R.id.editTextPublicKey);
                        EditText accessToken = view.findViewById(R.id.editTextAccessToken);

                        publickey.setText("");
                        accessToken.setText("");

                        //consumirServicioBitacoraLogin(response.body().getPayLoad().getIdFuerzaDeVenta(), view.etIdUsuario.getText().toString());
                    }catch (Exception e){
                        AnimacionesGenerales.mostrarLoader(false,view, null, null);
                        e.printStackTrace();
                    }

                } else {
                    AnimacionesGenerales.mostrarLoader(false,view, null, null);
                    Toast.makeText(view,"Ocurrio un error al guardar los datos",Toast.LENGTH_SHORT).show();
                    System.out.println("Los datos no se guardaron");
                    Log.d("errorMessage", "Ocurrio un error al guardar los datos");

                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                System.out.println("Ocurrio un error al guardar los datos");
                AnimacionesGenerales.mostrarLoader(false, view, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
            }
        });
    }

    /**
     * Description: Función encargada de guardar el tokenFCM del usuario
     */
    public void guardarUsuarioTokenFCM(TokenFCM tokenFCM){
        System.out.println("--insertar o guardar tokenFCM--");
        //obtener el id con el shared preferences
        RestClientService api = new RestClientServiceImpl();
        Call<RespMensaje> call = api.guardarUsuarioTokenFCM(tokenFCM);

        call.enqueue(new Callback<RespMensaje>() {
            @Override
            public void onResponse(Call<RespMensaje> call, Response<RespMensaje> response) {
                System.out.println("code response:"+response.code());
                //AnimacionesGenerales.mostrarLoader(false,view, null, null);
                if (response != null && response.code() == RESP_CODE_WEB_OK ) {

                    try{
                        System.out.println("");
                        Log.d("GUARDAR TOKEN FCM","RESPONSE EXITOSO");
                        //Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        AnimacionesGenerales.mostrarLoader(false,view, null, null);
                        e.printStackTrace();
                    }

                } else {
                    //AnimacionesGenerales.mostrarLoader(false,view, null, null);
                    Toast.makeText(view,"Ocurrio un error al guardar el token fcm",Toast.LENGTH_SHORT).show();
                    System.out.println("Ocurrio un error al guardar el token fcm");
                    Log.d("errorMessage", "Ocurrio un error al guardar el token fcm");

                }
            }

            @Override
            public void onFailure(Call<RespMensaje> call, Throwable t) {
                t.printStackTrace();
                System.out.println("Ocurrio un error al guardar los datos");
                AnimacionesGenerales.mostrarLoader(false, view, null, null);
                AnimacionesGenerales.mostrarAlertDialogErrorServer(view);
            }
        });

    }


     /*
      Función para una notificacion a un dispositivo especifico
     */

    public void sendNotificationToDevice(TokenFCM tokenFCM){
        System.out.println("--enviar notificación a un dispositivo--");
        //obtener el id con el shared preferences
        RestClientService api = new RestClientServiceImpl();
        Call<String> call = api.sendNotificationTODevice(tokenFCM);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(response!=null && response.code()==RESP_CODE_WEB_OK){

                    try{
                        System.out.println("");
                        Log.d("ENVIAR NOTIFICACION","RESPONSE EXITOSO");
                        //Toast.makeText(view,response.body().getMensaje(),Toast.LENGTH_SHORT).show();

                    }catch (Exception e){
                        e.printStackTrace();
                    }

                }else{
                    System.out.println("Ocurrio un error al enviar la notificacion");
                    Log.d("errorMessage", "Ocurrio un error al enviar la notificacion");
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                t.printStackTrace();
                System.out.println("Ocurrio un error al enviar la notificacion");
                Log.d("errorMessage", "Ocurrio un error al enviar la notificacion");
            }
        });


    }

}
