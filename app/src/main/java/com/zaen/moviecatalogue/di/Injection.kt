package com.zaen.moviecatalogue.di

import android.content.Context

import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.local.LocalDataSource
import com.zaen.moviecatalogue.source.local.room.MovieCatalogueDatabase
import com.zaen.moviecatalogue.source.remote.RemoteDataSource
import com.zaen.moviecatalogue.utils.AppExecutors
import com.zaen.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val database = MovieCatalogueDatabase.getInstance(context)

        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.getMovieCatalogueDao())
        val appExecutors = AppExecutors()

        return MovieCatalogueRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}
