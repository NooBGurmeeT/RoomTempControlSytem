package com.example.roomtemperaturesystem;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class Signup extends AppCompatActivity {
    TextInputEditText  textInputEditTextFullname,textInputEditTextUsername,textInputEditTextPassword,textInputEditTextEmail;
    Button buttonSignUp;
    TextView textViewLogin;
    ProgressBar progressBar;
    FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


        textInputEditTextFullname=findViewById(R.id.fullname);
        textInputEditTextUsername=findViewById(R.id.username);
        textInputEditTextEmail=findViewById(R.id.email);
        textInputEditTextPassword=findViewById(R.id.password);
        buttonSignUp=findViewById((R.id.buttonSignUp));
        textViewLogin=findViewById(R.id.loginText);
        progressBar=findViewById(R.id.progress);
        fAuth=FirebaseAuth.getInstance();

//        if(fAuth.getCurrentUser()!=null)
//        {
//            startActivity(new Intent(getApplicationContext(),Login.class));
//            finish();
//        }

        buttonSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String fullname, username, password, email;
                fullname = String.valueOf(textInputEditTextFullname.getText());
                username = String.valueOf(textInputEditTextUsername.getText());
                password = String.valueOf(textInputEditTextPassword.getText());
                email = String.valueOf(textInputEditTextEmail.getText());
                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(getApplicationContext(),"Registered Successfully",Toast.LENGTH_SHORT).show();
                               startActivity(new Intent(getApplicationContext(),Login.class));
                               finish();
                           }
                           else
                           {
                               Toast.makeText(getApplicationContext(),"Error! "+task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                           }
                        }
                    });
                }
                else
                {
                Toast.makeText(getApplicationContext(),"All fields required",Toast.LENGTH_SHORT).show();
                }
            }
        });
         textViewLogin.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 startActivity(new Intent(getApplicationContext(),Login.class));
                 finish();
             }
         });

    }
    public void onBackPressed() {
        Intent intent1=new Intent(Signup.this,Login.class);
        startActivity(intent1);
    }
}

