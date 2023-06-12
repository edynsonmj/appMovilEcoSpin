/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.lunchtray

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.lunchtray.ui.theme.EcoSpinTheme
//TODO: Nota
//maquetaciones de todas las vistas hechas, pero solo menu y historico tienen funcionalidad con la base de datos.
//Repositorio no fue posible usarlo, se accede directamente desde el view model a la base de datos.
//          entre las dificultades la no compatibilidad de la libreria hill para realizar inyecciones de dependencias
//          problemas para gestiones el estado de un query a la base de datos.
//los elementos del historial son cargados en su mayoria como string por cuestiones de tiempo.
//          si se agregan mas elementos los nombre y tipos de datos deben coincidir perfectamente con los del modelo de la aplicacion, lo contrario probocara el cierre de la aplicacion de forma prematura
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EcoSpinTheme {
                EcoSpinApp()
            }
        }
    }
}
