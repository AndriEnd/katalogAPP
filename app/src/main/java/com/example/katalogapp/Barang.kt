package com.example.katalogapp

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
@Parcelize
data class Barang(
    var nama: String,
    var description: String,
    var photo: String,
    var harga:Int
) : Parcelable

