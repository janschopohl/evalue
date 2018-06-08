package com.google.firebase.quickstart.database.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.quickstart.database.R;
import com.google.firebase.quickstart.database.models.Lecture;
import com.google.firebase.quickstart.database.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;

    public ImageView starView;
    public TextView bodyView;

    public PostViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.post_title);
        bodyView = itemView.findViewById(R.id.post_body);
    }

    public void bindToPost(Lecture l, View.OnClickListener starClickListener) {
        titleView.setText(l.title);
        bodyView.setText(l.subtopic1 + "\n" + l.subtopic2 + "\n" + l.subtopic3);

    }
}
