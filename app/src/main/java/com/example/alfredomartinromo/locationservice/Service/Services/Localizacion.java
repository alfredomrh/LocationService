package com.example.alfredomartinromo.locationservice.Service.Services;

import android.Manifest;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

//mirar este video aunque no utiliza el servicio correctamente
// https://www.youtube.com/watch?v=DsBTQ4F6n7s

/**este servicio no va a ser bind e implementa la interfaz de localización.
 *
 */

public class Localizacion extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private GoogleApiClient mGoogleApiClient;

    public Localizacion() {

    }

    @Override
    public void onCreate() {
        super.onCreate();

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    //.addConnectionCallbacks(this)
                    //.addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }

        Toast.makeText(this, "Servicio creado ahora", Toast.LENGTH_LONG).show();
        Log.d("EJEMPLOONBOOT", "Servicio creado");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        mGoogleApiClient.connect();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {

        mGoogleApiClient.disconnect();
        super.onDestroy();

        Toast.makeText(this, "Servicio destruido", Toast.LENGTH_LONG).show();
        Log.d("EJEMPLOONBOOT", "Servicio destruido");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        Location mLastLocation, destino;

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                    mGoogleApiClient);

            if (mLastLocation != null) {
                //aqui puedes recuperar la información del objeto Latitude

                //mLatitudeText.setText(String.valueOf(mLastLocation.getLatitude()));
                //mLongitudeText.setText(String.valueOf(mLastLocation.getLongitude()));

                //esta debería ser la localizacion de la sonda
                destino = new Location("Propio Proveedor");
                destino.setLatitude(122.30);
                destino.setLongitude(30.00);

                if(mLastLocation.distanceTo(destino) < 100) {//metros
               //mostrar en una notificacion que estamos cerca y arrancar otro hilo de lectura intentService
                }
            }

        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
