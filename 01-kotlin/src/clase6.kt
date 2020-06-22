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

    //println(vida)
/*
    val nuevoNumeroUno=SumarDosNumerosDos(1,1)
    val nuevoNumeroDos=SumarDosNumerosDos(null,1)
    val nuevoNumeroTres=SumarDosNumerosDos(1,null)
    val nuevoNumeroCuatro=SumarDosNumerosDos(null,null)*/
/*
    println(SumarDosNumerosDos.arregloNumero)
    SumarDosNumerosDos.agregarNumero(2)
    println(SumarDosNumerosDos.arregloNumero)
    SumarDosNumerosDos.eliminaNumero(1)
    println(SumarDosNumerosDos.arregloNumero)*/

    val nombre:String?=null
    println(nombre?.length)

}

abstract class Numeros(//val nuevosNumeros = Numeros(1,2)
         var numeroUno:Int,
         var numeroDos:Int
){
}

class Suma(
        protected var uno:Int,
        protected var dos:Int
):Numeros(uno,dos){
     fun suma():Int{
        return this.numeroUno+this.numeroDos
    }
}
class SumarDosNumerosDos(
        uno: Int,
        dos: Int
) : Numeros(uno, dos) {
    init {
        println("Init")
    }
    constructor(uno: Int?, dos: Int) : this(
            if (uno == null) 0 else uno,dos
    ) {
        println("Hola 1")
    }

    constructor(uno: Int, dos: Int?) : this(
            uno,
            if (dos == null) 0 else dos
    ) {
        println("Hola 2")
    }

    constructor(uno: Int?, dos: Int?) : this(
            if (uno == null) 0 else uno,
            if (dos == null) 0 else dos
    ) {
        println("Hola 3")
    }

    companion object{
        val arregloNumero = arrayListOf(1,2,3,4)

        fun agregarNumero(nuevoNumeros: Int){
            this.arregloNumero.add(nuevoNumeros)
        }
        fun eliminaNumero(posisionNumero: Int){
            this.arregloNumero.add(posisionNumero)
        }
    }
}