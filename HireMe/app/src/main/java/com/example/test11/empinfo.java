package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class empinfo extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private RequestQueue mQueue;
    TextView e_mail;
    TextView user_name;
    TextView pass_word;
    EditText name;
    Spinner qualification;
    EditText techSkill;
    EditText exp;

    String Email;
    String User;
    String Pass;
    String Name;
    String Qualif;
    String TechS;
    String Exp;
    String Type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empinfo);
        getSupportActionBar().hide();
        mQueue = Volley.newRequestQueue(this);

        qualification = findViewById(R.id.spin_Qualif);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.qualif, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(adapter);
        qualification.setOnItemSelectedListener(this);

         e_mail = findViewById(R.id.tv_displayemail);
         user_name = findViewById(R.id.tv_displayuser);
         pass_word = findViewById(R.id.tv_displaypass);
         name = findViewById(R.id.edt_name);
         techSkill = findViewById(R.id.edt_techs);
         exp = findViewById(R.id.edt_exp);
        Button submit =findViewById(R.id.submit_btn);
        Intent empinfopg =getIntent();

        Email = empinfopg.getStringExtra("email");
        e_mail.setText("email:"+Email);
        User = empinfopg.getStringExtra("username");
        user_name.setText("username:"+User);
        Pass = empinfopg.getStringExtra("pass");
        pass_word.setText("password:"+Pass);




        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Name = name.getText().toString();
                 TechS = techSkill.getText().toString().trim();
                 Exp = exp.getText().toString().trim();

                varifyLogin();
            }
        });

    }
  private void varifyLogin() {
      Type ="Employee";
        String url = "http://"+Localhost.hostIp+"/empInfoTransfer";
        JSONObject jo = new JSONObject();
        try {
            jo.put("email", Email);
            jo.put("userName", User);
            jo.put("password", Pass);
            jo.put("name", Name);
            jo.put("qualif",Qualif);
            jo.put("techSkills", TechS);
            jo.put("experience", Exp);
            jo.put("type", Type);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                url,jo, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                try {
                   /* Email=response.getString("email");
                    User=response.getString("userName");
                    Pass=response.getString("password");
                    Name=response.getString("name");
                    Qualif = response.getString("qualif");
                    TechS= response.getString("techSkills");
                    Exp=response.getString("experience");
                    Type=response.getString("type");

                    Intent tohrpg = new Intent(empinfo.this,Hrpasscorrect.class);
                    tohrpg.putExtra("email", Email);
                    tohrpg.putExtra("userName", User);
                    tohrpg.putExtra("password", Pass);
                    tohrpg.putExtra("name", Name);
                    tohrpg.putExtra("qualif", Qualif);
                    tohrpg.putExtra("techSkills", TechS);
                    tohrpg.putExtra("experience", Exp);*/

                    Intent redirect = new Intent(empinfo.this,MainActivity.class);
                    startActivity(redirect);
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(empinfo.this,"Server Down!!",Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        Qualif = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}