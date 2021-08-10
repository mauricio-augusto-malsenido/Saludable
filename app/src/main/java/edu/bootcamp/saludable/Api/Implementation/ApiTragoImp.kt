package edu.bootcamp.saludable.Api.Implementation

import edu.bootcamp.saludable.Api.ApiTrago
import edu.bootcamp.saludable.Model.Trago
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiTragoImp {
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://www.thecocktaildb.com/")
            .build()
    }

    fun getTrago(): Call<Trago>
    {
        return getRetrofit().create(ApiTrago::class.java).getTrago()
    }
}