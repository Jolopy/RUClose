package com.example.cczec.ruclose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class SubmitActivity extends AppCompatActivity {


    public int pickupNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        final Button submitsubmit1 =  (Button) findViewById(R.id.submitsubmit1);
        final Button submitsubmit2 =  (Button) findViewById(R.id.submitsubmit2);
        final Button submitsubmit3 =  (Button) findViewById(R.id.submitsubmit3);
        final Button submitsubmit4 =  (Button) findViewById(R.id.submitsubmit4);

        final Button submitLine = (Button) findViewById(R.id.submitLine);

        final EditText editText = (EditText) findViewById(R.id.editSubmit);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference sendRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/1");

        final DatabaseReference numberRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");

        numberRef.child("linenumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                pickupNumber = dataSnapshot.getValue(Integer.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submitsubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitActivity.this, MainActivity.class));

            }
        });

        submitLine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                sendRef.child(String.valueOf(pickupNumber)).child("line").setValue(value);
                sendRef.child(String.valueOf(pickupNumber)).child("up").setValue(0);
                sendRef.child(String.valueOf(pickupNumber)).child("down").setValue(0);
                sendRef.child(String.valueOf(pickupNumber)).child("uid").setValue(String.valueOf(pickupNumber));
                numberRef.child("linenumber").setValue(pickupNumber + 1);



            }
        });

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // do something, e.g. set your TextView here via .setText()
                    InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

    }
}
