package com.praptechie.serverdrivenuijetpackcompose

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

@Composable
fun ServerDrivenUIText(childrenData: ServerDrivenUIData,modifier: Modifier) {

    val textStyle =if(childrenData.style?.textStyle!=null) TextStyle(fontSize =childrenData.style.textStyle.fontSize?.sp?:10.sp, color = Color(android.graphics.Color.parseColor( "#${childrenData.style.textStyle.textColor}")) ) else null
    childrenData.content?.forEach {textContent->
        Text(text = textContent.text?:"No text data", style =textStyle?: TextStyle(color = Color.Black, fontSize = 15.sp),modifier=modifier)
    }

    childrenData.children?.forEach { data->
        RenderUI(listOf( data))
    }
}
