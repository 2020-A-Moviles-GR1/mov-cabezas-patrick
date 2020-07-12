
import java.io.File
import kotlin.collections.ArrayList

fun main(args:Array<String>) {
    val marcas = ArrayList<Marca>()
    val modelos = ArrayList<Modelo>()
    leerMarcas(marcas)
    leerModelos(modelos)
    while (true)
        menuPrincipal(marcas,modelos)
}
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

fun agregarMarca(marcas : ArrayList<Marca>){
    print("Ingresen el nombre de la Marca: ")
    val nombre = readLine().toString()
    print("Ingresen el pais de origen de la Marca: ")
    val pais = readLine().toString()
    print("Ingresen el Año de creacion de la Marca(2000): ")
    val anio = readLine().toString().toInt()
    print("Ingresen true o false si existe sucurla en su Pais: ")
    val sucursal_local = readLine()!!.toBoolean()
    print("Ingresen el valor de la empresa(100000): ")
    val valor = readLine().toString().toFloat()
    marcas.add( Marca(nombre,pais,anio,sucursal_local,valor))
}

fun eliminarMarca(marcas : ArrayList<Marca>, modelos: ArrayList<Modelo>){
    print("Ingresen el Nombre de la Marca a Eliminar: ")
    var nombre = readLine().toString()
    var esta=marcas.removeIf { iteracion : Marca ->
        iteracion.nombre==nombre
    }
    if(esta){
        eliminarModeloMarca(nombre,modelos)
    }else{
        print("No existe la Marca")
    }

}

fun mostrarMarcas(marcas : ArrayList<Marca>){
    for (i in marcas) {
        println(i)
    }
}
fun actualizarMarcas(marcas : ArrayList<Marca>){
    print("Ingresen el nombre de la Marca a actualizar: ")
    val nombre = readLine().toString()
    val esta = marcas.removeIf { iteracion : Marca ->
        iteracion.nombre==nombre
    }
    if(esta){
        print("Ingresen el pais de origen de la Marca: ")
        val pais = readLine().toString()
        print("Ingresen el Año de creacion de la Marca(2000): ")
        val anio = readLine().toString().toInt()
        print("Ingresen true o false si existe sucurla en su Pais: ")
        val sucursal_local = readLine()!!.toBoolean()
        print("Ingresen el valor de la empresa(100000): ")
        val valor = readLine().toString().toFloat()
        marcas.add( Marca(nombre,pais,anio,sucursal_local,valor))
    }else{
        println("No existe la Marca")
    }
}

fun guardaMarcas(marcas : ArrayList<Marca>){
    var texto:String=""
    for (i in marcas) {
        texto += i.nombre+"|"+i.pais_origen+"|"+i.anio_creacion+"|"+i.sucursal_local+"|"+i.valor+"|"+"\n"
    }
    File("marcas.txt").writeText(texto)
}
fun leerMarcas(marcas : ArrayList<Marca>){
    File("marcas.txt").forEachLine {
        var a = it.split("|")
        marcas.add(Marca(a[0],a[1],a[2].toInt(),a[3]!!.toBoolean(),a[4]!!.toFloat()))
    }
}

fun guardaModelos(modelos : ArrayList<Modelo>){
    var texto:String=""
    for (i in modelos) {
        texto += i.nombre+"|"+i.anio_lanzamiento+"|"+i.precio+"|"+i.disponible+"|"+i.marca+"|"+"\n"
    }
    File("modelos.txt").writeText(texto)
}
fun leerModelos(modelos : ArrayList<Modelo>){
    File("modelos.txt").forEachLine {
        var a = it.split("|")
        modelos.add(Modelo(a[0],a[1]!!.toInt(),a[2]!!.toFloat(),a[3]!!.toBoolean(),a[4]))
    }
}

fun agregarModelos(marcas : ArrayList<Marca>, modelos : ArrayList<Modelo>){
    print("Ingrese el nombre de la Marca: ")
    val marca = readLine().toString()
    val esta = marcas.find { it : Marca ->
        it.nombre==marca
    }
    if(esta != null){
        print("Ingresen el nombre del Modelo: ")
        val nombre = readLine().toString()
        print("Ingresen el año de lanzamiento del Modelo: ")
        val anio = readLine().toString().toInt()
        print("Ingresen el Precio del Modelo: ")
        val precio = readLine().toString().toFloat()
        print("Ingresen true o false si hay disponibilidad: ")
        val disponible = readLine().toString().toBoolean()
        modelos.add( Modelo(nombre,anio,precio,disponible,marca))
    }else{
        println("No existe la Marca")
    }
}

