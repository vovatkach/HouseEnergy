package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.vovatkach2427gmail.houseenergyoptimization.Model.Device;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.text.DecimalFormat;
import java.util.List;

/**
 * Created by vovat on 21.05.2017.
 */

public class RVAdapterStatistics extends RecyclerView.Adapter<RVAdapterStatistics.DevicesStatisticsViewHolder>{
    List<Device> devices;
    int limit;
    float costMin;
    float costMax;
    public RVAdapterStatistics(List<Device> devices, Context context)
    {
        this.devices=devices;
        SharedPreferences preferences=context.getSharedPreferences("tariff",Context.MODE_PRIVATE);
        limit=preferences.getInt("limit",3000);
        costMin=preferences.getFloat("costMin",(float) 0.9);
        costMax=preferences.getFloat("costMax",(float)1.68);
    }



    public static class DevicesStatisticsViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;
        TextView tvPower;
        TextView tvBeforeCaption;
        TextView tvAfterCaption;
        TextView tvBeforeCost;
        TextView tvAfterCost;
        SeekBar sbPower;

        public DevicesStatisticsViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById(R.id.tvDeviceNameStatistics);
            tvDeviceExtraInfo=(TextView)itemView.findViewById(R.id.tvExtraInfoDeviceStatistics);

            tvPower=(TextView)itemView.findViewById(R.id.tvPowerDeviceStatistics);

            tvBeforeCaption=(TextView)itemView.findViewById(R.id.tvTariffBeforeLimitStatistics);
            tvAfterCaption=(TextView)itemView.findViewById(R.id.tvTariffAfterLimitStatistics);

            tvBeforeCost=(TextView)itemView.findViewById(R.id.tvCostForHourBeforeLimitStatistics);
            tvAfterCost=(TextView)itemView.findViewById(R.id.tvCostForHourAfterLimitStatistics);

            sbPower=(SeekBar)itemView.findViewById(R.id.sbProgressStatistics);
        }
    }

    @Override
    public RVAdapterStatistics.DevicesStatisticsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_device_statisc, parent, false);
        RVAdapterStatistics.DevicesStatisticsViewHolder pvh = new RVAdapterStatistics.DevicesStatisticsViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final RVAdapterStatistics.DevicesStatisticsViewHolder holder, final int position) {
        holder.tvDeviceName.setText(devices.get(position).getName());
        holder.tvDeviceExtraInfo.setText(devices.get(position).getExtraInfo());

        holder.tvPower.setText("Потужність приладу "+Integer.toString(devices.get(position).getPowerConsumption())+"ВТ");
        holder.sbPower.setProgress(devices.get(position).getPowerConsumption());

        holder.tvBeforeCaption.setText("Тариф до "+Integer.toString(limit)+"");
        holder.tvAfterCaption.setText("Тариф до "+Integer.toString(limit)+"");

        String[] costs=getCosts(holder.sbPower.getProgress());
        holder.tvBeforeCost.setText(""+costs[0]+" грн в годину");
        holder.tvAfterCost.setText(""+costs[1]+" грн в годину");

        holder.sbPower.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                holder.tvPower.setText("Потужність приладу "+Integer.toString(progress)+"ВТ");
                String[] costs=getCosts(progress);
                holder.tvBeforeCost.setText(""+costs[0]+" грн в годину");
                holder.tvAfterCost.setText(""+costs[1]+" грн в годину");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

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
    private String[] getCosts(int power)
    {
        float costMin=this.costMin*((float) power/1000);
        float costMax=this.costMax*((float) power/1000);

        String pattern = "##0.000";
        DecimalFormat decimalFormat = new DecimalFormat(pattern);
        String[] mas=new String[2];
        mas[0]= decimalFormat.format(costMin);
        mas[1] = decimalFormat.format(costMax);
        return mas;
    }
}

