package com.praptechie.serverdrivenuijetpackcompose.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import com.praptechie.serverdrivenuijetpackcompose.data_models.ServerDrivenUIHeaderJsonData
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.launch

class ServerDrivenUIViewModel : ViewModel() {

    private val _serverDrivenUIData = MutableStateFlow(ServerDrivenUIHeaderJsonData())
    val serverDrivenUIData: StateFlow<ServerDrivenUIHeaderJsonData> get() = _serverDrivenUIData
    private val database = Firebase.database
    private val myRef = database.getReference()

    private fun getServerDrivenUIData(): Flow<ServerDrivenUIHeaderJsonData?> = callbackFlow {

        val listener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val serverDrivenData = snapshot.getValue(ServerDrivenUIHeaderJsonData::class.java)
                trySend(serverDrivenData).isSuccess
            }
            override fun onCancelled(error: DatabaseError) {}

        }
        myRef.addValueEventListener(listener)
        awaitClose {
            myRef.removeEventListener(listener)
        }
    }


    init {
        viewModelScope.launch {
            getServerDrivenUIData().collect { data ->
                data?.let {
                    _serverDrivenUIData.value = it
                }

            }
        }
    }

}