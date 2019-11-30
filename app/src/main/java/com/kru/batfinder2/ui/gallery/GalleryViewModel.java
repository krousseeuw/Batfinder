package com.kru.batfinder2.ui.gallery;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.kru.batfinder2.database.BatFinderRepository;
import com.kru.batfinder2.database.Sponsor;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {

    private final BatFinderRepository mRepository;
    private LiveData<List<Sponsor>> mSponsors;

    public GalleryViewModel(Application application) {
        super(application);
        mRepository = new BatFinderRepository(application);
        mSponsors = mRepository.getAllSponsors();
    }

    public LiveData<List<Sponsor>> getSponsors() {
        return mSponsors;
    }
}