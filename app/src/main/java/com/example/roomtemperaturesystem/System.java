package com.example.roomtemperaturesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;

public class System extends AppCompatActivity {
    Switch fanswitch;
    RadioButton fanspeed1;
    RadioButton fanspeed2;
    RadioButton fanspeed3;
    RadioButton fanspeed4;
    Button cool_mode;
    Button heat_mode;
    Button auto_mode;
    TextView CurrTempsystem;
    TextView ReqTempsystem;
    TextView FanSpeedsystem;
    TextView fanspeedtview;
    Button ExecuteBtn;
    ImageView backcolor;
    TextView expTime;
    MediaPlayer player;
//    boolean checkfanswitch;
//    int fanspeedvalue=0;
    SharedPreferences preferences;
    String mEmail;
    String mSubject="Temperature alert";
    String mMessage = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        fanswitch= findViewById(R.id.switch1);
        fanspeed1=findViewById((R.id.radioButton1));
        fanspeed2=findViewById((R.id.radioButton2));
        fanspeed3=findViewById((R.id.radioButton3));
        fanspeed4=findViewById((R.id.radioButton4));
        cool_mode=findViewById(R.id.button2);
        heat_mode=findViewById(R.id.button4);
        auto_mode=findViewById(R.id.button3);
        fanspeedtview=findViewById(R.id.textView2);
        CurrTempsystem=findViewById((R.id.textView7));
        ReqTempsystem=findViewById((R.id.textView8));
        FanSpeedsystem=findViewById((R.id.textView9));
        ExecuteBtn=findViewById(R.id.button7);
        backcolor=findViewById(R.id.imageview1);
        expTime=findViewById(R.id.textView17);
       player=MediaPlayer.create(getApplicationContext(),R.raw.military_alarm);

        preferences=getSharedPreferences("UserInfo",0);


