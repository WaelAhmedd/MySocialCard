package com.wael.android.mycard.Sign.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.wael.android.mycard.Main.View.MainActivity;
import com.wael.android.mycard.R;
import com.wael.android.mycard.Sign.Check_internet;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 5000;
    Check_internet check_internet;
    int what;
        ImageView imageView;
    public void setWhat(int what) {
        this.what = what;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        check_internet=new Check_internet(SplashActivity.this);
        check_internet.execute();
        imageView=findViewById(R.id.splash_iv);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                    if(what==1) {


                        /* Create an Intent that will start the Menu-Activity. */
                        Intent mainIntent = new Intent(SplashActivity.this, SignActivity.class);
                        SplashActivity.this.startActivity(mainIntent);
                        SplashActivity.this.finish();
                    }
                    else {

                        imageView.setImageResource(R.drawable.nointernet);
                    }

            }
        }, SPLASH_DISPLAY_LENGTH);
    }
    }

