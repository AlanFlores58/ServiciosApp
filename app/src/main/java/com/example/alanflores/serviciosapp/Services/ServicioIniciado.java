package com.example.alanflores.serviciosapp.Services;

import android.app.Service;
import android.content.Intent;

import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by alan.flores on 12/22/16.
 */

public class ServicioIniciado extends Service {

    AsynTaskHora asynTaskHora;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "Servicio Iniciado", Toast.LENGTH_SHORT).show();
        asynTaskHora = new AsynTaskHora();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        asynTaskHora.execute();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Servicio Destruido", Toast.LENGTH_SHORT).show();
        asynTaskHora.cancel(true);
    }

    private class AsynTaskHora extends AsyncTask<String, String, String>{

        private DateFormat dateFormat;
        private String hora;
        private boolean mostrando;

        @Override
        protected String doInBackground(String... strings) {
            while(mostrando){
                hora = dateFormat.format(new Date());
                try {
                    publishProgress(hora);
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            mostrando = true;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            Toast.makeText(getApplicationContext(), "Hora actual " + values[0], Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onCancelled() {
            mostrando = false;
            Thread.currentThread().interrupt();
            super.onCancelled();
        }
    }
}
