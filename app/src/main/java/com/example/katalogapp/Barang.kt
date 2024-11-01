package com.example.katalogapp

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
@Parcelize
data class Barang(
    var namaBarang: String,
    var price: String,
    var description: String,
    var photo: Int,
) : Parcelable

