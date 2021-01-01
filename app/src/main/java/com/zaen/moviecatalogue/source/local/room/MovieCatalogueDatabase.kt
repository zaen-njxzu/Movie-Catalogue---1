package com.zaen.moviecatalogue.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.zaen.moviecatalogue.source.local.entity.MovieEntity
import com.zaen.moviecatalogue.source.local.entity.TvShowEntity

@Database(entities = [MovieEntity::class, TvShowEntity::class], version = 1, exportSchema = false)
abstract class MovieCatalogueDatabase : RoomDatabase() {

    abstract fun getMovieCatalogueDao(): MovieCatalogueDao

    companion object {

        @Volatile
        private var INSTANCE: MovieCatalogueDatabase? = null

        fun getInstance(context: Context): MovieCatalogueDatabase {
            if (INSTANCE == null) {
                synchronized(MovieCatalogueDatabase::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.applicationContext,
                            MovieCatalogueDatabase::class.java, "MovieCatalogues.db")
                            .build()
                    }
                }
            }
            return INSTANCE as MovieCatalogueDatabase
        }
    }
}