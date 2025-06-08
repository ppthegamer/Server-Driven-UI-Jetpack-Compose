package com.praptechie.serverdrivenuicompose.data_models

data class ServerDrivenUIHeaderJsonData(
    val uiData:List<ServerDrivenUIData>?=null
)

data class ServerDrivenUIData(val type:String?=null,
                              val margin: MarginData?=null,
                              val style: StyleData?=null,
                              val children :List<ServerDrivenUIData>?=null,
                              val content:List<ContentData>?=null,
                              val action: ActionData?=null,
                              val itemSize: ItemSize?=null
    )

data class MarginData(val top:Long?=null,val left:Long?=null,val bottom:Long?=null,val right:Long?=null,val all:Long?=null)

data class StyleData(val modifier: ModifierData?=null, val columnStyle: ColumnStyle?=null, val rowStyle: RowStyle?= null, val textStyle: TextStyleData?=null, val boxContentAlignment:String?=null)

data class ModifierData(val clip: ModifierClipData?=null, val backgroundColor:String?=null
                        , val padding: MarginData?=null, val onClick: OnClickData?=null)
data class ModifierClipData(val shape:String?=null,val radius:Int?=null)

data class OnClickData(val action: ActionData?=null)
data class ActionParameters(val text:String?=null,val screen:String?=null)

data class ContentData(val text: String?=null,val imageUrl: String?=null,val url: String?=null,val modifier:ModifierData?=null)

data class ActionData(val perform:String?=null,val parameters: ActionParameters?=null)

data class ColumnStyle(val verticalArrangement: String?=null,val horizontalAlignment:String?=null,val spaceBy:Int?=null)
data class RowStyle(val horizontalArrangement: String?=null,val verticalAlignment:String?=null,val spaceBy:Int?=null)
data class TextStyleData(val fontSize: Int?=null,val textColor:String?=null,val fontWeight:String?=null)
data class ItemSize(val height:Int?=null,val width:Int?=null)