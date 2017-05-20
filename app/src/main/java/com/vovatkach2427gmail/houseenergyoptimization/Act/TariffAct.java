package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.R;

public class TariffAct extends AppCompatActivity {
    ImageView ivNavBack;

    EditText etMinTariff;
    EditText etMaxTariff;
    EditText etLimit;
    TextView tvAfterLimit;

    Button btnChangeMinTariff;
    Button btnChangeMaxTariff;
    Button btnChangeLimit;

    TextView tvBeforeLimit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tariff);
        ivNavBack=(ImageView)findViewById(R.id.ivNavBackTariff);
        etMinTariff=(EditText)findViewById(R.id.etMinTariff);
        etMaxTariff=(EditText)findViewById(R.id.etMaxTariff);
        etLimit=(EditText)findViewById(R.id.etLimit);

        tvBeforeLimit=(TextView)findViewById(R.id.tvBeforeLimit);
        tvAfterLimit=(TextView)findViewById(R.id.tvAfterLimit);

        SharedPreferences preferences=getSharedPreferences("tariff",MODE_PRIVATE);
        etMinTariff.setText(Float.toString(preferences.getFloat("costMin",(float)0)));
        etMaxTariff.setText(Float.toString(preferences.getFloat("costMax",(float)1)));
        tvBeforeLimit.setText("До "+Integer.toString(preferences.getInt("limit",1000))+" кВТ");
        tvAfterLimit.setText("До "+Integer.toString(preferences.getInt("limit",1000))+" кВТ");
        etLimit.setText(Integer.toString(preferences.getInt("limit",1000)));

        btnChangeMinTariff=(Button)findViewById(R.id.btnChangeMinTariff);
        btnChangeMaxTariff=(Button)findViewById(R.id.btnChangeMaxTariff);
        btnChangeLimit=(Button)findViewById(R.id.btnChangeLimit);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //-----------------------------------------------------------------------------------
        etMinTariff.setOnFocusChangeListener(onFocusChangeListener);
        etMaxTariff.setOnFocusChangeListener(onFocusChangeListener);
        etLimit.setOnFocusChangeListener(onFocusChangeListener);

        btnChangeMinTariff.setOnClickListener(onClickListener);
        btnChangeMaxTariff.setOnClickListener(onClickListener);
        btnChangeLimit.setOnClickListener(onClickListener);
        //------------------------------------------------------------------------------------
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
                        Intent intent=new Intent(TariffAct.this, MySetsAct.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.in_left,R.anim.out_right);
                    }
                });
            }
        });
    }

    View.OnFocusChangeListener onFocusChangeListener=new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            switch (v.getId())
            {
                case R.id.etMinTariff:
                    if(hasFocus)
                    {
                        btnChangeMinTariff.setEnabled(true);
                    }else {
                        btnChangeMinTariff.setEnabled(false);
                    }
                    break;
                case R.id.etMaxTariff:
                    if(hasFocus)
                    {
                        btnChangeMaxTariff.setEnabled(true);
                    }else {
                        btnChangeMaxTariff.setEnabled(false);
                    }
                    break;
                case R.id.etLimit:
                    if(hasFocus)
                    {
                        btnChangeLimit.setEnabled(true);
                    }else
                        {
                            btnChangeLimit.setEnabled(false);
                        }
            }
        }
    };
    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId())
            {
                case R.id.btnChangeMinTariff:
                    SharedPreferences preferencesMin=getSharedPreferences("tariff",MODE_PRIVATE);
                    if(preferencesMin.getFloat("costMin",(float)0)!=Float.parseFloat(etMinTariff.getText().toString()))
                    {
                        SharedPreferences.Editor editor=preferencesMin.edit();
                        editor.putFloat("costMin",Float.parseFloat(etMinTariff.getText().toString()));
                        editor.apply();
                    }
                    break;
                case R.id.btnChangeMaxTariff:
                    SharedPreferences preferencesMax=getSharedPreferences("tariff",MODE_PRIVATE);
                    if(preferencesMax.getFloat("costMax",(float)0)!=Float.parseFloat(etMaxTariff.getText().toString()))
                    {
                        SharedPreferences.Editor editor=preferencesMax.edit();
                        editor.putFloat("costMax",Float.parseFloat(etMaxTariff.getText().toString()));
                        editor.apply();
                    }
                    break;
                case R.id.btnChangeLimit:
                    SharedPreferences preferencesChangeLimit=getSharedPreferences("tariff",MODE_PRIVATE);
                    if(preferencesChangeLimit.getInt("limit",0)!=Integer.parseInt(etLimit.getText().toString()))
                    {

                        tvBeforeLimit.setText("До "+etLimit.getText().toString()+" кВТ");
                        tvAfterLimit.setText("До "+etLimit.getText().toString()+" кВТ");

                        SharedPreferences.Editor editor=preferencesChangeLimit.edit();
                        editor.putInt("limit",Integer.parseInt(etLimit.getText().toString()));
                        editor.apply();
                    }
            }
        }
    };
}
