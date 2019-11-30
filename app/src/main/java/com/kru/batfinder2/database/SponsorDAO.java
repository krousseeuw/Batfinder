package com.kru.batfinder2.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SponsorDAO {
    @Query("SELECT * from sponsor_table ORDER BY name ASC")
    LiveData<List<Sponsor>> getAllSponsors();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Sponsor sponsor);

    @Query("SELECT * from sponsor_table WHERE languageCode = :language ORDER BY name ASC")
    LiveData<List<Sponsor>> getAllSponsorsByLanguage(String language);
}
