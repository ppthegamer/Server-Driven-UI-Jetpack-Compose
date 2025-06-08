package com.praptechie.serverdrivenuicompose.ui_elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.praptechie.serverdrivenuicompose.RenderUI
import com.praptechie.serverdrivenuicompose.columnStyle
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIData


@Composable
fun ServerDrivenUIColumn(childrenData: ServerDrivenUIData, modifier: Modifier,) {
    val state = rememberScrollState()
    val (horizontalAlignment,verticalArrangement) = columnStyle(childrenData)
    Column (modifier=modifier.verticalScroll(enabled = true, state = state), verticalArrangement = verticalArrangement?:Arrangement.Center, horizontalAlignment = horizontalAlignment?:Alignment.CenterHorizontally) {

        childrenData.children?.forEach {
            if(childrenData.content!=null)
                childrenData.content.forEach {data->
                    RenderUI(listOf(it),data)
                }else
                RenderUI(listOf(it),null)
        }
    }
}




