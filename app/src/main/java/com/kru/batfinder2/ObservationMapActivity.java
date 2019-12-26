package com.kru.batfinder2;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kru.batfinder2.database.Observation;

import java.util.List;

public class ObservationMapActivity extends FragmentActivity implements OnMapReadyCallback {

    public static final String BAT_ID = "com.kru.batfinder2.BAT_ID";
    public static final int ID_NOT_SET = -1;
    private GoogleMap mMap;
    private ObservationMapViewModel mViewModel;
    private int mBatId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(), ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        mViewModel = viewModelProvider.get(ObservationMapViewModel.class);

        Intent intent = getIntent();
        mBatId = intent.getIntExtra(BAT_ID, ID_NOT_SET);

        if (mBatId == ID_NOT_SET){
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(getBaseContext(), "No bat id set", duration);
            toast.show();
        }

        mViewModel.loadObservationsForBat(mBatId);

        setContentView(R.layout.activity_observation_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        loadMapObservationPins();
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private void loadMapObservationPins() {
        mViewModel.getObservations().observe(this, new Observer<List<Observation>>() {
            @Override
            public void onChanged(List<Observation> observations) {
                if (!observations.isEmpty()) {
                    mMap.clear();
                    LatLngBounds.Builder builder = new LatLngBounds.Builder();
                    for (Observation observation : observations) {
                        Marker marker = mMap.addMarker(new MarkerOptions().position(new LatLng(observation.getLatitude(), observation.getLongitude())));
                        builder.include(marker.getPosition());
                    }

                    LatLngBounds bounds = builder.build();
                    CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 20);

                    mMap.animateCamera(cu);
                }
            }
        });
    }
}
