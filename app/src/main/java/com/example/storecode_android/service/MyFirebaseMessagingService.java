package com.example.storecode_android.service;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Looper;
import android.text.Html;
import android.util.AndroidRuntimeException;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.storecode_android.entidades.NotificationToDevice;
import com.example.storecode_android.entidades.ReqItemProduct;
import com.example.storecode_android.utils.SharedPref;
import com.example.storecode_android.view.MainDrawerActivity;
import com.google.firebase.database.Transaction;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Looper.prepare();

        Handler handler = new Handler();

        handler.post(() -> {

            String claveTransaccion= remoteMessage.getData().get("claveTransaccion");
            Double totalPagado = Double.parseDouble(remoteMessage.getData().get("totalVendido"));

            String items= remoteMessage.getData().get("items");
            System.out.println("Imprimiendo items---------------");
            System.out.println(items);
            Type listType = new TypeToken<List<ReqItemProduct>>(){}.getType();
            List<ReqItemProduct> listItem  = new Gson().fromJson(items, listType);


            Toast.makeText(getBaseContext(),remoteMessage.getNotification().getTitle()+claveTransaccion+"Total pagado"+totalPagado,Toast.LENGTH_LONG).show();
            System.out.println("Recibiendo notificacion en primer plano");
            System.out.println("Clave"+claveTransaccion);
            System.out.println("Tus productos");
            listItem.forEach(product ->{
                System.out.println(product.getDescription());
                System.out.println(product.getPrice());
            });
            System.out.println("Total"+totalPagado);
            //crear objeto NotificationToDevce para guardarlo
            String idUsuario= SharedPref.obtenerIdUsuario(getBaseContext());
            NotificationToDevice notificationToDevice = new NotificationToDevice(
                    Integer.parseInt(idUsuario),
                    null,
                    claveTransaccion,
                    totalPagado,
                    "ITEMS PENDIENTES POR GUARDAR"
            );

            SharedPref.guardarNotificacionDescartada(getBaseContext(),notificationToDevice);



            /*AlertDialog.Builder builder = new AlertDialog.Builder(getBaseContext().getApplicationContext());
            builder.setCancelable(false);

            builder.setTitle("Detalles de tu compra");

            builder.setMessage("Clave Transaccion"+claveTransaccion+"\n"+
                    "Total pagado: $"+totalPagado
            );

            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();

                }
            });
            try {
                builder.show();
            }catch (AndroidRuntimeException e){
                e.printStackTrace();
            }*/
        });


        Looper.loop();



    }
}
