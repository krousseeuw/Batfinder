package com.kru.batfinder2;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class BatListAdapter  extends RecyclerView.Adapter<BatListAdapter.ViewHolder> {
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
        holder.mBat = mBats.get(position);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final ImageView mImageView;
        public final TextView titleView;
        public final TextView subtitleView;
        public Bat mBat;
        IOnItemClickListener onBatClickListener;

        public ViewHolder(View view, IOnItemClickListener onBatClickListener) {
            super(view);
            mView = view;
            mImageView = view.findViewById(R.id.icon_view);
            titleView = view.findViewById(R.id.title_view);
            subtitleView = view.findViewById(R.id.subtitle_view);

            this.onBatClickListener = onBatClickListener;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            onBatClickListener.onItemClick(getAdapterPosition());
        }
    }
}
