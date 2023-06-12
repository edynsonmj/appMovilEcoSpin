package com.example.lunchtray.ui.historial

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.lunchtray.model.HistorialItem
import com.example.lunchtray.ui.ViewModelApp

@Composable
fun HistorialScreen(viewModel:ViewModelApp) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(50.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        viewModel.getHistorial2()
       items(viewModel.hitorialState.value.historial){
           item: HistorialItem ->  ItemList(item.fecha, item.hora, item.valor.toString(), item.vehiculo)
       }
    }
}
@Composable
fun ItemList(fecha: String,hora: String,valor:String,vehiculo:String){
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 10.dp),
        shape= AbsoluteRoundedCornerShape(22.dp)
    ) {
        Row() {
            Column(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(10.dp, 5.dp, 0.dp, 5.dp)
            ) {
                Text(text = fecha, fontWeight = FontWeight.ExtraBold)
                Text(text = vehiculo, color = Color.Gray)
            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp, 5.dp, 10.dp, 5.dp),
                horizontalAlignment = Alignment.End
            )
            {
                Text(text = "$ "+valor, fontWeight = FontWeight.ExtraBold)
            }
        }
    }
}

//TODO: este marcador dividiria los pagos por fecha, para implementar a posterior, es necesario cambiar en la base de datos la fecha a formato fecha.
//luego hacer un sort en base en la fecha a la lista que se aloja en el state
//poner condicional revisando la fecha en la columna desplazable.
//si la fecha cambia desde la anterior (otro dia), llamar esta funcion pasando la fecha nueva
@Composable()
fun Marcador(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp, 0.dp)
    ) {
        //TODO: marcador es la fecha de agrupacion, debe llegar como parametro
        Text(text = "Hoy", color = Color.Gray, fontWeight = FontWeight.ExtraBold)
    }
}

@Preview
@Composable
fun HistorialScreenPreview(){
    //HistorialScreen()
}