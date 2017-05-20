package com.vovatkach2427gmail.houseenergyoptimization.Fragment;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Adapter.RVAdapterOptimizationResult;
import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.ObjectiveFunction;
import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.Suit;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemResultFragment extends Fragment {
    List<Suit> suits;

    TextView tvCost;
    TextView tvPriority;
    RecyclerView rvResultList;


    public static ItemResultFragment newInstance(ArrayList<Suit> suits)
    {
        Bundle bundle=new Bundle();
        bundle.putParcelableArrayList("myList",suits);
        ItemResultFragment fragment=new ItemResultFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        suits=getArguments().getParcelableArrayList("myList");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_item_result, container, false);
        tvCost=(TextView)view.findViewById(R.id.tvOptimizationCost);
        tvPriority=(TextView)view.findViewById(R.id.tvOptimizationPriority);
        rvResultList=(RecyclerView)view.findViewById(R.id.rvResultOptimization);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences=getContext().getSharedPreferences("tariff",MODE_PRIVATE);
        float minCost=preferences.getFloat("costMin",(float) 0.9);
        float maxCost=preferences.getFloat("costMax",(float)1.68);
        int limit=preferences.getInt("limit",3000);

        double result;
        double powerFowDay=ObjectiveFunction.getCostOfSuits(suits)/1000;
        if(limit>powerFowDay*31)
        {
             result=powerFowDay*minCost*31;
        }else
            {
                result=powerFowDay*maxCost*31;
            }
        tvCost.setText("Сума= "+Integer.toString((int)(result + 0.5d))+"грн");
        tvPriority.setText("Пріоритет= "+Double.toString(ObjectiveFunction.getPriorityOfSuits(suits)));
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        rvResultList.setLayoutManager(layoutManager);
        rvResultList.setHasFixedSize(true);
        RVAdapterOptimizationResult adapter=new RVAdapterOptimizationResult(suits);
        rvResultList.setAdapter(adapter);
    }
}
