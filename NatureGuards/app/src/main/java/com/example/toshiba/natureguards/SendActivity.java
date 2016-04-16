package com.example.toshiba.natureguards;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.firebase.client.*;


import java.io.ByteArrayOutputStream;
import java.io.File;
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

    private Firebase ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        ButterKnife.bind(this);

        Firebase.setAndroidContext(this);
        ref = new Firebase(Config.FIREBASE_URL);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makeFireBase();
                sendEmail();
            }
        });

    }

    private void sendEmail() {

        boolean fl = true;

        String location = edtTxtLocation.getText().toString();
        String description = edtTxtDescription.getText().toString();

        if (location.isEmpty()) {
            Toast.makeText(getApplicationContext(), "NO Location",
                    Toast.LENGTH_LONG).show();
            fl = false;
        }
        if (description.isEmpty()) {
            Toast.makeText(getApplicationContext(), "NO TEXT",
                    Toast.LENGTH_LONG).show();
            fl = false;
        }
        if (!cBoxGorskoto.isChecked() &&
                !cBoxgrajdanska.isChecked() &&
                !cBoxAnimalProtected.isChecked() &&
                !cBoxOkolnaSreda.isChecked()) {
            Toast.makeText(getApplicationContext(), "NO CHECK",
                    Toast.LENGTH_LONG).show();
            fl = false;
        }

        if (fl) {
            Toast.makeText(getApplicationContext(), "send mail",
                    Toast.LENGTH_LONG).show();

//                    Intent emailIntent = new Intent((Intent.ACTION_SEND));

            Intent emailIntent = new Intent(Intent.ACTION_SENDTO);

            String recipient = "";
            if (cBoxGorskoto.isChecked()) {
                recipient = recipient + " pkatrankiev@abv.bg,";
            }
            if (cBoxAnimalProtected.isChecked()) {
                recipient = recipient + " pkatrankiev@gmail.com,";
            }
            if (cBoxOkolnaSreda.isChecked()) {
                recipient = recipient + " tapotiata@abv.bg,";
            }
            if (cBoxgrajdanska.isChecked()) {
                recipient = recipient + " sity_teh@abv.bg,";
            }
            String text = location + "\n" + description;

            emailIntent.setData(Uri.parse("mailto:" + recipient));
//                    emailIntent.putExtra(Intent.EXTRA_STREAM, );
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "imate mail");
            emailIntent.putExtra(Intent.EXTRA_TEXT, text);

            try {
                startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getApplicationContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
            }

        }
    }


    public void makeFireBase() {



        Events events = new Events();
        events.setImg(decodeImage());
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


    public String decodeImage() {
        Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, bos);
        byte[] data = bos.toByteArray();
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
}
