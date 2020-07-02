
import java.io.File
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    var marcas = ArrayList<Marca_auto>()
    var modelos = ArrayList<Modelo_auto>()
    var marca="BMdW"
    leerModelos(modelos)
    leerMarcas(marcas)
    eliminarMarca(marcas,modelos)
    mostrarModelos(modelos)
    println("\n*******************\n")
    mostrarMarcas(marcas)
}
class Marca_auto(
    var nombre:String,
    var pais_origen:String,
    var fecha_creacion:String,
    var sucursal_local:Boolean,
    var valor:Int
){
    override fun toString(): String {
        return "Marca_auto(nombre='$nombre', pais_origen='$pais_origen', fecha_creacion='$fecha_creacion', sucursal_local=$sucursal_local, valor=$valor)"
    }
}
class Modelo_auto(
        var nombre:String,
        var tipo:Int,
        var precio_bace:Float,
        var hibrido:Boolean,
        var marca:String
){
    override fun toString(): String {
        return "Modelo_auto(nombre='$nombre', tipo=$tipo, precio_bace=$precio_bace, hibrido=$hibrido, marca='$marca')"
    }
}

fun agregarMarca(marcas : ArrayList<Marca_auto>):ArrayList<Marca_auto>{
    //print("Ingresen el nombre de la marca: ")
    var nombre = "BMW"//readLine().toString()
    //print("Ingresen el pais de origen de la marca: ")
    var pais_origen = "alemania"//readLine().toString()
    //print("Ingresen la fecha de creacion de la marca(12-06-2020): ")
    var fecha_creacion = "octubre"//readLine().toString()
    //print("Ingresen True o False si existe sucurla en su pais: ")
    var sucursal_local = true//readLine()!!.toBoolean()
    //print("Ingresen el valor de la empresa(100000): ")
    var valor = 1000000//readLine()!!.toInt()
    marcas.add( Marca_auto(nombre,pais_origen,fecha_creacion,sucursal_local,valor))
    return marcas
}

fun eliminarMarca(marcas : ArrayList<Marca_auto>,modelos: ArrayList<Modelo_auto>):ArrayList<Marca_auto>{
    //print("Ingresen el nombre de la marca a eliminar: ")
    var nombre = "BMW"//readLine().toString()
    marcas.removeIf { iteracion : Marca_auto ->
        iteracion.nombre==nombre
    }
    eliminarModeloMarca(nombre,modelos)
    return marcas
}

fun mostrarMarcas(marcas : ArrayList<Marca_auto>){
    for (i in marcas) {
        println(i)
    }
}
fun actualizarMarcas(marcas : ArrayList<Marca_auto>):ArrayList<Marca_auto>{
    //print("Ingresen el nombre de la marca a actualizar: ")
    var nombre = "BMW"//readLine().toString()
    marcas.removeIf { iteracion : Marca_auto ->
        iteracion.nombre==nombre
    }
    //print("Ingresen el nuevo pais de origen de la marca: ")
    var pais_origen = "alemania"//readLine().toString()
    //print("Ingresen la nueva fecha de creacion de la marca(12-06-2020): ")
    var fecha_creacion = "octubre"//readLine().toString()
    //print("Ingresen True o False si existe sucurla en su pais: ")
    var sucursal_local = true//readLine()!!.toBoolean()
    //print("Ingresen el valor de la empresa(100000): ")
    var valor = 1000000//readLine()!!.toInt()
    marcas.add( Marca_auto(nombre,pais_origen,fecha_creacion,sucursal_local,valor))
    return marcas
}

fun guardaMarcas(marcas : ArrayList<Marca_auto>){
    var texto:String=""
    for (i in marcas) {
        texto += i.nombre+"|"+i.pais_origen+"|"+i.fecha_creacion+"|"+i.sucursal_local+"|"+i.valor+"|"+"\n"
    }
    File("marcas.txt").writeText(texto)
}
fun leerMarcas(marcas : ArrayList<Marca_auto>):ArrayList<Marca_auto>{
    File("marcas.txt").forEachLine {
        var a = it.split("|")
        marcas.add(Marca_auto(a[0],a[1],a[2],a[3]!!.toBoolean(),a[4]!!.toInt()))
    }
    return marcas
}

