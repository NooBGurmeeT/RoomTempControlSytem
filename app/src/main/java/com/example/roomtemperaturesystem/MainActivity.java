package com.example.roomtemperaturesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText eName;
    private EditText ePassword;
    private TextView eAttemptsInfo;
    private Button eLogin;
    Button btnregister;
    private String Username ;
    private String Password ;

    boolean isValid = false;
    private int count = 10;
//    class Credentials
//    {
//        String name = "Admin";
//        String password = "123456";
//    }
SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        eName = findViewById(R.id.etName);
        ePassword = findViewById(R.id.etPassword);
        eAttemptsInfo = findViewById(R.id.tvAttemptsinfo);
        eLogin = findViewById(R.id.btnLogin);
        btnregister=findViewById(R.id.btnregister);
        preferences=getSharedPreferences("UserInfo",0);
        eLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName =eName.getText().toString();
                String inputPassword =ePassword.getText().toString();
                Username= preferences.getString("Username","");
                Password=preferences.getString("Password","");
                if(inputName.isEmpty()||inputPassword.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter name and password!", Toast.LENGTH_LONG).show();
                }
                else {
                    isValid = validate(inputName,inputPassword);
                    if(!isValid) {
                        count--;
                        eName.setText(Username);
                        Toast.makeText(MainActivity.this, "Incorrect Details Entered!", Toast.LENGTH_LONG).show();
                        eAttemptsInfo.setText("No of Attempts Remainning: " + count);
                        if (count == 0)
                            eLogin.setEnabled(false);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_LONG).show();
                         Intent intent = new Intent(MainActivity.this, System.class);
                         startActivity(intent);
                    }
                }


            }
        });
        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent1);
            }
        });

    }
    private boolean validate(String name, String password){


        /* Check the credentials */
        if(name.equals(Username) && password.equals(Password))
        {
            return true;
        }
        return false;

    }
    public void onBackPressed() {
        finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }
}
