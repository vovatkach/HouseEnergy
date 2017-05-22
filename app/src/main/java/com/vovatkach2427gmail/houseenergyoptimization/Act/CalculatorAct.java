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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterCalc;
import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.Suit;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

public class CalculatorAct extends AppCompatActivity {
    ImageView ivNavBack;
    RecyclerView rvCalc;
    RVAdapterCalc adapterCalc;
    List<Device> devices;

    Button btnCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        rvCalc=(RecyclerView)findViewById(R.id.rvCalculator);
        btnCalc=(Button)findViewById(R.id.btnCalc);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker=new DataBaseWorker(CalculatorAct.this);
                devices=dataBaseWorker.getAllDevices();
                dataBaseWorker.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(CalculatorAct.this);
                        adapterCalc=new RVAdapterCalc(devices);
                        rvCalc.setLayoutManager(layoutManager);
                        rvCalc.setAdapter(adapterCalc);
                    }
                });
            }
        });
        thread.start();
        //------------------------------------------------
        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(adapterCalc.getDevicesToCalc().isEmpty())
                {
                    Toast toast=Toast.makeText(CalculatorAct.this,"Виберіть хочаб один прилад",Toast.LENGTH_SHORT);
                    toast.show();
                }else {
                Intent intent=new Intent(CalculatorAct.this,ResultCalcActivity.class);
                Bundle bundle=new Bundle();
                ArrayList<Device> arrayList=new ArrayList<Device>(adapterCalc.getDevicesToCalc());
                bundle.putParcelableArrayList("myList",arrayList);
                intent.putExtra("list",bundle);
                startActivity(intent);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);}
            }
        });
        //-------------------------------------------------
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackCalculator);
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
                        Intent intent=new Intent(CalculatorAct.this, MySetsAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
