package com.google.firebase.quickstart.database;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.quickstart.database.models.Lecture;

public class PostDetailActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "PostDetailActivity";

    public static final String EXTRA_LECTURE_KEY = "post_key";

    private DatabaseReference mLectureReference;
    //private DatabaseReference mCommentsReference;
    private ValueEventListener mLectureListener;
    private String mLectureKey;

    /*private TextView mAuthorView;
    private TextView mTitleView;
    private TextView mBodyView;
    private EditText mCommentField;
    private Button mCommentButton;
    private RecyclerView mCommentsRecycler;*/

    private TextView mLectureTitle;

    // 3 subtopics
    private TextView mSubtopic1Title;
    private RatingBar mSubtopic1Rating;
    private TextView mSubtopic2Title;
    private RatingBar mSubtopic2Rating;
    private TextView mSubtopic3Title;
    private RatingBar mSubtopic3Rating;

    private Button mVoteButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lecture_scores);

        // Get post key from intent
        mLectureKey = getIntent().getStringExtra(EXTRA_LECTURE_KEY);
        if (mLectureKey == null) {
            throw new IllegalArgumentException("Must pass EXTRA_TOPIC_KEY");
        }

        // Initialize Database
        mLectureReference = FirebaseDatabase.getInstance().getReference()
                .child("lectures").child(mLectureKey);

        // Initialize Views
        mLectureTitle = findViewById(R.id.lecture_title);
        mSubtopic1Title = findViewById(R.id.subtopic1_title);
        mSubtopic1Rating = findViewById(R.id.subtopic1_rating);

        mSubtopic2Title = findViewById(R.id.subtopic2_title);
        mSubtopic2Rating = findViewById(R.id.subtopic2_rating);

        mSubtopic3Title = findViewById(R.id.subtopic3_title);
        mSubtopic3Rating = findViewById(R.id.subtopic3_rating);

    }

    @Override
    public void onStart() {
        super.onStart();

        // Add value event listener to the post
        // [START post_value_event_listener]
        ValueEventListener lectureListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                Lecture lecture = dataSnapshot.getValue(Lecture.class);
                // [START_EXCLUDE]
                mLectureTitle.setText(lecture.title);
                mSubtopic1Title.setText(lecture.subtopic1);
                mSubtopic2Title.setText(lecture.subtopic2);
                mSubtopic3Title.setText(lecture.subtopic3);
                // [END_EXCLUDE]
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                // [START_EXCLUDE]
                Toast.makeText(PostDetailActivity.this, "Failed to load post.",
                        Toast.LENGTH_SHORT).show();
                // [END_EXCLUDE]
            }
        };
        mLectureReference.addValueEventListener(lectureListener);
        // [END post_value_event_listener]

        // Keep copy of post listener so we can remove it when app stops
        mLectureListener = lectureListener;
    }

    @Override
    public void onStop() {
        super.onStop();

        // Remove post value event listener
        if (mLectureListener != null) {
            mLectureReference.removeEventListener(mLectureListener);
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.vote_button) {
            vote();
        }
    }


    private void vote() {
        final String uid = getUid();

        int rating1 = mSubtopic1Rating.getNumStars();
        int rating2 = mSubtopic2Rating.getNumStars();
        int rating3 = mSubtopic3Rating.getNumStars();

        // stars would be saved here...

    }
}
