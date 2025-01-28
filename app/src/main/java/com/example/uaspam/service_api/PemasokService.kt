package com.example.uaspam.service_api

import com.example.uaspam.model.AllPemasokResponse
import com.example.uaspam.model.Pemasok
import com.example.uaspam.model.PemasokDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface PemasokService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    //API untuk mendapatkan semua pemasok.
    //@GET("bacapemasok.php"
    @GET(".")
    suspend fun getAllPemasok(): AllPemasokResponse

    //API untuk mendapatkan detail pemasok berdasarkan ID.
    //@GET("baca1pemasok.php")
    @GET("{id_Pemasok}")
    suspend fun getPemasokById(@Path("id_Pemasok") id_Pemasok: String): PemasokDetailResponse

    // API untuk menambahkan pemasok baru.
    //@POST("insertpemasok.php")
    @POST("store")
    suspend fun insertPemasok(@Body pemasok: Pemasok): Response<Void>

    //API untuk mengupdate data pemasok berdasarkan ID.
    //@PUT("editpemasok.php")
    @PUT("{id_Pemasok}")
    suspend fun updatePemasok(@Path("id_Pemasok") id_Pemasok: String, @Body pemasok: Pemasok): Response<Void>

    //API untuk menghapus data pemasok berdasarkan ID.
    //@DELETE("deletepemasok.php")
    @DELETE("{id_Pemasok}")
    suspend fun deletePemasok(@Path("id_Pemasok") id_Pemasok: String): Response<Void>
}
