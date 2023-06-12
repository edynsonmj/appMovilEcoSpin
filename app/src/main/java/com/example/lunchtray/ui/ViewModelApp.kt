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
import com.example.lunchtray.model.MenuItem
import com.example.lunchtray.model.MenuItem.AccompanimentItem
import com.example.lunchtray.model.MenuItem.EntreeItem
import com.example.lunchtray.model.MenuItem.SideDishItem
import com.example.lunchtray.model.OrderUiState
import com.example.lunchtray.repositories.HistorialRepository
import com.example.lunchtray.repositories.ResultHistorico
import com.example.lunchtray.ui.historial.HistorialState
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.tasks.await
import java.text.NumberFormat

class ViewModelApp : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    private val _historialState = MutableStateFlow(HistorialState())
    val hitorialState: StateFlow<HistorialState> = _historialState.asStateFlow()

    private val historialRepository = HistorialRepository()

    private val db = Firebase.firestore
    init {
        getHistorial2()
    }
    fun getHistorial(){
        historialRepository.listarHistorial().onEach { result ->
            when(result){
                is ResultHistorico.Error -> {
                    _historialState.value = HistorialState(error = result.message ?: "Error Inesperado")
                }
                is ResultHistorico.Loading -> {
                    _historialState.value = HistorialState(isLoading = true)
                }
                is ResultHistorico.Success -> {
                    _historialState.value = HistorialState(historial = result.Data ?: emptyList())
                }
            }
        }
        _historialState
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

    fun listarHistorial() : Flow<ResultHistorico<List<HistorialItem>>> = flow{
        try{
            emit(ResultHistorico.Loading<List<HistorialItem>>())
            val listaHistoria = db.collection("historial")
                .get()
                .await()
                .map{
                        document->document.toObject(HistorialItem::class.java)
                }
            println("CONSULTA A LA LISTA TAMAÑO: "+listaHistoria.size)
            emit(ResultHistorico.Success<List<HistorialItem>>(data=listaHistoria))
        }catch (e: Exception){
            emit(ResultHistorico.Error<List<HistorialItem>>(message = e.localizedMessage ?: "Error desconocido"))
        }
    }

    fun updateEntree(entree: EntreeItem) {
        val previousEntree = _uiState.value.entree
        updateItem(entree, previousEntree)
    }

    fun updateSideDish(sideDish: SideDishItem) {
        val previousSideDish = _uiState.value.sideDish
        updateItem(sideDish, previousSideDish)
    }

    fun updateAccompaniment(accompaniment: AccompanimentItem) {
        val previousAccompaniment = _uiState.value.accompaniment
        updateItem(accompaniment, previousAccompaniment)
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val previousItemPrice = previousItem?.price ?: 0.0
            // subtract previous item price in case an item of this category was already added.
            val itemTotalPrice = currentState.itemTotalPrice - previousItemPrice + newItem.price
            // recalculate tax
            val tax = itemTotalPrice * taxRate
            currentState.copy(
                itemTotalPrice = itemTotalPrice,
                orderTax = tax,
                orderTotalPrice = itemTotalPrice + tax,
                entree = if (newItem is EntreeItem) newItem else currentState.entree,
                sideDish = if (newItem is SideDishItem) newItem else currentState.sideDish,
                accompaniment = if(newItem is AccompanimentItem) newItem else
                    currentState.accompaniment
            )
        }
    }
}
fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}
