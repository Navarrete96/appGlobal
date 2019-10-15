package com.example.appglobation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class CrearCuentaActivity extends AppCompatActivity {

    private Button btnCrearCuenta, btnVolver;
    private EditText editCorreo, editPassword;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        init();
        firebaseAuth = FirebaseAuth.getInstance();

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
                createAccount(editCorreo.getText().toString(),editPassword.getText().toString());

            }
        });
    }

    public void init(){
        btnCrearCuenta = findViewById(R.id.crearCuenta);
        btnVolver = findViewById(R.id.crearVolver);
        editCorreo = findViewById(R.id.editTextCorreo);
        editPassword = findViewById(R.id.editTextPassword);
    }

    private void createAccount(String email, String pass){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            MainActivity mainActivity = new MainActivity();
                            Intent intent = new Intent(CrearCuentaActivity.this,mainActivity.getClass());
                            startActivity(intent);
                        }else{


                        }
                    }
                });
    }
}
