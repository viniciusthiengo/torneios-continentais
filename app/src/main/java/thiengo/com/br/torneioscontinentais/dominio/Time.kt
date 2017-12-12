package thiengo.com.br.torneioscontinentais.dominio

import android.os.Parcel
import android.os.Parcelable


class Time(
        val nome: String,
        val idEscudo: Int,
        val idBandeiraPais: Int,
        val qtdCampeonatos: Int) : Parcelable {

    constructor(source: Parcel) : this(
            source.readString(),
            source.readInt(),
            source.readInt(),
            source.readInt()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) = with(dest) {
        writeString(nome)
        writeInt(idEscudo)
        writeInt(idBandeiraPais)
        writeInt(qtdCampeonatos)
    }

    companion object {
        @JvmField
        val KEY = "key_time"

        @JvmField
        val CREATOR: Parcelable.Creator<Time> = object : Parcelable.Creator<Time> {
            override fun createFromParcel(source: Parcel): Time = Time(source)
            override fun newArray(size: Int): Array<Time?> = arrayOfNulls(size)
        }
    }
}