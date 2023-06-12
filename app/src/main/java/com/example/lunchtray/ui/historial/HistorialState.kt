package com.example.lunchtray.ui.historial

import com.example.lunchtray.model.HistorialItem

//Atencion: los valores isLoading y Error fueron creados para gestionar el repositorio y los estados de la peticion.
//no fue posible implementar con repositorio, fue necesario acceder directamente desde el viewModel a los datos.
//en futuras revisiones, seria interesante buscar solucion a los problemas de librerias para solventar.
data class HistorialState(
    val isLoading: Boolean = false,
    var historial: List<HistorialItem> = emptyList(),
    val error: String = ""
)
