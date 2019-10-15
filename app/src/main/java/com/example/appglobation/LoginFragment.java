package com.example.appglobation;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import cn.pedant.SweetAlert.SweetAlertDialog;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button btnLogin, btnCrear;
    private EditText editTextEmail, editTextPassword;
    private FirebaseAuth auth;

    public LoginFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        init(view);
        auth = FirebaseAuth.getInstance();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearCuentaActivity cuentaActivity = new CrearCuentaActivity();
                Intent intent = new Intent(getContext(), cuentaActivity.getClass());
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (editTextEmail.getText().toString().isEmpty() || editTextPassword.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Los campos estan vacios", Toast.LENGTH_LONG).show();
                } else {
                    login(editTextEmail.getText().toString(), editTextPassword.getText().toString());
                }

            }
        });
        return view;
    }

    private void init(View v) {
        btnCrear = v.findViewById(R.id.btnCrear);
        btnLogin = v.findViewById(R.id.btnLogin);
        editTextEmail = v.findViewById(R.id.editEmail);
        editTextPassword = v.findViewById(R.id.editPassword);
    }

    private void login(String email, String password) {
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setContentText("Bienvenido!")
                                    .show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    HomeActivity homeActivity = new HomeActivity();
                                    Intent intent = new Intent(getContext(), homeActivity.getClass());
                                    startActivity(intent);
                                }
                            },3000);

                        } else {
                            new SweetAlertDialog(getContext(), SweetAlertDialog.ERROR_TYPE)
                                    .setTitleText("Oops...")
                                    .setContentText("El correo o contraseña son incorrectos")
                                    .show();
                        }
                    }
                });
    }

}
