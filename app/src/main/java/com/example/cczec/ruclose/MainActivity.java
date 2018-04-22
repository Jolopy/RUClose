package com.example.cczec.ruclose;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference topRef;
    DatabaseReference idsRef;
    DatabaseReference mainRef;

    private String get_user_id(){

        String android_id = Settings.Secure.getString(this.getContentResolver(),
                Settings.Secure.ANDROID_ID);
        return android_id;
    }


    private void check_pending_items(){
        String uid = get_user_id();
        // send call to firebase
        //System.out.println(uid);

        idsRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //System.out.println(snapshot.child("uid").getValue());
                if(!(snapshot.child("uid").getValue() == null)){
                    String uid = snapshot.child("uid").getValue().toString();
                    String line = snapshot.child("line").getValue().toString();

                    Intent myIntent = new Intent(MainActivity.this, CheckInPopUp.class);
                    myIntent.putExtra("uid",uid).putExtra("line",line).putExtra("get_user_id",get_user_id());
                    startActivity(myIntent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void initial_upvote(final String uid){

        mainRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int curUp = Integer.valueOf(snapshot.child("up").getValue().toString());
                curUp += 1;


                mainRef.child(uid).child("up").setValue(curUp);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void downvote(final String uid){

        mainRef.child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                int curUp = Integer.valueOf(snapshot.child("up").getValue().toString());
                curUp -= 1;

                int curDown = Integer.valueOf(snapshot.child("down").getValue().toString());
                curDown += 1;
                mainRef.child(uid).child("down").setValue(curDown);

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        database = FirebaseDatabase.getInstance();
        topRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/top_pickuplines");
        idsRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/android_ids");
        mainRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");

        final TextView top1 =  (TextView) findViewById(R.id.top1);
        final TextView top2 =  (TextView) findViewById(R.id.top2);
        final TextView top3 =  (TextView) findViewById(R.id.top3);
        final TextView top4 =  (TextView) findViewById(R.id.top4);
        final TextView top5 =  (TextView) findViewById(R.id.top5);
        final TextView top6 =  (TextView) findViewById(R.id.top6);
        final TextView top7 =  (TextView) findViewById(R.id.top7);
        final TextView top8 =  (TextView) findViewById(R.id.top8);

        final TextView up1 = (TextView)findViewById(R.id.top1votesup);
        final TextView up2 = (TextView)findViewById(R.id.top2votesup);
        final TextView up3 = (TextView)findViewById(R.id.top3votesup);
        final TextView up4 = (TextView)findViewById(R.id.top4votesup);
        final TextView up5 = (TextView)findViewById(R.id.top5votesup);
        final TextView up6 = (TextView)findViewById(R.id.top6votesup);
        final TextView up7 = (TextView)findViewById(R.id.top7votesup);
        final TextView up8 = (TextView)findViewById(R.id.top8votesup);

        final TextView down1 = (TextView)findViewById(R.id.top1votesdown);
        final TextView down2 = (TextView)findViewById(R.id.top2votesdown);
        final TextView down3 = (TextView)findViewById(R.id.top3votesdown);
        final TextView down4 = (TextView)findViewById(R.id.top4votesdown);
        final TextView down5 = (TextView)findViewById(R.id.top5votesdown);
        final TextView down6 = (TextView)findViewById(R.id.top6votesdown);
        final TextView down7 = (TextView)findViewById(R.id.top7votesdown);
        final TextView down8 = (TextView)findViewById(R.id.top8votesdown);


        final TextView submit1 = (TextView)findViewById(R.id.submit1);
        final TextView submit2 = (TextView)findViewById(R.id.submit2);
        final TextView submit4 = (TextView)findViewById(R.id.submit4);












        ///////////////////////////////////////////////////////////
        // Copy Text
        ///////////////////////////////////////////////////////////

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top1.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top1.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top1.getTag());
                result.put("line", top1.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top2.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top2.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top2.getTag());
                result.put("line", top2.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top3.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top3.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top3.getTag());
                result.put("line", top3.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top4.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top4.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top4.getTag());
                result.put("line", top4.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top5.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top5.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top5.getTag());
                result.put("line", top5.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top6.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top6.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top6.getTag());
                result.put("line", top6.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top7.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top7.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top7.getTag());
                result.put("line", top7.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        top8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top8.getText().toString());
                clipboard.setPrimaryClip(clipData);

                initial_upvote(top8.getTag().toString());
                HashMap<String, Object> result = new HashMap<>();
                result.put("uid", top8.getTag());
                result.put("line", top8.getText());

                idsRef.child(get_user_id()).setValue(result);
            }
        });

        ///////////////////////////////////////////////////////////
        // Get Text
        ///////////////////////////////////////////////////////////
        topRef.child("0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top1.setText(dataSnapshot.child("line").getValue().toString());
                top1.setTag(dataSnapshot.child("uid").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top2.setText(dataSnapshot.child("line").getValue().toString());
                top2.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("2").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top3.setText(dataSnapshot.child("line").getValue().toString());
                top3.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("3").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top4.setText(dataSnapshot.child("line").getValue().toString());
                top4.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("4").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top5.setText(dataSnapshot.child("line").getValue().toString());
                top5.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("5").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top6.setText(dataSnapshot.child("line").getValue().toString());
                top6.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("6").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top7.setText(dataSnapshot.child("line").getValue().toString());
                top7.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("7").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top8.setText(dataSnapshot.child("line").getValue().toString());
                top8.setTag(dataSnapshot.child("uid").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ///////////////////////////////////////////////////////////
        // Up Votes
        ///////////////////////////////////////////////////////////
        mainRef.child("0").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        mainRef.child("1").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("2").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("3").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("4").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("5").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("6").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("7").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up8.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ///////////////////////////////////////////////////////////
        // Down Votes
        ///////////////////////////////////////////////////////////
        mainRef.child("0").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("1").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("2").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("3").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("4").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("5").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("6").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        mainRef.child("7").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down8.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        submit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SubmitActivity.class));

            }
        });

        submit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RandomActivity.class));

            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        check_pending_items();
    }


}