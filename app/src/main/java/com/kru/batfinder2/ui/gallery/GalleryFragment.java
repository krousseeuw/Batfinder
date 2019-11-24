package com.kru.batfinder2.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kru.batfinder2.R;
import com.kru.batfinder2.adapters.SponsorListAdapter;
import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.interfaces.IOnItemClickListener;

public class GalleryFragment extends Fragment implements IOnItemClickListener {

    private GalleryViewModel galleryViewModel;
    private RecyclerView mRecylerView;
    private SponsorListAdapter mAdapter;
    private IOnSponsorLinkSelectedListener mListener;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        initializeRecyclerView(root);

        return root;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try{
            mListener = (IOnSponsorLinkSelectedListener) context;
        } catch (ClassCastException e){
            throw new ClassCastException(context.toString() + " must implement OnBatSelectedListener");
        }
    }

    private void initializeRecyclerView(View root) {
        mRecylerView = root.findViewById(R.id.sponsors_recycler_view);

        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

        mAdapter = new SponsorListAdapter(DataManager.getInstance().getSponsors(), this);
        mRecylerView.setAdapter(mAdapter);
    }

    @Override
    public void onItemClick(int position) {
        String link = DataManager.getInstance().getSponsors().get(position).getLink();
        mListener.onSponsorSelected(link);
    }

    public interface IOnSponsorLinkSelectedListener{
        public void onSponsorSelected(String link);
    }
}