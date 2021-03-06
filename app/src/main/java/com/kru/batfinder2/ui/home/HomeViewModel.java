package com.kru.batfinder2.ui.home;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kru.batfinder2.database.Bat;
import com.kru.batfinder2.database.BatFinderRepository;
import com.kru.batfinder2.database.Observation;
import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.data.DataManager;
import com.kru.batfinder2.models.ObservationDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<Bat> mSelectedBat = new MutableLiveData<Bat>();
    private final LiveData<List<Bat>> mBats;
    private final DataManager mDataManager;
    private BatFinderRepository mRepository;

    private MutableLiveData<String> mText;

    public HomeViewModel(Application application) {
        super(application);
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mDataManager = DataManager.getInstance();
        mRepository = new BatFinderRepository(application);
        mBats = mRepository.getAllBats();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void selectBat(Bat bat){
        mSelectedBat.setValue(bat);
    }

    public void refreshBatList(){
        List<Bat> batsToUpdate = new ArrayList<>();
        List<BatDTO> batdtos = mDataManager.getAllBats();

        for (BatDTO batDto: batdtos){
            batsToUpdate.add(new Bat(batDto));
        }

        mRepository.insertBats(batsToUpdate);
    }

    public void insertObservation(Observation observation){
        mRepository.insertObservation(observation);
    }

    public void refreshObservationList() {
        List<Observation> observationsToUpdate = new ArrayList<>();
        List<ObservationDTO> observationDTOS = mDataManager.getAllObservations();

        for (ObservationDTO observationDTO: observationDTOS){
            observationsToUpdate.add(new Observation(observationDTO));
        }

        mRepository.insertObservations(observationsToUpdate);
    }

    public boolean batPresent() {
        return false; //mRepository.batTableInitialized();
    }

    public LiveData<List<Bat>> getBatsList(){
        return mBats;
    }

    public MutableLiveData<Bat> getSelectedBat(){
        return mSelectedBat;
    }

    public Bat getBatDetails(int batId){
        for (Bat bat : Objects.requireNonNull(mBats.getValue())){
            if(batId == bat.getId()){
                return bat;
            }
        }

        return null;
    }
}