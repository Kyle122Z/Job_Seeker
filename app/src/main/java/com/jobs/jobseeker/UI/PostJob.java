package com.jobs.jobseeker.UI;

import static android.content.ContentValues.TAG;

import androidx.annotation.LongDef;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jobs.jobseeker.R;
import com.jobs.jobseeker.model.Jobs;

public class PostJob extends AppCompatActivity {
    String[] items = {"AGRICULTURE", "TECHNOLOGY", "EDUCATION", "SPORTS"};

    AutoCompleteTextView autoCompleteTextView;

    ArrayAdapter <String> adapterItems;

    ExtendedFloatingActionButton btnpost;
    TextInputLayout categoryPost;
    EditText company_namePost, rolePost, locationPost, skillsPost;
    DatabaseReference databasePost;

        @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(PostJob.this, Viewjobs.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_job);

        autoCompleteTextView = findViewById(R.id.auto_complete);

        adapterItems = new ArrayAdapter<String>(this, R.layout.list_item,items);

        autoCompleteTextView.setAdapter(adapterItems);

//        autoCompleteTextView.setOnItemClickListener();

        btnpost = findViewById(R.id.extended_fab);
        categoryPost = findViewById(R.id.category);
        company_namePost = findViewById(R.id.company_name);
        rolePost = findViewById(R.id.role);
        locationPost = findViewById(R.id.location);
        skillsPost = findViewById(R.id.skills);
        databasePost = FirebaseDatabase.getInstance().getReference();

        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Post_jobs();
            }
        });


        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view , int position , long id){
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"Item: "+item, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void Post_jobs(){
        String companie_name = company_namePost.getText().toString();
        String category = rolePost.getText().toString();
        String role = rolePost.getText().toString();
        String location = locationPost.getText().toString();
        String skills = skillsPost.getText().toString();
        String id = databasePost.push().getKey();

        Jobs jobs = new Jobs(category, companie_name, role, location, skills);
        databasePost.child("jobs").child(id).setValue(jobs)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "Data is pushed to the database");
                            Toast.makeText(PostJob.this, "Job posted successfully", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}