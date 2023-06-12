package com.example.lunchtray.model

data class HistorialItem(
    val fecha: String,
    val hora: String,
    val id: String,
    val valor: Double,
    val vehiculo: String,
    ){
    constructor():this ("","","",5000.0,"")
}