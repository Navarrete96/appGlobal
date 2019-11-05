package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.CharacterPickerDialog;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity {
    private CardView cardViewExit;
    private CardView cardViewCrearService;
    private CardView cardMyAccount;
    private CardView cardShowService;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        firebaseAuth = FirebaseAuth.getInstance();
        cardViewExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                MainActivity mainActivity = new MainActivity();
                Intent intent = new Intent(HomeActivity.this,mainActivity.getClass());
                startActivity(intent);
            }
        });

        cardViewCrearService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CrearServiciosActivity crearServiciosActivity = new CrearServiciosActivity();
                Intent intent = new Intent(HomeActivity.this,crearServiciosActivity.getClass());
                startActivity(intent);
            }
        });

        cardMyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MiCuentaActivity miCuentaActivity = new MiCuentaActivity();
                Intent intent = new Intent(HomeActivity.this,miCuentaActivity.getClass());
                startActivity(intent);
            }
        });

        cardShowService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListaServicioActivity listaServicioActivity = new ListaServicioActivity();
                Intent intent = new Intent(HomeActivity.this,listaServicioActivity.getClass());
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
    }

    private void init(){
        cardViewExit = findViewById(R.id.idSalir);
        cardViewCrearService = findViewById(R.id.idCreateService);
        cardMyAccount = findViewById(R.id.idMyAccount);
        cardShowService = findViewById(R.id.idBuscarServicio);
    }
}
