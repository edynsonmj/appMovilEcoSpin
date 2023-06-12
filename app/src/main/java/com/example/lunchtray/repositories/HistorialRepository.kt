package com.example.lunchtray.repositories

import com.example.lunchtray.model.HistorialItem
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await

//TODO: atencion, no fue posible usar repositorio, se ha accedido directamente desde el view model
class HistorialRepository()
{
    val db = FirebaseFirestore.getInstance()

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
}