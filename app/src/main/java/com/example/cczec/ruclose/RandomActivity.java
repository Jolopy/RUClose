package com.example.cczec.ruclose;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
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

import java.util.Random;
import java.util.concurrent.TimeoutException;
import java.util.regex.Pattern;

public class RandomActivity extends AppCompatActivity {

    private void get_email_list(){
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Context context = this;
        Account[] accounts = AccountManager.get(context).getAccounts();
        System.out.println("in test");
        for (Account account : accounts) {
            System.out.println(account.name);
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
            }
        }
    }


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

        final int random1 = new Random().nextInt(1000);
        final int random2 = new Random().nextInt(1000);
        final int random3 = new Random().nextInt(1000);
        final int random4 = new Random().nextInt(1000);
        final int random5 = new Random().nextInt(1000);
        final int random6 = new Random().nextInt(1000);
        final int random7 = new Random().nextInt(1000);
        final int random8 = new Random().nextInt(1000);


        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference topRef = database.getReferenceFromUrl("https://ruclose-28b01.firebaseio.com/pickuplines");


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
                get_email_list();
            }
        });

        top2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top2.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top3.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top4.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top5.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top6.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top7.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        top8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(RandomActivity.this, "Copied", Toast.LENGTH_SHORT).show();
                android.content.ClipboardManager clipboard = (android.content.ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
                android.content.ClipData clipData = android.content.ClipData.newPlainText("PlainText", top8.getText().toString());
                clipboard.setPrimaryClip(clipData);
            }
        });

        ///////////////////////////////////////////////////////////
        // Get Text
        ///////////////////////////////////////////////////////////
        topRef.child(String.valueOf(random1)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top1.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random2)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top2.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random3)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top3.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random4)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top4.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random5)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top5.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random6)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top6.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random7)).child("line").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String value =  dataSnapshot.getValue(String.class);
                top7.setText(value);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        topRef.child(String.valueOf(random8)).child("line").addValueEventListener(new ValueEventListener() {
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
}