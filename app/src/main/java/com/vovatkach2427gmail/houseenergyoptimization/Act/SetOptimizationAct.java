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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterDevicesOfSet;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

public class SetOptimizationAct extends AppCompatActivity {
    RecyclerView rvDevices;
    RecyclerView.LayoutManager layoutManager;
    RVAdapterDevicesOfSet adapter;
    Set set;
    Button btnGoOptimization;
    TextView tvCost;
    ImageView ivNavBack;
    SeekBar sbCost;

    int costMin;
    int costMax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_optimization);
        rvDevices=(RecyclerView)findViewById(R.id.rvCertainSet);
        btnGoOptimization=(Button)findViewById(R.id.btnGoOptimization);
        tvCost =(TextView) findViewById(R.id.tvCostOfOptimization);
        ivNavBack =(ImageView)findViewById(R.id.ivNavBackSetOptimizationAct);
        sbCost=(SeekBar)findViewById(R.id.sbCostOfOptimization);
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
        costMin=1;
        costMax=0;
        for (Device device:set.getListOfDevice())
        {
            float power=(float) device.getPowerConsumption()/1000;
            costMin+=power*device.gettMin()*31*0.9;
            costMax+=power*device.gettMax()*31*0.9;
        }
        Log.d("myLog",Integer.toString(costMin));
        Log.d("myLog",Integer.toString(costMax));
        sbCost.setMax(costMax-costMin);
        sbCost.setProgress((costMax-costMin)/2);
        tvCost.setText(Integer.toString(sbCost.getProgress()));
        //---------------------------------------
        btnGoOptimization.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tvCost.getText().toString().equals(""))
                {
                    Toast toast=Toast.makeText(SetOptimizationAct.this,"Введіть суму для оптимізації",Toast.LENGTH_SHORT);
                    toast.show();
                }else
                    {
                    Intent intent = new Intent(SetOptimizationAct.this, OptimizationResultAct.class);
                        intent.putExtra("Cost",Integer.parseInt(tvCost.getText().toString()));
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
        //----------------------------------------
        sbCost.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvCost.setText(Integer.toString(costMin+progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
