package com.example.toshiba.natureguards;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import butterknife.Bind;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {
    @Bind(R.id.img_splash)
    ImageView imgSplash;
    @Bind(R.id.grp_splash)
    RelativeLayout grpSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        animateSplash();
    }

    private void animateSplash(){
        Animation zoomIn = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
        Animation fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        grpSplash.startAnimation(fadeIn);
        imgSplash.startAnimation(zoomIn);

        zoomIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                Intent mainIntent = new Intent(MainActivity.this, Screen2Activity.class);
                MainActivity.this.startActivity(mainIntent);
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                MainActivity.this.finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
