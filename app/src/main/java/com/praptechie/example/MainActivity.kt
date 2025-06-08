package com.praptechie.example

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.praptechie.example.ui.theme.ServerDrivenUIJetpackComposeTheme
import com.praptechie.example.view_model.ExampleServerDrivenUIViewModel
import com.praptechie.serverdrivenuicompose.ServerDrivenUiHandler

class MainActivity : ComponentActivity() {
    private val serverDrivenUiHandler = ServerDrivenUiHandler()
    private val serverDrivenUIViewModel = ExampleServerDrivenUIViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ServerDrivenUIJetpackComposeTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Box(modifier = Modifier.padding(innerPadding)){
                      val uiJsonString by   serverDrivenUIViewModel.serverDrivenUIDataJson.collectAsState()
                        if(!uiJsonString.isNullOrEmpty())
                        serverDrivenUiHandler.ServerDrivenContainer(uiJsonString!!)
                    }

                }
            }
        }
    }
}

