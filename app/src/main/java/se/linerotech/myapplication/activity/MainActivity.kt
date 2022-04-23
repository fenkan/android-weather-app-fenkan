package se.linerotech.myapplication.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.util.Log
import android.widget.Toast
import retrofit2.Call
import retrofit2.Response
import se.linerotech.myapplication.R
import se.linerotech.myapplication.backend.RetrofitClient
import se.linerotech.myapplication.databinding.ActivityMainBinding
import se.linerotech.myapplication.model.WeatherData

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val validCities = listOf("panama", "gothenburg", "london", "oslo")
    private val cityMap = mapOf(
        "panama" to Pair(8.983333, -79.516670),
        "gothenburg" to Pair(57.708870, 11.974560),
        "oslo" to Pair(59.911491, 10.757933),
        "london" to Pair(51.509865, -0.118092)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.searchButton.setOnClickListener {

            //extract text from edit text
            val cityName = binding.mainEditText.text.toString().lowercase().trim()

            //check if the city name is valid
            if (cityName in validCities) {
                val lat = cityMap.getValue(cityName).first
                val lon = cityMap.getValue(cityName).second




                weatherData(lat, lon)
            } else {
                Toast.makeText(this, ("Please enter a valid city"), Toast.LENGTH_LONG).show()
            }


        }

    }

    private fun weatherData(lat: Double, lon:Double) {
        //get the lat, lon and appid
        val appid = "326f7336e3083d0a74e8cfb32e7cbe6a"
        val units = "metric"





        RetrofitClient
            .instance
            .getWeatherData(lat, lon, appid, units)
            .enqueue(object : retrofit2.Callback<WeatherData> {

                override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                    //Log the error
                    Log.e(TAG, "Error getting weather data: ${t.localizedMessage}")

                    //Show error message to the user
                    Toast.makeText(
                        this@MainActivity,
                        R.string.unable_to_get_weather_data,
                        Toast.LENGTH_LONG
                    ).show()

                }

                override fun onResponse(
                    call: Call<WeatherData>,
                    response: Response<WeatherData>
                ) {


                    if (response.isSuccessful) {
                        //Getting the
                        val weatherInfo = response.body()

                        //Passing data to the next activity
                        weatherInfo.let {
                            //Create an intent
                            val intent = Intent(this@MainActivity, WeatherDataActivity::class.java)

                            //Pass data to the activity
                            intent.putExtra("getWeatherData", it)

                            //Start the new activity
                            startActivity(intent)
                        }


                    } else {
                        val message = when (response.code()) {
                            500 -> R.string.internal_server_error
                            401 -> R.string.unauthorized
                            403 -> R.string.forbidden
                            404 -> R.string.city_not_found
                            else -> R.string.try_another_city
                        }
                        //show the message
                        Toast.makeText(this@MainActivity, message, Toast.LENGTH_LONG).show()
                        Log.e(TAG, getString(message))

                    }
                }
            })


    }

    companion object {
        private val TAG = MainActivity::class.java.simpleName
    }
}














