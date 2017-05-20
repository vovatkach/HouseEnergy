package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;
import com.vovatkach2427gmail.houseenergyoptimization.DB.Translator;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

public class PriewAct extends AppCompatActivity {
    CarouselView carouselView;
    Button btnGoToMeSets;
    CheckBox cbNotToShow;
    int [] imgs=
            {
                    R.drawable.priew_act_carosel_img1,
                    R.drawable.priew_act_carosel_img2,
                    R.drawable.priew_act_carosel_img3
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_priew);
        carouselView=(CarouselView)findViewById(R.id.carouselPriewAct);
        btnGoToMeSets=(Button)findViewById(R.id.btnGoToMySets);
        cbNotToShow=(CheckBox)findViewById(R.id.cbNotShowPriew);
        carouselView.setPageCount(imgs.length);
        carouselView.setImageListener(new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {
                imageView.setImageResource(imgs[position]);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnGoToMeSets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(cbNotToShow.isChecked())
                {
                    SharedPreferences preferences= getSharedPreferences("work", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=preferences.edit();
                    editor.putString("isPreview","no");
                    editor.commit();
                }
                Intent goToSelectCity=new Intent(PriewAct.this,MySetsAct.class);
                startActivity(goToSelectCity);
                overridePendingTransition(R.anim.in_left,R.anim.out_right);

            }
        });
        //-------------------------------
        /*
        List<Device> devices=new ArrayList<>();
        devices.add(new Device(1,"Телевізор","улюблений",20,2,4,24));
        devices.add(new Device(1,"Ноутбук","робочий",15,5,5,24));
        devices.add(new Device(1,"Пральна машина","потрібна",10,1,1,24));
        devices.add(new Device(1,"Лампа","романтична",1,2,5,10));
        devices.add(new Device(1,"Пилосос","новий",1,2,1,3));
        devices.add(new Device(1,"Холодильний","тата",15,2,10,24));
        Log.d("myLog", Translator.listOfDevivesToJson(devices));
        */
    }


}
