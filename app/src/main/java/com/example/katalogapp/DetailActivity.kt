package com.example.katalogapp

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail)

        // Ambil data dari intent
        val namaBarang = intent.getStringExtra(EXTRA_NAME)
        val hargaBarang = intent.getStringExtra(EXTRA_PRICE)
        val deskripsiBarang = intent.getStringExtra(EXTRA_DESCRIPTION)
        val fotoBarang = intent.getIntExtra(EXTRA_IMAGE, 0)

        // Hubungkan komponen dengan ID
        val tvNamaBarang: TextView = findViewById(R.id.tv_name_item)
        val tvHargaBarang: TextView = findViewById(R.id.tv_price)
        val tvDeskripsiBarang: TextView = findViewById(R.id.tv_item_description)
        val ivFotoBarang: ImageView = findViewById(R.id.iv_foto_barang)

        // Set data ke komponen UI
        tvNamaBarang.text = namaBarang
        tvHargaBarang.text = hargaBarang
        tvDeskripsiBarang.text = deskripsiBarang
        ivFotoBarang.setImageResource(fotoBarang)
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_PRICE = "extra_price"
        const val EXTRA_DESCRIPTION = "extra_description"
        const val EXTRA_IMAGE = "extra_image"
    }
}
