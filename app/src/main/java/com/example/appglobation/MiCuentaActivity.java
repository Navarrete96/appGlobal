package com.example.appglobation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MiCuentaActivity extends AppCompatActivity {


    private EditText editTextName, editTextPrimerApellido, editTextSegundo;
    private DatabaseReference databaseReference;
    private DatabaseReference databaseReferencePrimerApellido;
    private DatabaseReference databaseReferenceSegundoApellido;
    private FirebaseAuth firebaseAuth;
    private Button buttonVerMisServicios, buttonVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_cuenta);
        init();
    }

    public void init(){
        editTextName = findViewById(R.id.editTextNombre);
        editTextPrimerApellido = findViewById(R.id.idEditextPrimerApellido);
        editTextSegundo = findViewById(R.id.idEditextSegundoApellido);
        buttonVerMisServicios = findViewById(R.id.buttonVerMisServicios);
        buttonVolver = findViewById(R.id.buttonVolverMenu);
        firebaseAuth = FirebaseAuth.getInstance();
        showInformation();

        buttonVerMisServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        buttonVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeActivity  homeActivity = new HomeActivity();
                Intent intent = new Intent(MiCuentaActivity.this,homeActivity.getClass());
                startActivity(intent);
                finish();
            }
        });
    }

    public void showInformation(){

        String id = firebaseAuth.getCurrentUser().getUid();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User").child(id).child("Nombre");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    editTextName.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        databaseReferencePrimerApellido = FirebaseDatabase.getInstance().getReference().child("User").child(id).child("PrimerApellido");
        databaseReferencePrimerApellido.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editTextPrimerApellido.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReferenceSegundoApellido = FirebaseDatabase.getInstance().getReference().child("User").child(id).child("SegundoApellido");
        databaseReferenceSegundoApellido.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                editTextSegundo.setText(dataSnapshot.getValue(String.class));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
