package com.example.cczec.ruclose;

import android.app.Activity;
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
import android.widget.Toast;

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
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit);

        final TextView submitsubmit1 =  (TextView) findViewById(R.id.submitsubmit1);
        final TextView submitsubmit2 =  (TextView) findViewById(R.id.submitsubmit2);
        final TextView submitsubmit4 =  (TextView) findViewById(R.id.submitsubmit4);

        final Button submitLine = (Button) findViewById(R.id.submitLine);

        editText = (EditText) findViewById(R.id.editSubmit);

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

        submitsubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SubmitActivity.this, RandomActivity.class));

            }
        });

        submitsubmit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

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
                Toast.makeText(SubmitActivity.this, "You have Submitted your Pick Up Line", Toast.LENGTH_SHORT).show();


            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    hideKeyboard();
                }
            }
        });

    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }
}
