package com.example.alanflores.serviciosapp.Services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alan.flores on 12/22/16.
 */

public class ServicioVinculado extends Service{

    private IBinder iBinder = new MiBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "El servicio ha sido destruido", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return Service.START_STICKY;
    }

    public String getTimeStap(){
        String hora;
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss.SS");
        hora = dateFormat.format(new Date());
        return hora;
    }

    public class MiBinder extends Binder{
        public ServicioVinculado getService(){
            return ServicioVinculado.this;
        }
    }

}