        fanswitch.setChecked(false);
        fanspeed1.setVisibility(View.INVISIBLE);
        fanspeed2.setVisibility(View.INVISIBLE);
        fanspeed3.setVisibility(View.INVISIBLE);
        fanspeed4.setVisibility(View.INVISIBLE);
        fanspeedtview.setVisibility(View.INVISIBLE);
        FanSpeedsystem.setText("Fan is off");
        ExecuteBtn.setVisibility(View.INVISIBLE);
        fanswitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fanswitch.isChecked())
                {
                    fanspeed1.setVisibility(View.VISIBLE);
                    fanspeed2.setVisibility(View.VISIBLE);
                    fanspeed3.setVisibility(View.VISIBLE);
                    fanspeed4.setVisibility(View.VISIBLE);
                    fanspeedtview.setVisibility(View.VISIBLE);
                    fanspeed1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //fanspeedvalue=25;
                            FanSpeedsystem.setText("25");
                            fanspeed2.setChecked(false);
                            fanspeed3.setChecked(false);
                            fanspeed4.setChecked(false);


                        }
                    });
                    fanspeed2.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //fanspeedvalue=25;
                            FanSpeedsystem.setText("50");
                            fanspeed1.setChecked(false);
                            fanspeed3.setChecked(false);
                            fanspeed4.setChecked(false);


                        }
                    });
                    fanspeed3.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //fanspeedvalue=25;
                            FanSpeedsystem.setText("75");
                            fanspeed1.setChecked(false);
                            fanspeed2.setChecked(false);
                            fanspeed4.setChecked(false);


                        }
                    });
                    fanspeed4.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            //fanspeedvalue=25;
                            FanSpeedsystem.setText("100");
                            fanspeed2.setChecked(false);
                            fanspeed3.setChecked(false);
                            fanspeed1.setChecked(false);


                        }
                    });
                }
                else
                {
                    fanspeed1.setVisibility(View.INVISIBLE);
                    fanspeed2.setVisibility(View.INVISIBLE);
                    fanspeed3.setVisibility(View.INVISIBLE);
                    fanspeed4.setVisibility(View.INVISIBLE);
                    fanspeedtview.setVisibility(View.INVISIBLE);
                    fanspeed1.setChecked(false);
                    fanspeed2.setChecked(false);
                    fanspeed3.setChecked(false);
                    fanspeed1.setChecked(false);
                    FanSpeedsystem.setText("Fan is off");
                }
            }
        });




        final int[] check={0};
        double leftLimit = -80.00;
        double rightLimit = 80.00;
        final double[] CurrTempValue = {leftLimit + new Random().nextDouble() * (rightLimit - leftLimit)};
        CurrTempsystem.setText(String.format(Locale.US,"%.2f", CurrTempValue[0])+" °C");


        mEmail=preferences.getString("Email","");




       cool_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(System.this,Cooling.class);
                String value=Double.toString(CurrTempValue[0]);
                Bundle extras = new Bundle();
                extras.putString("CurrTempValue",value);
                intent1.putExtras(extras);
                startActivity(intent1);
            }
        });
        heat_mode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(System.this,Heating.class);
                String value=Double.toString(CurrTempValue[0]);
                Bundle extras = new Bundle();
                extras.putString("CurrTempValue",value);
                intent1.putExtras(extras);
                startActivity(intent1);
            }
        });

        final double[] ReqTempValue={0.00};

        final Intent intent=getIntent();
        String value1=intent.getStringExtra("ReqCoolTempValue");
        String value2=intent.getStringExtra("ReqHeatTempValue");
        if(value1 != null)
        {
            ReqTempValue[0] = Double.parseDouble(value1);
            ReqTempsystem.setText(String.format(Locale.US, "%.2f", ReqTempValue[0])+" °C");
            ExecuteBtn.setVisibility(View.VISIBLE);
            ExecuteBtn.setText("EXECUTE COOLING");
            String value3=intent.getStringExtra("CurrCoolTempValue2");
            assert value3 != null;
            CurrTempValue[0]=Double.parseDouble(value3);
            CurrTempsystem.setText( String.format(Locale.US,"%.2f",Double.parseDouble(value3))+" °C");
            expTime.setText(String.format(Locale.US,"%.2f",Math.abs(CurrTempValue[0]-ReqTempValue[0]))+" sec");
            check[0]=1;
        }
        if(value2!=null)
        {
            ReqTempValue[0] = Double.parseDouble(value2);
            ReqTempsystem.setText(String.format(Locale.US, "%.2f", ReqTempValue[0])+" °C");
            ExecuteBtn.setVisibility(View.VISIBLE);
            ExecuteBtn.setText("EXECUTE HEATING");
            String value3=intent.getStringExtra("CurrheatTempValue2");
            assert value3 != null;
            CurrTempsystem.setText( String.format(Locale.US,"%.2f",Double.parseDouble(value3))+" °C");
            CurrTempValue[0]=Double.parseDouble(value3);
            check[0]=2;
            expTime.setText(String.format(Locale.US,"%.2f",Math.abs(CurrTempValue[0]-ReqTempValue[0]))+" sec");
        }








        if(CurrTempValue[0]<-50||CurrTempValue[0]>50)
        {
            player.start();
            cool_mode.setEnabled(false);
            heat_mode.setEnabled(false);
            auto_mode.setEnabled(false);
            final int[] check1={0};
            ReqTempsystem.setText("20.00 °C");
            if(CurrTempValue[0]<-50)
            {
                mMessage="";
                String s1="The Temperature of room :-";
                String s2=String.format(Locale.US,"%.2f",CurrTempValue[0]);
                String s3="is dangerous!. Please evacuate the room. The temperature will ne normal in";
                String s4=String.format(Locale.US,"%.2f",Math.abs(CurrTempValue[0]-20));
                mMessage =s1+s2+s3+s4;
                sendAlertMail();
                check1[0]=1;

            }
            if(CurrTempValue[0]>50)
            {
                mMessage ="";
                String s1="The Temperature of room :-";
                String s2=String.format(Locale.US,"%.2f",CurrTempValue[0]);
                String s3="is dangerous!. Please evacuate the room. The temperature will ne normal in";
                String s4=String.format(Locale.US,"%.2f",Math.abs(CurrTempValue[0]-20));
                mMessage=s1+s2+s3+s4;
                sendAlertMail();
                check1[0]=2;
            }


            final boolean[] continueThread = {true};
            final Thread t=new Thread(){
                final int[]count1={0};
                @Override
                public void run(){

                    while(continueThread[0]){

                        try {
                            Thread.sleep(1000);  //1000ms = 1 sec

                            runOnUiThread(new Runnable() {

                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    if(check1[0]==1)
                                    {
                                        CurrTempValue[0] +=1.00;
                                       // final double ReqcooltempValue=Double.parseDouble(ReqTempsystem.getText().toString());
                                        CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                        double exptime=Math.abs(CurrTempValue[0]-20);
                                        expTime.setText(String.format(Locale.US,"%.2f",exptime)+" sec");
                                        if(count1[0]==0||count1[0]==2)
                                        {
                                            Toast.makeText(System.this, "LOW TEMP Alert,Normal state is bieng achieved", Toast.LENGTH_SHORT).show();
                                        }
                                        if(count1[0]%2==0)
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(255,0,0));

                                            count1[0]++;
                                        }
                                        else {
                                            backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                            count1[0]++;
                                        }
                                        if(CurrTempValue[0]+1>=20)
                                        {
                                            CurrTempValue[0]=20;
                                            CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                            Toast.makeText(System.this, "Normal state has been achieved", Toast.LENGTH_LONG).show();
                                            backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                            expTime.setText("0.00 sec");
                                            cool_mode.setEnabled(true);
                                            heat_mode.setEnabled(true);
                                            auto_mode.setEnabled(true);
                                            mMessage="The room temperature has been normalized, You can enter the room";
                                            sendAlertMail();
                                            continueThread[0]=false;
                                        }
                                    }
                                    if(check1[0]==2)
                                    {
                                        CurrTempValue[0] -=1.00;
                                        //final double ReqcooltempValue=Double.parseDouble(ReqTempsystem.getText().toString());
                                        CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                        if(count1[0]==0||count1[0]==2)
                                        {
                                            Toast.makeText(System.this, "High TEMP Alert,Normal state is bieng achieved", Toast.LENGTH_SHORT).show();
                                        }
                                        if(count1[0]%2==0)
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(255,0,0));

                                            count1[0]++;
                                        }
                                        else {
                                            backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                            //Toast.makeText(System.this, "High TEMP Alert,Normal state is bieng achieved", Toast.LENGTH_SHORT).show();
                                            count1[0]++;
                                        }
                                        if(CurrTempValue[0]-1<=20)
                                        {
                                            CurrTempValue[0]=20;
                                            CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                            Toast.makeText(System.this, "Normal state has been achieved", Toast.LENGTH_LONG).show();
                                            backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                            expTime.setText("0.00 sec");
                                            cool_mode.setEnabled(true);
                                            heat_mode.setEnabled(true);
                                            auto_mode.setEnabled(true);
                                            mMessage="The room temperature has been normalized, You can enter the room";
                                            sendAlertMail();
                                            continueThread[0]=false;
                                        }
                                    }

                                    }

                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            };
            t.start();

        }

       ExecuteBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               final boolean[] continueThread = {true};
