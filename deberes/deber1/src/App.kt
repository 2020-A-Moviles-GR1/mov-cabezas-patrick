import java.io.File
import javax.swing.JFrame
import javax.swing.JOptionPane

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
var frameMenuModelos:JFrame = JFrame();
var frameMenuMarcas:JFrame = JFrame();
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

// !!!!!!!!!!!!!!!!!!!!!!!!!!! Marcas !!!!!!!!!!!!!!!!!!!!!!
fun agragaMarca(marcas : ArrayList<Marca>,marca:Marca){
    marcas.add(marca)
}
fun mostrarMarcas(marcas : ArrayList<Marca>):String{
    var texto:String=""
    for (i in marcas) {
        texto += i.toString()+"\n"
    }
    return texto
}
fun actualizarMarcas(marcas : ArrayList<Marca>,marca:Marca){
    val nombre = marca.nombre
    val esta = marcas.removeIf { iteracion : Marca ->
        iteracion.nombre==nombre
    }
    if(esta){
        marcas.add(marca)
    }else{
        JOptionPane.showMessageDialog(frameMenuMarcas,"No existe la Marca")
    }
}
fun eliminarMarca(marcas : ArrayList<Marca>, modelos: ArrayList<Modelo>,nombreMarca:String){
    var nombre = nombreMarca
    var esta=marcas.removeIf { iteracion : Marca ->
        iteracion.nombre==nombre
    }
    if(esta){
        eliminarModeloMarca(nombre,modelos)
    }else{
        JOptionPane.showMessageDialog(frameMenuMarcas,"No existe la Marca")
    }
}
fun eliminarModeloMarca(marca: String,modelos: ArrayList<Modelo>){
    modelos.removeIf { iteracion : Modelo ->
        iteracion.marca==marca }
}


// !!!!!!!!!!!!!!!!!!  Modelos !!!!!!!!!!!!!!!!!!!111
fun agregarModelos(marcas : ArrayList<Marca>,modelos : ArrayList<Modelo>,modelo:Modelo){

    val marca = modelo.marca
    val esta = marcas.find { it : Marca ->
        it.nombre==marca
    }
    if(esta != null){
        modelos.add(modelo)
    }else{
        JOptionPane.showMessageDialog(frameMenuModelos,"No existe la Marca")
    }
}

fun mostrarModelos(modelos : ArrayList<Modelo>):String{
    var texto:String=""
    for (i in modelos) {
        texto += i.toString()+"\n"
    }
    return texto
}
fun actualizarModelos(modelos : ArrayList<Modelo>,modelo:Modelo){
    var nombre =modelo.nombre
    var esta=modelos.removeIf { iteracion : Modelo ->
        iteracion.nombre==nombre
    }
    if(esta){
        modelos.add(modelo)
    }else{
        JOptionPane.showMessageDialog(frameMenuModelos,"No existe el Modelo")
    }
}
fun eliminarModelos(modelos : ArrayList<Modelo>,nombre:String){
    val resultado=modelos.removeIf { iteracion : Modelo ->
        iteracion.nombre==nombre
    }
    if(!resultado){
        JOptionPane.showMessageDialog(frameMenuModelos,"No existe el Modelo")
    }

}

// !!!!!!!!!!!!!!!!!!!!! Archivos  !!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
fun leerMarcas(marcas : ArrayList<Marca>){
    File("marcas.txt").forEachLine {
        var a = it.split("|")
        marcas.add(Marca(a[0],a[1],a[2].toInt(),a[3]!!.toBoolean(),a[4]!!.toFloat()))
    }
}
fun guardaMarcas(marcas : ArrayList<Marca>){
    var texto:String=""
    for (i in marcas) {
        texto += i.nombre+"|"+i.pais_origen+"|"+i.anio_creacion+"|"+i.sucursal_local+"|"+i.valor+"|"+"\n"
    }
    File("marcas.txt").writeText(texto)
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
fun main() {
    abrirMenuMarcas()
    val marcas = ArrayList<Marca>()
    val modelos = ArrayList<Modelo>()
}
fun abrirMenuMarcas(){
    val frame = JFrame("Main")
    frame.contentPane = MenuMarcas().Panel
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
    frameMenuMarcas=frame
}
fun cerrarMenuMarcas(){
    frameMenuMarcas.dispose()
}
fun abrirMenuModelos(){
    val frame = JFrame("MenuModelos")
    frame.contentPane = MenuModelos().panel
    frame.defaultCloseOperation = JFrame.EXIT_ON_CLOSE
    frame.pack()
    frame.isVisible = true
    frameMenuModelos = frame

}
fun cerrarMenuModelos(){
    frameMenuModelos.dispose()
}
