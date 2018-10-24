package com.auribises.gw2018c;

import android.app.Notification;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {

    TextView txtTitle;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();

        txtTitle = findViewById(R.id.textView8);
        imageView = findViewById(R.id.imageView);

        Animation animation = AnimationUtils.loadAnimation(this, R.anim.alpha);
        //Animation animation = AnimationUtils.loadAnimation(this, R.anim.rotate);
        txtTitle.startAnimation(animation);

        AnimationDrawable animationDrawable = (AnimationDrawable)imageView.getBackground();
        animationDrawable.start();


        //handler.sendEmptyMessageDelayed(101,3000);
    }

    // Assignment: Create a SlideShow with Animations and MediaPlayer
    // alpha, rotate, scale and translate, set : Tween Animations

    Handler handler = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                Intent intent = new Intent(SplashActivity.this,NotificationActivity.class);
                startActivity(intent);
                finish();
            }
        }

    };
}
