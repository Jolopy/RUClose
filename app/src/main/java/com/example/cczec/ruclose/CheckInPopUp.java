package com.example.cczec.ruclose;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.widget.TextView;

import org.w3c.dom.Text;

public class CheckInPopUp extends AppCompatActivity {

    TextView checkinLine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkin);

        checkinLine = findViewById(R.id.checkinLine);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout((int)(width * 0.8) ,(int)(height * 0.8));

        String UID = getIntent().getStringExtra("UID");
        String line = getIntent().getStringExtra("line");

        checkinLine.setText(line);


    }
}
