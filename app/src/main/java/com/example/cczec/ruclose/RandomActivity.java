package com.example.cczec.ruclose;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

public class RandomActivity extends AppCompatActivity {

    public int randomRange,random1,random2,random3,random4,random5,random6,random7,random8;
    DatabaseReference mainRef;
    DatabaseReference topRef;
    DatabaseReference idsRef;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

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


        final TextView randomsubmit1 =  (TextView) findViewById(R.id.randomsubmit1);
        final TextView randomsubmit2 =  (TextView) findViewById(R.id.randomsubmit2);
        final TextView randomsubmit4 =  (TextView) findViewById(R.id.randomsubmit4);

        final ImageButton top1up = (ImageButton) findViewById(R.id.top1up);
        final ImageButton top2up = (ImageButton) findViewById(R.id.top2up);
        final ImageButton top3up = (ImageButton) findViewById(R.id.top3up);
        final ImageButton top4up = (ImageButton) findViewById(R.id.top4up);
        final ImageButton top5up = (ImageButton) findViewById(R.id.top5up);
        final ImageButton top6up = (ImageButton) findViewById(R.id.top6up);
        final ImageButton top7up = (ImageButton) findViewById(R.id.top7up);
        final ImageButton top8up = (ImageButton) findViewById(R.id.top8up);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        topRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");
        idsRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/android_ids");
        mainRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");


        topRef.child("linenumber").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                randomRange = dataSnapshot.getValue(Integer.class);
                System.out.println(randomRange);
                random1 = new Random().nextInt(randomRange);
                random2 = new Random().nextInt(randomRange);
                random3 = new Random().nextInt(randomRange);
                random4 = new Random().nextInt(randomRange);
                random5 = new Random().nextInt(randomRange);
                random6 = new Random().nextInt(randomRange);
                random7 = new Random().nextInt(randomRange);
                random8 = new Random().nextInt(randomRange);
                updateShit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        topRef.child("linenumber").removeEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        ///////////////////////////////////////////////////////////
        // Copy Text
        ///////////////////////////////////////////////////////////

        top1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
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



        randomsubmit4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RandomActivity.this, SubmitActivity.class));

            }
        });
        randomsubmit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RandomActivity.this, MainActivity.class));

            }
        });

        randomsubmit2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RandomActivity.this, RandomActivity.class));

            }
        });

    }
    private void updateShit(){
        ///////////////////////////////////////////////////////////
        // Get Text
        ///////////////////////////////////////////////////////////
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference topRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");

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


        topRef.child(String.valueOf(random1)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top1.setTag(dataSnapshot.child("uid").getValue().toString());
                top1.setText(dataSnapshot.child("line").getValue().toString());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random2)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top2.setTag(dataSnapshot.child("uid").getValue().toString());
                top2.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random3)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top3.setTag(dataSnapshot.child("uid").getValue().toString());
                top3.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random4)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top4.setTag(dataSnapshot.child("uid").getValue().toString());
                top4.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random5)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top5.setTag(dataSnapshot.child("uid").getValue().toString());
                top5.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random6)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top6.setTag(dataSnapshot.child("uid").getValue().toString());
                top6.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random7)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top7.setTag(dataSnapshot.child("uid").getValue().toString());
                top7.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random8)).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                top8.setTag(dataSnapshot.child("uid").getValue().toString());
                top8.setText(dataSnapshot.child("line").getValue().toString());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        ///////////////////////////////////////////////////////////
        // Up Votes
        ///////////////////////////////////////////////////////////
        topRef.child(String.valueOf(random1)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



        topRef.child(String.valueOf(random2)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random3)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random4)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random5)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random6)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random7)).child("up").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                up7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random8)).child("up").addValueEventListener(new ValueEventListener() {
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
        topRef.child(String.valueOf(random1)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down1.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random2)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down2.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random3)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down3.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random4)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down4.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random5)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down5.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random6)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down6.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random7)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down7.setText(String.valueOf(value));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random8)).child("down").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int value = dataSnapshot.getValue(Integer.class);
                down8.setText(String.valueOf(value));
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

                    Intent myIntent = new Intent(RandomActivity.this, CheckInPopUp.class);
                    myIntent.putExtra("uid",uid).putExtra("line",line).putExtra("get_user_id",get_user_id());
                    startActivity(myIntent);
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onResume(){
        super.onResume();
        check_pending_items();
    }

}