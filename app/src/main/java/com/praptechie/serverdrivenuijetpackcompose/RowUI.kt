package com.praptechie.serverdrivenuijetpackcompose

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun ServerDrivenUIRow(childrenData: ServerDrivenUIData, modifier: Modifier,) {

    val (verticalAlignment,horizontalArrangement) =rowStyle(childrenData)

    Row (modifier=modifier, horizontalArrangement = horizontalArrangement?: Arrangement.Center, verticalAlignment = verticalAlignment?: Alignment.CenterVertically) {
        childrenData.children?.forEach { data ->
            RenderUI(listOf(data))
        }
    }
}
