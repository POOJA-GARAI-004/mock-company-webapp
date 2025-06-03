package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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

import java.util.Iterator;

public class Hrpasscorrect extends AppCompatActivity {
    private RequestQueue mQueue;
    TextView username;
    LinearLayout layout;
    Button detail;
    String empName;
    String empEmail;
    String empQulif;
    String empTechs;
    String empExp;
    EditText search_bar;
    ImageButton search_btn;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hrpasscorrect);
        getSupportActionBar().hide();

        username = findViewById(R.id.tv_displayusername);
        Intent login_topwd =getIntent();
        String displayusername = login_topwd.getStringExtra("Hi");
        username.setText("Hi "+ displayusername);

        layout = findViewById(R.id.layout);
        mQueue = Volley.newRequestQueue(this);


        search_bar = findViewById(R.id.search_bar);
        search_btn = findViewById(R.id.search_button);

        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               search = search_bar.getText().toString().trim().toLowerCase();

                String urlSearch = "http://"+Localhost.hostIp+"/getSearchedEmp";
                JSONObject jo = new JSONObject();
                try {
                    jo.put("search", search);
                } catch (JSONException e) {
                    e.printStackTrace();
                }



                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        urlSearch, jo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            layout.removeAllViews();
                            Iterator<String> iterator = response.keys();
                            while(iterator.hasNext()){
                                View view = getLayoutInflater().inflate(R.layout.card, null);

                                TextView nameView = view.findViewById(R.id.name);
                                TextView skillView = view.findViewById(R.id.skill);
                                TextView expView = view.findViewById(R.id.exp);
                                TextView emailView = view.findViewById(R.id.email);
                                detail = view.findViewById(R.id.details);

                                JSONObject response1 = response.getJSONObject(iterator.next());

                                nameView.setText(response1.getString("name"));
                                skillView.setText("Skills: "+response1.getString("techSkills"));
                                expView.setText("Exp: "+response1.getString("experience"));
                                emailView.setText("Email: "+response1.getString("email"));


                                empName = response1.getString("name");
                                empEmail = response1.getString("email");
                                empQulif = response1.getString("qualif");
                                empTechs = response1.getString("techSkills");
                                empExp = response1.getString("experience");
                                Intent empDetail = new Intent(Hrpasscorrect.this,EmpDetailPage.class);
                                empDetail.putExtra("name", empName);
                                empDetail.putExtra("email", empEmail);
                                empDetail.putExtra("qualif", empQulif);
                                empDetail.putExtra("techSkills", empTechs);
                                empDetail.putExtra("experience", empExp);
                                detail.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {

                                        startActivity(empDetail);
                                    }
                                });
                                layout.addView(view);




                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });
                mQueue.add(request);
            }
        });


        String url = "http://"+Localhost.hostIp+"/getAllEMP";
        JSONObject jo = new JSONObject();
       try {
            jo.put("userName", displayusername);
        } catch (JSONException e) {
            e.printStackTrace();
        }



        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET,
                url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    Iterator<String> iterator = response.keys();
                    while(iterator.hasNext()){
                        View view = getLayoutInflater().inflate(R.layout.card, null);

                        TextView nameView = view.findViewById(R.id.name);
                        TextView skillView = view.findViewById(R.id.skill);
                        TextView expView = view.findViewById(R.id.exp);
                        TextView emailView = view.findViewById(R.id.email);
                        detail = view.findViewById(R.id.details);

                        JSONObject response1 = response.getJSONObject(iterator.next());

                        nameView.setText(response1.getString("name"));
                        skillView.setText("Skills: "+response1.getString("techSkills"));
                        expView.setText("Exp: "+response1.getString("experience"));
                        emailView.setText("Email: "+response1.getString("email"));


                        empName = response1.getString("name");
                        empEmail = response1.getString("email");
                        empQulif = response1.getString("qualif");
                        empTechs = response1.getString("techSkills");
                        empExp = response1.getString("experience");
                        Intent empDetail = new Intent(Hrpasscorrect.this,EmpDetailPage.class);
                        empDetail.putExtra("name", empName);
                        empDetail.putExtra("email", empEmail);
                        empDetail.putExtra("qualif", empQulif);
                        empDetail.putExtra("techSkills", empTechs);
                        empDetail.putExtra("experience", empExp);
                        detail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                startActivity(empDetail);
                            }
                        });
                        layout.addView(view);



                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mQueue.add(request);

    }
}