package com.example.alfredomartinromo.locationservice.Service.Receivers;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
/**
 * Created by alfredo.martinromo on 24/02/2017.
 * Receptor de avisos
 */

public class ReceiverBoot extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        // Lanzar Servicio
        Intent serviceIntent = new Intent();
        serviceIntent.setAction("com.example.alfredomartinromo.locationservice.Service.Services.Localizacion");
        context.startService(serviceIntent);
        /*// Lanzar actividad
        Intent i = new Intent(context, Main.class);
        // Cuando se intenta abrir una Activity desde un componente que no sea una actividad
        // se debe de avisar mediante un Flag que está siendo iniciada por un componente alternativo.
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);*/
    }
}