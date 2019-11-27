package com.kru.batfinder2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.data.DataManager;

import java.util.List;

public class HomeViewModel extends ViewModel {

    private final MutableLiveData<Integer> selectedBatId = new MutableLiveData<Integer>();
    private final MutableLiveData<List<BatDTO>> mBats = new MutableLiveData<List<BatDTO>>();
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

    public MutableLiveData<List<BatDTO>> getBatsList(){
        return mBats;
    }

    public BatDTO getSelectedBat(){
        return getBatDetails(selectedBatId.getValue());
    }

    public BatDTO getBatDetails(int batId){
        return mRepository.returnBatById(batId);
    }
}