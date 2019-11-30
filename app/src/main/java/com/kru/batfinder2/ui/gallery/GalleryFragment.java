package com.kru.batfinder2.ui.gallery;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kru.batfinder2.R;
import com.kru.batfinder2.adapters.SponsorListAdapter;
import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.database.Sponsor;
import com.kru.batfinder2.interfaces.IOnItemClickListener;

import java.util.List;

public class GalleryFragment extends Fragment implements IOnItemClickListener {

    private GalleryViewModel galleryViewModel;
    private RecyclerView mRecylerView;
    private SponsorListAdapter mAdapter;
    private IOnSponsorLinkSelectedListener mListener;
    private List<Sponsor> mSponsors;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel = new
                ViewModelProvider(this).get(GalleryViewModel.class);
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
        galleryViewModel.getSponsors().observe(getViewLifecycleOwner(), new Observer<List<Sponsor>>() {
            @Override
            public void onChanged(List<Sponsor> sponsors) {
                loadRecylerView(sponsors);
                mSponsors = sponsors;
            }
        });


        mRecylerView = root.findViewById(R.id.sponsors_recycler_view);

        mRecylerView.setHasFixedSize(true);
        mRecylerView.setLayoutManager(new LinearLayoutManager(getContext()));

    }

    private void loadRecylerView(List<Sponsor> sponsors) {
        mAdapter = new SponsorListAdapter(sponsors, this);
        mRecylerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        String link = mSponsors.get(position).getLink();
        mListener.onSponsorSelected(link);
    }

    public interface IOnSponsorLinkSelectedListener{
        public void onSponsorSelected(String link);
    }
}