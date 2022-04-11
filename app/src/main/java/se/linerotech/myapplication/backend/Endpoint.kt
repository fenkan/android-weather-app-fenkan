package se.linerotech.myapplication.backend

import retrofit2.http.GET
import retrofit2.http.Query

interface Endpoint {

    @GET("data/2.5/weather?lat={lat}&lon={lon}&appid={API key}")
    fun getWeatherData(@Query("lat")latitude: Double, @Query("lon")longitude: Double, @Query("appid")appid: String)
}