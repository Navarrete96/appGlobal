package com.example.appglobation;


import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {

    private Button btnLogin, btnCrear;

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_login, container, false);
        init(view);

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrearCuentaActivity cuentaActivity = new CrearCuentaActivity();
                Intent intent = new Intent(getContext(),cuentaActivity.getClass());
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HomeActivity homeActivity = new HomeActivity();
                Intent intent = new Intent(getContext(),homeActivity.getClass());
                startActivity(intent);
            }
        });
        return view;
    }

    private void init(View v){
        btnCrear = v.findViewById(R.id.btnCrear);
        btnLogin = v.findViewById(R.id.btnLogin);
    }

}
