package com.example.filmsandserials.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.filmsandserials.data.Film

@Database(entities = [Film::class], version = 1, exportSchema = false)
@TypeConverters(IdsConverter::class)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getContentDao(): ContentDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "ContentBD"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}