fun guardaModelos(modelos : ArrayList<Modelo_auto>){
    var texto:String=""
    for (i in modelos) {
        texto += i.nombre+"|"+i.tipo+"|"+i.precio_bace+"|"+i.hibrido+"|"+i.marca+"|"+"\n"
    }
    File("modelos.txt").writeText(texto)
}
fun leerModelos(modelos : ArrayList<Modelo_auto>):ArrayList<Modelo_auto>{
    File("modelos.txt").forEachLine {
        var a = it.split("|")
        modelos.add(Modelo_auto(a[0],a[1]!!.toInt(),a[2]!!.toFloat(),a[3]!!.toBoolean(),a[4]))
    }
    return modelos
}

fun agregarModelos(marcas : ArrayList<Marca_auto>,modelos : ArrayList<Modelo_auto>):ArrayList<Modelo_auto>{
    //print("Ingrese el nombre de la Marca: ")
    var marca = "BMddW"//readLine().toString()
    var esta = marcas.find { iteracion : Marca_auto ->
        iteracion.nombre==marca
    }
    if(esta != null){
        //print("Ingresen el nombre del modelo: ")
        var nombre = "BMW"//readLine().toString()
        //print("Ingresen que tipo de vehiculo es: ")
        var tipo = 27777//readLine()!!.toInt()
        //print("Ingresen el precio base: ")
        var precio_bace = 2000.1//readLine()!!.toFloat()
        //print("Ingresen True o False si es hibrido: ")
        var hibrido = false//readLine()!!.toBoolean()
        modelos.add( Modelo_auto(nombre,tipo,precio_bace!!.toFloat(),hibrido,marca))
    }else{
        println("No existe la Marca")
    }
    return modelos
}

fun mostrarModelos(modelos : ArrayList<Modelo_auto>){
    for (i in modelos) {
        println(i)
    }
}
fun actualizarModelos(modelos : ArrayList<Modelo_auto>):ArrayList<Modelo_auto>{
    //print("Ingresen el nombre del modelo a actualizar: ")
    var nombre = "BMW"//readLine().toString()
    var modelo=modelos.removeIf { iteracion : Modelo_auto ->
        iteracion.nombre==nombre
    }
    if(modelo){
        //print("Ingrese el nombre de la Marca: ")
        var marca = "BMW"//readLine().toString()
        //print("Ingresen el nombre del modelo: ")
        nombre = "nuevo"//readLine().toString()
        //print("Ingresen que tipo de vehiculo es: ")
        var tipo = 27777//readLine()!!.toInt()
        //print("Ingresen el precio base: ")
        var precio_bace = 2000.1//readLine()!!.toFloat()
        //print("Ingresen True o False si es hibrido: ")
        var hibrido = false//readLine()!!.toBoolean()
        modelos.add( Modelo_auto(nombre,tipo,precio_bace!!.toFloat(),hibrido,marca))
    }else{
        println("No existe el Vehiculo")
    }
     return modelos
}
fun eliminarModelos(modelos : ArrayList<Modelo_auto>):ArrayList<Modelo_auto>{
    //print("Ingresen el nombre del modelo a eliminar: ")
    var nombre = "BMW"//readLine().toString()
    var resultado=modelos.removeIf { iteracion : Modelo_auto ->
        iteracion.nombre==nombre
    }
    if (!resultado) println("No existe el Vehiculo")
    return modelos
}
//pendiente
fun eliminarModeloMarca(marca: String,modelos: ArrayList<Modelo_auto>){
modelos.removeIf { iteracion : Modelo_auto ->
    iteracion.marca==marca }
}

