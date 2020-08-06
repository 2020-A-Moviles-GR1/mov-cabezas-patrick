package com.example.moviles

import android.os.Parcel
import android.os.Parcelable
import java.util.*

class Usuario(
    var nombre: String?,
    var edad:Int,
    var fechaNacimiento:Date,
    var sueldo:Double
):Parcelable{
    //constructor se lee
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readInt(),
        parcel.readSerializable() as Date,
        parcel.readDouble()
    ) {
    }
//se serializa escribe
    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombre)
        parcel.writeInt(edad)
        parcel.writeDouble(sueldo)
        parcel.writeSerializable(fechaNacimiento)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Usuario> {
        override fun createFromParcel(parcel: Parcel): Usuario {
            return Usuario(parcel)
        }

        override fun newArray(size: Int): Array<Usuario?> {
            return arrayOfNulls(size)
        }
    }
}