package com.kru.batfinder2.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BatFinderRepository {
    private LiveData<List<Sponsor>> mAllSponsors;
    private BatDAO mBatDAO;
    private ObservationDAO mObservationDAO;
    private SponsorDAO mSponsorDAO;
    private LiveData<List<Bat>> mAllBats;

    public BatFinderRepository(Application application) {
        BatFinderRoomDatabase db = BatFinderRoomDatabase.getDatabase(application);
        mBatDAO = db.mBatDAO();
        mObservationDAO = db.mObservationDAO();
        mSponsorDAO = db.mSponsorDAO();
        mAllBats = mBatDAO.getAllBats();
        mAllSponsors = mSponsorDAO.getAllSponsors();
    }

    public LiveData<List<Bat>> getAllBats() {
        return mAllBats;
    }

    public void insertBats(List<Bat> bats){
        BatFinderRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBatDAO.insert(bats.toArray(new Bat[0]));
        });
    }

    public LiveData<List<Sponsor>> getAllSponsors() { return mAllSponsors;}

    //public boolean batTableInitialized(){ return mBatDAO.getBatCount() > 0;}
}
