package com.example.toshiba.natureguards;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;

/**
 * Created by toshiba on 16.4.2016 г..
 */
public class SendMailActivity extends AppCompatActivity {
    private static final String TAG = "111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.btn_send);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        final EditText editTextLokation = (EditText) findViewById(R.id.edit_lokation);
        final EditText editTextBody = (EditText) findViewById(R.id.edit_text_body);
        final CheckBox chboxGorsko = (CheckBox) findViewById(R.id.chbox_gorsko);
        final CheckBox chboxOkolna = (CheckBox) findViewById(R.id.chbox_okolna);
        final CheckBox chboxGrajdanska = (CheckBox) findViewById(R.id.chbox_grajdanska);
        final CheckBox chboxJiwotni = (CheckBox) findViewById(R.id.chbox_jiwotni);


        String filename = "logo.png";
        File filelocation = new File(Environment.getExternalStorageDirectory().getAbsolutePath(), filename);
        final Uri path = Uri.fromFile(filelocation);


        Log.e(TAG, "File Path=" + path);

//        *********************************************

//        Bitmap bm = BitmapFactory.decodeResource(getResources(),  R.id.imageView);
//        File f = new File(getExternalCacheDir()+"/logo.png");
//        try {
//            FileOutputStream outStream = new FileOutputStream(f);
//            bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
//            outStream.flush();
//            outStream.close();
//        } catch (Exception e) { throw new RuntimeException(e); }
//
//        Intent intent = new Intent();
//        intent.setAction(android.content.Intent.ACTION_VIEW);
//        intent.setDataAndType(Uri.fromFile(f), "image/png");
//        startActivity(intent);
//



//****************************************

        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                boolean fl = true;

                String lokation = editTextLokation.getText().toString();
                String bodyText = editTextBody.getText().toString();

                if (lokation.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NO Location",
                            Toast.LENGTH_LONG).show();
                    fl = false;
                }
                if (bodyText.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "NO TEXT",
                            Toast.LENGTH_LONG).show();
                    fl = false;
                }
                if (!chboxGorsko.isChecked() &&
                        !chboxGrajdanska.isChecked() &&
                        !chboxOkolna.isChecked() &&
                        !chboxJiwotni.isChecked()) {
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
                    if (chboxGorsko.isChecked()) {
                        recipient = recipient + " pkatrankiev@abv.bg,";
                    }
                    if (chboxGrajdanska.isChecked()) {
                        recipient = recipient + " pkatrankiev@gmail.com,";
                    }
                    if (chboxOkolna.isChecked()) {
                        recipient = recipient + " tapotiata@abv.bg,";
                    }
                    if (chboxJiwotni.isChecked()) {
                        recipient = recipient + " sity_teh@abv.bg,";
                    }
                    String text = lokation + "\n" + bodyText;

                    emailIntent.setData(Uri.parse("mailto:" + recipient));
                    emailIntent.putExtra(Intent.EXTRA_STREAM, path);
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "imate mail");
                    emailIntent.putExtra(Intent.EXTRA_TEXT, text);

                    try {
                        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        Toast.makeText(getApplicationContext(), "No email clients installed.", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }


}

