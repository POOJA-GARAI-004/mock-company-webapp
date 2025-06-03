package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class EmpDetailPage extends AppCompatActivity {
    private TextView name;
    private TextView email;
    private TextView qualif;
    private TextView techs;
    private TextView exp;

    String empEmail;
    String empQualif;
    String empName;
    String empSkill;
    String empExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_detail_page);
        getSupportActionBar().hide();

        name = findViewById(R.id.Empname);
        email = findViewById(R.id.Empemail);
        qualif = findViewById(R.id.Empqulif);
        techs = findViewById(R.id.EmpTechs);
        exp = findViewById(R.id.EmpExp);
        Intent empDetail = getIntent();

        empName = empDetail.getStringExtra("name");
        empEmail = empDetail.getStringExtra("email");
        empQualif = empDetail.getStringExtra("qualif");
        empSkill = empDetail.getStringExtra("techSkills");
        empExp = empDetail.getStringExtra("experience");


        name.setText("Name: "+empName);
        email.setText("Email: "+empEmail);
        qualif.setText("Qualification: "+empQualif);
        techs.setText("Skills: "+empSkill);
        exp.setText("Exp: "+empExp);
    }
}