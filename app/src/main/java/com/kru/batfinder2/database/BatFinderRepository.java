package com.kru.batfinder2.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.Locale;

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
        mAllSponsors = mSponsorDAO.getAllSponsorsByLanguage(getCurrentLanguage());
    }

    public LiveData<List<Bat>> getAllBats() {
        return mAllBats;
    }

    public void insertBats(List<Bat> bats){
        BatFinderRoomDatabase.databaseWriteExecutor.execute(() -> {
            mBatDAO.deleteAll();
            mBatDAO.insert(bats.toArray(new Bat[0]));
        });
    }

    public LiveData<List<Sponsor>> getAllSponsors() { return mAllSponsors;}

    private String getCurrentLanguage(){
        String language = "EN";
        if(Locale.getDefault().getLanguage().equals("nl")){
            language = "NL";
        }

        return language;
    }

    public void insertObservations(List<Observation> observationsToUpdate) {
        BatFinderRoomDatabase.databaseWriteExecutor.execute(() -> {
            mObservationDAO.deleteAll();
            mObservationDAO.insert(observationsToUpdate.toArray(new Observation[0]));
        });
    }

    public LiveData<List<Observation>> getAllObservationsByBatId(int batid) {
        return mObservationDAO.getObservationsByBatId(batid);
    }

    public void insertObservation(Observation observation) {
        BatFinderRoomDatabase.databaseWriteExecutor.execute(() -> {
            mObservationDAO.insert(observation);
        });
    }

    //public boolean batTableInitialized(){ return mBatDAO.getBatCount() > 0;}
}
