package com.praptechie.serverdrivenuicompose

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.praptechie.serverdrivenuicompose.view_model.ServerDrivenUIViewModel
import org.json.JSONObject

class ServerDrivenUiHandler {


    @Composable
   fun ServerDrivenContainer(uiJsonString:String){

       MainScreen(uiData=uiJsonString)
   }
    @Composable
    private fun MainScreen(serverDrivenUIViewModel: ServerDrivenUIViewModel = ServerDrivenUIViewModel(),uiData:String) {

        LaunchedEffect(Unit){
            serverDrivenUIViewModel.serializeData(uiData)
        }

        val serverDrivenUIData by serverDrivenUIViewModel.serverDrivenUIData.collectAsState()

        var contentAlignment: Alignment? = null
        if (serverDrivenUIData.uiData?.get(0) != null)
            contentAlignment = boxContentAlignment(serverDrivenUIData.uiData?.get(0)!!)
        if (serverDrivenUIData.uiData?.isNotEmpty() == true)
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = contentAlignment ?: Alignment.TopStart
            ) {
                RenderUI(serverDrivenUIData.uiData, null)
            }

    }
}