package edu.bootcamp.saludable.Api

import edu.bootcamp.saludable.Model.Trago
import retrofit2.Call
import retrofit2.http.GET

interface ApiTrago {

    @GET("api/json/v1/1/random.php")
    fun getTrago(): Call<Trago>

}