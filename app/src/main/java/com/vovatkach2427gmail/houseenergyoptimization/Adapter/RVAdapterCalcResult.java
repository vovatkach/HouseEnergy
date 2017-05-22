package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 22.05.2017.
 */

public class RVAdapterCalcResult extends RecyclerView.Adapter<RVAdapterCalcResult.DevicesCalcResultViewHolder> {
    List<Device> devices;
    public RVAdapterCalcResult(List<Device> devices)
    {
        this.devices=devices;
    }

    public static class DevicesCalcResultViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;
        TextView tvPower;
        TextView tvTime;
        public DevicesCalcResultViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceNameCalcResult);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoCalcResult);
            tvPower=(TextView)itemView.findViewById(R.id.tvPowerCalcResult);
            tvTime=(TextView)itemView.findViewById(R.id.tvTimeOfWorkingCalcResult);
        }
    }

    @Override
    public RVAdapterCalcResult.DevicesCalcResultViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calc_result, parent, false);
        RVAdapterCalcResult.DevicesCalcResultViewHolder pvh = new RVAdapterCalcResult.DevicesCalcResultViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RVAdapterCalcResult.DevicesCalcResultViewHolder holder, final int position) {
        holder.tvDeviceName.setText(devices.get(position).getName());
        holder.tvDeviceExtraInfo.setText(devices.get(position).getExtraInfo());

        holder.tvPower.setText("Потужність приладу "+Integer.toString(devices.get(position).getPowerConsumption())+"ВТ");
        holder.tvTime.setText("Час роботи "+Integer.toString(devices.get(position).gettMin())+"");
        //-----------------------------------------------------------------------------------------
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


