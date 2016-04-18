package com.example.toshiba.natureguards;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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
        btnPreview.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_preview:
                if (isNetworkAvailable() == false) {
                                  Toast.makeText(getApplicationContext(), "No Network Connection", Toast.LENGTH_LONG).show();
                                    } else {
                                        Intent previewIntent = new Intent(this, PreviewActivity.class);
                                        startActivity(previewIntent);
                                    }

                break;
            case R.id.btn_send_signal:
                Intent intent = new Intent(this, SendActivity.class);
                startActivity(intent);

                break;
        }
    }

    private boolean isNetworkAvailable() {
           ConnectivityManager connectivityManager
                        = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
                NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

}
