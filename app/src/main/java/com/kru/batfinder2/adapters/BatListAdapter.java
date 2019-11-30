package com.kru.batfinder2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.R;
import com.kru.batfinder2.database.Bat;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.BatDTO;

import java.util.List;
import java.util.Locale;

public class BatListAdapter extends BaseListAdapter {
    private final List<Bat> mBatDTOS;
    private IOnItemClickListener mOnItemListener;

    public BatListAdapter(List<Bat> batDTOS, IOnItemClickListener onItemListener){
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
        Uri theUri = Uri.parse(Uri.decode(mBatDTOS.get(position).getImageUrl()));
        Glide.with(holder.mImageView.getContext())
                .load(theUri)
                .into(holder.mImageView);
        if(Locale.getDefault().getLanguage().equals("nl")){
            holder.titleView.setText(mBatDTOS.get(position).getCommonNameNl());
        } else {
            holder.titleView.setText(mBatDTOS.get(position).getCommonNameEn());
        }

        holder.subtitleView.setText(mBatDTOS.get(position).getScientificName());
    }

    @Override
    public int getItemCount() {
        return mBatDTOS.size();
    }
}
