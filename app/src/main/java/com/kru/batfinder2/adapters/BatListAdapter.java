package com.kru.batfinder2.adapters;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.kru.batfinder2.R;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.Bat;

import java.util.List;

public class BatListAdapter extends BaseListAdapter {
    private final List<Bat> mBats;
    private IOnItemClickListener mOnItemListener;

    public BatListAdapter(List<Bat> bats, IOnItemClickListener onItemListener){
        mBats = bats;
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
        Uri theUri = Uri.parse(Uri.decode(mBats.get(position).getImage_url()));
        Glide.with(holder.mImageView.getContext())
                .load(theUri)
                .into(holder.mImageView);
        holder.titleView.setText(mBats.get(position).getCommon_name_en());
        holder.subtitleView.setText(mBats.get(position).getScientific_name());
    }

    @Override
    public int getItemCount() {
        return mBats.size();
    }
}
