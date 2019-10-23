package com.example.appglobation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

import cn.pedant.SweetAlert.SweetAlertDialog;

public class CrearCuentaActivity extends AppCompatActivity {

    private Button btnCrearCuenta, btnVolver;
    private EditText editCorreo, editPassword, editTextFechaNacimiento,editTextNombre,editTextPrimerApellido, editTextSegundoApellido;
    private FirebaseAuth firebaseAuth;
    private static final String ZERO = "0";
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        init();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference("User");

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

        editTextFechaNacimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCalendar();
            }
        });
    }

    public void init(){
        btnCrearCuenta = findViewById(R.id.crearCuenta);
        btnVolver = findViewById(R.id.crearVolver);
        editCorreo = findViewById(R.id.editTextCorreo);
        editPassword = findViewById(R.id.editTextPassword);
        editTextFechaNacimiento = findViewById(R.id.idFechaNacimiento);
        editTextNombre = findViewById(R.id.editTextNombre);
        editTextPrimerApellido = findViewById(R.id.editTextPrimerApellido);
        editTextSegundoApellido = findViewById(R.id.editTextSegundoApellido);
    }

    public void showCalendar() {
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int currentYear = calendar.get(Calendar.YEAR);
        calendar.set(1955,0,1);
        int year = currentYear - 18;


        DatePickerDialog date = new DatePickerDialog(this,R.style.DialogTheme,new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                int currentMonth = month + 1;
                String dayFormat = (dayOfMonth < 10) ? ZERO + dayOfMonth : String.valueOf(dayOfMonth);
                String monthFormat = (month < 9) ? ZERO + currentMonth : String.valueOf(currentMonth);
                editTextFechaNacimiento.setText(year + "-" + monthFormat + "-" + dayFormat);
            }
        },year,month,day);

        Calendar maxCalendar = Calendar.getInstance();
        maxCalendar.set(Calendar.MONTH,month);
        maxCalendar.set(Calendar.DAY_OF_MONTH,day);
        maxCalendar.set(Calendar.YEAR,year);
        date.getDatePicker().setMinDate(calendar.getTimeInMillis());
        date.getDatePicker().setMaxDate(maxCalendar.getTimeInMillis());
        date.show();
    }

    private void createAccount(String email, String pass){
        firebaseAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            DatabaseReference reference = databaseReference.child(firebaseAuth.getCurrentUser().getUid());
                            reference.child("Nombre").setValue(editTextNombre.getText().toString());
                            reference.child("PrimerApellido").setValue(editTextPrimerApellido.getText().toString());
                            reference.child("SegundoApellido").setValue(editTextSegundoApellido.getText().toString());
                            reference.child("FechaNacimiento").setValue(editTextFechaNacimiento.getText().toString());

                            new SweetAlertDialog(CrearCuentaActivity.this, SweetAlertDialog.SUCCESS_TYPE)
                                    .setContentText("Se creo la cuenta.")
                                    .show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    MainActivity mainActivity = new MainActivity();
                                    Intent intent = new Intent(CrearCuentaActivity.this,mainActivity.getClass());
                                    startActivity(intent);
                                }
                            },3000);

                        }else{
                            Toast.makeText(getApplicationContext(),"No se pudo crear cuenta en este momento",Toast.LENGTH_LONG).show();

                        }
                    }
                });
    }
}
