package com.example.chattingandcallingapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText phone, sms;
    private Button send;
    private ImageView call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        phone = findViewById(R.id.phoneNum);
        sms = findViewById(R.id.smstext);
        send = findViewById(R.id.send);
        call = findViewById(R.id.call);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone.getText().toString(), null, sms.getText().toString(), null, null);
                    Toast.makeText(MainActivity.this, "SMS sent", Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Sending Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().length() == 10)
                {
                    String dial = "tel:" + phone.getText().toString();
                    startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
                }
                else
                    Toast.makeText(MainActivity.this, "Enter a valid 10 digit number", Toast.LENGTH_SHORT).show();

            }
        });

    }
}