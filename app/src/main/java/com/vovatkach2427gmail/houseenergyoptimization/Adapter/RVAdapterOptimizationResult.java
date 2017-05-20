package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Algoritm.Suit;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

/**
 * Created by vovat on 20.05.2017.
 */

public class RVAdapterOptimizationResult extends RecyclerView.Adapter<RVAdapterOptimizationResult.OptimizationResultViewHolder> {
    private List<Suit> suits;
    public RVAdapterOptimizationResult(List<Suit> suits)
    {
      this.suits=suits;
    }


    public static class OptimizationResultViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvName;
        TextView tvExtraInfo;
        TextView tvPower;
        TextView tvPriority;
        TextView tvTimeOfWOrking;
        public OptimizationResultViewHolder(View itemView) {
            super(itemView);
            tvName=(TextView)itemView.findViewById(R.id.tvResultOptimizationDeviceName);
            tvExtraInfo=(TextView)itemView.findViewById(R.id.tvResultOptimizationDeviceExtraInfo);
            tvPower=(TextView)itemView.findViewById(R.id.tvResultOptimizationDevicePower);
            tvPriority=(TextView)itemView.findViewById(R.id.tvResultOptimizationDevicePriority);
            tvTimeOfWOrking=(TextView)itemView.findViewById(R.id.tvResultOptimizationTimeOfWork);
        }
    }
    @Override
    public OptimizationResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_result_optimization, parent, false);
        OptimizationResultViewHolder pvh = new OptimizationResultViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(OptimizationResultViewHolder holder, int position) {
        holder.tvName.setText(suits.get(position).getDevice().getName());
        holder.tvExtraInfo.setText(suits.get(position).getDevice().getExtraInfo());
        holder.tvPower.setText("Потужність "+Integer.toString(suits.get(position).getDevice().getPowerConsumption())+" ВТ");
        holder.tvPriority.setText("Пріоритет "+Integer.toString(suits.get(position).getDevice().getPriority())+"");
        holder.tvTimeOfWOrking.setText("Час роботи "+Integer.toString(suits.get(position).getTimeOfWorknig())+" год");
    }

    @Override
    public int getItemCount() {
        return suits.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
