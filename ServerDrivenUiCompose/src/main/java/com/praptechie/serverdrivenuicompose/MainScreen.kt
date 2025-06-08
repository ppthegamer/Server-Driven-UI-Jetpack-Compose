package com.praptechie.serverdrivenuicompose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.praptechie.serverdrivenuicompose.data_models.ContentData
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIData
import com.praptechie.serverdrivenuicompose.ui_elements.ServerDrivenUIBox
import com.praptechie.serverdrivenuicompose.ui_elements.ServerDrivenUIColumn
import com.praptechie.serverdrivenuicompose.ui_elements.ServerDrivenUIRow
import com.praptechie.serverdrivenuicompose.ui_elements.ServerDrivenUIText

@Composable
fun RenderUI(serverDrivenUIData: List<ServerDrivenUIData>?, contentData : ContentData?) {
    val context = LocalContext.current
    var modifier: Modifier by remember {
        mutableStateOf(Modifier)
    }
    serverDrivenUIData?.forEach { data ->
            modifier = contentModifier(data, context)
            when (data.type) {
                "BOX" -> {
                    ServerDrivenUIBox(modifier = modifier, uiData = data,contentData=contentData)
                }

                "COLUMN" -> {
                    ServerDrivenUIColumn(childrenData = data, modifier = modifier)
                }

                "ROW" -> {
                    ServerDrivenUIRow(childrenData = data, modifier = modifier)
                }

                "TEXT" -> {
                    ServerDrivenUIText(childrenData = data, modifier = modifier)
                }

                else -> {
                    Text(text = "${data.type}", color = Color.Black, fontSize = 15.sp)
                }
            }
        }



}

