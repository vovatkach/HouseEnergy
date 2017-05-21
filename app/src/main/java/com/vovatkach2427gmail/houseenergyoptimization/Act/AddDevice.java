package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

public class AddDevice extends AppCompatActivity {
    ImageView ivNavBack;
    EditText etName;
    EditText etExtraInfo;
    TextView tvPower;
    SeekBar sbPower;
    Button btnAddDevice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);
        etExtraInfo=(EditText)findViewById(R.id.etExtraInfoAddDeviceAct);
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackAddDeviceToBase);
        tvPower=(TextView)findViewById(R.id.tvPowerAddDeviceAct);
        sbPower=(SeekBar)findViewById(R.id.sbPowerAddDeviceAct);
        btnAddDevice=(Button)findViewById(R.id.btnAddDeviceToBase);
        tvPower.setText("Потужність "+Integer.toString(sbPower.getProgress())+" ВТ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //-----------------------------------------------
        btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etName.getText().toString().isEmpty()||etExtraInfo.getText().toString().isEmpty())
                {
                    Toast toast=Toast.makeText(AddDevice.this,"Заповніть всі поля",Toast.LENGTH_SHORT);
                    toast.show();
                }else
                    {
                        Device device=new Device(1,etName.getText().toString(),etExtraInfo.getText().toString(),sbPower.getProgress(),1,1,24);
                        DataBaseWorker dataBaseWorker=new DataBaseWorker(AddDevice.this);
                        dataBaseWorker.addDevice(device);
                        dataBaseWorker.close();
                        Toast toast=Toast.makeText(AddDevice.this,"Прилад додано",Toast.LENGTH_SHORT);
                        toast.show();
                        etName.setText("");
                        etExtraInfo.setText("");
                        sbPower.setProgress(100);
                    }
            }
        });
        //-----------------------------------------------
        sbPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvPower.setText("Потужність "+Integer.toString(progress)+" ВТ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //-----------------------------------------------
        etName=(EditText)findViewById(R.id.etNameDeviceAddDeviceAct);
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
                        Intent intent=new Intent(AddDevice.this, BaseAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }
}
