package com.example.examen1

class ServicioBDDMemoriaMarcas (){

    companion object{
        val listaMarcas = arrayListOf<Marca>()

        fun agragaMarca(marca:Marca){
            listaMarcas.add(marca)
        }
        fun eliminarMarca(posiicon:Int){
            listaMarcas.remove(listaMarcas.get(posiicon))
        }
    }
}
