package com.example.uaspam.service_api

import com.example.uaspam.model.AllKategoriResponse
import com.example.uaspam.model.Kategori
import com.example.uaspam.model.KategoriDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface KategoriService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    // Mendapatkan semua kategori
    //@GET("bacakategori.php"
    @GET(".")
    suspend fun getAllKategori(): AllKategoriResponse

    // Mendapatkan detail kategori berdasarkan ID
    //@GET("baca1kategori.php")
    @GET("(id_Kategori)")
    suspend fun getKategoriById(@Path("id_Kategori") id_Kategori: String): KategoriDetailResponse

    // Menambahkan kategori baru
    //@POST("insertkategori.php")
    @POST("store")
    suspend fun insertKategori(@Body kategori: Kategori)

    // Memperbarui kategori berdasarkan ID
    //@PUT("editkategori.php")
    @PUT("(id_Kategori)")
    suspend fun updateKategori(@Path("id_Kategori") id_Kategori: String, @Body kategori: Kategori)

    // Menghapus kategori berdasarkan ID
    //@DELETE("deletekategori.php")
    @DELETE("(id_Kategori)")
    suspend fun deleteKategori(@Path("id_Kategori") id_Kategori: String): Response<Void>
}