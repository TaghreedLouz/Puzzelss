package com.devtaghreed.puzzels.Model;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.devtaghreed.puzzels.R;
import com.devtaghreed.puzzels.RoomDataBase.Level;
import com.devtaghreed.puzzels.databinding.ItemRvStartPlayBinding;

import java.util.List;


public class Adapter extends RecyclerView.Adapter<Adapter.App_ViewHolder> {
    List<Level> levelArrayList;
    onClickListener onClickListener;
    public static String levelNumber;
    public static int getLevelId;


    public Adapter(List<Level> levelArrayList, onClickListener onClickListener) {
        this.levelArrayList = levelArrayList;
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public App_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemRvStartPlayBinding binding = ItemRvStartPlayBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new App_ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull App_ViewHolder holder, int position) {
        if (levelArrayList == null)
            return;
        Level level = levelArrayList.get(holder.getAdapterPosition());

        levelNumber = String.valueOf(level.getLevelId());
        getLevelId = level.getLevelId();
        holder.levelNumber.setText(levelNumber);
        holder.pointsNeeded.setText(String.valueOf(level.getPoints()));
        //todo rating
        holder.rate.setText(String.valueOf(((level.getPoints()/level.getLevelId()))*10)+"%");
        Animation animation = AnimationUtils.loadAnimation(holder.itemView.getContext(), R.anim.recycle_view);

        if (level.getPoints() > 0){
            holder.imgLock.setImageResource(R.drawable.ic_lock);
            holder.linearLayout.setBackgroundColor(Color.parseColor("#D0E3EC"));
        }
        holder.itemView.startAnimation(animation);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                levelNumber = String.valueOf(level.getLevelId());
                Log.d(Adapter.class.getSimpleName()+"question",levelNumber+"");
                //   if (level.getPoints() == 0) {
                    onClickListener.onClick(level.getLevelId());
           //    }
            }
        });
    }

    @Override
    public int getItemCount() {
        return levelArrayList != null ? levelArrayList.size() : 0;
    }

    class App_ViewHolder extends RecyclerView.ViewHolder {
        TextView levelNumber, pointsNeeded , rate;
        ImageView imgLock;
        LinearLayout linearLayout;

        public App_ViewHolder(@NonNull ItemRvStartPlayBinding binding) {
            super(binding.getRoot());
            levelNumber = binding.startPlayTvNumLevel;
            pointsNeeded = binding.startPlayTvNumPointsNeeded;
            imgLock = binding.startPlayImgLock;
            rate = binding.startPlayTvNumRate;
            linearLayout = binding.linearLayout;
        }
    }
}
