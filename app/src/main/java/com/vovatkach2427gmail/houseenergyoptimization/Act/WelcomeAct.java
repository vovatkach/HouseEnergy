package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Interpolator;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.R;

public class WelcomeAct extends AppCompatActivity {
    TextView tvWelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        tvWelcome=(TextView)findViewById(R.id.tvWelcome);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Interpolator interpolator=new AccelerateInterpolator();
        ObjectAnimator animator=ObjectAnimator.ofFloat(tvWelcome, View.ALPHA,0.0f,1.0f);
        animator.setDuration(5000);
        animator.setInterpolator(interpolator);
        tvWelcome.setVisibility(View.VISIBLE);
        animator.start();
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                SharedPreferences preferences=getSharedPreferences("work",MODE_PRIVATE);
                if(preferences.getString("isPreview","yes").equals("yes")){
                    Intent goToPriewAct=new Intent(WelcomeAct.this,PriewAct.class);
                    startActivity(goToPriewAct);
                    overridePendingTransition(R.anim.in_left,R.anim.out_right);
                }else
                {
                    Intent goToMySetsAct=new Intent(WelcomeAct.this,MySetsAct.class);
                    startActivity(goToMySetsAct);
                    overridePendingTransition(R.anim.in_left,R.anim.out_right);
                }
            }
        });
    }
}
