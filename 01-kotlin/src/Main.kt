import java.util.*

fun main(args:Array<String>){
    print("Hola")
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
    val sueldo:Float = 12.20f
    val fechaNacimiento=Date() //new Date
    //el when es como el switch de java
    when (sueldo){
        12.20f -> println("Sueldo normal")
        -12.20f -> println("Sueldo negativo")
        else -> println("No se reconoce el sueldo")
    }

    val esSueldoMayorAlEstalecido = (sueldo==12.20f)
    println(esSueldoMayorAlEstalecido)

    val calc=calculadorSueldo(16.00,14.00)//named parameters
    print(calc)
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