package com.zaen.moviecatalogue.utils

import android.content.Context
import com.google.gson.Gson
import com.zaen.moviecatalogue.source.remote.response.Movie
import com.zaen.moviecatalogue.source.remote.response.MoviesResponse
import org.json.JSONException
import java.io.IOException

class JsonHelper(private val context: Context) {

    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): MoviesResponse {
        var list = MoviesResponse()
        try {
            val responseObject = parsingFileToString("movies.json").toString()
            list = Gson().fromJson(responseObject, MoviesResponse::class.java)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

    fun loadTvShows(): MoviesResponse {
        var list = MoviesResponse()
        try {
            val responseObject = parsingFileToString("tvshows.json").toString()
            list = Gson().fromJson(responseObject, MoviesResponse::class.java)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return list
    }

}