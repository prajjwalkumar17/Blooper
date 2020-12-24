package com.rejointech.blooper.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.rejointech.blooper.MainActivity;
import com.rejointech.blooper.R;

public class splashActivity extends AppCompatActivity {
    Animation fade, move, away;
    TextView textView;
    LottieAnimationView lottie;
    ImageView bloopImage, splashback;
    ProgressBar splashProgress;
    private static final int NUM_PAGES = 2;
    private ViewPager viewPager;
    private splashPagerAdapter splashPagerAdapter;
    private SharedPreferences sharedPreferences;
    private static int SPLASH_TIME_OUT = 4100;
    private FirebaseAuth auth;


    @Override
    public void onStart() {
        super.onStart();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            startActivity(new Intent(getApplicationContext(), SigninMethodsHolder.class));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        viewPager = findViewById(R.id.pager);
        auth = FirebaseAuth.getInstance();
        splashPagerAdapter = new splashPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(splashPagerAdapter);
        textView = findViewById(R.id.textView);
        bloopImage = findViewById(R.id.bloopImage);
        lottie = findViewById(R.id.lottie);
        splashProgress = findViewById(R.id.splashProgress);
        splashback = findViewById(R.id.splashback);
        fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        move = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.movinguptodown);
        bloopImage.startAnimation(move);
        textView.startAnimation(fade);
        splashback.animate().translationY(-2500).setDuration(1000).setStartDelay(4000);
        bloopImage.animate().translationY(1400).setDuration(1000).setStartDelay(3500);
        lottie.animate().translationY(1400).setDuration(1000).setStartDelay(3000);
        splashProgress.animate().translationY(1400).setDuration(1000).setStartDelay(2000);
        textView.animate().translationY(1400).setDuration(1000).setStartDelay(2500);
        away=AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fadeaway);
        viewPager.startAnimation(away);

/*
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sharedPreferences=getSharedPreferences("sharedpref",MODE_PRIVATE);
                boolean isfirsttime = sharedPreferences.getBoolean("firsttime",true);
                if(isfirsttime){
                    //onboardingactivity in same activity so no need to use intent
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putBoolean("firsttime",false);
                    editor.apply();
                    finish();
                } else {
                    startActivity(new Intent(splashActivity.this,SigninMethodsHolder.class));
                    finish();
                }
            }
        },SPLASH_TIME_OUT);
*/
    }



    private static class splashPagerAdapter extends FragmentStatePagerAdapter{
        public splashPagerAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){

                case 0:
                    overVIew1 tab1=new overVIew1();
                    return tab1;
                case 1:
                    overView2 tab2=new overView2();
                    return tab2;


            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}