package com.example.examen1

class Modelo(
    var nombre:String,
    var anio_lanzamiento:Int,
    var precio:Float,
    var disponible:Boolean,//estado
    var marca:String
){
    override fun toString(): String {
        return "Modelo(Nombre='$nombre', Año Lanzamiento=$anio_lanzamiento, Precio=$precio, Disponible=$disponible, Marca='$marca')"
    }
}