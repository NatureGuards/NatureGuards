package com.example.toshiba.natureguards;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.firebase.client.core.Tag;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.example.toshiba.natureguards.R.drawable.blue_forest;

/**
 * Created by toshiba on 16.4.2016 г..
 */
public class Screen2Activity extends AppCompatActivity implements View.OnClickListener {



    @Bind(R.id.btn_send_signal)
    Button btnSendSignal;
    @Bind(R.id.btn_preview)
    Button btnPreview;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        ButterKnife.bind(this);
        btnSendSignal.setOnClickListener(this);
<<<<<<< HEAD
=======
        btnPreview.setOnClickListener(this);
>>>>>>> 09701293ed7925e5358fde970c9295c86321457e


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_preview:
<<<<<<< HEAD
=======
                Intent previewIntent = new Intent(this, PreviewActivity.class);
                startActivity(previewIntent);
>>>>>>> 09701293ed7925e5358fde970c9295c86321457e

                break;
            case R.id.btn_send_signal:
                Intent intent = new Intent(this, SendActivity.class);
                startActivity(intent);

                break;
        }
    }



}
