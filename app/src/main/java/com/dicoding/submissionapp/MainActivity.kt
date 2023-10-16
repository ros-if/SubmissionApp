package com.dicoding.submissionapp

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvProducts: RecyclerView
    private val list = ArrayList<Product>()
    private fun showSelectedProduct(product: Product) {
        Toast.makeText(this, "Kamu memilih " + product.name, Toast.LENGTH_SHORT).show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvProducts = findViewById(R.id.rv_products)
        rvProducts.setHasFixedSize(true)

        list.addAll(getListProducts())
        showRecyclerList()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_list -> {
                rvProducts.layoutManager = LinearLayoutManager(this)
            }
            R.id.action_grid -> {
                rvProducts.layoutManager = GridLayoutManager(this, 2)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun getListProducts(): ArrayList<Product> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listProduct = ArrayList<Product>()
        for (i in dataName.indices) {
            val product = Product(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listProduct.add(product)
        }
        return listProduct
    }

    private fun showRecyclerList() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        val listProductAdapter = ListProductAdapter(list)
        rvProducts.adapter = listProductAdapter

        listProductAdapter.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Product) {
                showSelectedProduct(data)
            }
        })
    }
}