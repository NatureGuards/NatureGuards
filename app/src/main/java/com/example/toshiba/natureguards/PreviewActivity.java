package com.example.toshiba.natureguards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class PreviewActivity extends AppCompatActivity {

    @Bind(R.id.rec_view)
    RecyclerView recView;
    MyAdapter myAdapter;
    List<Events> event = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview2);
        ButterKnife.bind(this);
        recView.setLayoutManager(new LinearLayoutManager(this));
        Collections.reverse(event);
        myAdapter = new MyAdapter(event);
        recView.setAdapter(myAdapter);


        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase(Config.FIREBASE_URL);
        ref.addValueEventListener(new ValueEventListener() {
            public static final String TAG = "sdsd";

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Log.d(TAG, "onDataChange() " + snapshot);
                Events events = null;
                for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                    //Getting the data from snapshot
                    event.add(postSnapshot.getValue(Events.class));
                    //Adding it to a string
//                    events = new Events();
//                    String recievingString = events.getImg();
//                    byte[] decodedString = Base64.decode(recievingString, Base64.DEFAULT);
//                    Bitmap bmp = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    //Displaying it on textview

                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.e(TAG, "onCancelled() " + firebaseError.getMessage());
                System.out.println("The read failed: " + firebaseError.getMessage());
            }
        });
    }
}