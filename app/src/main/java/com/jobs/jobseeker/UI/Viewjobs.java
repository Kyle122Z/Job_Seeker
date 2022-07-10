package com.jobs.jobseeker.UI;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jobs.jobseeker.R;
import com.jobs.jobseeker.adapters.Jobs_adapter;
import com.jobs.jobseeker.interfaces.Click_CardViewInterface;
import com.jobs.jobseeker.model.Jobs;

import java.util.ArrayList;

public class Viewjobs extends AppCompatActivity implements Click_CardViewInterface {

    RecyclerView recyclerView;
    FloatingActionButton floating_post;
    DatabaseReference database;
    Jobs_adapter jobs_adapter;
    ArrayList<Jobs> list;

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        startActivity(new Intent(Viewjobs.this, ));
        //finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewjobs);

        recyclerView = findViewById(R.id.jobsList);
        floating_post = findViewById(R.id.floating_add_post);
        database = FirebaseDatabase.getInstance().getReference("jobs");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<>();

        jobs_adapter = new Jobs_adapter (this, list, this);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(jobs_adapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Jobs jobs = dataSnapshot.getValue(Jobs.class);
                    list.add(jobs);
                }
                jobs_adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        addJobs();
    }

    private void addJobs(){
        floating_post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == floating_post){
                    Intent intent = new Intent(Viewjobs.this, PostJob.class);
                    startActivity(intent);
                }
//                Snackbar.make(view, "Here's a Snackbar", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onItemCLick(int position) {

        Intent intent = new Intent(Viewjobs.this, JobDescription.class);

        intent.putExtra("category", list.get(position).getCategory());
        intent.putExtra("company_name", list.get(position).getCompany_name());
        intent.putExtra("role", list.get(position).getRole());
        intent.putExtra("location", list.get(position).getLocation());
        intent.putExtra("job_description", list.get(position).getJob_description());
        startActivity(intent);
    }
}












        /*

//        database.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                String value = dataSnapshot.getValue(String.class);
//                Log.d(TAG, "Value is:" + value);

//                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()){
//                    Jobs jobs = dataSnapshot1.getValue(Jobs.class);
//                    list.add(jobs);
//                }
//                jobs_adapter.notifyDataSetChanged();
//            }

//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w(TAG, "Failed to read", databaseError.toException());
//            }
//        });
    }

    public void basicReadWrite() {
        // [START write_message]
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Database now works");
        // [END write_message]

        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        // [END read_message]

        */



    /*

    public void onDetails(final View view){
        final List<Jobs> list = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("jobs");
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for(DataSnapshot postSnapshot:dataSnapshot.getChildren())
                {
                    String title = postSnapshot.child("title").getValue().toString();
                    Intent intent = new Intent(view.getContext(), JobDescription.class);
                    intent.putExtra("title",title);
                    startActivity(intent);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    } */