//
               final double[] CurrCoolTempValue1 = {CurrTempValue[0]};
               final int[]count={0} ;
               final Thread t=new Thread(){

                @Override
                public void run(){

                    while(continueThread[0]){

                        try {
                            Thread.sleep(1000);  //1000ms = 1 sec

                            runOnUiThread(new Runnable() {

                                @SuppressLint("SetTextI18n")
                                @Override
                                public void run() {
                                    if(check[0]==1)
                                    {

                                        CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrCoolTempValue1[0])+" °C");

                                        if(count[0]==0)
                                        {
                                            mMessage="";
                                            String s1="Current temp:";
                                            String s2=String.format(Locale.US,"%.2f",CurrCoolTempValue1[0]);
                                            String s3="  Required Temp:";
                                            String s4=String.format(Locale.US,"%.2f",ReqTempValue[0]);
                                            String s5=" Mode:- Cooling";
                                            String s6="   Expexted Time:-";
                                            String s7=String.format(Locale.US,"%.2f",CurrCoolTempValue1[0]-ReqTempValue[0]);
                                            mMessage=s1+s2+s3+s4+s5+s6+s7;
                                            sendAlertMail();
                                        }
                                        if(count[0]%2==0)
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(224,247,250));
                                            count[0]++;
                                        }
                                        else
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(245,245,245));
                                            count[0]++;
                                        }
                                        double exptime=Math.abs(CurrCoolTempValue1[0]-ReqTempValue[0]);
                                        expTime.setText(String.format(Locale.US,"%.2f",exptime)+" sec");
                                        CurrCoolTempValue1[0] -=1.00;
                                        if(CurrCoolTempValue1[0]-1.0<=ReqTempValue[0])
                                        {
                                            continueThread[0] =false;
                                            expTime.setText("0.00 sec");
                                            CurrTempsystem.setText(String.format(Locale.US,"%.2f",ReqTempValue[0])+" °C");
                                            Toast.makeText(System.this,"COOLING Completed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                    if(check[0]==2)
                                    {


                                        CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrCoolTempValue1[0])+" °C");

                                        if(count[0]==0)
                                        {
                                            mMessage="";
                                            String s1="Current temp:";
                                            String s2=String.format(Locale.US,"%.2f",CurrCoolTempValue1[0]);
                                            String s3="  Required Temp:";
                                            String s4=String.format(Locale.US,"%.2f",ReqTempValue[0]);
                                            String s5=" Mode:- Heating";
                                            String s6="   Expexted Time:-";
                                            String s7=String.format(Locale.US,"%.2f",-CurrCoolTempValue1[0]+ReqTempValue[0]);
                                            mMessage=s1+s2+s3+s4+s5+s6+s7;
                                            sendAlertMail();
                                        }
                                        if(count[0]%2==0)
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(255,245,157));
                                            count[0]++;
                                        }
                                        else
                                        {
                                            backcolor.setBackgroundColor(Color.rgb(245,245,245));
                                            count[0]++;
                                        }
                                        CurrCoolTempValue1[0] +=1.00;
                                        double exptime=Math.abs(CurrCoolTempValue1[0]-ReqTempValue[0]);
                                        expTime.setText(String.format(Locale.US,"%.2f",exptime)+" sec");
                                        if(CurrCoolTempValue1[0]+1.0>=ReqTempValue[0])
                                        {
                                            continueThread[0] =false;
                                            expTime.setText("0.00 sec");
                                            CurrTempsystem.setText(String.format(Locale.US,"%.2f",ReqTempValue[0])+" °C");
                                            Toast.makeText(System.this,"HEATING Completed", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                            });

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }

             };
               t.start();
       }
       });













       auto_mode.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               ReqTempsystem.setText("20.00 °C");
               if(CurrTempValue[0]<20||CurrTempValue[0]>20)
               {
                   final int[] check1={0};
                   if(CurrTempValue[0]<20)
                   {
                       mMessage="";
                       String s1="Current temp: ";
                       String s2=String.format(Locale.US,"%.2f",CurrTempValue[0]);
                       String s3="  Required Temp: 20";
                       String s5="  Mode:- Auto Heating";
                       String s6="  Expexted Time:-";
                       String s7=String.format(Locale.US,"%.2f",-CurrTempValue[0]+20);
                       mMessage=s1+s2+s3+s5+s6+s7;
                       sendAlertMail();
                       check1[0]=1;
                   }
                   if(CurrTempValue[0]>20)
                   {
                       mMessage="";
                       String s1="Current temp:";
                       String s2=String.format(Locale.US,"%.2f",CurrTempValue[0]);
                       String s3="  Required Temp: 20";
                       String s5=" Mode:- Auto Cooling ";
                       String s6="   Expexted Time: ";
                       String s7=String.format(Locale.US,"%.2f",CurrTempValue[0]-20);
                       mMessage=s1+s2+s3+s5+s6+s7;
                       sendAlertMail();
                       check1[0]=2;
                   }

                   ReqTempsystem.setText("20.00");
                   final boolean[] continueThread = {true};
                   final Thread t=new Thread(){
                       final int[]count1={0};
                       @Override
                       public void run(){

                           while(continueThread[0]){

                               try {
                                   Thread.sleep(1000);  //1000ms = 1 sec

                                   runOnUiThread(new Runnable() {

                                       @SuppressLint("SetTextI18n")
                                       @Override
                                       public void run() {
                                           if(check1[0]==1)
                                           {
                                               CurrTempValue[0] +=1.00;
                                               // final double ReqcooltempValue=Double.parseDouble(ReqTempsystem.getText().toString());
                                               CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                               double exptime=Math.abs(CurrTempValue[0]-20);
                                               expTime.setText(String.format(Locale.US,"%.2f",exptime)+" sec");
                                               if(count1[0]==0||count1[0]==2)
                                               {
                                                   Toast.makeText(System.this, "Auto Heating Mode ON", Toast.LENGTH_SHORT).show();
                                               }
                                               if(count1[0]%2==0)
                                               {
                                                   backcolor.setBackgroundColor(Color.rgb(255,245,157));

                                                   count1[0]++;
                                               }
                                               else {
                                                   backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                                   count1[0]++;
                                               }
                                               if(CurrTempValue[0]+1>=20)
                                               {
                                                   CurrTempValue[0]=20;
                                                   CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                                   Toast.makeText(System.this, "Required Temparature  has been achieved", Toast.LENGTH_LONG).show();
                                                   backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                                   expTime.setText("0.00 sec");

                                                   //mMessage="The room temperature has been normalized, You can enter the room";
                                                   //sendAlertMail();
                                                   continueThread[0]=false;
                                                   //finish();
                                               }
                                           }
                                           if(check1[0]==2)
                                           {
                                               CurrTempValue[0] -=1.00;
                                               //final double ReqcooltempValue=Double.parseDouble(ReqTempsystem.getText().toString());
                                               CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0]) +" °C");
                                               if(count1[0]==0||count1[0]==2)
                                               {
                                                   Toast.makeText(System.this, "Auto Cooling Mode ON", Toast.LENGTH_SHORT).show();
                                               }
                                               if(count1[0]%2==0)
                                               {

                                                   backcolor.setBackgroundColor(Color.rgb(224,247,250));

                                                   count1[0]++;
                                               }
                                               else {
                                                   backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                                   //Toast.makeText(System.this, "High TEMP Alert,Normal state is bieng achieved", Toast.LENGTH_SHORT).show();
                                                   count1[0]++;
                                               }
                                               if(CurrTempValue[0]-1<=20)
                                               {
                                                   CurrTempValue[0]=20;
                                                   CurrTempsystem.setText(String.format(Locale.US,"%.2f",CurrTempValue[0])+" °C");
                                                   Toast.makeText(System.this, "Required Temparature  has been achieved", Toast.LENGTH_LONG).show();
                                                   backcolor.setBackgroundColor(Color.rgb(245, 245, 245));
                                                   expTime.setText("0.00 sec");
                                                   //mMessage="Required Temparature  has been achieved";
                                                   //sendAlertMail();
                                                   continueThread[0]=false;
                                                   //finish();
                                               }
                                           }

                                       }

                                   });

                               } catch (InterruptedException e) {
                                   e.printStackTrace();
                               }

                           }
                       }
                   };
                   t.start();

               }
           }
       });


//
//
//        cooling_mode.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                t.start();
//            }
//        });



    }

    public void sendAlertMail() {

        JavaMailAPI javaMailAPI=new JavaMailAPI(System.this,mEmail,mSubject,mMessage);
         javaMailAPI.execute();
    }
    @Override
    public void onBackPressed() {
        Intent intent1=new Intent(System.this,Login.class);
        startActivity(intent1);
    }
}
