package com.example.jodhymobiledevmorning

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET ("character")
    fun getJodhy():Call<ResponseJodhy>
}