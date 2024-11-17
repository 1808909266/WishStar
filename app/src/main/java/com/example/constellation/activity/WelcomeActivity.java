package com.example.constellation.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.constellation.R;

public class WelcomeActivity extends AppCompatActivity {
    ImageView splashImg;
    LottieAnimationView lottieAnimationView;
    private final long SPLASH_LENGTH = 2000;
    Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


        splashImg = findViewById(R.id.img);
        lottieAnimationView = findViewById(R.id.lottie);

        splashImg.animate().translationY(-3000).setDuration(1000).setStartDelay(4000);

        lottieAnimationView.animate().translationY(2000).setDuration(1000).setStartDelay(4000);

        handler.postDelayed(new Runnable() {

            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_LENGTH);

    }



}