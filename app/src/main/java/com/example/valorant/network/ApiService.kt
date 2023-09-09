package com.example.valorant.network

import com.example.valorant.data.Agents
import com.example.valorant.data.DataItem
import com.example.valorant.data.Weapon
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("agents")
    fun getAllAgents() : Call<Agents>

    @GET("weapons")
    fun getAllWeapon() : Call<Weapon>
}