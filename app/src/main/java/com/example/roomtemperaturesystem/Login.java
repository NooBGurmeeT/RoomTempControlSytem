package com.example.roomtemperaturesystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
   TextInputEditText eMail,Password;
   Button login;
   TextView textViewSignup;
   ProgressBar progressBar;
   FirebaseAuth fAuth;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        eMail=findViewById(R.id.username);
        Password=findViewById(R.id.password);
        progressBar=findViewById(R.id.progress);
        login=findViewById(R.id.buttonLogin);
        textViewSignup=findViewById(R.id.signUpText);
        fAuth=FirebaseAuth.getInstance();

        preferences=getSharedPreferences("UserInfo",0);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname, username, password, email;
                //fullname = String.valueOf(textInputEditTextFullname.getText());
                email = String.valueOf(eMail.getText());
                password = String.valueOf(Password.getText());

                if (!password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                SharedPreferences.Editor editor=preferences.edit();
                                editor.putString("Email",email);
                                editor.apply();
                                Toast.makeText(Login.this,"Login Successfully",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),System.class));
                                finish();
                            }
                            else
                            {
                                Toast.makeText(Login.this,"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(Login.this,"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });
        textViewSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));
                finish();
            }
        });


    }
    public void onBackPressed() {
        finishAffinity();
    }
}
