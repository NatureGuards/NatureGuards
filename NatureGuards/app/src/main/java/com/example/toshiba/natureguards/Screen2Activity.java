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

    private static final int CAMERA_REQUEST = 10;

    @Bind(R.id.btn_send_signal)
    Button btnSendSignal;
    @Bind(R.id.btn_preview)
    Button btnPreview;
    @Bind(R.id.img_captured)
    ImageView imgCaptured;
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen2);
        ButterKnife.bind(this);
        btnSendSignal.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_preview:

                break;
            case R.id.btn_send_signal:
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgCaptured.setImageBitmap(imageBitmap);
        }
    }

}
