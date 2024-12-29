package com.praptechie.serverdrivenuijetpackcompose.ui_elements

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.praptechie.serverdrivenuijetpackcompose.RenderUI
import com.praptechie.serverdrivenuijetpackcompose.data_models.ServerDrivenUIData
import com.praptechie.serverdrivenuijetpackcompose.columnStyle


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




