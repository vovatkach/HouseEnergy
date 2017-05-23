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
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterDevivesAddSetAct;
import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

public class AddSetAct extends AppCompatActivity {
    ImageView ivNavBack;

    List<Device> devices;
    RVAdapterDevivesAddSetAct adapter;

    RecyclerView rvDevices;
    Button btnCreateSet;
    EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_set);
        rvDevices=(RecyclerView)findViewById(R.id.rvListOfDeviceAddSetAct);
        btnCreateSet=(Button)findViewById(R.id.btnCreateSetAddSetAct);
        etName=(EditText)findViewById(R.id.etNameOfSetAddSetAct);
    }

    @Override
    protected void onResume() {
        super.onResume();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(AddSetAct.this);
        rvDevices.setHasFixedSize(true);
        rvDevices.setLayoutManager(layoutManager);
        Thread thread=new Thread(new Runnable() {
            @Override
            public void run() {
                DataBaseWorker dataBaseWorker=new DataBaseWorker(AddSetAct.this);
                devices=dataBaseWorker.getAllDevices();
                dataBaseWorker.close();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter=new RVAdapterDevivesAddSetAct(devices,AddSetAct.this);
                        rvDevices.setAdapter(adapter);
                    }
                });
            }
        });
        thread.start();
        //------------------------------------------------------
        btnCreateSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()||adapter.getChoouseDevices().isEmpty())
                {
                    Toast toast=Toast.makeText(AddSetAct.this,"Не введено ім'я набору або не вибрано жодного приладу",Toast.LENGTH_LONG);
                    toast.show();
                }else
                    {
                        DataBaseWorker dataBaseWorker=new DataBaseWorker(AddSetAct.this);
                        Set set=new Set(0,etName.getText().toString(),adapter.getChoouseDevices());
                        dataBaseWorker.addSet(set);
                        dataBaseWorker.close();
                        Toast toast=Toast.makeText(AddSetAct.this,"Набір створено",Toast.LENGTH_LONG);
                        toast.show();
                        etName.setText("");
                        adapter.clearChooseDevice();
                    }
            }
        });
        //------------------------------------------------------
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackAddSet);
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
                        Intent intent=new Intent(AddSetAct.this, MySetsAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
