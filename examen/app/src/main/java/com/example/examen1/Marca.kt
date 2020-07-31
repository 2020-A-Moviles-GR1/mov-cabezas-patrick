package com.example.examen1

class Marca(
    var nombre:String,
    var pais_origen:String,
    var anio_creacion:Int,
    var sucursal_local:Boolean,
    var valor:Float
){
    override fun toString(): String {
        return "Marca(Nombre='$nombre', Pais Origen='$pais_origen', Año Creacion='$anio_creacion', Sucursal Local=$sucursal_local, Valor=$valor.)"
    }
}