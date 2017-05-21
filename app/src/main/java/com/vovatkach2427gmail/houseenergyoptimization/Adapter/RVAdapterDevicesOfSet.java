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
 * Created by vovat on 17.05.2017.
 */

public class RVAdapterDevicesOfSet extends RecyclerView.Adapter<RVAdapterDevicesOfSet.DevicesOfSetViewHolder> {
    List<Device> devices;
    public RVAdapterDevicesOfSet(List<Device> devices)
    {
        this.devices=devices;
    }



    public static class DevicesOfSetViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;
        TextView tvPower;
        SeekBar sbPower;
        TextView tvTmin;
        SeekBar sbTmin;
        TextView tvTmax;
        SeekBar sbTmax;
        TextView tvPriority;
        SeekBar sbPriority;

        public DevicesOfSetViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceNameItemDevice);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoItemDevice);

            tvPower=(TextView)itemView.findViewById(R.id.tvPowerItemDevice);
            sbPower=(SeekBar)itemView.findViewById(R.id.sbPowerItemDevice);

            tvTmin=(TextView)itemView.findViewById(R.id.tvMinTimeOfWorkItemDevice);
            sbTmin=(SeekBar)itemView.findViewById(R.id.sbMinTimeOfWorkItemDevice);

            tvTmax=(TextView)itemView.findViewById(R.id.tvMaxTimeOfWorkItemDevice);
            sbTmax=(SeekBar)itemView.findViewById(R.id.sbMaxTimeOfWorkItemDevice);

            tvPriority=(TextView)itemView.findViewById(R.id.tvPriorityItemDevice);
            sbPriority=(SeekBar)itemView.findViewById(R.id.sbPriorityItemDevice);
        }
    }

    @Override
    public DevicesOfSetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_of_set, parent, false);
        DevicesOfSetViewHolder pvh = new DevicesOfSetViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(DevicesOfSetViewHolder holder, int position) {
        holder.tvDeviceName.setText(devices.get(position).getName());
        holder.tvDeviceExtraInfo.setText(devices.get(position).getExtraInfo());

        holder.tvPower.setText("Потужність приладу "+Integer.toString(devices.get(position).getPowerConsumption())+"ВТ");
        holder.sbPower.setProgress(devices.get(position).getPowerConsumption());

        holder.tvTmin.setText("Час роботи(мінімальний) "+Integer.toString(devices.get(position).gettMin())+" годин");
        holder.sbTmin.setProgress(devices.get(position).gettMin());

        holder.tvTmax.setText("Час роботи(максимальний) "+Integer.toString(devices.get(position).gettMax())+" годин");
        holder.sbTmax.setProgress(devices.get(position).gettMax());

        holder.tvPriority.setText("Пріоритет приладу "+Integer.toString(devices.get(position).getPriority())+"");
        holder.sbPriority.setProgress(devices.get(position).getPriority());
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
