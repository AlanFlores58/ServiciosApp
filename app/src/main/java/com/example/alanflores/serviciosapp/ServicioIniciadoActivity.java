package com.example.alanflores.serviciosapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.alanflores.serviciosapp.Services.ServicioIniciado;

public class ServicioIniciadoActivity extends AppCompatActivity {

    Button buttonIniciarServicio;
    Button buttonDetenerServicio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_iniciado);

        buttonIniciarServicio = (Button)findViewById(R.id.button_iniciar_servicio);
        buttonDetenerServicio = (Button)findViewById(R.id.button_detener_servicio);

        buttonIniciarServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServicioIniciadoActivity.this, ServicioIniciado.class);
                startService(intent);
            }
        });

        buttonDetenerServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServicioIniciadoActivity.this, ServicioIniciado.class);
                stopService(intent);
            }
        });
    }
}
