package com.zaen.moviecatalogue.di

import android.content.Context

import com.zaen.moviecatalogue.source.MovieCatalogueRepository
import com.zaen.moviecatalogue.source.remote.RemoteDataSource
import com.zaen.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {

        val remoteRepository = RemoteDataSource.getInstance(JsonHelper(context))

        return MovieCatalogueRepository.getInstance(remoteRepository)
    }
}
