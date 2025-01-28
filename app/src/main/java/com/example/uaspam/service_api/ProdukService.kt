package com.example.uaspam.service_api

import com.example.uaspam.model.AllProdukResponse
import com.example.uaspam.model.Produk
import com.example.uaspam.model.ProdukDetailResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ProdukService {
    @Headers(
        "Accept: application/json",
        "Content-Type: application/json"
    )
    //@GET("bacaproduk.php")
    @GET(".")
    suspend fun getAllProduk(): AllProdukResponse

    //@GET("baca1produk.php")
    @GET("(id_Produk)")
    suspend fun getProdukById(@Path("id_Produk") id_Produk: String): ProdukDetailResponse

    //@POST("insertproduk.php")
    @POST("store")
    suspend fun insertProduk(@Body produk: Produk)

    //@PUT("editproduk.php")
    @PUT("(id_Produk)")
    suspend fun updateProduk(@Path("id_Produk") id_Produk: String, @Body produk: Produk)

    //@DELETE("deleteproduk.php")
    @DELETE("(id_Produk)")
    suspend fun deleteProduk(@Path("id_Produk") id_Produk: String): Response<Void>
}