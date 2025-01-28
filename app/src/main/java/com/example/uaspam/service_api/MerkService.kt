package com.example.uaspam.service_api

import com.example.uaspam.model.AllMerkResponse
import com.example.uaspam.model.Merk
import com.example.uaspam.model.MerkDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface MerkService {

    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    //API untuk mendapatkan semua merk.
    //@GET("bacamerk.php"
    @GET(".")
    suspend fun getAllMerk(): AllMerkResponse

    //API untuk mendapatkan detail merk berdasarkan ID.
    //@GET("baca1merk.php")
    @GET("{id_Merk}")
    suspend fun getMerkById(@Path("id_Merk") id_Merk: String): MerkDetailResponse

    //API untuk menambahkan merk baru.
    //@POST("insertmerk.php")
    @POST("store")
    suspend fun insertMerk(@Body merk: Merk): Response<Void>

    //API untuk mengupdate data merk berdasarkan ID.
    //@PUT("editmerk.php")
    @PUT("{id_Merk}")
    suspend fun updateMerk(@Path("id_Merk") id_Merk: String, @Body merk: Merk): Response<Void>

    //API untuk menghapus data merk berdasarkan ID.
    //@DELETE("deletemerk.php")
    @DELETE("{id_Merk}")
    suspend fun deleteMerk(@Path("id_Merk") id_Merk: String): Response<Void>
}
