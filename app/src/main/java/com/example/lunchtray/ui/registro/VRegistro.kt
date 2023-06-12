package com.example.lunchtray.ui.registro

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun RegistroScreen() {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        )
    {
        item{
            var nombre by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = nombre,
                onValueChange = { newText -> nombre = newText },
                label = { Text(text = "Nombre(s)") },
                placeholder = { Text(text = "Ingresa tu nombre") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "icono de texto"
                    )
                },
                modifier = Modifier.padding(0.dp,10.dp)
            )
        }
        item {
            var apellido by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = apellido,
                onValueChange = { newText -> apellido = newText },
                label = { Text(text = "Apellido(s)") },
                placeholder = { Text(text = "Ingresa tus apellidos") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "icono de texto"
                    )
                },
                modifier = Modifier.padding(0.dp,10.dp)
            )
        }
        item {
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
                modifier = Modifier.padding(0.dp,10.dp)
            )
        }
        item {
            //https://alitalhacoban.medium.com/show-hide-password-jetpack-compose-d0c4abac568f
            var contraseña by remember { mutableStateOf(TextFieldValue("")) }
            OutlinedTextField(
                value = contraseña,
                onValueChange = { newText -> contraseña = newText },
                label = { Text(text = "Contraseña") },
                placeholder = { Text(text = "Ingresa tu contraseña") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "icono de contraseña"
                    )
                },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.padding(0.dp,10.dp)
            )
        }

        item {
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
                modifier = Modifier.padding(0.dp,10.dp)
            )
        }

        item{
            //TODO: agregar remember ¿como se hace para un checkbox?
            Row(
                modifier = Modifier.height(48.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = false,
                    onCheckedChange = {}
                )
                Spacer(Modifier.width(15.dp))
                Text(text = "Acepto los terminos y condiciones")
            }
        }

        item {
            Button(onClick = { /*TODO*/ }) {
                Text(text = "Crear cuenta")
            }
        }
    }
}

@Preview
@Composable
fun RegistroScreePreview() {
    RegistroScreen()
}