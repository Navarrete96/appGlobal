package com.example.appglobation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CrearServiciosActivity extends AppCompatActivity {

    private Button btnSalir, btnCrearServicio;
    private EditText editTextName, editTextType, editTextPrice, editTextPhone;
    private DatabaseReference databaseReference;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_servicios);
        databaseReference = FirebaseDatabase.getInstance().getReference("Services");
        firebaseAuth = FirebaseAuth.getInstance();
        init();
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                HomeActivity homeActivity = new HomeActivity();
                Intent intent = new Intent(CrearServiciosActivity.this, homeActivity.getClass());
                startActivity(intent);

            }
        });

        btnCrearServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (editTextName.getText().toString().isEmpty() || editTextType.getText().toString().isEmpty() || editTextPrice.getText().toString().isEmpty()
                        || editTextPhone.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Por favor, llenar los campos solicitados", Toast.LENGTH_LONG).show();
                } else {
                    DatabaseReference reference = databaseReference.child(firebaseAuth.getCurrentUser().getUid());
                    reference.child("servicio").setValue(editTextName.getText().toString());
                    reference.child("tipo").setValue(editTextType.getText().toString());
                    reference.child("precio").setValue(editTextPrice.getText().toString());
                    reference.child("telefono").setValue(editTextPhone.getText().toString());
                    HomeActivity homeActivity = new HomeActivity();
                    Intent intent = new Intent(CrearServiciosActivity.this, homeActivity.getClass());
                    startActivity(intent);
                    finish();
                }

            }
        });
    }

    private void init() {
        editTextName = findViewById(R.id.serviceName);
        editTextType = findViewById(R.id.serviceType);
        editTextPrice = findViewById(R.id.price);
        editTextPhone = findViewById(R.id.telephone);
        btnSalir = findViewById(R.id.botonVolver);
        btnCrearServicio = findViewById(R.id.crearServicio);
    }
}
