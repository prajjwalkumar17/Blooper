package com.rejointech.blooper;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements navCotroller.drawer,navCotroller.toolController{
   DrawerLayout drawer_layout;
    NavigationView nav_view;
    View tool;
    int uiui;
    BottomNavigationView botnav;
    ImageView imageView2;
    Animation rotate;
    NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        nav_view = findViewById(R.id.nav_view);
        botnav=findViewById(R.id.botnav);
        rotate= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate);

        navController= Navigation.findNavController(this,R.id.fragmentHost);

        drawer_layout = findViewById(R.id.drawer_layout);
        imageView2=findViewById(R.id.imageView2);
        botnav.setBackground(null);
        tool=findViewById(R.id.tool);
        botnav.getMenu().getItem(2).setEnabled(false);

        NavigationUI.setupWithNavController(botnav,navController);
        NavigationUI.setupWithNavController(nav_view,navController);
    }
    public void clickMenu(View view){
        opemDrawer(drawer_layout);
        imageView2.startAnimation(rotate);
    }

    private static void opemDrawer(DrawerLayout drawer_layout) {
        drawer_layout.openDrawer(GravityCompat.START);
    }

    private static void closeDrawer(DrawerLayout drawer_layout) {
        drawer_layout.closeDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {
        if(drawer_layout.isDrawerOpen(GravityCompat.START)){
            closeDrawer(drawer_layout);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void setDrawer_locked() {
        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }

    @Override
    public void setDrawer_unlocked() {

        drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public void setToolInvisible() {
        tool.setVisibility(View.INVISIBLE);


    }

    @Override
    public void setToolVisible() {
        tool.setVisibility(View.VISIBLE);

    }

    @Override
    public void toolbuttonunclickable() {
        botnav.getMenu().getItem(4).setEnabled(true);


    }

    @Override
    public void toolbuttonunnotclickable() {
        botnav.getMenu().getItem(4).setEnabled(false);


    }
}