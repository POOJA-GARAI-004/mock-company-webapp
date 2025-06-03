package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Localhost extends AppCompatActivity {
    private EditText ipadd;
    private Button done;
    public static String hostIp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_localhost);
        done = findViewById(R.id.button);
        ipadd = findViewById(R.id.editTextTextPersonName);
        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 hostIp = ipadd.getText().toString().trim();
                Intent localhost = new Intent(Localhost.this,MainActivity.class);
                startActivity(localhost);
            }
        });
    }
}