package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CrearCuentaActivity extends AppCompatActivity {

    private Button btnCrearCuenta, btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        init();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(CrearCuentaActivity.this,mainActivity.getClass());
                startActivity(intent);
            }
        });

        btnCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(CrearCuentaActivity.this,mainActivity.getClass());
                startActivity(intent);
            }
        });
    }

    public void init(){
        btnCrearCuenta = findViewById(R.id.crearCuenta);
        btnVolver = findViewById(R.id.crearVolver);
    }
}
