package com.example.toshiba.natureguards;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.firebase.client.*;


import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

public class SendActivity extends AppCompatActivity {

    @Bind(R.id.edt_txt_location)
    EditText edtTxtLocation;
    @Bind(R.id.edt_txt_description)
    EditText edtTxtDescription;
    @Bind(R.id.btn_send)
    Button btnSend;
    @Bind(R.id.img_send)
    ImageView imgSend;
    @Bind(R.id.img_recieve)
    ImageView imgRecieve;
    @Bind(R.id.cbox_animals_protected)
    CheckBox cBoxAnimalProtected;
    @Bind(R.id.cbox_gorskoto)
    CheckBox cBoxGorskoto;
    @Bind(R.id.cbox_grajdanska)
    CheckBox cBoxgrajdanska;
    @Bind(R.id.cbox_okolona_sreda)
    CheckBox cBoxOkolnaSreda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
        final Firebase ref = new Firebase(Config.FIREBASE_URL);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
                byte[] data = bos.toByteArray();
                String base64 = Base64.encodeToString(data, Base64.DEFAULT);
                Events events = new Events();
                events.setImg(base64);
                events.setLocation(edtTxtLocation.getText().toString());
                events.setDescription(edtTxtDescription.getText().toString());

                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String currentDateandTime = sdf.format(new Date());

                ref.child(currentDateandTime).setValue(events);

                ref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            //Getting the data from snapshot
                            Events events = postSnapshot.getValue(Events.class);


                            //Adding it to a string
                            String recievingString = events.getImg();
                            byte[] decodedString = Base64.decode(recievingString, Base64.DEFAULT);
                            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                            imgRecieve.setImageBitmap(decodedByte);
                            //Displaying it on textview
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        System.out.println("The read failed: " + firebaseError.getMessage());
                    }
                });




            }
        });

    }
}
