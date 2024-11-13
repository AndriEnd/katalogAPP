package com.example.katalogapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private lateinit var content: View
    private val viewModel: MainViewModel by viewModels() // Pastikan sudah benar

    private lateinit var rvBarang: RecyclerView
    private val list = ArrayList<Barang>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        content = findViewById(R.id.rv_item) // Pastikan ID ini benar di layout Anda

        // Menambahkan OnPreDrawListener untuk menunda hingga data siap
        content.viewTreeObserver.addOnPreDrawListener(
            object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    return if (viewModel.isDataReady.value == true) {
                        content.viewTreeObserver.removeOnPreDrawListener(this)
                        true
                    } else {
                        false
                    }
                }
            }
        )
        // Inisialisasi RecyclerView
        rvBarang = findViewById(R.id.rv_item)
        rvBarang.setHasFixedSize(true)
        list.addAll(getListBarang())
        showRecyclerList()
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

class MainViewModel : ViewModel() {

    // LiveData untuk melacak apakah data sudah siap
    private val _isDataReady = MutableLiveData(false)
    val isDataReady: LiveData<Boolean> get() = _isDataReady

    init {
        // Simulasi pemuatan data
        loadData()
    }

    private fun loadData() {
        // Gunakan coroutine untuk melakukan pemuatan data secara asinkron
        viewModelScope.launch {
            delay(2000) // Simulasi delay pemuatan data
            _isDataReady.value = true // Data sudah siap
        }
    }
}
