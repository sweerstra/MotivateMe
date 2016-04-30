package com.motivateme;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        boolean check = getSharedPreferences("com.motivateme.data", Context.MODE_PRIVATE)
                .getBoolean("login-confirmation", true);

        try {
            Thread.sleep(1000);
            startActivity(new Intent(this, check ? LoginActivity.class : MainActivity.class));
            finish();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
