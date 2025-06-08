package com.praptechie.serverdrivenuicompose.ui_elements


import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.praptechie.serverdrivenuicompose.boxContentAlignment
import com.praptechie.serverdrivenuicompose.data_models.ContentData
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIData

@Composable
fun ServerDrivenUIBox(modifier: Modifier, uiData: ServerDrivenUIData, contentData: ContentData?) {
    val contentAlignment = boxContentAlignment(uiData)

    Box(modifier = modifier, contentAlignment = contentAlignment){
        if(contentData!=null)
        {
            val textStyle =if(uiData.style?.textStyle!=null) TextStyle(fontSize =uiData.style.textStyle.fontSize?.sp?:10.sp, color = Color(android.graphics.Color.parseColor( "#${uiData.style.textStyle.textColor}")) ) else null
            Text(text = contentData.text?:"No text data", style =textStyle?: TextStyle(color = Color.Black, fontSize = 15.sp))
        }
        uiData.content?.forEach {textData->
            val textStyle =if(uiData.style?.textStyle!=null) TextStyle(fontSize =uiData.style.textStyle.fontSize?.sp?:10.sp, color = Color(android.graphics.Color.parseColor( "#${uiData.style.textStyle.textColor}")) ) else null
            Text(text = textData.text?:"No text data", style =textStyle?: TextStyle(color = Color.Black, fontSize = 15.sp))

        }
      /*  uiData.children?.forEach { data->
            RenderUI(listOf( data))
        }*/
    }
}