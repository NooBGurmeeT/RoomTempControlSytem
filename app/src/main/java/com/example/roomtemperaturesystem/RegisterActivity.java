package com.example.roomtemperaturesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {
    EditText etUsername;
    EditText etEmail;
    EditText etPassword;
    EditText etFullname;
    Button btnRegister;
    //String str_name,str_email,str_password;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etUsername=findViewById(R.id.etFullname);
        etUsername=findViewById(R.id.etUsername);
        etEmail=findViewById(R.id.etEmail);
        etPassword=findViewById(R.id.etPassword);
        btnRegister=findViewById(R.id.butRegister);

        preferences=getSharedPreferences("UserInfo",0);
         btnRegister.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 String username,email,password;
                 //fullname=etFullname.getText().toString();
                 username=etUsername.getText().toString();
                 email=etEmail.getText().toString();
                 password=etPassword.getText().toString();
                 if(username.equals("")||email.equals("")||password.equals(""))
                 {
                     Toast.makeText(RegisterActivity.this, "All field required", Toast.LENGTH_SHORT).show();
                 }
                 else {

                     SharedPreferences.Editor editor=preferences.edit();
                     editor.putString("Username",username);
                     editor.putString("Email",email);
                     editor.putString("Password",password);
                     editor.apply();
                     Toast.makeText(RegisterActivity.this, "Success", Toast.LENGTH_SHORT).show();
                     Intent intent1 = new Intent(RegisterActivity.this, MainActivity.class);
                     startActivity(intent1);
                 }
             }
         });

    }
}
