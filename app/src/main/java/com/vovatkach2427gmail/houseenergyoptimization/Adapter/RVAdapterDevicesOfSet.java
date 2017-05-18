package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

/**
 * Created by vovat on 17.05.2017.
 */

public class RVAdapterDevicesOfSet {
    public static class DevicesOfSetViewHolder extends RecyclerView.ViewHolder
    {
        TextView tvDeviceName;
        TextView tvDeviceExtraInfo;
        TextView tvPower;
        SeekBar cbPower;
        TextView tvTmin;
        SeekBar cbTmin;
        TextView tvTmax;
        SeekBar cbTmax;
        TextView tvPriority;
        SeekBar cbPriority;

        public DevicesOfSetViewHolder(View itemView) {
            super(itemView);
            tvDeviceName=(TextView)itemView.findViewById()
        }
    }
}
