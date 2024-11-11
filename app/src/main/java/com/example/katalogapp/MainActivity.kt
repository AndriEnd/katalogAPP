package com.example.katalogapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen


class MainActivity : AppCompatActivity() {
    private lateinit var rvBarang: RecyclerView
    private val list = ArrayList<Barang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        // Pasang splash screen sistem
        installSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvBarang = findViewById(R.id.rv_item)
        rvBarang.setHasFixedSize(true)
        list.addAll(getListBarang())
        showRecyclerList()

        // Menggunakan Handler untuk menunda aksi jika diperlukan
        Handler(Looper.getMainLooper()).postDelayed({
            // Tambahkan logika setelah penundaan jika diperlukan
        }, 3000)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_list -> {
                rvBarang.layoutManager = LinearLayoutManager(this)
                true
            }
            R.id.action_grid -> {
                rvBarang.layoutManager = GridLayoutManager(this, 2)
                true
            }
            R.id.about_page -> {
                val intent = Intent(this, About_activity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun showSelectedBarang(barang: Barang) {
        Toast.makeText(this, "Detail: ${barang.namaBarang}", Toast.LENGTH_SHORT).show()
    }

    private fun getListBarang(): ArrayList<Barang> {
        val dataName = resources.getStringArray(R.array.name_item)
        val price = resources.getStringArray(R.array.price_item)
        val dataDescription = resources.getStringArray(R.array.data_deskripsi)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listBarang = ArrayList<Barang>()

        for (i in dataName.indices) {
            val barang = Barang(dataName[i], price[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listBarang.add(barang)
        }
        dataPhoto.recycle()
        return listBarang
    }

    private fun showRecyclerList() {
        rvBarang.layoutManager = LinearLayoutManager(this)
        val listBarangAdapter = ListBarangAdapter(list)
        rvBarang.adapter = listBarangAdapter

        listBarangAdapter.setOnItemClickCallback(object : ListBarangAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Barang) {
                showSelectedBarang(data)
            }
        })
    }
}
