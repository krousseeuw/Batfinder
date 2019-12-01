package com.kru.batfinder2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BatDAO {
    // Get all
    @Query("SELECT * from bat_table")
    LiveData<List<Bat>> getAllBats();

    // Update from database
    @Insert(onConflict = OnConflictStrategy.ABORT)
    void insert(Bat... bats);

    @Query("SELECT count(*) from bat_table")
    int getBatCount();

    @Query("DELETE from bat_table")
    void deleteAll();
}
