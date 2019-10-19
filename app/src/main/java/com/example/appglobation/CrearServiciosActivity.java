package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrearServiciosActivity extends AppCompatActivity {

    private Button btnSalir, btnCrearServicio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicios);
        init();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeActivity = new HomeActivity();
                Intent intent = new Intent(CrearServiciosActivity.this,homeActivity.getClass());
                startActivity(intent);

            }
        });

        btnCrearServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init(){
        btnSalir = findViewById(R.id.botonVolver);
        btnCrearServicio = findViewById(R.id.crearServicio);
    }
}
