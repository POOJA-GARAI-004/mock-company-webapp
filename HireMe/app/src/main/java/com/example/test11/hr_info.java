package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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

public class hr_info extends AppCompatActivity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {
    private RequestQueue mQueue;
    TextView e_mail;
    TextView user_name;
    TextView pass_word;
    EditText hrName;
    EditText componyName;
    Spinner qualification;
    EditText techSkill;
    EditText exp;
    String text;

    String email_n;
    String user_n;
    String pass_n;
    String Name;
    String compName;
    String skills;
    String Exp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hr_info);
        getSupportActionBar().hide();
        mQueue = Volley.newRequestQueue(this);

        qualification = findViewById(R.id.spin_hrqulif);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.qualif, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        qualification.setAdapter(adapter);
        qualification.setOnItemSelectedListener(this);

        e_mail = findViewById(R.id.tv_displayemail);
        user_name = findViewById(R.id.tv_displayuser);
        pass_word = findViewById(R.id.tv_displaypass);
        hrName = findViewById(R.id.edthr_name);
        componyName = findViewById(R.id.edtcomp_name);
        techSkill = findViewById(R.id.edthr_skill);
        exp = findViewById(R.id.edthr_exp);
        Button submit =findViewById(R.id.hrbtn_submit);
        Intent empinfopg =getIntent();

        email_n = empinfopg.getStringExtra("email");
        e_mail.setText("email:"+email_n);
        user_n = empinfopg.getStringExtra("username");
        user_name.setText("username:"+user_n);
        pass_n = empinfopg.getStringExtra("pass");
        pass_word.setText("password:"+pass_n);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Name = hrName.getText().toString().trim();
                compName = componyName.getText().toString().trim();
                skills = techSkill.getText().toString().trim();
                Exp = exp.getText().toString().trim();
                varifyLogin();
            }
        });

    }
    private void varifyLogin() {
        String Type ="Hr";
        String url = "http://"+Localhost.hostIp+"/hrInfoTransfer";
        JSONObject jo = new JSONObject();
        try {
            jo.put("email", email_n);
            jo.put("userName", user_n);
            jo.put("password", pass_n);
            jo.put("name", Name);
            jo.put("company", compName);
            jo.put("qualif",text);
            jo.put("techSkills", skills);
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
                    Intent redirect = new Intent(hr_info.this,MainActivity.class);
                    startActivity(redirect);
                } catch (Exception e) {

                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(hr_info.this,"Server Down!!",Toast.LENGTH_SHORT).show();

            }
        });
        mQueue.add(request);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        text = adapterView.getItemAtPosition(i).toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {


    }
}