package com.kru.batfinder2.ui.home;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kru.batfinder2.SynchronizationService;
import com.kru.batfinder2.models.Bat;
import com.kru.batfinder2.interfaces.BatFinderApi;
import com.kru.batfinder2.adapters.BatListAdapter;
import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.interfaces.IOnItemClickListener;
import com.kru.batfinder2.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        homeViewModel.getBatsList().observe(this, new Observer<List<Bat>>() {
            @Override
            public void onChanged(List<Bat> bats) {
                loadRecyclerView(bats);
            }
        });
    }

    private void loadRecyclerView(List<Bat> bats){
        mAdapter = new BatListAdapter(bats, this);
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