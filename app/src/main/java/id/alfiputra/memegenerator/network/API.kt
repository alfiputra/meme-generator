package id.alfiputra.memegenerator.network

import id.alfiputra.memegenerator.model.Meme
import retrofit2.Call
import retrofit2.http.GET

interface API {
    @GET("get_memes")
    fun getMemes(): Call<ArrayList<Meme>>
}