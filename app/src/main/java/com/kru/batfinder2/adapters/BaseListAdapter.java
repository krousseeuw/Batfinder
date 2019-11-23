package com.kru.batfinder2.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.kru.batfinder2.R;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.models.Bat;

public abstract class BaseListAdapter extends RecyclerView.Adapter<BatListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public final View mView;
        public final ImageView mImageView;
        public final TextView titleView;
        public final TextView subtitleView;
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
