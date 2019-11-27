package com.kru.batfinder2.database;

import androidx.room.Database;

@Database(entities = {Bat.class, Sponsor.class, Observation.class}, version = 1, exportSchema = false)
public class BatFinderRoomDatabase {

}
