package com.example.roomtemperaturesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Heating extends AppCompatActivity {

    EditText ReqheatTempetext;
    TextView Currheattemptview;
    Button heating_mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heating);

        ReqheatTempetext=findViewById(R.id.editText2);
        Currheattemptview=findViewById(R.id.textView15);
        heating_mode=findViewById(R.id.button6);
//

        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        final String value = extras.getString("CurrTempValue");
        final double CurrTempValue =Double.parseDouble(value);
        Currheattemptview.setText(String.format(Locale.US,"%.2f",CurrTempValue)+" °C");
        //Currheattemptview.addTextChangedListener();
        //Currheattemptview.addTextChangedListener(logincheck);



        heating_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //t.start();
                //final double ReqheatTempValue=Double.parseDouble(ReqheatTempetext.getText().toString());
                String value1=ReqheatTempetext.getText().toString();
                if(TextUtils.isEmpty(ReqheatTempetext.getText().toString()))
                {
                    Toast.makeText(Heating.this, "plz enter value between "+String.format(Locale.US,"%.2f",CurrTempValue)+"and 50.00", Toast.LENGTH_SHORT).show();
                    //return;
                }
                else
                {
                    final double ReqheatTempValue=Double.parseDouble(value1);
                    if(ReqheatTempValue<CurrTempValue)
                    {
                        Toast.makeText(Heating.this, "plz enter value between "+String.format(Locale.US,"%.2f",CurrTempValue)+"and 50.00", Toast.LENGTH_SHORT).show();
                    }
                    else if(ReqheatTempValue>50.00)
                    {
                        Toast.makeText(Heating.this, "plz enter value between "+String.format(Locale.US,"%.2f",CurrTempValue)+"and 50.00", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        Intent intent= new Intent(Heating.this,System.class);
                        //String value=Double.toString(ReqCoolTempValue);
                        intent.putExtra("ReqHeatTempValue",value1);
                        int requestCode=1;
                        intent.putExtra("CurrheatTempValue2",Double.toString(CurrTempValue));
                        startActivityForResult(intent,requestCode);
                    }
                }
            }
        });
    }
//    private TextWatcher logincheck=new TextWatcher() {
//        @Override
//        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
//            String s1=Currheattemptview.getText().toString().trim();
//            heating_mode.setEnabled(!s1.isEmpty());
//        }
//
//        @Override
//        public void afterTextChanged(Editable editable) {
//
//        }
//    };
}
