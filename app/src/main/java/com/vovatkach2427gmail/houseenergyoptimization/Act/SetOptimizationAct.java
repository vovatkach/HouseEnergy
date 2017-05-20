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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterDevicesOfSet;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

public class SetOptimizationAct extends AppCompatActivity {
    RecyclerView rvDevices;
    RecyclerView.LayoutManager layoutManager;
    RVAdapterDevicesOfSet adapter;
    Set set;
    Button btnGoOptimization;
    EditText etCost;
    ImageView ivNavBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_optimization);
        rvDevices=(RecyclerView)findViewById(R.id.rvCertainSet);
        btnGoOptimization=(Button)findViewById(R.id.btnGoOptimization);
        etCost=(EditText)findViewById(R.id.etCostOfOptimization);
        ivNavBack =(ImageView)findViewById(R.id.ivNavBackSetOptimizationAct);
        //-------------
        Intent intent=getIntent();
        DataBaseWorker dataBaseWorker=new DataBaseWorker(SetOptimizationAct.this);
        set=dataBaseWorker.getSet(intent.getIntExtra("setId",1));
        dataBaseWorker.close();
        //-------------------------------------------------
        TextView tvTitle=(TextView)findViewById(R.id.tvSetNameActSetOptimization);
        tvTitle.setText(set.getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        layoutManager=new LinearLayoutManager(SetOptimizationAct.this);
        rvDevices.setLayoutManager(layoutManager);
        adapter=new RVAdapterDevicesOfSet(set.getListOfDevice());
        rvDevices.setAdapter(adapter);
        //---------------------------------------
        btnGoOptimization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etCost.getText().toString().equals(""))
                {
                    Toast toast=Toast.makeText(SetOptimizationAct.this,"Введіть суму для оптимізації",Toast.LENGTH_SHORT);
                    toast.show();
                }else
                    {
                    Intent intent = new Intent(SetOptimizationAct.this, OptimizationResultAct.class);
                        intent.putExtra("Cost",Integer.parseInt(etCost.getText().toString()));
                        intent.putExtra("setId",set.getId());
                    startActivity(intent);
                    overridePendingTransition(R.anim.in_left, R.anim.out_right);
                }
            }
        });
        //---------------------------------------
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
                        Intent intent=new Intent(SetOptimizationAct.this, MySetsAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
