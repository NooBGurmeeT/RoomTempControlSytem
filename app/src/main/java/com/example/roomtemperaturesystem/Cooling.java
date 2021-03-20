package com.example.roomtemperaturesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
import java.util.ResourceBundle;

public class Cooling extends AppCompatActivity {
     EditText ReqcoolTempetext;
     TextView Currcooltemptview;
     Button cooling_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooling3);

        ReqcoolTempetext=findViewById(R.id.editText1);
        Currcooltemptview=findViewById(R.id.textView12);
        cooling_mode=findViewById(R.id.button5);
//

        final Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String value = extras.getString("CurrTempValue");
        final double CurrTempValue =Double.parseDouble(value);
        Currcooltemptview.setText(String.format(Locale.US,"%.2f",CurrTempValue)+" °C");
//
        cooling_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //t.start();
                //final double ReqheatTempValue=Double.parseDouble(ReqheatTempetext.getText().toString());
                String value1=ReqcoolTempetext.getText().toString();
                if(TextUtils.isEmpty(ReqcoolTempetext.getText().toString()))
                {
                    Toast.makeText(Cooling.this, "plz enter value between  -50.00 and "+String.format(Locale.US,"%.2f",CurrTempValue), Toast.LENGTH_SHORT).show();
                    //return;
                }
                else
                {
                    final double ReqcoolTempValue=Double.parseDouble(value1);
                    if(ReqcoolTempValue<-50.00||ReqcoolTempValue>CurrTempValue)
                    {
                        Toast.makeText(Cooling.this, "plz enter value between  -50.00 and "+String.format(Locale.US,"%.2f",CurrTempValue), Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent= new Intent(Cooling.this,System.class);
                        //String value=Double.toString(ReqCoolTempValue);
                        intent.putExtra("ReqCoolTempValue",value1);
                        //String value2=Double.toString()
                        intent.putExtra("CurrCoolTempValue2",value);
                        int requestCode=1;
                        startActivityForResult(intent,requestCode);
                    }
                }
            }
        });

    }
}

