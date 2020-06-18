fun main(args: Array<String>){

    val arregloCumpleanos: ArrayList<Int> = arrayListOf(30, 31, 22, 23, 20)
    //print(arregloCumpleanos)

    val respuestaFilter = arregloCumpleanos
            .filter { iteracion: Int ->
                val esMayorA23 = iteracion > 23
                return@filter esMayorA23
            }

    arregloCumpleanos
            .filter { it > 23 }
    //println(respuestaFilter)
    //println(arregloCumpleanos)

    // Any -> OR (Some)
    // All -> AND (Every)
    // AND -> TRUE, Todo lo demas falso
    // OR -> TODO es falso, lo demas era verdadero
    // 1) Devolver una expresion (TRUE o FALSE)
    // 2) Devuelve un Booleano
    // (30, 31, 22, 23, 20)
    val respuestaAny = arregloCumpleanos
            .any { iterador: Int ->
                return@any iterador < 25
            }
    //println(respuestaAny)

    val respuestaAll = arregloCumpleanos
            .all { iterador: Int ->
                return@all iterador > 18
            }
    //println(respuestaAll)
//uso de redude
    val respuestaReduce:Int=arregloCumpleanos.reduce{acumulador,iteracion->
        return@reduce acumulador+iteracion
    }
    //println(respuestaReduce)
    //uso de la fucion fold
    val respuestaFold:Int=arregloCumpleanos.fold(100,{acumulador,iteracion->
        return@fold acumulador-iteracion
    })
    //println(respuestaFold)

    val vida = arregloCumpleanos.map { it*.8 }.filter { it>18 }.fold(100.00,{acc,d->acc-d})

    println(vida)


}

abstract class Numeros(//val nuevosNumeros = Numeros(1,2)
         val numeroUno:Int,
         val numeroDos:Int
){
}

class Suma(
        private var uno:Int,
        private var dos:Int
):Numeros(uno,dos){
     fun suma():Int{
        return this.numeroUno+this.numeroDos
    }
}