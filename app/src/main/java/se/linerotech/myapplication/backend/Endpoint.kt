package se.linerotech.myapplication.backend

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import se.linerotech.myapplication.model.WeatherData

interface Endpoint {

    @GET("data/2.5/weather")
    fun getWeatherData(@Query("lat")latitude: Double,
                       @Query("lon")longitude: Double,
                       @Query("appid")appid: String, @Query("units")units: String)

    :
            Call<WeatherData>
}