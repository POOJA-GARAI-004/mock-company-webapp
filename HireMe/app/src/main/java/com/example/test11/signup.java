package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class signup extends AppCompatActivity {
    private EditText email;
    private EditText username;
    private EditText password;
    private Button submit;
    private RadioButton rempbtn;
    private RadioButton rhrbtn;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().hide();
        email = (EditText) findViewById(R.id.Email);
        username = (EditText) findViewById(R.id.username1);
        
        password = (EditText) findViewById(R.id.password1);
        submit = (Button) findViewById(R.id.submit_btn);
        rempbtn = (RadioButton) findViewById(R.id.R_empbtn);
        rhrbtn = (RadioButton) findViewById(R.id.R_hrbtn);
        mQueue = Volley.newRequestQueue(this);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                varifyLogin(username.getText().toString());

            }
        });


    }
    private void varifyLogin(String user) {
        String url = "http://"+Localhost.hostIp+"/checkAvailability";
        JSONObject jo = new JSONObject();
        try {

            jo.put("userName", user);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,jo, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                    if(username.getText().toString().equals(response.getString("userName"))){
                        //username same
                        Toast.makeText(signup.this,"UserName Unavailable",Toast.LENGTH_SHORT).show();
                    }else
                    {
                        //incorrect
                        Toast.makeText(signup.this,"Signup Successful",Toast.LENGTH_SHORT).show();
                        if(rempbtn.isChecked()){
                            Intent empinfopg = new Intent(signup.this,empinfo.class);
                            String email_n =email.getText().toString().trim();
                            String user_n =username.getText().toString().trim();
                            String pass_n =password.getText().toString().trim();
                            empinfopg.putExtra("email",email_n);
                            empinfopg.putExtra("username",user_n);
                            empinfopg.putExtra("pass",pass_n);
                            startActivity(empinfopg);
                        }
                        else if(rhrbtn.isChecked()){
                            Intent hrinfopg =new Intent(signup.this,hr_info.class);
                            String email_n =email.getText().toString().trim();
                            String user_n =username.getText().toString().trim();
                            String pass_n =password.getText().toString().trim();
                            hrinfopg.putExtra("email",email_n);
                            hrinfopg.putExtra("username",user_n);
                            hrinfopg.putExtra("pass",pass_n);
                            startActivity(hrinfopg);
                        }
                        else{
                            Toast.makeText(signup.this,"Please select Hr Or Employee",Toast.LENGTH_SHORT).show();
                        }
                    }

                } catch (JSONException e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(signup.this,"Server Down!!",Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }
}