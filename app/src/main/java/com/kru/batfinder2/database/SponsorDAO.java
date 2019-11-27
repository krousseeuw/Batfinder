package com.kru.batfinder2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SponsorDAO {
    @Query("SELECT * from sponsor_table ORDER BY name ASC")
    List<Sponsor> getAllSponsors();
}
