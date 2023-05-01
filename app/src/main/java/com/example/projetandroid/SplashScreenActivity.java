package com.example.projetandroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    final int SPLASH_TIME_OUT = 5000;
    Animation mTopAnim ;
    ImageView mLogo ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        //Animation de logo
        mTopAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation);
        mLogo=findViewById(R.id.main_iv_logo);
        mLogo.setAnimation(mTopAnim);

        SharedPreferences sp1=this.getSharedPreferences("Login", MODE_PRIVATE);

        int id = sp1.getInt("Id", 0);
        if(id != 0)
        {
            //redirect to main page
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }
        else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreenActivity.this, StartActivity.class);
                    startActivity(intent);
                    finish();
                }
            },SPLASH_TIME_OUT);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("msg1","inside menu activity");
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }
}