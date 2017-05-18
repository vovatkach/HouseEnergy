package com.vovatkach2427gmail.houseenergyoptimization.Adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.vovatkach2427gmail.houseenergyoptimization.Act.AddSetAct;
import com.vovatkach2427gmail.houseenergyoptimization.Act.MySetsAct;
import com.vovatkach2427gmail.houseenergyoptimization.Act.SetOptimizationAct;
import com.vovatkach2427gmail.houseenergyoptimization.Model.Set;
import com.vovatkach2427gmail.houseenergyoptimization.R;

import java.util.List;

/**
 * Created by vovat on 16.05.2017.
 */

public class RVAdapterSets extends RecyclerView.Adapter<RVAdapterSets.SetViewHolder>{
    private List<Set> sets;
    private Activity activity;
    public RVAdapterSets(List<Set> sets,Activity activity)
    {
     this.sets=sets;
        this.activity=activity;
    }
    public static class SetViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        TextView tvName;
        ImageView ivDelete;
        public SetViewHolder(View itemView) {
            super(itemView);
            cardView=(CardView)itemView.findViewById(R.id.cardSet);
            tvName=(TextView)itemView.findViewById(R.id.tvSetName);
            ivDelete=(ImageView)itemView.findViewById(R.id.ivDeleteSet);
        }
    }
    @Override
    public SetViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_set, parent, false);
        SetViewHolder pvh = new SetViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final SetViewHolder holder, int position) {
        holder.tvName.setText(sets.get(position).getName());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(activity, SetOptimizationAct.class);
                activity.startActivity(intent);
                activity.overridePendingTransition(R.anim.in_left,R.anim.out_right);
            }
        });
        holder.ivDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animatorX=ObjectAnimator.ofFloat(holder.ivDelete,View.SCALE_X,1.0f, 0.85f, 1.15f, 1.0f);
                ObjectAnimator animatorY=ObjectAnimator.ofFloat(holder.ivDelete,View.SCALE_Y,1.0f, 0.85f, 1.15f, 1.0f);
                AnimatorSet animatorSet=new AnimatorSet();
                animatorSet.play(animatorX).with(animatorY);
                animatorSet.setDuration(50);
                animatorSet.start();
                animatorSet.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                       Toast toast=Toast.makeText(activity,"Запис видалено",Toast.LENGTH_SHORT);
                       toast.show();
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return sets.size();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

}
