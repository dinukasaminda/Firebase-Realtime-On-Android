package com.tutorials.dinukasaminda.firebase_realtime_android;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    /*
    * app sha-1
    * 24:F0:F9:16:81:1E:A3:D5:D2:FA:6C:5D:5B:07:FD:E4:72:BE:B6:F1
    * */

    TextView mConditionTextView;
    Button mbtnSunny;
    Button mbtnFoggy;

    DatabaseReference mRotRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference mConditionRef =mRotRef.child("condition");

    public static String appTag ="firebase-realtime-example1";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mConditionTextView = (TextView)findViewById(R.id.txtViewCondition);
        mbtnSunny = (Button)findViewById(R.id.btnSunny);
        mbtnFoggy = (Button)findViewById(R.id.btnFoggy);

        mbtnSunny.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConditionRef.setValue("Sunny!");
            }
        });
        mbtnFoggy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConditionRef.setValue("Foggy!");
            }
        });
    }

    @Override
    protected void onStart(){
        super.onStart();

        mConditionRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String text=dataSnapshot.getValue(String.class);
                mConditionTextView.setText(text);

                Log.i(appTag,"onDataChange");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.i(appTag,"onCancelled Error");
            }
        });
    }
}






































