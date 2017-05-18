package com.vovatkach2427gmail.houseenergyoptimization.Act;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.vovatkach2427gmail.houseenergyoptimization.DB.DataBaseWorker;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

public class SetOptimizationAct extends AppCompatActivity {
    List<Device> devices;
    RecyclerView rvDevices;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_optimization);
        rvDevices=(RecyclerView)findViewById(R.id.rvCertainSet);
        //-------------
        DataBaseWorker dataBaseWorker=new DataBaseWorker(SetOptimizationAct.this);
        devices=dataBaseWorker.getAllDevices();
        dataBaseWorker.close();
        //-------------------------------------------------
    }

    @Override
    protected void onResume() {
        super.onResume();
        layoutManager=new LinearLayoutManager(SetOptimizationAct.this);
        rvDevices.setLayoutManager(layoutManager);
    }
}
