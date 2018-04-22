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
        idsRef.child("351db15961baf8f0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(!(snapshot.child("UID").getValue() == null)){
                    String uid = snapshot.child("UID").getValue().toString();
                    String line = snapshot.child("line").getValue().toString();

                    Intent myIntent = new Intent(MainActivity.this, CheckInPopUp.class);
                    myIntent.putExtra("UID",uid).putExtra("line",line);
                    startActivity(myIntent);

                    idsRef.child("351db15961baf8f0").removeValue();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void initial_upvote(String uid){

        System.out.println(mainRef.orderByChild("uid").equalTo(uid));

        //mainRef.child()
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


        check_pending_items();







        ///////////////////////////////////////////////////////////
        // Copy Text
        ///////////////////////////////////////////////////////////

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                mainRef.child(top1.getTag().toString()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        int curUp = Integer.valueOf(snapshot.child("up").getValue().toString());
                        curUp += 1;

                        System.out.println(curUp);
                        mainRef.child(top1.getTag().toString()).child("up").setValue(curUp);


                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                    }
                });




                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top1.getText().toString());
                clipboard.setPrimaryClip(clipData);

                HashMap<String, Object> result = new HashMap<>();
                result.put("UID", top1.getTag());
                result.put("line", top1.getText());

                idsRef.child(get_user_id()).setValue(result);


                //initial_upvote();

            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top2.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top3.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top4.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top5.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top6.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top7.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top8.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        ///////////////////////////////////////////////////////////
        // Get Text
        ///////////////////////////////////////////////////////////
        topRef.child("1").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top1.setText(dataSnapshot.child("line").getValue().toString());
                top1.setTag(dataSnapshot.child("uid").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("2").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("3").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("4").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("5").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("6").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("7").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top7.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("8").child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top8.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ///////////////////////////////////////////////////////////
        // Up Votes
        ///////////////////////////////////////////////////////////
        topRef.child("1").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        topRef.child("2").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("3").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("4").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("5").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("6").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("7").child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("8").child("up").addValueEventListener(new ValueEventListener() {
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
        topRef.child("1").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("2").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("3").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("4").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("5").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("6").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("7").child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child("8").child("down").addValueEventListener(new ValueEventListener() {
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
}