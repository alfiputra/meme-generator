package id.alfiputra.memegenerator.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.alfiputra.memegenerator.adapter.MemeAdapter
import id.alfiputra.memegenerator.R
import id.alfiputra.memegenerator.model.Meme
import id.alfiputra.memegenerator.network.API
import id.alfiputra.memegenerator.network.ServiceBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var memeList: ArrayList<Meme>
    private lateinit var memeAdapter: MemeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rv_memeLayout)
        memeList = ArrayList()
        loadMemes()
    }

    private fun loadMemes() {
        //initiate the service
        val destinationService = ServiceBuilder.buildService(API::class.java)
        val requestCall = destinationService.getMemes()

        //make network call asynchronously
        requestCall.enqueue(object : Callback<ArrayList<Meme>> {
            override fun onResponse(
                call: Call<ArrayList<Meme>>,
                response: Response<ArrayList<Meme>>,
            ) {
                if (response.isSuccessful) {
                    memeList = response.body()!!
                    recyclerView.apply {
                        setHasFixedSize(true)
                        gridLayoutManager =
                            GridLayoutManager(this@MainActivity,
                                3,
                                LinearLayoutManager.VERTICAL,
                                false)
                        memeAdapter = MemeAdapter(response.body()!!)
                    }
                } else {
                    Toast.makeText(this@MainActivity,
                        "Something went wrong ${response.message()}",
                        Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ArrayList<Meme>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong $t",
                    Toast.LENGTH_LONG).show()
            }
        })
    }
}