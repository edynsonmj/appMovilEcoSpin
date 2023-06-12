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
package com.example.lunchtray.ui

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.lunchtray.model.HistorialItem
import com.example.lunchtray.ui.historial.HistorialState
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ViewModelApp : ViewModel() {

    private val _historialState = MutableStateFlow(HistorialState())
    val hitorialState: StateFlow<HistorialState> = _historialState.asStateFlow()

    //Atencion: se accede directamente a la base de datos, lo ideal seria tener un repositorio para ello
    //no fue posible de momento - incompatibilidad con la librerial Hill para inyeccion de dependencias y gestion del los estados del query a la base de datos.
    private val db = Firebase.firestore
    init {
        getHistorial2()
    }

    fun getHistorial2(){
        var auxList: ArrayList<HistorialItem>
        db.collection("historial")
            .get()
            .addOnSuccessListener { result ->
                if(result.isEmpty){
                    Log.d(TAG,"esta vacio")
                }
                Log.d(TAG,result.size().toString())
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
                var list = result.documents.map { document->document.toObject(HistorialItem::class.java) }
                _historialState.value.historial = list as List<HistorialItem>
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }
}
