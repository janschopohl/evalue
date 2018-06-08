package com.google.firebase.quickstart.database.models;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;

import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties

public class Lecture {

    public String title;
    public String subtopic1;
    public String subtopic2;
    public String subtopic3;
    public Map<String, Boolean> stars = new HashMap<>();

    public Lecture() {
        // Default constructor required for calls to DataSnapshot.getValue(Post.class)
    }

    public Lecture(String title, String subtopic1, String subtopic2, String subtopic3) {
        this.title = title;
        this.subtopic1 = subtopic1;
        this.subtopic2 = subtopic2;
        this.subtopic3 = subtopic3;
    }

    // [START post_to_map]
    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("titel", title);
        result.put("subtopic1", subtopic1);
        result.put("subtopic2", subtopic2);
        result.put("subtopic3", subtopic3);
        return result;

    }
    // [END post_to_map]

}
