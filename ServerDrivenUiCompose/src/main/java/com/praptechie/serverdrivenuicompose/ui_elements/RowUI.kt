package com.praptechie.serverdrivenuicompose.ui_elements

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.praptechie.serverdrivenuicompose.RenderUI
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIData
import com.praptechie.serverdrivenuicompose.rowStyle


@Composable
fun ServerDrivenUIRow(childrenData: ServerDrivenUIData, modifier: Modifier,) {
    val state = rememberScrollState()
    val (verticalAlignment,horizontalArrangement) = rowStyle(childrenData)

    Row (modifier=modifier.horizontalScroll(state), horizontalArrangement = horizontalArrangement?: Arrangement.Center, verticalAlignment = verticalAlignment?: Alignment.CenterVertically) {
        childrenData.children?.forEach { _ ->
            if(childrenData.content!=null)
            childrenData.content.forEach {data->
                RenderUI((childrenData.children),data)
            }else
            RenderUI((childrenData.children),null)
        }
    }
}
