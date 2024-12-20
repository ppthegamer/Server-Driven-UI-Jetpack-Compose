package com.praptechie.serverdrivenuijetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ServerDrivenUIColumn(childrenData: ServerDrivenUIData, modifier: Modifier,) {

    val (horizontalAlignment,verticalArrangement) =columnStyle(childrenData)

    Column (modifier=modifier, verticalArrangement = verticalArrangement?:Arrangement.Center, horizontalAlignment = horizontalAlignment?:Alignment.CenterHorizontally) {
        childrenData.children?.forEach { data ->
            RenderUI(listOf(data))
        }
    }
}




