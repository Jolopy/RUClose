package com.example.cczec.ruclose;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

public class CheckInPopUp extends AppCompatActivity {

    TextView checkinLine,upVotes,downVotes;
    FirebaseDatabase database;
    DatabaseReference deleteRef,mainRef;
    public String uid;


    private void downvote(final String uid){

        mainRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int curUp = Integer.valueOf(snapshot.child("up").getValue().toString());
                curUp -= 1;

                int curDown = Integer.valueOf(snapshot.child("down").getValue().toString());
                curDown += 1;
                mainRef.child(uid).child("down").setValue(curDown);
                mainRef.child(uid).child("up").setValue(curUp);


            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        checkinLine = findViewById(R.id.checkinLine);
        upVotes = findViewById(R.id.checkinvotesup);
        downVotes = findViewById(R.id.checkinvotesdown);
        final ImageButton upButton = (ImageButton) findViewById(R.id.checkinup);
        final ImageButton downButton = (ImageButton) findViewById(R.id.checkindown);


        database = FirebaseDatabase.getInstance();
        deleteRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/android_ids");
        mainRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8) ,(int)(height * 0.5));

        uid = getIntent().getStringExtra("uid");
        String line = getIntent().getStringExtra("line");
        String get_user_id = getIntent().getStringExtra("get_user_id");

        checkinLine.setText(line);
        deleteRef.child(get_user_id).removeValue();

        upButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        downButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downvote(uid);
                finish();
            }
        });

        mainRef.child(uid).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                upVotes.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        mainRef.child(uid).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                downVotes.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





    }
}
