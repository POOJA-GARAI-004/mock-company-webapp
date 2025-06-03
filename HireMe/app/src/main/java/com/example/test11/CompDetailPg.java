package com.example.test11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class CompDetailPg extends AppCompatActivity {
    private TextView coname;
    private TextView name;
    private TextView email;
    private TextView qualif;
    private TextView techs;
    private TextView exp;

    String compname;
    String hrEmail;
    String hrQualif;
    String hrName;
    String hrSkill;
    String hrExp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_detail_pg);
        getSupportActionBar().hide();

        coname = findViewById(R.id.Compname);
        name = findViewById(R.id.HRname);
        email = findViewById(R.id.HRemail);
        qualif = findViewById(R.id.HRqulif);
        techs = findViewById(R.id.HRTechs);
        exp = findViewById(R.id.HRExp);
        Intent hrDetail = getIntent();

       compname = hrDetail.getStringExtra("company");
       hrName =   hrDetail.getStringExtra("name");
       hrEmail =  hrDetail.getStringExtra("email");
       hrQualif = hrDetail.getStringExtra("qualif");
       hrSkill =  hrDetail.getStringExtra("techSkills");
       hrExp =    hrDetail.getStringExtra("experience");

       coname.setText("Name: "+compname);
        name.setText("Hr: "+hrName);
        email.setText("Email: "+hrEmail);
        qualif.setText("Qualification: "+hrQualif);
        techs.setText("Skills: "+hrSkill);
        exp.setText("Exp: "+hrExp);
    }
}