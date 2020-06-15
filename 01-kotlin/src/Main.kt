import java.util.*

fun main(args:Array<String>) {
    println("Hola Mundo")
    //mutables

    //siempre usar variables inmutables.
    //usar variable descriptivas
    //el objetivo es que el codigo sea mantenible.
    //tiene duck typing para determinar variables
    //se necesita asignar el tipo cuando no hay valor al iniciarla
    var edadCachorro : Int
    edadCachorro = 4
    var edadPersona  = 23
    //variable inmutables
    val nombreProfesor:String = "Vivente Adrian"
    val sueldo = 12.20
    val fechaNacimiento=Date() //new Date
    //el when es como el switch de java
    when (sueldo){
        12.20 -> println("Sueldo normal")
        -12.20 -> println("Sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEstalecido = (sueldo==12.20)
    println(esSueldoMayorAlEstalecido)

    val calc=calculadorSueldo(16.00,14.00)//named parameters
    println("el resultado de la funciones: "+calc)



    //cualquier tipo de varialbel puede ser nula. con?
    //arreglos
    val arregloConstante: Array<Int> = arrayOf(1,2,3)
    val arregloCumpleanos: ArrayList<Int> = arrayListOf<Int>(32,30,20,23)
    //println(arregloCumpleanos)
    arregloCumpleanos.add(24)
    //println(arregloCumpleanos)
    arregloCumpleanos.remove(30)
    //print(arregloCumpleanos)

    //operadores de arreglos
    //usos de forEach
    /*arregloCumpleanos.forEach { println("Valor de la iteracion: "+it) }

    arregloCumpleanos.forEach({valorIteracion:Int ->
        println("Valor iteracion: "+valorIteracion)
    })

    arregloCumpleanos.forEach { valorIteracion:Int->
        println(valorIteracion)
        //para poner variables en el string
        println("Valor con -1 = ${valorIteracion*-1}")
    }*/


    //la funcion map cambiar el areglo
    println(arregloCumpleanos)
    val respuesta = arregloCumpleanos.map { it*-1 }
    println(respuesta)

    val respuestaMApDos= arregloCumpleanos.map { iterador:Int ->
        val nuevoValor = iterador*-1
        val otroValor = nuevoValor*2
        return@map otroValor
    }
    println(respuestaMApDos)

    val respuestaFilter=arregloCumpleanos.filter {
        val esMayor23:Boolean=it>23
        return@filter esMayor23
    }
    arregloCumpleanos.filter { iteracion:Int -> iteracion > 23 }
    println(respuestaFilter)

}
//los dos valores son requeridos
//calculoEspecial:Boolean?es un parametro opcional oyedeb se =r nulos
fun calculadorSueldo(
        sueldo:Double,
        tasa:Double = 12.00,
        calculoEspecial:Int?=null
): Double {
    if(calculoEspecial != null){
        return sueldo*tasa*calculoEspecial
    }else{
        return sueldo*tasa
    }
}


