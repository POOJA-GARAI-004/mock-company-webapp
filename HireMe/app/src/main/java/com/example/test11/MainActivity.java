package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.RequestFuture;
import com.android.volley.toolbox.Volley;
import com.google.android.material.button.MaterialButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.CountDownLatch;

public class MainActivity extends AppCompatActivity {
    private Button imgbtn;
    private TextView signup;
    private TextView login;
    private EditText username;
    private EditText password;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        signup = (Button) findViewById(R.id.btn_signup);
        login = (Button) findViewById((R.id.loginbtn));
        username = (EditText) findViewById((R.id.username));
        password = (EditText) findViewById(R.id.password);
        imgbtn = (Button) findViewById(R.id.button2);
        mQueue = Volley.newRequestQueue(this);
        imgbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent localhost = new Intent(MainActivity.this,Localhost.class);
                startActivity(localhost);
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signup_intent = new Intent(MainActivity.this,signup.class);
                startActivity(signup_intent);
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                     varifyLogin(username.getText().toString(),password.getText().toString());

                }catch (Exception e){
                    Toast.makeText(MainActivity.this,e.getMessage(),Toast.LENGTH_SHORT).show();
                }
             }
        });

    }
    private void varifyLogin(String user, String pwd) {
        String url = "http://"+Localhost.hostIp+"/login";
        JSONObject jo = new JSONObject();
        try {
            jo.put("userName", user);
            jo.put("password", pwd);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,jo, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(username.getText().toString().equals(response.getString("userName"))  && "Employee".equals(response.getString("role"))){
                        //correct
                        Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                        Intent userpasscorrect = new Intent(MainActivity.this,Userpasscorrect.class);

                        String adi = username.getText().toString().trim();
                        userpasscorrect.putExtra("Hi", adi);
                        startActivity(userpasscorrect);
                    }else if(username.getText().toString().equals(response.getString("userName"))  && "HR".equals(response.getString("role"))){
                        //correct
                        Toast.makeText(MainActivity.this,"LOGIN SUCCESSFUL",Toast.LENGTH_SHORT).show();
                        Intent hrpasscorrect = new Intent(MainActivity.this,Hrpasscorrect.class);

                        String adi = username.getText().toString().trim();
                        hrpasscorrect.putExtra("Hi", adi);
                        startActivity(hrpasscorrect);
                    }else{
                        Toast.makeText(MainActivity.this,"LOGIN FAILED !!!",Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {

                 }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(MainActivity.this,"Server Down!!"+error,Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }
}