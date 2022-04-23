package se.linerotech.myapplication.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import se.linerotech.myapplication.databinding.ActivityWeatherDataBinding
import se.linerotech.myapplication.model.WeatherData

class WeatherDataActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeatherDataBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherDataBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //Receiving the weather data
        val weatherData = intent.getParcelableExtra<WeatherData>("getWeatherData")!!
        weatherData.weather
        binding.texViewWeather.text = weatherData.main!!.temp.toString() +"C"
    }
}