package com.kru.batfinder2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kru.batfinder2.database.BatFinderRepository;
import com.kru.batfinder2.database.Observation;

import java.util.List;

public class ObservationMapViewModel extends AndroidViewModel {
    private BatFinderRepository mRepository;
    private LiveData<List<Observation>> mObservations;

    public ObservationMapViewModel(@NonNull Application application) {
        super(application);
        mRepository = new BatFinderRepository(application);
    }

    public void loadObservationsForBat(int batid){
        mObservations = mRepository.getAllObservationsByBatId(batid);
    }

    public LiveData<List<Observation>> getObservations() {
        return mObservations;
    }
}
