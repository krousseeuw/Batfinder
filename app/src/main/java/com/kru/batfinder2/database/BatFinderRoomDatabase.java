package com.kru.batfinder2.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Bat.class, Sponsor.class, Observation.class}, version = 1, exportSchema = false)
public abstract class BatFinderRoomDatabase extends RoomDatabase {
    public abstract BatDAO mBatDAO();

    public abstract ObservationDAO mObservationDAO();

    public abstract SponsorDAO mSponsorDAO();

    private static volatile BatFinderRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor
            = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static BatFinderRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (BatFinderRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            BatFinderRoomDatabase.class, "batfinder_database")
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }

        return INSTANCE;
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            databaseWriteExecutor.execute(() -> {
                SponsorDAO dao = INSTANCE.mSponsorDAO();

                dao.insert(new Sponsor("https://www.bats.org.uk/", "BatDTO Conservation Trust", "UK's Largest BatDTO Organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/apple-touch-icon.png", "EN"));
                dao.insert(new Sponsor("http://www.batcon.org/", "BatDTO Conservation International", "International BatDTO organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Bat_Conservation_International_logo.png", "EN"));
                dao.insert(new Sponsor("https://www.merlintuttle.org/", "Merlin Tuttle's BatDTO Conservation", "US-Based organisation", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Merlin+Tuttle_s+Bat+Conservation.jpg", "EN"));
                dao.insert(new Sponsor("https://www.natuurpunt.be/afdelingen/vleermuizenwerkgroep", "Vleermuizenwerkgroep", "Werkgroep voor vleermuizen van Natuurpunt", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Merlin+Tuttle_s+Bat+Conservation.jpg", "NL"));
                dao.insert(new Sponsor("https://www.zoogdiervereniging.nl/", "Zoogdierenvereniging", "Nederlandse zoogdieren groep, doet veel werk met vleermuizen", "http://dtsl.ehb.be/~kevin.rousseeuw/BatData/img/Merlin+Tuttle_s+Bat+Conservation.jpg", "NL"));
            });
        }
    };
}
