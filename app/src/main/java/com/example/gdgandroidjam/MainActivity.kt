package com.example.gdgandroidjam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val retrofit = Retrofit.Builder()
                .baseUrl("http://api.weatherstack.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        val service = retrofit.create(WeatherStackService::class.java)
        service.getCurrentWeather(
                "7340a46c103c11fd9d09e05ab88f5275",
                "Tashkent"
        ).enqueue(object : Callback<CurrentWeatherResponse>{
            override fun onResponse(call: Call<CurrentWeatherResponse>, response: Response<CurrentWeatherResponse>) {
                val data = response.body()
                if(response.isSuccessful && data != null){
                    temperature_text.text = data.current.temperature.toString()
                    city_name.text = data.location.region
                    time_text.text = data.current.observation_time
                }
            }

            override fun onFailure(call: Call<CurrentWeatherResponse>, t: Throwable) {
                val alertDialog = AlertDialog.Builder(this@MainActivity)
                alertDialog.create()
                alertDialog.setMessage("No connection")
                alertDialog.show()
            }
        } )
    }
}