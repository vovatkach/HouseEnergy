package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

/**
 * Created by vovat on 21.05.2017.
 */

public class RVAdapterDevices extends RecyclerView.Adapter<RVAdapterDevices.DevicesViewHolder>{
    List<Device> devices;
    public RVAdapterDevices(List<Device> devices)
    {
        this.devices=devices;
    }



    public static class DevicesViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;
        TextView tvPower;
        SeekBar sbPower;

        public DevicesViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceName);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoDevice);

            tvPower=(TextView)itemView.findViewById(R.id.tvPowerDevice);
            sbPower=(SeekBar)itemView.findViewById(R.id.sbPowerDevice);
        }
    }

    @Override
    public RVAdapterDevices.DevicesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device, parent, false);
        RVAdapterDevices.DevicesViewHolder pvh = new RVAdapterDevices.DevicesViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(RVAdapterDevices.DevicesViewHolder holder, int position) {
        holder.tvDeviceName.setText(devices.get(position).getName());
        holder.tvDeviceExtraInfo.setText(devices.get(position).getExtraInfo());

        holder.tvPower.setText("Потужність приладу "+Integer.toString(devices.get(position).getPowerConsumption())+"ВТ");
        holder.sbPower.setProgress(devices.get(position).getPowerConsumption());
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

