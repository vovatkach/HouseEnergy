package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vovat on 22.05.2017.
 */

public class RVAdapterDevivesAddSetAct extends RecyclerView.Adapter<RVAdapterDevivesAddSetAct.DevicesAddSetViewHolder> {
    List<Device> devices;
    List<Device> choouseDevices;
    Context context;
    public RVAdapterDevivesAddSetAct(List<Device> devices,Context context)
    {
        this.devices=devices;
        this.choouseDevices=new ArrayList<>();
        this.context=context;
    }

    public static class DevicesAddSetViewHolder extends RecyclerView.ViewHolder
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

        Button btnAddToSet;

        public DevicesAddSetViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceNameAddSetAct);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoAddSetAct);

            tvPower=(TextView)itemView.findViewById(R.id.tvPowerItemDeviceAddSetAct);
            sbPower=(SeekBar)itemView.findViewById(R.id.sbPowerItemDeviceAddSetAct);

            tvTmin=(TextView)itemView.findViewById(R.id.tvMinTimeOfWorkItemDeviceAddSetAct);
            sbTmin=(SeekBar)itemView.findViewById(R.id.sbMinTimeOfWorkItemDeviceAddSetAct);

            tvTmax=(TextView)itemView.findViewById(R.id.tvMaxTimeOfWorkItemDeviceAddSetAct);
            sbTmax=(SeekBar)itemView.findViewById(R.id.sbMaxTimeOfWorkItemDeviceAddSetAct);

            tvPriority=(TextView)itemView.findViewById(R.id.tvPriorityItemDeviceAddSetAct);
            sbPriority=(SeekBar)itemView.findViewById(R.id.sbPriorityItemDeviceAddSetAct);

            btnAddToSet=(Button)itemView.findViewById(R.id.btnAddDeviceAddSetAct);
        }
    }

    @Override
    public RVAdapterDevivesAddSetAct.DevicesAddSetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_of_add_set, parent, false);
        RVAdapterDevivesAddSetAct.DevicesAddSetViewHolder pvh = new  RVAdapterDevivesAddSetAct.DevicesAddSetViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RVAdapterDevivesAddSetAct.DevicesAddSetViewHolder holder, final int position) {
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

        //------------------------------------------------------------------------
        holder.sbPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.tvPower.setText("Потужність приладу "+progress+"ВТ");
                devices.get(position).setPowerConsumption(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //-------------------------------------------------------------------------------
        holder.sbTmin.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.tvTmin.setText("Час роботи(мінімальний) "+progress+" годин");
                devices.get(position).settMin(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //-------------------------------------------------------------------------------
        holder.sbTmax.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.tvTmax.setText("Час роботи(максимальний) "+progress+" годин");
                devices.get(position).settMax(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //----------------------------------------------------------------------------------
        holder.sbPriority.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.tvPriority.setText("Пріоритет приладу "+progress+"");
                devices.get(position).setPriority(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //--------------------------------------------------------------------------------------
        holder.btnAddToSet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choouseDevices.add(devices.get(position));
                Toast toast=Toast.makeText(context,"Прилаж додано",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return devices.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
    public List<Device> getChoouseDevices()
    {
        return choouseDevices;
    }
    public void clearChooseDevice()
    {
       choouseDevices.clear();
    }
}

