package com.praptechie.serverdrivenuicompose

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.praptechie.serverdrivenuicompose.data_models.ServerDrivenUIData

fun contentModifier(data: ServerDrivenUIData, context: Context):Modifier{

    var modifier :Modifier =Modifier
    data.itemSize.let { size ->
        modifier=
            if(size?.width!=null && size.height!=null)
                modifier
            .width(size.width.dp)
            .height(size.height.dp )
        else if(size?.width!=null) modifier
                .width(size.width.dp)
        else if(size?.height!=null) modifier
                .width(size.height.dp)
        else modifier
    }

    data.style?.modifier?.clip.let {clip->
        modifier=  when (clip?.shape) {
            "CIRCLE_SHAPE" -> modifier.clip(CircleShape)
            "RECTANGLE"->  modifier.clip(RectangleShape)
            "CUSTOM" -> clip.radius?.let { radius ->
                modifier.clip(RoundedCornerShape(radius.dp))
            }!!
            else -> modifier
        }

    }
    data.style?.modifier?.backgroundColor.let { color->
        if(color!=null)
        modifier=   modifier.background(color = Color(android.graphics.Color.parseColor("#${color}" ))
        )
    }
    data.style?.modifier?.padding.let {padding->
        modifier=    if(padding?.all!=null && padding.all>0)
            modifier.padding(padding.all.toInt().dp)
        else
            modifier.padding(top =padding?.top?.toInt()?.dp?:0.dp,start=padding?.left?.toInt()?.dp?:0.dp,end=padding?.right?.toInt()?.dp?:0.dp,bottom=padding?.bottom?.toInt()?.dp?:0.dp)
    }

    data.style?.modifier?.onClick?.action.let {onClick->

        modifier= modifier.clickable {
            when(onClick?.perform){
                "navigate"->{

                }
                "show_toast"->{
                    Toast.makeText(context,onClick.parameters?.text.toString(), Toast.LENGTH_SHORT).show()

                }
                else ->{
                    Toast.makeText(context,"nothing to show", Toast.LENGTH_SHORT).show()
                }

            }

        }
    }
    return modifier
}

fun columnStyle(data: ServerDrivenUIData): Pair<Alignment.Horizontal?, Arrangement.Vertical?> {
    val horizontalAlignment = when (data.style?.columnStyle?.horizontalAlignment) {
        "START" -> Alignment.Start
        "CENTER_HORIZONTALLY" -> Alignment.CenterHorizontally
        "END" -> Alignment.End
        else -> null // Default value
    }

    val verticalArrangement = when (data.style?.columnStyle?.verticalArrangement) {
        "TOP" -> Arrangement.Top
        "CENTER_VERTICALLY" -> Arrangement.Center
        "BOTTOM" -> Arrangement.Bottom
        "SPACED_EVENLY" -> Arrangement.SpaceEvenly
        "SPACED_BETWEEN" -> Arrangement.SpaceBetween
        "SPACED_AROUND" -> Arrangement.SpaceAround
        "SPACE_BY" -> {Arrangement.spacedBy(data.style.columnStyle.spaceBy?.dp?:0.dp)}
        else ->null
    }

    return horizontalAlignment to verticalArrangement

}

fun rowStyle(data: ServerDrivenUIData): Pair<Alignment.Vertical?, Arrangement.Horizontal?>{
    val horizontalArrangement = when (data.style?.rowStyle?.horizontalArrangement) {
        "START" -> Arrangement.Start
        "CENTER_HORIZONTALLY" -> Arrangement.Center
        "END" -> Arrangement.End
        "SPACED_EVENLY" -> Arrangement.SpaceEvenly
        "SPACED_BETWEEN" -> Arrangement.SpaceBetween
        "SPACED_AROUND" -> Arrangement.SpaceAround
        "SPACE_BY" -> {Arrangement.spacedBy(data.style.rowStyle.spaceBy?.dp?:0.dp)}
        else -> null // No arrangement when not specified
    }

    val verticalAlignment = when (data.style?.rowStyle?.verticalAlignment) {
        "TOP" -> Alignment.Top
        "CENTER_VERTICALLY" -> Alignment.CenterVertically
        "BOTTOM" -> Alignment.Bottom
        else -> null // No alignment when not specified
    }

    return verticalAlignment to horizontalArrangement
}

fun boxContentAlignment(serverDrivenUIData: ServerDrivenUIData): Alignment {
    val contentAlignment =when(serverDrivenUIData.style?.boxContentAlignment){
        "CENTER"->{
            Alignment.Center}
        "CENTER_START"->{
            Alignment.CenterStart}
        "CENTER_END"->{
            Alignment.CenterEnd}
        "TOP_CENTER"->{
            Alignment.TopCenter}
        "TOP_START"->{
            Alignment.TopStart}
        "TOP_END"->{
            Alignment.TopEnd}
        "BOTTOM_CENTER"->{
            Alignment.BottomCenter}
        "BOTTOM_START"->{
            Alignment.BottomStart}
        "Bottom_END"->{
            Alignment.BottomEnd}
        else->{ Alignment.TopStart}
    }
    return contentAlignment
}
