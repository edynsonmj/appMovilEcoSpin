package com.example.lunchtray.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.nio.file.WatchEvent

@Composable
fun HistorialScreen() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .paddingFromBaseline(50.dp, 0.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //TODO: recibir datos de base de datos.
        //TODO: ordenar segun fecha.
        //TODO: segun fecha marcar secciones.
        Marcador()
        ItemList()
        ItemList()
        ItemList()
        ItemList()
        ItemList()
    }
}
@Composable
fun ItemList(){
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
                Text(text = "hora")
                Text(text = "Transporte")
            }
            Column(modifier = Modifier
                .fillMaxWidth(0.5f)
                .padding(5.dp, 5.dp, 0.dp, 5.dp),
                horizontalAlignment = Alignment.End
            )
            {
                Text("valor")
            }
        }
    }
}

@Composable()
fun Marcador(){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(40.dp,0.dp)
    ) {
        //TODO: marcador es la fecha de agrupacion, debe llegar como parametro
        Text(text = "Hoy", color = Color.Gray, fontWeight = FontWeight.ExtraBold)
    }
}

@Preview
@Composable
fun HistorialScreenPreview(){
    HistorialScreen()
}