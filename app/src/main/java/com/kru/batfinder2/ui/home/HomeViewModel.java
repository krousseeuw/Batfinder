package com.kru.batfinder2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kru.batfinder2.models.Bat;
import com.kru.batfinder2.data.DataManager;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> selectedBatId = new MutableLiveData<Integer>();
    private final MutableLiveData<List<Bat>> mBats = new MutableLiveData<List<Bat>>();
    private final DataManager mRepository;

    private MutableLiveData<String> mText;

    public HomeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
        mRepository = DataManager.getInstance();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public void selectBat(int batId){
        selectedBatId.setValue(batId);
    }

    public MutableLiveData<Integer> getSelectedBatId(){
        return selectedBatId;
    }

    public void refreshBatList(){
        mBats.setValue(mRepository.getAllBats());
    }

    public MutableLiveData<List<Bat>> getBatsList(){
        return mBats;
    }

    public Bat getSelectedBat(){
        return getBatDetails(selectedBatId.getValue());
    }

    public Bat getBatDetails(int batId){
        return mRepository.returnBatById(batId);
    }
}