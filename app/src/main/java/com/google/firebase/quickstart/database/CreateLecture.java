package com.google.firebase.quickstart.database;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.quickstart.database.models.Lecture;
import com.google.firebase.quickstart.database.models.Post;

import java.util.HashMap;
import java.util.Map;

public class CreateLecture extends AppCompatActivity {
    // [START declare_database_ref]
    private DatabaseReference mDatabase;
    // [END declare_database_ref]

    private EditText titel;
    private EditText subtopic1;
    private EditText subtopic2;
    private EditText subtopic3;
    private Button mCreateButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_lecture);

        // [START initialize_database_ref]
        mDatabase = FirebaseDatabase.getInstance().getReference();
        // [END initialize_database_ref]

        titel = findViewById(R.id.createLectureName);
        subtopic1 = findViewById(R.id.subtopic1);
        subtopic2 = findViewById(R.id.subtopic2);
        subtopic3 = findViewById(R.id.subtopic3);

        mCreateButton = findViewById(R.id.submitButton);

        mCreateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitLecture();
            }
        });
    }

    private void submitLecture() {
        Toast.makeText(this, "Create..", Toast.LENGTH_SHORT).show();
        // [START write_fan_out]
        String key = mDatabase.child("lectures").push().getKey();
        Lecture lecture = new Lecture(titel.getText().toString(), subtopic1.getText().toString(), subtopic2.getText().toString(), subtopic3.getText().toString());
        Map<String, Object> lectureValues = lecture.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/lectures/" + key, lectureValues);
        //childUpdates.put("/user-posts/" + userId + "/" + key, postValues);
        mDatabase.updateChildren(childUpdates);
        // [END write_fan_out]

        Toast.makeText(this, "Created", Toast.LENGTH_SHORT).show();


    }

}
