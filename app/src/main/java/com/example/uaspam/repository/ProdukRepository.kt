package com.example.uaspam.repository

import com.example.uaspam.model.AllProdukResponse
import com.example.uaspam.model.Produk
import com.example.uaspam.model.ProdukDetailResponse

import com.example.uaspam.service_api.ProdukService
import java.io.IOException

interface ProdukRepository {
    suspend fun getproduk():AllProdukResponse
    suspend fun insertproduk(produk: Produk)
    suspend fun updateproduk(id_produk: String, produk: Produk)
    suspend fun deleteproduk(id_produk: String)
    suspend fun getprodukByid_produk(id_produk: String):ProdukDetailResponse
}

class NetworkprodukRepository(
    private val produkApiService: ProdukService
) : ProdukRepository{

    override suspend fun insertproduk(produk: Produk) {
        produkApiService.insertProduk(produk)
    }

    override suspend fun updateproduk(id_produk: String, produk: Produk) {
        produkApiService.updateProduk(id_produk, produk)
    }

    override suspend fun deleteproduk(id_produk: String) {
        try {
            val response = produkApiService.deleteProduk(id_produk)
            if (!response.isSuccessful) {
                throw IOException("Failed to delete produk. HTTP Status Code: " +
                        "${response.code()}")
            } else {
                response.message()
                println(response.message())
            }
        } catch (e:Exception){
            throw e
        }
    }

    override suspend fun getproduk(): AllProdukResponse {
        return produkApiService.getAllProduk()
    }

    override suspend fun getprodukByid_produk(id_produk: String): ProdukDetailResponse {
        return  produkApiService.getProdukById(id_produk)
    }
}