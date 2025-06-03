package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Iterator;

public class Userpasscorrect extends AppCompatActivity {
    TextView username;
    LinearLayout complayout;
    private RequestQueue mQueue;
    Button hrdetail;
    String CoName;
    String hrname;
    String hremail;
    String hrqulif;
    String hrtechs;
    String hrexp;
    EditText search_bar;
    ImageButton search_btn;
    String search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpasscorrect);
        getSupportActionBar().hide();

        search_btn = findViewById(R.id.search_button);
        search_bar = findViewById(R.id.search_bar);

        search = search_bar.getText().toString().trim().toLowerCase();


        username = findViewById(R.id.tv_displayusername);
        Intent login_topwd =getIntent();
        String displayusername = login_topwd.getStringExtra("Hi");
        username.setText("Hi "+ displayusername);

        complayout = findViewById(R.id.LLcomplist);
        mQueue = Volley.newRequestQueue(this);



        search_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search = search_bar.getText().toString().trim().toLowerCase();

                String url = "http://"+Localhost.hostIp+"/getSearchedHR";
                JSONObject jo = new JSONObject();
                try {
                    jo.put("search", search);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST,
                        url, jo, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            complayout.removeAllViews();
                            Iterator<String> iterator = response.keys();
                            while(iterator.hasNext()){
                                View view = getLayoutInflater().inflate(R.layout.componycard, null);

                                TextView companyName = view.findViewById(R.id.compName);
                                TextView hrName = view.findViewById(R.id.Hrname);
                                TextView hrEmail = view.findViewById(R.id.hremail);
                                TextView skills = view.findViewById(R.id.skills);
                                TextView exp = view.findViewById(R.id.rexp);
                                hrdetail = view.findViewById(R.id.details);

                                JSONObject response1 = response.getJSONObject(iterator.next());
                                companyName.setText(response1.getString("company"));
                                hrName.setText("Hr: "+response1.getString("name"));
                                hrEmail.setText("Email: "+response1.getString("email"));
                                skills.setText("Skill: "+response1.getString("techSkills"));
                                exp.setText("Exp: "+response1.getString("experience"));


                                CoName = response1.getString("company");
                                hrname = response1.getString("name");
                                hremail = response1.getString("email");
                                hrqulif = response1.getString("qualif");
                                hrtechs = response1.getString("techSkills");
                                hrexp = response1.getString("experience");

                                Intent hrDetail = new Intent(Userpasscorrect.this,CompDetailPg.class);
                                hrDetail.putExtra("company", CoName);
                                hrDetail.putExtra("name", hrname);
                                hrDetail.putExtra("email", hremail);
                                hrDetail.putExtra("qualif", hrqulif);
                                hrDetail.putExtra("techSkills", hrtechs);
                                hrDetail.putExtra("experience", hrexp);
                                hrdetail.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        startActivity(hrDetail);
                                    }
                                });
                                complayout.addView(view);

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





        String url = "http://"+Localhost.hostIp+"/getAllHR";
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
                        View view = getLayoutInflater().inflate(R.layout.componycard, null);

                        TextView companyName = view.findViewById(R.id.compName);
                        TextView hrName = view.findViewById(R.id.Hrname);
                        TextView hrEmail = view.findViewById(R.id.hremail);
                        TextView skills = view.findViewById(R.id.skills);
                        TextView exp = view.findViewById(R.id.rexp);
                        hrdetail = view.findViewById(R.id.details);

                        JSONObject response1 = response.getJSONObject(iterator.next());
                        companyName.setText(response1.getString("company"));
                        hrName.setText("Hr: "+response1.getString("name"));
                        hrEmail.setText("Email: "+response1.getString("email"));
                        skills.setText("Skill: "+response1.getString("techSkills"));
                        exp.setText("Exp: "+response1.getString("experience"));


                        CoName = response1.getString("company");
                        hrname = response1.getString("name");
                        hremail = response1.getString("email");
                        hrqulif = response1.getString("qualif");
                        hrtechs = response1.getString("techSkills");
                        hrexp = response1.getString("experience");

                        Intent hrDetail = new Intent(Userpasscorrect.this,CompDetailPg.class);
                        hrDetail.putExtra("company", CoName);
                        hrDetail.putExtra("name", hrname);
                        hrDetail.putExtra("email", hremail);
                        hrDetail.putExtra("qualif", hrqulif);
                        hrDetail.putExtra("techSkills", hrtechs);
                        hrDetail.putExtra("experience", hrexp);
                        hrdetail.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                startActivity(hrDetail);
                            }
                        });
                        complayout.addView(view);

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