package com.example.lunchtray.ui.historial

import com.example.lunchtray.model.HistorialItem

data class HistorialState(
    val isLoading: Boolean = false,
    var historial: List<HistorialItem> = emptyList(),
    val error: String = ""
)
