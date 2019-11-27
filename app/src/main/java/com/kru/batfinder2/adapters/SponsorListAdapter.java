package com.kru.batfinder2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.R;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.SponsorDTO;

import java.util.List;

public class SponsorListAdapter extends BaseListAdapter{
    private final List<SponsorDTO> mSponsorDTOS;
    private IOnItemClickListener mOnItemListener;

    public SponsorListAdapter(List<SponsorDTO> sponsorDTOS, IOnItemClickListener onItemListener){
        mSponsorDTOS = sponsorDTOS;
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
        Uri theUri = Uri.parse(Uri.decode(mSponsorDTOS.get(position).getLogoUrl()));
        Glide.with(holder.mImageView.getContext())
                .load(theUri)
                .into(holder.mImageView);
        holder.titleView.setText(mSponsorDTOS.get(position).getName());
        holder.subtitleView.setText(mSponsorDTOS.get(position).getExtraInfo());
    }

    @Override
    public int getItemCount() {
        return mSponsorDTOS.size();
    }
}
