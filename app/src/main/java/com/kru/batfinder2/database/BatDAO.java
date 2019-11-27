package com.kru.batfinder2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;

import java.util.List;

@Dao
public interface BatDAO {
    // Get all
    LiveData<List<Bat>> getAllBats();

    // Update from database
    void insert(Bat... bats);
}
