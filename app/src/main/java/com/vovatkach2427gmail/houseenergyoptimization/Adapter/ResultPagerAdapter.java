package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.Suit;
import com.vovatkach2427gmail.houseenergyoptimization.Fragment.ItemResultFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 19.05.2017.
 */

public class ResultPagerAdapter extends FragmentPagerAdapter {
    private String tabTitles[];
    private Context context;
    List<List<Suit>> resultList;

    public ResultPagerAdapter(FragmentManager fm, Context context,List<List<Suit>> resultList) {
        super(fm);
        this.context=context;
        this.resultList=resultList;
        tabTitles=new String[resultList.size()];
        for (int i=0;i<resultList.size();i++)
        {
            tabTitles[i]="Варіант "+Integer.toString(i+1);
        }

    }

    @Override
    public Fragment getItem(int position) {
        ArrayList<Suit> arrayList=new ArrayList<Suit>(resultList.get(position));
        return ItemResultFragment.newInstance(arrayList);
    }

    @Override
    public int getCount() {
        return resultList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
