package com.example.lunchtray.ui.menu

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lunchtray.R

@Composable
fun MenuPrincipalScreen(
    historialBotonClick: () -> Unit,
    modifier: Modifier = Modifier
){
    LazyColumn(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,

    ) {
        item {
            Text(text = stringResource(R.string.opciones), fontWeight = FontWeight.Bold, fontSize = 30.sp)
            Card(onClick = { /*TODO*/ }, modifier = Modifier.padding(5.dp)) {
                Column(){
                    Text(text = "Bicicletas", modifier=Modifier.align(CenterHorizontally), fontSize = 25.sp)
                    Image(painter = painterResource(id = R.drawable.bicicleta), contentDescription = "bicicleta");
                }
            }
            Spacer(modifier = Modifier.size(10.dp))
            Text(text = "¡Selecciona una opcion!", fontSize = 10.sp, color = Color.Green)
            Spacer(modifier = Modifier.size(10.dp))

            Card(onClick = { /*TODO*/ }) {
                Column(){
                    Text(text = "Scooter", modifier=Modifier.align(CenterHorizontally), fontSize = 25.sp)
                    Image(painter = painterResource(id = R.drawable.scooter), contentDescription = "scooter");
                }
            }
            Row(horizontalArrangement = Arrangement.End) {
                Button(onClick = historialBotonClick, Modifier.size(150.dp,35.dp)) {
                    Text(text = "Historial")
                }
            }
        }

    }
}

@Preview
@Composable
fun MenuPrincipalScreenPreview(){
    MenuPrincipalScreen(historialBotonClick = { /*TODO*/ })
}