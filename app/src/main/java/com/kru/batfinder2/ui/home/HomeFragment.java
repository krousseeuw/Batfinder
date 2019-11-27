package com.kru.batfinder2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.adapters.BatListAdapter;
import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.R;

import java.util.List;

public class HomeFragment extends Fragment implements IOnItemClickListener {

    private HomeViewModel homeViewModel;
    private RecyclerView recyclerView;
    private BatListAdapter mAdapter;
    private View mRoot;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this.getActivity()).get(HomeViewModel.class);
        mRoot = inflater.inflate(R.layout.fragment_home, container, false);

        recyclerView = mRoot.findViewById(R.id.bats_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        initializeDisplayContent();

        return mRoot;
    }

    private void initializeDisplayContent(){
        homeViewModel.getBatsList().observe(this, new Observer<List<BatDTO>>() {
            @Override
            public void onChanged(List<BatDTO> batDTOS) {
                loadRecyclerView(batDTOS);
            }
        });
    }

    private void loadRecyclerView(List<BatDTO> batDTOS){
        mAdapter = new BatListAdapter(batDTOS, this);
        recyclerView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(int position) {
        int batId = DataManager.getInstance().getAllBats().get(position).getId();
        homeViewModel.selectBat(batId);
        Navigation.findNavController(mRoot).navigate(R.id.action_nav_home_to_batdetail);
    }

    public void updateRecylerView() {
        mAdapter.notifyDataSetChanged();
    }
}