package com.praptechie.serverdrivenuijetpackcompose

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import com.google.firebase.Firebase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.database
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

@Composable
fun MainScreen(){

    var serverDrivenUIData: List<ServerDrivenUIData>? by remember {
        mutableStateOf(null)
    }

        serverDrivenUIData= getData().collectAsState(initial = null).value?.uiData
    var contentAlignment:Alignment?=null
    if (serverDrivenUIData!=null)
        contentAlignment = boxContentAlignment(serverDrivenUIData?.get(0)!!)
Box(modifier = Modifier.fillMaxSize(), contentAlignment = contentAlignment?:Alignment.TopStart){
     RenderUI(serverDrivenUIData)}

}

@Composable
fun RenderUI(serverDrivenUIData: List<ServerDrivenUIData>?) {
    val context = LocalContext.current
    var modifier :Modifier by remember {
        mutableStateOf(Modifier)
    }

    serverDrivenUIData?.forEach { uiData->
        uiData.let {data->

            data.children?.forEach {aasdf->
                Log.e("asdfadsfafelasdfae",aasdf.toString())
            }
            modifier = contentModifier(data,context)
            when(data.type){
                "BOX"->{

                    /* data.itemSize.let { size ->
                        modifier= modifier
                            .width(size?.width?.dp ?: 100.dp)
                            .height(size?.height?.dp ?: 50.dp)
                     }

                     data.style?.modifier?.clip.let {clip->
                        modifier=  when (clip?.shape) {
                             "CIRCLE_SHAPE" -> modifier.clip(CircleShape)
                             "CUSTOM" -> clip.radius?.let { radius ->
                                 modifier.clip(RoundedCornerShape(radius.dp))
                             }!!
                             else -> modifier
                         }

                     }
                     data.style?.modifier?.backgroundColor.let { color->

                      modifier=   modifier.background(color = Color(android.graphics.Color.parseColor("#$color" ))
                         )
                     }
                     data.style?.modifier?.padding.let {padding->
                         Log.e("uipadding",padding.toString())
                     modifier=    if(padding?.all!=null && padding.all>0)
                             modifier.padding(padding.all.toInt().dp)
                         else
                             modifier.padding(top =padding?.top?.toInt()?.dp?:0.dp,start=padding?.left?.toInt()?.dp?:0.dp,end=padding?.right?.toInt()?.dp?:0.dp,bottom=padding?.bottom?.toInt()?.dp?:0.dp)
                         }

                     data.style?.modifier?.onClick.let {onClick->

                         modifier=      modifier.clickable {
                             when(onClick?.action?.perform){
                                 "navigate"->{

                                 }
                                 "show_toast"->{
                                     Toast.makeText(context,onClick.action.parameters?.text.toString(),Toast.LENGTH_LONG).show()

                                 }
                                 else ->{}

                         }

                     }
                 }*/
                    ServerDrivenUIBox(modifier = modifier,uiData=data)
                }
                "COLUMN"->
                {
                    ServerDrivenUIColumn(childrenData = data, modifier = modifier)
                }
                "ROW"->{
                    ServerDrivenUIRow(childrenData = data, modifier = modifier)
                }
                "TEXT"->
                {
                    Log.e("iasdfadf",data.toString())
                    ServerDrivenUIText(childrenData = data, modifier = modifier)
                }
                else ->{
                    Text(text = "${data.type}" , color = Color.Black, fontSize = 15.sp)
                }
            }
        }


    }
}






fun getData(): Flow<ServerDrivenUIHeaderJsonData?> = callbackFlow {
    val database = Firebase.database
    val myRef = database.getReference()

    val listener = object : ValueEventListener {
        override fun onDataChange(snapshot: DataSnapshot) {
            try {
                val jsonData = snapshot.getValue(ServerDrivenUIHeaderJsonData::class.java)
                // Emit the fetched data to the Flow
                trySend(jsonData)
            } catch (e: Exception) {
                // Handle error if necessary
                trySend(null)
            }
        }

        override fun onCancelled(error: DatabaseError) {
            // Handle error if necessary
            trySend(null)
        }
    }

    // Attach the listener to the database reference
    myRef.addValueEventListener(listener)

    // Return a cleanup action when the flow collection is canceled
    awaitClose { myRef.removeEventListener(listener) }
}

