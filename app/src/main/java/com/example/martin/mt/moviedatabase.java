package com.example.martin.mt;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;
@Database( entities = {classmoviestore.class},version = 1 )
public abstract  class moviedatabase extends RoomDatabase {
abstract movieDao getmovieDao();
}
