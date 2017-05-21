package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterDevices;
import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterStatistics;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

public class StatisticAct extends AppCompatActivity {
    ImageView ivNavBack;
    List<Device> devices;
    RecyclerView rvDeviceStatistics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        rvDeviceStatistics=(RecyclerView)findViewById(R.id.rvStatistics);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker=new DataBaseWorker(StatisticAct.this);
                devices=dataBaseWorker.getAllDevices();
                dataBaseWorker.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(StatisticAct.this);
                        rvDeviceStatistics.setLayoutManager(layoutManager);
                        RVAdapterStatistics adapterDevices=new RVAdapterStatistics(devices,StatisticAct.this);
                        rvDeviceStatistics.setAdapter(adapterDevices);
                    }
                });
            }
        });
        thread.start();
        //-----------------------------------------------------
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackStatistics);
        ivNavBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(ivNavBack,View.SCALE_X,1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(ivNavBack,View.SCALE_Y,1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(50);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        Intent intent=new Intent(StatisticAct.this, MySetsAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
