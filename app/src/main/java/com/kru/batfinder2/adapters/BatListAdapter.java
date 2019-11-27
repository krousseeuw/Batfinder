package com.kru.batfinder2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.R;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.BatDTO;

import java.util.List;

public class BatListAdapter extends BaseListAdapter {
    private final List<BatDTO> mBatDTOS;
    private IOnItemClickListener mOnItemListener;

    public BatListAdapter(List<BatDTO> batDTOS, IOnItemClickListener onItemListener){
        mBatDTOS = batDTOS;
        mOnItemListener = onItemListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bat_list_item, parent, false);
        return new ViewHolder(view, mOnItemListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Uri theUri = Uri.parse(Uri.decode(mBatDTOS.get(position).getImage_url()));
        Glide.with(holder.mImageView.getContext())
                .load(theUri)
                .into(holder.mImageView);
        holder.titleView.setText(mBatDTOS.get(position).getCommon_name_en());
        holder.subtitleView.setText(mBatDTOS.get(position).getScientific_name());
    }

    @Override
    public int getItemCount() {
        return mBatDTOS.size();
    }
}
