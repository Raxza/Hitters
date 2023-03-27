package com.raxza.hitters.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Menu::class, Set::class], version = 1, exportSchema = false)
abstract class HittersDatabase : RoomDatabase() {
    abstract fun hittersDao(): HittersDao

    companion object {
        @Volatile
        private var instance: HittersDatabase? = null
        fun getInstance(context: Context): HittersDatabase =
            instance ?: synchronized(this) {
                instance ?: Room.databaseBuilder(
                    context.applicationContext,
                    HittersDatabase::class.java, "Hitters.db"
                )   .fallbackToDestructiveMigration()
                    .build()
                    .also { instance = it }
            }
    }
}