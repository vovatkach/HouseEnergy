package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterSets;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

public class MySetsAct extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageView ivAddSett;
    RecyclerView rvSets;
    List<Set> sets;
    LinearLayoutManager layoutManager;
    RVAdapterSets adapterSets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //----------------------------------------------------------------------
        setContentView(R.layout.activity_my_sets);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(null);
        ivAddSett=(ImageView)findViewById(R.id.ivAddSet);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //------------------------------------------------------------------------
        rvSets=(RecyclerView)findViewById(R.id.rvSets);
        layoutManager=new LinearLayoutManager(MySetsAct.this);
        rvSets.setLayoutManager(layoutManager);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //-------------------------------------------------------------------------------
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker=new DataBaseWorker(MySetsAct.this);
                sets=dataBaseWorker.getSets();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapterSets=new RVAdapterSets(sets,MySetsAct.this);
                        rvSets.setAdapter(adapterSets);
                    }
                });
            }
        });
        thread.start();
        //--------------------------------------------------------------------------------
        ivAddSett.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(ivAddSett,View.SCALE_X,1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(ivAddSett,View.SCALE_Y,1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(50);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent=new Intent(MySetsAct.this, AddSetAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });

            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        switch (id)
        {
            case R.id.nav_tariff:
                Intent goToTariffAct=new Intent(MySetsAct.this,TariffAct.class);
                startActivity(goToTariffAct);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
