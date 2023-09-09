package com.example.valorant.ui.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.valorant.data.Agents
import com.example.valorant.data.DataItem
import com.example.valorant.data.Weapon
import com.example.valorant.data.WeaponItems
import com.example.valorant.network.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    private val _agentsData : MutableStateFlow<List<DataItem>> = MutableStateFlow(listOf())
    val agentsData : StateFlow<List<DataItem>> = _agentsData
    var selectedAgents by mutableStateOf<DataItem?>(value = null)
        private set

    private val _weaponsData : MutableStateFlow<List<WeaponItems>> = MutableStateFlow(listOf())
    val weaponsData : StateFlow<List<WeaponItems>> = _weaponsData

    var selectedWeapon by mutableStateOf<WeaponItems?>(value = null)
        private set
    var tabIndex by mutableStateOf(0)
        private set

    var errorString : String = " "
    var loading: Boolean by mutableStateOf(false)

    fun tabIndex(index: Int){
        viewModelScope.launch {
            tabIndex = index
        }
    }
    init{
        retrieveAgentsData()
        retrieveWeaponsData()
    }
    fun selectedAgents(agents: DataItem){
        viewModelScope.launch {
            selectedAgents = agents
        }

    }
    fun selectedWeapon(weapon: WeaponItems){
        viewModelScope.launch {
            selectedWeapon = weapon
        }
    }


    private fun retrieveAgentsData(){
        viewModelScope.launch {
            loading = true
            val call : Call<Agents> = RetrofitInstance.apiService.getAllAgents()
            call.enqueue(object : Callback<Agents> {
                override fun onResponse(
                    call: Call<Agents>,
                    response: Response<Agents>
                ) {
                    if(response.isSuccessful){
                        val responseData: List<DataItem>? = response.body()?.data
                        if(responseData != null){
                            _agentsData.value = responseData.filter { dataItem ->  dataItem.role?.displayName != null }
                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<Agents>, t: Throwable) {
                    loading = false
                    Log.d("Failed Retrieve", "Network Error")
                }

            })
        }
    }

    private fun retrieveWeaponsData(){
        viewModelScope.launch {
            loading = true
            val call : Call<Weapon> = RetrofitInstance.apiService.getAllWeapon()
            call.enqueue(object : Callback<Weapon> {
                override fun onResponse(
                    call: Call<Weapon>,
                    response: Response<Weapon>
                ) {
                    Log.e(response.toString(), "Network Success")
                    if(response.isSuccessful){
                        val responseData: List<WeaponItems>? = response.body()?.data
                        if(responseData != null){
                            _weaponsData.value = responseData
                        }
                        loading = false
                    } else{
                        loading = false
                    }
                }

                override fun onFailure(call: Call<Weapon>, t: Throwable) {
                    errorString = t.toString()
                    Log.e(t.toString(), "Network Error")
                    loading = false
                }

            })
        }
    }

}