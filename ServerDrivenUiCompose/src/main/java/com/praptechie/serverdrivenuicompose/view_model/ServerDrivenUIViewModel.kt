package com.praptechie.serverdrivenuicompose.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIHeaderJsonData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.json.JSONObject

val ServerDrivenUILogTag = "ServerDrivenUi"
class ServerDrivenUIViewModel : ViewModel() {

    private val _serverDrivenUIData = MutableStateFlow(ServerDrivenUIHeaderJsonData())
    val serverDrivenUIData: StateFlow<ServerDrivenUIHeaderJsonData> get() = _serverDrivenUIData

    fun serializeData(json:String){
        viewModelScope.launch {
            try{
                _serverDrivenUIData.value =Gson().fromJson(json,ServerDrivenUIHeaderJsonData::class.java)
            }catch (e:Exception){
                Log.e(ServerDrivenUILogTag,"${e.message}")
            }

        }
    }


}