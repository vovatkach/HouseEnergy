package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.ResultPagerAdapter;
import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.ObjectiveFunction;
import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.Suit;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

public class OptimizationResultAct extends AppCompatActivity {
    ImageView ivNavBack;
    int cost;
    int setId;
    Set mySet;
    List<List<Suit>> resultList;

    ViewPager viewPager;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimization_result);
        ivNavBack=(ImageView)findViewById(R.id.ivNabBackResultOptimizationAct);
        viewPager=(ViewPager)findViewById(R.id.vpResultOptimization);
        tabLayout=(TabLayout)findViewById(R.id.tlResultOptimization);

        Intent intent=getIntent();
        cost=intent.getIntExtra("Cost",500);
        setId=intent.getIntExtra("setId",0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker=new DataBaseWorker(OptimizationResultAct.this);
                mySet=dataBaseWorker.getSet(setId);
                dataBaseWorker.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                       TextView tvTitle=(TextView)findViewById(R.id.tvTitleResultOptimizationAct);
                        tvTitle.setText(mySet.getName());
                    }
                });
                ObjectiveFunction funOptimization=new ObjectiveFunction();
                SharedPreferences preferences=getSharedPreferences("tariff",MODE_PRIVATE);
                float minCost=preferences.getFloat("costMin",(float) 0.9);
                float maxCost=preferences.getFloat("costMax",(float)1.68);
                int limit=preferences.getInt("limit",3000);
                float powerForDay;
                if(limit*minCost>cost)
                {
                    float costForDay=(float)cost/31;
                    powerForDay=(costForDay/minCost)*1000;
                }else
                    {
                        float costForDay=(float)cost/31;
                        powerForDay=(costForDay/maxCost)*1000;
                    }
                resultList=funOptimization.findTheOptimalSuite(mySet.getListOfDevice(),powerForDay);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (!resultList.isEmpty())
                        {
                            viewPager.setAdapter(new ResultPagerAdapter(getSupportFragmentManager(),OptimizationResultAct.this,resultList));
                            tabLayout.setupWithViewPager(viewPager);
                        }

                    }
                });
            }
        });
        thread.start();
        //------------------------------------------------------------------------------
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
                        Intent intent=new Intent(OptimizationResultAct.this, SetOptimizationAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