fun mostrarModelos(modelos : ArrayList<Modelo>){
    println("Ingrese el nombre de la Marca o enter para mostrar todos lo Modelos: ")
    val marca = readLine().toString()
    if(marca==""){
        for (i in modelos) {
            println(i)
        }
    }else{
        val modelo = modelos.filter { iteracion : Modelo ->
            iteracion.marca==marca
        }
        for (i in modelo) {
            println(i)
        }
    }
}
fun actualizarModelos(marcas : ArrayList<Marca>,modelos : ArrayList<Modelo>){
    print("Ingresen el nombre del Modelo a actualizar: ")
    var nombre = readLine().toString()
    var modelo=modelos.removeIf { iteracion : Modelo ->
        iteracion.nombre==nombre
    }
    if(modelo){
        agregarModelos(marcas,modelos)
    }else{
        println("No existe el Modelo")
    }
}
fun eliminarModelos(modelos : ArrayList<Modelo>){
    print("Ingresen el nombre del Modelo a eliminar: ")
    val nombre = readLine().toString()
    val resultado=modelos.removeIf { iteracion : Modelo ->
        iteracion.nombre==nombre
    }
    if (!resultado) println("No existe el Modelo")
}

fun eliminarModeloMarca(marca: String,modelos: ArrayList<Modelo>){
    modelos.removeIf { iteracion : Modelo ->
        iteracion.marca==marca }
}

fun menuPrincipal(marcas : ArrayList<Marca>, modelos : ArrayList<Modelo>){
    var texto:String
    texto ="--------------------------------------"+"\n"
    texto+="-          Menu Principal            -"+"\n"
    texto+="- Registro de Marcas de Celulare     -"+"\n"
    texto+="- 1. Ingresar una nueva Marca        -"+"\n"
    texto+="- 2. Mostrar las Marcas registradas  -"+"\n"
    texto+="- 3. Modificar una Marca             -"+"\n"
    texto+="- 4. Eliminar una Marca              -"+"\n"
    texto+="- 5. Menu Modelos de Celulare        -"+"\n"
    texto+="- 6. Salir                           -"+"\n"
    texto+="--------------------------------------"+"\n"
    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>6 || opcion<1){
                print("ingrese un valor correcto")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese un valor valido")
        }
    }
    when(opcion){
        1-> agregarMarca(marcas)
        2-> mostrarMarcas(marcas)
        3-> actualizarMarcas(marcas)
        4-> eliminarMarca(marcas,modelos)
        5-> {
            while (menuModelos(marcas, modelos)){ }
        }
        6-> {
            guardaMarcas(marcas)
            guardaModelos(modelos)
            System.exit(0)
        }
    }
}
fun menuModelos(marcas : ArrayList<Marca>, modelos : ArrayList<Modelo>) : Boolean{
    var estado=true
    var texto:String
    texto ="--------------------------------------"+"\n"
    texto+="-       Menu Modelos Celulares       -"+"\n"
    texto+="- Registro de Modelos de Celulare    -"+"\n"
    texto+="- 1. Ingresar una nueva Modelo       -"+"\n"
    texto+="- 2. Mostrar las Modelo registradas  -"+"\n"
    texto+="- 3. Modificar una Modelo            -"+"\n"
    texto+="- 4. Eliminar una Modelo             -"+"\n"
    texto+="- 5. Menu Marcas de Celulare         -"+"\n"
    texto+="--------------------------------------"+"\n"
    println(texto)
    var opcion:Int
    while(true){
        try {
            opcion = readLine().toString()!!.toInt()
            if(opcion>5 || opcion<1){
                print("ingrese un valor correcto")
            }else{
                break
            }
        }catch (e: NumberFormatException){
            print("Ingrese un valor valido")
        }
    }
    when(opcion){
        1-> agregarModelos(marcas,modelos)
        2-> mostrarModelos(modelos)
        3-> actualizarModelos(marcas,modelos)
        4-> eliminarModelos(modelos)
        5-> estado= false
    }
    return estado
}


