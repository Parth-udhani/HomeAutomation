package com.beyond.bookrental;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


import java.util.Objects;

public class SplashActivity extends AppCompatActivity {

    ImageView iv;
    TextView tv;
    SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Objects.requireNonNull(getSupportActionBar()).hide();
        sp = getSharedPreferences(ConstantSp.PREF,MODE_PRIVATE);
        iv = findViewById(R.id.splash_iv);
        tv = findViewById(R.id.splash_tv);
        AlphaAnimation animation = new AlphaAnimation(0,1);
        animation.setDuration(1000);

        iv.startAnimation(animation);
        tv.startAnimation(animation);

        new Handler().postDelayed(() -> {
            if(Objects.equals(sp.getString(ConstantSp.USER_TYPE, ""), "")){
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
            else if (true){
                startActivity(new Intent(SplashActivity.this,DashboardNavigationActivity.class));
                finish();
            }
            else{
                if (new ConnectionDetector(SplashActivity.this).networkConnected()) {
                } else {
                    new ConnectionDetector(SplashActivity.this).networkDisconnected();
                }
            }
        },3000);
    }
}
