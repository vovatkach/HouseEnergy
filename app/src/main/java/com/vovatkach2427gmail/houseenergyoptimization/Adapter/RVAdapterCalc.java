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
 * Created by vovat on 21.05.2017.
 */

public class RVAdapterCalc extends RecyclerView.Adapter<RVAdapterCalc.DevicesCalcViewHolder> {
    List<Device> devices;
    List<Device> devicesToCalc;
    public RVAdapterCalc(List<Device> devices)
    {
        this.devices=devices;
        devicesToCalc=new ArrayList<>();
    }



    public static class DevicesCalcViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;

        TextView tvPower;
        SeekBar sbPower;

        TextView tvTime;
        SeekBar sbTime;

        Button btnAddDevice;


        public DevicesCalcViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceNameCalc);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoCalc);

            tvPower=(TextView)itemView.findViewById(R.id.tvPowerCalc);
            sbPower=(SeekBar)itemView.findViewById(R.id.sbPowerCalc);

            tvTime=(TextView)itemView.findViewById(R.id.tvTimeOfWorkingCalc);
            sbTime=(SeekBar)itemView.findViewById(R.id.sbTimeOfWorkingCalc);

            btnAddDevice=(Button)itemView.findViewById(R.id.btnAddToListCalc);
        }
    }

    @Override
    public RVAdapterCalc.DevicesCalcViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calc, parent, false);
        RVAdapterCalc.DevicesCalcViewHolder pvh = new RVAdapterCalc.DevicesCalcViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RVAdapterCalc.DevicesCalcViewHolder holder, final int position) {
        holder.tvDeviceName.setText(devices.get(position).getName());
        holder.tvDeviceExtraInfo.setText(devices.get(position).getExtraInfo());

        holder.tvPower.setText("Потужність приладу "+Integer.toString(devices.get(position).getPowerConsumption())+"ВТ");
        holder.sbPower.setProgress(devices.get(position).getPowerConsumption());

        holder.sbTime.setProgress(12);
        devices.get(position).settMin(holder.sbTime.getProgress());
        holder.tvTime.setText("Час роботи в день "+Integer.toString(holder.sbTime.getProgress())+"");
        //-----------------------------------------------------------------------------------------
        holder.sbPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                devices.get(position).setPowerConsumption(progress);
                holder.tvPower.setText("Потужність приладу "+progress+"ВТ");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //------------------------------------------------------------------------------------------
        holder.sbTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                devices.get(position).settMin(progress);
                holder.tvTime.setText("Час роботи в день"+progress+"");

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        //------------------------------------------------------------------------------------------
        holder.btnAddDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              devicesToCalc.add(devices.get(position));
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
    //----------------------------------------------------------------------------------------------
    public List<Device> getDevicesToCalc()
    {
        return devicesToCalc;
    }
}

