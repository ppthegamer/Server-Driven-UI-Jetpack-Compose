package com.praptechie.serverdrivenuijetpackcompose


import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ServerDrivenUIBox(modifier: Modifier, uiData: ServerDrivenUIData) {
    val contentAlignment = boxContentAlignment(uiData)

    Box(modifier = modifier, contentAlignment = contentAlignment){
        uiData.children?.forEach { data->
            RenderUI(listOf( data))
        }
    }
}