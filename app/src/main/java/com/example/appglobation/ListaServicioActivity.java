package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ListaServicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_servicio);

        HomeFragment fragment = new HomeFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.idFrame,fragment).commit();
    }
}
