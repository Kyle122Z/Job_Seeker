package com.jobs.jobseeker.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.jobs.jobseeker.R;

public class JobDescription extends AppCompatActivity {

        @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(JobDescription.this, Viewjobs.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_description);


        String category = getIntent().getStringExtra("category");
        String company_name = getIntent().getStringExtra("company_name");
        String role = getIntent().getStringExtra("role");
        String location = getIntent().getStringExtra("location");
        String job_description = getIntent().getStringExtra("job_description");

        TextView companyNameJD, roleNameJD, jd_description, JD_skills;

        companyNameJD = findViewById(R.id.companyNameJD);
        roleNameJD = findViewById(R.id.roleNameJD);
        jd_description= findViewById(R.id.Jd_Description);


        companyNameJD.setText(company_name);
        roleNameJD.setText(role);
        jd_description.setText(job_description);
    }
}