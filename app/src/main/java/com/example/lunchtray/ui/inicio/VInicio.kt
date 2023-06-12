package com.example.lunchtray.ui.inicio

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun InicioScreen() {

    Column(
        modifier = Modifier
            .fillMaxWidth().paddingFromBaseline(0.dp,50.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = email,
            onValueChange = { newText -> email = newText },
            label = { Text(text = "Email") },
            placeholder = { Text(text = "Ingresa tu correo electronico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Email,
                    contentDescription = "icono de email"
                )
            },
            modifier = Modifier.padding(0.dp, 10.dp)
        )

        var contraseña by remember { mutableStateOf(TextFieldValue("")) }
        OutlinedTextField(
            value = contraseña,
            onValueChange = { newText -> contraseña = newText },
            label = { Text(text = "Confirmar contraseña") },
            placeholder = { Text(text = "Ingresa tu contraseña") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Lock,
                    contentDescription = "icono de contraseña"
                )
            },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.padding(0.dp, 10.dp)
        )
        Button(onClick = { /*TODO*/ }, Modifier.paddingFromBaseline(50.dp, 0.dp)) {
            Text(text = "ENTRAR")
        }
        Row() {
            Text(text = "¿No tienes una cuenta?")
            Text(text = " Registrate", fontWeight = FontWeight.Bold)
        }
    }
}

@Preview
@Composable
fun InicioScreenPreview() {
    InicioScreen()
}