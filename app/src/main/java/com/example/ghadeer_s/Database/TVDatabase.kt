package com.example.ghadeer_s.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//create database...
@Database(entities = [TV::class], version = 1, exportSchema = false)
abstract class TVDatabase : RoomDatabase() {
    companion object {
        var instance: TVDatabase? = null;
        fun getInstance(ctx: Context): TVDatabase {
            if (instance != null) {
                return instance as TVDatabase;
            }
            instance = Room.databaseBuilder(ctx, TVDatabase::class.java, "MangaDB.db")
                .run { allowMainThreadQueries() }.build();
            return instance as TVDatabase;
        }
    }

    abstract fun TVDao(): TVDao;
}