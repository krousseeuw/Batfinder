package com.kru.batfinder2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.R;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.Sponsor;

import java.util.List;

public class SponsorListAdapter extends BaseListAdapter{
    private final List<Sponsor> mSponsors;
    private IOnItemClickListener mOnItemListener;

    public SponsorListAdapter(List<Sponsor> sponsors, IOnItemClickListener onItemListener){
        mSponsors = sponsors;
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
        Uri theUri = Uri.parse(Uri.decode(mSponsors.get(position).getLogoUrl()));
        Glide.with(holder.mImageView.getContext())
                .load(theUri)
                .into(holder.mImageView);
        holder.titleView.setText(mSponsors.get(position).getName());
        holder.subtitleView.setText(mSponsors.get(position).getExtraInfo());
    }

    @Override
    public int getItemCount() {
        return mSponsors.size();
    }
}
