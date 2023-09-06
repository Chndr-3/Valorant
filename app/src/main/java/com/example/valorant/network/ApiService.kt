package com.example.valorant.network

import com.example.valorant.data.Agents
import com.example.valorant.data.DataItem
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    fun getAllAgents() : Call<Agents>
}