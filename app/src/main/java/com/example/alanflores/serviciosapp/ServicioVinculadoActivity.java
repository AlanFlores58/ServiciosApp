package com.example.alanflores.serviciosapp;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.alanflores.serviciosapp.Services.ServicioVinculado;

public class ServicioVinculadoActivity extends AppCompatActivity {

    private boolean serviceBound = false;
    private TextView textTiempo;
    private Button buttonTiempo;
    private ServicioVinculado servicioVinculado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_vinculado);

        textTiempo = (TextView) findViewById(R.id.text_tiempo);
        buttonTiempo = (Button) findViewById(R.id.button_obtener_tiempo);
        buttonTiempo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(serviceBound)
                    textTiempo.append(servicioVinculado.getTimeStap() + "\n");
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, ServicioVinculado.class);
        bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(serviceBound){
            unbindService(serviceConnection);
            serviceBound = false;
        }else {
            Toast.makeText(this, "Servicio no vinculado", Toast.LENGTH_SHORT).show();
        }
    }

    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            ServicioVinculado.MiBinder miBinder =  (ServicioVinculado.MiBinder) iBinder;
            servicioVinculado = miBinder.getService();
            serviceBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            serviceBound = false;
        }
    };
}
