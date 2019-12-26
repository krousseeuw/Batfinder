package com.kru.batfinder2.data;

import com.kru.batfinder2.models.BatDTO;
import com.kru.batfinder2.models.ObservationDTO;
import com.kru.batfinder2.models.SponsorDTO;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager dataManagerInstance = null;
    private List<BatDTO> mBatDTOS = new ArrayList<>();
    private List<ObservationDTO> mObservationDTOS = new ArrayList<>();

    public static DataManager getInstance() {
        if(dataManagerInstance == null) {
            dataManagerInstance = new DataManager();
        }

        return dataManagerInstance;
    }

    public List<BatDTO> getAllBats() {
        return mBatDTOS;
    }

    public void updateBatList(List<BatDTO> batDTOS){
        mBatDTOS = batDTOS;
    }

    public void updateObservationList(List<ObservationDTO> list) {
        mObservationDTOS = list;
    }

    public List<ObservationDTO> getAllObservations() {
        return mObservationDTOS;
    }
}
