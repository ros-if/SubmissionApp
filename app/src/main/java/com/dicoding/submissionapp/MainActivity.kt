package com.dicoding.submissionapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var rvProducts: RecyclerView
    private val list = ArrayList<Product>()
    private lateinit var listProductAdapter: ListProductAdapter

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
            R.id.action_about -> {
                navigateToAboutActivity()
                return true
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
        dataPhoto.recycle()
        return listProduct
    }

    private fun showRecyclerList() {
        rvProducts.layoutManager = LinearLayoutManager(this)
        listProductAdapter = ListProductAdapter(list, true)
        rvProducts.adapter = listProductAdapter

        listProductAdapter.setOnItemClickCallback(object : ListProductAdapter.OnItemClickCallback{
            override fun onItemClicked(data: Product) {
                navigateToDetailActivity(data)
            }
        })
    }

    private fun navigateToDetailActivity(product: Product) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("product", product)
        startActivity(intent)
    }

    private fun navigateToAboutActivity() {
        val intent = Intent(this, AboutActivity::class.java)
        startActivity(intent)
    }
}