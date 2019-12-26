package com.kru.batfinder2.ui.batdetail;

import androidx.core.app.ActivityCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.kru.batfinder2.ObservationMapActivity;
import com.kru.batfinder2.database.Bat;
import com.kru.batfinder2.database.Observation;
import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.R;
import com.kru.batfinder2.ui.home.HomeViewModel;

import java.util.Locale;
import java.util.concurrent.Executor;

public class BatDetailFragment extends Fragment {
    private HomeViewModel mViewModel;
    private ImageView mImageView;
    private TextView mCommonNameView;
    private TextView mScientificNameView;
    private TextView mBodyLengthView;
    private TextView mDescription;
    private TextView mPhotoCredit;
    private int mBatId;
    private FusedLocationProviderClient mFusedLocationClient;

    public static BatDetailFragment newInstance() {
        return new BatDetailFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.bat_detail_fragment, container, false);
        setHasOptionsMenu(true);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this.getActivity());

        mImageView = root.findViewById(R.id.image_bat_big);
        mCommonNameView = root.findViewById(R.id.text_bat_common_name);
        mScientificNameView = root.findViewById(R.id.text_scientific_name);
        mBodyLengthView = root.findViewById(R.id.text_bat_bodylength);
        mDescription = root.findViewById(R.id.text_description);
        mPhotoCredit = root.findViewById(R.id.text_photocredit);

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this.getActivity()).get(HomeViewModel.class);
        // TODO: Use the ViewModel
        mViewModel.getSelectedBat().observe(getViewLifecycleOwner(), this::displayBat);
    }

    private void displayBat(Bat bat) {
        mBatId = bat.getId();

        Glide.with(this)
                .load(bat.getImageUrl())
                .into(mImageView);

        if(Locale.getDefault().getLanguage().equals(getString(R.string.dutchLanguageCode))) {
            mCommonNameView.setText(bat.getCommonNameNl());
            mDescription.setText(bat.getDescriptionNl());
        } else {
            mCommonNameView.setText(bat.getCommonNameEn());
            mDescription.setText(bat.getDescriptionEn());
        }

        mDescription.setMovementMethod(new ScrollingMovementMethod());
        mScientificNameView.setText(bat.getScientificName());
        mBodyLengthView.setText(getString(R.string.bodyLengthString, Integer.toString(bat.getMinBodyLength()), Integer.toString(bat.getMaxBodyLength())));
        mPhotoCredit.setText(getString(R.string.photoCreditString, bat.getImageAuthorName()));
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.bat_detail_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_view_observations:
                navigateToMapView();
                return true;
            case R.id.action_record_observation:
                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                        && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    askLocationPermission();
                    return false;
                }
                else {
                    getCurrentLocation();
                    return true;
                }
        }

        return super.onOptionsItemSelected(item);
    }

    private void askLocationPermission() {
        ActivityCompat.requestPermissions(this.getActivity(), new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                1000);

    }

    private void getCurrentLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this.getActivity(), new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                            Observation newObservation = new Observation(mBatId, location.getLongitude(), location.getLatitude());
                            mViewModel.insertObservation(newObservation);
                            navigateToMapView();
                        }
                    }
                });
    }

    private void navigateToMapView() {
        Bundle bundle = new Bundle();
        bundle.putInt(ObservationMapActivity.BAT_ID, mBatId);
        Intent myIntent = new Intent(this.getContext(), ObservationMapActivity.class);
        myIntent.putExtras(bundle);
        startActivity(myIntent);
    }
}
