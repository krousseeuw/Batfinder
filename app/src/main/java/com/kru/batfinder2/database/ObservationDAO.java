package com.kru.batfinder2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ObservationDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Observation observation);

    @Query("SELECT * from observation_table WHERE batId = :batId")
    LiveData<List<Observation>> getObservationsByBatId(int batId);

    //Add batch
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Observation... observations);

    @Query("DELETE from observation_table")
    void deleteAll();
}
