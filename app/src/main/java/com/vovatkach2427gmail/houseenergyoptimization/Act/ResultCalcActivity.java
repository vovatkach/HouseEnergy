package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterCalcResult;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

public class ResultCalcActivity extends AppCompatActivity {
    List<Device> devices;

    ImageView ivNavBack;
    TextView tvCost;

    RecyclerView rvDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_calc);
        Intent intent=getIntent();
        Bundle bundle=intent.getBundleExtra("list");
        devices=bundle.getParcelableArrayList("myList");
        rvDevices=(RecyclerView)findViewById(R.id.rvResultCalc);
    }

    @Override
    protected void onResume() {
        super.onResume();
        tvCost=(TextView)findViewById(R.id.tvCostOfCacl);
        SharedPreferences preferences=getSharedPreferences("tariff",MODE_PRIVATE);
        int limit=preferences.getInt("limit",3000);
        float minCost=preferences.getFloat("costMin",(float) 0.9);
        float maxCost=preferences.getFloat("costMax",(float)1.68);
        int allPower=0;
        for (Device device:devices)
        {
            allPower+=device.getPowerConsumption()*device.gettMin();
        }
       if (allPower*31<=limit*1000)
       {
           int cost= (int) (((float)allPower/1000)*minCost*31);
           tvCost.setText("Ціна в місяць "+cost+" грн");
       }else
           {
               int cost= (int) (((float)allPower/1000)*maxCost*31);
               tvCost.setText("Ціна в місяць "+cost+" грн");
           }

           RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(ResultCalcActivity.this);
        RVAdapterCalcResult adapterCalcResult=new RVAdapterCalcResult(devices);
        rvDevices.setHasFixedSize(true);
        rvDevices.setLayoutManager(layoutManager);
        rvDevices.setAdapter(adapterCalcResult);
     //------------------------------------------------------------
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackResultCalc);
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
                        Intent intent=new Intent(ResultCalcActivity.this, CalculatorAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
